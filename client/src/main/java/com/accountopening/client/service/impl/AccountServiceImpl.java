package com.accountopening.client.service.impl;

import com.accountopening.client.dto.BankDTO;
import com.accountopening.client.enums.BankMessage;
import com.accountopening.client.integration.DataProvider;
import com.accountopening.client.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    final
    DataProvider dataProvider;

    public AccountServiceImpl(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public BankDTO checkAccountBankClient(String passport) {
        BankMessage bankMessage = dataProvider.getAccountInfo(passport);
        return BankDTO.builder()
                .bankMessage(bankMessage)
                .status(bankMessage.getStatus())
                .message(bankMessage.getMessage())
                .build();
    }
}