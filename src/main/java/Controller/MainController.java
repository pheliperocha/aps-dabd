/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.EmailDao;
import Model.Email;
import View.MainView;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author PhelipeRocha
 */
public class MainController {
    
    private Gmail service;
    private List<Message> messages;
    private MainView view;

    public MainController(Gmail service, List<Message> messages) {
        
        this.service = service;
        this.messages = messages;
        
    }

    public void setView(MainView view) {
        this.view = view;
    }
    
    public void updateView() throws IOException, MessagingException{
        view.populate(this.service, this.messages);
    }
    
    public void populateDB() throws IOException, MessagingException, ClassNotFoundException, SQLException, ParseException {

        for (int i = 0; i < 10; i++) {
            
            String messageId = messages.get(i).getId();
            
            Message message = service.users().messages().get("me", messageId).setFormat("raw").execute();

            Base64 base64Url = new Base64(true);
            byte[] emailBytes = base64Url.decodeBase64(message.getRaw());
            
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            
            MimeMessage mime = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
            
            Email obj = new Email(mime, message.getSnippet());
            
            EmailDao emailDao = new EmailDao();
            emailDao.insert(obj);

        }
        
    }
}
