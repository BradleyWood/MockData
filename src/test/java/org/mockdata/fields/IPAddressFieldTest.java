package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;

public class IPAddressFieldTest {

    @Test
    public void testIpGeneration() {
        IPAddressField field = new IPAddressField();

        field.stream().limit(10).forEach(f -> Assert.assertTrue(field.isValid(f)));
    }

    @Test
    public void testIsValid() {
        IPAddressField field = new IPAddressField();

        Assert.assertFalse(field.isValid("255.255.255.256"));
        Assert.assertFalse(field.isValid("255.255.255.256"));
        Assert.assertFalse(field.isValid("900.255.255.256"));
        Assert.assertFalse(field.isValid("100.255.255."));
        Assert.assertFalse(field.isValid("255.255.256"));
        Assert.assertFalse(field.isValid(0));
        Assert.assertFalse(field.isValid(null));

        Assert.assertTrue(field.isValid("255.255.255.255"));
        Assert.assertTrue(field.isValid("9.0.0.0"));
        Assert.assertTrue(field.isValid("009.0.0.0"));
    }
}
