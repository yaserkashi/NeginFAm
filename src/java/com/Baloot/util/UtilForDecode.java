/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Baloot.util;

/**
 *
 * @author mohammad
 */
public class UtilForDecode {
    /**
     * تابع مورد نیاز برای دیکد کردن اطلاعات
     * @param boolean1 
     * @return استرینگ دارد یا ندارد برمیگرداند 
     */
    public static String decod(Boolean boolean1) {
        if (boolean1) {
            return "دارد";
        } else {
            return "ندارد";
        }
    }
    /**
     * تابع مورد نیاز برای دیکد کردن اطلاعات
     * @param character کاراکتر باید صفر یا یک عددی باشد 
     * @return استرینگ دارد یا ندارد برمیگرداند 
     */
    public static String decod(Character character)
    {
    if(character=='0')
    {
    return "ندارد";
    }
    if(character=='1')
    {
    return "دارد";
    }
    return "مشخص نشده!!!";
    }
    
}
