package org.mockdata.model;

import org.jetbrains.annotations.NotNull;

public class ArrayField extends DataField {

    @NotNull
    private final DataField field;
    private int size;

    public ArrayField(@NotNull final DataField field, final int size) {
        this.field = field;
        this.size = size;
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
    public boolean isValid(Object element) {
        if (!(element instanceof Object[]))
            return false;
        Object[] data = (Object[]) element;

        if (data.length != size)
            return false;

        for (int i = 0; i < data.length; i++) {
            if (!field.isValid(data[i]))
                return false;
        }
        return true;
    }
}
