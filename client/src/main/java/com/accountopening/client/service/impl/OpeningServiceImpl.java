package com.accountopening.client.service.impl;

import com.accountopening.client.dto.AccountDTO;
import com.accountopening.client.dto.OpeningDTO;
import com.accountopening.client.enums.OpeningMessage;
import com.accountopening.client.integration.DataProvider;
import com.accountopening.client.service.OpeningService;
import org.springframework.stereotype.Service;

@Service
public class OpeningServiceImpl implements OpeningService {
    final
    DataProvider dataProvider;

    public OpeningServiceImpl(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public OpeningDTO addAccount(AccountDTO accountDTO) {
        OpeningMessage openingMessage = dataProvider.addAccount(accountDTO);
        return OpeningDTO.builder()
                .openingMessage(openingMessage)
                .status(openingMessage.getStatus())
                .message(openingMessage.getMessage())
                .build();
    }
}
