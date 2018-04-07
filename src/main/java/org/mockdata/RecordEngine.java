package org.mockdata;

import org.jetbrains.annotations.NotNull;
import org.mockdata.model.DataField;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RecordEngine implements Iterable<Record> {

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
    public Spliterator<Record> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }

    public Stream<Record> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @NotNull
    @Override
    public Iterator<Record> iterator() {
        return new RecordItr();
    }

    private class RecordItr implements Iterator<Record> {

        private final int expectedModCount;

        RecordItr() {
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Record next() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException("Record model modified while iterating");

            return Record.of(dataFields);
        }
    }
}
