/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Service;

import com.mycompany.webshophanghieu.HibernateConnecction.HibernateUtil;
import com.mycompany.webshophanghieu.Pojo.Orders;
import com.mycompany.webshophanghieu.Pojo.OrderDetail;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Admin
 */
public class OrderService {
    
    private final SessionFactory seFactory=HibernateUtil.getSessionFactory();

    public OrderService() {
        
    }
    public List<Orders> getOrders(){
       
        try(Session session=seFactory.openSession()){
            
           CriteriaBuilder builder=session.getCriteriaBuilder();
           CriteriaQuery<Orders> query=builder.createQuery(Orders.class);
           Root<Orders> root=query.from(Orders.class);
           query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
    public List<OrderDetail> getOrderDetailByOreder(Orders orders){
//        kw=String.format("*/ %s kw);
        try(Session session=seFactory.openSession()){
            String hql="FROM OrderDetail od Where od.order.id="+orders.getId();
            Query query=session.createQuery(hql);
            return query.getResultList();
        }
    }
    public boolean addOrSaveOrder(Orders or){
        try (Session session=seFactory.openSession()){
            try{
                session.getTransaction().begin();
                session.saveOrUpdate(or);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }
    public boolean addOrSaveDetail(OrderDetail orderD){
        try (Session session=seFactory.openSession()){
            try{
                session.getTransaction().begin();
                session.saveOrUpdate(orderD);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }
    public Orders getOrderByID(int orId){
        try(Session session=seFactory.openSession()){
            return session.get(Orders.class, orId);
            
        }
    }
}
