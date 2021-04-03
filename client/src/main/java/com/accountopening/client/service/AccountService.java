package com.accountopening.client.service;

import com.accountopening.client.dto.BankDTO;

public interface AccountService {
    BankDTO checkAccountBankClient(String passport);
}
