/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
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
     * @param data
     * @param data2
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public String cont1(@RequestParam("data") String data,
            @RequestParam("data2") String data2) {
        data = data + data2;
        return data;
    }
}
