/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Factor;

import com.Baloot.User.UserServices;
import com.Baloot.User.Users;
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
public class FactorServices {
    public static List<ReportList> getReportList() {
        List<ReportList> list = new ArrayList<>();
        Connection dbConnection;
        Statement statement;

        String select = "select * from factor_item fitem, factor f,public.order  where fitem.factor_id=f.id and f.user_id=1 and fitem.order_id=public.order.id";
        try {
            dbConnection = DataConnect.getConnection();
            statement = dbConnection.createStatement();
            
            ResultSet rs= statement.executeQuery(select);
            while (rs.next()){
                ReportList item = new ReportList();
                item.setCondition(rs.getInt("pay_condition"));
                item.setDate(rs.getString("order_date"));
                item.setFatorId(rs.getInt("factor_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setStep(rs.getInt("condition"));
                item.setSum(rs.getInt("sum_price"));
                list.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public static List<ReportList> getReportListById(int id) {
        List<ReportList> list = new ArrayList<>();
        Connection dbConnection;
        PreparedStatement ps;

        String select = "select * from factor_item fitem, factor f,public.order  where fitem.factor_id=f.id and f.user_id=? and fitem.order_id=public.order.id";
        
        try {
            dbConnection = DataConnect.getConnection();
            ps = dbConnection.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery(select);
            while (rs.next()){
                ReportList item = new ReportList();
                item.setCondition(rs.getInt("pay_condition"));
                item.setDate(rs.getString("order_date"));
                item.setFatorId(rs.getInt("factor_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setStep(rs.getInt("condition"));
                item.setSum(rs.getInt("sum_price"));
                list.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public static int insertRecordIntoTable(Factor factor) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        int id = 0;
        String insertTableSQL = "INSERT INTO factor (user_id,sum_price,off,pay_condition,p_factor,date_time) VALUES (?,?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, factor.getUserId().getId());
            preparedStatement.setDouble(2, factor.getSumPrice());
            preparedStatement.setDouble(3, factor.getOff());
            preparedStatement.setInt(4, factor.getPayCondition());
            preparedStatement.setBoolean(5, factor.getPFactor());
            preparedStatement.setString(6, factor.getDateTime());
            

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
    public static Factor selectFactorById(Integer id)
    {
     Factor factor = new Factor();
         Connection con = null;
         PreparedStatement ps;
         
         try {
             con = DataConnect.getConnection();
             ps = con.prepareStatement("Select * from factor where id= ?");
             ps.setInt(1,id);
             ResultSet rs = ps.executeQuery();
             
             while (rs.next()) {

                 //user_id,sum_price,off,pay_condition,p_factor,date_time
                 factor.setId(rs.getInt("id"));
                 factor.setSumPrice(rs.getDouble("sum_price"));
                 factor.setOff(rs.getDouble("off"));
                 factor.setPayCondition(rs.getInt("pay_condition"));
                 factor.setPFactor(rs.getBoolean("p_factor"));
                 factor.setDateTime(rs.getString("date_time"));
                 Users users=UserServices.getUserById(rs.getInt("user_id"));
                 factor.setUserId(users);
                 
             }
             return factor;
         } catch (SQLException ex) {
             System.out.println("Error -->" + ex.getMessage());
             return null;
         } finally {
             DataConnect.close(con);
         }
      
    }
    public static void payFactor(Integer id,Integer payCondition) throws SQLException
    {
     Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateTableSQL = "UPDATE factor SET pay_condition = ? WHERE id = ?";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            preparedStatement.setInt(1, payCondition);
            preparedStatement.setInt(2, id);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Condition updated in DBOrder table!");

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
