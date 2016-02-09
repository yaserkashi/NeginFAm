/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Message;

import com.Baloot.SmsMessage.SmsMessage;
import com.Baloot.TextSmsMessage.TextSmsMessage;
import com.Baloot.TextSmsMessage.TextSmsMessageServices;
import com.Baloot.User.UserServices;
import com.Baloot.User.Users;
import com.Baloot.util.PersianCalendar;
import com.Baloot.util.SendSMS;
import com.Baloot.util.Sendemail;
import java.sql.SQLException;
import java.util.List;
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
public class SendMessageFromAdmin {
    private String title;
    private String text;
    private List<Users> selected;
    private Boolean type;
    private String selectedStr;

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

    public List<Users> getSelected() {
        return selected;
    }
    
    public void setSelected(List<Users> selected) {
        this.selected = selected;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getSelectedStr() {
        if (selected != null)
            for (Users selected1 : selected) {
                selectedStr = selectedStr + selected1.getUsername() + ",";
            }
        return selectedStr;
    }

    public void setSelectedStr(String selectedStr) {
        this.selectedStr = selectedStr;
    }
           
    public void submit() {
        System.out.println("SUBMIT FUNCTION!");
        Users user = UserServices.getUserByUsername("fateme");
        if (type) {
            Sendemail sm = new Sendemail();
            System.out.println(user.getEmail()+title+text);
            sm.sendEmail(user.getEmail(), title, text);
            Message msg = new Message();
            msg.setTitle(title);
            msg.setText(text);
            PersianCalendar pc = new PersianCalendar();
            msg.setDateTime(pc.getIranianDateTime());
            msg.setRead(false);
            msg.setUserIdGet(user);
            msg.setUserIdSend(UserServices.getUserByUsername("admin"));

            try {
                MessageServices.insertRecordIntoTable(msg);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(SendMessageFromAdmin.class.getName() + "پیام ارسال شد."));
            } catch (SQLException ex) {
                Logger.getLogger(SendMessageFromAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            SendSMS ss = new SendSMS();
            ss.send(user.getPhoneNum(), text);
            
            TextSmsMessage tsm = new TextSmsMessage();
            tsm.setText(text);
            
            try {
                int id = TextSmsMessageServices.insertRecordIntoTable(tsm);
                tsm.setId(id);
                SmsMessage sms = new SmsMessage();
                sms.setTextId(tsm);
                sms.setUserId(user);
                sms.setCondition("sent");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(SendMessageFromAdmin.class.getName() + "پیام ارسال شد."));
            } catch (SQLException ex) {
                Logger.getLogger(SendMessageFromAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
