/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Yaser
 */
@ManagedBean
public class Test {
    private Connection getDBConnection() {

        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/neginfam", "postgres", "1234");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
    
    public void select(){
        System.out.println("select Function");
        Connection dbConnection = null;
        Statement statement = null;

        String select = "Select * from users";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            
            ResultSet rs= statement.executeQuery(select);
            System.out.println("select shod :)");
            while (rs.next()){
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } 
    }
    
}
