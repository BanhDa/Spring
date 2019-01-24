/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.ntqsolution.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author tuantv
 */

@Getter
@Configuration
public class MongoConfig {
    
    @Value("${application.database.mongodb.host}")
    private String host;

    @Value("${application.database.mongodb.port}")
    private Integer port;

    @Value("${application.database.mongodb.user}")
    private String user;

    @Value("${application.database.mongodb.password}")
    private String password;

    @Value("${application.database.mongodb.authenticationDatabase}")
    private String authenticationDatabase;
    
    @Bean
    public MongoClient getMongoClient() {
        MongoCredential mongoCredential = MongoCredential.createCredential(user, authenticationDatabase, password.toCharArray());
        return new MongoClient(new ServerAddress(host, port), Arrays.asList(mongoCredential));
    }
}
