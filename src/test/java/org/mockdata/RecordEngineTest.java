package org.mockdata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockdata.fields.DataField;
import org.mockdata.fields.IntField;

import java.io.*;
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
        Header header = new Header("x", "y", "z");
        recordEngine = new RecordEngine(header, new IntField(MINA, MAXA), new IntField(MINB, MAXB), new IntField());
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

    @Test
    public void testWriteRecord() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(baos);
        int numRecords = 10;

        recordEngine.writeHeader(stream);
        recordEngine.writeRecords(stream, numRecords);

        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray())));
        String header = br.readLine();
        Assert.assertEquals("x,y,z", header);


        List<DataField> fields = recordEngine.getDataFields();
        for (int i = 0; i < numRecords; i++) {
            String record = br.readLine();
            String[] numbers = record.split(",");

            for (int j = 0; j < numbers.length; j++) {
                Assert.assertTrue(fields.get(j).isValid(Integer.parseInt(numbers[j])));
            }
        }
        Assert.assertNull(br.readLine());
    }
}
