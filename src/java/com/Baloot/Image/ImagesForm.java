/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template attachFile, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Image;

/**
 *
 * @author Ali-M
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
 
@ManagedBean
public class ImagesForm {
     
    private List<Image> images = ImageServices.getALLImages();
    private List<Image> selectedImages = ImageServices.getSelectedImages();
    private List<Image> selectedList;
    private UploadedFile attachFile;
 
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

    public UploadedFile getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(UploadedFile file) {
        this.attachFile = file;
    }
    
    public void uploaded() throws Exception {
        if (attachFile != null) {
            try {
                FacesMessage message = new FacesMessage("Succesful", attachFile.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                save(FilenameUtils.getName(attachFile.getFileName()), attachFile.getInputstream());
            } catch (IOException ex) {
                Logger.getLogger(ImagesForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            System.out.println("eror null file");
        
        }
    }
    
    private static void save(String filename, InputStream input) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest httpServletRequest = (HttpServletRequest) context
                .getExternalContext().getRequest();
        String path = httpServletRequest.getSession().getServletContext()
                .getRealPath("/resources/");
        try {
            OutputStream output = new FileOutputStream(new File(path, filename));
            IOUtils.copy(input, output);
            
            System.out.println(ImagesForm.class.getName() + ":Done!");

        } catch (IOException e) {
            System.out.println(ImagesForm.class.getName() + e.getMessage());
        } finally {
            IOUtils.closeQuietly(input);
        }
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