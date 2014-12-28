/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 灯
 */
public final class TodoWatchException extends Exception{

    public TodoWatchException(String message) {
        this(message, 500);
    }
    
    public TodoWatchException(String message,Integer code) {
        TodoWatchException.NotFound(message);
    }
    
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    static public String NotFound (String message){
        ModelAndView m = new ModelAndView();
        
        return message;
    }
}
