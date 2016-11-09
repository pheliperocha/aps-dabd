/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.Message;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static main.main.getGmailService;

/**
 *
 * @author PhelipeRocha
 */
public class MainView {
    
    private final JFrame frame = new JFrame();
    private final Container container = frame.getContentPane();
    private JScrollPane listPane;
    private final Container topPane = new JPanel();
    
    private static void populando(List<Message> emails, DefaultListModel model, Gmail service) throws IOException {
        
        int i = 0;
        
        for (Message email : emails) {
            
            String messageId = email.getId();
            
            Message message = service.users().messages().get("me", messageId).execute();

            model.add(i, message.getSnippet());
            
            i++;
        }
        
    }
 
    public MainView(List<Message> emails, Gmail service) throws IOException {
        
        frame.setTitle("Lista de Emails");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        container.setLayout(new BorderLayout(20,20));
        
        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model);
        
        populando(emails, model, service);
        
        listPane = new JScrollPane(list);
        
        JButton btnAdd = new JButton("Salvar em Banco de Dados");
        btnAdd.addActionListener((ActionEvent e) -> {
            System.out.print("Salvar");
        });
        
        topPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topPane.add(btnAdd);
        
        container.add(BorderLayout.NORTH, topPane);
        container.add(BorderLayout.CENTER, listPane);
        
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(800, 800);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);
    }

}
