package com.accountopening.client.dto;

import lombok.Builder;

@Builder
public class IntegrationDTO {
    private ValidationDTO validationDTO;
    private OpeningDTO openingDTO;
    private BankDTO bankDTO;
    private MvdDTO mvdDTO;
    private CreditDTO creditDTO;
    private String message;

    public IntegrationDTO() {
    }

    public IntegrationDTO(ValidationDTO validationDTO, OpeningDTO openingDTO,
                          BankDTO bankDTO, MvdDTO mvdDTO, CreditDTO creditDTO, String message) {
        this.validationDTO = validationDTO;
        this.openingDTO = openingDTO;
        this.bankDTO = bankDTO;
        this.mvdDTO = mvdDTO;
        this.creditDTO = creditDTO;
        this.message = message;
    }

    public ValidationDTO getValidationDTO() {
        return validationDTO;
    }

    public void setValidationDTO(ValidationDTO validationDTO) {
        this.validationDTO = validationDTO;
    }

    public OpeningDTO getOpeningDTO() {
        return openingDTO;
    }

    public void setOpeningDTO(OpeningDTO openingDTO) {
        this.openingDTO = openingDTO;
    }

    public BankDTO getBankDTO() {
        return bankDTO;
    }

    public void setBankDTO(BankDTO bankDTO) {
        this.bankDTO = bankDTO;
    }

    public MvdDTO getMvdDTO() {
        return mvdDTO;
    }

    public void setMvdDTO(MvdDTO mvdDTO) {
        this.mvdDTO = mvdDTO;
    }

    public CreditDTO getCreditDTO() {
        return creditDTO;
    }

    public void setCreditDTO(CreditDTO creditDTO) {
        this.creditDTO = creditDTO;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
