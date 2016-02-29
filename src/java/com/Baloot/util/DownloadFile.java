/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author FK
 */
public class DownloadFile {
    public void download(String fileName) {
        try {
            File file = new File(fileName );
            FileInputStream stream = new FileInputStream(file);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context
                    .getExternalContext().getResponse();
            response.reset();
            response.setBufferSize(5120000);
            response.setCharacterEncoding("UTF-8");
      
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            BufferedInputStream input = null;
            BufferedOutputStream output = null;
            try {
                input = new BufferedInputStream(stream);
                output = new BufferedOutputStream(response.getOutputStream(),
                        5120000);
                byte[] buffer = new byte[5120000];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                input.close();
                output.close();
            }
            context.responseComplete();
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void download(String fileName, String format, String browserType) {
        try {
            File file = new File(fileName + "." + format);
            FileInputStream stream = new FileInputStream(file);
            fileName = URLEncoder.encode(fileName, "UTF-8");
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context
                    .getExternalContext().getResponse();
            response.reset();
            response.setBufferSize(5120000);
            response.setCharacterEncoding("UTF-8");
            if (format.equals("pdf"))
                response.setContentType("application/" + format + "; charset=UTF-8");
            System.out.println("here in download, name: " + fileName + "." + format);
            if (browserType.indexOf("Firefox") != -1 || browserType.endsWith("Firefox"))
                response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + "." + format);
            else
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName + "." + format);
            BufferedInputStream input = null;
            BufferedOutputStream output = null;
            try {
                input = new BufferedInputStream(stream);
                output = new BufferedOutputStream(response.getOutputStream(),
                        5120000);
                byte[] buffer = new byte[5120000];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                input.close();
                output.close();
            }
            context.responseComplete();
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

