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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;


public class HJDividingPanel extends JPanel {
    
    private final String texto;
    private Font formatoTexto = new Font("Arial", Font.BOLD, 18);
    private Color colorTexto = Colores.TEXTO;
    
    private final int justificacion;
    public static int CENTRO = 0;
    public static int IZQUIERDA = 1;
    public static int DERECHA = 2;
    
    private final int sangria;
    
    private final int largo;
    private final int grosor;
    private Color colorLinea = Colores.LINEAS;
    
    
    public HJDividingPanel(String texto, Font formato, Color colorDeTexto, int justificacion, int sangria, Color colorDeLinea, int largo, int grosor) {
        
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
        
        if(colorDeLinea!=null)
            colorLinea = colorDeLinea;
        
        this.largo = largo;
        this.grosor = grosor;
        
        
        Dimension tamano = new Dimension(this.largo, formatoTexto.getSize());
        
        setSize(tamano);
        setPreferredSize(tamano);
        setMaximumSize(tamano);
        setMinimumSize(tamano);
        
    }
    
    
    //................................
    
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2D = (Graphics2D)g.create();
           
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        g2D.setStroke(new BasicStroke(grosor, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
        
        g2D.setFont(formatoTexto);
        
        
        int alturaLinea = Math.round((float)(getHeight()/2.0));
        
        int anchoTexto = getFontMetrics(formatoTexto).stringWidth(texto);
        float alturaParaDibujarTexto = (float)(getHeight()-getHeight()/6.0);
        
        
        if(justificacion==CENTRO)
        {
            g2D.setColor(colorLinea);
            
            int mitadTexto = Math.round((float)(anchoTexto/2.0));
            
            int finalPrimeraLinea = Math.round((float)(getWidth()/2.0 - mitadTexto));
            
            g2D.drawLine(0, alturaLinea, finalPrimeraLinea-11, alturaLinea);
            
            
            g2D.setColor(colorTexto);
            
            g2D.drawString(texto, finalPrimeraLinea, alturaParaDibujarTexto);
            
            
            g2D.setColor(colorLinea);
            
            g2D.drawLine(finalPrimeraLinea+anchoTexto+10, alturaLinea, getWidth(), alturaLinea);
        }
        
        
        if(justificacion==IZQUIERDA)
        {
            g2D.setColor(colorLinea);
            
            g2D.drawLine(0, alturaLinea, sangria-11, alturaLinea);
            
            
            g2D.setColor(colorTexto);
            
            g2D.drawString(texto, sangria, alturaParaDibujarTexto);
            
            
            g2D.setColor(colorLinea);
            
            g2D.drawLine(sangria+anchoTexto+10, alturaLinea, getWidth(), alturaLinea);
        }
        
        
        if(justificacion==DERECHA)
        {
            g2D.setColor(colorLinea);
            
            g2D.drawLine(0, alturaLinea, getWidth()-anchoTexto-sangria-11, alturaLinea);
            
            
            g2D.setColor(colorTexto);
            
            g2D.drawString(texto, getWidth()-anchoTexto-sangria, alturaParaDibujarTexto);
            
            
            g2D.setColor(colorLinea);
            
            g2D.drawLine(getWidth()-sangria+10, alturaLinea, getWidth(), alturaLinea);
        }
        
        
        g2D.dispose();
    }
    
}
