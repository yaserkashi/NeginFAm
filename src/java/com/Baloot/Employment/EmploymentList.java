/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Employment;

import java.util.List;
import javax.faces.bean.ManagedBean;

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
        return selected;
    }

    public void setSelected(Employment selected) {
        this.selected = selected;
    }
    
    
}
