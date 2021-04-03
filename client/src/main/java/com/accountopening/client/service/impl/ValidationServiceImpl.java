package com.accountopening.client.service.impl;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ValidationDTO;
import com.accountopening.client.entity.Account;
import com.accountopening.client.service.ValidationService;
import com.accountopening.client.service.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class ValidationServiceImpl implements ValidationService {
    private final Validator validationName;

    public ValidationServiceImpl(@Qualifier("validationName") Validator validationName) {
        this.validationName = validationName;
    }

    @Override
    public ValidationDTO validate(AccountDTO accountDTO) {
        log.debug("validate --> account: {}", accountDTO);
        final ValidationDTO result = ValidationDTO.builder()
                .errors(new ArrayList<>())
                .build();
        validationName.validate(accountDTO, result);
        result.setCriticalError(!result.getErrors().isEmpty());
        return result;
    }
}
