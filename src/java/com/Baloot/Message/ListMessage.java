/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Message;

import com.Baloot.User.UserServices;
import com.Baloot.util.SessionBean;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author FK
 */
@ManagedBean
public class ListMessage {
    private int id = UserServices.getUserByUsername(SessionBean.getUserName()).getId();
    private List<Message> list = MessageServices.getMessagesById(id);
    private Message selectedMessage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      
    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

    public Message getSelectedMessage() {
        System.out.println("Set Message");
        return selectedMessage;
    }

    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;
    }  

    public String getSenderUsername (int id) {
        return UserServices.getUserById(id).getUsername();
    }
}
