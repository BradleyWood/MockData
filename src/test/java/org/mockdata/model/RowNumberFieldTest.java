package org.mockdata.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class RowNumberFieldTest {

    @Test
    public void testRowNumber() {
        RowNumberField field = new RowNumberField();
        AtomicInteger expected = new AtomicInteger();
        field.stream().limit(100).forEach(r -> Assert.assertEquals(expected.getAndIncrement(), (int) r));
    }

    @Test
    public void testIncBy5() {
        RowNumberField field = new RowNumberField(0, 5);
        AtomicInteger expected = new AtomicInteger();
        field.stream().limit(100).forEach(r -> Assert.assertEquals(expected.getAndAdd(5), (int) r));
    }

    @Test
    public void testStartingPoint() {
        int start = 10050000;
        RowNumberField field = new RowNumberField(start);
        AtomicInteger expected = new AtomicInteger(start);
        field.stream().limit(100).forEach(r -> Assert.assertEquals(expected.getAndIncrement(), (int) r));
    }
}
