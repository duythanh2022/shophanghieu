/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Service;

import com.mycompany.webshophanghieu.HibernateConnecction.HibernateUtil;
import com.mycompany.webshophanghieu.Pojo.Order;
import com.mycompany.webshophanghieu.Pojo.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Admin
 */
public class OrderService {
    
    private final SessionFactory seFactory=HibernateUtil.getSessionFactory();
    
    
    public boolean addOrSaveOrder(Order o){
        try (Session session=seFactory.openSession()){
            try{
                session.getTransaction().begin();
                session.saveOrUpdate(o);
                session.getTransaction().commit();
            }catch(Exception ex){
                System.err.println(ex);
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
}
