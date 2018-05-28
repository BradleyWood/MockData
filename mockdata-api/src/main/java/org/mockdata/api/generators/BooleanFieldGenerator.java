package org.mockdata.api.generators;

import org.mockdata.fields.BooleanField;
import org.mockdata.fields.DataField;

import java.util.Map;

final class BooleanFieldGenerator extends FieldGenerator {

    @Override
    final DataField instantiate(final Map<String, Object> parameters) {
        return new BooleanField();
    }

    @Override
    String getTypeName() {
        return "boolean";
    }

    @Override
    public boolean isValid(final Map<String, Object> parameters) {
        return true;
    }
}
