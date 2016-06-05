/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Baloot.page;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
            String filePath = "\\web\\resources\\html";
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest httpServletRequest = (HttpServletRequest) context
                    .getExternalContext().getRequest();
            String stringPath = httpServletRequest.getSession().getServletContext()
                    .getRealPath("/");
            Path path = Paths.get(stringPath);
            filePath = path.getParent().getParent().toString() + filePath;
            if (!Files.exists(Paths.get(filePath))) {
                Files.createDirectories(Paths.get(filePath));
            }
            File newTextFile = new File(filePath,"thetextfile.html");
            System.out.println(newTextFile.getAbsolutePath() + text);

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
