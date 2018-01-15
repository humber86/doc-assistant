/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.Box;


public class HJBox extends Box {
    
    private Image imagen;
    
    private boolean conRectangulo = false;
    private int fuenteX1;
    private int fuenteY1;
    private int fuenteX2;
    private int fuenteY2;
    
    
    public HJBox(int direccionContenido, Image imagenFondo) {
        
        super(direccionContenido);
        
        imagen = imagenFondo;
        
    }
    
    
    public HJBox(int direccionContenido, Image imagenFondoImage, int[] cuadroFuente) {
        
        this(direccionContenido, imagenFondoImage);
        
        if(cuadroFuente.length!=4)
            throw new IllegalArgumentException("cuadroFuente (int[]) debe contener 4 puntos.");
        
        fuenteX1 = cuadroFuente[0];
        fuenteY1 = cuadroFuente[1];
        fuenteX2 = cuadroFuente[2];
        fuenteY2 = cuadroFuente[3];
        
        Rectangle rectanguloFuente = new Rectangle(0, 0, imagen.getWidth(null), imagen.getHeight(null));
        
        if(fuenteX2==rectanguloFuente.width)
            fuenteX2--;
        
        if(fuenteY2==rectanguloFuente.height)
            fuenteY2--;
        
        if(rectanguloFuente.contains(fuenteX1, fuenteY1)==false || rectanguloFuente.contains(fuenteX2, fuenteY2)==false)
            throw new IllegalArgumentException("Puntos en cuadroFuente (int[]) fuera de la imagen fuente.");
        
        conRectangulo = true;
        
    }
    
    
    //...........................
    
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if(conRectangulo==false)
            g2D.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        else
            g2D.drawImage(imagen, 0, 0, getWidth(), getHeight(), fuenteX1, fuenteY1, fuenteX2, fuenteY2, null);
        
        g2D.dispose();
    }
    
}
