/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author tuantv
 */
@Data
@AllArgsConstructor
public class AppUser {
    
    private Integer id;
    
    private String userName;
    
    private String password;
    
    private String role;
}
