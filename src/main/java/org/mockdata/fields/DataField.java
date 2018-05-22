package org.mockdata.fields;

import org.apache.commons.math3.distribution.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class DataField<T> implements Verifiable, Iterable<T> {

    private List<Field> dependentFields;

    protected final Random random = new Random();

    private double extremeProportion = 0;

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

    /**
     * Generates an extreme value for this field including edge cases
     * and illegal values
     *
     * @return An outlier or illegal value for this field
     */
    @Nullable
    public abstract T generateExtremes();

    /**
     * Generate the field with the given data dependencies
     *
     * @param dependencies Data dependencies from previously generated fields in the same row
     * @return The next data entry
     */
    public final T generate(@NotNull final Map<Class<? extends DataField>, List<Object>> dependencies)
            throws IllegalAccessException {
        if (dependentFields == null) {
            dependentFields = Arrays.stream(getClass().getDeclaredFields())
                    .filter(f -> f.getDeclaredAnnotation(DependentField.class) != null)
                    .collect(Collectors.toList());
        }

        if (extremeProportion < 0 || extremeProportion > 1)
            throw new IllegalArgumentException("Proportion of extreme values must be between 0 and 1");

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

        if (random.nextDouble() < extremeProportion)
            return generateExtremes();

        return generate();
    }

    public final double getExtremeProportion() {
        return extremeProportion;
    }

    public final void setExtremeProportion(double extremeProportion) {
        this.extremeProportion = extremeProportion;
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
