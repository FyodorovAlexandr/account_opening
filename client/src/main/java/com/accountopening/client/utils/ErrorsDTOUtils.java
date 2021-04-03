package com.accountopening.client.utils;

import com.accountopening.client.dto.ErrorDTO;
import com.accountopening.client.enums.Attribute;
import com.accountopening.client.enums.UserMessage;

public class ErrorsDTOUtils {

    public static ErrorDTO getError(UserMessage userMessage, Attribute attribute) {
        return ErrorDTO.builder()
                .attribute(attribute)
                .userMessage(userMessage)
                .message(userMessage.getValue())
                .build();
    }

    public static ErrorDTO getError(UserMessage userMessage) {
        return ErrorDTO.builder()
                .userMessage(userMessage)
                .message(userMessage.getValue())
                .build();
    }
}
