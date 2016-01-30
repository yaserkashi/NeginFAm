/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.User;

import com.Baloot.util.SessionBean;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;

/**
 *
 * @author FK
 */
@ManagedBean
public class ChangePass {
    private String currentPass;
    @Size(min = 6,message = "رمز عبور نباید کمتر از شش حرف باشد.")
    private String pass;

    public String getCurrentPass() {
        return currentPass;
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public void submit() {
        System.out.println(ChangePass.class.getName() + ":Submit Function!");
        Users user = UserServices.getUserByUsername(SessionBean.getUserName());
        if (!currentPass.equals(user.getPasword())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("رمز عبور فعلی را اشتباه وارد کرده اید!"));
        } else {
            try {
                UserServices.changePassword(user.getId(), pass);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("رمز عبور شما با موفقیت تغییر کرد."));
            } catch (SQLException ex) {
                Logger.getLogger(ChangePass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
