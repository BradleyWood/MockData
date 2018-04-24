package org.mockdata.fields;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockdata.data.Gender;
import org.mockdata.util.DataUtilities;

public class FirstNameField extends DataField<String> {

    @Nullable
    @DependentField(dependentOn = GenderField.class)
    private Gender gender;

    @NotNull
    @Override
    public String generate() {
        String name;
        if (gender == Gender.Male) {
            name = DataUtilities.selectMaleName(realDistribution.sample());
        } else if (gender == Gender.Female) {
            name = DataUtilities.randomFemaleName();
        } else {
            name = DataUtilities.selectFirstName(random.nextBoolean(), realDistribution.sample());
        }

        if (name == null || name.isEmpty())
            return "Bob";

        if (!isValid(name)) {
            throw new RuntimeException();
        }

        return name;
    }

    @Override
    public boolean isValid(final Object element) {
        if (!(element instanceof String))
            return false;

        final String name = (String) element;

        return !name.isEmpty() & !name.matches("[0-9] \t\r\n");
    }
}
