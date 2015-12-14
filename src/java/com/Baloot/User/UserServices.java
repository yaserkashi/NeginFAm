/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.User;

import com.Baloot.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author FK
 */
public class UserServices {
    public static Integer validate(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select username, pasword, access_level from Users where username = ? and pasword = ?");
            ps.setString(1, user);
            ps.setString(2, password);
 
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                String accessLevel = rs.getString("access_level");
                return Integer.valueOf(accessLevel);
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return 0;
        } finally {
            DataConnect.close(con);
        }
        return 0;
    }
    
    public static void insertRecordIntoTable(Users user) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO users (name,family,username,pasword,email,phone_num,access_level) VALUES (?,?,?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFamily());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPasword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhoneNum());
            preparedStatement.setInt(7, 2);

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
