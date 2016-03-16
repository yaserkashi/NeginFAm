/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Paper;

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
public class PaperServices {
    public static int insertRecordIntoTable(Paper paper) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;
        String insertTableSQL = "INSERT INTO paper (groups,field,orientation_of_field,title,date,end_date_time,explain,option,delivery_type,attach_file) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, paper.getGroup());
            preparedStatement.setInt(2, paper.getField());
            preparedStatement.setInt(3, paper.getOrientationOfField());
            preparedStatement.setString(4, paper.getTitle());
            preparedStatement.setString(5, paper.getDate());
            preparedStatement.setString(6, paper.getEndDateTime());
            preparedStatement.setString(7, paper.getExplain());
            preparedStatement.setString(8, paper.getOption());
            preparedStatement.setBoolean(9, paper.getDeliveryType());
            preparedStatement.setString(10, paper.getAttachFile());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into DBPaper table!");
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
      public static List<Paper> getALLPaper() {
        Connection con = null;
        PreparedStatement ps;
        List<Paper> list = new ArrayList<>();
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from paper");
 
            ResultSet rs = ps.executeQuery();
            //group,field,orientation_of_field,title,date,end_date_time,explain,option,delivery_type,attach_file
            while (rs.next()) {
                Paper paper = new Paper();
                 paper.setId(rs.getInt("id"));
                paper.setAttachFile(rs.getString("attach_file"));
                paper.setDeliveryType(rs.getBoolean("delivery_type"));
                paper.setExplain(rs.getString("explain"));
                paper.setField(rs.getInt("field"));
                paper.setGroup(rs.getInt("groups"));
                paper.setOrientationOfField(rs.getInt("orientation_of_field"));
                paper.setTitle(rs.getString("title"));
                paper.setOption(rs.getString("option"));
                paper.setDate(rs.getString("date"));
                paper.setEndDateTime(rs.getString("end_date_time"));
          
                list.add(paper);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
    }
      public static Paper getPaperById(Integer id) {
        Connection con = null;
        PreparedStatement ps;
        
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from paper where id= ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
               Paper paper = new Paper();
            while (rs.next()) {
             
                //group,field,orientation_of_field,title,date,end_date_time,explain,option,delivery_type,attach_file
                paper.setId(rs.getInt("id"));
                paper.setAttachFile(rs.getString("attach_file"));
                paper.setDeliveryType(rs.getBoolean("delivery_type"));
                paper.setExplain(rs.getString("explain"));
                paper.setField(rs.getInt("field"));
                paper.setGroup(rs.getInt("groups"));
                paper.setOrientationOfField(rs.getInt("orientation_of_field"));
                paper.setTitle(rs.getString("title"));
                paper.setOption(rs.getString("option"));
                paper.setDate(rs.getString("date"));
                paper.setEndDateTime(rs.getString("end_date_time"));
               
            }
            return paper;
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
    }
}
