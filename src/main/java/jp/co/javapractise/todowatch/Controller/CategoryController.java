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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody()
    public ResponseEntity<CategoryResponse> create(
                @RequestBody CategoryResponse cReq,
            HttpServletRequest request
    ) throws TodoWatchException {
        checkLogin(request);
        String userId =  (String)request.getSession().getAttribute("userId");
        Category reqCat = new Category();
        reqCat.setBody(cReq.getBody());
        reqCat.setUserId(userId);
        Category res = getService().createCategory(reqCat);
        CategoryResponse resC = new CategoryResponse();
        resC.setBody(res.getBody());
        resC.setId(res.getId());
        return new ResponseEntity<>(resC,HttpStatus.CREATED);
    }
    
    @RequestMapping(method=RequestMethod.DELETE, value="{id}")
    @ResponseBody
    public ResponseEntity<String> delete(
            @PathVariable String id,
            HttpServletRequest request
    ) throws TodoWatchException{
        checkLogin(request);
        getService().categoryDelete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
