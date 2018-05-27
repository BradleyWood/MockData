package org.mockdata.api.generators;


import lombok.Getter;
import lombok.Setter;
import org.mockdata.fields.DataField;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class FieldGenerator {

    private @Getter @Setter FieldGenerator successor;

    public DataField instantiate(final String type, final Map<String, Object> parameters) {
        if (type.toLowerCase().equals(getTypeName().toLowerCase())) {
            if (!isValid(parameters))
                return null;

            return instantiate(parameters);
        }

        if (getSuccessor() != null)
            return getSuccessor().instantiate(type, parameters);

        return null;
    }

    abstract boolean isValid(final Map<String, Object> parameters);

    abstract DataField instantiate(final Map<String, Object> parameters);

    abstract String getTypeName();

    public static FieldGenerator getChain() {
        final List<FieldGenerator> generators = Arrays.asList(
                new BooleanFieldGenerator(),
                new BlankFieldGenerator(),
                new ConstantFieldGenerator(),
                new IntFieldGenerator()
        );
        Iterator<FieldGenerator> iterator = generators.iterator();

        FieldGenerator head = iterator.next();
        FieldGenerator tmp = head;

        while (iterator.hasNext()) {
            FieldGenerator next = iterator.next();
            tmp.setSuccessor(next);
            tmp = next;
        }

        return head;
    }
}
