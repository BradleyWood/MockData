package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

public class DoubleField extends ContinuousNumericField<Double> {

    public static final double DEFAULT_MIN = 0D;
    public static final double DEFAULT_MAX = 1D;

    public DoubleField() {
        this(DEFAULT_MIN, DEFAULT_MAX);
    }

    public DoubleField(final double min, final double max) {
        super(min, max);
    }

    @NotNull
    @Override
    public Double generate() {
        double min = getMin().doubleValue();
        double max = getMax().doubleValue();

        return min + (max - min) * random.nextDouble();
    }

    @Override
    public boolean isValid(Object element) {
        if (!(element instanceof Double))
            return false;
        return super.isValid(element);
    }
}
