/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseWheelListener;
import javax.swing.JScrollPane;


public class HJScrollPane extends JScrollPane {
    
    private MouseWheelListener escuchadorRuedaRaton;
    
    
    public HJScrollPane(Component componenteVisto) {
        
        super(componenteVisto);
        
    }
    
    
    //..........................
    
    
    public void removerEscuchadorRuedaRaton() {
        
        escuchadorRuedaRaton = getMouseWheelListeners()[0];
        
        removeMouseWheelListener(escuchadorRuedaRaton);
    }
    
    public void colocarEscuchadorRuedaRaton() { addMouseWheelListener(escuchadorRuedaRaton); }
    
    
    @Override
    public void paintBorder(Graphics g) {
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.setColor(Colores.LINEAS_OSCURAS);
        
        g2D.drawRoundRect(0, 0, getWidth()-1, getHeight()-2, 8, 8);
        
        g2D.dispose();
    }
    
    
}
