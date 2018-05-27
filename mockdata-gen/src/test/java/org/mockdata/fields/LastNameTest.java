package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;
import org.mockdata.util.DataUtilities;

public class LastNameTest {

    @Test
    public void testLastName() {
        LastNameField field = new LastNameField();

        field.stream().limit(100).forEach(s -> Assert.assertTrue(s, DataUtilities.isLastName(s)));
    }
}
