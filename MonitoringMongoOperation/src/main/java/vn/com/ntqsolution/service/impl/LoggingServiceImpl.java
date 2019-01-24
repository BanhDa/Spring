/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.ntqsolution.service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.com.ntqsolution.config.Config;
import vn.com.ntqsolution.repository.impl.RepositoryImpl;
import vn.com.ntqsolution.service.base.LoggingService;

/**
 *
 * @author tuantv
 */
@Slf4j
@Service
@AllArgsConstructor
public class LoggingServiceImpl implements LoggingService{

    private final RepositoryImpl repositoryImpl;
    private final Config config;
    
    @Override
    public void addSlowCurrentMongoOperationLog() {
        List<Object> currentOps = repositoryImpl.getSlowCurrentDBOperation(config.getDbOpeationSlowTime());
        if (currentOps == null || currentOps.isEmpty()) {
            return;
        }
        currentOps.forEach(currentOp -> {
            log.info(currentOp.toString());
        });
        log.info("-----------------------------------------------------------------------------------------");
    }
    
}
