/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Factor;

import com.Baloot.util.DataConnect;
import java.sql.Connection;
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
    public List<ReportList> getReportList() {
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
}
