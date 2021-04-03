package com.accountopening.server.service.impl;

import com.accountopening.server.entity.Account;
import com.accountopening.server.enums.BankMessage;
import com.accountopening.server.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BankAccountServiceImplTest {
    @InjectMocks
    BankAccountServiceImpl bankAccountService;

    @Mock
    AccountRepository accountRepository;

    @Test
    public void testCheckIsBankAccountTrue() {
        String passport = "1234567891";

        when(accountRepository.findByPassport(passport))
                .thenReturn(Optional.of(Account.builder().passport(passport).build()));

        BankMessage bankMessage = BankMessage.IS_BANK_CLIENT;
        assertEquals(bankMessage, bankAccountService.checkIsBankAccount(passport));
    }

    @Test
    public void testCheckIsBankAccountFalse() {
        String passport = "1234567891";
        Account account = null;

        when(accountRepository.findByPassport(passport))
                .thenReturn(Optional.ofNullable(account));

        BankMessage bankMessage = BankMessage.NOT_BANK_CLIENT;
        assertEquals(bankMessage, bankAccountService.checkIsBankAccount(passport));
    }
}