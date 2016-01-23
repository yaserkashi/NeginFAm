/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Employment;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FK
 */
@Entity
@Table(name = "employment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employment.findAll", query = "SELECT e FROM Employment e"),
    @NamedQuery(name = "Employment.findById", query = "SELECT e FROM Employment e WHERE e.id = :id"),
    @NamedQuery(name = "Employment.findByName", query = "SELECT e FROM Employment e WHERE e.name = :name"),
    @NamedQuery(name = "Employment.findByLastName", query = "SELECT e FROM Employment e WHERE e.lastName = :lastName"),
    @NamedQuery(name = "Employment.findBySex", query = "SELECT e FROM Employment e WHERE e.sex = :sex"),
    @NamedQuery(name = "Employment.findByMobile", query = "SELECT e FROM Employment e WHERE e.mobile = :mobile"),
    @NamedQuery(name = "Employment.findByPhone", query = "SELECT e FROM Employment e WHERE e.phone = :phone"),
    @NamedQuery(name = "Employment.findByNationalCode", query = "SELECT e FROM Employment e WHERE e.nationalCode = :nationalCode"),
    @NamedQuery(name = "Employment.findByAddress", query = "SELECT e FROM Employment e WHERE e.address = :address"),
    @NamedQuery(name = "Employment.findByEmail", query = "SELECT e FROM Employment e WHERE e.email = :email"),
    @NamedQuery(name = "Employment.findByOnlineTranslate", query = "SELECT e FROM Employment e WHERE e.onlineTranslate = :onlineTranslate"),
    @NamedQuery(name = "Employment.findByRecords", query = "SELECT e FROM Employment e WHERE e.records = :records"),
    @NamedQuery(name = "Employment.findByLastEducationAverage", query = "SELECT e FROM Employment e WHERE e.lastEducationAverage = :lastEducationAverage"),
    @NamedQuery(name = "Employment.findByOtherField", query = "SELECT e FROM Employment e WHERE e.otherField = :otherField"),
    @NamedQuery(name = "Employment.findByImage", query = "SELECT e FROM Employment e WHERE e.image = :image"),
    @NamedQuery(name = "Employment.findByBirthCertificate", query = "SELECT e FROM Employment e WHERE e.birthCertificate = :birthCertificate"),
    @NamedQuery(name = "Employment.findByBankName", query = "SELECT e FROM Employment e WHERE e.bankName = :bankName"),
    @NamedQuery(name = "Employment.findByBankCardNumber", query = "SELECT e FROM Employment e WHERE e.bankCardNumber = :bankCardNumber"),
    @NamedQuery(name = "Employment.findByTranslateLanguage", query = "SELECT e FROM Employment e WHERE e.translateLanguage = :translateLanguage"),
    @NamedQuery(name = "Employment.findByField", query = "SELECT e FROM Employment e WHERE e.field = :field"),
    @NamedQuery(name = "Employment.findByLastEducationField", query = "SELECT e FROM Employment e WHERE e.lastEducationField = :lastEducationField"),
    @NamedQuery(name = "Employment.findByLastEducationUniversity", query = "SELECT e FROM Employment e WHERE e.lastEducationUniversity = :lastEducationUniversity")})
public class Employment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 75)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "sex")
    private Boolean sex;
    @Size(max = 12)
    @Column(name = "mobile")
    private String mobile;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 12)
    @Column(name = "phone")
    private String phone;
    @Size(max = 10)
    @Column(name = "national _code")
    private String nationalCode;
    @Size(max = 950)
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 500)
    @Column(name = "email")
    private String email;
    @Column(name = "online_translate")
    private Boolean onlineTranslate;
    @Size(max = 950)
    @Column(name = "records")
    private String records;
    @Column(name = "last_education_average")
    private Integer lastEducationAverage;
    @Size(max = 550)
    @Column(name = "other_field")
    private String otherField;
    @Size(max = 550)
    @Column(name = "image")
    private String image;
    @Size(max = 550)
    @Column(name = "birth_certificate")
    private String birthCertificate;
    @Size(max = 100)
    @Column(name = "bank_name")
    private String bankName;
    @Size(max = 16)
    @Column(name = "bank_card_number")
    private String bankCardNumber;
    @Size(max = 10)
    @Column(name = "translate_language")
    private String translateLanguage;
    @Size(max = 50)
    @Column(name = "field")
    private String field;
    @Size(max = 250)
    @Column(name = "last_education_field")
    private String lastEducationField;
    @Size(max = 250)
    @Column(name = "last_education_university")
    private String lastEducationUniversity;

    public Employment() {
    }

    public Employment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
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

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getOnlineTranslate() {
        return onlineTranslate;
    }

    public void setOnlineTranslate(Boolean onlineTranslate) {
        this.onlineTranslate = onlineTranslate;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(String birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getTranslateLanguage() {
        return translateLanguage;
    }

    public void setTranslateLanguage(String translateLanguage) {
        this.translateLanguage = translateLanguage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employment)) {
            return false;
        }
        Employment other = (Employment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Employment[ id=" + id + " ]";
    }
    
}
