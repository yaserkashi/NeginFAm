/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Order;
import com.Baloot.Design.*;
import com.Baloot.Translate.*;
import com.Baloot.Type.*;
import com.Baloot.Paper.*;
import java.util.List;
import com.Baloot.Enum.*;
/**
 *
 * @author Ali-M
 */

public class OrderFollowUpForUser {
    
    private Order  selectedOreder;
    private Design design;
    private Type type;
    private Paper paper;
    private Translate translate;

    public Translate getTranslate() {
        return translate;
    }

    public void setTranslate(Translate translate) {
        this.translate = translate;
    }
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Order getSelectedOreder() {
        return selectedOreder;
    }

    public void setSelectedOreder(Order selectedOreder) {
        this.selectedOreder = selectedOreder;
    }

    public Design getDesign() {
        return design;
    }

    public void setDesign(Design design) {
        this.design = design;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }
    public List<Order> ListOfOrdreForUser()
    {
    return OrderServices.listOfOrderForUser(userId);
    }
   public void SelectedOrderAction()
   {
  String typeOforder=selectedOreder.getTableName();
 
       switch (typeOforder)
       {
                case "type":
         type=  TypeServices.getTypeById(selectedOreder.getTableId());
        break;
                case "design":
          design=DesignServices.getDesignById(selectedOreder.getTableId());
        break;
                 case "translate":
 translate=TranslateServices.getTranslateById(selectedOreder.getTableId());
        break;
                 case "paper":
         paper=PaperServices.getPaperById(selectedOreder.getTableId());
        break;
       }
             
   }
}
