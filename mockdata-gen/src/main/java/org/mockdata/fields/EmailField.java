package org.mockdata.fields;

import org.apache.commons.csv.CSVRecord;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockdata.util.DataUtilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmailField extends DataField<String> {

    private static List<String> domainNames = null;
    private static boolean domainsNotFound = false;

    @Nullable
    @DependentField(dependentOn = FirstNameField.class)
    private String firstName = null;

    @Nullable
    @DependentField(dependentOn = LastNameField.class)
    private String lastName = null;

    private void loadDomains() {
        try {
            domainNames = new ArrayList<>();
            for (CSVRecord strings : DataUtilities.readCsv("data/email-domains.csv")) {
                domainNames.add(strings.get(0));
            }
        } catch (IOException e) {
            System.err.println("Warning: File data/email-domains.csv not found");
            domainsNotFound = true;
        }
    }

    private String getDomain() {
        if (domainNames == null && !domainsNotFound) {
            loadDomains();
        }
        if (domainsNotFound) {
            return "@gmail.com";
        }
        return domainNames.get(random.nextInt(domainNames.size()));
    }

    @NotNull
    @Override
    public String generate() {
        final String fn = firstName != null ? firstName : "John";
        final String ln = lastName != null ? lastName : "Doe";

        return fn + "." + ln + "@" + getDomain();
    }

    @Override
    public String generateExtremes() {
        return null;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
