/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.service;

import java.util.Date;
import java.util.List;
import jp.co.javapractise.todowatch.entity.api.CreateResponse;
import jp.co.javapractise.todowatch.entity.dao.Category;
import jp.co.javapractise.todowatch.entity.dao.Todo;

/**
 *
 * @author akari
 */
public interface TodoService {
    public List<Todo> find(String userId, String category,Integer status,Integer start,Integer count,Date sday,Date eday);
    // publi get();
    public void delete(String id);
    public Todo create(Todo todo);
    public Long count(String userId, String category,Integer status,Integer start,Integer count,Date sday,Date eday);
    public List<Category> getCategory (String userId);
}
