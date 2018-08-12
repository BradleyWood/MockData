package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;
import org.mockdata.data.Gender;
import org.mockdata.util.DataUtilities;

public class GenderField extends DataField<Gender> {

    @DependentField(dependentOn = FirstNameField.class)
    private String firstName;

    @NotNull
    @Override
    public Gender generate() {
        if (firstName != null && !firstName.isEmpty()) {
            final Gender gender = DataUtilities.getGender(firstName);
            if (gender != null)
                return gender;
        }

        return random.nextBoolean() ? Gender.Male : Gender.Female;
    }

    @Override
    public Gender generateExtremes() {
        return null;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

}
