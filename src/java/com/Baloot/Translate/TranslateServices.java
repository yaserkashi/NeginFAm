/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Translate;

import com.Baloot.User.UserServices;
import com.Baloot.User.Users;
import com.Baloot.util.DataConnect;
import com.Baloot.util.SessionBean;
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
public class TranslateServices {
    public static int insertRecordIntoTable(Translate translate) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;
        String insertTableSQL = "INSERT INTO translate (language,field,title,date_time,end_date_time,explain,option,user_id,attach_file,delivery_type) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, translate.getLanguage());
            preparedStatement.setInt(2, translate.getField());
            preparedStatement.setString(3, translate.getTitle());
            preparedStatement.setString(4, translate.getDateTime());
            preparedStatement.setString(5, translate.getEndDateTime());
            preparedStatement.setString(6, translate.getExplain());
            preparedStatement.setString(7, translate.getOption());
            preparedStatement.setInt(8, translate.getUserId().getId());
            preparedStatement.setString(9, translate.getAttachFile());
            preparedStatement.setBoolean(10, translate.getDeliveryType());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into DBTranslate table!");
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
    public static List<Translate> getALLTranslate() {
        Connection con = null;
        PreparedStatement ps;
        List<Translate> list = new ArrayList<>();
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from translate");
 
            ResultSet rs = ps.executeQuery();
           // language,field,title,date_time,end_date_time,explain,option
           
            while (rs.next()) {
                Translate translate = new Translate();
                translate.setId(rs.getInt("id"));
                translate.setAttachFile(rs.getString("attach_file"));
                translate.setDeliveryType(rs.getBoolean("delivery_type"));
                translate.setExplain(rs.getString("explain"));
                translate.setField(rs.getInt("field"));
                translate.setTitle(rs.getString("title"));
                translate.setOption(rs.getString("option"));
                translate.setEndDateTime(rs.getString("end_date_time"));
                translate.setLanguage(rs.getInt("language"));
                translate.setDateTime(rs.getString("date_time"));
                Users user = UserServices.getUserById(rs.getInt("user_id"));
                translate.setUserId(user);
                list.add(translate);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
    }
      public static Translate getTranslateById(Integer id) {
        Connection con = null;
        PreparedStatement ps;
      
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from translate where id= ?");
            ps.setInt(id, 1);
            ResultSet rs = ps.executeQuery();
            Translate translate = new Translate();
            while (rs.next()) {
                
              // language,field,title,date_time,end_date_time,explain,option,user_id
              
                translate.setId(rs.getInt("id"));
                translate.setAttachFile(rs.getString("attach_file"));
                translate.setDeliveryType(rs.getBoolean("delivery_type"));
                translate.setExplain(rs.getString("explain"));
                translate.setField(rs.getInt("field"));
                translate.setTitle(rs.getString("title"));
                translate.setOption(rs.getString("option"));
                translate.setEndDateTime(rs.getString("end_date_time"));
                translate.setLanguage(rs.getInt("language"));
                translate.setDateTime(rs.getString("date_time"));
                Users user = UserServices.getUserByUsername(SessionBean.getUserName());
                translate.setUserId(user);
               
               
            }
            return translate;
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
    }
}
