package com.accountopening.client.service.validation;

import com.accountopening.client.dto.ErrorDTO;
import com.accountopening.client.enums.Attribute;
import com.accountopening.client.enums.UserMessage;
import com.accountopening.client.utils.StringUtil;

import java.util.List;

public interface ValidationMiddleName extends Validator {

    default void validateNoNumbersMiddleName(String name, List<ErrorDTO> errors) {
        if (!StringUtil.isBlank(name)) {
            if (!name.matches("[a-zA-Z_а-яА-Я]+")) {
                errors.add(ErrorDTO.builder()
                        .attribute(Attribute.MIDDLE_NAME)
                        .userMessage(UserMessage.WRONG_FORMAT_MIDDLE_NAME)
                        .message(UserMessage.WRONG_FORMAT_MIDDLE_NAME.getValue())
                        .build());
            }
        }
    }
}
