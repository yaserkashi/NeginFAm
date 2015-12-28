/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Design;

import com.Baloot.Coding.Coding;
import com.Baloot.Coding.CodingServices;
import com.Baloot.Enum.CombosEnum;
import com.Baloot.Enum.OrderTypesEnum;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author FK
 */
@ManagedBean
public class DesignForm {
    private Integer designType;
    private List<Coding> designTypes = CodingServices.getCodings(OrderTypesEnum.design.ordinal(), CombosEnum.design_type.ordinal());
    private String size;
    private List<Coding> sizes = CodingServices.getCodings(OrderTypesEnum.design.ordinal(), CombosEnum.size.ordinal());
    private String optionalSize;
    private String printType;
    private List<Coding> printTypes = CodingServices.getCodings(OrderTypesEnum.design.ordinal(), CombosEnum.print_type.ordinal());
    private String printOption;
    private List<Coding> printOptions = CodingServices.getCodings(OrderTypesEnum.design.ordinal(), CombosEnum.print_option.ordinal());
    private String designOption;
    private List<Coding> designOptions = CodingServices.getCodings(OrderTypesEnum.design.ordinal(), CombosEnum.design_option.ordinal());
    private String explian;
    private String endDate;
    private String attachFile;

    public List<Coding> getDesignTypes() {
        return designTypes;
    }

    public List<Coding> getSizes() {
        return sizes;
    }

    public List<Coding> getPrintTypes() {
        return printTypes;
    }

    public List<Coding> getPrintOptions() {
        return printOptions;
    }

    public List<Coding> getDesignOptions() {
        return designOptions;
    }
      
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
        System.out.println("SUBMIT FUNCTION!");
        FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("درسته ......"));
    }
}
