package com.accountopening.client.service.validation.impl;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ErrorDTO;
import com.accountopening.client.dto.ValidationDTO;
import com.accountopening.client.enums.Attribute;
import com.accountopening.client.enums.UserMessage;
import com.accountopening.client.helper.validation.ValidationHelper;
import com.accountopening.client.service.validation.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ValidationAddressImplTest {

    @InjectMocks
    ValidationAddressImpl validationAddress;

    @Mock
    Validator nextValidator;

    private ValidationDTO result;

    @Before
    public void init() {
        result = ValidationDTO.builder()
                .errors(new ArrayList<>())
                .build();
        doNothing().when(nextValidator).validate(any(AccountDTO.class), eq(result));
    }

    @Test
    public void testValidateWithError() {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .attribute(Attribute.ADDRESS)
                .userMessage(UserMessage.MISS_ADDRESS)
                .message(UserMessage.MISS_ADDRESS.getValue())
                .build();
        AccountDTO accountDTO = ValidationHelper.getAccount();
        accountDTO.setAddress(null);
        validationAddress.validate(accountDTO,  result);
        boolean a = result.getErrors().get(0).getAttribute().equals(errorDTO.getAttribute());
        Assert.assertTrue(a);
    }

    @Test
    public void testValidateWithoutError(){
        AccountDTO accountDTO = ValidationHelper.getAccount();
        validationAddress.validate(accountDTO, result);
        Assert.assertTrue(result.getErrors().isEmpty());
    }


}