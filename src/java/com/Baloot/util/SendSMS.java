package com.Baloot.util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Ali-M
 */
@ManagedBean
public class SendSMS {
private static String userName="9138758450";
private static String passWord="9138758450";
private static String fromNumber="50005000902126";
private String textMessage;

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }
    
    private List<String> numbers;
   public void send()
   {
     for(int i=0;i<numbers.size();i++)
     {
 try {
            //System.out.println();
            //System.out.println("your credit is :" + getCredit("9138598190", "Asti4449970349*"));
           
           sendSms(userName, passWord,numbers.get(i),fromNumber,textMessage,"false");
     // sendSms("9138598190", "Asti4449970349*","9380824739","50005000902000","سلام ببخشید مزاحم شدم","false");
           // System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }
   }
    public void send(List<String> number,String text)
   {
     for(int i=0;i<number.size();i++)
     {
 try {
            //System.out.println();
            //System.out.println("your credit is :" + getCredit("9138598190", "Asti4449970349*"));
           
            sendSms(userName, passWord,number.get(i),fromNumber,text,"false");
           // System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }
   }
     public void send(String number,String text)
   {
     
 try {
            //System.out.println();
            //System.out.println("your credit is :" + getCredit("9138598190", "Asti4449970349*"));
           
            sendSms(userName, passWord,number,fromNumber,text,"false");
           // System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    
   }
   public static Object getCredit(String username,String password) throws Exception {
        String url = "http://api.payamak-panel.com/post/send.asmx";
        Object[] params = new Object[2];
        params[0] = username;
        params[1] = password;
        String method = "GetCredit";
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new java.net.URL(url));
        call.setOperationName(new QName("http://tempuri.org/", method));
        call.setSOAPActionURI("http://tempuri.org/" + method);
        call.addParameter(new QName("http://tempuri.org/", "username"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "password"), XMLType.XSD_STRING, ParameterMode.IN);
        Object ret = call.invoke(params);
        return ret;
    }

    public static  class SendResult  implements Serializable
    {
        String res ;
    }

    public static Object sendSms(String username,String password,String to ,String from,String text,String isflash) throws Exception {
        String url = "http://api.payamak-panel.com/post/send.asmx";
        Object[] params = new Object[6];
        params[0] = username;
        params[1] = password;
        params[2] = to;
        params[3] = from;
        params[4] = text;
        params[5] = isflash;

        String method = "SendSimpleSMS2";
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new java.net.URL(url));
        call.setOperationName(new QName("http://tempuri.org/", method));
        call.setSOAPActionURI("http://tempuri.org/" + method);
        call.addParameter(new QName("http://tempuri.org/", "username"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "password"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "to"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "from"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "text"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "isflash"), XMLType.XSD_BOOLEAN, ParameterMode.IN);
        // call.setReturnType(new QName("SendResult"));
        Object ret = call.invoke(params);
        return ret;
    }
    /**
     * تابع ارسال اسمس به وسیله پنل اسمس 
     * @param to =شماره فردی که میخواهید پیام را دریافت کند
     * @param text=متن پیام
     * @param isflash=به صورت دیفالت برابر با "false"قرار دهید
     * @return =نتیجه ارسال را بر میگرداند 
     * @throws Exception 
     */
      public static Object sendSms(String to ,String text,String isflash) throws Exception {
        String url = "http://api.payamak-panel.com/post/send.asmx";
        Object[] params = new Object[6];
        params[0] = userName;
        params[1] = passWord;
        params[2] = to;
        params[3] =fromNumber;
        params[4] = text;
        params[5] = isflash;

        String method = "SendSimpleSMS2";
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new java.net.URL(url));
        call.setOperationName(new QName("http://tempuri.org/", method));
        call.setSOAPActionURI("http://tempuri.org/" + method);
        call.addParameter(new QName("http://tempuri.org/", "username"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "password"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "to"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "from"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "text"), XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter(new QName("http://tempuri.org/", "isflash"), XMLType.XSD_BOOLEAN, ParameterMode.IN);
        // call.setReturnType(new QName("SendResult"));
        Object ret = call.invoke(params);
        return ret;
    }

  
}
