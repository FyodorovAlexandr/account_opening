package com.accountopening.client.service;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ValidationDTO;

public interface ValidationService {
    ValidationDTO validate(AccountDTO AccountDTO);
}
