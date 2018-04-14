package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class DataField<T> implements Verifiable, Iterable<T> {

    private List<Field> dependentFields;

    protected final Random random = new Random();

    @NotNull
    public abstract T generate();

    /**
     * Generate the field with the given data dependencies
     *
     * @param dependencies Data dependencies from previously generated fields in the same row
     * @return The next data entry
     */
    @NotNull
    public final T generate(@NotNull final Map<Class<? extends DataField>, Object> dependencies) throws IllegalAccessException {
        if (dependentFields == null) {
            dependentFields = Arrays.stream(getClass().getDeclaredFields())
                    .filter(f -> f.getDeclaredAnnotation(DependentField.class) != null)
                    .collect(Collectors.toList());
        }

        for (Field field : dependentFields) {
            final DependentField depAnno = field.getAnnotation(DependentField.class);
            final Object value = dependencies.get(depAnno.dependentOn());
            field.setAccessible(true);
            field.set(this, value);
        }

        return generate();
    }

    public final Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new FieldItr();
    }

    private class FieldItr implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public T next() {
            return generate();
        }
    }
}
