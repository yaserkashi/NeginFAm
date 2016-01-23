/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Factor;

import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author FK
 */
@ManagedBean
public class AdminFinancialManagement {
    private List<ReportList> list = FactorServices.getReportList();
    private ReportList selected;

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
