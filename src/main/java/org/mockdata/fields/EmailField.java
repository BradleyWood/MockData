package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmailField extends DataField<String> {

    public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[" +
            "01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x" +
            "0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Nullable
    @DependentField(dependentOn = FirstNameField.class)
    private String firstName = null;

    @Nullable
    @DependentField(dependentOn = LastNameField.class)
    private String lastName = null;

    @NotNull
    @Override
    public String generate() {
        final String fn = firstName != null ? firstName : "John";
        final String ln = lastName != null ? lastName : "Doe";

        return fn + "." + ln + "@gmail.com";
    }

    @Override
    public String generateExtremes() {
        return null;
    }

    @Override
    public boolean isValid(final Object element) {
        if (!(element instanceof String))
            return false;

        return ((String) element).toLowerCase().matches(EMAIL_REGEX);
    }
}
