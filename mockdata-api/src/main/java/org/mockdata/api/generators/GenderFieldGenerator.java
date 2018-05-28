package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.GenderField;

import java.util.Map;

class GenderFieldGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        return true;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new GenderField();
    }

    @Override
    String getTypeName() {
        return "gender";
    }
}
