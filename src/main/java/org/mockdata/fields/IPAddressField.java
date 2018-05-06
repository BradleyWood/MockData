package org.mockdata.fields;

public class IPAddressField extends DataField<String> {

    public static final String IP_ADDRESS_REGEX = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    @Override
    public String generate() {
        return random.nextInt(0xFF) + "." + random.nextInt(0xFF) + "." +
                random.nextInt(0xFF) + "." + random.nextInt(0xFF);
    }

    @Override
    public String generateExtremes() {
        return null;
    }

    @Override
    public boolean isValid(Object element) {
        if (!(element instanceof String))
            return false;

        return ((String) element).toLowerCase().matches(IP_ADDRESS_REGEX);
    }
}
