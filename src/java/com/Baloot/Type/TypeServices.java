/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Type;

import com.Baloot.User.UserServices;
import com.Baloot.User.Users;
import com.Baloot.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FK
 */
public class TypeServices {
    public static int insertRecordIntoTable(Type type) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;
        String insertTableSQL = "INSERT INTO type (language,field,title,date_time,explain,option,user_id) VALUES (?,?,?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, type.getLanguage());
            preparedStatement.setInt(2, type.getField());
            preparedStatement.setString(3, type.getTitle());
            preparedStatement.setString(4, type.getDateTime());
            preparedStatement.setString(5, type.getExplain());
            preparedStatement.setString(6, type.getOption());
            preparedStatement.setInt(7, type.getUserId().getId());

            // execute insert SQL stetement
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("Record is inserted into DBType table!");
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();

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
        return id;
    }
     public static List<Type> getALLType() {
        Connection con = null;
        PreparedStatement ps;
        List<Type> list = new ArrayList<>();
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from type");
 
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                Type type = new Type();
                //language,field,title,date_time,explain,option,user_id
                 type.setId(rs.getInt("id"));
                type.setAttachFile(rs.getString("attach_file"));
                type.setDeliveryType(rs.getBoolean("delivery_type"));
                type.setExplain(rs.getString("explain"));
                type.setField(rs.getInt("field"));
                type.setTitle(rs.getString("title"));
                type.setOption(rs.getString("option"));              
                type.setEndDateTime(rs.getString("end_date_time"));
                type.setDateTime(rs.getString("date_time"));
                Users user = UserServices.getUserById(rs.getInt("user_id"));
                type.setUserId(user);
                type.setLanguage(rs.getInt("language"));
                list.add(type);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
    }
      public static Type getTypeById(Integer id) {
        Connection con = null;
        PreparedStatement ps;
     
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from type where id= ?");
            ps.setInt(id, 1);
            ResultSet rs = ps.executeQuery();
             Type type = new Type();
            while (rs.next()) {
               
                //language,field,title,date_time,explain,option,user_id
                 type.setId(rs.getInt("id"));
                type.setAttachFile(rs.getString("attach_file"));
                type.setDeliveryType(rs.getBoolean("delivery_type"));
                type.setExplain(rs.getString("explain"));
                type.setField(rs.getInt("field"));
                type.setTitle(rs.getString("title"));
                type.setOption(rs.getString("option"));              
                type.setEndDateTime(rs.getString("end_date_time"));
                type.setDateTime(rs.getString("date_time"));
                Users user = UserServices.getUserById(rs.getInt("user_id"));
                type.setUserId(user);
                type.setLanguage(rs.getInt("language"));
             
            }
            return type;
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
    }
}
