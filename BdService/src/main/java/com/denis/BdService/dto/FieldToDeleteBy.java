package com.denis.BdService.dto;

public record FieldToDeleteBy<T>(String field_name, T value) {
}
