/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package login;
import com.Baloot.User.*;
import com.Baloot.util.*;
import java.math.BigInteger;
import java.util.Random;
import java.security.SecureRandom;
/**
 *کلاس فراموشی رمز 
 * این کلاس به صفحه ای که بعد از زدن دکمه 
 * زمر خود را فرامش کرده ام وصل میشود
 * @author Ali
 */
public class ForgotPassword {
    /**
     * نام کاربری
     */
    private String userName;
    /**
     * ایمیل
     */
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return email;
    }

    public void setPassword(String email) {
        this.email = email;
    }
    /**
     * تابع عوض کردن رمز کاربر و ارسال ایمیل مناسب به کاربر
     * 
     */
    public void changePassword()
    { 
        try {
            if(userName!=null&&email!=null)
            {
                SecureRandom random=new SecureRandom();
                BigInteger bigInteger=new BigInteger(30, random);
                String newPssWord=bigInteger.toString(32);
                Users user=UserServices.getUserByUsername(userName);
                if(user!=null&&user.getEmail()==email)
                {
                Sendemail.Send("neginfamardakan", "09134512648",email,"تغییر زمر عبور",newPssWord);
                UserServices.changePassword(user.getId(), newPssWord);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
}
