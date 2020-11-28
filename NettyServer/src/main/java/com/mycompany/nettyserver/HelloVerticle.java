/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nettyserver;

import io.vertx.core.AbstractVerticle;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.types.HttpEndpoint;

/**
 *
 * @author tuantv
 */
public class HelloVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        ServiceDiscovery discovery = ServiceDiscovery.create(vertx);
        // As we know we want to use an HTTP microservice, we can
// retrieve a WebClient already configured for the service
        HttpEndpoint
                .rxGetWebClient(discovery,
                        // This method is a filter to select the service
                        rec -> rec.getName().endsWith("hello")
                ).
                flatMap(client
                        -> // We have retrieved the WebClient, use it to call
                        // the service
                        client.get("/").as(BodyCodec.string()).rxSend())
                .subscribe(response -> System.out.println(response.body()));
    }

}
