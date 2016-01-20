
import com.google.common.base.Strings;
import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Erdeshir
 */
@ManagedBean
public class sendemail {

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<String> getMailList() {
        return mailList;
    }

    public void setMailList(List<String> mailList) {
        this.mailList = mailList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private String mail;
    private List<String> mailList;
    private String title;
    private String message;
    public  void mailSendHand() {
        groupSendEmail(mailList, "نگین فام", message);
    }

    public void  groupSendEmail(List<String> mailAddress,String title,String message) {
        Object[] mailAddressA=mailAddress.toArray();
       
      
       
       
        
        for (int i = 0; i <mailAddress.size(); i++) {
            
        
        try {
            
            Send("ali41asti", "4440041388", mailAddress.get(i).toString(), title, message);
            System.out.println("okkkkkkkkkkkkk");
           
        } catch (MessagingException ex) {
           
            
            
        }
        }   
    }
    public void  sendEmail(String mailAddress,String title,String message) {
        try {
            
            Send("ali41asti", "4440041388", mailAddress, title, message);
           
        } catch (MessagingException ex) {
           
          
            
        }
           
    }
    public static void Send(final String username, final String password, String recipientEmail, String title, String message) throws AddressException, MessagingException {
        sendemail.Send(username, password, recipientEmail, "", title, message);
    }

   
    public static void Send(final String username, final String password, String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

     
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(username + "@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

        if (ccEmail.length() > 0) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
        }

        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport)session.getTransport("smtp");
        try
        {
        t.connect("smtp.gmail.com",465, username, password);
        }catch(MessagingException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        t.sendMessage(msg, msg.getAllRecipients());      
        t.close();
    }
    
    
}
