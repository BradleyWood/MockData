package org.mockdata;

import org.jetbrains.annotations.NotNull;
import org.mockdata.model.DataField;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RecordEngine {

    private final List<DataField> dataFields = new LinkedList<>();

    public RecordEngine(DataField[] dataFields) {
        this.dataFields.addAll(Arrays.asList(dataFields));
    }

    public RecordEngine(List<DataField> dataFields) {
        this.dataFields.addAll(dataFields);
    }

    @NotNull
    public List<DataField> getDataFields() {
        return dataFields;
    }

    public void addDataFields(DataField... dataFields) {
        this.dataFields.addAll(Arrays.asList(dataFields));
    }

    public void removeDataFields(DataField... dataFields) {
        this.dataFields.removeAll(Arrays.asList(dataFields));
    }
}
