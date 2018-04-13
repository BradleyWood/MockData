package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockdata.data.Gender;
import org.mockdata.util.DataUtilities;

import java.util.Random;

public class FirstNameField extends DataField<String> {

    private final Random random = new Random();

    @Nullable
    @DependentField(dependentOn = GenderField.class)
    private Gender gender;

    @NotNull
    @Override
    public String generate() {
        String name;
        if (gender == Gender.Male) {
            name = DataUtilities.randomMaleName();
        } else if (gender == Gender.Female) {
            name = DataUtilities.randomFemaleName();
        } else {
            name = DataUtilities.randomFirstName(random.nextBoolean());
        }

        if (name == null || name.isEmpty())
            return "Bob";

        if (!isValid(name)) {
            throw new RuntimeException();
        }

        return name;
    }

    @Override
    public boolean isValid(Object element) {
        if (!(element instanceof String))
            return false;

        final String name = (String) element;

        return !name.isEmpty() & !name.matches("[0-9] \t\r\n");
    }
}
