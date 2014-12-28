/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.Controller;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import jp.co.javapractise.todowatch.entity.api.CreateRequest;
import jp.co.javapractise.todowatch.entity.api.CreateResponse;
import jp.co.javapractise.todowatch.entity.api.FindResponse;
import jp.co.javapractise.todowatch.entity.dao.Category;
import jp.co.javapractise.todowatch.entity.dao.Person;
import jp.co.javapractise.todowatch.entity.dao.Todo;
import jp.co.javapractise.todowatch.service.TodoService;
import jp.co.javapractise.todowatch.service.impl.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akari
 */
@Controller
@RequestMapping(value = "/json")
public class JsonController {
    private final String dbsetting = "mongo";
    private final String MONGO_DB = "mongo";
    @Autowired
    private HttpServletRequest request;

    /**
     *
     * @param data
     * @param data2
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public List<FindResponse> find(
            @RequestParam Integer start,
            @RequestParam Integer count,
            @RequestParam Integer category,
            @RequestParam Date sday,
            @RequestParam Date eday,
            @RequestParam Integer status) {
        List<Todo> todos = getService().find(category, status, start, count, sday, eday);
        Set<Integer> cSet = new HashSet<Integer>();
        for (Todo todo : todos) {
            cSet.add(todo.getCategory());
        }
        
        List<FindResponse> res = new ArrayList<>();
        
        return res;
    }
  
    @ResponseBody
    @RequestMapping(method=RequestMethod.POST)
    public CreateResponse create(
            @RequestBody CreateRequest cReq
    ) {
        Todo reqTodo = new Todo();
        Category cat = new Category();
        cat.setBody(cReq.getCategory());
        reqTodo.setBody(cReq.getBody());
        
        
        Todo resTodo = getService().create(reqTodo);
        CreateResponse cRes = new CreateResponse();
        return cRes;
    }
    
    private TodoService getService(){
        if (dbsetting.equals(MONGO_DB))
            return new TodoServiceImpl();
        else 
            return null;
    }
}
