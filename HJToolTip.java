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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JToolTip;


public class HJToolTip extends JToolTip {
    
    private final String texto;
    
    
    public HJToolTip(String textoToolTip) {
        
        texto = textoToolTip;
        
        setOpaque(false);
    }
    
    
    private Shape obtenerFormaToolTip() {
        
        return new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, 10, 10);
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        g2D.setColor(new Color(244,241,196));
        
        g2D.fill(obtenerFormaToolTip());
        
        
        g2D.setColor(new Color(30,30,30));
        
        g2D.drawString(texto, 4, getHeight()-4);
        
        
        g2D.dispose();
    }
    
    
    @Override
    public void paintBorder(Graphics g) {
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.setColor(new Color(100,100,100));
        
        g2D.draw(obtenerFormaToolTip());
        
        g2D.dispose();
    }
    
}
