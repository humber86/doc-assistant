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
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;


public class HJMenuItem extends JMenuItem implements MouseListener {
    
    private Color colorNormal = Colores.NORMAL;
    private Color colorRollover = Colores.PRESSED;
    private Color colorPressed = Colores.PRESSED;
    
    private final Color colorLinea = Colores.LINEAS;
    
    private String texto;
    private int anchoTexto;
    private final int margenTexto = 10;
    private final Color colorTexto = Colores.TEXTO;
    private final Font formatoTexto = new Font("Dialog", Font.BOLD, 16);
    private float anchoParaDibujarTexto;
    private float alturaParaDibujarTexto;
    
    private Image imagen;
    private int anchoImagen;
    private int alturaImagen;
    private int anchoParaDibujarImagen;
    private int alturaParaDibujarImagen;
    
    private final int anchoEspacioImagen = 30;
    private final int alturaEspacioImagen = 30;
    
    private boolean entro = false;
    private boolean sePresiono = false;
    
    
    public HJMenuItem(IconoDeImagen icono, String textoItem, Color[] colores_Normal_Rollover_Pressed) {
        
        if( icono!=null && (icono.getIconWidth()>(anchoEspacioImagen-2) || icono.getIconHeight()>(alturaEspacioImagen-2)) )
            throw new IllegalArgumentException("ancho o altura de iconoDeImagen (IconoDeImagen) no puede ser mayor a anchoEspacioEmagen o alturaEspacioImagen, respectivamente (int = 28).");
        
        if(icono!=null)
        {
            imagen = icono.getImage();
            anchoImagen = icono.getIconWidth();
            alturaImagen = icono.getIconHeight();
            
        }else{
            
            imagen = null;
            anchoImagen = 0;
            alturaImagen = 0;
        }
        
        
        texto = textoItem;
        
        setFont(formatoTexto);
        
        if(texto!=null)
            anchoTexto = getFontMetrics(formatoTexto).stringWidth(texto);
        else
            anchoTexto = 0;
        
        
        establecerTamano();
        
        
        anchoParaDibujarImagen = Math.round((float)((anchoEspacioImagen-anchoImagen)/2.0));
        alturaParaDibujarImagen = Math.round((float)((getHeight()-alturaImagen)/2.0));
        
        anchoParaDibujarTexto = anchoEspacioImagen+margenTexto;
        alturaParaDibujarTexto = (float)((getHeight()+getFont().getSize())/2.0 - 2);
        
        
        establecerColores(colores_Normal_Rollover_Pressed);
        
        
        anadirMouseListener();
        
    }
    
    
    private void establecerTamano() {
        
        Dimension tamano = new Dimension(anchoEspacioImagen+margenTexto+anchoTexto+margenTexto+20, alturaEspacioImagen);
        
        setSize(tamano);
        setPreferredSize(tamano);
        setMinimumSize(tamano);
    }
    
    
    private void establecerColores(Color[] colores) {
        
        if(colores!=null)
        {
            if(colores.length>0)
                colorNormal = colores[0];
            
            if(colores.length>1)
            {
                colorRollover = colores[1];
                colorPressed = colores[1];
            }
            
            if(colores.length>2)
                colorPressed = colores[2];
        }
    }
    
    
    private void anadirMouseListener() {
        
        addMouseListener(this);
    }
    
    
    //...............................
    
    
    @Override
    public void mouseEntered(MouseEvent me) {
        
        entro = true;
        repaint();
    }
    
    
    @Override
    public void mouseExited(MouseEvent me) {
        
        entro = false;
        repaint();
    }
    
    
    @Override
    public void mousePressed(MouseEvent me) {
        
        sePresiono = true;
        repaint();
    }
    
    
    @Override
    public void mouseReleased(MouseEvent me) {
        
        sePresiono = false;
        entro = false;
        repaint();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }
    
    
    //.............................
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        
        if(isEnabled())
        {
            if(entro)
            {
                g2D.setColor(colorRollover);
                
                if(sePresiono)
                    g2D.setColor(colorPressed);
                
            }else{
                
                g2D.setColor(colorNormal);
            }
            
        }else{
            
            g2D.setColor(colorPressed);
        }
        
        g2D.fillRect(0, 0, getWidth(), getHeight());
        
        
        if(imagen!=null)
            g2D.drawImage(imagen, anchoParaDibujarImagen, alturaParaDibujarImagen, null);
        
        
        g2D.setColor(colorLinea);
        g2D.setStroke(new BasicStroke((float)1.0, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
        
        g2D.drawLine(anchoEspacioImagen, 0, anchoEspacioImagen, getHeight());
        
        
        if(texto!=null)
        {
            g2D.setFont(formatoTexto); 
            g2D.setColor(colorTexto);
            
            g2D.drawString(texto, anchoParaDibujarTexto, alturaParaDibujarTexto);
        }
        
        
        g2D.dispose();
    }
    
    
}
