/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Email;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author PhelipeRocha
 */
public class TableView extends JTable {
    
    private DefaultTableModel model;

    public TableView() {
        super();
        model = new DefaultTableModel();
        model.addColumn("gmail_id");
        model.addColumn("Email");
        model.addColumn("Assunto");
        model.addColumn("Data");
        
        this.setModel(model);
        
        TableColumnModel tcm = this.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
    }
    
    public JTable getTable() {
        return this;
    }
    
    public void setObject(Email obj) {
        
        Object[] row = { obj.getGmail_id(), obj.getAddress(), obj.getSubject(), obj.getDate() };
        DefaultTableModel m = (DefaultTableModel) this.getModel();
        m.addRow(row);
        
    }
    
}
