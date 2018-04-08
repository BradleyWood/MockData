package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;

public class DataFieldTest {

    @Test
    public void testStream() {
        final String val = "abcdefg";
        ConstantField<String> constString = new ConstantField<>(val);

        constString.stream().limit(5).forEach(v -> Assert.assertEquals(val, v));
    }
}
