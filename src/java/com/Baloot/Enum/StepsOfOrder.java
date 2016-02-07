/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.Enum;

/**
* <h3>توضیحات</h3>
*  این مقدارشمارشی برای تعیین مراحل 
* یک سفارش است
* @author  Ali Asti
* @version 1.0
* @since   2014-03-31 
*/
public enum StepsOfOrder {
    /**
     *  registrationOrder=ثبت سفارش
     */
    registrationOrder,
    /**
     *  ,registrationFactor=صدور فاکتور
     */
    registrationFactor,
    /**
     * payFactor=فاکتور توسط مشتری پرداخت شده است
     */
    payFactor,
    /**
     * فاکتور توسط مدیر تایید پرداخت شده است
     * و کار آغاز شده است
     */
    ConfirmationPayFactor,
    /**
     * کار تمام شده و فایل پروژه برای مشتری آپلود شده است
     */
    EndOrder,
    /**
     * سفارش کنسل شده است
     */
    dissuasion
    
}
