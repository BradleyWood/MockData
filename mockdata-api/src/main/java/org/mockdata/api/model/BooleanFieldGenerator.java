package org.mockdata.api.model;

import org.mockdata.fields.BooleanField;
import org.mockdata.fields.DataField;

import java.util.Map;

public final class BooleanFieldGenerator extends FieldGenerator {

    BooleanFieldGenerator() {
    }

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
