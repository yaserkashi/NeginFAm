/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Order;

import com.Baloot.Design.*;
import com.Baloot.Enum.*;
import com.Baloot.Factor.Factor;
import com.Baloot.Factor.FactorServices;
import com.Baloot.FactorItem.FactorItem;
import com.Baloot.FactorItem.FactorItemServices;
import com.Baloot.Paper.*;
import com.Baloot.Translate.*;
import com.Baloot.Type.*;
import com.Baloot.User.UserServices;
import com.Baloot.User.Users;
import com.Baloot.util.PersianCalendar;
import com.Baloot.util.SessionBean;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ali-M
 */
@ManagedBean
public class OrderFollowUpForAdmin {

    private Order selectedOreder;
    private Design design;
    private Type type;
    private Paper paper;
    private Translate translate;
    private String unit;
    private Double off;
    private Double unitPrice;

    public OrderFollowUpForAdmin() {
        unit = "ok";
        off = 0.1;
        unitPrice = 10.0;
        number = 10;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getOff() {
        return off;
    }

    public void setOff(Double off) {
        this.off = off;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    private Integer number;

    public Translate getTranslate() {
        return translate;
    }

    public void setTranslate(Translate translate) {
        this.translate = translate;
    }
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Order getSelectedOreder() {
        return selectedOreder;
    }

    public void setSelectedOreder(Order selectedOreder) {
        this.selectedOreder = selectedOreder;
    }

    public Design getDesign() {
        return design;
    }

    public void setDesign(Design design) {
        this.design = design;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public List<Order> listOfOrdreForAdmin() {
        return OrderServices.AllOfOrder();
    }

    public void insertNewFactor() {

        if (selectedOreder != null) {

            Factor factor = new Factor();
            PersianCalendar pc = new PersianCalendar();
            String currentDate = pc.getIranianDateTime();
            factor.setDateTime(currentDate);
            Users user = selectedOreder.getUserId();
            factor.setUserId(user);
            factor.setSumPrice((unitPrice - (unitPrice * off)) * number);
            factor.setPFactor(true);
            factor.setPayCondition(0);
            factor.setOff(off);
            try {
                Integer factorId = FactorServices.insertRecordIntoTable(factor);
                factor.setId(factorId);

            } catch (SQLException ex) {
                Logger.getLogger(OrderFollowUpForAdmin.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
            try {
                FactorItem factorItem = new FactorItem();
                System.out.println(" facto ID : " + factor.getId());
                factorItem.setFactorId(factor);
                factorItem.setOrderId(selectedOreder.getId());
                factorItem.setUnit(unit);
                factorItem.setNumber(number);
                factorItem.setUnitPrice(unitPrice);
                OrderServices.updateCondition(selectedOreder.getId(), 1);
                FactorItemServices.insertRecordIntoTable(factorItem);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public String selectedOrderAction() {
        String pageOut = new String();
        String typeOforder;
        try {
            typeOforder = selectedOreder.getTableName();
            System.out.println("hereeeeeeeeeeee" + typeOforder);
            switch (typeOforder) {
                case "type":
                    type = TypeServices.getTypeById(selectedOreder.getTableId());
                    pageOut = "/pages/user/type1.xhtml";
                    break;
                case "design":
                    design = DesignServices.getDesignById(selectedOreder.getTableId());
                    pageOut = "/pages/admin/preparefactor.xhtml";
                    System.out.println("ok" + design.getEndDate() + pageOut);
                    break;
                case "translate":
                    translate = TranslateServices.getTranslateById(selectedOreder.getTableId());
                    pageOut = "/pages/user/translate1.xhtml";
                    break;
                case "paper":
                    paper = PaperServices.getPaperById(selectedOreder.getTableId());
                    pageOut = "/pages/user/article1.xhtml";
                    break;
            }
        } catch (Exception e) {
            System.out.println("here in exeption " + e.getMessage());
        }
        return pageOut;
    }
}
