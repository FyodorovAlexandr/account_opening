package com.accountopening.client.service.impl;

import com.accountopening.client.dto.*;
import com.accountopening.client.enums.BankMessage;
import com.accountopening.client.enums.CreditAttribute;
import com.accountopening.client.enums.MVDAttribute;
import com.accountopening.client.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IntegrationServiceImpl implements IntegrationService {

    private final MVDService mvdService;
    private final AccountService accountService;
    private final OpeningService openingService;
    private final CreditService creditService;

    public IntegrationServiceImpl(MVDService mvdService, AccountService accountService,
                                  OpeningService openingService, CreditService creditService) {
        this.mvdService = mvdService;
        this.accountService = accountService;
        this.openingService = openingService;
        this.creditService = creditService;
    }

    @Override
    public IntegrationDTO integrate(AccountDTO accountDTO, IntegrationDTO integrationDTO) {
        BankDTO bankDTO = accountService.checkAccountBankClient(accountDTO.getPassport());
        if (bankDTO.getBankMessage().equals(BankMessage.NOT_BANK_CLIENT)) {
            IntegrationDTO outerSystemResultDTO = checkPassportWithOuterSystems(accountDTO.getPassport());
            MvdDTO mvdDTO = outerSystemResultDTO.getMvdDTO();
            CreditDTO creditDTO = outerSystemResultDTO.getCreditDTO();
            if (creditDTO.getCreditAttribute().equals(CreditAttribute.NO_DEBT)
                    || creditDTO.getCreditAttribute().equals(CreditAttribute.DATA_NOT_FOUND)
                    && mvdDTO.getMvdAttribute().equals(MVDAttribute.NOT_ON_THE_LIST)) {
                integrationDTO.setBankDTO(bankDTO);
                integrationDTO.setCreditDTO(creditDTO);
                integrationDTO.setMvdDTO(mvdDTO);
                OpeningDTO openingDTO = openingService.addAccount(accountDTO);
                integrationDTO.setOpeningDTO(openingDTO);
            } else {
                integrationDTO.setBankDTO(bankDTO);
                integrationDTO.setCreditDTO(creditDTO);
                integrationDTO.setMvdDTO(mvdDTO);
                integrationDTO.setMessage("Открытие счета невозможно, пожалуйста, обратитесь в офис банка");
                return integrationDTO;
            }
        } else {
            integrationDTO.setBankDTO(bankDTO);
        }
        integrationDTO.setMessage("Счет открыт");
        return integrationDTO;
    }

    private IntegrationDTO checkPassportWithOuterSystems(String passport) {
        IntegrationDTO outerSystemResultDTO = new IntegrationDTO();
        Thread[] threads = new Thread[2];

        log.debug("Вызов внешней системы: МВД");
        threads[0] = new Thread(() ->
                outerSystemResultDTO.setMvdDTO(mvdService.checkPassportMVD(passport)));
        threads[0].start();

        log.debug("Вызов внешней системы: Бюро кредитных историй");
        threads[1] = new Thread(() ->
                outerSystemResultDTO.setCreditDTO(creditService.findDataOnCreditHistory(passport)));
        threads[1].start();

        try {
            threads[0].join();
            threads[1].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return outerSystemResultDTO;
    }
}
