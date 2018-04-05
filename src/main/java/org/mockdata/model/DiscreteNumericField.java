package org.mockdata.model;

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
    public boolean isValid(Number element) {
        if (!super.isValid(element) || (element instanceof Float || element instanceof Double) || (element instanceof BigDecimal))
            return false;

        if (element instanceof Integer)
            return element.longValue() <= Integer.MAX_VALUE && element.longValue() >= Integer.MIN_VALUE;
        else if (element instanceof Short)
            return element.longValue() <= Short.MAX_VALUE && element.longValue() >= Short.MIN_VALUE;
        else if (element instanceof Byte)
            return element.longValue() <= Byte.MAX_VALUE && element.longValue() >= Byte.MIN_VALUE;

        return true;
    }
}
