package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;
import org.mockdata.RecordEngine;

public class EmailFieldTest {

    @Test
    public void testEmail() {
        final EmailField field = new EmailField();
        final RecordEngine re = new RecordEngine(new FirstNameField(), new LastNameField(), field);

        re.stream().limit(100).map(r -> r.getValues()[2]).forEach(email -> Assert.assertTrue(field.isValid(email)));
    }
}
