/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Employment;

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
public class EmploymentServices {
    public static void insertRecordIntoTable(Employment empl) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO employment (name,last_name,sex,mobile,phone,national_code,address,email,online_translate,records,last_education_average,other_field,image,birth_certificate,bank_name,bank_card_number,translate_language,field,last_education_field,last_education_university) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            dbConnection = DataConnect.getConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, empl.getName());
            preparedStatement.setString(2, empl.getLastName());
            preparedStatement.setBoolean(3, empl.getSex());
            preparedStatement.setString(4, empl.getMobile());
            preparedStatement.setString(5, empl.getPhone());
            preparedStatement.setString(6, empl.getNationalCode());
            preparedStatement.setString(7, empl.getAddress());
            preparedStatement.setString(8, empl.getEmail());
            preparedStatement.setBoolean(9, empl.getOnlineTranslate());
            preparedStatement.setString(10, empl.getRecords());
            preparedStatement.setInt(11, empl.getLastEducationAverage());
            preparedStatement.setString(12, empl.getOtherField());
            preparedStatement.setString(13, empl.getImage());
            preparedStatement.setString(14, empl.getBirthCertificate());
            preparedStatement.setString(15, empl.getBankName());
            preparedStatement.setString(16, empl.getBankCardNumber());
            preparedStatement.setString(17, empl.getTranslateLanguage());
            preparedStatement.setString(18, empl.getField());
            preparedStatement.setString(19, empl.getLastEducationField());
            preparedStatement.setString(20, empl.getLastEducationUniversity());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into DBEmployment table!");

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
    
    public static List<Employment> getALLEmployments() {
        Connection con = null;
        PreparedStatement ps;
        List<Employment> list = new ArrayList<>();
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select * from employment");
 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Employment empl = new Employment();
                empl.setId(rs.getInt("id"));
                empl.setName(rs.getString("name"));
                empl.setLastName(rs.getString("last_name"));
                empl.setEmail(rs.getString("email"));
                empl.setPhone(rs.getString("phone"));
                empl.setMobile(rs.getString("mobile"));
                empl.setAddress(rs.getString("address"));
                empl.setBankCardNumber(rs.getString("bank_card_num"));
                empl.setBankName(rs.getString("bank_name"));
                empl.setBirthCertificate(rs.getString("birth_certificate"));
                empl.setField(rs.getString("field"));
                empl.setImage(rs.getString("image"));
                empl.setLastEducationAverage(rs.getInt("last_education_average"));
                empl.setLastEducationField(rs.getString("last_education_field"));
                empl.setLastEducationUniversity(rs.getString("last_education_university"));
                empl.setNationalCode(rs.getString("national_code"));
                empl.setOnlineTranslate(rs.getBoolean("online_translate"));
                empl.setOtherField(rs.getString("other_field"));
                empl.setRecords(rs.getString("records"));
                empl.setSex(rs.getBoolean("sex"));
                empl.setTranslateLanguage(rs.getString("translate_languge"));
                list.add(empl);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Error -->" + ex.getMessage());
            return null;
        } finally {
            DataConnect.close(con);
        }
    }
}
