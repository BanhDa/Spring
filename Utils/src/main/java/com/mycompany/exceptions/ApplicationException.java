/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tuantv
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplicationException extends RuntimeException{
    
    private int code;
    
    public ApplicationException() {
        super();
    }
    
    public ApplicationException(int code, String message) {
        super(message);
        this.code = code;
    }
}
