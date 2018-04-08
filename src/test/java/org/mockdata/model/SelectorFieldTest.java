package org.mockdata.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockdata.Record;
import org.mockdata.RecordEngine;

import java.util.Collection;
import java.util.stream.Collectors;

@RunWith(Parameterized.class)
public class SelectorFieldTest {

    private final SelectorField<Integer> selector;

    public SelectorFieldTest(Object[] intData) {
        selector = new SelectorField(intData);
    }

    @Test
    public void selectionTest() {
        Integer v = selector.generate();
        Assert.assertNotNull(v);
        Assert.assertTrue(selector.isValid(v));
    }

    @Parameterized.Parameters(name = "Selector Test")
    public static Collection parameters() {
        IntField intField = new IntField(1, 100);
        ArrayField af = new ArrayField(new IntField(), intField.generate());
        RecordEngine re = new RecordEngine(af);

        // generate 100 random sets of varying length containing random integers

        return re.stream().limit(100).peek(a -> af.setSize(intField.generate()))
                .map(Record::getValues).collect(Collectors.toList());
    }
}
