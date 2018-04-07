package org.mockdata.model;

import org.junit.Assert;
import org.junit.Test;

public class BooleanFieldTest {

    @Test
    public void testGenerate() {
        BooleanField field = new BooleanField();

        for (int i = 0; i < 10; i++) {
            Assert.assertNotNull(field.generate());
            Assert.assertEquals(Boolean.class, field.generate().getClass());
        }
    }

    @Test
    public void testIsValid() {
        BooleanField field = new BooleanField();
        Assert.assertTrue(field.isValid(true));
        Assert.assertTrue(field.isValid(false));
        Assert.assertFalse(field.isValid(null));
    }
}
