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
import javax.swing.JRadioButton;


public class HJRadioButton extends JRadioButton {
    
    
    public HJRadioButton() {
        
        establecerCaracteristicas();
        
    }
    
    
    public HJRadioButton(String texto) {
        
        super(texto);
        
        
        establecerCaracteristicas();
        
    }
    
    
    private void establecerCaracteristicas() {
        
        setFont(new Font("Arial", Font.BOLD, 14));
        
        setForeground(Colores.TEXTO);
        
        setOpaque(false);
    }
    
    
}
