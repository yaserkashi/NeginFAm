/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.User;

import com.Baloot.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


/**
 *
 * @author Yaser
 */
@ManagedBean
public class SignUp {
    private String userName;
    private String password;
    private String rePassword;
    private String email;
    private String name;
    private String lastname;
    private String mobile;
    private String introduction;
    private String partnerCode;
    private String secCode;
    private boolean lowCheck;

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
    
    public void submit(){
        System.out.println("Submit Function");
//        try {
//            insertRecordIntoTable();
//        } catch (SQLException ex) {
//            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public void insertRecordIntoTable() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO users (name,family,username,pasword,email,phone_num,access_level) VALUES (?,?,?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, userName);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, mobile);
            preparedStatement.setString(7, "0");

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into DBUSER table!");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Connection status", "ezafe shod"));

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }

    }
}
