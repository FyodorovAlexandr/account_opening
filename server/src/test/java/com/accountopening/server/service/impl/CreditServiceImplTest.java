package com.accountopening.server.service.impl;

import com.accountopening.server.entity.Credit;
import com.accountopening.server.enums.CreditAttribute;
import com.accountopening.server.repository.CreditRepository;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CreditServiceImplTest {

    @InjectMocks
    CreditServiceImpl creditService;

    @Mock
    CreditRepository creditRepository;

    @org.junit.Test
    public void testFindDataOnCreditHistoryBadDebt() {
        String passport = ("1234567891");
        when(creditRepository.findByPassport("1234567891")).
                thenReturn(Optional.of(Credit.builder().passport("1234567891").latePayment(true).build()));
        CreditAttribute creditAttribute = CreditAttribute.BAD_DEBT;
        assertEquals(creditAttribute, creditService.findDataOnCreditHistory(passport));
    }

    @org.junit.Test
    public void testFindDataOnCreditHistoryNoDebt() {
        String passport = ("1234567891");
        when(creditRepository.findByPassport("1234567891")).
                thenReturn(Optional.of(Credit.builder().passport("1234567891").latePayment(false).build()));
        CreditAttribute creditAttribute = CreditAttribute.NO_DEBT;
        assertEquals(creditAttribute, creditService.findDataOnCreditHistory(passport));
    }

    @org.junit.Test
    public void testFindDataOnCreditHistoryNotFound() {
        String passport = ("1234567891");;
        when(creditRepository.findByPassport("1234567899")).
                thenReturn(Optional.of(Credit.builder().passport("1234567899").build()));
        CreditAttribute creditAttribute = CreditAttribute.DATA_NOT_FOUND;
        assertEquals(creditAttribute, creditService.findDataOnCreditHistory(passport));
    }
}
