/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.page;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.faces.bean.ManagedBean;

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
            File file = new File("test.html");

            // if file doesnt exists, then create it 
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();
            //System.out.println("Done writing to " + fileName); //For testing 
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
