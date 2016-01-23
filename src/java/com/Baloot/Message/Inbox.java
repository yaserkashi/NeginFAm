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
public class Inbox {
    private List<Message> list = MessageServices.getInbox(UserServices.getUserByUsername(SessionBean.getUserName()).getId());

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }
    
}
