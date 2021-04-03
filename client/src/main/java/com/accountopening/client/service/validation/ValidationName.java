package com.accountopening.client.service.validation;

import com.accountopening.client.dto.ErrorDTO;
import com.accountopening.client.enums.Attribute;
import com.accountopening.client.enums.UserMessage;
import com.accountopening.client.utils.ErrorsDTOUtils;
import com.accountopening.client.utils.StringUtil;

import java.util.List;

public interface ValidationName extends Validator {

    default void validateMissName(String name, List<ErrorDTO> errors) {
        if (StringUtil.isBlank(name)) {
            errors.add(ErrorsDTOUtils.getError(UserMessage.MISS_NAME, Attribute.NAME));
        } else {
            validateNoNumbers(name, errors);
        }
    }

    void validateNoNumbers(String name, List<ErrorDTO> errors);
}
