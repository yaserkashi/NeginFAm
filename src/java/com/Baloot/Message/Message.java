/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Message;

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
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByText", query = "SELECT m FROM Message m WHERE m.text = :text"),
    @NamedQuery(name = "Message.findByDateTime", query = "SELECT m FROM Message m WHERE m.dateTime = :dateTime"),
    @NamedQuery(name = "Message.findByRead", query = "SELECT m FROM Message m WHERE m.read = :read"),
    @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
    @NamedQuery(name = "Message.findByTitle", query = "SELECT m FROM Message m WHERE m.title = :title")})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 750)
    @Column(name = "text")
    private String text;
    @Size(max = 30)
    @Column(name = "date_time")
    private String dateTime;
    @Column(name = "read")
    private Boolean read;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "title")
    private String title;
    @JoinColumn(name = "user_id_get", referencedColumnName = "id")
    @ManyToOne
    private Users userIdGet;
    @JoinColumn(name = "user_id_send", referencedColumnName = "id")
    @ManyToOne
    private Users userIdSend;

    public Message() {
    }

    public Message(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Users getUserIdGet() {
        return userIdGet;
    }

    public void setUserIdGet(Users userIdGet) {
        this.userIdGet = userIdGet;
    }

    public Users getUserIdSend() {
        return userIdSend;
    }

    public void setUserIdSend(Users userIdSend) {
        this.userIdSend = userIdSend;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Message[ id=" + id + " ]";
    }
    
}
