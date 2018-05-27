package org.mockdata.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.mockdata.fields.DataField;

@EqualsAndHashCode
@AllArgsConstructor
public abstract class FieldConfig implements Verifiable {

    @SerializedName("invalid_proportion")
    private final Double invalidProportion;

    abstract DataField instantiate();

    @NotNull
    public Double getInvalidProportion() {
        if (invalidProportion == null) return 0.0;
        return invalidProportion;
    }

    @Override
    public boolean isValid() {
        return getInvalidProportion() >= 0 && getInvalidProportion() <= 1;
    }
}
