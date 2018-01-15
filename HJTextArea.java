/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JTextArea;


public class HJTextArea extends JTextArea {
    
    
    public HJTextArea(int filas, int columnas) {
        
        super(filas, columnas);
        
        setFont(new Font("Arial", Font.BOLD, 14));
        
        setForeground(new Color(30,30,30));
        
        setMargin(new Insets(4, 4, 4, 4));
        
        setLineWrap(true);
        
        setWrapStyleWord(true);
        
    }
    
    
    public boolean esTextoValido() {
        
        String texto = getText();
        
        for(int i=0 ; i<=texto.length()-1 ; i++)
        {
            if(texto.charAt(i)!=' ' && texto.charAt(i)!='\t')
                return true;
        }
        
        return false;
    }
    
    
    public void limpiar() {
        
        setText(null);
    }
    
    
    @Override
    public void paintBorder(Graphics g) {
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.setColor(Colores.LINEAS_OSCURAS);
        
        g2D.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 8, 8);
        
        g2D.dispose();
    }
    
    
}
