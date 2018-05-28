package org.mockdata.api.generators;

import org.mockdata.fields.BlankField;
import org.mockdata.fields.DataField;

import java.util.Map;

class BlankFieldGenerator extends FieldGenerator {

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new BlankField();
    }

    @Override
    String getTypeName() {
        return "blank";
    }

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        return true;
    }
}
