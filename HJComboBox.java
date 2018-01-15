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
import javax.swing.JComboBox;
import javax.swing.JToolTip;


public class HJComboBox<Object> extends JComboBox<Object> {
    
    
    public HJComboBox() {
        
        establecerCaracteristicas();
        
    }
    
    
    public HJComboBox(Object[] elementos) {
        
        super(elementos);
        
        establecerCaracteristicas();
        
    }
    
    
    private void establecerCaracteristicas() {
        
        setFont(new Font("Arial", Font.BOLD, 14));
        
        setForeground(Colores.TEXTO);
        
        setBackground(Colores.NORMAL);
    }
    
    
    //............................
    
    
    @Override
    public JToolTip createToolTip() {
        
        return new HJToolTip(getToolTipText());
    }
    
    
}
