/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akari
 */
@Controller
@RequestMapping(value = "/json")
public class JsonController {

    public JsonController() {
        this.jsonList = new Class1();
    }
    
    private class Class1{
        public String a;
        public String b;
    }
    
    private final Class1 jsonList;

    /**
     *
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public Class1 cont1() {
        jsonList.a = "test1";
        jsonList.b = "test2";
        return jsonList;
    }
}
