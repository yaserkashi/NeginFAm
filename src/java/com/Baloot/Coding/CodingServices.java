/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Coding;

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
public class CodingServices {
    public static List<Coding> getCodings(Integer orderId, Integer typeId) {
        List<Coding> arr = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps;
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select id, text from Coding where order_id = ? and type_id = ?");
            ps.setInt(1, orderId);
            ps.setInt(2, typeId);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                Coding cod = new Coding();
                cod.setId(rs.getInt("id"));
                cod.setText(rs.getString("text"));
                cod.setOrderId(orderId);
                cod.setTypeId(typeId);
                arr.add(cod);
            }
            return arr;
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
    }
    /**
     * انتخاب از جدول کدینگ با استفاده از کلید اصلی
     * @param codeId=کلید اصلی
     * @return یه شی از نوع coing
     */
    public static Coding getCodings(Integer codeId) {
        Connection con = null;
        PreparedStatement ps;

        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from Coding where id = ? ");
            ps.setInt(1, codeId);

            ResultSet rs = ps.executeQuery();
            Coding cod = new Coding();
            if (rs.next()) {

                cod.setId(rs.getInt("id"));
                cod.setText(rs.getString("text"));
                cod.setOrderId(rs.getInt("order_id"));
                cod.setTypeId(rs.getInt("type_id"));

            }
            return cod;
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
    }
}
