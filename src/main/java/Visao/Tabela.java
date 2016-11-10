package Visao;

import Modelo.Email;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Tabela {
    
    private Object [][] dados = {};
    private JTable tabela;
    private DefaultTableModel model;

    public Tabela() {
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
