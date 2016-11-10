/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.EmailDao;
import Model.Email;
import View.ReadView;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.model.Message;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author PhelipeRocha
 */
public class ReadController {
    
    private Email obj;
    
    public ReadController() {}

    public ReadController(String gmail_id) throws IOException, MessagingException {

        Message message = MainController.service.users().messages().get("me", gmail_id).setFormat("raw").execute();
        
        Base64 base64Url = new Base64(true);
        byte[] emailBytes = Base64.decodeBase64(message.getRaw());
        
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        
        MimeMessage mime = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
        
        Email obj = new Email(gmail_id, mime, message.getSnippet());
        this.obj = obj;
        
        ReadView readView = new ReadView(this, obj);
        
    }
    
    public void setEmailFromDB(int db_id) throws IOException, ClassNotFoundException, SQLException {
        
        EmailDao emailDao = new EmailDao();
        Email obj = emailDao.get(db_id);
        
        //ReadView readView = new ReadView(this, obj);
    }
    
    public boolean save() throws ClassNotFoundException, SQLException, ParseException {
        
        EmailDao emailDao = new EmailDao();
        
        return emailDao.insert(this.obj);
    }
    
}
