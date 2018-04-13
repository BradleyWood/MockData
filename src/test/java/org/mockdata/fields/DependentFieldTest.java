package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;
import org.mockdata.testfields.IntDependentField;

import java.util.Collections;
import java.util.Map;

public class DependentFieldTest {

    @Test
    public void testDependentField() throws IllegalAccessException {
        Integer value = 400;
        Map<Class<? extends DataField>, Object> data = Collections.singletonMap(IntField.class, value);
        IntDependentField em = new IntDependentField();

        Assert.assertEquals("null", em.generate());

        Assert.assertEquals(value.toString(), em.generate(data));
    }
}
