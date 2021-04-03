package com.accountopening.client.service.validation.impl;


import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ErrorDTO;
import com.accountopening.client.dto.ValidationDTO;
import com.accountopening.client.enums.Attribute;
import com.accountopening.client.enums.UserMessage;
import com.accountopening.client.service.validation.ValidationName;
import com.accountopening.client.service.validation.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("validationName")
public class ValidationNameImpl implements ValidationName {

    private final Validator nextValidator;

    public ValidationNameImpl(@Qualifier("validationSurname")Validator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validateNoNumbers(String name, List<ErrorDTO> errors) {
        if(!name.matches("[a-zA-Z_а-яА-Я]+")){
            errors.add(ErrorDTO.builder()
            .attribute(Attribute.NAME)
            .userMessage(UserMessage.WRONG_FORMAT_NAME)
            .message(UserMessage.WRONG_FORMAT_NAME.getValue())
            .build());
        }
    }

    @Override
    @Transactional
    public void validate(AccountDTO accountDTO, ValidationDTO validationDTO) {
        validateMissName(accountDTO.getName(), validationDTO.getErrors());
        nextValidator.validate(accountDTO, validationDTO);
    }
}
