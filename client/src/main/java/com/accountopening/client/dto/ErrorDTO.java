package com.accountopening.client.dto;

import com.accountopening.client.enums.Attribute;
import com.accountopening.client.enums.UserMessage;
import lombok.Builder;

@Builder
public class ErrorDTO {
    private Attribute attribute;
    private UserMessage userMessage;
    private String message;

    public ErrorDTO(Attribute attribute, UserMessage userMessage, String message) {
        this.attribute = attribute;
        this.userMessage = userMessage;
        this.message = message;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public UserMessage getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(UserMessage userMessage) {
        this.userMessage = userMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
