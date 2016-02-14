/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Image;

import com.Baloot.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FK
 */
public class ImageServices {
    public static List<Image> getALLImages() {
        Connection con = null;
        PreparedStatement ps;
        List<Image> list = new ArrayList<>();
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from image");
 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Image img = new Image();
                img.setId(rs.getInt("id"));
                img.setAddress(rs.getString("address"));
                list.add(img);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
    }
    
    public static List<Image> getSelectedImages() {
        Connection con = null;
        PreparedStatement ps;
        List<Image> list = new ArrayList<>();
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from image WHERE selected = TRUE");
 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Image img = new Image();
                img.setId(rs.getInt("id"));
                img.setAddress(rs.getString("address"));
                list.add(img);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
    }
    
    public static void updateSelected(List<Image> selected) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateTableSQL = "UPDATE image SET selected = false";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            // execute update SQL stetement
            preparedStatement.executeUpdate();
            
            updateTableSQL = "UPDATE image SET selected = true where id = ?";
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);
            for (Image i : selected) {
                preparedStatement.setInt(1, i.getId());
                // execute update SQL stetement
                preparedStatement.executeUpdate();
            }
            System.out.println("Records updated in image table!");
                      
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
    
    public static void insertRecordIntoTable(Image image) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO image (address, selected) VALUES (?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, image.getAddress());
            preparedStatement.setBoolean(2, image.getSelected());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into image table!");

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
