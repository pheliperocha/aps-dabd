/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MainController;
import Controller.ReadController;
import Controller.SavedController;
import Model.Email;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author PhelipeRocha
 */
public class SavedView {
    
    private final JFrame frame;
    private final Container container;
    private final JScrollPane listPane;
    private final Container topPane;
    public TableView tabela;
    private final SavedController controller;
 
    public SavedView(SavedController controller) throws IOException {
        this.frame = new JFrame();
        this.topPane = new JPanel();
        this.container = frame.getContentPane();
        this.controller = controller;
        
        frame.setTitle("Lista de Emails Salvas no Banco de Datos");
        
        container.setLayout(new BorderLayout(20,20));
        
        tabela = new TableView();
        
        listPane = new JScrollPane(tabela.getTable());
        
        JButton btnOpen = new JButton("Abrir");
        btnOpen.addActionListener((ActionEvent e) -> {
            int row = tabela.getSelectedRow();
            int id = Integer.parseInt(tabela.getModel().getValueAt(row, 0).toString());
            
            try {
                ReadController readController = new ReadController();
                readController.setEmailFromDB(id);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SavedView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        topPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topPane.add(btnOpen);
        
        container.add(BorderLayout.NORTH, topPane);
        container.add(BorderLayout.CENTER, listPane);
        
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 600);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);
    }

}
