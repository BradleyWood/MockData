package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

public class BooleanField extends DataField<Boolean> {

    @NotNull
    @Override
    public Boolean generate() {
        return random.nextBoolean();
    }

    @Override
    public boolean isValid(final Object element) {
        return element instanceof Boolean;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

}
