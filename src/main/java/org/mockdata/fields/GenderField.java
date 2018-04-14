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
            Gender gender = DataUtilities.getGender(firstName);
            if (gender != null)
                return gender;
        }

        return random.nextBoolean() ? Gender.Male : Gender.Female;
    }

    @Override
    public boolean isValid(Object element) {
        return element instanceof Gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

}
