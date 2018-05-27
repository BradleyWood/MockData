package org.mockdata.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
public class DataRequest implements Verifiable {

    @SerializedName("num_records")
    private final Integer numRecords;

    @SerializedName("format")
    private final Format format;

    @SerializedName("field_config")
    private final @Getter List<FieldConfig> fieldConfig;

    @NotNull
    public Integer getNumRecords() {
        if (numRecords == null) return 1000;
        return numRecords;
    }

    @NotNull
    public Format getFormat() {
        if (format == null) return Format.CSV;
        return format;
    }

    @Override
    public boolean isValid() {
        if (getNumRecords() < 1 || getNumRecords() > 1000)
            return false;

        return fieldConfig != null && !fieldConfig.isEmpty() && fieldConfig.stream().allMatch(FieldConfig::isValid);
    }

}
