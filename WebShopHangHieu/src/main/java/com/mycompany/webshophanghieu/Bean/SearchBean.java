/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Bean;

import com.mycompany.webshophanghieu.Pojo.Brand;
import com.mycompany.webshophanghieu.Pojo.Product;
import com.mycompany.webshophanghieu.Service.ProductService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@ManagedBean
@Named(value = "searchBean")
@RequestScoped
public class SearchBean {

    private String kw;
//    private Brand brand;
    private final static ProductService proService=new ProductService();
    /**
     * Creates a new instance of SearchBean
     */
    public SearchBean() {
    }
    public String proByKw(){
        List<Product> pros=new ArrayList<>();
        pros=proService.getProducts(kw);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("search", pros);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("searchkw", kw);
        return "Products.xhtml?faces-redirect=true";
    }
    public String deleteProSearch(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("search");
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("searchkw");
         return "Products.xhtml?faces-redirect=true";
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }
    
}
