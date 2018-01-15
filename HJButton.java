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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JToolTip;


public class HJButton extends JButton implements MouseListener {  
    
    private Color colorNormal = Colores.NORMAL;
    private Color colorRollover = Colores.ROLLOVER;
    private Color colorPressed = Colores.ROLLOVER;
    private final Color colorLineas = Colores.LINEAS_OSCURAS;
    
    private final Color colorTexto = Colores.TEXTO;
    private Font formatoTexto = new Font("Arial", Font.BOLD, 16);
    
    private String texto;
    private int anchoTexto;
    private int alturaTexto;
    
    private final Image imagen;
    private final int anchoImagen;
    private final int alturaImagen;
    
    private int distanciaImagenTexto;
    
    private boolean esVertical = false;
    
    private int top = 4;
    private int left = 10;
    private int bottom = 4;
    private int right = 10;
    
    private boolean entro = false;
    private boolean sePresiono = false;
    
    
    public HJButton(IconoDeImagen icono, String texto, Font tipoLetra, Color[] colores_Normal_Rollover_Pressed, boolean vertical, int ancho, int altura, Insets margenes) {
        
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        
        
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
        
        
        this.texto = texto;
        
        if(tipoLetra!=null)
            formatoTexto = tipoLetra;
        
        setFont(formatoTexto);
        
        if(this.texto!=null)
        {
            anchoTexto = getFontMetrics(formatoTexto).stringWidth(this.texto);
            alturaTexto = getFont().getSize();
            
        }else{
            
            anchoTexto = 0;
            alturaTexto = 0;
        }
        
        
        esVertical = vertical;
        
        if(imagen!=null && this.texto!=null)
        {
            if(esVertical)
                distanciaImagenTexto = 4;
            else
                distanciaImagenTexto = 6;
            
        }else{
            
            distanciaImagenTexto = 0;
        }
        
        
        if(margenes!=null)
        {
            top = margenes.top;
            left = margenes.left;
            bottom = margenes.bottom;
            right = margenes.right;
        }
        
        
        refrescarTamano();
        establecerTamano(ancho, altura);
        
        establecerColores(colores_Normal_Rollover_Pressed);
        
        anadirMouseListener();
        
    }
    
    
    public HJButton(IconoDeImagen icono, String texto, Color[] colores_Normal_Rollover_Pressed) {
        
        this(icono, texto, null, colores_Normal_Rollover_Pressed, false, -1, -1, null);
        
    }
    
    
    private void refrescarTamano() {
        
        Dimension tamano;
        
        if(esVertical)
        {
            if(anchoImagen>anchoTexto)
                tamano = new Dimension(left+anchoImagen+right, top+alturaImagen+distanciaImagenTexto+alturaTexto+bottom);
            else
                tamano = new Dimension(left+anchoTexto+right, top+alturaImagen+distanciaImagenTexto+alturaTexto+bottom);
            
        }else{
            
            if(alturaImagen>alturaTexto)
                tamano = new Dimension(left+anchoImagen+distanciaImagenTexto+anchoTexto+right, top+alturaImagen+bottom);
            else
                tamano = new Dimension(left+anchoImagen+distanciaImagenTexto+anchoTexto+right, top+alturaTexto+bottom);
        }
        
        setSize(tamano);
        setPreferredSize(tamano);
        setMaximumSize(tamano);
        setMinimumSize(tamano);
    }
    
    
    private void establecerTamano(int ancho, int altura) {
        
        if(ancho>0)
        {
            setSize(ancho, getSize().height);
            setPreferredSize(new Dimension(ancho, getSize().height));
            setMaximumSize(new Dimension(ancho, getSize().height));
            setMinimumSize(new Dimension(ancho, getSize().height));
        }
        
        if(altura>0)
        {
            setSize(getSize().width, altura);
            setPreferredSize(new Dimension(getSize().width, altura));
            setMaximumSize(new Dimension(getSize().width, altura));
            setMinimumSize(new Dimension(getSize().width, altura));
        }
    }
    
    
    private float obtenerAnchoParaDibujarTexto() {
        
        if(esVertical)
            return (float)((getWidth()-anchoTexto)/2.0);
        else
            return (float)((getWidth()+left+anchoImagen+distanciaImagenTexto)/2.0 - (anchoTexto+right)/2.0);
    }
    
    
    private float obtenerAlturaParaDibujarTexto() {
        
        if(esVertical)
            return (float)((getHeight()+top+alturaImagen+distanciaImagenTexto+alturaTexto)/2.0 - bottom/2.0 - 2);   
        else
            return (float)((getHeight()+alturaTexto)/2.0 - 2);
    }
    
    
    private int obtenerAnchoParaDibujarImagen() {
        
        if(esVertical)
            return Math.round((float)((getWidth()-anchoImagen)/2.0));
        else
            return Math.round((float)((getWidth()+left)/2.0 - (anchoImagen+distanciaImagenTexto+anchoTexto+right)/2.0));
    }
    
    
    private int obtenerAlturaParaDibujarImagen() {
        
        if(esVertical)
            return Math.round((float)((getHeight()+top)/2.0 - (alturaImagen+distanciaImagenTexto+alturaTexto+bottom)/2.0));    
        else
            return Math.round((float)((getHeight()-alturaImagen)/2.0));
    }
    
    
    private void establecerColores(Color[] colores) {
        
        if(colores!=null)
        {
            if(colores.length>0 && colores[0]!=null)
                colorNormal = colores[0];
            
            if(colores.length>1 && colores[1]!=null)
            {
                colorRollover = colores[1];
                colorPressed = colores[1];
            }
            
            if(colores.length>2 && colores[2]!=null)
                colorPressed = colores[2];
        }
    }
    
    
    private void anadirMouseListener() {
        
        addMouseListener(this);
    }
    
    
    //.............................
    
    
    @Override
    public void setText(String texto) {
        
        this.texto = texto;
        
        if(this.texto!=null)
        {
            anchoTexto = getFontMetrics(formatoTexto).stringWidth(this.texto);
            alturaTexto = getFont().getSize();
            
        }else{
            
            anchoTexto = 0;
            alturaTexto = 0;
        }
        
        if(imagen!=null && this.texto!=null)
        {
            if(esVertical)
                distanciaImagenTexto = 4;
            else
                distanciaImagenTexto = 6;
            
        }else{
            
            distanciaImagenTexto = 0;
        }
        
        refrescarTamano();
        
        revalidate();
        repaint();
    }
    
