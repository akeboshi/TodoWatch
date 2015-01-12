/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import jp.co.javapractise.todowatch.config.MongoDBConfig;
import jp.co.javapractise.todowatch.entity.api.CategoryResponse;
import jp.co.javapractise.todowatch.entity.dao.Category;
import jp.co.javapractise.todowatch.entity.dao.Person;
import jp.co.javapractise.todowatch.exception.TodoWatchException;
import jp.co.javapractise.todowatch.service.TodoService;
import jp.co.javapractise.todowatch.service.impl.TodoServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akari
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    private final String dbsetting = "mongo";
    private final String MONGO_DB = "mongo";
    /**
     *
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody()
    public ResponseEntity<List<CategoryResponse>> get(
     HttpServletRequest request) throws TodoWatchException {
        checkLogin(request);
        String userId =  (String)request.getSession().getAttribute("userId");
        checkId(userId);
        
        List<Category> categorys = getService().getCategory(userId);
        
        List<CategoryResponse> crs = new ArrayList<>();
        for (Category cat : categorys){
            CategoryResponse cr = new CategoryResponse();
            cr.setBody(cat.getBody());
            cr.setId(cat.getId());
            crs.add(cr);
        }
        
        return new ResponseEntity<>(crs,HttpStatus.OK);
    }
    
    @RequestMapping(value="find",method=RequestMethod.GET)
    @ResponseBody()
    public ResponseEntity<Person> cont2() {
        Person p = new Person("name", 11);
        return new ResponseEntity<>(p,HttpStatus.ACCEPTED);
    }
    
    private void checkLogin(HttpServletRequest request) throws TodoWatchException{
        if (request.getSession().getAttribute("userId") == null)
            throw new TodoWatchException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        private void checkId(String id) {
        if (id == null)
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private TodoService getService(){
        if (dbsetting.equals(MONGO_DB))
            return new TodoServiceImpl();
        else 
            return null;
    }
}
