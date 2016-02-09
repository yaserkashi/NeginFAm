/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.User;

import botdetect.web.jsf.JsfCaptcha;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;


/**
 *
 * @author Yaser
 */
@ManagedBean
public class SignUp {
    @Size(min = 4,message = "نام کاربری نباید کمتر از چهار حرف باشد.")
    private String userName;
    @Size(min = 6,message = "رمز عبور نباید کمتر از شش حرف باشد.")
    private String password;
    private String rePassword;
    @Email(message = "رایانامه باید معتبر باشد.")
    private String email;
    private String name;
    private String lastname;
    private String mobile;
    private String introduction;
    private String secCode;
    private boolean lowCheck;
    private JsfCaptcha captcha;

    public JsfCaptcha getCaptcha() {
        return captcha;
    }
    
    public SignUp() {
    }

    public void setCaptcha(JsfCaptcha captcha) {
        this.captcha = captcha;
    }
    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public boolean isLowCheck() {
        return lowCheck;
    }

    public void setLowCheck(boolean lowCheck) {
        this.lowCheck = lowCheck;
    }
    public String submit(){
        System.out.println("Submit Function");
        if (!captcha.validate(secCode)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("کدامنیتی را اشتباه وارد کرده اید!"));
        } else if (UserServices.isUsernameUsed(userName)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("نام کاربری که انتخاب کرده اید قبلا استفاده شده است!"));
        } else if (UserServices.isEmailUsed(email)) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("کاربری با این رایانامه در سایت ثبت نام کرده است!"));
        } else 
            try {
                Users user = new Users();
                user.setName(name);
                user.setFamily(lastname);
                user.setEmail(email);
                user.setUsername(userName);
                user.setPasword(password);
                user.setPhoneNum(mobile);
                UserServices.insertRecordIntoTable(user);
                FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("ثبت نام شما با موفقیت انجام شد."));
                return "/pages/user/user.xhtml";
            } catch (SQLException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
     return "/";
    }
}
