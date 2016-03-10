/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.SmsMessage;

import com.Baloot.Enum.StepsOfOrder;
import com.Baloot.TextSmsMessage.TextSmsMessage;
import com.Baloot.TextSmsMessage.TextSmsMessageServices;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;

/**
 *کلاس برای صفحه پیام های پیش فرض
 * @author mohammad
 */
public class DefulteSMS {
public DefulteSMS()
{
    try {
        message1=TextSmsMessageServices.getDefultSMS(StepsOfOrder.registrationFactor.ordinal());
        message2=TextSmsMessageServices.getDefultSMS(StepsOfOrder.EndOrder.ordinal());
    } catch (Exception e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("پیام پیش فرضی وجود ندارد"));
    }
}
private TextSmsMessage message1;
private TextSmsMessage message2;

    public TextSmsMessage getMessage1() {
        return message1;
    }

    public void setMessage1(TextSmsMessage message1) {
        this.message1 = message1;
    }

    public TextSmsMessage getMessage2() {
        return message2;
    }

    public void setMessage2(TextSmsMessage message2) {
        this.message2 = message2;
    }
/**
 * تابع ذخیره ویرایش پیام های پیش فرض
 */
    public void saveChange() {
        if(message1.getText().length()==0||message2.getText().length()==0)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("متن پیام ها نباید خالی باشد"));
        }else
        {
            try {
                  TextSmsMessageServices.updateDefultSMS(message1);
        TextSmsMessageServices.updateDefultSMS(message2);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("پیام ها با موفقیت ویرایش شد"));
            } catch (Exception e) {
            }
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ویرایش پیام ها با مشکل مواجه شده لطفا صفحه را رفرش کنید و دوباره سعی کنید"));
        }
            

    }

}
