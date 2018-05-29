package org.mockdata.api.generators;

import org.mockdata.fields.DataField;
import org.mockdata.fields.IPAddressField;

import java.util.Map;

public class IPFieldGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        return true;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new IPAddressField();
    }

    @Override
    String getTypeName() {
        return "ip_address";
    }
}
