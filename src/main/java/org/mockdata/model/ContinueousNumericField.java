package org.mockdata.model;

public abstract class ContinueousNumericField<T extends Number> extends NumericField<T> implements DataConstraint<T> {

    @Override
    public boolean isValid(Number element) {
        return super.isValid(element);
    }
}
