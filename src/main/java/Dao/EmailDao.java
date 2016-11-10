/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Email;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PhelipeRocha
 */
public class EmailDao {
    
    Connection banco;
    
    public EmailDao() throws ClassNotFoundException, SQLException {
        ConFactory conn = new ConFactory();
        banco = conn.connect();
    }
    
    public Email get(int id) throws SQLException {

        PreparedStatement stmt = banco.prepareStatement("SELECT * FROM tb_emails WHERE id = ?");
        stmt.setInt(1, id);
        
        ResultSet result = stmt.executeQuery();
        
        Email email = new Email();
        //email.setGmail_id(result.getString(1));
        //email.setAddress(result.getString(2));
        //email.setSubject(result.getString(3));
        //email.setMessage(result.getString(4));
        
        return email;
    }
    
    public ArrayList<Email> list() throws SQLException {

        PreparedStatement stmt = banco.prepareStatement("SELECT * FROM tb_emails");
        ArrayList<Email> emails_list = new ArrayList<>();
        
        ResultSet result = stmt.executeQuery();
        Email email;
        while(result.next()) {
            email = new Email();
            email.setGmail_id(result.getString(1));
            email.setAddress(result.getString(2));
            email.setSubject(result.getString(3));
            email.setMessage(result.getString(4));
            email.setDate(result.getDate(5));
            emails_list.add(email);
        }
        
        
        return emails_list;
    }
    
    public boolean insert(Email e) throws SQLException, ParseException {

        PreparedStatement stmt = banco.prepareStatement("INSERT INTO tb_emails (address, subject, message, date) VALUES (?, ?, ?, ?)");
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date parsed = format.parse(e.getDate());
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());

        stmt.setString(1, e.getAddress());
        stmt.setString(2, e.getSubject());
        stmt.setString(3, e.getMessage());
        stmt.setDate(4, sqlDate);
        
        return stmt.execute();
    }
    
    @Override
    protected void finalize() throws SQLException, Throwable {
        try {
            banco.close();
        } finally {
            super.finalize();
        }
    }
    
}
