/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.User;

import com.Baloot.util.CaptchaUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.validation.constraints.Size;
import nl.captcha.Captcha;
import org.hibernate.validator.constraints.Email;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


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
    private String partnerCode;
    private String secCode;
    private boolean lowCheck;
    private Captcha captcha;
    private StreamedContent image;
    private String answer;
    
    @PostConstruct
    public void init() {
        generateNewCaptcha();
    }
   
    public Captcha getCaptcha() {
        return captcha;
    }
    public StreamedContent getImage() {
        return image;
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

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
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
    
    public void generateNewCaptcha() {
        try {
            captcha = CaptchaUtil.generateNewCaptcha();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(), "png", os);
            image = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png"); 
            answer = captcha.getAnswer();
            System.out.println("answer = " + answer);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
    
    public void submit(){
        System.out.println("Submit Function");
        if (!answer.equals(secCode)) {
            System.out.println(answer + " " + secCode);
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
            } catch (SQLException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
     
    }
}
