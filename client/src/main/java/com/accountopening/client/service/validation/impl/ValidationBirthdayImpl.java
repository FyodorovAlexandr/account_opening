package com.accountopening.client.service.validation.impl;


import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ErrorDTO;
import com.accountopening.client.dto.ValidationDTO;
import com.accountopening.client.enums.Attribute;
import com.accountopening.client.enums.UserMessage;
import com.accountopening.client.service.validation.ValidationBirthday;
import com.accountopening.client.utils.DateUtils;
import com.accountopening.client.utils.ErrorsDTOUtils;
import com.accountopening.client.utils.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component("validationBirthday")
public class ValidationBirthdayImpl implements ValidationBirthday {

    private void validateBirthdayBeforeEighteenYears(Date birthday, List<ErrorDTO> errors) {
        if(checkEighteenYears(birthday)){
            errors.add(ErrorsDTOUtils.getError(UserMessage.NOT_EIGHTEEN_YEARS, Attribute.BIRTHDATE));
        }
    }

    @Override
    @Transactional
    public void validate(AccountDTO accountDTO, ValidationDTO validationDTO) {

        final List<ErrorDTO> errors = validationDTO.getErrors();
        if (StringUtil.isBlank(accountDTO.getBirthday())) {
            errors.add(ErrorsDTOUtils.getError(UserMessage.MISS_BIRTHDAY, Attribute.BIRTHDATE));
        } else
            try {
                final Date birthday = DateUtils.convertToDatabaseColumn(accountDTO.getBirthday());
                validateBirthdayBeforeNow(birthday, errors);
                validateBirthdayBeforeEighteenYears(birthday, errors);
            } catch (DateTimeException e) {
                errors.add(ErrorsDTOUtils.getError(UserMessage.WRONG_FORMAT_OF_BIRTHDATE));
            }

    }

    private boolean checkEighteenYears(Date birthday){
        final int EIGHTEEN_YEARS = 6570;
        Instant now = Instant.now();
        Duration diff = Duration.between(now, birthday.toInstant());
        long days = diff.toDays();
        return Math.abs(days) < EIGHTEEN_YEARS;
    }
}
