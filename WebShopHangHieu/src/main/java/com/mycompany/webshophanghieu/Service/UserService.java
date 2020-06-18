/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Service;

import com.mycompany.webshophanghieu.HibernateConnecction.HibernateUtil;
import com.mycompany.webshophanghieu.Pojo.User;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Admin
 */
public class UserService {
    private final static SessionFactory factory=HibernateUtil.getSessionFactory();
    
    public List<User> getUsers(){
        try(Session session=factory.openSession()){
            CriteriaBuilder builder=session.getCriteriaBuilder();
            CriteriaQuery<User> query=builder.createQuery(User.class);
            Root<User> root=query.from(User.class);
            query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
    public boolean addOrUpdateProduct(User user){
        try(Session session=factory.openSession()){
            try{
                session.getTransaction().begin();
                session.saveOrUpdate(user);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }
    public User getUserByID(int id){
        try(Session session=factory.openSession()){
            return session.get(User.class, id);
        }
    }
}
