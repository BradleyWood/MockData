package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;
import org.mockdata.data.Gender;

import java.util.Random;

public class GenderField extends DataField<Gender> {

    private final Random random = new Random();

    @NotNull
    @Override
    public Gender generate() {
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
