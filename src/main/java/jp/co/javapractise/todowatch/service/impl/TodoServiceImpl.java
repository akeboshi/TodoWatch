/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.service.impl;

import com.mongodb.WriteResult;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import jp.co.javapractise.todowatch.exception.TodoWatchException;
import jp.co.javapractise.todowatch.config.MongoDBConfig;
import jp.co.javapractise.todowatch.entity.dao.Category;
import jp.co.javapractise.todowatch.entity.dao.Todo;
import jp.co.javapractise.todowatch.service.TodoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author akari
 */
public class TodoServiceImpl implements TodoService {

    ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
    MongoTemplate mongo = ctx.getBean(MongoTemplate.class);

    @Override
    public List<Todo> find(String userId, String category, Integer status,
            Integer start, Integer count, Date sday, Date eday) {
        if (userId == null)
           throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Query query = new Query(Criteria.where("userId").is(userId));
        if (start != null)
            query.skip(start - 1);        
        if (count != null)
            query.limit(count);
        if (category != null)
            query.addCriteria(Criteria.where("category").is(category));
        if (status != null)
            query.addCriteria(Criteria.where("status").is(status));
        if (sday != null)
            query.addCriteria(Criteria.where("created").gte(sday));
        if (eday != null)
            query.addCriteria(Criteria.where("created").lte(eday));
        query.with(new Sort(Sort.Direction.ASC, "created"));
        return mongo.find(query, Todo.class);
    }
    
    @Override
    public Long count(String userId, String category, Integer status,
            Integer start, Integer count, Date sday, Date eday) {
        if (userId == null)
           throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Query query = new Query(Criteria.where("userId").is(userId));
        if (start != null)
            query.skip(start - 1);        
        if (count != null)
            query.limit(count);
        if (category != null)
            query.addCriteria(Criteria.where("category").is(category));
        if (status != null)
            query.addCriteria(Criteria.where("status").is(status));
        if (sday != null)
            query.addCriteria(Criteria.where("created").gte(sday));
        if (eday != null)
            query.addCriteria(Criteria.where("created").lte(eday));
        query.with(new Sort(Sort.Direction.ASC, "created"));
        return mongo.count(query, Todo.class);
    }

    public List<Category> findCategory(Set<Integer> cat) {
        return mongo.find(null, Category.class);
    }

    @Override
    public void delete(String id) {
        WriteResult wr = mongo.remove(id);
        if (wr.getError() != null) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public Todo create(Todo todo) {
        Date created = Calendar.getInstance().getTime();
        todo.setCreated(created);
        if (todo.getId() == null) {
            mongo.insert(todo);
        } else {
            Query query = new Query(Criteria.where("id").is(todo.getId()));
            mongo.upsert(query, null, Todo.class);
        }
        return todo;
    }

    public List<Category> getCategory(Category category) throws TodoWatchException {
        if (category.getId() != null) {
            Query query = new Query(Criteria.where("id").is(category.getId()));
            List<Category> response = mongo.find(query, Category.class);
            return response;
        } else if (category.getBody() != null) {
            mongo.insert(category);
            Query query = new Query(Criteria.where("body").is(category.getBody()));
            List<Category> response = mongo.find(query, Category.class);
            return response;
        } else {
            throw new TodoWatchException("Category is not found");
        }
    }

    /**
     * userIdがもつカテゴリをすべて返すメソッド
     *
     * @param userId
     * @return List Category
     */
    @Override
    public List<Category> getCategory(String userId) {
        if (userId == null) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            Query query = new Query(Criteria.where("userId").is(userId));
            List<Category> response = mongo.find(query, Category.class);
            return response;
        }
    }
}
