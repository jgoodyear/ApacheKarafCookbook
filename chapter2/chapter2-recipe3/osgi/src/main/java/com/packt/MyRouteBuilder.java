package com.packt;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

    public void configure() {

        from("file:src/data?noop=true")
            .choice()
                .when(xpath("/recipe = 'cookie'"))
                    .log("Cookie  message")
                    .to("file:target/messages/cookies")
                .otherwise()
                    .log("Other message")
                    .to("file:target/messages/others");
    }

}
