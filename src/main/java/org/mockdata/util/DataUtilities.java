package org.mockdata.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DataUtilities {

    public static final String MALE_FIRST_NAMES = "data/census-dist-male-first.csv";
    public static final String FEMALE_FIRST_NAMES = "data/census-dist-female-first.csv";
    public static final String LAST_NAMES = "data/census-dist-all-last.csv";

    private static final Random random = new Random();

    private static List<String> maleFirstNames;
    private static List<Double> mfnPercentiles;

    private static List<String> femaleFirstNames;
    private static List<Double> ffnPercentiles;

    private static List<String> lastNames;
    private static List<Double> lnPercentiles;

    public static List<CSVRecord> readCsv(final String path) throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get(path));
        CSVParser parser = new CSVParser(br, CSVFormat.EXCEL);

        return parser.getRecords();
    }

    private static List<String> getNames(final List<CSVRecord> records) {
        return records.stream().map(r -> r.get(0)).collect(Collectors.toList());
    }

    private static List<Double> getPercentiles(final List<CSVRecord> records) {
        return records.stream().map(r -> Double.parseDouble(r.get(2))).collect(Collectors.toList());
    }

    private static String selectName(final List<String> names, final List<Double> percentiles) {
        final double key = random.nextDouble() * percentiles.get(percentiles.size() - 1);
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

    public static String randomFirstName(final boolean male) {
        try {
            if (male && maleFirstNames == null) {
                final List<CSVRecord> records = readCsv(MALE_FIRST_NAMES);
                maleFirstNames = getNames(records);
                mfnPercentiles = getPercentiles(records);
            } else if (!male && femaleFirstNames == null) {
                final List<CSVRecord> records = readCsv(FEMALE_FIRST_NAMES);
                femaleFirstNames = getNames(records);
                ffnPercentiles = getPercentiles(records);
            }
        } catch (IOException e) {
            System.err.println("Error: Cannot find first names");
            return null;
        }

        return selectName(male ? maleFirstNames : femaleFirstNames, male ? mfnPercentiles : ffnPercentiles);
    }

    public static String randomMaleName() {
        return randomFirstName(true);
    }

    public static String randomFemaleName() {
        return randomFirstName(false);
    }

    public static String randomLastName() {
        try {
            if (lastNames == null) {
                final List<CSVRecord> records = readCsv(LAST_NAMES);
                lastNames = getNames(records);
                lnPercentiles = getPercentiles(records);
            }
        } catch (Exception e) {
            System.err.println("Error: Cannot find last names");
            return null;
        }
        return selectName(lastNames, lnPercentiles);
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
