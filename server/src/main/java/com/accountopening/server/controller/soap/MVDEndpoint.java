package com.accountopening.server.controller.soap;

import com.accountopening.server.entity.MVD;
import com.accountopening.server.repository.MVDRepository;
import localhost._8081.ws.mvd.GetMVDRequest;
import localhost._8081.ws.mvd.GetMVDResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MVDEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8081/ws/mvd";

    private final MVDRepository mvdRepository;

    public MVDEndpoint(MVDRepository mvdRepository) {
        this.mvdRepository = mvdRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMVDRequest")
    @ResponsePayload
    public GetMVDResponse getName(@RequestPayload GetMVDRequest request) {
        GetMVDResponse response = new GetMVDResponse();
        MVD mvd = mvdRepository.findByPassport(request.getPassport()).orElse(null);
        response.setIsInMVDList(mvd != null && mvd.getPassport() != null);
        return response;
    }
}
