/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.service.impl;

import jp.co.javapractise.todowatch.config.MongoDBConfig;
import jp.co.javapractise.todowatch.entity.api.CreateIN;
import jp.co.javapractise.todowatch.entity.api.CreateOut;
import jp.co.javapractise.todowatch.entity.dao.Category;
import jp.co.javapractise.todowatch.entity.dao.Todo;
import jp.co.javapractise.todowatch.service.TodoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author akari
 */
public class TodoServiceImpl implements TodoService{
    ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
    MongoTemplate mongo = ctx.getBean(MongoTemplate.class);


    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CreateOut create(CreateIN api) {
        Todo todo = new Todo();
        todo.setBody(api.getBody());
        Category cat = new Category();
        mongo.insert(todo);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
