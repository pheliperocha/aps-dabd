/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author PhelipeRocha
 */
public class Email {
    
    private String gmail_id;
    private String address;
    private String subject;
    private String message;
    private String date;

    public Email(String messageId, MimeMessage mime, String snippet) throws MessagingException {
        
        String e = ((InternetAddress) mime.getFrom()[0]).getAddress();
        
        this.gmail_id = messageId;
        this.address = e;
        this.subject = mime.getSubject();
        this.message = snippet;
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String d = df.format(mime.getSentDate());
        
        this.date = d;

    }
    
    public Email() {
    }

    public String getGmail_id() {
        return gmail_id;
    }

    public void setGmail_id(String gmail_id) {
        this.gmail_id = gmail_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String d = df.format(date);
        
        this.date = d;
    }
    
}
