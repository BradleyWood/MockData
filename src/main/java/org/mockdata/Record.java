package org.mockdata;

import org.mockdata.model.DataField;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Record {

    private final Header header;
    private final Object[] values;

    private Record(final Header header, final Object... values) {
        this.header = header;
        this.values = values;
    }

    public Header getHeader() {
        return header;
    }

    public Object[] getValues() {
        return values;
    }

    public Object get(final int idx) {
        if (idx >= values.length)
            throw new IndexOutOfBoundsException("Index out of bounds: " + idx);
        return values[idx];
    }

    public Object get(final String name) {
        int idx = header.getIndex(name);
        if (idx != -1) {
            if (idx >= values.length)
                throw new IndexOutOfBoundsException("Column name out of bounds: name=\"" + name + "\" index=" + idx);
            return get(idx);
        }
        return null;
    }

    public Object getOrDefault(final String name, Object defaultValue) {
        Object obj = get(name);
        return obj != null ? obj : defaultValue;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int i = 0;
        for (Object val : values) {
            builder.append(val);
            i++;
            if (i < values.length) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(header, record.header) &&
                Arrays.equals(values, record.values);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(header);
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }

    public static Record of(final List<DataField> dataFields) {
        return of(new Header(), dataFields);
    }

    public static Record of(final Header header, final List<DataField> dataFields) {
        Object[] values = new Object[dataFields.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = dataFields.get(i).generate();
        }
        return new Record(header, values);
    }
}
