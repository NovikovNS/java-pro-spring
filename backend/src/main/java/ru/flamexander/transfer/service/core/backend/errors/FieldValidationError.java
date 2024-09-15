package ru.flamexander.transfer.service.core.backend.errors;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FieldValidationError {
    private String fieldName;
    private String message;

    public FieldValidationError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
