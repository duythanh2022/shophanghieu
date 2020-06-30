/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Service;

import com.mycompany.webshophanghieu.HibernateConnecction.HibernateUtil;
import com.mycompany.webshophanghieu.Pojo.Brand;
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
public class BrandService {
    
    private final static SessionFactory sessionf=HibernateUtil.getSessionFactory();
    
    public List<Brand> getBrands(){
       
        try(Session session=sessionf.openSession()){
            
           CriteriaBuilder builder=session.getCriteriaBuilder();
           CriteriaQuery<Brand> query=builder.createQuery(Brand.class);
           Root<Brand> root=query.from(Brand.class);
           query.select(root);
            return session.createQuery(query).getResultList();
        }
    }
    public boolean addOrSaveBrand(Brand brand){
        try (Session session=sessionf.openSession()){
            try{
                session.getTransaction().begin();
                session.saveOrUpdate(brand);
                session.getTransaction().commit();
            }catch(Exception ex){
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }
    
    public Brand getBrandByID(int brandId){
        try(Session session=sessionf.openSession()){
            return session.get(Brand.class, brandId);
            
        }
    }
}
