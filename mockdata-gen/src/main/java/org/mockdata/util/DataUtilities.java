package org.mockdata.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;
import org.mockdata.data.Gender;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataUtilities {

    public static final String MALE_FIRST_NAMES = "data/census-dist-male-first.csv";
    public static final String FEMALE_FIRST_NAMES = "data/census-dist-female-first.csv";
    public static final String LAST_NAMES = "data/census-dist-all-last.csv";

    private static final Random random = new Random();

    private static List<String> countries;

    private static List<String> maleFirstNames;
    private static List<Double> mfnPercentiles;

    private static List<String> femaleFirstNames;
    private static List<Double> ffnPercentiles;

    private static List<String> lastNames;
    private static List<Double> lnPercentiles;

    public static List<CSVRecord> readCsv(final String path) throws IOException {
        InputStream in = DataUtilities.class.getClassLoader().getResourceAsStream(path);
        if (in == null) {
            System.out.println("Path not found: "+ path);
            in = new FileInputStream(path);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        CSVParser parser = new CSVParser(br, CSVFormat.EXCEL);

        return parser.getRecords();
    }

    private static List<String> getNames(final List<CSVRecord> records) {
        return records.stream().map(r -> r.get(0)).collect(Collectors.toList());
    }

    private static List<Double> getPercentiles(final List<CSVRecord> records) {
        return records.stream().map(r -> Double.parseDouble(r.get(2))).collect(Collectors.toList());
    }

    /**
     * A best guess of Gender for the given name
     *
     * @param name The first name
     * @return Best Guess Gender or null if the name is not present in the database
     */
    public static Gender getGender(final String name) {
        try {
            if (maleFirstNames == null) {
                loadMaleNames();
            }
            if (femaleFirstNames == null) {
                loadFemaleNames();
            }
            int maleIdx = maleFirstNames.indexOf(name.toUpperCase());
            int femaleIdx = femaleFirstNames.indexOf(name.toUpperCase());

            if (maleIdx >= 0 && femaleIdx >= 0) {
                double maleP = mfnPercentiles.get(maleIdx);
                double femaleP = ffnPercentiles.get(femaleIdx);
                return maleP < femaleP ? Gender.Male : Gender.Female;
            } else if (maleIdx >= 0) {
                return Gender.Male;
            } else if (femaleIdx >= 0) {
                return Gender.Female;
            }
        } catch (Exception e) {
            System.err.println("Error: Cannot load names");
        }
        return null;
    }

    public static boolean isLastName(final String name) {
        try {
            if (lastNames == null) {
                loadLastNames();
            }
        } catch (Exception e) {
            System.err.println("Error: Cannot load last names");
            return false;
        }
        return lastNames.contains(name.toUpperCase());
    }

    private static String selectName(final List<String> names, final List<Double> percentiles, final double percentile) {
        final double key = percentile * percentiles.get(percentiles.size() - 1);
        final int index = searchName(percentiles, key);
        final String name = names.get(index).toLowerCase();

        if (name.isEmpty()) {
            System.err.println("Failed to select random name");
            return null;
        }

        char[] letters = name.toCharArray();
        letters[0] = Character.toUpperCase(letters[0]);

        return new String(letters);
    }

    private static void loadMaleNames() throws IOException {
        final List<CSVRecord> records = readCsv(MALE_FIRST_NAMES);
        maleFirstNames = getNames(records);
        mfnPercentiles = getPercentiles(records);
    }

    private static void loadFemaleNames() throws IOException {
        final List<CSVRecord> records = readCsv(FEMALE_FIRST_NAMES);
        femaleFirstNames = getNames(records);
        ffnPercentiles = getPercentiles(records);
    }

    private static void loadLastNames() throws IOException {
        final List<CSVRecord> records = readCsv(LAST_NAMES);
        lastNames = getNames(records);
        lnPercentiles = getPercentiles(records);
    }

    public static String selectFirstName(final boolean male, final double percentile) {
        try {
            if (male && maleFirstNames == null) {
                loadMaleNames();
            } else if (!male && femaleFirstNames == null) {
                loadFemaleNames();
            }
        } catch (IOException e) {
            System.err.println("Error: Cannot find first names");
            return null;
        }

        return selectName(male ? maleFirstNames : femaleFirstNames, male ? mfnPercentiles : ffnPercentiles, percentile);
    }

    private static void loadCountries() throws IOException {
        countries = new ArrayList<>();
        for (final CSVRecord country : readCsv("data/countries.csv")) {
            countries.add(country.get(0));
        }
    }

    public static String randomCountry() {
        if (countries == null) {
            try {
                loadCountries();
            } catch (final IOException e) {
                return null;
            }
        }

        return countries.get(random.nextInt(countries.size()));
    }

    public static boolean isCountry(@NotNull final String country) {
        if (countries == null) {
            try {
                loadCountries();
            } catch (final IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        for (final String name : countries) {
            if (name.toLowerCase().equals(country.toLowerCase()))
                return true;
        }
        return false;
    }

    public static String selectMaleName(final double percentile) {
        return selectFirstName(true, percentile);
    }

    public static String randomMaleName() {
        return selectMaleName(random.nextDouble());
    }

    public static String selectFemaleName(final double percentile) {
        return selectFirstName(false, percentile);
    }

    public static String randomFemaleName() {
        return selectFemaleName(random.nextDouble());
    }

    public static String selectLastName(final double percentile) {
        try {
            if (lastNames == null) {
                loadLastNames();
            }
        } catch (Exception e) {
            System.err.println("Error: Cannot find last names");
            return null;
        }
        return selectName(lastNames, lnPercentiles, percentile);
    }

    public static String randomLastName() {
        return selectLastName(random.nextDouble());
    }

    private static int searchName(List<Double> percentiles, double key) {
        return searchName(percentiles, key, 0, percentiles.size() - 1);
    }

    private static int searchName(List<Double> percentiles, double key, int low, int high) {
        if (high < low) {
            if (high + 1 < percentiles.size()) {
                return high + 1;
            }
            return percentiles.size() - 1;
        }
        int mid = (low + high) / 2;

        double xd = percentiles.get(mid);
        if (xd > key) {
            return searchName(percentiles, key, low, mid - 1);
        } else if (xd < key) {
            return searchName(percentiles, key, mid + 1, high);
        }
        return mid;
    }
}
