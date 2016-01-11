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
import com.Baloot.Type.TypeForm;
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
    private String dateTime;
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

    public void setDateTime(String dateTime) {
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

    public String getDateTime() {
        return dateTime;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public UploadedFile getAttachFile() {
        return attachFile;
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
    
    public void submit() {
        System.out.println("Submit Function!");
        Translate translate = new Translate();
        translate.setLanguage(language);
        translate.setField(field);
        translate.setTitle(title);
        translate.setExplain(explain);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        translate.setDateTime(dateFormat.format(date));
        translate.setEndDateTime(dateTime);
        translate.setOption(getOption(table, subtable, chart, shape));
        translate.setAttachFile(attachFile.getFileName());
        translate.setUserId(UserServices.getUserByUsername(SessionBean.getUserName()));
        try {
            TranslateServices.insertRecordIntoTable(translate);
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("درسته ......"));
        } catch (SQLException ex) {
            Logger.getLogger(TypeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
