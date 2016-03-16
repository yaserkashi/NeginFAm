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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ali-M
 */
@ManagedBean
@ViewScoped
public class OrderFollowUpForAdmin {

    private List<Order> listOfOrdreForAdmin = OrderServices.AllOfOrder();

    public List<Order> getListOfOrdreForAdmin() {
        return listOfOrdreForAdmin;
    }

    public void setListOfOrdreForAdmin(List<Order> listOfOrdreForAdmin) {
        this.listOfOrdreForAdmin = listOfOrdreForAdmin;
    }
    private Order selectedOreder;
    private Design design;
    private Type type;
    private Paper paper;
    private Translate translate;
    private String unit;
    private Double off;
    private Double unitPrice;
    private Integer number;
    private Double sumPrice;
    private Double finalPrice;

    public OrderFollowUpForAdmin() {
        System.out.println("OrderFollowUpForAdmin CReated");
    }

    public Double getSumPrice() {
        if (unitPrice != null && number != null) {
            return sumPrice = (unitPrice * number);
        }
        return sumPrice;
    }

    public Double getFinalPrice() {
        if (!(unitPrice == null || number == null || off == null)) {
            return finalPrice = (unitPrice - (unitPrice * (off / 100))) * number;
        }
        return finalPrice;
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
        if (selectedOreder == null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            selectedOreder = (Order) session.getAttribute("selectedOreder");
            selectedOrderAction();
        }
        return selectedOreder;
    }

    public void setSelectedOreder(Order selectedOreder) {
        HttpSession session = SessionBean.getSession();
        session.setAttribute("selectedOreder", selectedOreder);
        this.selectedOreder = selectedOreder;
        selectedOrderAction();
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

    public void confirmationPayFactor() throws SQLException {
        OrderServices.updateCondition(selectedOreder.getId(), StepsOfOrder.ConfirmationPayFactor.ordinal());
    }

    public void insertNewFactor() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        selectedOreder = (Order) session.getAttribute("selectedOreder");
        System.out.println("here in insert factor " + selectedOreder);
        if (selectedOreder != null && selectedOreder.getCondition() == 0) {
            Factor factor = new Factor();
            PersianCalendar pc = new PersianCalendar();
            String currentDate = pc.getIranianDateTime();
            factor.setDateTime(currentDate);
            Users user = selectedOreder.getUserId();
            factor.setUserId(user);
            factor.setSumPrice((unitPrice - (unitPrice * (off / 100))) * number);
            factor.setPFactor(true);
            factor.setPayCondition(0);
            if (off != null) {
                factor.setOff(off);
            }
            try {
                Integer factorId = FactorServices.insertRecordIntoTable(factor);
                factor.setId(factorId);
                FactorItem factorItem = new FactorItem();
                System.out.println(" facto ID : " + factor.getId());
                factorItem.setFactorId(factor);
                factorItem.setOrderId(selectedOreder.getId());
                factorItem.setUnit(unit);
                factorItem.setNumber(number);
                factorItem.setUnitPrice(unitPrice);
                OrderServices.updateCondition(selectedOreder.getId(), StepsOfOrder.registrationFactor.ordinal());
                FactorItemServices.insertRecordIntoTable(factorItem);
                FacesContext.getCurrentInstance().getExternalContext().redirect("trackOrder.xhtml");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                Logger.getLogger(OrderFollowUpForAdmin.class.getName()).log(Level.SEVERE, null, e);

            } catch (IOException ex) {
                Logger.getLogger(OrderFollowUpForAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void selectedOrderAction() {
        String typeOforder;
        try {
            typeOforder = selectedOreder.getTableName();
            switch (typeOforder) {
                case "type":
                    type = TypeServices.getTypeById(selectedOreder.getTableId());
                    break;
                case "design":
                    design = DesignServices.getDesignById(selectedOreder.getTableId());
                    break;
                case "translate":
                    translate = TranslateServices.getTranslateById(selectedOreder.getTableId());
                    break;
                case "paper":
                    paper = PaperServices.getPaperById(selectedOreder.getTableId());
                    break;
            }
        } catch (Exception e) {
            System.out.println("here in exeption " + e.getMessage());
        }
    }

    public void showFactorForSelectedOrder() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            selectedOreder = (Order) session.getAttribute("selectedOreder");
            FacesContext.getCurrentInstance().getExternalContext().redirect("factor.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            selectedOreder = (Order) session.getAttribute("selectedOreder");
            OrderServices.updateCondition(selectedOreder.getId(), StepsOfOrder.dissuasion.ordinal());
            FacesContext.getCurrentInstance().getExternalContext().redirect("trackOrder.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadFile() {
        try {
            String fileName="a.jpg";
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            selectedOreder = (Order) session.getAttribute("selectedOreder");            
            OrderServices.insertFinalFile(selectedOreder.getId(),fileName);
            OrderServices.updateCondition(selectedOreder.getId(), StepsOfOrder.EndOrder.ordinal());
        } catch (SQLException ex) {
            Logger.getLogger(OrderFollowUpForAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
