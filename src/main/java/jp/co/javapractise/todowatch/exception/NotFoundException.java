/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author akari
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NotFoundException  extends TodoWatchException
{
    public NotFoundException(String message) {
        super(message);
    }
}
