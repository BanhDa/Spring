/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.ntqsolution.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author tuantv
 */
@Getter
@Configuration
public class Config {
    
    @Value("${application.logging.currentDBOperation.slowTime}")
    private long dbOpeationSlowTime;
}
