package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;
import org.mockdata.Record;
import org.mockdata.RecordEngine;
import org.mockdata.testfields.IntDependentField;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DependentFieldTest {

    @Test
    public void testDependentField() throws IllegalAccessException {
        final Integer value = 400;
        final Map<Class<? extends DataField>, List<Object>> data = Collections.singletonMap(IntField.class, Collections.singletonList(value));
        final IntDependentField em = new IntDependentField();

        Assert.assertEquals("null", em.generate());

        Assert.assertEquals(value.toString(), em.generate(data));
    }

    @Test
    public void testDependentRows() {
        final RecordEngine re = new RecordEngine(new IntField(), new IntDependentField());

        re.stream().limit(10).map(Record::getValues).forEach(data -> Assert.assertEquals(data[0].toString(), data[1]));
    }

    @Test
    public void testMissingDependentData() {
        final RecordEngine re = new RecordEngine(new IntDependentField(), new IntField());
        // test no data passage when dependent fields are placed first
        re.stream().limit(10).map(Record::getValues).forEach(data -> Assert.assertEquals("null", data[0]));
    }
}
