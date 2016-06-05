/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Employment;

import com.Baloot.Order.Order;
import com.Baloot.util.SessionBean;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FK
 */
@ManagedBean
public class EmploymentList {
    List<Employment> list = EmploymentServices.getALLEmployments();
    Employment selected;

    public List<Employment> getList() {
        return list;
    }

    public void setList(List<Employment> list) {
        this.list = list;
    }

    public Employment getSelected() {
        if (selected == null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            selected = (Employment) session.getAttribute("selected");        
        }
        return selected;
    }

    public void setSelected(Employment selected) {
         HttpSession session = SessionBean.getSession();
        session.setAttribute("selected", selected);
        this.selected = selected;
    }
    
    
}
