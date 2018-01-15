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
import javax.swing.JCheckBox;
import javax.swing.JToolTip;


public class HJCheckBox extends JCheckBox {
    
    
    public HJCheckBox() {
        
        establecerCaracteristicas();
        
    }
    
    
    public HJCheckBox(String texto) {
        
        super(texto);
        
        
        establecerCaracteristicas();
        
    }
    
    
    private void establecerCaracteristicas() {
        
        setFont(new Font("Arial", Font.BOLD, 14));
        
        setForeground(Colores.TEXTO);
        
        setOpaque(false);
    }
    
    
    //............................
    
    
    @Override
    public JToolTip createToolTip() {
        
        return new HJToolTip(getToolTipText());
    }
    
    
}
