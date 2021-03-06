package org.mockdata.api.generators;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.mockdata.fields.*;

import java.lang.reflect.Constructor;

public enum Field {

    ARRAY("array", ArrayField.class),
    BLANK_FIELD("blank", BlankField.class),
    BOOLEAN("boolean", BooleanField.class),
    CONSTANT("constant", ConstantField.class),
    COUNTRY("country", CountryField.class),
    DOUBLE("double", DoubleField.class),
    EMAIL("email", EmailField.class),
    EXCLUSIVE_SELECTOR("exclusive_selector", ExclusiveSelectorField.class),
    FIRST_NAME("first_name", FirstNameField.class),
    GENDER("gender", GenderField.class),
    IP_ADDRESS("ip_address", IPAddressField.class),
    INTEGER("integer", IntField.class),
    LAST_NAME("last_name", LastNameField.class),
    ROW_NUMBER("row_number", RowNumberField.class),
    SELECTOR("selector", SelectorField.class);

    private final @Getter String name;
    private final @Getter Class type;

    <T extends DataField> Field(final String name, final Class<T> type) {
        this.name = name;
        this.type = type;
    }

    public static DataField getField(@NotNull final String name) {
        for (final Field value : values()) {
            if (value.getName().equals(name.toLowerCase())) {
                try {
                    final Constructor<DataField> constructor = value.type.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return constructor.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
