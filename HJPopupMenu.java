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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class HJPopupMenu extends JPopupMenu {
    
    private final ArrayList<JMenuItem> arrayItems;
    
    private final Color colorLineas = new Color(150,150,150);
    
    
    public HJPopupMenu() {
        
        arrayItems = new ArrayList<>(0);
        
    }
    
    
    //........................
    
    
    @Override
    public JMenuItem add(JMenuItem menuItem) {
        
        arrayItems.add(menuItem);
        
        return super.add(menuItem);
    }
    
    
    public JMenuItem obtenerMenuItem(int posicion) {
        
        return arrayItems.get(posicion);
    }
    
    
    //............................
    
    @Override
    public void addSeparator() {
        
        JPopupMenu.Separator separador = new Separator();
        
        separador.setPreferredSize(new Dimension(getWidth(), 1));
        
        separador.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, colorLineas));
        
        add(separador);
    }
    
    
    //............................
    
    @Override
    public void paintBorder(Graphics g) {
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setColor(colorLineas);
        
        g2D.drawRect(0, 2, getWidth()-1, getHeight()-4);
        
        g2D.dispose();
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        
    }
    
    
    @Override
    public void paintChildren(Graphics g) {
        
        super.paintChildren(g);
        
        for(int i=0 ; i<=arrayItems.size()-1 ; i++)
        {
            arrayItems.get(i).setBorderPainted(false);
        }
    }
    
}
