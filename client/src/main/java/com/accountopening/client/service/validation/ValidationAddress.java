package com.accountopening.client.service.validation;

import com.accountopening.client.dto.ErrorDTO;
import com.accountopening.client.enums.Attribute;
import com.accountopening.client.enums.UserMessage;
import com.accountopening.client.utils.ErrorsDTOUtils;
import com.accountopening.client.utils.StringUtil;

import java.util.List;

public interface ValidationAddress extends Validator {

    default void validateMissAddress(String name, List<ErrorDTO> errors) {
        if (StringUtil.isBlank(name)) {
            errors.add(ErrorsDTOUtils.getError(UserMessage.MISS_ADDRESS, Attribute.ADDRESS));
        }
    }
}
