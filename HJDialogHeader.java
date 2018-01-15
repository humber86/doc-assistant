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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;


public class HJDialogHeader extends HJPaintedPanel {
    
    private int posX;
    private int posY;
    
    private JLabel lIcono;
    
    private HJLabel lTitulo;
    
    private HJCustomizedButton botonCerrar;
    
    
    public HJDialogHeader(IconoDeImagen icono, String titulo, JDialog dialogoDueno) {
        
        super(new FlowLayout(), new Color(240,240,240), new Color(225,225,225), null, null, 100, false);
        
        setLayout(new OverlayLayout(this));
        
        
        setSize(dialogoDueno.getWidth(), 36);
        setPreferredSize(new Dimension(dialogoDueno.getWidth(), 36));
        setMaximumSize(new Dimension(2048, 36));
        
        
        //..........................
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                posX = me.getX();
                posY = me.getY();
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                
                dialogoDueno.setLocation(me.getXOnScreen()-posX, me.getYOnScreen()-posY);
            }
        });
        
        
        //....................................
        
        if(icono!=null && icono.getIconHeight()>33)
            throw new IllegalArgumentException("Altura de icono (IconoDeImagen) no puede ser mayor a 33.");
        
        int alturaParaDibujarIcono = 0;
        if(icono!=null)
            alturaParaDibujarIcono = Math.round((float)((getHeight()-icono.getIconHeight())/2.0 - 1));
        
        lIcono = new JLabel(icono);
        
        JPanel panelIcono = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, alturaParaDibujarIcono));
        panelIcono.add(lIcono);
        panelIcono.setOpaque(false);
        
        
        lTitulo = new HJLabel(titulo);
        lTitulo.setFont(new Font("Dialog", Font.BOLD, 18));
        
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelTitulo.add(lTitulo);
        panelTitulo.setOpaque(false);
        
        
        botonCerrar = new HJCustomizedButton(HJCustomizedButton.SALIR);
        
        JPanel panelBotonCerrar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 4));
        panelBotonCerrar.add(botonCerrar);
        panelBotonCerrar.setOpaque(false);
        
        
        //..............................
        
        
        add(panelBotonCerrar);
        add(panelTitulo);
        add(panelIcono);
        
        
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.LINEAS));
        
    }
    
    
    public Icon obtenerIcono() {
        
        return lIcono.getIcon();
    }
    
    public void establecerIcono(Icon icono) {
        
        lIcono.setIcon(icono);
    }
    
    
    public String obtenerTitulo() {
        
        return lTitulo.getText();
    }
    
    public void establecerTitulo(String titulo) {
        
        lTitulo.setText(titulo);
    }
    
    
    public HJCustomizedButton obtenerBotonCerrar() {
        
        return botonCerrar;
    }
    
    
}
