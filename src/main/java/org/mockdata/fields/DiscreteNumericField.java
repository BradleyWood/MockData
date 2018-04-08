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
    public boolean isValid(Object element) {
        if (!super.isValid(element) || !(element instanceof Number) || (element instanceof Float || element instanceof Double) || (element instanceof BigDecimal))
            return false;

        Number num = (Number) element;

        if (element instanceof Integer)
            return num.longValue() <= Integer.MAX_VALUE && num.longValue() >= Integer.MIN_VALUE;
        else if (element instanceof Short)
            return num.longValue() <= Short.MAX_VALUE && num.longValue() >= Short.MIN_VALUE;
        else if (element instanceof Byte)
            return num.longValue() <= Byte.MAX_VALUE && num.longValue() >= Byte.MIN_VALUE;

        return true;
    }
}
