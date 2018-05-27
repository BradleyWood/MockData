package org.mockdata.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.mockdata.fields.DataField;

import java.util.*;

@EqualsAndHashCode
public class FieldConfig implements Verifiable {

    @SerializedName("type")
    private String type;

    @SerializedName("invalid_proportion")
    private Double invalidProportion;

    @SerializedName("parameters")
    private Map<String, Object> parameters;

    @Expose(serialize = false, deserialize = false)
    private static final FieldGenerator chain = getChain();

    public DataField instantiate() {
        if (!isValid())
            return null;

        return chain.instantiate(type, getParameters());
    }

    @NotNull
    public Double getInvalidProportion() {
        if (invalidProportion == null) return 0.0;
        return invalidProportion;
    }

    public Map<String, Object> getParameters() {
        if (parameters == null) return Collections.EMPTY_MAP;
        return parameters;
    }

    @Override
    public boolean isValid() {
        return type != null && getInvalidProportion() >= 0 && getInvalidProportion() <= 1;
    }

    private static FieldGenerator getChain() {
        final List<FieldGenerator> generators = Arrays.asList(
                new BooleanFieldGenerator(),
                new BlankFieldGenerator(),
                new ConstantFieldGenerator()
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
