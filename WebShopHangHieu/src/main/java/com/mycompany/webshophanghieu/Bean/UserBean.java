/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Bean;

import com.mycompany.webshophanghieu.Pojo.User;
import com.mycompany.webshophanghieu.Service.UserService;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Admin
 */
@ManagedBean
@Named(value = "userBean")
@Dependent
public class UserBean {

    private final static UserService uService=new UserService();
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    public List<User> getUsers(){
        return uService.getUsers();
    }
    
    
}
