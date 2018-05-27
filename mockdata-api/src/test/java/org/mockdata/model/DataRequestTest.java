package org.mockdata.model;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.mockdata.RecordEngine;
import org.mockdata.api.model.DataRequest;
import org.mockdata.api.model.FieldConfig;
import org.mockdata.api.model.Format;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class DataRequestTest {

    private final Gson gson = new Gson();

    @Test
    public void testDefaultValues() {
        final DataRequest request = gson.fromJson("{}",
                DataRequest.class);

        Assert.assertEquals(1000, (int) request.getNumRecords());

        Assert.assertEquals(Format.CSV, request.getFormat());
    }

    @Test
    public void testMissingValues() throws FileNotFoundException {
        final DataRequest request = gson.fromJson(new FileReader("testdata/json/test_defaults.json"),
                DataRequest.class);

        Assert.assertEquals(50, (int) request.getNumRecords());

        Assert.assertEquals(Format.CSV, request.getFormat());
    }

    @Test
    public void testBooleanGeneration() throws FileNotFoundException {
        final DataRequest request = gson.fromJson(new FileReader("testdata/json/boolean_test.json"),
                DataRequest.class);

        List<FieldConfig> config = request.getFieldConfig();

        RecordEngine re = new RecordEngine();

        for (final FieldConfig fieldConfig : config) {
            if (fieldConfig != null) {
                re.addDataFields(fieldConfig.instantiate());
            }
        }

        Assert.assertEquals("Expected 3 boolean fields", 3, re.getDataFields().size());
        Assert.assertEquals("expected 100 records", 100, (int) request.getNumRecords());

        re.generate(request.getNumRecords()).forEach(oa -> {
            Assert.assertEquals(3, oa.length);
            for (final Object o : oa) {
                Assert.assertNotNull(o);
                Assert.assertTrue("expected boolean result", o instanceof Boolean);
            }
        });
    }
}
