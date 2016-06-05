/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Employment;

import com.Baloot.Image.ImagesForm;
import com.Baloot.util.SessionBean;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Digits;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.validator.constraints.Email;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author FK
 */
@ManagedBean
public class EmploymentForm {

    private String name;
    private String lastName;
    private String sex;
    @Email(message = "پست الکترونیکی باید معتبر باشد.")
    private String email;
    @Digits(integer = 11, fraction = 0, message = "موبایل باید یازده رقم باشد.")
    private String mobile;
    @Digits(integer = 11, fraction = 0, message = "تلفن باید یازده رقم باشد.")
    private String phone;
    private String address;
    private String nationalCode;
    private String onlineTranslate;
    private String records;
    private String lastEducationField;
    private String lastEducationUniversity;
    private Integer lastEducationAverage;
    private String otherField;
    private String bankCardNum;
    private String bankName;
    private String birthCertificate;
    private String image;
    private List<SelectItem> languages;
    private String[] selectedLanguages;
    private List<SelectItem> fields;
    private String[] selectedFields;
    private String[] selectedCities;
    private List<String> cities;

    @PostConstruct
    public void init() {
        languages = new ArrayList<SelectItem>();
        languages.add(new SelectItem("ENtoFA", "انگلیسی به فارسی"));
        languages.add(new SelectItem("FAtoEN", "فارسی به انگلیسی"));
        languages.add(new SelectItem("FRtoRA", "فرانسه به فارسی"));
        languages.add(new SelectItem("FAtoEN", "فارسی به فرانسه"));

        fields = new ArrayList<SelectItem>();
        fields.add(new SelectItem("aloomPa", "علوم پایه"));
        fields.add(new SelectItem("mohandesi", "مهندسی"));
        fields.add(new SelectItem("pezashki", "پزشکی"));
        fields.add(new SelectItem("aloomEn", "علوم انسانی"));
 
        cities = new ArrayList<String>();
        cities.add("Miami");
        cities.add("London");
        cities.add("Paris");
        cities.add("Istanbul");
        cities.add("Berlin");
        cities.add("Barcelona");
        cities.add("Rome");
        cities.add("Brasilia");
        cities.add("Amsterdam");
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public String[] getSelectedCities() {
        return selectedCities;
    }

    public void setSelectedCities(String[] selectedCities) {
        this.selectedCities = selectedCities;
    }

    public String[] getSelectedLanguages() {
        return selectedLanguages;
    }

    public void setSelectedLanguages(String[] selectedLanguages) {
        this.selectedLanguages = selectedLanguages;
    }

    public List<SelectItem> getLanguages() {
        return languages;
    }

    public void setLanguages(List<SelectItem> languages) {
        this.languages = languages;
    }

    public List<SelectItem> getFields() {
        return fields;
    }

    public void setFields(List<SelectItem> fields) {
        this.fields = fields;
    }

 
    public String[] getSelectedFields() {
        return selectedFields;
    }

    public void setSelectedFields(String[] selectedFields) {
        this.selectedFields = selectedFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getOnlineTranslate() {
        return onlineTranslate;
    }

    public void setOnlineTranslate(String onlineTranslate) {
        this.onlineTranslate = onlineTranslate;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public String getLastEducationField() {
        return lastEducationField;
    }

    public void setLastEducationField(String lastEducationField) {
        this.lastEducationField = lastEducationField;
    }

    public String getLastEducationUniversity() {
        return lastEducationUniversity;
    }

    public void setLastEducationUniversity(String lastEducationUniversity) {
        this.lastEducationUniversity = lastEducationUniversity;
    }

    public Integer getLastEducationAverage() {
        return lastEducationAverage;
    }

    public void setLastEducationAverage(Integer lastEducationAverage) {
        this.lastEducationAverage = lastEducationAverage;
    }

    public String getOtherField() {
        return otherField;
    }

    public void setOtherField(String otherField) {
        this.otherField = otherField;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(String birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void imgUpload(FileUploadEvent event) {
        UploadedFile attachFile = event.getFile();
        HttpSession session = SessionBean.getSession();
        session.setAttribute("meliAttachFile", attachFile);
//        if (attachFile != null) {
//            try {
        FacesMessage message = new FacesMessage("Done..", attachFile.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
////                save(FilenameUtils.getName(attachFile.getFileName()), attachFile.getInputstream());
//                Image img = new Image();
//                img.setAddress(attachFile.getFileName());
//                img.setSelected(false);
//                ImageServices.insertRecordIntoTable(img);
//            } catch (Exception ex) {
//                Logger.getLogger(DesignForm.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

    }

    public void meliUpload(FileUploadEvent event) {
        UploadedFile attachFile = event.getFile();
        HttpSession session = SessionBean.getSession();
        session.setAttribute("imgAttachFile", attachFile);
//        if (attachFile != null) {
//            try {
        FacesMessage message = new FacesMessage("Done.11.", attachFile.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
////                save(FilenameUtils.getName(attachFile.getFileName()), attachFile.getInputstream());
//                Image img = new Image();
//                img.setAddress(attachFile.getFileName());
//                img.setSelected(false);
//                ImageServices.insertRecordIntoTable(img);
//            } catch (Exception ex) {
//                Logger.getLogger(DesignForm.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

    }

    private void save(String filename, InputStream input) {
        try {
            System.out.println("hereeeeeeeeeeeeeeee");
            String filePath = "\\web\\resources\\images\\empinfo";
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
        System.out.println(EmploymentForm.class.getName() + ":Submit Function!");
        Employment empl = new Employment();
        empl.setAddress(address);
        empl.setBankCardNumber(bankCardNum);
        empl.setBankName(bankName);
        empl.setEmail(email);
        empl.setLastEducationAverage(lastEducationAverage);
        empl.setLastEducationField(lastEducationField);
        empl.setLastEducationUniversity(lastEducationUniversity);
        empl.setLastName(lastName);
        empl.setMobile(mobile);
        empl.setName(name);
        empl.setNationalCode(nationalCode);
//        HttpSession session = SessionBean.getSession();
//        session.setAttribute("nationalCode", nationalCode);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        try {
            UploadedFile imgAttachFile = (UploadedFile) session.getAttribute("imgAttachFile");
            UploadedFile meliAttachFile = (UploadedFile) session.getAttribute("meliAttachFile");
            if (imgAttachFile != null) {
                empl.setImage(FilenameUtils.getName(imgAttachFile.getFileName()));
                save(nationalCode + "img" + FilenameUtils.getName(imgAttachFile.getFileName()), imgAttachFile.getInputstream());
            }
            if (meliAttachFile != null) {
                empl.setBirthCertificate(FilenameUtils.getName(meliAttachFile.getFileName()));
                save(nationalCode + "meli" + FilenameUtils.getName(meliAttachFile.getFileName()), meliAttachFile.getInputstream());
            }

        } catch (IOException ex) {
            Logger.getLogger(Employment.class.getName()).log(Level.SEVERE, null, ex);
        }
        Boolean ot = false;
        if (onlineTranslate.equals("yes")) {
            ot = true;
        }
        empl.setOnlineTranslate(ot);
        empl.setOtherField(otherField);
        empl.setPhone(phone);
        empl.setRecords(records);
        Boolean s = false;
        if (sex.equals("man")) {
            s = true;
        }
        empl.setSex(s);
        if (selectedFields != null) {
            empl.setField(Arrays.toString(selectedFields));
        }
        if (selectedLanguages != null) {
            empl.setTranslateLanguage(Arrays.toString(selectedLanguages));
        }
        try {
            EmploymentServices.insertRecordIntoTable(empl);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("اطلاعات شما با موفقیت ثبت شد."));
//            FacesContext.getCurrentInstance().getExternalContext().redirect("uploadPage.xhtml");
        } catch (SQLException ex) {
            Logger.getLogger(EmploymentForm.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

//    public void submit2() {
//        Employment empl = new Employment();
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
//                .getExternalContext().getSession(false);
//        nationalCode=  (String) session.getAttribute("nationalCode");
//        try {
//            UploadedFile imgAttachFile = (UploadedFile) session.getAttribute("imgAttachFile");
//            UploadedFile meliAttachFile = (UploadedFile) session.getAttribute("meliAttachFile");
//            if (imgAttachFile != null) {
//                empl.setImage(FilenameUtils.getName(imgAttachFile.getFileName()));
//                save(nationalCode + "img" + FilenameUtils.getName(imgAttachFile.getFileName()), imgAttachFile.getInputstream());
//            }
//            if (meliAttachFile != null) {
//                empl.setBirthCertificate(FilenameUtils.getName(meliAttachFile.getFileName()));
//                save(nationalCode + "meli" + FilenameUtils.getName(meliAttachFile.getFileName()), meliAttachFile.getInputstream());
//            }
//
//            EmploymentServices.insertRecordIntoTable(empl);
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage("آپلود شما با موفقیت انجام شد."));
//        } catch (IOException | SQLException ex) {
//            Logger.getLogger(Employment.class.getName()).log(Level.SEVERE, null, ex);
//        }
}
