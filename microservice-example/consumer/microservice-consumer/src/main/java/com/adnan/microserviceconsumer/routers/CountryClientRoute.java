package com.adnan.microserviceconsumer.routers;

import com.adnan.microserviceconsumer.bean.GetCountryRequestBuilder;
import com.adnan.springsoap.gen.GetCountryResponse;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class CountryClientRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {


        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto)
                .host("localhost").port(8080);


        rest("/api/country")
                .get("/search?country={country}")
                .to("direct:getCountry");

        from("direct:getCountry")
                .log("Received request for country: ${header.country}")
                .setBody(simple("${header.country}"))
                .bean(GetCountryRequestBuilder.class)
                .to("cxf:http://localhost:8081/ws?serviceClass=com.adnan.springsoap.gen.CountriesPort&defaultOperationName=getCountry")
                .convertBodyTo(GetCountryResponse.class)
                .marshal().json(JsonLibrary.Jackson);


    }
}
