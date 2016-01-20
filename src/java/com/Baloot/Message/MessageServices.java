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
    public List<Message> getInbox(int id) {
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
    
    public List<Message> getOutbox(int id) {
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
}
