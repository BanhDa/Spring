/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.ntqsolution.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tuantv
 */
@RestController
public class UserController {
    
    @GetMapping("/admin")
    public String helloAdmin() {
        return "{\"data\":\"hello admin\"}";
    }
    
    @GetMapping("/ad")
    public String helloAd() {
        return "{\"data\":\"hello ad\"}";
    }
    
    @GetMapping("/register")
    public String register() {
        return "{\"data\":\"register\"}";
    }
}
