/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Message;

import com.Baloot.User.UserServices;
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
public class MessageServices {
    public static List<Message> getInbox(int id) {
        List<Message> list = new ArrayList<>();
        Connection dbConnection;
        PreparedStatement ps;

        try {
            dbConnection = DataConnect.getConnection();
            
            ps = dbConnection.prepareStatement("Select * from message where user_id_get = ?");
            ps.setInt(1, id);
 
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Message item = new Message();
                item.setUserIdSend(UserServices.getUserById(rs.getInt("user_id_send")));
                item.setUserIdGet(UserServices.getUserById(id));
                item.setTitle(rs.getString("title"));
                item.setText(rs.getString("text"));
                item.setRead(rs.getBoolean("read"));
                item.setDateTime(rs.getString("date_time"));
                list.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static List<Message> getOutbox(int id) {
        List<Message> list = new ArrayList<>();
        Connection dbConnection;
        PreparedStatement ps;

        try {
            dbConnection = DataConnect.getConnection();
            
            ps = dbConnection.prepareStatement("Select * from message where user_id_send = ?");
            ps.setInt(1, id);
 
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Message item = new Message();
                item.setUserIdGet(UserServices.getUserById(rs.getInt("user_id_get")));
                item.setUserIdSend(UserServices.getUserById(id));
                item.setTitle(rs.getString("title"));
                item.setText(rs.getString("text"));
                item.setRead(rs.getBoolean("read"));
                item.setDateTime(rs.getString("date_time"));
                list.add(item);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static void insertRecordIntoTable(Message message) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO message (text,user_id_send,user_id_get,date_time,read,title) VALUES (?,?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, message.getText());
            preparedStatement.setInt(2, message.getUserIdSend().getId());
            preparedStatement.setInt(3, message.getUserIdGet().getId());
            preparedStatement.setString(4, message.getDateTime());
            preparedStatement.setBoolean(5, message.getRead());
            preparedStatement.setString(6, message.getTitle());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into DBMessage table!");

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
