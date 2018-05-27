package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

public class RowNumberField extends IntField {

    private int val;
    private int incBy;

    public RowNumberField() {
        this(0);
    }

    public RowNumberField(final int start) {
        this(start, 1);
    }

    public RowNumberField(final int start, final int incBy) {
        super(start, Integer.MAX_VALUE);
        this.val = start;
        this.incBy = incBy;
    }

    @NotNull
    @Override
    public Integer generate() {
        int row = val;
        val += incBy;
        return row;
    }
}
