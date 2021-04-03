package com.accountopening.client.service.validation;

import com.accountopening.client.dto.ErrorDTO;
import com.accountopening.client.enums.Attribute;
import com.accountopening.client.enums.UserMessage;
import com.accountopening.client.utils.ErrorsDTOUtils;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface ValidationBirthday extends Validator {

    default void validateBirthdayBeforeNow(Date birthday, List<ErrorDTO> errors){
        Instant now = Instant.now();
        if(birthday.toInstant().isAfter(now)){
            errors.add(ErrorsDTOUtils.getError(UserMessage.WRONG_FORMAT_OF_BIRTHDATE, Attribute.BIRTHDATE));
        }
    }
}
