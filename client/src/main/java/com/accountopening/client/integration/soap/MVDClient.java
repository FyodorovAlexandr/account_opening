package com.accountopening.client.integration.soap;

import com.accountopening.client.wsdl.GetMVDRequest;
import com.accountopening.client.wsdl.GetMVDResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Slf4j
public class MVDClient extends WebServiceGatewaySupport {
    public GetMVDResponse checkPassportMVD(String passport) {
        GetMVDRequest request = new GetMVDRequest();
        request.setPassport(passport);

        logger.debug("Запрос в МВД для паспорта: " + passport);

        return (GetMVDResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws/mvd", request,
                        new SoapActionCallback("GetMVDRequest"));
    }
}
