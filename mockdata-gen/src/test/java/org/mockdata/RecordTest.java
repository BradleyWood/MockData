package org.mockdata;

import org.junit.Assert;
import org.junit.Test;
import org.mockdata.fields.BooleanField;
import org.mockdata.fields.GenderField;
import org.mockdata.fields.IntField;

import java.util.Arrays;
import java.util.Collection;

public class RecordTest {

    @Test
    public void testGetNoHeader() {
        Record record = Record.of(Arrays.asList(new IntField(), new IntField()));
        Assert.assertEquals(2, record.getValues().length);

        Assert.assertEquals(Integer.class, record.get(0).getClass());
        Assert.assertEquals(Integer.class, record.get(1).getClass());
    }

    @Test
    public void testGetWithHeader() {
        Record record = Record.of(new Header("x", "y"), Arrays.asList(new IntField(), new IntField()));
        Assert.assertEquals(2, record.getValues().length);

        Assert.assertEquals(Integer.class, record.get(0).getClass());
        Assert.assertEquals(Integer.class, record.get(1).getClass());

        Assert.assertEquals(Integer.class, record.get("x").getClass());
        Assert.assertEquals(Integer.class, record.get("y").getClass());

        Assert.assertNotEquals(record.get("x"), record.get("y"));

        int val = (Integer) record.get(0);

        Assert.assertEquals(val, record.getOrDefault("x", -5));
        Assert.assertEquals(0, record.getOrDefault("z", 0));
    }

    @Test
    public void testToString() {
        Record record = Record.of(Arrays.asList(new IntField(), new IntField()));
        String str = record.toString();
        String[] numbers = str.split(",");
        for (String number : numbers) {
            Integer.parseInt(number);
        }
    }

    @Test
    public void testGenerate() {
        RecordEngine xd = new RecordEngine(new IntField(0, 200), new IntField(0, 200), new GenderField(), new BooleanField());
        Collection<Object[]> data = xd.generate(1000);

        Assert.assertEquals(1000, data.size());
    }
}
