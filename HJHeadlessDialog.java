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
import java.awt.Component;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.border.Border;


public class HJHeadlessDialog extends JDialog {
    
    private final Box cajaVertical;
    
    
    public HJHeadlessDialog(Frame ventanaDuena, boolean modalidadTope) {
        
        super(ventanaDuena, modalidadTope);
        
        setLayout(new GridLayout(1, 1, 0, 0));
        
        
        setUndecorated(true);
        
        if(estaSoportadaTransparencia())
            setShape(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        
        
        //............................
        
        
        cajaVertical = Box.createVerticalBox();
        
        cajaVertical.setOpaque(true);
        cajaVertical.setBackground(Colores.NORMAL);
        
        cajaVertical.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Colores.LINEAS));
        
        
        super.add(cajaVertical);
        
    }
    
    
    private boolean estaSoportadaTransparencia() {
        
        GraphicsEnvironment entornoGrafico = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        GraphicsDevice[] dispositivosGraficos = entornoGrafico.getScreenDevices();
        
        boolean estaSoportada = true;
        
        for(int i=0 ; i<=dispositivosGraficos.length-1 ; i++)
        {
            if(dispositivosGraficos[i].isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT)==false)
                estaSoportada = false;
        }
        
        return estaSoportada;
    }
    
    
    //................................
    
    
    @Override
    public Component add(Component component) {
        
        cajaVertical.add(component);
        
        return component;
    }
    
    
    public void setBackgroundColor(Color color) {
        
        cajaVertical.setBackground(color);
    }
    
    
    public void setBorder(Border borde) {
        
        cajaVertical.setBorder(borde);
    }
    
    
}
