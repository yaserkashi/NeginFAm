/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Design;

import com.Baloot.Enum.OrderTypesEnum;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author FK
 */
@ManagedBean
public class DesignForm {
    private Integer designType;
    private String size;
    private String optionalSize;
    private String printType;
    private String printOption;
    private String designOption;
    private String explian;
    private String endDate;
    private String attachFile;

    public void setDesignType(Integer designType) {
        this.designType = designType;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setOptionalSize(String optionalSize) {
        this.optionalSize = optionalSize;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public void setPrintOption(String printOption) {
        this.printOption = printOption;
    }

    public void setExplian(String explian) {
        this.explian = explian;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }
    
    public Integer getDesignType() {
        return designType;
    }

    public String getSize() {
        return size;
    }

    public String getOptionalSize() {
        return optionalSize;
    }

    public String getPrintType() {
        return printType;
    }

    public String getPrintOption() {
        return printOption;
    }

    public String getExplian() {
        return explian;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getAttachFile() {
        return attachFile;
    }
    
    public String getDesignOption() {
        return designOption;
    }

    public void setDesignOption(String designOption) {
        this.designOption = designOption;
    }
    
    public void submit() {
        Integer i = OrderTypesEnum.design.ordinal(); 
    }
}
