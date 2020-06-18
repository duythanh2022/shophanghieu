/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Converter;

import com.mycompany.webshophanghieu.Service.BrandService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Admin
 */
@FacesConverter("BrandConverter")
public class BrandConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        return new BrandService().getBrandByID(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        return value.toString();
    }
    
}
