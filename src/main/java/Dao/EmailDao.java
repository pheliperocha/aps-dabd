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
        
        stmt.setString(0, "Teste1");
        stmt.setString(1, "Teste2");
        stmt.setString(2, "Teste3");
        stmt.setString(3, "Teste4");
        
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
