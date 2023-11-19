package com.adnan.microserviceconsumer.routers;

import com.adnan.microserviceconsumer.bean.GetCountryRequestBuilder;
import com.adnan.springsoap.gen.GetCountryResponse;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class CountryClientRoute extends RouteBuilder {


    @Override
    public void configure() throws Exception {


        from("direct:getCountry")
                .setBody(constant("Spain"))
                .bean(GetCountryRequestBuilder.class)
                .to("cxf:http://localhost:8081/ws?serviceClass=com.adnan.springsoap.gen.CountriesPort&defaultOperationName=getCountry")
                .process(e -> {
                    System.out.println((e.getIn().getBody()));
                })
                .log("Response: ${body[0]} ")
                .convertBodyTo(GetCountryResponse.class)
                .log("Response: ${body}")
                        .end();

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto);


        rest("/api/getCountry")
                .post("{countryName}")
                .to("direct:getCountry");


    }
}
