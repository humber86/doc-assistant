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
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;
import javax.swing.JPanel;


public class HJTonguePanel extends JPanel {
    
    public static final int LENGUETA_AMBOS_LADOS = 0;
    public static final int LENGUETA_IZQUIERDA = 1;
    public static final int LENGUETA_DERECHA = 2;
    private int tipoPanel;
    
    private Color colorFuerteRelleno;
    private Color colorClaroRelleno;
    private Color colorFuerteBorde;
    private Color colorClaroBorde;
    
    
    public HJTonguePanel(LayoutManager manejadorDeLayout, int tipoPanel) {
        
        setLayout(manejadorDeLayout);
        
        setOpaque(false);
        
        this.tipoPanel = tipoPanel;
        
        colorFuerteRelleno = new Color(230,230,230);
        colorClaroRelleno = new Color(230,230,230);
        
        colorFuerteBorde = new Color(50,50,50);
        colorClaroBorde = new Color(70,70,70);
        
    }
    
    
    public HJTonguePanel(LayoutManager manejadorDeLayout, int tipoPanel, Color colorRellenoFuerte, Color colorRellenoClaro, Color colorBordeFuerte, Color colorBordeClaro) {
        
        this(manejadorDeLayout, tipoPanel);
        
        if(colorRellenoFuerte!=null)
            colorFuerteRelleno = colorRellenoFuerte;
        if(colorRellenoClaro!=null)
            colorClaroRelleno = colorRellenoClaro;
        
        if(colorBordeFuerte!=null)
            colorFuerteBorde = colorBordeFuerte;
        if(colorBordeClaro!=null)
            colorClaroBorde = colorBordeClaro;
        
    }
    
    
    private Shape obtenerFormaDePanel(boolean contorno) {
        
        Path2D.Double camino = new Path2D.Double(Path2D.WIND_EVEN_ODD);
        
        int anchoBoton = getWidth();
        int altoBoton = getHeight();
        
        camino.moveTo(0, 0);
        
        if(tipoPanel==LENGUETA_IZQUIERDA)
        {
            camino.quadTo(altoBoton/2, 0, altoBoton/2, altoBoton/2);
            
            camino.quadTo(altoBoton/2, altoBoton-1, altoBoton, altoBoton-1);
            
            if(contorno)
            {
                camino.lineTo(anchoBoton-1, altoBoton-1);
                
            }else{
                
                camino.lineTo(anchoBoton, altoBoton-1);
                
                camino.lineTo(anchoBoton, 0);
            }
        }
        
        if(tipoPanel==LENGUETA_DERECHA)
        {
            if(contorno)
                camino.moveTo(0, altoBoton-1);
            else
                camino.lineTo(0, altoBoton-1);
            
            camino.lineTo(anchoBoton-altoBoton-1, altoBoton-1);

            camino.quadTo(anchoBoton-(altoBoton/2)-1, altoBoton-1, anchoBoton-(altoBoton/2)-1, altoBoton/2);

            camino.quadTo(anchoBoton-(altoBoton/2)-1, 0, anchoBoton-1, 0);
        }
        
        if(tipoPanel==LENGUETA_AMBOS_LADOS)
        {
            camino.quadTo(altoBoton/2, 0, altoBoton/2, altoBoton/2);
            
            camino.quadTo(altoBoton/2, altoBoton-1, altoBoton, altoBoton-1);
            
            camino.lineTo(anchoBoton-altoBoton-1, altoBoton-1);
            
            camino.quadTo(anchoBoton-(altoBoton/2)-1, altoBoton-1, anchoBoton-(altoBoton/2)-1, altoBoton/2);

            camino.quadTo(anchoBoton-(altoBoton/2)-1, 0, anchoBoton-1, 0);
        }
        
        if(contorno)  
            return camino;
        
        camino.closePath();
        
        return camino;
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.setPaint(new GradientPaint((float)0.0, (float)0.0, colorFuerteRelleno, (float)0.0, (float)getHeight()/2, colorClaroRelleno, true));
        
        g2D.fill(obtenerFormaDePanel(false));
        
        g2D.dispose();
    }
    
    
    @Override
    public void paintBorder(Graphics g) {
        
        super.paintBorder(g);
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.setStroke(new BasicStroke((float)1.0, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
        
        g2D.setPaint(new GradientPaint((float)0.0, (float)0.0, colorFuerteBorde, (float)0.0, (float)getHeight()/2, colorClaroBorde, true));
        
        g2D.draw(obtenerFormaDePanel(true));
        
        g2D.dispose();
    }
    
}
