package com.accountopening.server.service.impl;

import com.accountopening.server.entity.Account;
import com.accountopening.server.enums.BankMessage;
import com.accountopening.server.repository.AccountRepository;
import com.accountopening.server.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public BankMessage checkIsBankAccount(String passport) {
        final Account account = accountRepository.findByPassport(passport).orElse(null);

        if (account != null) {
            return BankMessage.IS_BANK_CLIENT;
        } else {
            return BankMessage.NOT_BANK_CLIENT;
        }
    }
}