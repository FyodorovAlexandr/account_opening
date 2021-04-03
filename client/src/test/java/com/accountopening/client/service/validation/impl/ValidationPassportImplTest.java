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
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ValidationPassportImplTest {
    @InjectMocks
    ValidationPassportImpl validationPassport;

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
    public void testValidateWithErrorMissPassport() {
        List<ErrorDTO> result = new ArrayList<>();
        result.add(ErrorDTO.builder()
                .userMessage(UserMessage.MISS_PASSPORT)
                .attribute(Attribute.PASSPORT)
                .message(UserMessage.MISS_PASSPORT.getValue())
                .build());
        List<ErrorDTO> errorsDTO = new ArrayList<>();
        validationPassport.validationMissPassport(null, errorsDTO);
        Assert.assertEquals(result.get(0).getUserMessage(), errorsDTO.get(0).getUserMessage());
        Assert.assertEquals(result.get(0).getAttribute(), errorsDTO.get(0).getAttribute());
    }

    @Test
    public void testValidationWithErrorNumbers(){
        AccountDTO accountDTO = ValidationHelper.getAccount();
        accountDTO.setPassport("990099009900");

        List<ErrorDTO> resultError = new ArrayList<>();
        resultError.add(ErrorDTO.builder()
                .userMessage(UserMessage.WRONG_FORMAT_OF_PASSPORT)
                .attribute(Attribute.PASSPORT)
                .message(UserMessage.WRONG_FORMAT_OF_PASSPORT.getValue())
                .build());
        validationPassport.validate(accountDTO, result);
        Assert.assertEquals(result.getErrors().get(0).getUserMessage(), resultError.get(0).getUserMessage());
        Assert.assertEquals(result.getErrors().size(), 1);
    }

    @Test
    public void testValidateWithoutError(){
        AccountDTO accountDTO = ValidationHelper.getAccount();
        validationPassport.validate(accountDTO, result);
        Assert.assertTrue(result.getErrors().isEmpty());
    }
}