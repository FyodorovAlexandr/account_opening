package com.accountopening.client.service.validation.impl;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ValidationDTO;
import com.accountopening.client.service.validation.ValidationAddress;
import com.accountopening.client.service.validation.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("validationAddress")
public class ValidationAddressImpl implements ValidationAddress {

    private final Validator nextValidator;

    public ValidationAddressImpl(@Qualifier("validationBirthday") Validator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(AccountDTO account, ValidationDTO validationDTO) {
            validateMissAddress(account.getAddress(), validationDTO.getErrors());
            nextValidator.validate(account, validationDTO);
    }

}
