package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

public class BooleanField extends DataField<Boolean> {

    @NotNull
    @Override
    public Boolean generate() {
        return random.nextBoolean();
    }

    @Override
    public Boolean generateExtremes() {
        return null;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

}
