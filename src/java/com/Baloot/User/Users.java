/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.User;

import com.Baloot.Design.Design;
import com.Baloot.Factor.Factor;
import com.Baloot.Message.Message;
import com.Baloot.Order.Order;
import com.Baloot.SmsMessage.SmsMessage;
import com.Baloot.Translate.Translate;
import com.Baloot.Type.Type;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByFamily", query = "SELECT u FROM Users u WHERE u.family = :family"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByPasword", query = "SELECT u FROM Users u WHERE u.pasword = :pasword"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPhoneNum", query = "SELECT u FROM Users u WHERE u.phoneNum = :phoneNum"),
    @NamedQuery(name = "Users.findByAccessLevel", query = "SELECT u FROM Users u WHERE u.accessLevel = :accessLevel")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "family")
    private String family;
    @Size(max = 30)
    @Column(name = "username")
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "pasword")
    private String pasword;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Size(max = 15)
    @Column(name = "phone_num")
    private String phoneNum;
    @Column(name = "access_level")
    private Integer accessLevel;
    @OneToMany(mappedBy = "userId")
    private Collection<Design> designCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<SmsMessage> smsMessageCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Factor> factorCollection;
    @OneToMany(mappedBy = "userIdGet")
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "userIdSend")
    private Collection<Message> messageCollection1;
    @OneToMany(mappedBy = "userId")
    private Collection<Type> typeCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Translate> translateCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<Order> orderCollection;

    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    @XmlTransient
    public Collection<Design> getDesignCollection() {
        return designCollection;
    }

    public void setDesignCollection(Collection<Design> designCollection) {
        this.designCollection = designCollection;
    }

    @XmlTransient
    public Collection<SmsMessage> getSmsMessageCollection() {
        return smsMessageCollection;
    }

    public void setSmsMessageCollection(Collection<SmsMessage> smsMessageCollection) {
        this.smsMessageCollection = smsMessageCollection;
    }

    @XmlTransient
    public Collection<Factor> getFactorCollection() {
        return factorCollection;
    }

    public void setFactorCollection(Collection<Factor> factorCollection) {
        this.factorCollection = factorCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection1() {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1) {
        this.messageCollection1 = messageCollection1;
    }

    @XmlTransient
    public Collection<Type> getTypeCollection() {
        return typeCollection;
    }

    public void setTypeCollection(Collection<Type> typeCollection) {
        this.typeCollection = typeCollection;
    }

    @XmlTransient
    public Collection<Translate> getTranslateCollection() {
        return translateCollection;
    }

    public void setTranslateCollection(Collection<Translate> translateCollection) {
        this.translateCollection = translateCollection;
    }

    @XmlTransient
    public Collection<Order> getOrderCollection() {
        return orderCollection;
    }

    public void setOrderCollection(Collection<Order> orderCollection) {
        this.orderCollection = orderCollection;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Users[ id=" + id + " ]";
    }
    
}
