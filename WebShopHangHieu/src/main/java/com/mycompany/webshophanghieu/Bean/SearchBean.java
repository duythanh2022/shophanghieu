/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Bean;

import com.mycompany.webshophanghieu.Pojo.Brand;
import com.mycompany.webshophanghieu.Pojo.Product;
import com.mycompany.webshophanghieu.Service.BrandService;
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
    private long begin;
    private long end;
    private List<Product> pros;
//    private Brand brand;
    private final static ProductService proService=new ProductService();
    private final static BrandService brandService=new BrandService();
    /**
     * Creates a new instance of SearchBean
     */
    public SearchBean() {
    }
    public String proByKw(){
       
        if (kw!=null) { 
             pros=proService.getProducts(kw);
        }
        
        return "true";//Products.xhtml?faces-redirect=
    }
    public String proByPri(){
       
        if (begin<end && begin>=0) { 
             pros=proService.getProducts(begin,end);
        }
        
        return "sucss";//Products.xhtml?faces-redirect=
    }
    
    public String proByBrand(String id){
         
//        String id=FacesContext.getCurrentInstance().getExternalContext()
//                    .getRequestParameterMap().get("braID");
        Brand brandID=brandService.getBrandByID(Integer.parseInt(id));
  
        if(brandID!=null) { 
            pros=proService.getProducts(brandID);
        }
        return "sucss";
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public List<Product> getPros() {
        return pros;
    }

    public void setPros(List<Product> pros) {
        this.pros = pros;
    }

    public long getBegin() {
        return begin;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    

    
    
}
