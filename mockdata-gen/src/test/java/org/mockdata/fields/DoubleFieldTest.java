package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;

public class DoubleFieldTest {

    private boolean isValid(final Number min, final Number max, final double number) {
        if (min != null && Double.compare(min.doubleValue(), number) > 0)
            return false;

        if (max != null)
            return Double.compare(max.doubleValue(), number) >= 0;

        return true;
    }

    @Test
    public void testRangedGeneration() {
        final DoubleField field = new DoubleField(-100d, -90d);

        field.stream().limit(100).forEach(v -> Assert.assertTrue(isValid(field.getMin(), field.getMax(), v)));
    }

    @Test
    public void testDoubleGeneration() {
        final DoubleField field = new DoubleField();

        field.stream().limit(100).forEach(v -> Assert.assertTrue(isValid(field.getMin(), field.getMax(), v)));
    }
}
