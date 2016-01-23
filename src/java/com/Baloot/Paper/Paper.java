/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Paper;

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
@Table(name = "paper")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paper.findAll", query = "SELECT p FROM Paper p"),
    @NamedQuery(name = "Paper.findById", query = "SELECT p FROM Paper p WHERE p.id = :id"),
    @NamedQuery(name = "Paper.findByGroup", query = "SELECT p FROM Paper p WHERE p.group = :group"),
    @NamedQuery(name = "Paper.findByField", query = "SELECT p FROM Paper p WHERE p.field = :field"),
    @NamedQuery(name = "Paper.findByOrientationOfField", query = "SELECT p FROM Paper p WHERE p.orientationOfField = :orientationOfField"),
    @NamedQuery(name = "Paper.findByOption", query = "SELECT p FROM Paper p WHERE p.option = :option"),
    @NamedQuery(name = "Paper.findByExplain", query = "SELECT p FROM Paper p WHERE p.explain = :explain"),
    @NamedQuery(name = "Paper.findByTitle", query = "SELECT p FROM Paper p WHERE p.title = :title"),
    @NamedQuery(name = "Paper.findByDeliveryType", query = "SELECT p FROM Paper p WHERE p.deliveryType = :deliveryType"),
    @NamedQuery(name = "Paper.findByAttachFile", query = "SELECT p FROM Paper p WHERE p.attachFile = :attachFile"),
    @NamedQuery(name = "Paper.findByEndDateTime", query = "SELECT p FROM Paper p WHERE p.endDateTime = :endDateTime"),
    @NamedQuery(name = "Paper.findByDate", query = "SELECT p FROM Paper p WHERE p.date = :date")})
public class Paper implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "group")
    private Integer group;
    @Column(name = "field")
    private Integer field;
    @Column(name = "orientation_of_field")
    private Integer orientationOfField;
    @Size(max = 8)
    @Column(name = "option")
    private String option;
    @Size(max = 750)
    @Column(name = "explain")
    private String explain;
    @Size(max = 500)
    @Column(name = "title")
    private String title;
    @Column(name = "delivery_type")
    private Boolean deliveryType;
    @Size(max = 550)
    @Column(name = "attach_file")
    private String attachFile;
    @Size(max = 30)
    @Column(name = "end_date_time")
    private String endDateTime;
    @Size(max = 30)
    @Column(name = "date")
    private String date;

    public Paper() {
    }

    public Paper(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public Integer getField() {
        return field;
    }

    public void setField(Integer field) {
        this.field = field;
    }

    public Integer getOrientationOfField() {
        return orientationOfField;
    }

    public void setOrientationOfField(Integer orientationOfField) {
        this.orientationOfField = orientationOfField;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Boolean deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        if (!(object instanceof Paper)) {
            return false;
        }
        Paper other = (Paper) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Paper[ id=" + id + " ]";
    }
    
}
