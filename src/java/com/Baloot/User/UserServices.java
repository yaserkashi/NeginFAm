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
}
