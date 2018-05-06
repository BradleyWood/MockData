package org.mockdata.fields;

import org.apache.commons.math3.distribution.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class DataField<T> implements Verifiable, Iterable<T> {

    private List<Field> dependentFields;

    protected final Random random = new Random();

    protected IntegerDistribution intDistribution;
    protected RealDistribution realDistribution;

    public DataField() {
        this(new UniformIntegerDistribution(Integer.MIN_VALUE, Integer.MAX_VALUE),
                new UniformRealDistribution());
    }

    public DataField(final IntegerDistribution intDistribution) {
        this(intDistribution, new NormalDistribution());
    }

    public DataField(final RealDistribution realDistribution) {
        this(new UniformIntegerDistribution(Integer.MIN_VALUE, Integer.MAX_VALUE), realDistribution);
    }

    public DataField(final IntegerDistribution intDistribution, final RealDistribution realDistribution) {
        this.intDistribution = intDistribution;
        this.realDistribution = realDistribution;
    }

    public abstract T generate();

    public abstract T generateExtremes();

    /**
     * Generate the field with the given data dependencies
     *
     * @param dependencies Data dependencies from previously generated fields in the same row
     * @return The next data entry
     */
    @NotNull
    public final T generate(@NotNull final Map<Class<? extends DataField>, List<Object>> dependencies)
            throws IllegalAccessException {
        if (dependentFields == null) {
            dependentFields = Arrays.stream(getClass().getDeclaredFields())
                    .filter(f -> f.getDeclaredAnnotation(DependentField.class) != null)
                    .collect(Collectors.toList());
        }

        for (final Field field : dependentFields) {
            final DependentField depAnno = field.getAnnotation(DependentField.class);
            final List<Object> value = dependencies.get(depAnno.dependentOn());
            field.setAccessible(true);

            if (List.class.isAssignableFrom(field.getType())) {
                field.set(this, value);
            } else if (field.getType().isArray()) {
                field.set(this, value.toArray());
            } else if (value != null && !value.isEmpty()) {
                field.set(this, value.get(value.size() - 1));
            }
        }

        return generate();
    }

    public final void setIntegerDistribution(final IntegerDistribution intDistribution) {
        this.intDistribution = intDistribution;
    }

    public final void setRealDistribution(RealDistribution realDistribution) {
        this.realDistribution = realDistribution;
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
