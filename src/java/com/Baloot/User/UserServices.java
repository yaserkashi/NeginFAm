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
        PreparedStatement ps;
 
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
    
    public static void updateTable(Users user) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateTableSQL = "UPDATE users SET name = ?,family = ?,username = ?,email = ?,phone_num = ? WHERE id = ?";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFamily());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhoneNum());
            preparedStatement.setInt(6, user.getId());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record updated in DBUSER table!");

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
    
    public static Boolean isUsernameUsed(String user) {
        Connection con = null;
        PreparedStatement ps;
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from Users where username = ?");
            ps.setString(1, user);
 
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }
    
    public static Users getUserByUsername(String username) {
        Connection con = null;
        PreparedStatement ps;
        Users user = new Users();
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from Users where username = ?");
            ps.setString(1, username);
 
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(username);
                user.setPasword(rs.getString("pasword"));
                user.setName(rs.getString("name"));
                user.setFamily(rs.getString("family"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNum(rs.getString("phone_num"));
                user.setAccessLevel(rs.getInt("access_level"));
                return user;
            }
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
        return null;
    }
    
    public static Boolean isEmailUsed(String email) {
        Connection con = null;
        PreparedStatement ps;
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from Users where email = ?");
            ps.setString(1, email);
 
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }
}
