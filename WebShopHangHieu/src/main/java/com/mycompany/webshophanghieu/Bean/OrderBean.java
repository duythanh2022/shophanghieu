/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Bean;

import com.mycompany.webshophanghieu.Pojo.Order;
import com.mycompany.webshophanghieu.Pojo.OrderDetail;
import com.mycompany.webshophanghieu.Pojo.Product;
import com.mycompany.webshophanghieu.Pojo.User;
import com.mycompany.webshophanghieu.Service.OrderService;
import com.mycompany.webshophanghieu.Service.ProductService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@ManagedBean
@Named(value = "orderBean")
@RequestScoped
public class OrderBean {

    private static final OrderService ordServicce=new OrderService();
    /**
     * Creates a new instance of OrderBean
     */
    public OrderBean() {
    }
     public String addOrder(){
        Map<Integer,Object>cart= (Map<Integer,Object>) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("cart");
        
        User user=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        
        Order o=new Order();
        o.setUser(user);
        o.setTotal(this.getSubtotal());
        Date date=new Date();
        Timestamp times=new Timestamp(date.getTime());
//        o.setDate(times.getTime());
        o.setDate(0);
        if (ordServicce.addOrSaveOrder(o)) {
            
            ProductService proS=new ProductService();
            for (Object map : cart.values()) {
                Map<String,Object> d=(Map<String,Object>) map;
                OrderDetail oD=new OrderDetail();
                oD.setOrder(o);
                Product p=proS.getProByID(Integer.parseInt(d.get("proid").toString()));
                oD.setProduct(p);
                oD.setUnitPrice(p.getPrice());
                oD.setAmountDetail(Integer.parseInt(d.get("count").toString()));
                oD.setTotalDetail(Integer.parseInt(d.get("proprice").toString())*Integer.parseInt(d.get("count").toString()));
                ordServicce.addOrSaveDetail(oD);
            }
        }
        else{
            return "CheckOut";
        }
        return "index";
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
}
