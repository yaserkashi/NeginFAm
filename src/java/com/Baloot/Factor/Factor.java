/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Factor;

import com.Baloot.FactorItem.FactorItem;
import com.Baloot.User.Users;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author FK
 */
@Entity
@Table(name = "factor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factor.findAll", query = "SELECT f FROM Factor f"),
    @NamedQuery(name = "Factor.findBySumPrice", query = "SELECT f FROM Factor f WHERE f.sumPrice = :sumPrice"),
    @NamedQuery(name = "Factor.findByOff", query = "SELECT f FROM Factor f WHERE f.off = :off"),
    @NamedQuery(name = "Factor.findByPayCondition", query = "SELECT f FROM Factor f WHERE f.payCondition = :payCondition"),
    @NamedQuery(name = "Factor.findByPFactor", query = "SELECT f FROM Factor f WHERE f.pFactor = :pFactor"),
    @NamedQuery(name = "Factor.findByDateTime", query = "SELECT f FROM Factor f WHERE f.dateTime = :dateTime"),
    @NamedQuery(name = "Factor.findById", query = "SELECT f FROM Factor f WHERE f.id = :id")})
public class Factor implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sum_price")
    private Double sumPrice;
    @Column(name = "off")
    private Double off;
    @Column(name = "pay_condition")
    private Integer payCondition;
    @Column(name = "p_factor")
    private Boolean pFactor;
    @Size(max = 30)
    @Column(name = "date_time")
    private String dateTime;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "factorId")
    private Collection<FactorItem> factorItemCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public Factor() {
    }

    public Factor(Integer id) {
        this.id = id;
    }

    public Double getSumPrice() {
        System.out.println("here"+ sumPrice);
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Double getOff() {
        return off;
    }

    public void setOff(Double off) {
        this.off = off;
    }

    public Integer getPayCondition() {
        return payCondition;
    }

    public void setPayCondition(Integer payCondition) {
        this.payCondition = payCondition;
    }

    public Boolean getPFactor() {
        return pFactor;
    }

    public void setPFactor(Boolean pFactor) {
        this.pFactor = pFactor;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<FactorItem> getFactorItemCollection() {
        return factorItemCollection;
    }

    public void setFactorItemCollection(Collection<FactorItem> factorItemCollection) {
        this.factorItemCollection = factorItemCollection;
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
        if (!(object instanceof Factor)) {
            return false;
        }
        Factor other = (Factor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factor[ id=" + id + " ]";
    }
    
}
