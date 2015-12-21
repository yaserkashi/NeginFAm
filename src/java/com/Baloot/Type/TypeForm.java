/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Type;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author FK
 */
@ManagedBean
public class TypeForm {
    private Integer language;
    private Integer field;
    private String title;
    private boolean formulation;
    private boolean layout;
    private boolean illustrations;
    private boolean table;
    private boolean charts;
    private boolean shape;
    private boolean editorial;
    private String explain;
    private String dateTime;
    private boolean delivery;
    private String attachFile;

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public void setField(Integer field) {
        this.field = field;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFormulation(boolean formulation) {
        this.formulation = formulation;
    }

    public void setLayout(boolean layout) {
        this.layout = layout;
    }

    public void setIllustrations(boolean illustrations) {
        this.illustrations = illustrations;
    }

    public void setTable(boolean table) {
        this.table = table;
    }

    public void setCharts(boolean charts) {
        this.charts = charts;
    }

    public void setShape(boolean shape) {
        this.shape = shape;
    }

    public void setEditorial(boolean editorial) {
        this.editorial = editorial;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public Integer getLanguage() {
        return language;
    }

    public Integer getField() {
        return field;
    }

    public String getTitle() {
        return title;
    }

    public boolean isFormulation() {
        return formulation;
    }

    public boolean isLayout() {
        return layout;
    }

    public boolean isIllustrations() {
        return illustrations;
    }

    public boolean isTable() {
        return table;
    }

    public boolean isCharts() {
        return charts;
    }

    public boolean isShape() {
        return shape;
    }

    public boolean isEditorial() {
        return editorial;
    }

    public String getExplain() {
        return explain;
    }

    public String getDateTime() {
        return dateTime;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public String getAttachFile() {
        return attachFile;
    }
    
    public void submit() {
        
    }
}
