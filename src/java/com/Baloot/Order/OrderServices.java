/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Order;

import com.Baloot.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author FK
 */
public class OrderServices {
    public static void insertRecordIntoTable(Order order) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO public.order (table_name,table_id,user_id,order_date,condition) VALUES (?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, order.getTableName());
            preparedStatement.setInt(2, order.getTableId());
            preparedStatement.setInt(3, order.getUserId().getId());
            preparedStatement.setString(4, order.getOrderDate());
            preparedStatement.setInt(5, order.getCondition());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into DBOrder table!");

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
