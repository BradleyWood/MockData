package org.mockdata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockdata.model.IntField;

import java.util.List;
import java.util.stream.Collectors;

public class RecordEngineTest {

    private static final int MINA = 50;
    private static final int MAXA = 100;

    private static final int MINB = 40;
    private static final int MAXB = 55;

    private RecordEngine recordEngine;

    @Before
    public void setup() {
        recordEngine = new RecordEngine(new IntField(MINA, MAXA), new IntField(MINB, MAXB), new IntField());
    }

    @Test
    public void streamTest() {
        List<Record> records = recordEngine.stream().limit(1000).collect(Collectors.toList());

        Assert.assertEquals(1000, records.size());

        for (Record record : records) {
            Assert.assertEquals(3, record.getValues().length);
            Assert.assertTrue((Integer) record.get(0) >= MINA && (Integer) record.get(0) <= MAXA);
            Assert.assertTrue((Integer) record.get(1) >= MINB && (Integer) record.get(1) <= MAXB);
        }
    }
}
