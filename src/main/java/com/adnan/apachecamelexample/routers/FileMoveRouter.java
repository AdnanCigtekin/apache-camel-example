package com.adnan.apachecamelexample.routers;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileMoveRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file://files/input")
                .log("${headers}")
                .log("${body}")
                .to("file://files/output");
    }
}
