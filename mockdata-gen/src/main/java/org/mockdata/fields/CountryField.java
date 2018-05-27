package org.mockdata.fields;

import org.mockdata.util.DataUtilities;

/**
 * Generates random countries from the data set.
 * Not yet dependant on the average internet user-country
 * proportions. Setting distribution does nothing.
 * Dependant on no other fields.
 */
public class CountryField extends DataField<String> {

    public static final String[] EXTREMES = { null, "", "NotACountry", " Canada", " Canada ", "CaNAdA", "Can ada"};

    @Override
    public String generate() {
        return DataUtilities.randomCountry();
    }

    @Override
    public String generateExtremes() {
        return EXTREMES[random.nextInt(EXTREMES.length)];
    }

    @Override
    public boolean isValid(final Object element) {
        if (!(element instanceof String))
            return false;

        return DataUtilities.isCountry((String) element);
    }
}
