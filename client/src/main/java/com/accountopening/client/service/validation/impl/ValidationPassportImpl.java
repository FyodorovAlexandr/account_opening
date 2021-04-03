package com.accountopening.client.service.validation.impl;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ValidationDTO;
import com.accountopening.client.service.validation.ValidationPassport;
import com.accountopening.client.service.validation.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("validationPassport")
public class ValidationPassportImpl implements ValidationPassport {


    private final Validator nextValidator;

    public ValidationPassportImpl(@Qualifier("validationAddress")
                                          Validator validator) {
        this.nextValidator = validator;
    }

    @Override
    @Transactional
    public void validate(AccountDTO accountDTO, ValidationDTO validationDTO) {
            validationMissPassport(accountDTO.getPassport(), validationDTO.getErrors());
            nextValidator.validate(accountDTO, validationDTO);
    }
}
