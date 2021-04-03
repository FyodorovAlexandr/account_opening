package com.accountopening.client.service.impl;

import com.accountopening.client.dto.MvdDTO;
import com.accountopening.client.enums.MVDAttribute;
import com.accountopening.client.integration.soap.MVDClient;
import com.accountopening.client.service.MVDService;
import com.accountopening.client.wsdl.GetMVDResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MVDServiceImpl implements MVDService {
    MVDClient client;

    @Autowired
    public MVDServiceImpl(MVDClient client) {
        this.client = client;
    }

    @Override
    public MvdDTO checkPassportMVD(String passport) {
        MvdDTO mvdDTO = new MvdDTO();
        GetMVDResponse response = client.checkPassportMVD(passport);
        if (response.isIsInMVDList()) {
            mvdDTO.setMvdAttribute(MVDAttribute.IN_THE_LIST);
            mvdDTO.setStatus(MVDAttribute.IN_THE_LIST.getStatus());
            mvdDTO.setMessage(MVDAttribute.IN_THE_LIST.getMessage());
        } else {
            mvdDTO.setMvdAttribute(MVDAttribute.NOT_ON_THE_LIST);
            mvdDTO.setStatus(MVDAttribute.NOT_ON_THE_LIST.getStatus());
            mvdDTO.setMessage(MVDAttribute.NOT_ON_THE_LIST.getMessage());
        }
        return mvdDTO;
    }
}
