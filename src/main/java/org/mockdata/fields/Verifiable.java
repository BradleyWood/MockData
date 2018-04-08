package org.mockdata.fields;

public interface Verifiable<E> {

    boolean isValid(E element);
}
