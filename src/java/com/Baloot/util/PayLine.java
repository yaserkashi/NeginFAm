/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author SeyedYaghoob
 */
/// <summary>
/// Summary description for PayLine
/// </summary>
@ManagedBean
public class PayLine {

    public PayLine() {
        //
        // TODO: Add constructor logic here
        //
    }

    String urlSend = " http://payline.ir/payment-test/gateway-send";
    String urlGet = "http://payline.ir/payment-test/gateway-result-second";
    String api = "adxcv-zzadq-polkjsad-opp13opoz-1sdf455aadzmck1244567";
    String redirect = "http://localhost:8084/WebApplication1";
    String Results;
    String Resultg;

    public String getResultg() {
        return Resultg;
    }

    public void setResultg(String Resultg) {
        this.Resultg = Resultg;
    }

    public String getResults() {
        return Results;
    }

    public void setResults(String Results) {
        this.Results = Results;
    }

    public String Send(String url, String api, double amount, String redirect) {
        URL url1 = null;
        try {
            url1 = new URL(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(PayLine.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url1.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(PayLine.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.setRequestMethod("POST");
        } catch (ProtocolException ex) {
            Logger.getLogger(PayLine.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String parameters = "api=" + api + "&amount=" + amount + "&redirect=" + redirect;
        conn.setRequestProperty("Content-Length", Integer.toString(parameters.getBytes().length));
        //Send Request
        try {
            /*wr = conn.getOutputStream();
             wr.write(parameters.getBytes());*/
            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(
                    conn.getOutputStream());
            wr.writeBytes(parameters);
            wr.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        //get Response        
        BufferedReader reader;
        String Line;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((Line = reader.readLine()) != null) {
                builder.append(Line);
            }
            reader.close();
            return Results = builder.toString();
        } catch (IOException ex) {
            Logger.getLogger(PayLine.class.getName()).log(Level.SEVERE, null, ex);
            return "-2";
        }
    }

    public String Get(String url, String api, String trans_id, String id_get) {
        try {
            String parameters = "api=" + api + "&trans_id=" + trans_id + "&id_get=" + id_get;
            URL url1 = null;
            try {
                url1 = new URL(url);
            } catch (MalformedURLException ex) {
                Logger.getLogger(PayLine.class.getName()).log(Level.SEVERE, null, ex);
            }
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Lenght", Integer.toString(parameters.getBytes().length));
            //send
            OutputStream oStream;
            conn.setDoOutput(true);
            oStream = conn.getOutputStream();
            oStream.write(parameters.getBytes());
            oStream.close();

            //get
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String Line;
            StringBuilder builder = new StringBuilder();
            while ((Line = reader.readLine()) != null) {
                builder.append(Line);                
            }
            reader.close();
            return builder.toString();

        } catch (IOException ex) {
            Logger.getLogger(PayLine.class.getName()).log(Level.SEVERE, null, ex);
            return "-2";
        }
    }
}
