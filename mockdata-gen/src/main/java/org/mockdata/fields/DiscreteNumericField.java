package org.mockdata.fields;

import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;

public abstract class DiscreteNumericField<T extends Number> extends NumericField<T> {

    public DiscreteNumericField() {
        this(null, null);
    }

    public DiscreteNumericField(@Nullable final Number min, @Nullable final Number max) {
        super(min, max);
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
