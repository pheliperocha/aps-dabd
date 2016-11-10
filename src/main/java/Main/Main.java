package Main;

import Controller.ServiceController;
import Controller.MainController;
import View.MainView;
import java.io.IOException;
import javax.mail.MessagingException;

public class Main {    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, MessagingException {
        
        ServiceController gmailController = new ServiceController();
        
        MainController controller = new MainController(gmailController.getService(), gmailController.getEmails());
        
        MainView view = new MainView(controller);
        
        controller.setView(view);

        controller.updateView();

    }
}
