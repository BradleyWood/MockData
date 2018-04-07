package org.mockdata.model;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Produces the constant value
 *
 * @param <T> The element type
 */
public class ConstantField<T> extends DataField<T> {

    @Nullable
    private final T value;

    public ConstantField(@Nullable T value) {
        this.value = value;
    }

    @Nullable
    @Override
    public T generate() {
        return value;
    }

    @Override
    public boolean isValid(T element) {
        return Objects.equals(value, element);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstantField<?> that = (ConstantField<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
