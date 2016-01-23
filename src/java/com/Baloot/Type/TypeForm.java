/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Type;

import com.Baloot.Coding.Coding;
import com.Baloot.Coding.CodingServices;
import com.Baloot.Enum.CombosEnum;
import com.Baloot.Enum.OrderTypesEnum;
import com.Baloot.Order.Order;
import com.Baloot.Order.OrderServices;
import com.Baloot.User.Users;
import com.Baloot.User.UserServices;
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
public class TypeForm {
    private Integer language;
    private List<Coding> languges = CodingServices.getCodings(OrderTypesEnum.type.ordinal(), CombosEnum.language.ordinal());
    private Integer field;
    private List<Coding> fields = CodingServices.getCodings(OrderTypesEnum.type.ordinal(), CombosEnum.field.ordinal());
    private String title;
    private boolean formulation;
    private boolean layout;
    private boolean illustrations;
    private boolean table;
    private boolean charts;
    private boolean shape;
    private boolean editorial;
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

    public void setFormulation(boolean formulation) {
        this.formulation = formulation;
    }

    public void setLayout(boolean layout) {
        this.layout = layout;
    }

    public void setIllustrations(boolean illustrations) {
        this.illustrations = illustrations;
    }

    public void setTable(boolean table) {
        this.table = table;
    }

    public void setCharts(boolean charts) {
        this.charts = charts;
    }

    public void setShape(boolean shape) {
        this.shape = shape;
    }

    public void setEditorial(boolean editorial) {
        this.editorial = editorial;
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

    public boolean isFormulation() {
        return formulation;
    }

    public boolean isLayout() {
        return layout;
    }

    public boolean isIllustrations() {
        return illustrations;
    }

    public boolean isTable() {
        return table;
    }

    public boolean isCharts() {
        return charts;
    }

    public boolean isShape() {
        return shape;
    }

    public boolean isEditorial() {
        return editorial;
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
    
    public String getOption(boolean f,boolean l,boolean i, boolean t, boolean c, boolean s, boolean e) {
        String options = "";
        if (f)
            options += '1';
        else
            options += '0';
        if (l)
            options += '1';
        else
            options += '0';
        if (i)
            options += '1';
        else
            options += '0';
        if (t)
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
        if (e)
            options += '1';
        else
            options += '0';
        return options;
    }
    
    public void uploaded(FileUploadEvent event) {
        System.out.println("Uploaded Function!");
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
            
            System.out.println("Done!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(input);
        }
    }
    
    public void submit() {
        System.out.println("Submit Function!");
        Type type = new Type();
        Order order = new Order();
        type.setLanguage(language);
        type.setField(field);
        type.setTitle(title);
        type.setExplain(explain);
        PersianCalendar pc = new PersianCalendar();
        String currentDate = pc.getIranianDateTime();
        type.setDateTime(pc.DateToString(pc.getIranianDateFromDate(dateTime)));
        type.setOption(getOption(formulation,layout,illustrations,table, charts, shape, editorial));
        Users user = UserServices.getUserByUsername(SessionBean.getUserName());
        type.setUserId(user);
        if(attachFile != null)
            type.setAttachFile(attachFile.getFileName());
        type.setDeliveryType(delivery);
 
        order.setTableName("type");
        order.setCondition(0);
        
        order.setOrderDate(currentDate);
        order.setUserId(user);
        try {
            int id = TypeServices.insertRecordIntoTable(type);
            order.setTableId(id);
            OrderServices.insertRecordIntoTable(order);
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("درسته ......"));
        } catch (SQLException ex) {
            Logger.getLogger(TypeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
