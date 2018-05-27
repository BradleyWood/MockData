package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;

public class DoubleFieldTest {

    @Test
    public void testRangedGeneration() {
        final DoubleField field = new DoubleField(-100d, -90d);

        field.stream().limit(100).forEach(v -> Assert.assertTrue(field.isValid(v)));
    }

    @Test
    public void testDoubleGeneration() {
        final DoubleField field = new DoubleField();

        field.stream().limit(100).forEach(v -> Assert.assertTrue(field.isValid(v)));
    }
}
