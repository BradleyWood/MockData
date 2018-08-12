package org.mockdata.fields;

public class IPAddressField extends DataField<String> {

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
    public boolean isValid() {
        return true;
    }
}
