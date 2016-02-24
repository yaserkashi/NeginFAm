/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.page;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author FK
 */
@ManagedBean
public class EditorView {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void createFile() {
        try {
            
            File newTextFile = new File("thetextfile.html");

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(text);
            fw.close();
            System.out.println("File Created!");
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("فایل مورد نظر ثبت شد."));
        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
    }
}
