package Modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    
    private String email;
    private String assunto;
    private String messagem;
    private String data;

    public Email(MimeMessage mime, String snippet) {
        
        try {
            String email = ((InternetAddress) mime.getFrom()[0]).getAddress();
            
            this.email = email;
            this.assunto = mime.getSubject();
            this.messagem = snippet;
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String d = df.format(mime.getSentDate());
            
            this.data = d;
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }

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
