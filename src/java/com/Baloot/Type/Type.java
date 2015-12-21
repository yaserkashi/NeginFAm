/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Type;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Type.findById", query = "SELECT t FROM Type t WHERE t.id = :id"),
    @NamedQuery(name = "Type.findByLanguage", query = "SELECT t FROM Type t WHERE t.language = :language"),
    @NamedQuery(name = "Type.findByField", query = "SELECT t FROM Type t WHERE t.field = :field"),
    @NamedQuery(name = "Type.findByTitle", query = "SELECT t FROM Type t WHERE t.title = :title"),
    @NamedQuery(name = "Type.findByDateTime", query = "SELECT t FROM Type t WHERE t.dateTime = :dateTime"),
    @NamedQuery(name = "Type.findByExplain", query = "SELECT t FROM Type t WHERE t.explain = :explain"),
    @NamedQuery(name = "Type.findByOption", query = "SELECT t FROM Type t WHERE t.option = :option"),
    @NamedQuery(name = "Type.findByUserId", query = "SELECT t FROM Type t WHERE t.userId = :userId"),
    @NamedQuery(name = "Design.findByAttachFile", query = "SELECT d FROM Design d WHERE d.attachFile = :attachFile")})
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "language")
    private Integer language;
    @Column(name = "field")
    private Integer field;
    @Size(max = 2147483647)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "date-time")
    private String dateTime;
    @Size(max = 2147483647)
    @Column(name = "explain")
    private String explain;
    @Size(max = 2147483647)
    @Column(name = "option")
    private String option;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "attach_file")
    private String attachFile;

    public Type() {
    }

    public Type(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
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
    
}
