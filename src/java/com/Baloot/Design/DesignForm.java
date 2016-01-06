/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Design;

import com.Baloot.Coding.Coding;
import com.Baloot.Coding.CodingServices;
import com.Baloot.Enum.CombosEnum;
import com.Baloot.Enum.OrderTypesEnum;
import com.Baloot.User.UserServices;
import com.Baloot.util.SessionBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author FK
 */
@ManagedBean
public class DesignForm {
    private Integer designType;
    private List<Coding> designTypes = CodingServices.getCodings(OrderTypesEnum.design.ordinal(), CombosEnum.design_type.ordinal());
    private String size;
    private List<Coding> sizes = CodingServices.getCodings(OrderTypesEnum.design.ordinal(), CombosEnum.size.ordinal());
    private String optionalSize;
    private String printType;
    private List<Coding> printTypes = CodingServices.getCodings(OrderTypesEnum.design.ordinal(), CombosEnum.print_type.ordinal());
    private String printOption;
    private List<Coding> printOptions = CodingServices.getCodings(OrderTypesEnum.design.ordinal(), CombosEnum.print_option.ordinal());
    private String designOption;
    private List<Coding> designOptions = CodingServices.getCodings(OrderTypesEnum.design.ordinal(), CombosEnum.design_option.ordinal());
    private String explian;
    private String endDate;
    private UploadedFile attachFile;

    public List<Coding> getDesignTypes() {
        return designTypes;
    }

    public List<Coding> getSizes() {
        return sizes;
    }

    public List<Coding> getPrintTypes() {
        return printTypes;
    }

    public List<Coding> getPrintOptions() {
        return printOptions;
    }

    public List<Coding> getDesignOptions() {
        return designOptions;
    }
      
    public void setDesignType(Integer designType) {
        this.designType = designType;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setOptionalSize(String optionalSize) {
        this.optionalSize = optionalSize;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public void setPrintOption(String printOption) {
        this.printOption = printOption;
    }

    public void setExplian(String explian) {
        this.explian = explian;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public Integer getDesignType() {
        return designType;
    }

    public String getSize() {
        return size;
    }

    public String getOptionalSize() {
        return optionalSize;
    }

    public String getPrintType() {
        return printType;
    }

    public String getPrintOption() {
        return printOption;
    }

    public String getExplian() {
        return explian;
    }

    public String getEndDate() {
        return endDate;
    }
    
    public String getDesignOption() {
        return designOption;
    }

    public void setDesignOption(String designOption) {
        this.designOption = designOption;
    }

    public UploadedFile getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(UploadedFile attachFile) {
        this.attachFile = attachFile;
    }
    
    public void uploaded(FileUploadEvent event) {
        System.out.println("ghghghh");
        try {
            attachFile = event.getFile();
            save(FilenameUtils.getName(attachFile.getFileName()), attachFile.getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void save(String filename, InputStream input) {
        System.out.println(filename);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest httpServletRequest = (HttpServletRequest) context
                .getExternalContext().getRequest();
        String path = httpServletRequest.getSession().getServletContext()
                .getRealPath("/resources/downloadFile/");
        System.out.println(path);
        try {
            OutputStream output = new FileOutputStream(new File(path, filename));
            IOUtils.copy(input, output);
//            int read;
//            byte[] bytes = new byte[1024];
//
//            while ((read = input.read(bytes)) != -1) {
//                    output.write(bytes, 0, read);
//            }
            
            System.out.println("Done!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(input);
        }
    }
    
    public void submit() {
        System.out.println("SUBMIT FUNCTION!");
        Design design = new Design();
        design.setDesignType(designType);
        design.setSize(size);
        if (!optionalSize.equals(""))
            design.setSize(optionalSize);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        design.setRegisterDate(dateFormat.format(date));
        design.setPrintType(printType);
        design.setDesingOption(designOption);
        design.setPrintOption(printOption);
        design.setEndDate(endDate);
        design.setExplain(explian);
        design.setUserId(UserServices.getUserByUsername(SessionBean.getUserName()));
        try {
            DesignServices.insertRecordIntoTable(design);
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("درسته ......"));
        } catch (SQLException ex) {
            Logger.getLogger(DesignForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
