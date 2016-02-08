/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Order;

import com.Baloot.Design.*;
import com.Baloot.Factor.Factor;
import com.Baloot.FactorItem.FactorItem;
import com.Baloot.FactorItem.FactorItemServices;
import com.Baloot.Paper.*;
import com.Baloot.Translate.*;
import com.Baloot.Type.*;
import com.Baloot.User.UserServices;
import com.Baloot.util.SessionBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ali-M
 */
@ManagedBean
public class OrderFollowUpForUser {

    public OrderFollowUpForUser() {
    }

    private Order selectedOreder;
    private Design design;
    private Type type;
    private Paper paper;
    private Translate translate;
    private FactorItem factorItemForSectedOrder;

    public FactorItem getFactorItemForSectedOrder() {
        return factorItemForSectedOrder;
    }

    public void setFactorItemForSectedOrder(FactorItem factorItemForSectedOrder) {
        this.factorItemForSectedOrder = factorItemForSectedOrder;
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

    public List<Order> listOfOrdreForUser() {
        try {
            userId = UserServices.getUserByUsername(SessionBean.getUserName()).getId();

            List<Order> list = OrderServices.listOfOrderForUser(userId);

            return list;

        } catch (Exception e) {

            List<Order> list = new ArrayList<>();
            System.out.println(e.getMessage());
            return list;
        }

    }

    public String selectedOrderAction() {
        String pageOut = new String();
        String typeOforder ;
        try {
            typeOforder = selectedOreder.getTableName();
          
            switch (typeOforder) {
                case "type":
                    type = TypeServices.getTypeById(selectedOreder.getTableId());
                    pageOut = "/pages/user/type1.xhtml";
                    break;
                case "design":
                    design = DesignServices.getDesignById(selectedOreder.getTableId());
                    pageOut = "/pages/user/design1.xhtml";
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

            System.out.println("in end " + pageOut);
            if (pageOut.isEmpty()) {
                pageOut = "/pages/user/" + selectedOreder.getTableName() + "1.xhtml";
            }
        } catch (Exception e) {
            System.out.println("here in exeption " + e.getMessage());
        }
        return pageOut;
    }
    /**
     *<b> تابع برای بدست آوردن فاکتور برای</b>
     *<b> سفارش انتخاب شده توسط کابر</b>
     */
    public void showFactorForSelectedOrder() {
        if (selectedOreder != null) {
           factorItemForSectedOrder = FactorItemServices.selectFactorItemByOrderId(selectedOreder.getId());
        }
    }

    public String whichPage() {
        
        try {
            if (selectedOreder !=null) {
                return "/pages/user/" + selectedOreder.getTableName() + "1.xhtml";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/user/design1.xhtml";
    }
}