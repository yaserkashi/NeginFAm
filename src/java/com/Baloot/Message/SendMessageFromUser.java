/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Message;

import com.Baloot.User.UserServices;
import com.Baloot.util.PersianCalendar;
import com.Baloot.util.SessionBean;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author FK
 */
@ManagedBean
public class SendMessageFromUser {
    private String title;
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public void submit() {
        Message msg = new Message();
        
        msg.setTitle(title);
        msg.setText(text);
        PersianCalendar pc = new PersianCalendar();
        msg.setDateTime(pc.getIranianDateTime());
        msg.setRead(false);
        msg.setUserIdGet(UserServices.getUserByUsername("admin"));
        msg.setUserIdSend(UserServices.getUserByUsername(SessionBean.getUserName()));
        
        try {
            MessageServices.insertRecordIntoTable(msg);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("پیام ارسال شد."));
        } catch (SQLException ex) {
            Logger.getLogger(SendMessageFromUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
