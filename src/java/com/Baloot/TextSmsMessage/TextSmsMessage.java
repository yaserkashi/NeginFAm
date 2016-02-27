/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.TextSmsMessage;

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
 * @author Ali
 */
@Entity
@Table(name = "text_sms_message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TextSmsMessage.findAll", query = "SELECT t FROM TextSmsMessage t"),
    @NamedQuery(name = "TextSmsMessage.findByText", query = "SELECT t FROM TextSmsMessage t WHERE t.text = :text"),
    @NamedQuery(name = "TextSmsMessage.findById", query = "SELECT t FROM TextSmsMessage t WHERE t.id = :id"),
    @NamedQuery(name = "TextSmsMessage.findByStaticValueType", query = "SELECT t FROM TextSmsMessage t WHERE t.staticValueType = :staticValueType")})
public class TextSmsMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 250)
    @Column(name = "text")
    private String text;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "static_value_type")
    private Integer staticValueType;

    public TextSmsMessage() {
    }

    public TextSmsMessage(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaticValueType() {
        return staticValueType;
    }

    public void setStaticValueType(Integer staticValueType) {
        this.staticValueType = staticValueType;
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
        if (!(object instanceof TextSmsMessage)) {
            return false;
        }
        TextSmsMessage other = (TextSmsMessage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Baloot.TextSmsMessage.TextSmsMessage[ id=" + id + " ]";
    }
    
}
