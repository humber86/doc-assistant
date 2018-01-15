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
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JToolTip;


public class HJLabel extends JLabel {
    
    
    public HJLabel() {
        
        setFont(new Font("Arial", Font.BOLD, 14));
        
        setForeground(Colores.TEXTO);
        
    }
    
    
    public HJLabel(String texto) {
        
        this();
        
        setText(texto);
        
    }
    
    
    public HJLabel(Icon icono) {
        
        this();
        
        setIcon(icono);
        
    }
    
    
    public HJLabel(Icon icono, String texto) {
        
        this();
        
        setIcon(icono);
        
        setText(texto);
        
        setHorizontalTextPosition(JLabel.RIGHT);
        
    }
    
    
    //.........................
    
    
    @Override
    public JToolTip createToolTip() {
        
        return new HJToolTip(getToolTipText());
    }
    
}
