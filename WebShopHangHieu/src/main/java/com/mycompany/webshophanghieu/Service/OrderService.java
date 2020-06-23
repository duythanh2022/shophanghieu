/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Service;

import com.mycompany.webshophanghieu.HibernateConnecction.HibernateUtil;
import com.mycompany.webshophanghieu.Pojo.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Admin
 */
public class OrderService {
    private final static SessionFactory factory=HibernateUtil.getSessionFactory();
    
    public boolean addOrSaveCate(Order cate){
        try (Session session=factory.openSession()){
            try{
                session.getTransaction().begin();
                session.saveOrUpdate(cate);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }
}
