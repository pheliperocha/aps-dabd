/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.MainView;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import java.io.IOException;
import java.util.List;
import javax.mail.MessagingException;

/**
 *
 * @author PhelipeRocha
 */
public class MainController {
    
    private Gmail service;
    private List<Message> messages;
    private MainView view;

    public MainController(Gmail service, List<Message> messages, MainView view) {
        
        this.service = service;
        this.messages = messages;
        this.view = view;
        
    }
    
    public void updateView() throws IOException, MessagingException{
        view.populate(this.service, this.messages);
    }
}
