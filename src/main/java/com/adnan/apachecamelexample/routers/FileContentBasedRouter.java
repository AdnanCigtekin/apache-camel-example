package com.adnan.apachecamelexample.routers;

import com.adnan.apachecamelexample.beans.Person;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;


//@Component
public class FileContentBasedRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file://files/input/filter")
                .log("${headers}")
                .log("${body}")

                .unmarshal().json(JsonLibrary.Jackson, Person.class)
                .choice()

                    .when(simple("${body.team} == 'Fenerbahçe'"))
                        .marshal().json(JsonLibrary.Jackson)
                        .to("file://files/output/filter/fenerbahçe")

                    .when(simple("${body.team} == 'Galatasaray'"))
                        .marshal().json(JsonLibrary.Jackson)
                        .to("file://files/output/filter/galatasaray")

                .otherwise()
                    .log("IT IS AN UNKNOWN TEAM")
                    .to("file://files/output/filter/other-teams");

    }
}
