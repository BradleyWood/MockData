package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class GenderField extends DataField<GenderField.GENDER> {

    private final Random random = new Random();

    @NotNull
    @Override
    public GENDER generate() {
        return random.nextBoolean() ? GENDER.Male : GENDER.Female;
    }

    @Override
    public boolean isValid(Object element) {
        return element instanceof GENDER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    public enum GENDER {
        Male,
        Female
    }
}