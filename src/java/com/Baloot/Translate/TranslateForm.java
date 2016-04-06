/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Translate;

import com.Baloot.Coding.Coding;
import com.Baloot.Coding.CodingServices;
import com.Baloot.Enum.CombosEnum;
import com.Baloot.Enum.OrderTypesEnum;
import com.Baloot.Order.Order;
import com.Baloot.Order.OrderServices;
import com.Baloot.Paper.PaperForm;
import com.Baloot.Type.TypeForm;
import com.Baloot.User.Users;
import com.Baloot.User.UserServices;
import com.Baloot.util.PersianCalendar;
import com.Baloot.util.SessionBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author FK
 */
@ManagedBean
public class TranslateForm {
    private Integer language;
    private List<Coding> languges = CodingServices.getCodings(OrderTypesEnum.translate.ordinal(), CombosEnum.language.ordinal());
    private Integer field;
    private List<Coding> fields = CodingServices.getCodings(OrderTypesEnum.translate.ordinal(), CombosEnum.field.ordinal());
    private String title;
    private boolean table;
    private boolean subtable;
    private boolean chart;
    private boolean shape;
    private String explain;
    private Date dateTime;
    private boolean delivery;
    private UploadedFile attachFile;

    public List<Coding> getLanguges() {
        return languges;
    }

    public List<Coding> getFields() {
        return fields;
    }
    
    public void setLanguage(Integer language) {
        this.language = language;
    }

    public void setField(Integer field) {
        this.field = field;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTable(boolean table) {
        this.table = table;
    }
    
    public void setSubtable(boolean subtable) {
        this.subtable = subtable;
    }

    public void setChart(boolean chart) {
        this.chart = chart;
    }

    public void setShape(boolean shape) {
        this.shape = shape;
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

    public Integer getLanguage() {
        return language;
    }

    public Integer getField() {
        return field;
    }

    public String getTitle() {
        return title;
    }

    public boolean isTable() {
        return table;
    }
    
    public boolean isSubtable() {
        return subtable;
    }

    public boolean isChart() {
        return chart;
    }

    public boolean isShape() {
        return shape;
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
    
   public void upload(FileUploadEvent event) {
        attachFile = event.getFile();
        HttpSession session = SessionBean.getSession();
        session.setAttribute("attachFile", attachFile);
        if (attachFile != null) {
            try {
                FacesMessage message = new FacesMessage("Succesful", attachFile.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                //submit();
            } catch (Exception ex) {
                Logger.getLogger(PaperForm.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            Logger.getLogger(PaperForm.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public String getOption(boolean t, boolean st, boolean c, boolean s) {
        String options = "";
        if (t)
            options += '1';
        else
            options += '0';
        if (st)
            options += '1';
        else
            options += '0';
        if (c)
            options += '1';
        else
            options += '0';
        if (s)
            options += '1';
        else
            options += '0';
        return options;
    }
    
    public void submit()  {
        System.out.println(TranslateForm.class.getName()+":Submit Function!");
        Translate translate = new Translate();
        Order order = new Order();
        translate.setLanguage(language);
        translate.setField(field);
        translate.setTitle(title);
        translate.setExplain(explain);
        PersianCalendar pc = new PersianCalendar();
        String currentDate = pc.getIranianDateTime();
        translate.setDateTime(currentDate);
        if (dateTime != null)
            translate.setEndDateTime(pc.DateToString(pc.getIranianDateFromDate(dateTime)));
        else
            translate.setEndDateTime("");
        translate.setOption(getOption(table, subtable, chart, shape));
         //------------------------------------------
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        attachFile = (UploadedFile) session.getAttribute("attachFile");
        if (attachFile != null) {
            translate.setAttachFile(FilenameUtils.getName(attachFile.getFileName()));
        }
        Users user = UserServices.getUserByUsername(SessionBean.getUserName());
        translate.setUserId(user);
        translate.setDeliveryType(delivery);
        
        order.setTableName("translate");
        order.setCondition(0);
        order.setOrderDate(currentDate);
        order.setUserId(user);
        try {
            int id = TranslateServices.insertRecordIntoTable(translate);
            order.setTableId(id);
            OrderServices.insertRecordIntoTable(order);
              try {
                save("trans" + id + FilenameUtils.getName(attachFile.getFileName()), attachFile.getInputstream());
            } catch (IOException ex) {
                Logger.getLogger(PaperForm.class.getName()).log(Level.SEVERE, null, ex);
            }
//               com.Baloot.util.SendSMS.sendSms(user.getPhoneNum(),"سفارش شما باموفقیت ثبت شد","false");
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("سفارش شما ثبت شد."));
        } catch (SQLException ex) {
            Logger.getLogger(TypeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
