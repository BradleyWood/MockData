package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.SelectorField;

import java.util.List;
import java.util.Map;

class SelectorFieldGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        return parameters.get("values") instanceof List;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new SelectorField<>((List<?>)parameters.get("values"));
    }

    @Override
    String getTypeName() {
        return "selector";
    }
}
