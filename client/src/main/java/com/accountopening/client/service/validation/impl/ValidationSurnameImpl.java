package com.accountopening.client.service.validation.impl;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ErrorDTO;
import com.accountopening.client.dto.ValidationDTO;
import com.accountopening.client.enums.Attribute;
import com.accountopening.client.enums.UserMessage;
import com.accountopening.client.service.validation.ValidationSurname;
import com.accountopening.client.service.validation.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("validationSurname")
public class ValidationSurnameImpl implements ValidationSurname {

    private final Validator nextValidator;

    public ValidationSurnameImpl(@Qualifier("validationMiddleName") Validator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validateNoNumbersSurname(String name, List<ErrorDTO> errors) {
        if(!name.matches("[a-zA-Z_а-яА-Я]+")){
            errors.add(ErrorDTO.builder()
                    .attribute(Attribute.LAST_NAME)
                    .userMessage(UserMessage.WRONG_FORMAT_LAST_NAME)
                    .message(UserMessage.WRONG_FORMAT_LAST_NAME.getValue())
                    .build());
        }
    }

    @Override
    @Transactional
    public void validate(AccountDTO accountDTO, ValidationDTO validationDTO) {
        validateMissSurname(accountDTO.getSurname(), validationDTO.getErrors());
        nextValidator.validate(accountDTO, validationDTO);
    }
}
