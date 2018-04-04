package org.mockdata.model;

import org.jetbrains.annotations.Nullable;

public abstract class NumericField extends DataField<Number> implements DataConstraint<Number> {

    @Nullable
    private final Number min;
    @Nullable
    private final Number max;

    public NumericField() {
        this(null, null);
    }

    public NumericField(final Number min, final Number max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(Number element) {
        if (min != null) {
            if (Double.compare(min.doubleValue(), element.doubleValue()) >= 0) {
                return false;
            }
        }
        if (max != null) {
            return Double.compare(max.doubleValue(), element.doubleValue()) > 0;
        }
        return true;
    }

}
