package com.accountopening.client.dto;

import com.accountopening.client.enums.BankMessage;
import lombok.Builder;

@Builder
public class BankDTO {
    private BankMessage bankMessage;
    private String status;
    private String message;

    public BankMessage getBankMessage() {
        return bankMessage;
    }

    public void setBankMessage(BankMessage bankMessage) {
        this.bankMessage = bankMessage;
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
