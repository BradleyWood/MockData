package org.mockdata.model;

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
    public boolean isValid(GENDER element) {
        return element != null;
    }

    enum GENDER {
        Male,
        Female
    }
}
