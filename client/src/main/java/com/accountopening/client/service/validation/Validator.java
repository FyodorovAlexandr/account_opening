package com.accountopening.client.service.validation;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ValidationDTO;

public interface Validator {
    void validate(AccountDTO account, ValidationDTO validationDTO);
}
