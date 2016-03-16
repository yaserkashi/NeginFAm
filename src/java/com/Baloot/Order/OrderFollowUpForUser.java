/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Order;

import com.Baloot.Design.*;
import com.Baloot.Enum.StepsOfOrder;
import com.Baloot.Factor.FactorServices;
import com.Baloot.FactorItem.FactorItem;
import com.Baloot.FactorItem.FactorItemServices;
import com.Baloot.Paper.*;
import com.Baloot.Translate.*;
import com.Baloot.Type.*;
import com.Baloot.User.UserServices;
import com.Baloot.util.PayLine;
import com.Baloot.util.SessionBean;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ali-M
 */
@ManagedBean
@ViewScoped
public class OrderFollowUpForUser {

    public OrderFollowUpForUser() {
        System.out.println("Creatde");
        try {
            userId = UserServices.getUserByUsername(SessionBean.getUserName()).getId();
            List<Order> list = OrderServices.listOfOrderForUser(userId);
            listOfOrdreForUser = list;
        } catch (Exception e) {
            List<Order> list = new ArrayList<>();
            System.out.println(e.getMessage());
            listOfOrdreForUser = list;
        }
    }
    private List<Order> listOfOrdreForUser;

    public List<Order> getListOfOrdreForUser() {
        return listOfOrdreForUser;
    }

    public void setListOfOrdreForUser(List<Order> listOfOrdreForUser) {
        this.listOfOrdreForUser = listOfOrdreForUser;
    }
    private Order selectedOreder;
    private Design design;
    private Type type;
    private Paper paper;
    private Translate translate;
    private FactorItem factorItemForSelectedOrder;

    public FactorItem getFactorItemForSelectedOrder() {
        if (factorItemForSelectedOrder == null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            factorItemForSelectedOrder = (FactorItem) session.getAttribute("factorItemForSelectedOrder");
        }
        return factorItemForSelectedOrder;
    }

    public void setFactorItemForSelectedOrder(FactorItem factorItemForSelectedOrder) {
        this.factorItemForSelectedOrder = factorItemForSelectedOrder;
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

    /**
     * <b> تابع برای بدست آوردن فاکتور برای</b>
     * <b> سفارش انتخاب شده توسط کابر</b>
     *
     * @return page factor
     */
    public void showFactorForSelectedOrder() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            selectedOreder = (Order) session.getAttribute("selectedOreder");
            factorItemForSelectedOrder = FactorItemServices.selectFactorItemByOrderId(selectedOreder.getId());
            HttpSession session1 = SessionBean.getSession();
            session1.setAttribute("factorItemForSelectedOrder", factorItemForSelectedOrder);
            FacesContext.getCurrentInstance().getExternalContext().redirect("factor.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(OrderFollowUpForUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelOrder() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            selectedOreder = (Order) session.getAttribute("selectedOreder");
            OrderServices.updateCondition(selectedOreder.getId(), StepsOfOrder.dissuasion.ordinal());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * تابع است که به کلید پرداخت فاکتور وصل میشود
     *
     * @return لینک پرداخت
     */
    public String payLink() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            selectedOreder = (Order) session.getAttribute("selectedOreder");
            int id = selectedOreder.getId();
            FactorItem item = FactorItemServices.selectFactorItemByOrderId(id);
            System.out.println(item.getNumber());
            try {
                PayLine pay = new PayLine();
                System.out.println("HERE IS PAY");
                        String result = pay.Send("http://payline.ir/payment-test/gateway-send", "adxcv-zzadq-polkjsad-opp13opoz-1sdf455aadzmck1244567", item.getFactorId().getSumPrice(), "http://localhost:12841/NeginFAm4/pages/user/resultPayFactor.xhtml");
                System.out.println(result);
                if (Integer.parseInt(result) > 0) {
                    OrderServices.InsertGetId(id, Integer.parseInt(result));                  
                    return "http://payline.ir/payment-test/gateway-" + result;
                }
            } catch (Exception e) {
                System.out.println("Error -------->>>>>>> " + e.getMessage());
            }

            return "";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * تابع چک پرداخت آنلاین که در لود صفحه باید صدا زده شود
     */
    public String checkPay() throws SQLException {
        try {
            String id_get = null;
            String trans_id = null;
            String result;
            PayLine pay = new PayLine();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            if (request != null) {
                id_get = request.getParameter("id_get");
                trans_id = request.getParameter("trans_id");
                Order order = OrderServices.selectOrderByGetId(Integer.parseInt(id_get));
                if (order != null) {
                    result = pay.Get("http://payline.ir/payment-test/gateway-result-second", "adxcv-zzadq-polkjsad-opp13opoz-1sdf455aadzmck1244567", trans_id, id_get);
                    if (Integer.parseInt(result) == 1) {
                        FactorItem factorItem = FactorItemServices.selectFactorItemByOrderId(order.getId());
                        FactorServices.payFactor(factorItem.getFactorId().getId(), 1);
                        OrderServices.updateCondition(order.getId(), StepsOfOrder.payFactor.ordinal());
                        return "موفق اومد";
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("reror------>>>" + e.getMessage());
        }

        return "ناموفق بود";

    }

    public void yaser() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            selectedOreder = (Order) session.getAttribute("selectedOreder");
            OrderServices.updateCondition(selectedOreder.getId(), StepsOfOrder.dissuasion.ordinal());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public void download() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            selectedOreder = (Order) session.getAttribute("selectedOreder");            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
