package org.mockdata.fields;

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

    public ConstantField(@Nullable final T value) {
        this.value = value;
    }

    @Nullable
    @Override
    public T generate() {
        return value;
    }

    @Override
    public boolean isValid(final Object element) {
        return Objects.equals(value, element);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ConstantField<?> that = (ConstantField<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
