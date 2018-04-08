package org.mockdata.model;

import org.jetbrains.annotations.NotNull;

public class RowNumberField extends IntField {

    private int start;
    private int incBy;

    public RowNumberField() {
        this(0);
    }

    public RowNumberField(final int start) {
        this(start, 1);
    }

    public RowNumberField(final int start, final int incBy) {
        super(start, Integer.MAX_VALUE);
        this.start = start;
        this.incBy = incBy;
    }

    @NotNull
    @Override
    public Integer generate() {
        int row = start;
        start += incBy;
        return row;
    }
}
