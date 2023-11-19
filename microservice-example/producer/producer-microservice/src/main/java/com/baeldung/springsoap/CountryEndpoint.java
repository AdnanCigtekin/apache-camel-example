package com.baeldung.springsoap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.baeldung.springsoap.client.gen.GetCountryRequest;
import com.baeldung.springsoap.client.gen.GetCountryResponse;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.adnan.com/springsoap/gen";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        System.out.println("RECEIVED REQUEST");
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        System.out.println(response);
        return response;
    }
}
