package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.FirstNameField;

import java.util.Map;

public class FirstNameGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        return true;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new FirstNameField();
    }

    @Override
    String getTypeName() {
        return "first_name";
    }
}