package com.accountopening.client.dto;

import com.accountopening.client.enums.CreditAttribute;
import lombok.Builder;

@Builder
public class CreditDTO {
    private CreditAttribute creditAttribute;
    private String status;
    private String message;

    public CreditDTO() {
    }

    public CreditDTO(CreditAttribute creditAttribute, String status, String message) {
        this.creditAttribute = creditAttribute;
        this.status = status;
        this.message = message;
    }

    public CreditAttribute getCreditAttribute() {
        return creditAttribute;
    }

    public void setCreditAttribute(CreditAttribute creditAttribute) {
        this.creditAttribute = creditAttribute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
