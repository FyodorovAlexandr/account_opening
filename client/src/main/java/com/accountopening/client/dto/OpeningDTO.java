package com.accountopening.client.dto;

import com.accountopening.client.enums.OpeningMessage;
import lombok.Builder;

@Builder
public class OpeningDTO {
    private OpeningMessage openingMessage;
    private String status;
    private String message;

    public OpeningMessage getOpeningMessage() {
        return openingMessage;
    }

    public void setOpeningMessage(OpeningMessage openingMessage) {
        this.openingMessage = openingMessage;
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
