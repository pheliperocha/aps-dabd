/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.EmailDao;
import Model.Email;
import View.SavedView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PhelipeRocha
 */
public final class SavedController {
    
    private final SavedView view;

    public SavedController() throws IOException, ClassNotFoundException, SQLException {

        view = new SavedView(this);
        this.populate();
        
    }
    
    public void populate() throws ClassNotFoundException, SQLException {
        
        EmailDao emailDao = new EmailDao();
        ArrayList<Email> emails_list = emailDao.list();
        
        for (Email email: emails_list) {
            this.view.tabela.setObject(email);
        }
    }
    
}
