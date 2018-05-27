package org.mockdata.api.model;


import lombok.Getter;
import lombok.Setter;
import org.mockdata.fields.DataField;

import java.util.Map;

public abstract class FieldGenerator implements Verifiable {

    private @Getter @Setter FieldGenerator successor;

    DataField instantiate(final String type, final Map<String, Object> parameters) {
        if (type.toLowerCase().equals(getTypeName().toLowerCase()))
            return instantiate(parameters);

        if (getSuccessor() != null)
            return getSuccessor().instantiate(type, parameters);

        return null;
    }

    abstract DataField instantiate(final Map<String, Object> parameters);


    abstract String getTypeName();

}
