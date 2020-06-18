/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@FacesValidator( "UploadValidator")
public class UploadValidator implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object value) throws ValidatorException {
        Part p=(Part)value;
        //anh png or jpg
        if (!p.getContentType().equals("image/png")&&
            !p.getContentType().equals("image/jpg")) {
            FacesMessage mgs=new FacesMessage("need png or jpg");
            throw new ValidatorException(mgs);
        }
    }
    
}
