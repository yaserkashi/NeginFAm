/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.User;

import botdetect.web.jsf.JsfCaptcha;
import com.Baloot.Order.Order;
import com.Baloot.util.CaptchaUtil;
import com.Baloot.util.SessionBean;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import nl.captcha.Captcha;
import org.hibernate.validator.constraints.Email;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Yaser
 */
@ManagedBean

public final class SignUp {

    @Size(min = 4, message = "نام کاربری نباید کمتر از چهار حرف باشد.")
    private String userName;
    @Size(min = 6, message = "رمز عبور نباید کمتر از شش حرف باشد.")
    private String password;
    private String rePassword;
    @Email(message = "رایانامه باید معتبر باشد.")
    private String email;
    private String name;
    private String lastname;
    @Digits(integer = 11, fraction = 0, message = "تلفن باید یازده رقم باشد.")
    private String mobile;
    private String introduction;
    private String secCode;
    @AssertTrue(message = "باید قوانین سایت را مطالعه و قبول کنید.")
    private boolean lowCheck;
//    private JsfCaptcha captcha;
    private String answer;
    private Captcha captcha;
    private StreamedContent image;

    public SignUp() {
//        generateNewCaptcha();
    }

    public StreamedContent getImage() {
        generateNewCaptcha();
        return image;
    }

    public void setImage(StreamedContent image) {
        this.image = image;
    }

    public Captcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Captcha captcha) {
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

    public String submit() {
        System.out.println("Submit Function");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        String a = (String) session.getAttribute("ans");
        System.out.println("a is "+a+"seccode is "+secCode);
        if (!(a.equals(secCode))) {
            System.out.println("eshtebah code amniati");
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("signup.xhtml?faces-redirect=true");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("کدامنیتی را اشتباه وارد کرده اید!"));
                return "/";
            } catch (IOException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (UserServices.isUsernameUsed(userName)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("نام کاربری که انتخاب کرده اید قبلا استفاده شده است!"));
        } else if (UserServices.isEmailUsed(email)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("کاربری با این رایانامه در سایت ثبت نام کرده است!"));
        } else {
            try {
                System.out.println("shoroe sabt");
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
//                HttpSession session = SessionBean.getSession();
                session.setAttribute("username", userName);                
                 FacesContext.getCurrentInstance().getExternalContext().redirect("signupsucces.xhtml");
//                 signUpLogin(userName, password);
                return "/pages/user/user.xhtml";
            } catch (SQLException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("eroor dar sabt");
            } catch (IOException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "/";
    }
    
    public void signUpLogin(String user,String pwd) {
        Integer valid = UserServices.validate(user, pwd);
        switch (valid) {
            case 0 : {
                FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "نام کاربری و رمز عبور اشتباه",
                            "لطفا رمزعبور و نام کاربری درست را وارد کنید."));
                
            }
          case 1 : {
                HttpSession session = SessionBean.getSession();
                session.setAttribute("username", user);
              
            }
            case 2 : {
                HttpSession session = SessionBean.getSession();
                session.setAttribute("username", user);
              
            }
        }
       
    }

    public void generateNewCaptcha() {
        try {
            captcha = CaptchaUtil.generateNewCaptcha();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(), "png", os);
            image = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");
            HttpSession session = SessionBean.getSession();
            answer = captcha.getAnswer();
            session.setAttribute("ans", answer);            
            System.out.println("in generate answer =.... " + answer);
            FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("image");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void valid() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        String a = (String) session.getAttribute("ans");
        System.out.println(a);
        System.out.println(answer + " " + secCode);
        if (a.equals(secCode)) {
            System.out.println(a + "...................+++ " + secCode);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("کدامنیتی را درست وارد کرده اید!"));
        } else {
            try {               
                FacesContext.getCurrentInstance().getExternalContext().redirect("signup.xhtml?faces-redirect=true");
                 FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("کدامنیتی را درستاشتباه وارد کرده اید!"));
            } catch (IOException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
