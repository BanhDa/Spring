/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.ntqsolution.worker;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.com.ntqsolution.service.base.LoggingService;

/**
 *
 * @author tuantv
 */
@Slf4j
@Component
@AllArgsConstructor
public class CurrentDBOperationLogging {
    
    private final LoggingService loggingService;
    
    @Scheduled(fixedDelayString = "${application.logging.currentDBOperation.fixedDelay}")
    public void logging() {
        loggingService.addSlowCurrentMongoOperationLog();
    }
}
