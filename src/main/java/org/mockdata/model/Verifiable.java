package org.mockdata.model;

public interface Verifiable<E> {

    boolean isValid(E element);
}
