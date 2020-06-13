/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.HibernateConnecction;

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
        pros.put(Environment.USER, "root");
        pros.put(Environment.PASS, "");
        config.setProperties(pros);
        
        ServiceRegistry regisstry=new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();
        FACTORY=config.buildSessionFactory(regisstry);
    }
    public static SessionFactory getSessionFactory(){
        return FACTORY;
    }
}
