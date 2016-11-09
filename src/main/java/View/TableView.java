/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Email;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author PhelipeRocha
 */
public class TableView {
    
    private Object [][] dados = {};
    private JTable tabela;
    private DefaultTableModel model;

    public TableView() {
        model = new DefaultTableModel();
        model.addColumn("Email");
        model.addColumn("Assunto");
        model.addColumn("Mensagem");
        model.addColumn("Data");
        
        this.tabela = new JTable();
        this.tabela.setModel(model);
    }
    
    public JTable getTable() {
        return this.tabela;
    }
    
    public void setObject(Email obj) {
        
        Object[] row = { obj.getEmail(), obj.getAssunto(), obj.getMessagem(), obj.getData() };
        DefaultTableModel model = (DefaultTableModel) this.tabela.getModel();
        model.addRow(row);
    }
    
}
