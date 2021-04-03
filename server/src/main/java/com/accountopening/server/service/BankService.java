package com.accountopening.server.service;

import com.accountopening.server.enums.BankMessage;

public interface BankService {
    BankMessage checkIsBankAccount(String passport);
}
