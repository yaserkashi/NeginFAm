/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.TextSmsMessage;

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
public class TextSmsMessageServices {

    public static int insertRecordIntoTable(TextSmsMessage tsm) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;
        String insertTableSQL = "INSERT INTO text_sms_message (text) VALUES (?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, tsm.getText());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into DB table!");
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

    public static void updateDefultSMS(TextSmsMessage tsm) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "UPDATE text_sms_message SET text=? WHERE static_value_type=?";
        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, tsm.getText());
            preparedStatement.setInt(2, tsm.getStaticValueType());
            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is Update!");

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

    /**
     * تابع برای گرفتن متن اسمس برای مراحل سفارش
     *
     * @param stepOforder=مرحله سفارش
     * @return
     */
    public static TextSmsMessage getDefultSMS(Integer stepOforder) {
        Connection dbConnection;
        PreparedStatement ps;
        TextSmsMessage item = new TextSmsMessage();
        try {
            dbConnection = DataConnect.getConnection();

            ps = dbConnection.prepareStatement("Select * from text_sms_message where static_value_type = ?");
            ps.setInt(1, stepOforder);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                //table_name,table_id,user_id,order_date,condition
                item.setId(rs.getInt("id"));
                item.setText(rs.getString("text"));
                item.setStaticValueType(stepOforder);

            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return item;
    }
}
