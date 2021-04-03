package com.accountopening.server.service.impl;

import com.accountopening.server.entity.Credit;
import com.accountopening.server.enums.CreditAttribute;
import com.accountopening.server.repository.CreditRepository;
import com.accountopening.server.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;

    @Override
    public CreditAttribute findDataOnCreditHistory(String passport) {
        Credit credit = creditRepository.findByPassport(passport).orElse(null);

        if (credit != null) {
            if (credit.getPassport().equalsIgnoreCase(passport) &&
                    credit.getLatePayment()) {
                return CreditAttribute.BAD_DEBT;
            } else if (credit.getPassport().equalsIgnoreCase(passport) &&
                    !credit.getLatePayment()) {
                return CreditAttribute.NO_DEBT;
            } else {
                return CreditAttribute.DATA_NOT_FOUND;
            }
        }
        return CreditAttribute.DATA_NOT_FOUND;
    }
}