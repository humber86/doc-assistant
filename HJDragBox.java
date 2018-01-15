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
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.Box;


public class HJDragBox extends Box {
    
    private int posX;
    private int posY;
    
    
    public HJDragBox(int direccionContenido, Window ventanaAMover) {
        
        super(direccionContenido);
        
        
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
                
                ventanaAMover.setLocation(me.getXOnScreen()-posX, me.getYOnScreen()-posY);
            }
        });
        
        
        setOpaque(true);
        setBackground(new Color(230,230,230));
        
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.LINEAS));
        
    }
    
    
}
