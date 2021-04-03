package com.accountopening.client.helper.impl;

import com.accountopening.client.dto.*;
import com.accountopening.client.enums.BankMessage;
import com.accountopening.client.enums.CreditAttribute;
import com.accountopening.client.enums.MVDAttribute;
import com.accountopening.client.enums.OpeningMessage;

public class IntegrationServiceHelper {

    public static AccountDTO getAccountDTO(){
        return AccountDTO.builder()
                .name("John")
                .surname("Smith")
                .patronymic("Ivanovich")
                .passport("5555555555")
                .address("USA")
                .birthday("1999-09-09")
                .build();
    }

    public static MvdDTO getMvdDTONotOnTheList(){
        return MvdDTO.builder()
                .mvdAttribute(MVDAttribute.NOT_ON_THE_LIST)
                .status(MVDAttribute.NOT_ON_THE_LIST.getStatus())
                .message(MVDAttribute.NOT_ON_THE_LIST.getMessage())
                .build();
    }

    public static MvdDTO getMvdDTOOnTheList(){
        return MvdDTO.builder()
                .mvdAttribute(MVDAttribute.IN_THE_LIST)
                .status(MVDAttribute.IN_THE_LIST.getStatus())
                .message(MVDAttribute.IN_THE_LIST.getMessage())
                .build();
    }

    public static BankDTO getBankDTONotBankClient(){
        return BankDTO.builder()
                .bankMessage(BankMessage.NOT_BANK_CLIENT)
                .status(BankMessage.NOT_BANK_CLIENT.getStatus())
                .message(BankMessage.NOT_BANK_CLIENT.getMessage())
                .build();
    }

    public static BankDTO getBankDTOBankClient(){
        return BankDTO.builder()
                .bankMessage(BankMessage.IS_BANK_CLIENT)
                .status(BankMessage.IS_BANK_CLIENT.getStatus())
                .message(BankMessage.IS_BANK_CLIENT.getMessage())
                .build();
    }

    public static CreditDTO getCreditDTODataNotFound(){
        return CreditDTO.builder()
                .creditAttribute(CreditAttribute.DATA_NOT_FOUND)
                .status(CreditAttribute.DATA_NOT_FOUND.getStatus())
                .message(CreditAttribute.DATA_NOT_FOUND.getMessage())
                .build();
    }

    public static CreditDTO getCreditDTOBadDebt(){
        return CreditDTO.builder()
                .creditAttribute(CreditAttribute.BAD_DEBT)
                .status(CreditAttribute.BAD_DEBT.getStatus())
                .message(CreditAttribute.BAD_DEBT.getMessage())
                .build();
    }

    public static CreditDTO getCreditDTONoDebt(){
        return CreditDTO.builder()
                .creditAttribute(CreditAttribute.NO_DEBT)
                .status(CreditAttribute.NO_DEBT.getStatus())
                .message(CreditAttribute.NO_DEBT.getMessage())
                .build();
    }

    public static OpeningDTO getOpeningDTOSuccessful(){
        return OpeningDTO.builder()
                .openingMessage(OpeningMessage.SUCCESSFUL_OPENING)
                .status(OpeningMessage.SUCCESSFUL_OPENING.getStatus())
                .message(OpeningMessage.SUCCESSFUL_OPENING.getMessage())
                .build();
    }

    public static OpeningDTO getOpeningDTOErrorOpening(){
        return OpeningDTO.builder()
                .openingMessage(OpeningMessage.ERROR_ACCOUNT_OPENING)
                .status(OpeningMessage.ERROR_ACCOUNT_OPENING.getStatus())
                .message(OpeningMessage.ERROR_ACCOUNT_OPENING.getMessage())
                .build();
    }
}
