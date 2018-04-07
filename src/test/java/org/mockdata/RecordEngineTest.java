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
        List<Object[]> records = recordEngine.stream().limit(1000).collect(Collectors.toList());

        Assert.assertEquals(1000, records.size());

        for (Object[] record : records) {
            Assert.assertEquals(3, record.length);
            Assert.assertTrue((Integer) record[0] >= MINA && (Integer) record[0] <= MAXA);
            Assert.assertTrue((Integer) record[1] >= MINB && (Integer) record[1] <= MAXB);
        }
    }
}
