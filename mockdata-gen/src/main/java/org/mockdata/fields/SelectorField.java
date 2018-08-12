package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Selects a random field from the set
 *
 * @param <T> Optional type parameter
 */
public class SelectorField<T> extends DataField<T> {

    @NotNull
    private List<T> set;

    public SelectorField(final T[] set) {
        this(Arrays.asList(set));
    }

    public SelectorField(final List<T> set) {
        if (set.isEmpty())
            throw new IllegalArgumentException("Cannot select field from empty set");
        this.set = new LinkedList<>(set);
    }

    private SelectorField() {
        set = null;
    }

    public boolean contains(final T o) {
        return set.contains(o);
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
    public boolean isValid() {
        return set != null;
    }
}
