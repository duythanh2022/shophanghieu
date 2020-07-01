/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Bean;

import com.mycompany.webshophanghieu.Pojo.Brand;
import com.mycompany.webshophanghieu.Pojo.Product;
import com.mycompany.webshophanghieu.Service.ProductService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@ManagedBean
@Named(value = "productBean")
@RequestScoped
public class ProductBean {

    private final static ProductService proService=new ProductService();
    private int id;
    private String name;
    private String color;
    private Brand brand;
    private long price;
    private int amount;
    private Part image;
    private String imgName;
    
    /**
     * Creates a new instance of ProductBean
     */
    public ProductBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            String proid=FacesContext.getCurrentInstance().getExternalContext()
                    .getRequestParameterMap().get("product_id");
            if (proid!=null&&proid!="") {
                Product pro=proService.getProByID(Integer.parseInt(proid));
                this.id=pro.getId();
                this.name=pro.getName();
                this.color=pro.getColor();
                this.brand=pro.getBrand();
                this.price=pro.getPrice();
                this.amount=pro.getAmount();
                this.imgName=pro.getImage();
            }
        }
    }
    
    public List<Product> getProduct(){
        return proService.getProducts();
    }
    
    public String addOrUpdateProduct(){
//        String proid=FacesContext.getCurrentInstance().getExternalContext()
//                    .getRequestParameterMap().get("product_id");
        Product pro;
        if (this.id>0) {
            pro=proService.getProByID(this.id);
        }
        else{
            pro=new Product();
        }
            pro.setName(this.name);
            pro.setColor(this.color);
            pro.setBrand(this.brand);
            pro.setPrice(this.price);
            pro.setAmount(this.amount);
            try {
                if (this.image!=null) {
                    this.uploadFile();
                    pro.setImage(this.image.getSubmittedFileName());
                }


                if (proService.addOrSavePro(pro)==true) {
                    return "AdminProduct?faces-redirect=true";
                }
            } catch (IOException ex) {
                Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);

            }
        
            return "AdminProductDetail"; 
        
    }
    
    private void uploadFile() throws IOException{
//        String part=FacesContext.getCurrentInstance().getExternalContext()
//                .getRealPath("/resources/image/Upload")+"/"+this.image.getSubmittedFileName();
        String part="C:/Users/Admin/OneDrive/Documents/GitHub/shophanghieu/WebShopHangHieu/src/main/webapp/resources/image/upload/"+this.image.getSubmittedFileName();
        try(InputStream in=this.image.getInputStream();
                OutputStream out=new FileOutputStream(part)){
            byte[] d=new byte[1024];
            int byteread;
            while ((byteread=in.read(d))!=-1) {                
                out.write(d, 0, byteread);
            }
        }
    }
    
    public String deleteProduct(Product pro) throws Exception{
        if (proService.deleteProduct(pro)) {
            return "success";
        }
        throw new Exception("Delete False");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
    

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    

    

  

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    

}
