/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.TableModel;


public class HJTable extends JTable {
    
    
    public HJTable(TableModel modeloDeTabla) {
        
        super(modeloDeTabla);
        
        
        setRowHeight(25);
        
        setFont(new Font("Dialog", Font.PLAIN, 14));
        
        getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        
    }
    
    
}
