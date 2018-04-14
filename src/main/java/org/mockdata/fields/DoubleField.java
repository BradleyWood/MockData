package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class DoubleField extends ContinuousNumericField<Double> {

    public static final double DEFAULT_MIN = 0D;
    public static final double DEFAULT_MAX = 1D;

    private final Random random = new Random();

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
