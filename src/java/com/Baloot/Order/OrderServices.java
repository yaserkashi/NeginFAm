/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.Order;

import com.Baloot.User.UserServices;
import com.Baloot.User.Users;
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

    /**
     *
     * @param id=آیدی سفارش
     * @param getID=آیدیی که
     * @throws SQLException
     */
    public static void InsertGetId(int id, int getID) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateTableSQL = "UPDATE public.order SET get_id = ? WHERE id = ?";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            preparedStatement.setInt(1, getID);
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

    public static void updateCondition(int id, int newCondition) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateTableSQL = "UPDATE public.order SET condition = ? WHERE id = ?";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            preparedStatement.setInt(1, newCondition);
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

    public static List<Order> listOfOrderForUser(int userId) {
        List<Order> orderList = new ArrayList<>();
        Connection dbConnection;
        PreparedStatement ps;

        try {
            dbConnection = DataConnect.getConnection();

            ps = dbConnection.prepareStatement("Select * from public.order where user_id = ?");
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order item = new Order();
                //table_name,table_id,user_id,order_date,condition
                item.setCondition(rs.getInt("condition"));
                item.setId(rs.getInt("id"));
                item.setOrderDate(rs.getString("order_date"));
                item.setTableId(rs.getInt("table_id"));
                item.setTableName(rs.getString("table_name"));
                 item.setFinalFile(rs.getString("final_file"));
                Users user = UserServices.getUserById(userId);
                item.setUserId(user);
                orderList.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderList;
    }

    public static Order selectOrderById(int id) {

        Connection dbConnection;
        PreparedStatement ps;
        Order item = new Order();
        try {
            dbConnection = DataConnect.getConnection();

            ps = dbConnection.prepareStatement("Select * from public.order where id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                //table_name,table_id,user_id,order_date,condition
                item.setCondition(rs.getInt("condition"));
                item.setId(id);
                item.setOrderDate(rs.getString("order_date"));
                item.setTableId(rs.getInt("table_id"));
                item.setTableName(rs.getString("table_name"));
                Users user = UserServices.getUserById(rs.getInt("user_id"));
                item.setUserId(user);
                System.out.println(rs.getString("final_file"));
                item.setFinalFile(rs.getString("final_file"));
                System.out.println(item.getFinalFile());

            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return item;
    }

    public static List<Order> AllOfOrder() {
        List<Order> orderList = new ArrayList<>();
        Connection dbConnection;
        PreparedStatement ps;

        try {
            dbConnection = DataConnect.getConnection();

            ps = dbConnection.prepareStatement("Select * from public.order");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order item = new Order();
                //table_name,table_id,user_id,order_date,condition
                item.setCondition(rs.getInt("condition"));
                item.setId(rs.getInt("id"));
                item.setOrderDate(rs.getString("order_date"));
                item.setTableId(rs.getInt("table_id"));
                item.setTableName(rs.getString("table_name"));
                 item.setFinalFile(rs.getString("final_file"));
                Users user = UserServices.getUserById(rs.getInt("user_id"));
                item.setUserId(user);
                orderList.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orderList;
    }

    public static Order selectOrderByGetId(int getId) {
        Connection dbConnection;
        PreparedStatement ps;
        Order item = new Order();
        try {
            dbConnection = DataConnect.getConnection();

            ps = dbConnection.prepareStatement("Select * from public.order where get_id = ?");
            ps.setInt(1, getId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                //table_name,table_id,user_id,order_date,condition
                item.setCondition(rs.getInt("condition"));
                item.setId(rs.getInt("id"));
                item.setOrderDate(rs.getString("order_date"));
                item.setTableId(rs.getInt("table_id"));
                item.setTableName(rs.getString("table_name"));
                 item.setFinalFile(rs.getString("final_file"));
                item.setGet_id(getId);
                Users user = UserServices.getUserById(rs.getInt("user_id"));
                item.setUserId(user);

            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return item;
    }
    
    public static void insertFinalFile(int id, String newFinalFile) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String updateTableSQL = "UPDATE public.order SET final_file = ? WHERE id = ?";
        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            preparedStatement.setString(1, newFinalFile);
            preparedStatement.setInt(2, id);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("FileName Insert in DBOrder table!");

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
