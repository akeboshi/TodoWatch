/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.config;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.*;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
/**
 *
 * @author akari
 */
@Configuration
public class MongoDBConfig {
        
    @Bean  
    public MongoFactoryBean mongo() {
        MongoFactoryBean mongo = new MongoFactoryBean();
        mongo.setHost("localhost");
        mongo.setPort(27017);
        return mongo;
    }
    
    @Bean 
    public MongoTemplate mongoTemplate(Mongo mongo) {
        UserCredentials uc = new UserCredentials("mongo", "java");
        MongoTemplate mongoTemplate = new MongoTemplate(mongo, "test", uc);
        return mongoTemplate;
    }
}
