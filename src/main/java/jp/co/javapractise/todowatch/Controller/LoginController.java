/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.javapractise.todowatch.Controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jp.co.javapractise.todowatch.config.MongoDBConfig;
import jp.co.javapractise.todowatch.entity.api.LoginUser;
import jp.co.javapractise.todowatch.entity.dao.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author 灯
 */
@Controller
@RequestMapping(value = "/")
public class LoginController {
       ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDBConfig.class);
    MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);
    	
    @Autowired
    private HttpServletRequest request;
    /**
     *
     * @return
     */
    @RequestMapping(value="stlogin",method=RequestMethod.POST)
    @ResponseBody()
    public Integer staticLogin(@RequestBody LoginUser user) throws TodoWatchException {
        if (user.getUser() == null || user.getPasswd() == null){
            throw new TodoWatchException(null, 403);
        }
        request.getSession().setAttribute("id", "name");
        return 1;
    }

}
