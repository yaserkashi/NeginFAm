/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.FactorItem;

import com.Baloot.Factor.Factor;
import com.Baloot.Factor.FactorServices;
import com.Baloot.Type.Type;
import com.Baloot.User.UserServices;
import com.Baloot.User.Users;
import com.Baloot.util.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Ali
 */
public class FactorItemServices {
   
     public static int insertRecordIntoTable(FactorItem factorItem) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;
        String insertTableSQL = "INSERT INTO factor_item (factor_id,order_id,unit,numbers,unit_price) VALUES (?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, factorItem.getFactorId().getId());
            preparedStatement.setInt(2, factorItem.getOrderId());
            preparedStatement.setString(3, factorItem.getUnit());
            preparedStatement.setInt(4, factorItem.getNumber());
            preparedStatement.setDouble(5, factorItem.getUnitPrice());
           
            

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
    
     public static FactorItem selectFactorItemByOrderId(Integer orderId){
         FactorItem factorItem = new FactorItem();
         Connection con = null;
         PreparedStatement ps;
         
         try {
             con = DataConnect.getConnection();
             ps = con.prepareStatement("Select * from factor_item where order_id= ?");
             ps.setInt(1, orderId);
             ResultSet rs = ps.executeQuery();
             
             while (rs.next()) {

                 //factor_id,order_id,unit,numbers,unit_price
                 factorItem.setId(rs.getInt("id"));
                 factorItem.setOrderId(orderId);
                 factorItem.setUnit(rs.getString("unit"));
                 factorItem.setNumber(rs.getInt("numbers"));
                 factorItem.setUnitPrice(rs.getDouble("unit_price"));
                 Factor factor=FactorServices.selectFactorById(rs.getInt("factor_id"));
                 factorItem.setFactorId(factor);
             }
             return factorItem;
         } catch (SQLException ex) {
             System.out.println("Error -->" + ex.getMessage());
             return null;
         } finally {
             DataConnect.close(con);
         }
       
     }
}
