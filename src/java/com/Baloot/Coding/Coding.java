/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Coding;

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
@Table(name = "coding")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coding.findAll", query = "SELECT c FROM Coding c"),
    @NamedQuery(name = "Coding.findById", query = "SELECT c FROM Coding c WHERE c.id = :id"),
    @NamedQuery(name = "Coding.findByText", query = "SELECT c FROM Coding c WHERE c.text = :text"),
    @NamedQuery(name = "Coding.findByOrderId", query = "SELECT c FROM Coding c WHERE c.orderId = :orderId"),
    @NamedQuery(name = "Coding.findByTypeId", query = "SELECT c FROM Coding c WHERE c.typeId = :typeId")})
public class Coding implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "text")
    private String text;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "type_id")
    private Integer typeId;

    public Coding() {
    }

    public Coding(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
        if (!(object instanceof Coding)) {
            return false;
        }
        Coding other = (Coding) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Coding[ id=" + id + " ]";
    }
    
}
