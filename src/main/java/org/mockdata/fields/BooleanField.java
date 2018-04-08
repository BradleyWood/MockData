package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BooleanField extends DataField<Boolean> {

    private final Random random = new Random();

    @NotNull
    @Override
    public Boolean generate() {
        return random.nextBoolean();
    }

    @Override
    public boolean isValid(Object element) {
        return element instanceof Boolean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

}
