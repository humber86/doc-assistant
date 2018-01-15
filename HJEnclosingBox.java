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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Box;


public class HJEnclosingBox extends Box {
    
    private final String texto;
    private Font formatoTexto = new Font("Arial", Font.BOLD, 18);
    private Color colorTexto = Colores.TEXTO;
    
    private final int justificacion;
    public static int CENTRO = 0;
    public static int IZQUIERDA = 1;
    public static int DERECHA = 2;
    
    private final int sangria;
    
    private final int grosor;
    private Color colorLinea = Colores.LINEAS;
    
    
    public HJEnclosingBox(int direccionBox, String texto, Font formato, Color colorDeTexto, int justificacion, int sangria, Color colorDeLinea, int grosor) {
        
        super(direccionBox);
        
        
        setOpaque(false);
        
        
        if(texto==null)
            throw new IllegalArgumentException("texto (String) no puede ser nulo.");
        
        this.texto = texto;
        
        if(formato!=null)
            formatoTexto = formato;
        
        if(colorDeTexto!=null)
            colorTexto = colorDeTexto;
        
        this.justificacion = justificacion;
        
        this.sangria = sangria;
        
        this.grosor = grosor;
        
        if(colorDeLinea!=null)
            colorLinea = colorDeLinea;
        
    }
    
    
    //................................
    
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2D.setStroke(new BasicStroke(grosor, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
        
        g2D.setFont(formatoTexto);
        
        
        int alturaLineaSuperior = Math.round((float)(g2D.getFont().getSize()/2.0));
        
        int anchoTexto = getFontMetrics(formatoTexto).stringWidth(texto);
        float alturaParaDibujarTexto = (float)(g2D.getFont().getSize()-g2D.getFont().getSize()/6.0);
        
        
        if(justificacion==CENTRO)
        {
            g2D.setColor(colorLinea);
            
            int mitadTexto = Math.round((float)(anchoTexto/2.0));
            
            int finalPrimeraLineaSuperior = Math.round((float)(getWidth()/2.0 - mitadTexto));
            
            g2D.drawLine(0, alturaLineaSuperior, finalPrimeraLineaSuperior-11, alturaLineaSuperior);
            
            
            g2D.setColor(colorTexto);
            
            g2D.drawString(texto, finalPrimeraLineaSuperior, alturaParaDibujarTexto);
            
            
            g2D.setColor(colorLinea);
            
            g2D.drawLine(finalPrimeraLineaSuperior+anchoTexto+10, alturaLineaSuperior, getWidth(), alturaLineaSuperior);
        }
        
        
        if(justificacion==IZQUIERDA)
        {
            g2D.setColor(colorLinea);
            
            g2D.drawLine(0, alturaLineaSuperior, sangria-11, alturaLineaSuperior);
            
            
            g2D.setColor(colorTexto);
            
            g2D.drawString(texto, sangria, alturaParaDibujarTexto);
            
            
            g2D.setColor(colorLinea);
            
            g2D.drawLine(sangria+anchoTexto+10, alturaLineaSuperior, getWidth(), alturaLineaSuperior);
        }
        
        
        if(justificacion==DERECHA)
        {
            g2D.setColor(colorLinea);
            
            g2D.drawLine(0, alturaLineaSuperior, getWidth()-anchoTexto-sangria-11, alturaLineaSuperior);
            
            
            g2D.setColor(colorTexto);
            
            g2D.drawString(texto, getWidth()-anchoTexto-sangria, alturaParaDibujarTexto);
            
            
            g2D.setColor(colorLinea);
            
            g2D.drawLine(getWidth()-sangria+10, alturaLineaSuperior, getWidth(), alturaLineaSuperior);
        }
        
        
        int deltaX = 0;
        if(grosor>1)  deltaX = 1;
        
        g2D.drawLine(deltaX, alturaLineaSuperior, deltaX, getHeight()-grosor);
        
        g2D.drawLine(0, getHeight()-grosor, getWidth(), getHeight()-grosor);
        
        g2D.drawLine(getWidth()-grosor+deltaX, getHeight()-grosor, getWidth()-grosor+deltaX, alturaLineaSuperior);
        
        
        g2D.dispose();
    }
    
    
}
