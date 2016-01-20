/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.User;

import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author FK
 */
@ManagedBean
public class UsersManagement {
    private List<Users> list = UserServices.getALLUsers();
    private Users selected;

    public List<Users> getList() {
        return list;
    }

    public void setList(List<Users> list) {
        this.list = list;
    }

    public Users getSelected() {
        return selected;
    }

    public void setSelected(Users selected) {
        this.selected = selected;
    }
    
    public void deleteUser(int id) {
        UserServices.deleteUserById(id);
    }
}
