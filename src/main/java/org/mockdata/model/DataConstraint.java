package org.mockdata.model;

public interface DataConstraint<E> {

    boolean isValid(E element);
}
