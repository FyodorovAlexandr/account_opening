package com.accountopening.client.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class ValidationDTO {
    private List<ErrorDTO> errors;
    private boolean criticalError;

    public ValidationDTO(List<ErrorDTO> errors, boolean criticalError) {
        this.errors = errors;
        this.criticalError = criticalError;
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }

    public boolean isCriticalError() {
        return criticalError;
    }

    public void setCriticalError(boolean criticalError) {
        this.criticalError = criticalError;
    }
}
