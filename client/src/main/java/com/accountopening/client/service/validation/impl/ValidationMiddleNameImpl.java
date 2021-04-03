package com.accountopening.client.service.validation.impl;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ValidationDTO;
import com.accountopening.client.service.validation.ValidationMiddleName;
import com.accountopening.client.service.validation.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("validationMiddleName")
public class ValidationMiddleNameImpl implements ValidationMiddleName {

    private final Validator nextValidator;

    public ValidationMiddleNameImpl(@Qualifier("validationPassport") Validator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    @Transactional
    public void validate(AccountDTO accountDTO, ValidationDTO validationDTO) {
        validateNoNumbersMiddleName(accountDTO.getPatronymic(), validationDTO.getErrors());
        nextValidator.validate(accountDTO, validationDTO);
    }
}
