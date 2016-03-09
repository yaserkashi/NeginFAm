/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Type;

import com.Baloot.Coding.CodingServices;
import com.Baloot.User.Users;
import com.Baloot.util.UtilForDecode;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t"),
    @NamedQuery(name = "Type.findByLanguage", query = "SELECT t FROM Type t WHERE t.language = :language"),
    @NamedQuery(name = "Type.findByField", query = "SELECT t FROM Type t WHERE t.field = :field"),
    @NamedQuery(name = "Type.findByTitle", query = "SELECT t FROM Type t WHERE t.title = :title"),
    @NamedQuery(name = "Type.findByDateTime", query = "SELECT t FROM Type t WHERE t.dateTime = :dateTime"),
    @NamedQuery(name = "Type.findByExplain", query = "SELECT t FROM Type t WHERE t.explain = :explain"),
    @NamedQuery(name = "Type.findByOption", query = "SELECT t FROM Type t WHERE t.option = :option"),
    @NamedQuery(name = "Type.findByAttachFile", query = "SELECT t FROM Type t WHERE t.attachFile = :attachFile"),
    @NamedQuery(name = "Type.findById", query = "SELECT t FROM Type t WHERE t.id = :id"),
    @NamedQuery(name = "Type.findByDeliveryType", query = "SELECT t FROM Type t WHERE t.deliveryType = :deliveryType"),
    @NamedQuery(name = "Type.findByEndDateTime", query = "SELECT t FROM Type t WHERE t.endDateTime = :endDateTime")})
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "language")
    private Integer language;
    @Column(name = "field")
    private Integer field;
    @Size(max = 2147483647)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "date_time")
    private String dateTime;
    @Size(max = 2147483647)
    @Column(name = "explain")
    private String explain;
    @Size(max = 2147483647)
    @Column(name = "option")
    private String option;
    @Size(max = 2147483647)
    @Column(name = "attach_file")
    private String attachFile;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "delivery_type")
    private Boolean deliveryType;
    @Size(max = 30)
    @Column(name = "end_date_time")
    private String endDateTime;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public Type() {
    }

    public Type(Integer id) {
        this.id = id;
    }

    public Integer getLanguage() {
        return language;
    }
/**
     * زبان به صورت فارسی
     *
     * @return
     */
    public String languageText() {
        if (language != null) {
            return CodingServices.getCodings(language).getText();
        }
        return "نامشخص";
    }
    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getField() {
        return field;
    }

    public void setField(Integer field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Boolean deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof Type)) {
            return false;
        }
        Type other = (Type) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Type[ id=" + id + " ]";
    }
    /**
     * فرمول نویسی
     *
     * @return دارد یا ندارد برمیگرداند
     */
    public String option1() {
        return UtilForDecode.decod(option.charAt(0));
    }

    /**
     * صفحه آرایی
     *
     * @return دارد یا ندارد برمیگرداند
     */
    public String option2() {
        return UtilForDecode.decod(option.charAt(1));
    }

    /**
     * تصویرگری
     *
     * @return دارد یا ندارد برمیگرداند
     */
    public String option3() {
        return UtilForDecode.decod(option.charAt(2));
    }

    /**
     * رسم جدول
     *
     * @return دارد یا ندارد برمیگرداند
     */
    public String option4() {
        return UtilForDecode.decod(option.charAt(3));
    }

    /**
     * رسم نمودار
     *
     * @return دارد یا ندارد برمیگرداند
     */
    public String option5() {
        return UtilForDecode.decod(option.charAt(4));
    }

    /**
     * رسم شکل
     *
     * @return دارد یا ندارد برمیگرداند
     */
    public String option6() {
        return UtilForDecode.decod(option.charAt(5));
    }

    /**
     * ویراستاری
     *
     * @return دارد یا ندارد برمیگرداند
     */
    public String option7() {
        return UtilForDecode.decod(option.charAt(6));
    }
    
     /**
     * تحویل حضوری دارد یا نه 
     * @return استرینگ فارسی دارد یا ندارد بر میگرداند
     */
    public String hasDelivery() {
        return UtilForDecode.decod(deliveryType);
    }

}
