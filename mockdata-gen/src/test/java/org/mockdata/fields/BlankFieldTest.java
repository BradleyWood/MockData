package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;

public class BlankFieldTest {

    @Test
    public void generateTest() {
        BlankField bf = new BlankField();
        for (final String s : bf.stream().limit(10).collect(Collectors.toList())) {
            Assert.assertEquals("", s);
        }
    }
}
