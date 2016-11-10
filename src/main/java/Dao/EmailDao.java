package Dao;

import Modelo.Email;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailDao {
    
    Connection banco;
    
    public EmailDao() {
        try {
            FabricaConexoes conn = new FabricaConexoes();
            banco = conn.connect();
        } catch (SQLException ex) {
            Logger.getLogger(EmailDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmailDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean inserir(Email e) throws SQLException, ParseException {

        PreparedStatement stmt = banco.prepareStatement("INSERT INTO emails_tabela (email, assunto, mensagem, data) VALUES (?, ?, ?, ?)");
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date parsed = format.parse(e.getData());
        java.sql.Date date = new java.sql.Date(parsed.getTime());

        stmt.setString(1, e.getEmail());
        stmt.setString(2, e.getAssunto());
        stmt.setString(3, e.getMessagem());
        stmt.setDate(4, date);
        
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
