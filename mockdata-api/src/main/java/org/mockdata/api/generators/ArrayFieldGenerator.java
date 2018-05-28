package org.mockdata.api.generators;

import com.google.gson.Gson;
import org.mockdata.api.model.FieldConfig;
import org.mockdata.fields.ArrayField;
import org.mockdata.fields.DataField;

import java.util.Map;

public class ArrayFieldGenerator extends FieldGenerator {

    @Override
    boolean isValid(final Map<String, Object> parameters) {
        final Object fieldParam = parameters.get("field");
        final Object size = parameters.get("size");

        if (fieldParam == null || size == null)
            return false;

        if (size instanceof Number)
            parameters.put("size", ((Number) size).intValue());

        Gson gson = new Gson();

        final FieldConfig config = gson.fromJson(fieldParam.toString(), FieldConfig.class);

        if (config == null)
            return false;

        parameters.put("field", config.instantiate());

        return true;
    }

    @Override
    DataField instantiate(final Map<String, Object> parameters) {
        return new ArrayField((DataField) parameters.get("field"), (Integer) parameters.get("size"));
    }

    @Override
    String getTypeName() {
        return "array";
    }
}
