package org.mockdata.model;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.mockdata.api.model.DataRequest;
import org.mockdata.api.model.Format;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class DataRequestTest {

    private final Gson gson = new Gson();

    @Test
    public void testDefaultValues() {
        final DataRequest request = gson.fromJson("{}",
                DataRequest.class);

        Assert.assertEquals(1000, (int) request.getNumRecords());

        Assert.assertEquals(0.0, request.getInvalidProportion(), 0.000001);
        Assert.assertEquals(Format.CSV, request.getFormat());
    }

    @Test
    public void testMissingValues() throws FileNotFoundException {
        final DataRequest request = gson.fromJson(new FileReader("testdata/json/test_defaults.json"),
                DataRequest.class);

        Assert.assertEquals(50, (int) request.getNumRecords());

        Assert.assertEquals(0.0, request.getInvalidProportion(), 0.000001);
        Assert.assertEquals(Format.CSV, request.getFormat());
    }
}
