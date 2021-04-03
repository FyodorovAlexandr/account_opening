package com.accountopening.server.service.impl;

import com.accountopening.server.dto.AccountDTO;
import com.accountopening.server.entity.Account;
import com.accountopening.server.enums.OpeningMessage;
import com.accountopening.server.helper.HelperConvert;
import com.accountopening.server.repository.OpeningRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OpeningServiceImplTest {

    @InjectMocks
    OpeningServiceImpl openingService;

    @Mock
    OpeningRepository openingRepository;

    @org.junit.Test
    public void testAddAccountSuccessful() {

        AccountDTO accountDTO = AccountDTO.builder().passport("1234567891").name("Alexander").surname("Alexandrov")
                .patronymic("Alexandrovich").address("г. Казань, ул. Баумана д. 12").birthday("1954-03-02").build();

        when(openingRepository.saveAndFlush(Account.builder()
                .passport(accountDTO.getPassport())
                .name(accountDTO.getName())
                .surname(accountDTO.getSurname())
                .patronymic(accountDTO.getPatronymic())
                .registrationAddress(accountDTO.getAddress())
                .dateOfBirth(HelperConvert.convertToDatabaseColumn(accountDTO.getBirthday()))
                .build())).thenReturn(any());

        OpeningMessage openingMessage = OpeningMessage.SUCCESSFUL_OPENING;
        assertEquals(openingMessage, openingService.addAccount(accountDTO));
    }

    @org.junit.Test
    public void testAddAccountError() {
        AccountDTO accountDTO = AccountDTO.builder().passport(null).name("Alexander").surname("Alexandrov")
                .patronymic("Alexandrovich").address("г. Казань, ул. Баумана д. 12").birthday("1954-03-02").build();

        when(openingRepository.saveAndFlush(Account.builder()
                .passport(accountDTO.getPassport())
                .name(accountDTO.getName())
                .surname(accountDTO.getSurname())
                .patronymic(accountDTO.getPatronymic())
                .registrationAddress(accountDTO.getAddress())
                .dateOfBirth(HelperConvert.convertToDatabaseColumn(accountDTO.getBirthday()))
                .build())).thenReturn(null);

        OpeningMessage openingMessage = OpeningMessage.ERROR_ACCOUNT_OPENING;
        assertEquals(openingMessage, openingService.addAccount(accountDTO));
    }
}
