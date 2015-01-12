/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.Controller;

import jp.co.javapractise.todowatch.exception.TodoWatchException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jp.co.javapractise.todowatch.entity.api.CreateRequest;
import jp.co.javapractise.todowatch.entity.api.CreateResponse;
import jp.co.javapractise.todowatch.entity.api.FindResponse;
import jp.co.javapractise.todowatch.entity.dao.Category;
import jp.co.javapractise.todowatch.entity.dao.Todo;
import jp.co.javapractise.todowatch.service.TodoService;
import jp.co.javapractise.todowatch.service.impl.TodoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     *
     * @param start
     * @param count
     * @param category
     * @param sday
     * @param eday
     * @param status
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<FindResponse>> find(
            @RequestParam(defaultValue = "1") Integer start,
            @RequestParam(defaultValue = "10") Integer count,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Date sday,
            @RequestParam(required = false) Date eday,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) throws TodoWatchException {
        checkLogin(request);
        String userId =  (String)request.getSession().getAttribute("userId");
        List<Todo> todos = getService().find(userId, category, status, start, count, sday, eday);
        
        List<Category> cList = getService().getCategory(userId);
        Map<String,Category> cMap = new HashMap<>();
        for (Category ca : cList){
            cMap.put(ca.getId(), ca);
        }
        
        List<FindResponse> res = new ArrayList<>();
        for (Todo todo : todos){
            FindResponse fr = new FindResponse();
            fr.setTitle(todo.getTitle());
            fr.setDescription(todo.getDescription());
            fr.setCategory(cMap.get(todo.getCategory()));
            fr.setId(todo.getId());
            fr.setLevel(todo.getLevel());
            fr.setStatus(todo.getStatus());
            res.add(fr);
        }
        
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    
        /**
     *
     * @param start
     * @param count
     * @param category
     * @param sday
     * @param eday
     * @param status
     * @return
     */
    @RequestMapping(value="/count", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Long> count(
            @RequestParam(defaultValue = "1") Integer start,
            @RequestParam(defaultValue = "10") Integer count,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Date sday,
            @RequestParam(required = false) Date eday,
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) throws TodoWatchException {
        checkLogin(request);
        String userId =  (String)request.getSession().getAttribute("userId");
        return new ResponseEntity<>(
                getService().count(userId, category, status, start, count, sday, eday),
                HttpStatus.OK);
    }
    
    @ResponseBody
    @RequestMapping(method=RequestMethod.DELETE, value="{id}")
    public ResponseEntity<String> update (
            @PathVariable String id,
            HttpServletRequest request
    ) throws TodoWatchException{
        checkLogin(request);
        checkId(id);
        
        getService().delete(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @ResponseBody
    @RequestMapping(method=RequestMethod.PUT, value="{id}")
    public CreateResponse update (
        @RequestBody CreateRequest cReq,
            @PathVariable String id,
            HttpServletRequest request
    ) throws TodoWatchException{
        checkLogin(request);
        checkId(id);
        return save(id, cReq, request);
    }
  
    @ResponseBody
    @RequestMapping(method=RequestMethod.POST)
    public CreateResponse create(
            @RequestBody CreateRequest cReq,
            HttpServletRequest request
    ) throws TodoWatchException {
        checkLogin(request);
        System.out.println(request.getSession().getAttribute("userId"));
        return save(null, cReq, request);
    }
    
    private CreateResponse save (String id, CreateRequest cr, HttpServletRequest request){
        Todo reqTodo = new Todo();
        Category cat = new Category();
        cat.setBody(cr.getCategory());
        reqTodo.setId(id);
        reqTodo.setTitle(cr.getTitle());
        reqTodo.setDeadline(cr.getDeadline());
        reqTodo.setLevel(cr.getLevel());
        reqTodo.setStatus(cr.getStatus());
        String userId = (String)request.getSession().getAttribute("userId");
        reqTodo.setUserId(userId);
 
        Todo resTodo = getService().create(reqTodo);            
        List<Category> cList = getService().getCategory(userId);
        Map<String,Category> cMap = new HashMap<>();
        for (Category ca : cList){
            cMap.put(ca.getId(), ca);
        }
        CreateResponse cRes = new CreateResponse();
        cRes.setTitle(resTodo.getTitle());
        cRes.setDescription(resTodo.getDescription());
        cRes.setCategory(cMap.get(resTodo.getCategory()));
        cRes.setId(resTodo.getId());
        cRes.setLevel(resTodo.getLevel());
        cRes.setStatus(resTodo.getStatus());
        
        return cRes;
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
