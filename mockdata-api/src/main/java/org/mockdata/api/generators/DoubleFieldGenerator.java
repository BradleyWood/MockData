package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.DoubleField;

import java.util.Map;

class DoubleFieldGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        final Object min = parameters.getOrDefault("min", Integer.MIN_VALUE);
        final Object max = parameters.getOrDefault("min", Integer.MAX_VALUE);

        if (min instanceof Number && max instanceof Number) {
            return ((Number) min).doubleValue() <= ((Number) max).doubleValue();
        }

        return false;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new DoubleField(((Number) parameters.getOrDefault("min", Integer.MIN_VALUE)).doubleValue(),
                ((Number) parameters.getOrDefault("max", Integer.MAX_VALUE)).doubleValue());
    }

    @Override
    String getTypeName() {
        return "double";
    }
}
