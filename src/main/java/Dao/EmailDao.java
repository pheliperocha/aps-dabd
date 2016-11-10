/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Email;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    
    public boolean insert(Email e) throws SQLException, ParseException {

        PreparedStatement stmt = banco.prepareStatement("INSERT INTO tb_emails (email, assunto, mensagem, data) VALUES (?, ?, ?, ?)");
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date parsed = format.parse(e.getData());
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());

        stmt.setString(1, e.getEmail());
        stmt.setString(2, e.getAssunto());
        stmt.setString(3, e.getMessagem());
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
