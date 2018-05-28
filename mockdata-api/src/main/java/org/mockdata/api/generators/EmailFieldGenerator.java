package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.EmailField;

import java.util.Map;

public class EmailFieldGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        return true;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new EmailField();
    }

    @Override
    String getTypeName() {
        return "email";
    }
}
