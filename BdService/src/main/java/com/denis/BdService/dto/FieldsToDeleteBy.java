package com.denis.BdService.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record FieldsToDeleteBy<T>(FieldToDeleteBy<T>... fieldsToDeleteBy) {
    public List<FieldToDeleteBy<T>> getListOfFields(){
        return new ArrayList<>(Arrays.asList(fieldsToDeleteBy));
    }
}
