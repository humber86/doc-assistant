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
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolTip;


public class HJCustomizedButton extends JButton {
    
    private Color colorTextoNormal = Colores.TEXTO;
    private Color colorTextoRollover = Colores.ROLLOVER;
    
    private int grosorSubrayado;
    
    public static final int SALIR = 0;
    
    
    public HJCustomizedButton() {
        
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        
        setMargin(new Insets(-2, -2, -2, -2));
        
    }
    
    
    public HJCustomizedButton(IconoDeImagen icono, boolean rolloverConManita) {
        
        this();
        
        
        if(icono==null)
            throw new IllegalArgumentException("icono (IconoDeImagen) no puede ser nulo.");
            
        setIcon(icono);
        
        
        if(rolloverConManita)
            addMouseListener(new ListenerRolloverConManita());
        
    }
    
    
    public HJCustomizedButton(String nombreArchivoIconoNormal, String nombreArchivoIconoRollover, int ancho, int altura, boolean rolloverConManita) {
        
        this();
        
        
        ImageIcon iconoImagenNormal = new ImageIcon(getClass().getResource("images/"+nombreArchivoIconoNormal));
        
        if(iconoImagenNormal==null)
            throw new IllegalArgumentException("ImageIcon es nulo para nombreArchivoIconoNormal: "+nombreArchivoIconoNormal+".");
            
        setIcon(new ImageIcon(iconoImagenNormal.getImage().getScaledInstance(ancho, altura, Image.SCALE_SMOOTH)));
        
        
        ImageIcon iconoImagenRollover = new ImageIcon(getClass().getResource("images/"+nombreArchivoIconoRollover));
        
        if(iconoImagenRollover==null)
            throw new IllegalArgumentException("ImageIcon es nulo para nombreArchivoIconoRollover: "+nombreArchivoIconoRollover+".");
           
        setRolloverIcon(new ImageIcon(iconoImagenRollover.getImage().getScaledInstance(ancho, altura, Image.SCALE_SMOOTH)));
        setPressedIcon(new ImageIcon(iconoImagenRollover.getImage().getScaledInstance(ancho, altura, Image.SCALE_SMOOTH)));
        
        
        if(rolloverConManita)
            addMouseListener(new ListenerRolloverConManita());
        
    }
    
    
    public HJCustomizedButton(int tipoBoton) {
        
        this();
        
        
        switch(tipoBoton) {
            
            case SALIR : establecerBotonSalir();
                         break;
            
            default:
        }
        
    }
    
    
    public HJCustomizedButton(String texto, Color[] colores_Normal_Rollover, boolean textoRollover, boolean rolloverConManita, int grosorSubrayado) {
        
        this();
        
        
        setText(texto);
        
        setFont(new Font("Dialog", Font.BOLD, 14));
        
        establecerColores(colores_Normal_Rollover);
        
        this.grosorSubrayado = grosorSubrayado;
        
        
        setForeground(colorTextoNormal);
        
        if(textoRollover || rolloverConManita)
        {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {

                    if(textoRollover)
                        setForeground(colorTextoRollover);
                    
                    if(rolloverConManita)
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    
                    if(textoRollover)
                        setForeground(colorTextoNormal);
                    
                    if(rolloverConManita)
                        setCursor(null);
                }
            });
        }
        
    }
    
    
    private void establecerColores(Color[] colores) {
        
        if(colores!=null)
        {
            if(colores.length>0 && colores[0]!=null)
            {
                colorTextoNormal = colores[0];
                
                setForeground(colores[0]);
            }
            
            if(colores.length>1 && colores[1]!=null)
                colorTextoRollover = colores[1];
        }
    }
    
    
    //...........................
    
    
    public void establecerPopupMenu(HJPopupMenu menuPopup) {
        
        establecerPopupMenuDesplazandoX(menuPopup, 0);
    }
    
    
    public void establecerPopupMenuDesplazandoX(HJPopupMenu menuPopup, int desplazamientoX) {
        
        if(menuPopup!=null)
        {
            HJCustomizedButton boton = this;
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent me) {
                    
                    if(menuPopup.isShowing()==false)
                        menuPopup.show(boton, desplazamientoX, getHeight());
                    else
                        menuPopup.setVisible(false);
                }
            });
        }
    }
    
    
    //.............................
    
    
    private void establecerBotonSalir() {
        
        setIcon(new ImageIcon((new ImageIcon(getClass().getResource("images/SalirNormal.png"))).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        setRolloverIcon(new ImageIcon((new ImageIcon(getClass().getResource("images/SalirRollover.png"))).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        setPressedIcon(new ImageIcon((new ImageIcon(getClass().getResource("images/SalirRollover.png"))).getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
    }
    
    
    //..............................
    
    @Override
    public JToolTip createToolTip() {
        
        return new HJToolTip(getToolTipText());
    }
    
    
    //..............................
    
    @Override
    public void paintBorder(Graphics g) {
        
        if(grosorSubrayado>0)
        {
            Graphics2D g2D = (Graphics2D)g.create();
            
            g2D.setColor(getForeground());
            
            g2D.setStroke(new BasicStroke((float)grosorSubrayado, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
            
            g2D.drawLine(0, getHeight()-2, getWidth(), getHeight()-2);
            
            g2D.dispose();
        }
    }
    
    
    
    //................................................................................
    //................................................................................
    
    
    
    private class ListenerRolloverConManita extends MouseAdapter {
        
        @Override
        public void mouseEntered(MouseEvent me) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
        @Override
        public void mouseExited(MouseEvent me) {
            setCursor(null);
        }
                
        
    }
    
    
    //...............................................................................
    
    
}
