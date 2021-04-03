package com.accountopening.client.service.impl;

import com.accountopening.client.dto.*;
import com.accountopening.client.helper.impl.IntegrationServiceHelper;
import com.accountopening.client.service.AccountService;
import com.accountopening.client.service.CreditService;
import com.accountopening.client.service.MVDService;
import com.accountopening.client.service.OpeningService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class IntegrationServiceImplTest {

    @InjectMocks
    IntegrationServiceImpl integrationService;

    @Mock
    MVDService mvdService;

    @Mock
    AccountService accountService;

    @Mock
    OpeningService openingService;

    @Mock
    CreditService creditService;

    private ValidationDTO validationDTO;

    @Before
    public void init() {
        validationDTO = ValidationDTO.builder()
                .errors(new ArrayList<>())
                .build();
    }

    @Test
    public void testIntegrateSuccessful() {
        AccountDTO accountDTO = IntegrationServiceHelper.getAccountDTO();
        MvdDTO mvdDTO = IntegrationServiceHelper.getMvdDTONotOnTheList();
        BankDTO bankDTO = IntegrationServiceHelper.getBankDTONotBankClient();
        CreditDTO creditDTO = IntegrationServiceHelper.getCreditDTODataNotFound();
        OpeningDTO openingDTO = IntegrationServiceHelper.getOpeningDTOSuccessful();
        IntegrationDTO integrationDTO = IntegrationDTO.builder()
                .validationDTO(validationDTO)
                .build();

        when(mvdService.checkPassportMVD(accountDTO.getPassport()))
                .thenReturn(mvdDTO);
        when(accountService.checkAccountBankClient(accountDTO.getPassport()))
                .thenReturn(bankDTO);
        when(creditService.findDataOnCreditHistory(accountDTO.getPassport()))
                .thenReturn(creditDTO);
        when(openingService.addAccount(accountDTO))
                .thenReturn(openingDTO);
        integrationService.integrate(accountDTO, integrationDTO);
        Assert.assertEquals("Счет открыт", integrationDTO.getMessage());
    }

    @Test
    public void testIntegrateMvdInTheList(){
        AccountDTO accountDTO = IntegrationServiceHelper.getAccountDTO();
        MvdDTO mvdDTO = IntegrationServiceHelper.getMvdDTOOnTheList();
        BankDTO bankDTO = IntegrationServiceHelper.getBankDTONotBankClient();
        CreditDTO creditDTO = IntegrationServiceHelper.getCreditDTODataNotFound();
        IntegrationDTO integrationDTO = IntegrationDTO.builder()
                .validationDTO(validationDTO)
                .build();
        when(mvdService.checkPassportMVD(accountDTO.getPassport()))
                .thenReturn(mvdDTO);
        when(accountService.checkAccountBankClient(accountDTO.getPassport()))
                .thenReturn(bankDTO);
        when(creditService.findDataOnCreditHistory(accountDTO.getPassport()))
                .thenReturn(creditDTO);
        integrationService.integrate(accountDTO, integrationDTO);
        Assert.assertEquals("Открытие счета невозможно, пожалуйста, обратитесь в офис банка",
                integrationDTO.getMessage());
    }

    @Test
    public void testIntegrationBankClient(){
        AccountDTO accountDTO = IntegrationServiceHelper.getAccountDTO();
        BankDTO bankDTO = IntegrationServiceHelper.getBankDTOBankClient();
        OpeningDTO openingDTO = IntegrationServiceHelper.getOpeningDTOSuccessful();
        IntegrationDTO integrationDTO = IntegrationDTO.builder()
                .validationDTO(validationDTO)
                .build();
        when(accountService.checkAccountBankClient(accountDTO.getPassport()))
                .thenReturn(bankDTO);
        when(openingService.addAccount(accountDTO))
                .thenReturn(openingDTO);
        integrationService.integrate(accountDTO, integrationDTO);
        Assert.assertEquals("Счет открыт", integrationDTO.getMessage());
    }

    @Test
    public void testIntegrateCreditBadDebt(){
        AccountDTO accountDTO = IntegrationServiceHelper.getAccountDTO();
        MvdDTO mvdDTO = IntegrationServiceHelper.getMvdDTONotOnTheList();
        BankDTO bankDTO = IntegrationServiceHelper.getBankDTONotBankClient();
        CreditDTO creditDTO = IntegrationServiceHelper.getCreditDTOBadDebt();
        IntegrationDTO integrationDTO = IntegrationDTO.builder()
                .validationDTO(validationDTO)
                .build();
        when(mvdService.checkPassportMVD(accountDTO.getPassport()))
                .thenReturn(mvdDTO);
        when(accountService.checkAccountBankClient(accountDTO.getPassport()))
                .thenReturn(bankDTO);
        when(creditService.findDataOnCreditHistory(accountDTO.getPassport()))
                .thenReturn(creditDTO);
        integrationService.integrate(accountDTO, integrationDTO);
        Assert.assertEquals("Открытие счета невозможно, пожалуйста, обратитесь в офис банка",
                integrationDTO.getMessage());
    }

    @Test
    public void testIntegrateCreditNoDebt(){
        AccountDTO accountDTO = IntegrationServiceHelper.getAccountDTO();
        MvdDTO mvdDTO = IntegrationServiceHelper.getMvdDTONotOnTheList();
        BankDTO bankDTO = IntegrationServiceHelper.getBankDTONotBankClient();
        CreditDTO creditDTO = IntegrationServiceHelper.getCreditDTONoDebt();
        OpeningDTO openingDTO = IntegrationServiceHelper.getOpeningDTOSuccessful();
        IntegrationDTO integrationDTO = IntegrationDTO.builder()
                .validationDTO(validationDTO)
                .build();

        when(mvdService.checkPassportMVD(accountDTO.getPassport()))
                .thenReturn(mvdDTO);
        when(accountService.checkAccountBankClient(accountDTO.getPassport()))
                .thenReturn(bankDTO);
        when(creditService.findDataOnCreditHistory(accountDTO.getPassport()))
                .thenReturn(creditDTO);
        when(openingService.addAccount(accountDTO))
                .thenReturn(openingDTO);
        integrationService.integrate(accountDTO, integrationDTO);
        Assert.assertEquals("Счет открыт", integrationDTO.getMessage());
    }
}