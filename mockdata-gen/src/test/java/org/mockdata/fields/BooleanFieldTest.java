package org.mockdata.fields;

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

}
