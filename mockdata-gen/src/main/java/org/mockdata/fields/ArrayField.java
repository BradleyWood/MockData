package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

public class ArrayField extends DataField {

    @NotNull
    private final DataField field;
    private int size;

    public ArrayField(@NotNull final DataField field, final int size) {
        this.field = field;
        this.size = size;
    }

    private ArrayField() {
        field = null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    @NotNull
    @Override
    public Object generate() {
        Object[] data = new Object[size];
        for (int i = 0; i < data.length; i++) {
            data[i] = field.generate();
        }
        return data;
    }

    @Override
    public Object generateExtremes() {
        return null;
    }

    @Override
    public boolean isValid() {
        return field != null && size >= 0;
    }
}
