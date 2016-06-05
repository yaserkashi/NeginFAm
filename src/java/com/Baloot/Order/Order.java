/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Order;

import com.Baloot.Enum.StepsOfOrder;
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
@Table(name = "order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o"),
    @NamedQuery(name = "Order1.findById", query = "SELECT o FROM Order1 o WHERE o.id = :id"),
    @NamedQuery(name = "Order1.findByTableName", query = "SELECT o FROM Order1 o WHERE o.tableName = :tableName"),
    @NamedQuery(name = "Order1.findByTableId", query = "SELECT o FROM Order1 o WHERE o.tableId = :tableId"),
    @NamedQuery(name = "Order1.findByOrderDate", query = "SELECT o FROM Order1 o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "Order1.findByCondition", query = "SELECT o FROM Order1 o WHERE o.condition = :condition")})
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "table_name")
    private String tableName;
    @Column(name = "table_id")
    private Integer tableId;
    @Size(max = 250)
    @Column(name = "order_date")
    private String orderDate;
    @Column(name = "condition")
    private Integer condition;
    /*
     /آیدی برای چک پرداخت آنلاین
     */
    @Column(name = "get_id")
    private Integer get_id;

    /*
     /آیدی برای چک پرداخت آنلاین
     */
    @Size(max = 250)
    @Column(name = "final_file")
    private String finalFile;

    
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Users userId;

    public Integer getGet_id() {
        return get_id;
    }

    public void setGet_id(Integer get_id) {
        this.get_id = get_id;
    }

    public Order() {
    }

    public Order(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }
    
    public String getFinalFile() {
        return finalFile;
    }

    public void setFinalFile(String finalFile) {
        this.finalFile = finalFile;
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
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Order1[ id=" + id + " ]";
    }

    /**
     * وضعیت یک سفارش
     *
     * @return وضعیت سفارش را به صورت یک استرینگ فارسی برمیگرداند
     */
    public String conditionView() {
        if (condition == StepsOfOrder.registrationOrder.ordinal()) {
            return "سفارش ثبت شده";
        } else if (condition == StepsOfOrder.registrationFactor.ordinal()) {
            return " فاکتور صادر شده";
        } else if (condition == StepsOfOrder.payFactor.ordinal()) {
            return "فاکتور توسط مشتری پرداخت شده";
        } else if (condition == StepsOfOrder.ConfirmationPayFactor.ordinal()) {
            return "پرداخت تایید شده و انجام کاار شروع شده";
        } else if (condition == StepsOfOrder.EndOrder.ordinal()) {
            return "کار به اتمام رسیده است";
        } else if (condition == StepsOfOrder.dissuasion.ordinal()) {
            return "سفارش کنسل شده است";
        }
        return "نا مشخص";
    }

    /**
     * نام فارسی جداول یا همان عنوان فارسی سفارشات
     *
     * @return یک استرینگ فارسی
     */
    public String tablePersianName() {
        if (tableName.equals("type")) {
            return "تایپ";
        } else if (tableName.equals("design")) {
            return "طراحی";
        } else if (tableName.equals("translate")) {
            return "ترجمه";
        } else if (tableName.equals("paper")) {
            return "مقاله";
        }
        return "نامشخص";
    }
    
     public void downloadFinalFile() {
        try {            
            System.out.println("id and attach file is "+ id+finalFile );
            String filename="final"+this.tableName+this.id+this.finalFile;            
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
