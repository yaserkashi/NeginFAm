/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Factor;

/**
 *
 * @author FK
 */
public class ReportList {
    private Integer fatorId;
    private Integer orderId;
    private Integer Condition;
    private Integer sum;
    private String date;
    private Integer step;
    private Integer userId;

    public Integer getFatorId() {
        return fatorId;
    }

    public void setFatorId(Integer fatorId) {
        this.fatorId = fatorId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCondition() {
        return Condition;
    }

    public void setCondition(Integer Condition) {
        this.Condition = Condition;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    } 

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
}
