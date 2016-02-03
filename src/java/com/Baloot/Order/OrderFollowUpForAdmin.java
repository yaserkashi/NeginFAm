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
/**
 *
 * @author Ali-M
 */

public class OrderFollowUpForAdmin {
    
    private Order  selectedOreder;
    private Design design;
    private Type type;
    private Paper paper;
    private Translate translate;
    private String unit;
    private Double off;
    private Double unitPrice;

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
    public List<Order> ListOfOrdreForUser()
    {
    return OrderServices.AllOfOrder();
    }
    public void InsertNewFactor()
    {
    if(selectedOreder!=null)
    {
        Factor factor=new Factor();
         PersianCalendar pc = new PersianCalendar();
        String currentDate = pc.getIranianDateTime();
        factor.setDateTime(currentDate);
        Users user = UserServices.getUserByUsername(SessionBean.getUserName());
        design.setUserId(user);
        factor.setUserId(user);
        factor.setSumPrice((unitPrice-(unitPrice*off))*number);
        factor.setPFactor(true);
        factor.setPayCondition(0);
        factor.setOff(off);
        try {
            Integer factorId=FactorServices.insertRecordIntoTable(factor);
            factor.setId(factorId);
            FactorItem factorItem=new FactorItem();
            factorItem.setFactorId(factor);
            factorItem.setOrderId(selectedOreder.getId());
            factorItem.setUnit(unit);
            factorItem.setNumber(number);
            factorItem.setUnitPrice(unitPrice);
            FactorItemServices.insertRecordIntoTable(factorItem);
            OrderServices.updateCondition(selectedOreder.getId(), 1);
        } catch (SQLException ex) {
            Logger.getLogger(OrderFollowUpForAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    }
   public void SelectedOrderAction()
   {
  String typeOforder=selectedOreder.getTableName();
 
       switch (typeOforder)
       {
                case "type":
         type=  TypeServices.getTypeById(selectedOreder.getTableId());
        break;
                case "design":
          design=DesignServices.getDesignById(selectedOreder.getTableId());
        break;
                 case "translate":
 translate=TranslateServices.getTranslateById(selectedOreder.getTableId());
        break;
                 case "paper":
         paper=PaperServices.getPaperById(selectedOreder.getTableId());
        break;
       }
             
   }
}
