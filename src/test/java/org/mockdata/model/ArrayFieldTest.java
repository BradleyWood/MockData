package org.mockdata.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockdata.RecordEngine;

import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrayFieldTest {

    private final int size;

    public ArrayFieldTest(final int size) {
        this.size = size;
    }

    @Test
    public void testGenerate() {
        ArrayField field = new ArrayField(new ConstantField<>("const"), size);
        Assert.assertEquals(size, field.getSize());

        Object[] data = (Object[]) field.generate();

        Assert.assertEquals(size, data.length);

        for (Object datum : data) {
            Assert.assertEquals("const", datum);
        }
    }

    @Parameterized.Parameters(name = "ArrayFieldTest Size={0}")
    public static Collection<Object[]> parameters() {
        RecordEngine recordEngine = new RecordEngine(new IntField(0, 150));
        return recordEngine.generate(10);
    }
}
