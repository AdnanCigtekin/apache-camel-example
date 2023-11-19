package com.adnan.microserviceconsumer.bean;

import com.adnan.springsoap.gen.GetCountryRequest;

public class GetCountryRequestBuilder {

    public GetCountryRequest getBook(String name) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);

        return request;
    }
}