    @Override
    public String getText() {
        
        return texto;
    }
    
    
    @Override
    public JToolTip createToolTip() {
        
        return new HJToolTip(getToolTipText());
    }
    
    
    //.............................
    
    public void convertirAToggleButton() {
        
        removeMouseListener(getMouseListeners()[1]);
        
        addMouseListener(new EscuchadorToggle());
    }
    
    
    public void establecerSeleccionado(boolean seleccioando) {
        
        setSelected(seleccioando);
        
        sePresiono = seleccioando;
        repaint();
    }
    
    
    //.............................
    
    
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
        repaint();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }
    
    
    //................................
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        
        if(isEnabled())
        {
            if(entro)
            {   
                if(sePresiono)
                    g2D.setColor(colorPressed);
                else
                    g2D.setColor(colorRollover);
                
            }else{
                
                if(sePresiono)
                    g2D.setColor(colorPressed);
                else
                    g2D.setColor(colorNormal);
            }
            
        }else{
            
            g2D.setColor(colorPressed);
        }
        
        g2D.fillRect(0, 0, getWidth(), getHeight());
        
        
        if(imagen!=null)
            g2D.drawImage(imagen, obtenerAnchoParaDibujarImagen(), obtenerAlturaParaDibujarImagen(), null);
        
        if(texto!=null)
        {
            g2D.setFont(formatoTexto); 
            g2D.setColor(colorTexto);
            
            g2D.drawString(texto, obtenerAnchoParaDibujarTexto(), obtenerAlturaParaDibujarTexto());
        }
        
        
        g2D.setColor(colorLineas);
        g2D.drawRect(0, 0, getWidth()-1, getHeight()-1);
        
        
        g2D.dispose();
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class EscuchadorToggle implements MouseListener {
        
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
            
            sePresiono = !sePresiono;
            repaint();
        }
        
        
        @Override
        public void mouseReleased(MouseEvent me) {
            
        }
        
        
        @Override
        public void mouseClicked(MouseEvent me) {
            
        }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
