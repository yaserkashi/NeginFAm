/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Order;

import com.Baloot.Design.*;
import com.Baloot.Enum.StepsOfOrder;
import com.Baloot.Factor.Factor;
import com.Baloot.Factor.FactorServices;
import com.Baloot.FactorItem.FactorItem;
import com.Baloot.FactorItem.FactorItemServices;
import com.Baloot.Paper.*;
import com.Baloot.Translate.*;
import com.Baloot.Type.*;
import com.Baloot.User.UserServices;
import com.Baloot.util.PayLine;
import com.Baloot.util.SessionBean;
import com.sun.xml.fastinfoset.EncodingConstants;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ali-M
 */
@ManagedBean
@SessionScoped
public class OrderFollowUpForUser {

    public OrderFollowUpForUser() {
    }

    private Order selectedOreder;
    private Design design;
    private Type type;
    private Paper paper;
    private Translate translate;
    private FactorItem factorItemForSelectedOrder;

    public FactorItem getFactorItemForSelectedOrder() {
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

//    public String selectedOrderAction() {
//        String pageOut = new String();
//        String typeOforder;
//        try {
//            typeOforder = selectedOreder.getTableName();
//
//            switch (typeOforder) {
//                case "type":
//                    type = TypeServices.getTypeById(selectedOreder.getTableId());
//                    pageOut = "/pages/user/type1.xhtml";
//                    break;
//                case "design":
//                    design = DesignServices.getDesignById(selectedOreder.getTableId());
//                    pageOut = "/pages/user/design1.xhtml";
//                    System.out.println("ok" + design.getEndDate() + pageOut);
//                    break;
//                case "translate":
//                    translate = TranslateServices.getTranslateById(selectedOreder.getTableId());
//                    pageOut = "/pages/user/translate1.xhtml";
//                    break;
//                case "paper":
//                    paper = PaperServices.getPaperById(selectedOreder.getTableId());
//                    pageOut = "/pages/user/paper1.xhtml";
//                    break;
//            }
//
//            System.out.println("in end " + pageOut);
//            if (pageOut.isEmpty()) {
//                pageOut = "/pages/user/" + selectedOreder.getTableName() + "1.xhtml";
//            }
//        } catch (Exception e) {
//            System.out.println("here in exeption " + e.getMessage());
//        }
//        return pageOut;
//    }
    /**
     * <b> تابع برای بدست آوردن فاکتور برای</b>
     * <b> سفارش انتخاب شده توسط کابر</b>
     *
     * @return page factor
     */
    public String showFactorForSelectedOrder() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String msg = (String) map.get("msg");
        int id = Integer.valueOf(msg);
        System.out.println("ojhgsahIASGKFHDJ" + id);
        selectedOreder = OrderServices.selectOrderById(id);
        factorItemForSelectedOrder = FactorItemServices.selectFactorItemByOrderId(selectedOreder.getId());
        System.out.println("herrrrrrrrrrrrrrrrrreeeeeeeeeeeee" + factorItemForSelectedOrder.getFactorId().getSumPrice());
        return "/pages/user/factor.xhtml?faces-redirect=true";
    }

    public void cancelOrder() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Map map = context.getExternalContext().getRequestParameterMap();
            String msg = (String) map.get("msg");
            int id = Integer.valueOf(msg);
            selectedOreder = OrderServices.selectOrderById(id);
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
            FacesContext context = FacesContext.getCurrentInstance();
            Map map = context.getExternalContext().getRequestParameterMap();
            String msg = (String) map.get("msg");
            int id = Integer.valueOf(msg);
            FactorItem item = FactorItemServices.selectFactorItemByOrderId(id);

            try {
                PayLine pay = new PayLine();

                String result = pay.Send("http://payline.ir/payment-test/gateway-send", "adxcv-zzadq-polkjsad-opp13opoz-1sdf455aadzmck1244567", item.getFactorId().getSumPrice(), "http://localhost:12841/neginLast/pages/user/resultPayFactor.xhtml");
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
            FacesContext context = FacesContext.getCurrentInstance();
            Map map = context.getExternalContext().getRequestParameterMap();
            String msg = (String) map.get("msg");
            int id = Integer.valueOf(msg);
            System.out.println("id" + id);
            selectedOreder = OrderServices.selectOrderById(id);
            OrderServices.updateCondition(selectedOreder.getId(), StepsOfOrder.dissuasion.ordinal());
            System.out.println("selected ");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
