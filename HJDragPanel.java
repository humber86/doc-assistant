/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class HJDragPanel extends JPanel {
    
    private int posX;
    private int posY;
    
    
    public HJDragPanel() {
        
        
    }
    
    
    public HJDragPanel(JFrame ventanaAMover) {
        
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
        
    }
    
    
    public HJDragPanel(JDialog dialogoAMover) {
        
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
                
                dialogoAMover.setLocation(me.getXOnScreen()-posX, me.getYOnScreen()-posY);
            }
        });
        
    }
    
    
}
