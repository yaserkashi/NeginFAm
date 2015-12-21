/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.FactorItem;

import com.Baloot.Factor.Factor;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "factor_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FactorItem.findAll", query = "SELECT f FROM FactorItem f"),
    @NamedQuery(name = "FactorItem.findById", query = "SELECT f FROM FactorItem f WHERE f.id = :id"),
    @NamedQuery(name = "FactorItem.findByOrderId", query = "SELECT f FROM FactorItem f WHERE f.orderId = :orderId"),
    @NamedQuery(name = "FactorItem.findByUnit", query = "SELECT f FROM FactorItem f WHERE f.unit = :unit"),
    @NamedQuery(name = "FactorItem.findByNumber", query = "SELECT f FROM FactorItem f WHERE f.number = :number"),
    @NamedQuery(name = "FactorItem.findByUnitPrice", query = "SELECT f FROM FactorItem f WHERE f.unitPrice = :unitPrice")})
public class FactorItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "order_id")
    private Integer orderId;
    @Size(max = 250)
    @Column(name = "unit")
    private String unit;
    @Column(name = "number")
    private Integer number;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unit_price")
    private Double unitPrice;
    @JoinColumn(name = "factor_id", referencedColumnName = "id")
    @ManyToOne
    private Factor factorId;

    public FactorItem() {
    }

    public FactorItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Factor getFactorId() {
        return factorId;
    }

    public void setFactorId(Factor factorId) {
        this.factorId = factorId;
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
        if (!(object instanceof FactorItem)) {
            return false;
        }
        FactorItem other = (FactorItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.FactorItem[ id=" + id + " ]";
    }
    
}
