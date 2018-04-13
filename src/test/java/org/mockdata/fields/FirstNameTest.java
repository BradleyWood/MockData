package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;
import org.mockdata.util.DataUtilities;

public class FirstNameTest {

    @Test
    public void genFirstNameTest() {
        FirstNameField field = new FirstNameField();

        field.stream().limit(100).forEach(s -> Assert.assertNotNull(DataUtilities.getGender(s)));
    }
}
