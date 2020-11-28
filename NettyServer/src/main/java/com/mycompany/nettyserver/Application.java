/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nettyserver;

import io.vertx.core.Vertx;

/**
 *
 * @author tuantv
 */
public class Application {
    
    public static void main(String[] args) {
        HelloVerticle helloVerticle = new HelloVerticle();
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(helloVerticle);
    }
}
