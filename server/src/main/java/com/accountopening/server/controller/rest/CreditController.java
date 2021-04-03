package com.accountopening.server.controller.rest;

import com.accountopening.server.enums.CreditAttribute;
import com.accountopening.server.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @GetMapping("/credit")
    public ResponseEntity<CreditAttribute> getCreditHistory(@RequestParam String passport) {
        CreditAttribute creditAttribute = creditService.findDataOnCreditHistory(passport);
        return ResponseEntity.ok(creditAttribute);
    }
}