package org.mockdata.fields;

import org.jetbrains.annotations.Nullable;

public abstract class ContinuousNumericField<T extends Number> extends NumericField<T> {

    public ContinuousNumericField() {
        this(null, null);
    }

    public ContinuousNumericField(@Nullable final Double min, @Nullable final Double max) {
        super(min, max);
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }
}
