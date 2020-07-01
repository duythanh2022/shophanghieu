/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Bean;

import com.mycompany.webshophanghieu.Pojo.Brand;
import com.mycompany.webshophanghieu.Service.BrandService;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Admin
 */
@ManagedBean
@Named(value = "brandBean")
@RequestScoped
public class BrandBean {
    
    private String nameBr;

    

    private final static BrandService brandService=new BrandService();
    
    public BrandBean() {
    }
    public List<Brand> getBrand(){
        return brandService.getBrands();
    }
    public String addBrand(){
        Brand brand=new Brand();
        brand.setName(this.nameBr);
        
        if (brandService.addOrSaveBrand(brand)==true) {
            return "AdminBrand";
        }
        return "AdminBrand";
    }

    public String deleteBrand(Brand br) throws Exception{
        if (brandService.deleteBrand(br)) {
            return "success";
        }
        throw new Exception("Delete False");
    }
    public String getNameBr() {
        return nameBr;
    }

    public void setNameBr(String nameBr) {
        this.nameBr = nameBr;
    }
    
   

    
}
