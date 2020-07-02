/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Bean;

import com.mycompany.webshophanghieu.Pojo.OrderDetail;
import com.mycompany.webshophanghieu.Pojo.Orders;
import com.mycompany.webshophanghieu.Pojo.Product;
import com.mycompany.webshophanghieu.Pojo.User;
import com.mycompany.webshophanghieu.Service.OrderService;
import com.mycompany.webshophanghieu.Service.ProductService;
import com.mycompany.webshophanghieu.Service.UserService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
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
    private int id;
    private long date;
    private long total;
    private User user;
    private static final OrderService orService=new OrderService();
    /**
     * Creates a new instance of OrderBean
     */
    public OrderBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            String proid=FacesContext.getCurrentInstance().getExternalContext()
                    .getRequestParameterMap().get("order_id");
            if (proid!=null&&proid!="") {
                Orders or=orService.getOrderByID(Integer.parseInt(proid));
                this.id=or.getId();
                this.date=or.getDateod();
                this.total=or.getTotal();
                this.user=or.getUserid();
            }
        }
    }
    
    public List<Orders> getOrders(){
        return orService.getOrders();
    }
    public List<OrderDetail> getOrderDetail(){
        return orService.getOrderDetailByOreder(orService.getOrderByID(this.id));
    }
    public String addOrder(){
        Map<Integer,Object>cart= (Map<Integer,Object>) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("cart");
        
        User user=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        user=new UserService().getUserByID(user.getId());
        Orders od=new Orders();
        od.setUserid(user);
       
        Date dt=new Date();
        Timestamp times=new Timestamp(dt.getTime());
        od.setDateod(times.getTime());
        od.setTotal(this.getSubtotal());
        if (orService.addOrSaveOrder(od)) {
            
            ProductService proS=new ProductService();
            for (Object map : cart.values()) {
                Map<String,Object> d=(Map<String,Object>) map;
                OrderDetail orD=new OrderDetail();
                orD.setOrder(od);
                Product p=proS.getProByID(Integer.parseInt(d.get("proid").toString()));
                orD.setProduct(p);
                orD.setUnitPrice(p.getPrice());
                orD.setAmountDetail(Integer.parseInt(d.get("count").toString()));
                orD.setTotalDetail(Integer.parseInt(d.get("proprice").toString())*Integer.parseInt(d.get("count").toString()));
                orService.addOrSaveDetail(orD);
            }
            removeCart();
        }
        else{
            return "CheckOut";
        }
        return "index";
    }
    public String getdate(long ts){
        Timestamp times=new Timestamp(ts);
        Date d=new Date(times.getTime());
        return String.valueOf(d);
    }
     private long getSubtotal(){
        Map<Integer,Object>carts1= (Map<Integer,Object>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart");
        long total=0;
        for (Object map : carts1.values()) {
            Map<String,Object> d=(Map<String,Object>) map;
            total+=(Integer.parseInt(d.get("proprice").toString())*Integer.parseInt(d.get("count").toString()));
        }
        
        return total;
    }
     private void removeCart(){
        Map<Integer,Object>carts1= (Map<Integer,Object>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cart");
        carts1.clear();
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     
}
