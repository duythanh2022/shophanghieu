/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Bean;

import com.mycompany.webshophanghieu.Pojo.Orders;
import com.mycompany.webshophanghieu.Pojo.OrderDetail;
import com.mycompany.webshophanghieu.Pojo.Product;
import com.mycompany.webshophanghieu.Pojo.User;
import com.mycompany.webshophanghieu.Service.OrderService;
import com.mycompany.webshophanghieu.Service.ProductService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@ManagedBean
@Named(value = "cartBean")
@SessionScoped
public class CartBean implements Serializable {

    /**
     * Creates a new instance of CartBean
     */
    
    public CartBean() {
    }
    
    @PostConstruct
    public void init(){
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart")==null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cart",new HashMap<>());
        }
    }
    public String addItemtoCart(int proid,String proName,long proprice){
        
        Map<Integer,Object>cart= (Map<Integer,Object>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart");
        if (cart.get(proid)==null) {
            Map<String, Object> d=new HashMap<>();
            d.put("proid", proid);
            d.put("proName", proName);
            d.put("proprice", proprice);
            d.put("count", 1);
            cart.put(proid, d);
        }else{
            Map<String, Object> d=(Map<String, Object>) cart.get(proid);
            d.put("count", Integer.parseInt( d.get("count").toString())+1);
            
        }
        return "success";
    }
    public String deleteCart(String idcart){
        Map<Integer,Object>cart= (Map<Integer,Object>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart");
       
        cart.remove(Integer.parseInt(idcart));
        return "success";
    }
    public List<Map<String,Object>> getCarts(){
        Map<Integer,Object>carts= (Map<Integer,Object>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart");
        List<Map<String,Object>> kq=new ArrayList<>();
        for (Object map : carts.values()) {
            Map<String,Object> d=(Map<String,Object>) map;
            kq.add(d);
        }
        return kq;
    }
    public long getSubtotal(){
        Map<Integer,Object>carts= (Map<Integer,Object>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart");
        long total=0;
        for (Object map : carts.values()) {
            Map<String,Object> d=(Map<String,Object>) map;
            total+=(Integer.parseInt(d.get("proprice").toString())*Integer.parseInt(d.get("count").toString()));
        }
        
        return total;
    }
    public String checkLogin1(){
        User user=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user!=null&&getSubtotal()>0) {
            return "CheckOut";
        }
        return "Login?faces-redirect=true";
    }
   
}
