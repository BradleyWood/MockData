package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

public class BlankField extends DataField<String> {

    @NotNull
    @Override
    public String generate() {
        return "";
    }

    @Override
    public boolean isValid(final Object element) {
        return "".equals(element);
    }
}
