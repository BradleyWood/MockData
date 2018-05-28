package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Selects a random field from the set
 *
 * @param <T> Optional type parameter
 */
public class SelectorField<T> extends DataField<T> {

    protected List<T> set;

    public SelectorField(final T[] set) {
        this(Arrays.asList(set));
    }

    public SelectorField(final List<T> set) {
        if (set.isEmpty())
            throw new IllegalArgumentException("Cannot select field from empty set");
        this.set = new LinkedList<>(set);
    }

    @NotNull
    @Override
    public T generate() {
        return set.get(random.nextInt(set.size()));
    }

    @Override
    public T generateExtremes() {
        return null;
    }

    @Override
    public boolean isValid(final Object element) {
        return set.contains(element);
    }
}