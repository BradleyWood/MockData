package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.DoubleField;

import java.util.Map;

class DoubleFieldGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        final Object min = parameters.getOrDefault("min", Integer.MIN_VALUE);
        final Object max = parameters.getOrDefault("max", Integer.MAX_VALUE);

        if (min instanceof Number && max instanceof Number) {
            Double l = ((Number) min).doubleValue();
            Double h = ((Number) max).doubleValue();
            if (l <= h) {
                parameters.put("min", l);
                parameters.put("max", h);
                return true;
            }
        }

        return false;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new DoubleField((Double) parameters.get("min"), (Double) parameters.get("max"));
    }

    @Override
    String getTypeName() {
        return "double";
    }
}
