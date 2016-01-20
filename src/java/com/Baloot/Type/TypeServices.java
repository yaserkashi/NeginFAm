/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Type;

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
}
