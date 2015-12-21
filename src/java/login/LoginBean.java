package login;

import services.captcha.MyCaptcha;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {

    private String userName;
    private String password;
    private String validate;
    private List<String> images;


    public String login() throws Exception {
     int i=orderType.tranclate.ordinal();
        System.out.println("here1");
        if (execute()) {
            try {
                if (this.getUserName() == "mary" && this.getPassword() == "123")
                    System.out.println("here2");
            } catch (Exception e) {
                System.out.println("invalid username and password:,,,,,,,,,,,,,,,,,,,,,,,,,," + e);
                return "incorrect";
            }
            return "correct";
        } else {
            System.out.println("here3");
            return "incorrect";

        }
    }

    public Boolean execute() throws Exception {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        javax.servlet.http.HttpSession session = request.getSession();
        String parm = validate;
        String c = (String) session.getAttribute(MyCaptcha.CAPTCHA_KEY);
        return parm.equals(c);
    }

    public String goToAnotherPage() {
        return "go";
    }

    public void init1() {
        images = new ArrayList<String>();
        images.add("images/slide1.jpg");
        images.add("images/slide2.jpg");
        images.add("images/slide3.jpg");
        images.add("images/slide4.jpg");
    }

    public List<String> getImages() {
        init1();
        return images;
    }


    public String cancel() {
        return null;
    }

    public String logout() {
        System.out.println("here5");
        return "loggedout";
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

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }
}