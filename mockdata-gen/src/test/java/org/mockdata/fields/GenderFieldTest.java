package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;
import org.mockdata.data.Gender;

public class GenderFieldTest {

    @Test
    public void testGenerate() {
        GenderField field = new GenderField();
        for (int i = 0; i < 10; i++) {
            Assert.assertNotNull(field.generate());
            Assert.assertEquals(Gender.class, field.generate().getClass());
        }
    }

    @Test
    public void testIsValid() {
        GenderField field = new GenderField();
        Assert.assertTrue(field.isValid(Gender.Female));
        Assert.assertTrue(field.isValid(Gender.Male));
        Assert.assertFalse(field.isValid(null));
    }

    @Test
    public void testValue() {
        Assert.assertEquals("Male", Gender.Male.toString());
        Assert.assertEquals("Female", Gender.Female.toString());
    }
}
