package org.mockdata.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Produces the constant value
 *
 * @param <T> The element type
 */
public class ConstantField<T> extends DataField<T> implements DataConstraint<T> {

    @Nullable
    private final T value;

    public ConstantField(@Nullable T value) {
        this.value = value;
    }

    @NotNull
    @Override
    public T generate() {
        return value;
    }

    @Override
    public boolean isValid(T element) {
        return Objects.equals(value, element);
    }
}
