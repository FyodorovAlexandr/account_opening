package com.accountopening.client.service;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.OpeningDTO;

public interface OpeningService {
    OpeningDTO addAccount(AccountDTO accountDTO);
}
