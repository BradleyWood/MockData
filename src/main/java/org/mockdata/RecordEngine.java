package org.mockdata;

import org.jetbrains.annotations.NotNull;
import org.mockdata.model.DataField;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RecordEngine implements Iterable<Record> {

    private final List<DataField> dataFields = new ArrayList<>();
    private final Header header;
    private int modCount = 0;

    public RecordEngine(final DataField... dataFields) {
        this(Arrays.asList(dataFields));
    }

    public RecordEngine(final Header header, final DataField... dataFields) {
        this(header, Arrays.asList(dataFields));
    }

    public RecordEngine(final List<DataField> dataFields) {
        this(new Header(), dataFields);
    }

    public RecordEngine(final Header header, final List<DataField> dataFields) {
        this.header = header;
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

    public Collection<Object[]> generate(final int numRecords) {
        return stream().limit(numRecords).map(Record::getValues).collect(Collectors.toList());
    }

    public void writeCsv(final String path, final int numRecords) throws FileNotFoundException {
        writeCsv(path, true, numRecords);
    }

    public void writeCsv(final String path, final boolean header, final int numRecords) throws FileNotFoundException {
        PrintStream ps = new PrintStream(new FileOutputStream(path));
        if (header) {
            writeHeader(ps);
        }
        writeRecords(ps, numRecords);
        ps.close();
    }

    public void writeHeader(final PrintStream stream) {
        if (header != null && !header.isEmpty()) {
            stream.println(header);
        }
    }

    public void writeRecords(final PrintStream stream, final int numRecords) {
        stream().limit(numRecords).forEach(stream::println);
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

            return Record.of(header, dataFields);
        }
    }
}
