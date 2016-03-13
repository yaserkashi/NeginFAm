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
import com.Baloot.Order.Order;
import com.Baloot.Order.OrderServices;
import com.Baloot.User.UserServices;
import com.Baloot.User.Users;
import com.Baloot.util.PersianCalendar;
import com.Baloot.util.SessionBean;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
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
    private Date endDate;
    private UploadedFile attachFile;
    private Boolean delivery;

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

    public void setEndDate(Date endDate) {
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

    public Date getEndDate() {
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

    public Boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public void upload(FileUploadEvent event) {
        attachFile = event.getFile();
        if (attachFile != null) {
            try {
                FacesMessage message = new FacesMessage("Succesful", attachFile.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                save(FilenameUtils.getName(attachFile.getFileName()), attachFile.getInputstream());
            } catch (IOException ex) {
                Logger.getLogger(DesignForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void uploaded() throws Exception {
        if (attachFile != null) {
            try {
                FacesMessage message = new FacesMessage("Succesful", attachFile.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                submit();                
            } catch (IOException ex) {
                Logger.getLogger(DesignForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("eror null file");

        }
    }

    private void save(String filename, InputStream input) {
        try {           
            System.out.println("in save file name is :" + filename);
            String filePath = "\\web\\resources\\downloadfile";
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

        } catch (IOException e) {
            Logger.getLogger(DesignForm.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void submit() throws Exception {
        Design design = new Design();
        Order order = new Order();
        design.setDesignType(designType);
        design.setSize(size);
        if (!optionalSize.equals("")) {
            design.setSize(optionalSize);
        }
        PersianCalendar pc = new PersianCalendar();
        String currentDate = pc.getIranianDateTime();
        design.setRegisterDate(currentDate);
        design.setPrintType(printType);
        design.setDesignOption(designOption);
        design.setPrintOption(printOption);
        if (endDate != null) {
            design.setEndDate(pc.DateToString(pc.getIranianDateFromDate(endDate)));
        } else {
            design.setEndDate("");
        }
        design.setExplain(explian);
        Users user = UserServices.getUserByUsername(SessionBean.getUserName());
        design.setUserId(user);
        if (attachFile != null) {
            design.setAttachFile(attachFile.getFileName());
        }
        design.setDeliveryType(delivery);
        order.setTableName("design");
        order.setCondition(0);
        order.setOrderDate(currentDate);
        order.setUserId(user);
        try {
            int id = DesignServices.insertRecordIntoTable(design);
            order.setTableId(id);
            save("order"+id+FilenameUtils.getName(attachFile.getFileName()), attachFile.getInputstream());
            OrderServices.insertRecordIntoTable(order);
            com.Baloot.util.SendSMS.sendSms(user.getPhoneNum(), "سفارش شما باموفقیت ثبت شد", "false");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("سفارش شما ثبت شد."));
            FacesContext.getCurrentInstance().getExternalContext().redirect("succes.xhtml");
        } catch (SQLException ex) {
            Logger.getLogger(DesignForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
