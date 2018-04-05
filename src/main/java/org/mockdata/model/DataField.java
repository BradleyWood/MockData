package org.mockdata.model;

import org.jetbrains.annotations.NotNull;

public abstract class DataField<T> {

    @NotNull
    public abstract T generate();
}