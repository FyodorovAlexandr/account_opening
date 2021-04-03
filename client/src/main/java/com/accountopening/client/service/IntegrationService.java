package com.accountopening.client.service;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.IntegrationDTO;

public interface IntegrationService {
    IntegrationDTO integrate(AccountDTO accountDTO, IntegrationDTO integrationDTO);
}
