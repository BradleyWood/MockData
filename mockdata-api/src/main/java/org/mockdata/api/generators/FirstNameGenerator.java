package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.FirstNameField;

import java.util.Map;

public class FirstNameGenerator extends FieldGenerator {

    @Override
    boolean isValid(Map<String, Object> parameters) {
        return true;
    }

    @Override
    DataField instantiate(Map<String, Object> parameters) {
        return new FirstNameField();
    }

    @Override
    String getTypeName() {
        return "first_name";
    }
}
