package org.mockdata.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.mockdata.api.generators.FieldGenerator;
import org.mockdata.fields.DataField;

import java.util.*;

@EqualsAndHashCode
public class FieldConfig implements Verifiable {

    @SerializedName("type")
    private String type;

    @SerializedName("invalid_proportion")
    private Double invalidProportion;

    @SerializedName("independent")
    private Boolean independent;

    @SerializedName("parameters")
    private Map<String, Object> parameters;

    @Expose(serialize = false, deserialize = false)
    private static @Getter @Setter FieldGenerator chain = FieldGenerator.getChain();

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

    @NotNull
    public Map<String, Object> getParameters() {
        if (parameters == null) return Collections.EMPTY_MAP;
        return parameters;
    }

    @NotNull
    public Boolean isIndependent() {
        if (independent == null) return false;
        return independent;
    }

    @Override
    public boolean isValid() {
        return type != null && getInvalidProportion() >= 0 && getInvalidProportion() <= 1;
    }

}
