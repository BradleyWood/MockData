package org.mockdata;

import java.util.Arrays;
import java.util.LinkedList;

public class Header {

    private final LinkedList<String> names = new LinkedList<>();

    public Header(final String... names) {
        this.names.addAll(Arrays.asList(names));
    }

    public void addColumn(final String name) {
        names.addLast(name);
    }

    public void removeColumn(final String name) {
        names.remove(name);
    }

    public void addColumn(final int index, final String name) {
        names.add(index, name);
    }

    public int getIndex(final String name) {
        return names.indexOf(name);
    }

    public int size() {
        return names.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int i = 0;

        for (String name : names) {
            builder.append(name);
            i++;
            if (i < size()) {
                builder.append(",");
            }
        }

        return builder.toString();
    }
}
