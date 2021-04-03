package com.accountopening.server.service;

import com.accountopening.server.dto.AccountDTO;
import com.accountopening.server.enums.OpeningMessage;

public interface OpeningService {
    OpeningMessage addAccount(AccountDTO accountDTO);
}
