package org.mockdata.testfields;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockdata.fields.DataField;
import org.mockdata.fields.DependentField;
import org.mockdata.fields.IntField;

public class IntDependentField extends DataField<String> {


    @Nullable
    @DependentField(dependentOn = IntField.class)
    private Integer dependentInt;

    @NotNull
    @Override
    public String generate() {
        return dependentInt == null ? "null" : dependentInt.toString();
    }

    @Override
    public boolean isValid(Object element) {
        return true;
    }
}
