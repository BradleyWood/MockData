package org.mockdata.api.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

@EqualsAndHashCode
@AllArgsConstructor
public class DataRequest {

    @SerializedName("num_records")
    private final Integer numRecords;

    @SerializedName("invalid_proportion")
    private final Double invalidProportion;

    @SerializedName("format")
    private final Format format;

    @NotNull
    public Integer getNumRecords() {
        if (numRecords == null) return 1000;
        return numRecords;
    }

    @NotNull
    public Double getInvalidProportion() {
        if (invalidProportion == null) return 0.0;
        return invalidProportion;
    }

    @NotNull
    public Format getFormat() {
        if (format == null) return Format.CSV;
        return format;
    }
}
