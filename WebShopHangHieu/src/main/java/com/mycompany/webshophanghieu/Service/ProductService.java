/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Service;

import com.mycompany.webshophanghieu.HibernateConnecction.HibernateUtil;
import com.mycompany.webshophanghieu.Pojo.Brand;
import com.mycompany.webshophanghieu.Pojo.Product;
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
public class ProductService {
    private final static SessionFactory sessionf=HibernateUtil.getSessionFactory();
    
    public List<Product> getProducts(String kw){
//        kw=String.format("*/ %s kw);
        try(Session session=sessionf.openSession()){
            CriteriaBuilder builder=session.getCriteriaBuilder();
            CriteriaQuery<Product> query=builder.createQuery(Product.class);
            Root<Product> root=query.from(Product.class);
            query.select(root);
            if (kw  != null &&  !kw.isEmpty()) {
                String p=String.format("%%%s%%", kw);
                query=query.where(builder.or(builder.like(root.get("name").as(String.class), p),
                        builder.like(root.get("color").as(String.class), p)));
            }
//            (root.get("name").as(String.class), kw)
            return session.createQuery(query).getResultList();
        }
    }
    public List<Product> getProducts(long begin,long end){
//        kw=String.format("*/ %s kw);
        try(Session session=sessionf.openSession()){
            CriteriaBuilder builder=session.getCriteriaBuilder();
            CriteriaQuery<Product> query=builder.createQuery(Product.class);
            Root<Product> root=query.from(Product.class);
            query.select(root);
            
              
            query=query.where(builder.and(builder.lessThan(root.get("price").as(Long.class), end),
                    builder.greaterThan(root.get("price").as(Long.class), begin)));
           
//            (root.get("name").as(String.class), kw)
            return session.createQuery(query).getResultList();
        }
    }
    public List<Product> getProducts(Brand brandid){
//        kw=String.format("*/ %s kw);
        try(Session session=sessionf.openSession()){
            String hql="FROM Product P Where P.brand.id="+brandid.getId();
            Query query=session.createQuery(hql);
            return query.getResultList();
        }
    }
    public boolean addOrSaveCate(Product pro){
        try (Session session=sessionf.openSession()){
            try{
                session.getTransaction().begin();
                session.saveOrUpdate(pro);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }
    public Product getProByID(int id){
        try(Session session=sessionf.openSession()){
            return session.get(Product.class, id);
        }
    }
    
    public boolean deleteProduct(Product pro){
        try(Session session=sessionf.openSession()){
           try{
                session.getTransaction().begin();
                session.delete(pro);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }

    public List<Product> getProducts() {
        try(Session session=sessionf.openSession()){
            CriteriaBuilder builder=session.getCriteriaBuilder();
            CriteriaQuery<Product> query=builder.createQuery(Product.class);
            Root<Product> root=query.from(Product.class);
            query.select(root);
            
            return session.createQuery(query).getResultList();
        }
    }
    
}
