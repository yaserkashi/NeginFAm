/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Translate;

import com.Baloot.User.Users;
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
@Table(name = "translate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Translate.findAll", query = "SELECT t FROM Translate t"),
    @NamedQuery(name = "Translate.findByLanguage", query = "SELECT t FROM Translate t WHERE t.language = :language"),
    @NamedQuery(name = "Translate.findByField", query = "SELECT t FROM Translate t WHERE t.field = :field"),
    @NamedQuery(name = "Translate.findByDateTime", query = "SELECT t FROM Translate t WHERE t.dateTime = :dateTime"),
    @NamedQuery(name = "Translate.findByEndDateTime", query = "SELECT t FROM Translate t WHERE t.endDateTime = :endDateTime"),
    @NamedQuery(name = "Translate.findByTitle", query = "SELECT t FROM Translate t WHERE t.title = :title"),
    @NamedQuery(name = "Translate.findByOption", query = "SELECT t FROM Translate t WHERE t.option = :option"),
    @NamedQuery(name = "Translate.findByAttachFile", query = "SELECT t FROM Translate t WHERE t.attachFile = :attachFile"),
    @NamedQuery(name = "Translate.findByExplain", query = "SELECT t FROM Translate t WHERE t.explain = :explain"),
    @NamedQuery(name = "Translate.findById", query = "SELECT t FROM Translate t WHERE t.id = :id"),
    @NamedQuery(name = "Translate.findByDeliveryType", query = "SELECT t FROM Translate t WHERE t.deliveryType = :deliveryType")})
public class Translate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "language")
    private Integer language;
    @Column(name = "field")
    private Integer field;
    @Size(max = 2147483647)
    @Column(name = "date_time")
    private String dateTime;
    @Size(max = 2147483647)
    @Column(name = "end_date_time")
    private String endDateTime;
    @Size(max = 2147483647)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "option")
    private String option;
    @Size(max = 2147483647)
    @Column(name = "attach_file")
    private String attachFile;
    @Size(max = 2147483647)
    @Column(name = "explain")
    private String explain;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "delivery_type")
    private Boolean deliveryType;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public Translate() {
    }

    public Translate(Integer id) {
        this.id = id;
    }

    public Integer getLanguage() {
        return language;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
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
        if (!(object instanceof Translate)) {
            return false;
        }
        Translate other = (Translate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Translate[ id=" + id + " ]";
    }
    
}
