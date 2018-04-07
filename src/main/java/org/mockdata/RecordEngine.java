package org.mockdata;

import org.jetbrains.annotations.NotNull;
import org.mockdata.model.DataField;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RecordEngine implements Iterable<Object[]> {

    private final List<DataField> dataFields = new ArrayList<>();
    private int modCount = 0;

    public RecordEngine(final DataField... dataFields) {
        this.dataFields.addAll(Arrays.asList(dataFields));
    }

    public RecordEngine(final List<DataField> dataFields) {
        this.dataFields.addAll(dataFields);
    }

    @NotNull
    public List<DataField> getDataFields() {
        return dataFields;
    }

    public void addDataFields(final DataField... dataFields) {
        addDataFields(Arrays.asList(dataFields));
    }

    public void addDataFields(final List<DataField> fields) {
        modCount += fields.size();
        this.dataFields.addAll(fields);
    }

    public void removeDataFields(final DataField... dataFields) {
        removeDataFields(Arrays.asList(dataFields));
    }

    public void removeDataFields(final List<DataField> fields) {
        modCount += fields.size();
        this.dataFields.removeAll(fields);
    }

    @Override
    public Spliterator<Object[]> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }

    public Stream<Object[]> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @NotNull
    @Override
    public Iterator<Object[]> iterator() {
        return new RecordItr();
    }

    private class RecordItr implements Iterator<Object[]> {

        private final int expectedModCount;

        RecordItr() {
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Object[] next() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException("Record model modified while iterating");

            Object[] data = new Object[dataFields.size()];
            for (int i = 0; i < data.length; i++) {
                data[i] = dataFields.get(i).generate();
            }
            return data;
        }
    }
}
