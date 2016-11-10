/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MainController;
import Controller.ReadController;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

/**
 *
 * @author PhelipeRocha
 */
public class ReadView {
    
    private final JFrame frame;
    private final Container container;
    private final JScrollPane messagePane;
    private final Container topPane;
    private final Container topLeftPane;
    private final Container topRightPane;
    private final Container topBottomPane;
    private final Container bottomPane;
    private final ReadController controller;
 
    public ReadView(ReadController controller, Email obj) throws IOException {
        this.frame = new JFrame();
        this.topPane = new JPanel();
        this.topLeftPane = new JPanel();
        this.topRightPane = new JPanel();
        this.topBottomPane = new JPanel();
        this.container = frame.getContentPane();
        this.controller = controller;
        this.bottomPane = new JPanel();
        
        frame.setTitle("Ler Email");
        
        container.setLayout(new BorderLayout(20,20));
                
        JButton btnSave = new JButton("Salvar no Banco");
        btnSave.addActionListener((ActionEvent e) -> {
            try {
                if (controller.save()) {
                    JOptionPane.showMessageDialog(null, "Mensagem salva com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar mensagem");
                }
            } catch (ClassNotFoundException | SQLException | ParseException ex) {
                Logger.getLogger(ReadView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        JLabel labelAddress = new JLabel();
        labelAddress.setText("Email: " + obj.getAddress());
        
        JLabel labelDate = new JLabel();
        labelDate.setText("Data: " + obj.getDate());
        
        JLabel labelSubject = new JLabel();
        labelSubject.setText("Assunto: " + obj.getSubject());
        
        JTextArea message = new JTextArea();
        message.append("Mensagem aqui..." + obj.getMessage());
        
        messagePane = new JScrollPane(message);
        
        topPane.setLayout(new BorderLayout(20,20));
        topPane.add(BorderLayout.WEST, topLeftPane);
        topPane.add(BorderLayout.EAST, topRightPane);
        topPane.add(BorderLayout.SOUTH, topBottomPane);
        
        topLeftPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        topRightPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topBottomPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        topLeftPane.add(labelAddress);
        topRightPane.add(labelDate);
        topBottomPane.add(labelSubject);
        
        bottomPane.add(btnSave);
        
        container.add(BorderLayout.NORTH, topPane);
        container.add(BorderLayout.CENTER, messagePane);
        container.add(BorderLayout.SOUTH, bottomPane);
        
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 600);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        frame.setVisible(true);
    }

}
