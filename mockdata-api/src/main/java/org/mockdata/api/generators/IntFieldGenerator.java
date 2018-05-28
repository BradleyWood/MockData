package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.IntField;

import java.util.Map;

class IntFieldGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        final Object min = parameters.getOrDefault("min", Integer.MIN_VALUE);
        final Object max = parameters.getOrDefault("max", Integer.MAX_VALUE);

        if (min instanceof Number && max instanceof Number) {
            Integer l = ((Number) min).intValue();
            Integer h = ((Number) max).intValue();
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
        return new IntField((Integer) parameters.get("min"), (Integer) parameters.get("max"));
    }

    @Override
    String getTypeName() {
        return "integer";
    }
}
