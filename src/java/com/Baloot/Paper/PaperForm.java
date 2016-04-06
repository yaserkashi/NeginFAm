/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Paper;

import com.Baloot.Coding.Coding;
import com.Baloot.Coding.CodingServices;
import com.Baloot.Design.DesignForm;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
public class PaperForm {

    private Integer group;
    private List<Coding> groups = CodingServices.getCodings(OrderTypesEnum.paper.ordinal(), CombosEnum.group.ordinal());
    private Integer field;
    private List<Coding> fields = CodingServices.getCodings(OrderTypesEnum.paper.ordinal(), CombosEnum.field.ordinal());
    private Integer orientation;
    private List<Coding> orientations = CodingServices.getCodings(OrderTypesEnum.paper.ordinal(), CombosEnum.orientation.ordinal());
    private String title;
    private String plan;
    private String explain;
    private Date dateTime;
    private boolean delivery;
    private UploadedFile attachFile;
    private List<SelectItem> options;
    private String[] selectedOptions;

    @PostConstruct
    public void init() {
        options = new ArrayList<>();
        options.add(new SelectItem(
                "1"," ادیت علمی، نگارشی و مفهومی مقالات فارسی به شیوه نامه مجلات داخلی"));
        options.add(new SelectItem("2"," ساب میت و گرفتن اکسپت مقاله فارسی در مجلات داخلی"
                ));
        options.add(new SelectItem("3"," ترجمه فارسی به انگلیسی و ادیت علمی، گرامری، نگارشی و مفهومی متن انگلیسی مقاله "
                ));
        options.add(new SelectItem("4","ساب میت و گرفتن پذیرش مقاله انگلیسی از ژورنال های بین المللی"
                ));
        options.add(new SelectItem("5"," استخراج مقاله فارسی از پایان نامه کارشناسی ارشد یا دکتری"
                ));
        options.add(new SelectItem( "6"," استخراج مقاله انگلیسی از پایان نامه کارشناسی ارشد یا دکتری "
               ));
        options.add(new SelectItem("7"," نگارش مقاله بدون دریافت اطلاعات اولیه"
                ));
    }

    public List<SelectItem> getOptions() {
        return options;
    }

    public void setOptions(List<SelectItem> options) {
        this.options = options;
    }

    public String[] getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(String[] selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

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

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void uploaded() throws Exception {
        if (attachFile != null) {
            FacesMessage message = new FacesMessage("Succesful", attachFile.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            System.out.println(PaperForm.class.getName() + ": eror null file");

        }
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

    public void submit()  {
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
        if (dateTime != null) {
            paper.setEndDateTime(pc.DateToString(pc.getIranianDateFromDate(dateTime)));
        } else {
            paper.setEndDateTime("");
        }
        paper.setOption(Arrays.toString(selectedOptions) + "," + plan);
        //------------------------------------------
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        attachFile = (UploadedFile) session.getAttribute("attachFile");
        if (attachFile != null) {
            paper.setAttachFile(FilenameUtils.getName(attachFile.getFileName()));
        }
        //----------------------------
        paper.setDeliveryType(delivery);
        Users user = UserServices.getUserByUsername(SessionBean.getUserName());
        order.setTableName("paper");
        order.setCondition(0);
        order.setOrderDate(currentDate);
        order.setUserId(user);
        try {
            int id = PaperServices.insertRecordIntoTable(paper);
            order.setTableId(id);
            System.out.println("heree ");
            //----------------------
            try {
                save("paper" + id + FilenameUtils.getName(attachFile.getFileName()), attachFile.getInputstream());
            } catch (IOException ex) {
                Logger.getLogger(PaperForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            OrderServices.insertRecordIntoTable(order);
//            com.Baloot.util.SendSMS.sendSms(user.getPhoneNum(), "سفارش شما باموفقیت ثبت شد", "false");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("سفارش شما ثبت شد."));
        } catch (SQLException ex) {
            Logger.getLogger(TypeForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
