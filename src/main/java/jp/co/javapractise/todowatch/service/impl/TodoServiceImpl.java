/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.service.impl;

import com.mongodb.WriteResult;
import java.util.Date;
import java.util.List;
import java.util.Set;
import jp.co.javapractise.todowatch.config.MongoDBConfig;
import jp.co.javapractise.todowatch.entity.dao.Category;
import jp.co.javapractise.todowatch.entity.dao.Todo;
import jp.co.javapractise.todowatch.service.TodoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author akari
 */
public class TodoServiceImpl implements TodoService{
    ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
    MongoTemplate mongo = ctx.getBean(MongoTemplate.class);

        @Override
    public List<Todo> find(Integer category, Integer status, Integer start, Integer count, Date sday, Date eday) {
        return mongo.find(null, Todo.class);
    }
 
    public List<Category> findCategory(Set<Integer> cat){
        return mongo.find(null, Category.class);
    }

    @Override
    public void delete(String id) {
        WriteResult wr = mongo.remove(id);
        if (wr.getError() != null){
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Todo create(Todo todo) {
        if(todo.getId() == null){
            mongo.insert(todo);
        } else {
            Query query = new Query(Criteria.where("id").is(todo.getId()));
            mongo.upsert(query, null, Todo.class);
        }
        return todo;
    }
    
    public Category getCategory(Category category){
        if (category.getId() != null){
            Query query = new Query(Criteria.where("id").is(category.getId()));
            List<Category> response = mongo.find(query, Category.class);
            return response.get(0);
        } else if (category.getBody() != null){
            mongo.insert(category);
            Query query = new Query(Criteria.where("body").is(category.getBody()));
            List<Category> response = mongo.find(query, Category.class);
            return response.get(0);
        } else {
            return null;
        }
    }



}
