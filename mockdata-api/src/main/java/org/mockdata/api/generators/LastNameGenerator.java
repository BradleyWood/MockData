package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.LastNameField;

import java.util.Map;

class LastNameGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        return true;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new LastNameField();
    }

    @Override
    String getTypeName() {
        return "last_name";
    }
}
