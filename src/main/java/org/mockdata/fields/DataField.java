package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class DataField<T> implements Verifiable<T>, Iterable<T> {

    @NotNull
    public abstract T generate();

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
