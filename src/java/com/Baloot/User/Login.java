package com.Baloot.User;

import com.Baloot.util.SessionBean;
import java.io.Serializable;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
@ManagedBean
@SessionScoped
public class Login implements Serializable {
 
    private static final long serialVersionUID = 1094801825228386363L;
     
    private String pwd;
    private String msg;
    private String user;
 
    public String getPwd() {
        return pwd;
    }
 
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public String getUser() {
        return user;
    }
 
    public void setUser(String user) {
        this.user = user;
    }
 
    //validate login
    public String validateUsernamePassword() {
        Integer valid = UserServices.validate(user, pwd);
        switch (valid) {
            case 0 : {
                FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "نام کاربری و رمز عبور اشتباه",
                            "لطفا رمزعبور و نام کاربری درست را وارد کنید."));
                return "index";
            }
          case 1 : {
                HttpSession session = SessionBean.getSession();
                session.setAttribute("username", user);
                return "/pages/admin/contentManagement.xhtml?faces-redirect=true";
            }
            case 2 : {
                HttpSession session = SessionBean.getSession();
                session.setAttribute("username", user);
                return "/pages/user/followuporder.xhtml?faces-redirect=true";
            }
        }
        return "index";
    }
 
    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "/index.xhtml";
    }
}
