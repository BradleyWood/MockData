package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;
import org.mockdata.util.DataUtilities;

public class LastNameField extends DataField<String> {

    @NotNull
    @Override
    public String generate() {
        final String name = DataUtilities.selectLastName(realDistribution.sample());
        return name != null ? name : "Doe";
    }

    @Override
    public String generateExtremes() {
        return null;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
