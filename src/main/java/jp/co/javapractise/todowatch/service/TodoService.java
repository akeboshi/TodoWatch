/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.service;

import java.util.List;
import jp.co.javapractise.todowatch.entity.api.CreateIN;
import jp.co.javapractise.todowatch.entity.api.CreateOut;

/**
 *
 * @author akari
 */
public interface TodoService {
    // public List find();
    // publi get();
    public void delete();
    public CreateOut create(CreateIN api);
}
