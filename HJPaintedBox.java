/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Box;


public class HJPaintedBox extends Box {
    
    private Color colorRelleno_1 = new Color(230,230,230);
    private Color colorRelleno_2 = new Color(250,250,250);
    private Color colorBorde_1 = new Color(50,50,50);
    private Color colorBorde_2 = new Color(70,70,70);
    
    private boolean sePintaBorde = true;
    
    private float porcentajeFloat = (float)0.5;
    
    private boolean esVertical = false;
    
    
    public HJPaintedBox(int orientacion, Color colorRelleno1, Color colorRelleno2, Color colorBorde1, Color colorBorde2, int porcentaje, boolean esVertical) {
        
        super(orientacion);
        
        setOpaque(true);
        
        
        if(colorRelleno1!=null)
            colorRelleno_1 = colorRelleno1;
        if(colorRelleno2!=null)
            colorRelleno_2 = colorRelleno2;
        
        
        if(colorBorde1!=null)
            colorBorde_1 = colorBorde1;
        if(colorBorde2!=null)
            colorBorde_2 = colorBorde2;
        
        if(colorBorde1==null && colorBorde2==null)
            sePintaBorde = false;
        
        
        porcentajeFloat = (float)(porcentaje/100.0);
        
        
        this.esVertical = esVertical;
        
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if(esVertical)
            g2D.setPaint(new GradientPaint((float)0.0, (float)0.0, colorRelleno_1, getWidth()*porcentajeFloat, (float)0.0, colorRelleno_2, true));
        else
            g2D.setPaint(new GradientPaint((float)0.0, (float)0.0, colorRelleno_1, (float)0.0, getHeight()*porcentajeFloat, colorRelleno_2, true));
        
        g2D.fill(g2D.getClip());
        
        g2D.dispose();
    }
    
    
    @Override
    public void paintBorder(Graphics g) {
        
        super.paintBorder(g);
        
        if(sePintaBorde)
        {
            Graphics2D g2D = (Graphics2D)g.create();
            
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2D.setStroke(new BasicStroke((float)1.0, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
            
            if(esVertical)
                g2D.setPaint(new GradientPaint((float)0.0, (float)0.0, colorBorde_1, getWidth()*porcentajeFloat, (float)0.0, colorBorde_2, true));
            else
                g2D.setPaint(new GradientPaint((float)0.0, (float)0.0, colorBorde_1, (float)0.0, getHeight()*porcentajeFloat, colorBorde_2, true));
            
            g2D.draw(g2D.getClip());
            
            g2D.dispose();
        }
    }
    
    
}
