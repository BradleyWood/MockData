package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.RowNumberField;

import java.util.Map;

class RowNumberGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        Object start = parameters.getOrDefault("start", 0);

        if (!(start instanceof Number))
            return false;

        parameters.put("start", ((Number) start).intValue());
        return true;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new RowNumberField((Integer) parameters.get("start"));
    }

    @Override
    String getTypeName() {
        return "row_number";
    }
}
