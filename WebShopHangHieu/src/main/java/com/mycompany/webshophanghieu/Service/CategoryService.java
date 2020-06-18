/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Service;

import com.mycompany.webshophanghieu.HibernateConnecction.HibernateUtil;
import com.mycompany.webshophanghieu.Pojo.Category;
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
public class CategoryService {
    private final static SessionFactory sessionf=HibernateUtil.getSessionFactory();
    
    public List<Category> getCategories(){
       
        try(Session session=sessionf.openSession()){
            
           CriteriaBuilder builder=session.getCriteriaBuilder();
           CriteriaQuery<Category> query=builder.createQuery(Category.class);
           Root<Category> root=query.from(Category.class);
           query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
    public boolean addOrSaveCate(Category cate){
        try (Session session=sessionf.openSession()){
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
    public Category getCateByID(int id){
        try(Session session=sessionf.openSession()){
            return session.get(Category.class, id);
        }
    }
}
