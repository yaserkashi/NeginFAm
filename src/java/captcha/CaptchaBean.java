/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package captcha;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yaser
 */
@ManagedBean
@ViewScoped
public class CaptchaBean {

    public final static String SESSION_KEY_NAME = "mySessionKeyName";
    public final static String CORRECT = "Correct!";
    public final static String WRONG = "Wrong";

    String status;
    String value;

    public String check() {

        // Compare the CAPTCHA value with the user entered value.
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
//                .getExternalContext().getSession(false);(String) session.getAttribute(SESSION_KEY_NAME);
        String captchaValue = 
                (String)  ((HttpSession) FacesContext.getCurrentInstance().
          getExternalContext().getSession(true)).getAttribute(SESSION_KEY_NAME);
//        HttpSession session = request.getSession(true);
//        String captchaValue = (String) session.getAttribute(SESSION_KEY_NAME);
        System.out.println("herreeee " + value + "++++++++++++++++" + captchaValue);

        if (captchaValue.equalsIgnoreCase(value)) {
            status = CORRECT;
            System.out.println("heree corect");
        } else {
            status = WRONG;
        }

        return null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSessionKeyName() {        
        return SESSION_KEY_NAME;
    }
}
