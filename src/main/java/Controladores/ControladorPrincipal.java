package Controladores;

import Dao.EmailDao;
import Modelo.Email;
import Visao.Janela;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class ControladorPrincipal {
    
    private Gmail service;
    private List<Message> messages;
    private Janela view;

    public ControladorPrincipal(Gmail service, List<Message> messages) {
        
        this.service = service;
        this.messages = messages;
        
    }

    public void setView(Janela view) {
        this.view = view;
    }
    
    public void atualizarVisao() {
        view.preencherTabela(this.service, this.messages);
    }
    
    public void salvarNoBanco() {

        for (int i = 0; i < 10; i++) {
            
            try {
                String messageId = messages.get(i).getId();
                
                Message message = service.users().messages().get("me", messageId).setFormat("raw").execute();
                
                Base64 base64Url = new Base64(true);
                byte[] emailBytes = base64Url.decodeBase64(message.getRaw());
                
                Properties props = new Properties();
                Session session = Session.getDefaultInstance(props, null);
                
                MimeMessage mime = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
                
                Email obj = new Email(mime, message.getSnippet());
                
                EmailDao emailDao = new EmailDao();
                emailDao.inserir(obj);
            } catch (IOException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ControladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }
}
