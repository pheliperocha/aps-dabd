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
import java.text.SimpleDateFormat;

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
    
    public boolean insert(Email e) throws SQLException {

        PreparedStatement stmt = banco.prepareStatement("INSERT INTO tb_emails (email, assunto, mensagem, data) VALUES (?, ?, ?, ?)");
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ii:ss");
        String d = df.format(e.getData());
        
        stmt.setString(1, e.getEmail());
        stmt.setString(2, e.getAssunto());
        stmt.setString(3, e.getMessagem());
        stmt.setString(4, d);
        
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
