package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

public class BlankField extends DataField<String> {

    @NotNull
    @Override
    public String generate() {
        return "";
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
