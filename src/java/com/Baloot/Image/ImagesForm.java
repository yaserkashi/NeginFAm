/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Image;

/**
 *
 * @author Ali-M
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
@ManagedBean
public class ImagesForm {
     
    private List<Image> images = ImageServices.getALLImages();
    private List<Image> selectedImages = ImageServices.getSelectedImages();
    private List<Image> selectedList;
 
    public List<Image> getImages() {
        return images;
    }
    
    public List<Image> getSelectedImages() {
        return selectedImages;
    }

    public List<Image> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<Image> selectedList) {
        this.selectedList = selectedList;
    }
        
    public void submit() {
        System.out.println(ImagesForm.class.getName() + " : Submit Function!");
        try {
            ImageServices.updateSelected(selectedList);
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("تغییرات ذخیره شد."));
        } catch (SQLException ex) {
            Logger.getLogger(ImagesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}