package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;

public class IPAddressFieldTest {

    private static final String IP_ADDRESS_REGEX = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    private boolean isValid(final String address) {
        return address.toLowerCase().matches(IP_ADDRESS_REGEX);
    }

    @Test
    public void testIpGeneration() {
        IPAddressField field = new IPAddressField();

        field.stream().limit(10).forEach(f -> Assert.assertTrue(isValid(f)));
    }

}
