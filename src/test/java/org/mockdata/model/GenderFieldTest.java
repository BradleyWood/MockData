package org.mockdata.model;

import org.junit.Assert;
import org.junit.Test;

public class GenderFieldTest {

    @Test
    public void testGenerate() {
        GenderField field = new GenderField();
        for (int i = 0; i < 10; i++) {
            Assert.assertNotNull(field.generate());
            Assert.assertEquals(GenderField.GENDER.class, field.generate().getClass());
        }
    }

    @Test
    public void testIsValid() {
        GenderField field = new GenderField();
        Assert.assertTrue(field.isValid(GenderField.GENDER.Female));
        Assert.assertTrue(field.isValid(GenderField.GENDER.Male));
        Assert.assertFalse(field.isValid(null));
    }

    @Test
    public void testValue() {
        Assert.assertEquals("Male", GenderField.GENDER.Male.toString());
        Assert.assertEquals("Female", GenderField.GENDER.Female.toString());
    }
}
