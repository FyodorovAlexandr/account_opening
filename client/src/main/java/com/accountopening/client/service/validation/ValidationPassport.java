package com.accountopening.client.service.validation;

import com.accountopening.client.dto.ErrorDTO;
import com.accountopening.client.enums.Attribute;
import com.accountopening.client.enums.UserMessage;
import com.accountopening.client.utils.ErrorsDTOUtils;
import com.accountopening.client.utils.NumberUtils;
import com.accountopening.client.utils.StringUtil;

import java.util.List;

public interface ValidationPassport extends Validator {

    default void validationMissPassport(String serialNumber, List<ErrorDTO> errors) {
        if (StringUtil.isBlank(serialNumber)) {
            errors.add(ErrorsDTOUtils.getError(UserMessage.MISS_PASSPORT,
                    Attribute.PASSPORT));
        } else {
            if (NumberUtils.getCountsOfDigits(serialNumber)) {
                errors.add(ErrorsDTOUtils.getError(UserMessage.WRONG_FORMAT_OF_PASSPORT, Attribute.PASSPORT));
            }
        }
    }

}
