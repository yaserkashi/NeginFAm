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
import com.Baloot.Design.DesignForm;
import com.Baloot.util.SessionBean;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class ImagesForm {

    private List<Image> images = ImageServices.getALLImages();
    private List<Image> selectedImages = ImageServices.getSelectedImages();
//    private List<Image> selectedList;
    private UploadedFile attachFile;

    public List<Image> getImages() {
        return images;
    }

    public List<Image> getSelectedImages() {
        return selectedImages;
    }
//
//    public List<Image> getSelectedList() {
//        return selectedList;
//    }
//
//    public void setSelectedList(List<Image> selectedList) {
//        this.selectedList = selectedList;
//    }

    public UploadedFile getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(UploadedFile attachFile) {
        this.attachFile = attachFile;
    }
    
    public void upload(FileUploadEvent event) {
        attachFile = event.getFile();
        HttpSession session = SessionBean.getSession();
        session.setAttribute("attachFile", attachFile);
        if (attachFile != null) {
            try {
                FacesMessage message = new FacesMessage("Done..", attachFile.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                save(FilenameUtils.getName(attachFile.getFileName()), attachFile.getInputstream());
                Image img = new Image();
                img.setAddress(attachFile.getFileName());
                img.setSelected(false);
                ImageServices.insertRecordIntoTable(img);
            } catch (Exception ex) {
                Logger.getLogger(DesignForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
//
//    public void uploaded() throws Exception {
//        if (attachFile != null) {
//            try {
//                FacesMessage message = new FacesMessage("عکس با موفقیت آپلود شد.", attachFile.getFileName() + " is uploaded.");
//                FacesContext.getCurrentInstance().addMessage(null, message);
//                save(FilenameUtils.getName(attachFile.getFileName()), attachFile.getInputstream());
//                Image img = new Image();
//                img.setAddress(attachFile.getFileName());
//                img.setSelected(false);
//                ImageServices.insertRecordIntoTable(img);
//            } catch (IOException ex) {
//                Logger.getLogger(ImagesForm.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            System.out.println("eror null file");
//
//        }
//    }

    private void save(String filename, InputStream input) {
        try {
            String filePath = "\\web\\resources\\images\\slider";
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest httpServletRequest = (HttpServletRequest) context
                    .getExternalContext().getRequest();
            String stringPath = httpServletRequest.getSession().getServletContext()
                    .getRealPath("/");
            Path path = Paths.get(stringPath);
            filePath = path.getParent().getParent().toString() + filePath;
            if (!Files.exists(Paths.get(filePath))) {
                Files.createDirectories(Paths.get(filePath));
            }
            File finalFile = new File(filePath, filename);
            Files.copy(input, finalFile.toPath());
            System.out.println(ImagesForm.class.getName() + ":Done!");
        } catch (IOException e) {
            Logger.getLogger(ImagesForm.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void submit() {
        System.out.println(ImagesForm.class.getName() + " : Submit Function!");
        try {        
        ImageServices.updateSelected(images);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("تغییرات ذخیره شد."));
        } catch (SQLException ex) {
            Logger.getLogger(ImagesForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
