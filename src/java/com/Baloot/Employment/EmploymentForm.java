/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Employment;

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
import javax.validation.constraints.Digits;
import org.hibernate.validator.constraints.Email;
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
    private UploadedFile birthCertificate;
    private UploadedFile image;
    private List<SelectItem> languages;
    private String[] selectedLanguages;
    private List<SelectItem> fields;
    private String[] selectedFields;

    @PostConstruct
    public void init() {
        languages = new ArrayList<>();
        languages.add(new SelectItem("انگلیسی به فارسی", "انگلیسی به فارسی"));
        languages.add(new SelectItem("فارسی به انگلیسی", "فارسی به انگلیسی"));
        languages.add(new SelectItem("فرانسه به فارسی", "فرانسه به فارسی"));
        languages.add(new SelectItem("فارسی به فرانسه", "فارسی به فرانسه"));
        
        fields = new ArrayList<>();
        fields.add(new SelectItem("علوم پایه", "علوم پایه"));
        fields.add(new SelectItem("مهندسی", "مهندسی"));
        fields.add(new SelectItem("پزشکی", "پزشکی"));
        fields.add(new SelectItem("علوم انسانی", "علوم انسانی"));
    }

    public List<SelectItem> getLanguages() {
        return languages;
    }

    public void setLanguages(List<SelectItem> items) {
        this.languages = items;
    }

    public String[] getSelectedLanguages() {
        return selectedLanguages;
    }

    public void setSelectedLanguges(String[] selectedItems) {
        this.selectedLanguages = selectedItems;
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

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public UploadedFile getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(UploadedFile birthCertificate) {
        this.birthCertificate = birthCertificate;
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
        } catch (SQLException ex) {
            Logger.getLogger(EmploymentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
