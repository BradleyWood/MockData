package org.mockdata.fields;

import org.junit.Assert;
import org.junit.Test;
import org.mockdata.util.DataUtilities;

public class CountryFieldTest {

    @Test
    public void testCountryField() {
        final CountryField cf = new CountryField();
        cf.stream().limit(1000).forEach(country -> Assert.assertTrue(DataUtilities.isCountry(country)));
    }
}
