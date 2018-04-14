package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExclusiveSelectorField<T> extends DataField<T> {

    @NotNull
    private final List<T> originalSet;

    @Nullable
    @DependentField(dependentOn = ExclusiveSelectorField.class)
    private List<T> exclude = null;

    public ExclusiveSelectorField(final T[] set) {
        this(Arrays.asList(set));
    }

    public ExclusiveSelectorField(final List<T> set) {
        this.originalSet = new ArrayList<>(set);
    }

    @NotNull
    @Override
    public T generate() {
        final List<T> data = new LinkedList<>(originalSet);

        if (exclude != null)
            data.removeAll(exclude);

        if (data.isEmpty())
            throw new IllegalArgumentException("No unique values");

        return data.get(random.nextInt(data.size()));
    }

    @Override
    public boolean isValid(Object element) {
        if (exclude != null && exclude.contains(element))
            return false;

        return originalSet.contains(element);
    }
}
