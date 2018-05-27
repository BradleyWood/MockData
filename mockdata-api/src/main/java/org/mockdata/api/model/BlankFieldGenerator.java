package org.mockdata.api.model;

import org.mockdata.fields.BlankField;
import org.mockdata.fields.DataField;

import java.util.Map;

public class BlankFieldGenerator extends FieldGenerator {

    BlankFieldGenerator() {
    }

    @Override
    DataField instantiate(Map<String, Object> parameters) {
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
