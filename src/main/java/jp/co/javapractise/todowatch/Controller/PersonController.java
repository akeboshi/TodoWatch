/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.Controller;

import java.util.List;
import jp.co.javapractise.todowatch.config.MongoDBConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import jp.co.javapractise.todowatch.entity.dao.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author akari
 */
@Controller
@RequestMapping(value = "/person")
public class PersonController {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
    MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);
    /**
     *
     * @return
     */
    @RequestMapping(value="set",method=RequestMethod.GET)
    @ResponseBody()
    public Person cont1() {
        Person p = new Person("john",25);
        mongoTemplate.insert(p);
        return p;
    }
    
    @RequestMapping(value="find",method=RequestMethod.GET)
    @ResponseBody()
    public List<Person> cont2() {
        List<Person> persons = mongoTemplate.find(
                new Query(Criteria.where("name").is("john")),
                Person.class);
        return persons;
    }
}
