package org.mockdata;

import java.util.HashMap;
import java.util.Objects;

public class Header {

    private final HashMap<String, Integer> map = new HashMap<>();

    public Header(final String... names) {
        for (final String name : names) {
            addColumn(name);
        }
    }

    public void addColumn(final String name) {
        if (map.containsKey(name))
            throw new IllegalArgumentException("Duplicate column name: " + name);
        map.put(name, size());
    }

    public void removeColumn(final String name) {
        final Integer idx = map.remove(name);

        if (idx != null) {
            for (String key : map.keySet()) {
                int v = map.get(key);
                if (v > idx) {
                    map.put(key, v - 1);
                }
            }
        }
    }

    public void addColumn(final int idx, final String name) {
        if (map.containsKey(name))
            throw new IllegalArgumentException("Duplicate column name: " + name);

        for (final String key : map.keySet()) {
            int v = map.get(key);
            if (v >= idx) {
                map.put(key, v + 1);
            }
        }
        map.put(name, idx);
    }

    public int getIndex(final String name) {
        return map.getOrDefault(name, -1);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public String[] getColumnNames() {
        final String[] columnNames = new String[map.size()];

        int i = 0;
        for (int j = 0; j < size(); j++) {
            for (final String name : map.keySet()) {
                if (map.get(name) == i) {
                    columnNames[i] = name;
                    i++;
                }
            }
        }
        return columnNames;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        int i = 0;

        for (String name : getColumnNames()) {
            if (map.get(name) == i) {
                builder.append(name);
                i++;
                if (i < size()) {
                    builder.append(",");
                }
            }
        }

        return builder.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Header header = (Header) o;

        return Objects.equals(map, header.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
