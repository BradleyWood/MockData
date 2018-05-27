package org.mockdata.api.model;

import com.google.gson.annotations.SerializedName;

public enum Format {
    @SerializedName("csv")
    CSV,
    @SerializedName("json")
    JSON,
    @SerializedName("excel")
    EXCEL
}
