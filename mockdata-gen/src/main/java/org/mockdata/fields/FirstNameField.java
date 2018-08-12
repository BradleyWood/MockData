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

        return name;
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
