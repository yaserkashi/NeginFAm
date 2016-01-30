/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.User;

import com.Baloot.util.SessionBean;
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
 * @author FK
 */
@ManagedBean
public class EditProfile {
    private Users user = UserServices.getUserByUsername(SessionBean.getUserName());
    private Integer id = user.getId();
    @Size(min = 4, message = "نام کاربری نباید کمتر از چهار حرف باشد.")
    private String username = user.getUsername();
    private String name = user.getName();
    private String family = user.getFamily();
    private String phone = user.getPhoneNum();
    @Email(message = "رایانامه باید معتبر باشد.")
    private String email = user.getEmail();

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void submit() {
        try {
                user.setName(name);
                user.setFamily(family);
                user.setEmail(email);
                user.setUsername(username);
                user.setPhoneNum(phone);
                UserServices.updateTable(user);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("اطلاعات شما با موفقیت ویرایش شد."));
            } catch (SQLException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
