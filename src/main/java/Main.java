

import Controladores.GmailControlador;
import Controladores.ControladorPrincipal;
import Visao.Janela;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

public class Main {    

    public static void main(String[] args) {
        
        try {
            GmailControlador gmailController = new GmailControlador();
            
            ControladorPrincipal controller = new ControladorPrincipal(gmailController.getService(), gmailController.getEmails());
            
            Janela view = new Janela(controller);
            
            controller.setView(view);
            
            controller.atualizarVisao();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
