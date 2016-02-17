/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Employment;

import java.sql.SQLException;
import java.util.ArrayList;
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
    private List<Boolean> languages;
    private List<Boolean> fields;
    private String otherField;
    private String bankCardNum;
    private String bankName;
    private UploadedFile birthCertificate;
    private UploadedFile image;
    private List<SelectItem> items;
    private String[] selectedItems;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
        items.add(new SelectItem("Miami1", "valueMiami1"));
        items.add(new SelectItem("Miami2", "valueMiami2"));
        items.add(new SelectItem("Miami3", "valueMiami3"));
        items.add(new SelectItem("Miami11", "valueMiami31"));
    }

    public List<SelectItem> getItems() {
        return items;
    }

    public void setItems(List<SelectItem> items) {
        this.items = items;
    }

    public String[] getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(String[] selectedItems) {
        this.selectedItems = selectedItems;
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

    public List<Boolean> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Boolean> languages) {
        this.languages = languages;
    }

    public List<Boolean> getFields() {
        return fields;
    }

    public void setFields(List<Boolean> fields) {
        this.fields = fields;
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

    public String getLanguege(List<Boolean> languages) {
        String language = "";
        for (Boolean lang : languages) {
            if (lang) {
                language += "1";
            } else {
                language += "0";
            }
        }
        return language;
    }

    public String getField(List<Boolean> fields) {
        String field = "";
        for (Boolean f : fields) {
            if (f) {
                field += "1";
            } else {
                field += "0";
            }
        }
        return field;
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
        if (fields != null) {
            empl.setField(getField(fields));
        }
        if (languages != null) {
            empl.setTranslateLanguage(getLanguege(languages));
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
