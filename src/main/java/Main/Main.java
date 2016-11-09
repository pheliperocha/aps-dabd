package Main;

import Controller.GmailController;
import Controller.MainController;
import View.MainView;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.mail.MessagingException;

public class Main {    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, MessagingException {
        
        GmailController gmailController = new GmailController();
        
        MainView view = new MainView();
        
        MainController controller = new MainController(gmailController.getService(), gmailController.getEmails(), view);

        controller.updateView();

    }
}
