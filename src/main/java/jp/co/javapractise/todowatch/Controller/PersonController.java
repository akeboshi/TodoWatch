/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.Controller;

import jp.co.javapractise.todowatch.config.MongoDBConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import jp.co.javapractise.todowatch.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author akari
 */
@Controller
@RequestMapping(value = "/person")
public class PersonController {

    public PersonController() {
    }
    
    

    /**
     *
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody()
    public Person cont1() {
         ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
        MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);
        Person p = new Person("john",25);
        mongoTemplate.insert(p);
        return p;
    }
}
