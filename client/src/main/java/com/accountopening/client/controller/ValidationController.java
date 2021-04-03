package com.accountopening.client.controller;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.IntegrationDTO;
import com.accountopening.client.dto.ValidationDTO;
import com.accountopening.client.enums.*;
import com.accountopening.client.service.IntegrationService;
import com.accountopening.client.service.ValidationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class ValidationController {
    private final ValidationService validationService;
    private final IntegrationService integrationService;

    public ValidationController(ValidationService validationService, IntegrationService integrationService) {
        this.validationService = validationService;
        this.integrationService = integrationService;
    }

    @GetMapping("/check")
    public String search(@ModelAttribute("account") AccountDTO accountDTO) {
        return "search";
    }

    @PostMapping("/validate")
    public String validate(Model model, @ModelAttribute("account") AccountDTO accountDTO) {
        IntegrationDTO integrationDTO = new IntegrationDTO();
        ValidationDTO validationDTO = validationService.validate(accountDTO);

        if (validationDTO.isCriticalError()) {
            checkFormFieldsValidation(model, validationDTO);
            return "search";
        }
        integrationDTO.setValidationDTO(validationDTO);

        if (!validationDTO.isCriticalError()) {
            integrationDTO = integrationService.integrate(accountDTO, integrationDTO);

            if (integrationDTO.getBankDTO().getBankMessage().equals(BankMessage.IS_BANK_CLIENT)) {
                return "success";
            }

            if (integrationDTO.getMvdDTO().getMvdAttribute().equals(MVDAttribute.IN_THE_LIST)
                    || integrationDTO.getCreditDTO().getCreditAttribute().equals(CreditAttribute.BAD_DEBT)) {
                return "error";
            }

            if (integrationDTO.getOpeningDTO() != null &&
                    integrationDTO.getOpeningDTO().getOpeningMessage().equals(OpeningMessage.ERROR_ACCOUNT_OPENING)) {
                return "error";
            }
        }

        return "success";
    }

    private void checkFormFieldsValidation(Model model, ValidationDTO validationDTO) {
        if (validationDTO.getErrors().stream().anyMatch(e -> e.getAttribute().equals(Attribute.PASSPORT))) {
            String passportError = validationDTO.getErrors().stream().filter(e -> e.getAttribute().equals(Attribute.PASSPORT))
                    .collect(Collectors.toList()).get(0).getMessage();
            model.addAttribute("passportError", passportError);
        }
        if (validationDTO.getErrors().stream().anyMatch(e -> e.getAttribute().equals(Attribute.NAME))) {
            String nameError = validationDTO.getErrors().stream().filter(e -> e.getAttribute().equals(Attribute.NAME))
                    .collect(Collectors.toList()).get(0).getMessage();
            model.addAttribute("nameError", nameError);
        }
        if (validationDTO.getErrors().stream().anyMatch(e -> e.getAttribute().equals(Attribute.LAST_NAME))) {
            String surnameError = validationDTO.getErrors().stream().filter(e -> e.getAttribute().equals(Attribute.LAST_NAME))
                    .collect(Collectors.toList()).get(0).getMessage();
            model.addAttribute("surnameError", surnameError);
        }
        if (validationDTO.getErrors().stream().anyMatch(e -> e.getAttribute().equals(Attribute.MIDDLE_NAME))) {
            String patronymicError = validationDTO.getErrors().stream().filter(e -> e.getAttribute().equals(Attribute.MIDDLE_NAME))
                    .collect(Collectors.toList()).get(0).getMessage();
            model.addAttribute("patronymicError", patronymicError);
        }
        if (validationDTO.getErrors().stream().anyMatch(e -> e.getAttribute().equals(Attribute.ADDRESS))) {
            String addressError = validationDTO.getErrors().stream().filter(e -> e.getAttribute().equals(Attribute.ADDRESS))
                    .collect(Collectors.toList()).get(0).getMessage();
            model.addAttribute("addressError", addressError);
        }
        if (validationDTO.getErrors().stream().anyMatch(e -> e.getAttribute().equals(Attribute.BIRTHDATE))) {
            String birthdateError = validationDTO.getErrors().stream().filter(e -> e.getAttribute().equals(Attribute.BIRTHDATE))
                    .collect(Collectors.toList()).get(0).getMessage();
            model.addAttribute("birthdateError", birthdateError);
        }
    }
}
