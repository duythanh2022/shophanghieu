/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.HibernateConnecction;

import com.mycompany.webshophanghieu.Pojo.Brand;
import com.mycompany.webshophanghieu.Pojo.Category;
import com.mycompany.webshophanghieu.Pojo.Order;
import com.mycompany.webshophanghieu.Pojo.OrderDetail;
import com.mycompany.webshophanghieu.Pojo.Product;
import com.mycompany.webshophanghieu.Pojo.User;
import com.mycompany.webshophanghieu.Pojo.UserDetail;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Admin
 */
public class HibernateUtil {
    private final static SessionFactory FACTORY;
    static {
        Configuration config=new Configuration();
        Properties pros=new Properties();
        pros.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        pros.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        pros.put(Environment.URL, "jdbc:mysql://localhost:3306/shophanghieu");
        pros.put(Environment.USER, "tuong");
        pros.put(Environment.PASS, "123456");
        config.setProperties(pros);
        
        config.addAnnotatedClass(Brand.class);
        config.addAnnotatedClass(Category.class);
        config.addAnnotatedClass(Order.class);
        config.addAnnotatedClass(OrderDetail.class);
        config.addAnnotatedClass(Product.class);
        config.addAnnotatedClass(User.class);
        config.addAnnotatedClass(UserDetail.class);
        
        ServiceRegistry regisstry=new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();
        FACTORY=config.buildSessionFactory(regisstry);
    }
    public static SessionFactory getSessionFactory(){
        return FACTORY;
    }
}
