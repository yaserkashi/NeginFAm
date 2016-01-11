/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.SmsMessage;

import com.Baloot.TextSmsMessage.TextSmsMessage;
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
@Table(name = "sms_message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmsMessage.findAll", query = "SELECT s FROM SmsMessage s"),
    @NamedQuery(name = "SmsMessage.findByCondition", query = "SELECT s FROM SmsMessage s WHERE s.condition = :condition"),
    @NamedQuery(name = "SmsMessage.findById", query = "SELECT s FROM SmsMessage s WHERE s.id = :id")})
public class SmsMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 100)
    @Column(name = "condition")
    private String condition;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "text_id", referencedColumnName = "id")
    @ManyToOne
    private TextSmsMessage textId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public SmsMessage() {
    }

    public SmsMessage(Integer id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TextSmsMessage getTextId() {
        return textId;
    }

    public void setTextId(TextSmsMessage textId) {
        this.textId = textId;
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
        if (!(object instanceof SmsMessage)) {
            return false;
        }
        SmsMessage other = (SmsMessage) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "Entity.SmsMessage[ id=" + id + " ]";
    }
    
}
