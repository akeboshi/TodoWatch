/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.Controller;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jp.co.javapractise.todowatch.entity.api.CreateIN;
import jp.co.javapractise.todowatch.entity.api.CreateOut;
import jp.co.javapractise.todowatch.entity.dao.Category;
import jp.co.javapractise.todowatch.entity.dao.Person;
import jp.co.javapractise.todowatch.service.TodoService;
import jp.co.javapractise.todowatch.service.impl.TodoServiceImpl;
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

    /**
     *
     * @param data
     * @param data2
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public String find(
            @RequestParam("data") String data,
            @RequestParam("data2") String data2) {
        data = data + data2;
        return data;
    }
  
    @ResponseBody
    @RequestMapping(method=RequestMethod.POST)
    public CreateOut create(
            @RequestBody CreateIN ct
    ) {
        System.out.println("test");
        return getService().create(ct);
    }
    
    private TodoService getService(){
        if (dbsetting.equals(MONGO_DB))
            return new TodoServiceImpl();
        else 
            return null;
    }
}
