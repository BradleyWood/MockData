package org.mockdata.api.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import lombok.EqualsAndHashCode;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import org.mockdata.api.generators.Field;
import org.mockdata.fields.DataField;

import java.util.*;

@EqualsAndHashCode
public class FieldConfig implements Verifiable {

    @SerializedName("type")
    private String type = null;

    @SerializedName("invalid_proportion")
    private double invalidProportion = 0.0;

    @SerializedName("independent")
    private boolean independent = false;

    @SerializedName("parameters")
    private Map<String, Object> parameters;

    public DataField instantiate() {
        if (!isValid())
            return null;

        final DataField field = Field.getField(type);

        if (field == null)
            return null;

        if (parameters != null) {
            final InstanceCreator<DataField> creator = t -> field;
            final Gson gson = new GsonBuilder().registerTypeAdapter(DataField.class, creator).create();
            gson.fromJson(gson.toJsonTree(parameters), field.getClass());
        }

        field.setIndependence(independent);

        return field;
    }

    public double getInvalidProportion() {
        return invalidProportion;
    }

    @NotNull
    public Map<String, Object> getParameters() {
        if (parameters == null) return new HashMap<>();
        return parameters;
    }

    public boolean isIndependent() {
        return independent;
    }

    @Override
    public boolean isValid() {
        return type != null && getInvalidProportion() >= 0 && getInvalidProportion() <= 1;
    }
}
