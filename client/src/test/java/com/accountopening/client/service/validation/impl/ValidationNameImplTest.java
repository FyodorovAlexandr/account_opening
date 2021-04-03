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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ValidationNameImplTest {

    @InjectMocks
    ValidationNameImpl validationName;

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
    public void validateNoNumbers() {
        List<ErrorDTO> result = new ArrayList<>();
        List<ErrorDTO> errorsDTO = new ArrayList<>();
        validationName.validateNoNumbers("Ivan", errorsDTO);
        Assert.assertEquals(result, errorsDTO);
    }

    @Test
    public void validateNameWithNumbers() {
        List<ErrorDTO> result = new ArrayList<>();
        result.add(ErrorDTO.builder()
                .userMessage(UserMessage.WRONG_FORMAT_NAME)
                .attribute(Attribute.NAME)
                .message(UserMessage.WRONG_FORMAT_NAME.getValue())
                .build());
        List<ErrorDTO> errorsDTO = new ArrayList<>();
        validationName.validateNoNumbers("Ivan1", errorsDTO);
        Assert.assertEquals(result.get(0).getUserMessage(), errorsDTO.get(0).getUserMessage());
        Assert.assertEquals(result.get(0).getAttribute(), errorsDTO.get(0).getAttribute());
    }

    @Test
    public void testEmptyName(){
        List<ErrorDTO> result = new ArrayList<>();
        result.add(ErrorDTO.builder()
                .userMessage(UserMessage.MISS_NAME)
                .attribute(Attribute.NAME)
                .message(UserMessage.MISS_NAME.getValue())
                .build());
        List<ErrorDTO> errorsDTO = new ArrayList<>();
        validationName.validateMissName(null, errorsDTO);
        Assert.assertEquals(result.get(0).getUserMessage(), errorsDTO.get(0).getUserMessage());
        Assert.assertEquals(result.get(0).getAttribute(), errorsDTO.get(0).getAttribute());
    }

    @Test
    public void validate() {
        AccountDTO accountDTO = ValidationHelper.getAccount();
        validationName.validate(accountDTO, result);
        Assert.assertTrue(result.getErrors().isEmpty());
    }
}