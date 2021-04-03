package com.accountopening.client.helper.validation;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ErrorDTO;
import com.accountopening.client.dto.ValidationDTO;

import java.util.ArrayList;
import java.util.List;

public class ValidationHelper {

    public static AccountDTO getAccount(){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName("Ivan");
        accountDTO.setSurname("Ivanov");
        accountDTO.setPatronymic("Ivanovich");
        accountDTO.setBirthday("1999-01-01");
        accountDTO.setAddress("Moscow, dom 14");
        accountDTO.setPassport("9192939495");
        return accountDTO;
    }

    public static ValidationDTO getValidationDTOWithoutErrors(){
        List<ErrorDTO> errors = new ArrayList<>();
        return ValidationDTO.builder()
                .errors(errors)
                .criticalError(false)
                .build();
    }
}
