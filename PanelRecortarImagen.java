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
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;


public class PanelRecortarImagen extends HJDialog {
    
    private boolean seRecortoImagen = false;
    private Image imagenRecortada;
    
    
    public PanelRecortarImagen(Image imagen) {
        
        super(null, "Recortar Imagen", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //.........................
        
        PanelFoto panelFoto = new PanelFoto(imagen);
        
        
        //.........................
        
        HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
        bAceptar.addActionListener( e -> {
            
            seRecortoImagen = true;
            
            imagenRecortada = panelFoto.obtenerImagen();
            
            setVisible(false);
            dispose();
        });
        
        HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
        bCancelar.addActionListener( e -> {
            
            setVisible(false);
            dispose();
        });
                
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelBotones.add(bAceptar);
        panelBotones.add(Box.createHorizontalStrut(30));
        panelBotones.add(bCancelar);
        panelBotones.setOpaque(false);
        
        panelBotones.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //.......................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(panelFoto);
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    public boolean seRecortoImagen() { return seRecortoImagen; }
    
    
    public Image obtenerImagenRecortada() { return imagenRecortada; }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class PanelFoto extends JPanel implements MouseListener, MouseMotionListener {
        
        private final int anchoPanel = 600;
        private final int alturaPanel = 600;
        
        private final Image imagenReal;
        private int anchoImagenReal;
        private int alturaImagenReal;
        private Image imagenEscalada;
        private int anchoImagen;
        private int alturaImagen;
        private boolean esHorizontal;
        private boolean esMuyAlto;
        private int margenX;
        private int margenY;
        
        private Rectangle rectangulo;
        private int anchoRectangulo = 200;
        private int alturaRectangulo = 300;
        private final int minimoAnchoRectangulo = 130;
        private final int minimoAlturaRectangulo = 195;
        private int ladoIzquierdoRectangulo;
        private int ladoSuperiorRectangulo;
        
        private Ellipse2D.Double circulo;
        private final int radioCirculo = 15;
        
        private int pX;
        private int pY;
        private int nuevoPX;
        private int nuevoPY;
        private int deltaX;
        private int deltaY;
        private boolean estaEnRectangulo;
        private boolean estaEnCirulo;
        
        
        public PanelFoto(Image imagen) {
            
            setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            
            setSize(anchoPanel, alturaPanel);
            setPreferredSize(new Dimension(anchoPanel, alturaPanel));
            
            
            imagenReal = imagen;
            
            inicializarValores();
            
            
            anadirMouseListeners();
            
        }
        
        
        private void anadirMouseListeners() {
            
            addMouseListener(this);
            
            addMouseMotionListener(this);
        }
        
        
        //..........................
        
        
        public Image obtenerImagen() {
            
            BufferedImage imagenBuffered = new BufferedImage(anchoImagen, alturaImagen, BufferedImage.TYPE_INT_ARGB);
               
            Graphics2D g2D = imagenBuffered.createGraphics();
            
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2D.drawImage(imagenEscalada, 0, 0, null);
            
            
            if(ladoIzquierdoRectangulo<margenX)
                ladoIzquierdoRectangulo = margenX;
            
            if(ladoSuperiorRectangulo<margenY)
                ladoSuperiorRectangulo = margenY;
            
            BufferedImage imagenBufferedCortada = imagenBuffered.getSubimage(ladoIzquierdoRectangulo - margenX, ladoSuperiorRectangulo - margenY, anchoRectangulo, alturaRectangulo);
            
            
            return (Image)imagenBufferedCortada;
        }
        
        
        private void inicializarValores() {
            
            anchoImagenReal = imagenReal.getWidth(null);
            alturaImagenReal = imagenReal.getHeight(null);
            
            
            esHorizontal = anchoImagenReal>=alturaImagenReal;
            
            if(esHorizontal)
            {
                anchoImagen = anchoPanel;
                alturaImagen = Math.round(((float)anchoImagen)*((float)alturaImagenReal/(float)anchoImagenReal));
                
                margenX = 0;
                margenY = Math.round((float)(((float)alturaPanel-(float)alturaImagen)/2.0));
                
            }else{
                
                alturaImagen = alturaPanel;
                anchoImagen = Math.round(((float)alturaImagen)*((float)anchoImagenReal/(float)alturaImagenReal));
                
                margenX = Math.round((float)(((float)anchoPanel-(float)anchoImagen)/2.0));
                margenY = 0;
                
                float relacionLateralImagen = (float)alturaImagen/(float)anchoImagen;
                float relacionLateralRectangulo = (float)alturaRectangulo/(float)anchoRectangulo;
                
                esMuyAlto = relacionLateralImagen>relacionLateralRectangulo;
            }
            
            
            imagenEscalada = imagenReal.getScaledInstance(anchoImagen, alturaImagen, Image.SCALE_SMOOTH);
            
            
            rectangulo = new Rectangle(Math.round((float)(((float)anchoPanel-(float)anchoRectangulo)/2.0)), Math.round((float)(((float)alturaPanel-(float)alturaRectangulo)/2.0)), anchoRectangulo, alturaRectangulo);
            
            ladoIzquierdoRectangulo = rectangulo.x;
            ladoSuperiorRectangulo = rectangulo.y;
            
            
            circulo = new Ellipse2D.Double((anchoPanel+anchoRectangulo)/2.0 - radioCirculo, (alturaPanel+alturaRectangulo)/2.0 - radioCirculo, radioCirculo*2, radioCirculo*2);
            
            
            repaint();
        }
        
        
        //.........................
        
        @Override
        public void mouseEntered(MouseEvent me) {
            
        }
        
        
        @Override
        public void mouseExited(MouseEvent me) {
            
        }
        
        
        @Override
        public void mousePressed(MouseEvent me) {
            
            pX = me.getX();
            pY = me.getY();
            
            estaEnRectangulo = rectangulo.contains(pX, pY);
            
            if(circulo.contains(pX, pY))
            {
                estaEnCirulo = true;
                
                estaEnRectangulo = false;
                
            }else{
                
                estaEnCirulo = false;
            }
        }
        
        
        @Override
        public void mouseReleased(MouseEvent me) {
            
        }
        
        
        @Override
        public void mouseClicked(MouseEvent me) {
            
        }
        
        
        //......
        
        @Override
        public void mouseMoved(MouseEvent me) {
            
            if(rectangulo.contains(me.getX(), me.getY()))
                setCursor(new Cursor(Cursor.MOVE_CURSOR));
            else
                setCursor(null);
            
            if(circulo.contains(me.getX(), me.getY()))
            {
                setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                
            }else{
                
                if(rectangulo.contains(me.getX(), me.getY())==false)
                    setCursor(null);
            }
        }
        
        
        @Override
        public void mouseDragged(MouseEvent me) {
            
            nuevoPX = me.getX();
            deltaX = nuevoPX-pX;
            
            nuevoPY = me.getY();
            deltaY = nuevoPY-pY;
            
            if(estaEnRectangulo)
            {
                ladoIzquierdoRectangulo += deltaX;
                ladoSuperiorRectangulo += deltaY;
            }
            
            if(estaEnCirulo)
            {
                anchoRectangulo += deltaX;
                alturaRectangulo += deltaY;
            }
            
            pX = nuevoPX;
            pY = nuevoPY;
            
            
            repaint();
        }
        
        
        //.........................
        
        @Override
        public void paintComponent(Graphics g) {
            
            super.paintComponent(g);
            
            
            if(imagenEscalada!=null)
            {
                Graphics2D g2D = (Graphics2D)g.create();
                
                g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                
                g2D.drawImage(imagenEscalada, margenX, margenY, null);
                
                g2D.setColor(new Color(255, 255, 255, 100));
                
                g2D.fillRect(margenX, margenY, anchoImagen, alturaImagen);
                
                
                if(anchoRectangulo<minimoAnchoRectangulo)
                    anchoRectangulo = minimoAnchoRectangulo;
                
                if(alturaRectangulo<minimoAlturaRectangulo)
                    alturaRectangulo = minimoAlturaRectangulo;
                
                
                if(esHorizontal || esMuyAlto==false)
                {
                    if(alturaRectangulo>alturaImagen)
                        alturaRectangulo = alturaImagen;
                    
                    anchoRectangulo = Math.round((float)((float)alturaRectangulo*(2.0/3.0)));
                    
                }else{
                    
                    if(anchoRectangulo>anchoImagen)
                        anchoRectangulo = anchoImagen;
                    
                    alturaRectangulo = Math.round((float)((float)anchoRectangulo*1.5));
                }
                
                if(ladoIzquierdoRectangulo<margenX)
                    ladoIzquierdoRectangulo = margenX;
                
                if(ladoIzquierdoRectangulo>(getWidth()-margenX-anchoRectangulo))
                    ladoIzquierdoRectangulo = getWidth()-margenX-anchoRectangulo;
                
                if(ladoSuperiorRectangulo<margenY)
                    ladoSuperiorRectangulo = margenY;
                
                if(ladoSuperiorRectangulo>(getHeight()-margenY-alturaRectangulo))
                    ladoSuperiorRectangulo = getHeight()-margenY-alturaRectangulo;
                
                
                rectangulo.setFrame(ladoIzquierdoRectangulo, ladoSuperiorRectangulo, anchoRectangulo, alturaRectangulo);
                
                g2D.clip(rectangulo);
                
                g2D.drawImage(imagenEscalada, margenX, margenY, null);
                
                
                g2D.setClip(null);
                
                g2D.setColor(new Color(0,0,0,150));
                
                circulo.setFrame(rectangulo.x+rectangulo.width-radioCirculo, rectangulo.y+rectangulo.height-radioCirculo, radioCirculo*2, radioCirculo*2);
                
                g2D.fill(circulo);
                
                
                g2D.dispose();
            }
        }
        
        
    }
    
    
    //..............................................................................
    
    
}
