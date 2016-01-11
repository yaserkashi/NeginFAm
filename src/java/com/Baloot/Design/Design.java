/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Design;

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
@Table(name = "design")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Design.findAll", query = "SELECT d FROM Design d"),
    @NamedQuery(name = "Design.findByDesignType", query = "SELECT d FROM Design d WHERE d.designType = :designType"),
    @NamedQuery(name = "Design.findBySize", query = "SELECT d FROM Design d WHERE d.size = :size"),
    @NamedQuery(name = "Design.findByRegisterDate", query = "SELECT d FROM Design d WHERE d.registerDate = :registerDate"),
    @NamedQuery(name = "Design.findByPrintType", query = "SELECT d FROM Design d WHERE d.printType = :printType"),
    @NamedQuery(name = "Design.findByDesingOption", query = "SELECT d FROM Design d WHERE d.desingOption = :desingOption"),
    @NamedQuery(name = "Design.findByPrintOption", query = "SELECT d FROM Design d WHERE d.printOption = :printOption"),
    @NamedQuery(name = "Design.findByEndDate", query = "SELECT d FROM Design d WHERE d.endDate = :endDate"),
    @NamedQuery(name = "Design.findByExplain", query = "SELECT d FROM Design d WHERE d.explain = :explain"),
    @NamedQuery(name = "Design.findByAttachFile", query = "SELECT d FROM Design d WHERE d.attachFile = :attachFile"),
    @NamedQuery(name = "Design.findById", query = "SELECT d FROM Design d WHERE d.id = :id")})
public class Design implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "design_type")
    private Integer designType;
    @Size(max = 30)
    @Column(name = "size")
    private String size;
    @Size(max = 30)
    @Column(name = "register_date")
    private String registerDate;
    @Size(max = 30)
    @Column(name = "print_type")
    private String printType;
    @Size(max = 30)
    @Column(name = "desing_option")
    private String desingOption;
    @Size(max = 30)
    @Column(name = "print_option")
    private String printOption;
    @Size(max = 30)
    @Column(name = "end_date")
    private String endDate;
    @Size(max = 1000)
    @Column(name = "explain")
    private String explain;
    @Size(max = 250)
    @Column(name = "attach_file")
    private String attachFile;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public Design() {
    }

    public Design(Integer id) {
        this.id = id;
    }

    public Integer getDesignType() {
        return designType;
    }

    public void setDesignType(Integer designType) {
        this.designType = designType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getDesingOption() {
        return desingOption;
    }

    public void setDesingOption(String desingOption) {
        this.desingOption = desingOption;
    }

    public String getPrintOption() {
        return printOption;
    }

    public void setPrintOption(String printOption) {
        this.printOption = printOption;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Design)) {
            return false;
        }
        Design other = (Design) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Design[ id=" + id + " ]";
    }
    
}
