package com.accountopening.server.service;

import com.accountopening.server.enums.CreditAttribute;

public interface CreditService {

    CreditAttribute findDataOnCreditHistory(String passport);

}
