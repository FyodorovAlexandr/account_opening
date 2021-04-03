package com.accountopening.client.service.validation.impl;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.ValidationDTO;
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
public class ValidationBirthdayImplTest {

    @InjectMocks
    ValidationBirthdayImpl validationBirthday;

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
    public void testIsEighteenYears(){
        AccountDTO accountDTO = ValidationHelper.getAccount();
        accountDTO.setBirthday("2010-05-05");
        validationBirthday.validate(accountDTO, result);
        Assert.assertEquals(result.getErrors().get(0).getUserMessage(), UserMessage.NOT_EIGHTEEN_YEARS);
        Assert.assertEquals(result.getErrors().size(),1);
    }

    @Test
    public void testBirthdayIsEmpty(){
        AccountDTO accountDTO = ValidationHelper.getAccount();
        accountDTO.setBirthday(null);
        validationBirthday.validate(accountDTO, result);
        Assert.assertEquals(result.getErrors().get(0).getUserMessage(), UserMessage.MISS_BIRTHDAY);
        Assert.assertEquals(result.getErrors().size(),1);
    }

    @Test
    public void validate() {
        AccountDTO accountDTO = ValidationHelper.getAccount();
        validationBirthday.validate(accountDTO, result);
        Assert.assertTrue(result.getErrors().isEmpty());
    }
}