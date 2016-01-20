/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Design;

import com.Baloot.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author FK
 */
public class DesignServices {
    public static int insertRecordIntoTable(Design design) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;
        String insertTableSQL = "INSERT INTO design (design_type,size,register_date,print_type,design_option,print_option,user_id,end_date,explain,attach_file) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, design.getDesignType());
            preparedStatement.setString(2, design.getSize());
            preparedStatement.setString(3, design.getRegisterDate());
            preparedStatement.setString(4, design.getPrintType());
            preparedStatement.setString(5, design.getDesingOption());
            preparedStatement.setString(6, design.getPrintOption());
            preparedStatement.setInt(7, design.getUserId().getId());
            preparedStatement.setString(8, design.getEndDate());
            preparedStatement.setString(9, design.getExplain());
            preparedStatement.setString(10, design.getAttachFile());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into DBDesign table!");
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
}
