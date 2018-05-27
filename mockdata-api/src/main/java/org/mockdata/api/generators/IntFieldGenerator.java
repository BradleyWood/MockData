package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.IntField;

import java.util.Map;

public class IntFieldGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        final Object min = parameters.getOrDefault("min", Integer.MIN_VALUE);
        final Object max = parameters.getOrDefault("min", Integer.MAX_VALUE);

        if (min instanceof Number && max instanceof Number) {
            return ((Number) min).intValue() <= ((Number) max).intValue();
        }

        return false;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new IntField(((Number) parameters.getOrDefault("min", Integer.MIN_VALUE)).intValue(),
                ((Number) parameters.getOrDefault("max", Integer.MAX_VALUE)).intValue());
    }

    @Override
    String getTypeName() {
        return "integer";
    }
}
