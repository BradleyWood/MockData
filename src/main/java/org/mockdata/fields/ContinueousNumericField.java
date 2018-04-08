package org.mockdata.fields;

public abstract class ContinueousNumericField<T extends Number> extends NumericField<T> {

    @Override
    public boolean isValid(Number element) {
        return super.isValid(element);
    }
}
