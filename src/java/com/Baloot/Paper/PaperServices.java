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

/**
 *
 * @author FK
 */
public class PaperServices {
    public static int insertRecordIntoTable(Paper paper) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;
        String insertTableSQL = "INSERT INTO translate (group,field,orientation_of_field,title,date,end_date_time,explain,option,delivery_type,attach_file) VALUES (?,?,?,?,?,?,?,?,?,?)";

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
}
