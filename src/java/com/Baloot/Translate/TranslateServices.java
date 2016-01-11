/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Translate;

import com.Baloot.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author FK
 */
public class TranslateServices {
    public static void insertRecordIntoTable(Translate translate) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO translate (language,field,title,date_time,end_date_time,explain,option,user_id) VALUES (?,?,?,?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, translate.getLanguage());
            preparedStatement.setInt(2, translate.getField());
            preparedStatement.setString(3, translate.getTitle());
            preparedStatement.setString(4, translate.getDateTime());
            preparedStatement.setString(5, translate.getEndDateTime());
            preparedStatement.setString(6, translate.getExplain());
            preparedStatement.setString(7, translate.getOption());
            preparedStatement.setInt(8, translate.getUserId().getId());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into DBTranslate table!");

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
