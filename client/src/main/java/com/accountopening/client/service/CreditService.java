package com.accountopening.client.service;

import com.accountopening.client.dto.CreditDTO;

public interface CreditService {
    CreditDTO findDataOnCreditHistory(String passport);
}
