package org.mockdata.model;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BooleanField extends DataField<Boolean> implements Verifiable<Boolean> {

    private final Random random = new Random();

    @NotNull
    @Override
    public Boolean generate() {
        return random.nextBoolean();
    }

    @Override
    public boolean isValid(Boolean element) {
        return element != null;
    }
}
