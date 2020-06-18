/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="category", schema = "shophanghieu")
public class Category implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    private String name;

    @ManyToOne(fetch =FetchType.EAGER )
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        Category cate=(Category)obj;
        return this.id==cate.getId();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
        return hash;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    
}
