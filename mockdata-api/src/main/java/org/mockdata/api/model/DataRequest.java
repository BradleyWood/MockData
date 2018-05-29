package org.mockdata.api.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.mockdata.RecordEngine;
import org.mockdata.fields.DataField;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collection;
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

    public String processRequest() {
        final RecordEngine re = new RecordEngine();

        for (final FieldConfig config : getFieldConfig()) {
            final DataField field = config.instantiate();
            if (field == null)
                return null;

            field.setExtremeProportion(config.getInvalidProportion());
            field.setIndependence(config.isIndependent());
            re.addDataFields(field);
        }

        if (getFormat() == Format.CSV) {
            try {
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final PrintStream pw = new PrintStream(baos);
                re.writeRecords(pw, getNumRecords());

                return new String(baos.toByteArray());
            } catch (final Exception e) {
                e.printStackTrace();
                return null;
            }
        } else if (getFormat() == Format.JSON) {
            final Collection<Object[]> records = re.generate(getNumRecords());
            final Gson gson = new Gson();

            return gson.toJson(records);
        } else {
            return null;
        }
    }

}
