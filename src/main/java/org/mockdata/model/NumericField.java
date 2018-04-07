package org.mockdata.model;

import org.jetbrains.annotations.Nullable;

public abstract class NumericField<T extends Number> extends DataField<T> {

    @Nullable
    private final Number min;
    @Nullable
    private final Number max;

    public NumericField() {
        this(null, null);
    }

    public NumericField(@Nullable final Number min, @Nullable final Number max) {
        if (min != null && max != null && Double.compare(min.doubleValue(), max.doubleValue()) > 0)
            throw new IllegalArgumentException("Constraints error, max: " + max + " is less than min: " + min);
        this.min = min;
        this.max = max;
    }

    @Nullable
    public Number getMin() {
        return min;
    }

    @Nullable
    public Number getMax() {
        return max;
    }

    @Override
    public boolean isValid(Number element) {
        if (min != null) {
            if (Double.compare(min.doubleValue(), element.doubleValue()) > 0) {
                return false;
            }
        }
        if (max != null) {
            return Double.compare(max.doubleValue(), element.doubleValue()) >= 0;
        }
        return true;
    }

}
