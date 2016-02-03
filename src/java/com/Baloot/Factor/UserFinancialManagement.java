/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Factor;

import com.Baloot.User.UserServices;
import com.Baloot.util.SessionBean;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author FK
 */
@ManagedBean
public class UserFinancialManagement {
    private int id = UserServices.getUserByUsername(SessionBean.getUserName()).getId();
    private List<ReportList> list = FactorServices.getReportListById(id);
    private ReportList selected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ReportList> getList() {
        return list;
    }

    public void setList(List<ReportList> list) {
        this.list = list;
    }

    public ReportList getSelected() {
        return selected;
    }

    public void setSelected(ReportList selected) {
        this.selected = selected;
    }
        
    
}
