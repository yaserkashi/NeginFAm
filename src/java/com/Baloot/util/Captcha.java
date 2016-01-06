package com.Baloot.util;


import botdetect.web.jsf.JsfCaptcha;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="captcha")
@RequestScoped
public class Captcha {

    private String captchaCode;
    private JsfCaptcha captcha;
    private boolean correctLabelVisible, incorrectLabelVisible;
    
    public Captcha() {
    }
    
    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public JsfCaptcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(JsfCaptcha captcha) {
        this.captcha = captcha;
    }

    public boolean isCorrectLabelVisible() {
        return correctLabelVisible;
    }

    public boolean isIncorrectLabelVisible() {
        return incorrectLabelVisible;
    }

    
    public void validate(){
        // validate the Captcha to check we're not dealing with a bot
        boolean isHuman = captcha.validate(captchaCode);
        if (isHuman) {
            correctLabelVisible = true;
            incorrectLabelVisible = false;
        } else {
            correctLabelVisible = false;
            incorrectLabelVisible = true;
        }
        this.captchaCode = "";
    }
    
}