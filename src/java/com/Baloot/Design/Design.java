/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Design;

import com.Baloot.Coding.CodingServices;
import com.Baloot.User.Users;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.faces.context.FacesContext;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @NamedQuery(name = "Design.findByDesignOption", query = "SELECT d FROM Design d WHERE d.designOption = :designOption"),
    @NamedQuery(name = "Design.findByPrintOption", query = "SELECT d FROM Design d WHERE d.printOption = :printOption"),
    @NamedQuery(name = "Design.findByEndDate", query = "SELECT d FROM Design d WHERE d.endDate = :endDate"),
    @NamedQuery(name = "Design.findByExplain", query = "SELECT d FROM Design d WHERE d.explain = :explain"),
    @NamedQuery(name = "Design.findByAttachFile", query = "SELECT d FROM Design d WHERE d.attachFile = :attachFile"),
    @NamedQuery(name = "Design.findById", query = "SELECT d FROM Design d WHERE d.id = :id"),
    @NamedQuery(name = "Design.findByDeliveryType", query = "SELECT d FROM Design d WHERE d.deliveryType = :deliveryType")})
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
    @Column(name = "design_option")
    private String designOption;
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
    @Column(name = "delivery_type")
    private Boolean deliveryType;
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

    public String getDesignOption() {
        return designOption;
    }

    public void setDesignOption(String designOption) {
        this.designOption = designOption;
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

    public Boolean getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Boolean deliveryType) {
        this.deliveryType = deliveryType;
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

    /**
     * نوع طراحی
     *
     * @return مقدار استرینگ فارسی برمیگرداند
     */
    public String designTypeFarsi() {
        if (designType != null) {
            return CodingServices.getCodings(designType).getText();
        } else {
            return "نا مشخص";
        }
    }

    /**
     * تحویل حضوری
     *
     * @return دارد یا ندارد بر میگرداند
     */
    public String hasDelevry() {
        if (!deliveryType) {
            return "ندارد";
        } else {
            return "دارد";
        }
    }

    public void downloadUploadedFile() {
        try {            
            String filename="design"+this.id+this.attachFile;            
            String filePath = "\\web\\resources\\downloadfile";
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest httpServletRequest = (HttpServletRequest) context
                    .getExternalContext().getRequest();
            String stringPath = httpServletRequest.getSession().getServletContext()
                    .getRealPath("/");
            Path path = Paths.get(stringPath);
            filePath = path.getParent().getParent().toString() + filePath;  
            File file =new File(filePath, filename);
            FileInputStream stream = new FileInputStream(file);
            HttpServletResponse response = (HttpServletResponse) context
                    .getExternalContext().getResponse();
            response.reset();
            response.setBufferSize(5120000);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            BufferedInputStream input = null;
            BufferedOutputStream output = null;
            try {
                input = new BufferedInputStream(stream);
                output = new BufferedOutputStream(response.getOutputStream(),
                        5120000);
                byte[] buffer = new byte[5120000];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                input.close();
                output.close();
            }
            context.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
