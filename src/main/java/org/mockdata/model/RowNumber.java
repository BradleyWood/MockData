package org.mockdata.model;

import org.jetbrains.annotations.NotNull;

public class RowNumber extends IntField {

    private int start;
    private int incBy;

    public RowNumber() {
        this(0);
    }

    public RowNumber(final int start) {
        this(start, 1);
    }

    public RowNumber(final int start, final int incBy) {
        super(start, Integer.MAX_VALUE);
        this.start = start;
        this.incBy = incBy;
    }

    @NotNull
    @Override
    public Integer generate() {
        return start += incBy;
    }
}
