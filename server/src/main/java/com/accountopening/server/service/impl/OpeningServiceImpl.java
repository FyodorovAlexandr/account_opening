package com.accountopening.server.service.impl;

import com.accountopening.server.dto.AccountDTO;
import com.accountopening.server.entity.Account;
import com.accountopening.server.enums.OpeningMessage;
import com.accountopening.server.repository.OpeningRepository;
import com.accountopening.server.service.OpeningService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Service
@RequiredArgsConstructor
public class OpeningServiceImpl implements OpeningService {
    private final OpeningRepository openingRepository;

    private Date convertToDatabaseColumn(String str) {
        if (str == null) {
            return null;
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date data = null;
        try {
            data = df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String convertToEntityAttribute(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    @Override
    public OpeningMessage addAccount(AccountDTO accountDTO) {

        if (accountDTO.getPassport() != null && accountDTO.getName() != null
                && accountDTO.getSurname() != null && accountDTO.getAddress() != null
                && accountDTO.getBirthday() != null) {

            openingRepository.saveAndFlush(Account.builder()
                    .passport(accountDTO.getPassport())
                    .name(accountDTO.getName())
                    .surname(accountDTO.getName())
                    .patronymic(accountDTO.getPatronymic())
                    .registrationAddress(accountDTO.getAddress())
                    .dateOfBirth(convertToDatabaseColumn(accountDTO.getBirthday()))
                    .build());

            return OpeningMessage.SUCCESSFUL_OPENING;
        } else {
            return OpeningMessage.ERROR_ACCOUNT_OPENING;
        }
    }
}