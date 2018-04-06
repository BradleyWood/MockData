package org.mockdata.model;


import org.junit.Assert;
import org.junit.Test;

public class IntFieldTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBounds() {
        new IntField(5, 0);
    }

    @Test
    public void testEqualBounds() {
        IntField field = new IntField(5, 5);
        Assert.assertEquals((Integer) 5, field.generate());

        field = new IntField(-100, -100);
        Assert.assertEquals((Integer) (-100), field.generate());
    }
}
