package com.accountopening.client.service.impl;

import com.accountopening.client.dto.CreditDTO;
import com.accountopening.client.enums.CreditAttribute;
import com.accountopening.client.service.CreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreditServiceImpl implements CreditService {

    RestTemplate restTemplate = new RestTemplate();
    String fooResourceUrl = "http://localhost:8081/credit";

    @Override
    public CreditDTO findDataOnCreditHistory(String passport) {
        ResponseEntity<CreditAttribute> response
                = restTemplate.getForEntity(fooResourceUrl + "?passport=" + passport,
                CreditAttribute.class);
        CreditDTO creditDTO = CreditDTO.builder()
                .creditAttribute(response.getBody())
                .status(response.getBody().getStatus())
                .message(response.getBody().getMessage())
                .build();
        return creditDTO;
    }
}
