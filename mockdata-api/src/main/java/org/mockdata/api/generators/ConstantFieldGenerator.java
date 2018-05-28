package org.mockdata.api.generators;

import org.mockdata.fields.ConstantField;
import org.mockdata.fields.DataField;

import java.util.Map;

class ConstantFieldGenerator extends FieldGenerator {

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new ConstantField(parameters.get("value"));
    }

    @Override
    String getTypeName() {
        return "constant";
    }

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        return parameters.containsKey("value");
    }
}
