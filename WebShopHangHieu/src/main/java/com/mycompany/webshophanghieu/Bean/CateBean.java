/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Bean;

import com.mycompany.webshophanghieu.Pojo.Brand;
import com.mycompany.webshophanghieu.Pojo.Category;
import com.mycompany.webshophanghieu.Service.CategoryService;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Admin
 */
@ManagedBean
@Named(value = "cateBean")
@RequestScoped
public class CateBean {

    private String name;
    private Brand brand;
    private final static CategoryService cateService=new CategoryService();
    /**
     * Creates a new instance of CateBean
     */
    public CateBean() {
    }
    public List<Category> getCategory(){
        return cateService.getCategories();
    }
    public String addCategory(){
        Category cate=new Category();
        if (this.name!=null&&this.name!="") {
            
            cate.setName(this.name);
        }
        cate.setBrand(brand);
        if (cateService.addOrSaveCate(cate)==true) {
            return "AdminCate";
        }
        return "AdminCate";
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
