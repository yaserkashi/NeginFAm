/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Message;

import com.Baloot.Order.Order;
import com.Baloot.SmsMessage.SmsMessage;
import com.Baloot.SmsMessage.SmsMessageServices;
import com.Baloot.TextSmsMessage.TextSmsMessage;
import com.Baloot.TextSmsMessage.TextSmsMessageServices;
import com.Baloot.User.UserServices;
import com.Baloot.User.Users;
import com.Baloot.util.PersianCalendar;
import com.Baloot.util.SendSMS;
import com.Baloot.util.Sendemail;
import com.Baloot.util.SessionBean;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
    private String selectedStr = "";
    private Message selsectMessage;

    public Message getSelsectMessage() {
        if (selsectMessage == null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
            selsectMessage = (Message) session.getAttribute("selsectMessage");
        }
        return selsectMessage;
    }

    public void setSelsectMessage(Message selsectMessage) {
        System.out.println("SET MESSAGE");
        HttpSession session = SessionBean.getSession();
        session.setAttribute("selsectMessage", selsectMessage);
        this.selsectMessage = selsectMessage;
    }

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
        System.out.println("hereeeeee"+selected.toString());
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(":selectedStr");
        this.selected = selected;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getSelectedStr() {
        if (selected != null) {
            for (Users selected1 : selected) {
                selectedStr = selectedStr + selected1.getUsername() + ",";
            }
        }
        return selectedStr;
    }

    public void setSelectedStr(String selectedStr) {
        this.selectedStr = selectedStr;
    }

    public List<String> getEmails(List<Users> users) {
        List<String> emails = new ArrayList<>();
        for (Users u : users) {
            emails.add(u.getEmail());
        }
        return emails;
    }

    public List<String> getMobiles(List<Users> users) {
        List<String> mobiles = new ArrayList<>();
        for (Users u : users) {
            mobiles.add(u.getPhoneNum());
        }
        return mobiles;
    }

    public void submit() {
        System.out.println("SUBMIT FUNCTION!");
        if (type) {
            Sendemail sm = new Sendemail();
            sm.groupSendEmail(getEmails(selected), title, text);
            List<Message> messages = new ArrayList<>();
            for (Users user : selected) {
                Message msg = new Message();
                msg.setTitle(title);
                msg.setText(text);
                PersianCalendar pc = new PersianCalendar();
                msg.setDateTime(pc.getIranianDateTime());
                msg.setRead(false);
                msg.setUserIdGet(user);
                msg.setUserIdSend(UserServices.getUserByUsername("admin"));
                messages.add(msg);
            }

            try {
                MessageServices.insertRecordsIntoTable(messages);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("پیام ها ارسال شد."));
            } catch (SQLException ex) {
                Logger.getLogger(SendMessageFromAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            SendSMS ss = new SendSMS();
            ss.send(getMobiles(selected), text);

            TextSmsMessage tsm = new TextSmsMessage();
            tsm.setText(text);

            try {
                int id = TextSmsMessageServices.insertRecordIntoTable(tsm);
                tsm.setId(id);
                List<SmsMessage> list = new ArrayList<>();
                for (Users user : selected) {
                    SmsMessage sms = new SmsMessage();
                    sms.setTextId(tsm);
                    sms.setUserId(user);
                    sms.setCondition("sent");
                    list.add(sms);
                }
                SmsMessageServices.insertRecordsIntoTable(list);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("پیام ها ارسال شد."));
            } catch (SQLException ex) {
                Logger.getLogger(SendMessageFromAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void reply() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        selsectMessage = (Message) session.getAttribute("selsectMessage");

        if (selsectMessage == null) {
            System.out.println("is NULLLLLLLLLLLL");
            return;
        }
        System.out.println(SendMessageFromAdmin.class.getName() + "Reply FUNCTION!");
        Users to = selsectMessage.getUserIdSend();
        if (type) {
            Sendemail sm = new Sendemail();
            sm.sendEmail(to.getEmail(), title, text);
            Message msg = new Message();
            msg.setTitle(title);
            msg.setText(text);
            PersianCalendar pc = new PersianCalendar();
            msg.setDateTime(pc.getIranianDateTime());
            msg.setRead(false);
            msg.setUserIdGet(to);
            msg.setUserIdSend(UserServices.getUserByUsername("admin"));

            try {
                MessageServices.insertRecordIntoTable(msg);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("پیام ارسال شد."));
            } catch (SQLException ex) {
                Logger.getLogger(SendMessageFromAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            SendSMS ss = new SendSMS();
            ss.send(to.getPhoneNum(), text);

            TextSmsMessage tsm = new TextSmsMessage();
            tsm.setText(text);

            try {
                int id = TextSmsMessageServices.insertRecordIntoTable(tsm);
                tsm.setId(id);
                SmsMessage sms = new SmsMessage();
                sms.setTextId(tsm);
                sms.setUserId(to);
                sms.setCondition("sent");
                SmsMessageServices.insertRecordIntoTable(sms);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("پیام ارسال شد."));
            } catch (SQLException ex) {
                Logger.getLogger(SendMessageFromAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
