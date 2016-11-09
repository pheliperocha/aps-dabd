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
    
    private String email;
    private String assunto;
    private String messagem;
    private String data;

    public Email(MimeMessage mime, String snippet) throws MessagingException {
        
        String email = ((InternetAddress) mime.getFrom()[0]).getAddress();
        
        this.email = email;
        this.assunto = mime.getSubject();
        this.messagem = snippet;
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String d = df.format(mime.getSentDate());
        
        this.data = d;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }

    public String getData() {
        return data;
    }

    public void setData(Date data) {
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String d = df.format(data);
        
        this.data = d;
    }
    
}
