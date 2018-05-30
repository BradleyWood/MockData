package org.mockdata.api.generators;

import org.mockdata.fields.CountryField;
import org.mockdata.fields.DataField;

import java.util.Map;

public class CountryFieldGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        return true;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new CountryField();
    }

    @Override
    String getTypeName() {
        return "country";
    }
}
