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
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;

/**
 *
 * @author Admin
 */
@ManagedBean
@Named(value = "userBean")
@RequestScoped
public class UserBean {

    
    private String email;
    private String fullname;
    private String phone;
    private String address;
    private String pass;
    @Transient
    private String passConfirm;
            
    private final static UserService uService=new UserService();
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    
    public String register(){
        if (!this.pass.isEmpty()&&this.pass.equals(this.passConfirm)) {
            User user=new User();
            user.setEmail(email);
            user.setPass(pass);
            user.setFullname(fullname);
            user.setAddress(address);
            user.setPhone(phone);
            user.setRoll(1);
            if (uService.adduser(user)) {
                return "Login?faces-redirect=true";
            }
            
        }
        return "Register";
    }
    
    public String login(){
        User user=uService.login(email, pass);
        if (user!=null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
            return "index?faces-redirect=true";
        }
        return "Login";
    }
    public String checkLogin(){
        User user=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user!=null) {
            
            return "index?faces-redirect=true";
        }
        return "Login";
    }
    public String checkLogin1(){
        User user=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user!=null) {
            return "CheckOut";
        }
        return "Login?faces-redirect=true";
    }
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
        return "Login?faces-redirect=true";
    }
    
    public List<User> getUsers(){
        return uService.getUsers();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return fullname;
    }

    public void setName(String name) {
        this.fullname = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassConfirm() {
        return passConfirm;
    }

    public void setPassConfirm(String passConfirm) {
        this.passConfirm = passConfirm;
    }
    
    
}
