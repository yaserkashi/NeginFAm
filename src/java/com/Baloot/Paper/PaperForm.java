/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Paper;

import com.Baloot.Coding.Coding;
import com.Baloot.Coding.CodingServices;
import com.Baloot.Enum.CombosEnum;
import com.Baloot.Enum.OrderTypesEnum;
import com.Baloot.Order.Order;
import com.Baloot.Order.OrderServices;
import com.Baloot.Type.TypeForm;
import com.Baloot.User.UserServices;
import com.Baloot.User.Users;
import com.Baloot.util.PersianCalendar;
import com.Baloot.util.SessionBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author FK
 */
@ManagedBean
public class PaperForm {
    private Integer group;
    private List<Coding> groups = CodingServices.getCodings(OrderTypesEnum.paper.ordinal(), CombosEnum.group.ordinal());
    private Integer field;
    private List<Coding> fields = CodingServices.getCodings(OrderTypesEnum.paper.ordinal(), CombosEnum.field.ordinal());
    private Integer orientation;
    private List<Coding> orientations = CodingServices.getCodings(OrderTypesEnum.paper.ordinal(), CombosEnum.orientation.ordinal());
    private String title;
    private List<Boolean> options;
    private String plan;
    private String explain;
    private Date dateTime;
    private boolean delivery;
    private UploadedFile attachFile;

    public List<Coding> getGroups() {
        return groups;
    }

    public List<Coding> getFields() {
        return fields;
    }
    
    public void setGroup(Integer group) {
        this.group = group;
    }

    public void setField(Integer field) {
        this.field = field;
    }

    public void setTitle(String title) {
        this.title = title;
    }
   
    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public void setAttachFile(UploadedFile attachFile) {
        this.attachFile = attachFile;
    }

    public Integer getGroup() {
        return group;
    }

    public Integer getField() {
        return field;
    }

    public String getTitle() {
        return title;
    }

    public String getExplain() {
        return explain;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public UploadedFile getAttachFile() {
        return attachFile;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public List<Coding> getOrientations() {
        return orientations;
    }

    public List<Boolean> getOptions() {
        return options;
    }

    public void setOptions(List<Boolean> options) {
        this.options = options;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
           
    public void uploaded() throws Exception {
        if (attachFile != null) {
            try {
                FacesMessage message = new FacesMessage("Succesful", attachFile.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                save(FilenameUtils.getName(attachFile.getFileName()), attachFile.getInputstream());
                submit();
            } catch (IOException ex) {
                Logger.getLogger(PaperForm.class.getName()).log(Level.SEVERE, null, ex);
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
                .getRealPath("/resources/downloadFile/");
        try {
            OutputStream output = new FileOutputStream(new File(path, filename));
            IOUtils.copy(input, output);
            
            System.out.println(PaperForm.class.getName() + "Done!");

        } catch (IOException e) {
            System.out.println(PaperForm.class.getName() + e.getMessage());
        } finally {
            IOUtils.closeQuietly(input);
        }
    }
    
    public String getOption(List<Boolean> opt) {
        String opts = "";
        for (Boolean opt1 : opt) {
            if (opt1)
                opts += '1';
            else
                opts += '0';
        }
        return opts;
    }
    
    public void submit() throws Exception {
        System.out.println(PaperForm.class.getName() + ":Submit Function!");
        Paper paper = new Paper();
        Order order = new Order();
        paper.setGroup(group);
        paper.setField(field);
        paper.setOrientationOfField(orientation);
        paper.setTitle(title);
        paper.setExplain(explain);
        PersianCalendar pc = new PersianCalendar();
        String currentDate = pc.getIranianDateTime();
        paper.setDate(currentDate);
        if (dateTime != null)
            paper.setEndDateTime(pc.DateToString(pc.getIranianDateFromDate(dateTime)));
        else
            paper.setEndDateTime("");
        paper.setOption(getOption(options)+plan);
        if(attachFile != null)
            paper.setAttachFile(attachFile.getFileName());
        paper.setDeliveryType(delivery);
        Users user = UserServices.getUserByUsername(SessionBean.getUserName());
        order.setTableName("paper");
        order.setCondition(0);
        order.setOrderDate(currentDate);
        order.setUserId(user);
        try {
            int id = PaperServices.insertRecordIntoTable(paper);
            order.setTableId(id);
            OrderServices.insertRecordIntoTable(order);
               com.Baloot.util.SendSMS.sendSms(user.getPhoneNum(),"سفارش شما باموفقیت ثبت شد","false");
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("سفارش شما ثبت شد."));
        } catch (SQLException ex) {
            Logger.getLogger(TypeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
