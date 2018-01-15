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
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PanelCabecera extends JPanel {
    
    private final DocAssistant docAssistant;
    
    private Image imagen;
    
    private int posX;
    private int posY;
    
    
    public PanelCabecera(DocAssistant frameDocAssistant) {
        
        setLayout(new GridLayout(1, 1, 0, 0));
        
        setPreferredSize(new Dimension(1000,80));
        
        
        if(frameDocAssistant==null)
            throw new IllegalArgumentException("frameDocAssistant (DocAssistant) no puede ser null.");
        
        docAssistant = frameDocAssistant;
        
        
        imagen = null;
        
        imagen = (new ImageIcon(getClass().getResource("images/FondoCabecera.jpg"))).getImage();
        
        if(imagen==null)
            throw new NullPointerException("imagen (Image) es nula.");
        
        
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(100,100,100)));
        
        
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
                
                docAssistant.setLocation(me.getXOnScreen()-posX, me.getYOnScreen()-posY);
            }
        });
        
        
        //..............................
        
        HJLabel lAbanico = new HJLabel("Abanico");
        
        try{
            lAbanico.setFont(Font.createFont(Font.TRUETYPE_FONT, new File(Directorios.RECURSOS+"Montalban.ttf")).deriveFont(Font.PLAIN, (float)22.0));
        }catch(FontFormatException | IOException exc) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error en Archivos", new String[] {"Algún(os) archivo(s) del programa fue(ron) alterado(s)."}, IconoDeImagen.ERROR, null);
            return;
        }
        
        JPanel panelAbanico = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelAbanico.add(lAbanico);
        panelAbanico.setOpaque(false);
        
        HJLabel lDocAssistant = new HJLabel("Doc Assistant ");
        lDocAssistant.setFont(new Font("Arial Black", Font.PLAIN, 24));
        
        HJLabel lVersion = new HJLabel(""+Version.PRIMER_NIVEL+"."+Version.SEGUNDO_NIVEL);
        lVersion.setFont(new Font("Arial Black", Font.PLAIN, 24));
        
        JPanel panelDocAssitantVersion = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelDocAssitantVersion.add(lDocAssistant);
        panelDocAssitantVersion.add(lVersion);
        panelDocAssitantVersion.setOpaque(false);
        
        Box boxAbanicoDocAssistant = Box.createVerticalBox();
        boxAbanicoDocAssistant.add(Box.createVerticalStrut(10));
        boxAbanicoDocAssistant.add(panelAbanico);
        boxAbanicoDocAssistant.add(panelDocAssitantVersion);
        boxAbanicoDocAssistant.add(Box.createVerticalStrut(10));
        
        
        JPanel panelAbanicoDocAssistant = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelAbanicoDocAssistant.add(Box.createHorizontalStrut(15));
        panelAbanicoDocAssistant.add(new HJLabel(new IconoDeImagen("Abanico.png", -1, 60)));
        panelAbanicoDocAssistant.add(Box.createHorizontalStrut(15));
        panelAbanicoDocAssistant.add(boxAbanicoDocAssistant);
        panelAbanicoDocAssistant.add(Box.createHorizontalStrut(15));
        panelAbanicoDocAssistant.add(new HJLabel(new IconoDeImagen("LogoDocAssistant.png", -1, 60)));
        panelAbanicoDocAssistant.setOpaque(false);
        
        
        //....
        
        Box boxVerticalIzquierdo = Box.createVerticalBox();
        boxVerticalIzquierdo.add(panelAbanicoDocAssistant);
        
        
        //................................
        
        HJCustomizedButton botonMinimizar = new HJCustomizedButton("MinimizarNormal.png", "MinimizarRollover.png", 25, 25, false);
        botonMinimizar.addActionListener( e -> minimizar() );
        
        HJCustomizedButton botonSalir = new HJCustomizedButton("SalirNormal.png", "SalirRollover.png", 25, 25, false);
        botonSalir.addActionListener( e -> System.exit(0) );
        
        JPanel panelBotonesFrame = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelBotonesFrame.add(botonMinimizar);
        panelBotonesFrame.add(Box.createHorizontalStrut(6));
        panelBotonesFrame.add(botonSalir);
        panelBotonesFrame.add(Box.createHorizontalStrut(10));
        panelBotonesFrame.setOpaque(false);
        
        
        //....
        
        HJCustomizedButton botonTwitter = new HJCustomizedButton(new IconoDeImagen("Twitter.png", -1, 22), true);
        botonTwitter.setToolTipText("Síguenos en Twitter");
        botonTwitter.addActionListener( e -> Utilidades.abrirVinculo("https://twitter.com/AbanicoSystems") );
        
        HJCustomizedButton botonFacebook = new HJCustomizedButton(new IconoDeImagen("Facebook.png", -1, 22), true);
        botonFacebook.setToolTipText("Síguenos en Facebook");
        botonFacebook.addActionListener( e -> Utilidades.abrirVinculo("https://www.facebook.com/AbanicoSystems") );
        
        
        Color[] coloresBotonesSoloTexto = {Colores.BLANCO, Colores.ROLLOVER};
        
        HJCustomizedButton botonSoporte = new HJCustomizedButton("Soporte", coloresBotonesSoloTexto, true, true, 2);
        botonSoporte.addActionListener( e -> DocAssistant.mostrarSoporte() );
        
        HJCustomizedButton botonAcercaDe = new HJCustomizedButton("Acerca de", coloresBotonesSoloTexto, true, true, 2);
        botonAcercaDe.addActionListener( e -> DocAssistant.mostrarPanelAcercaDe() );
        
        HJCustomizedButton botonAyuda = new HJCustomizedButton("AyudaNormal.png", "AyudaRollover.png", 22, 22, false);
        botonAyuda.setToolTipText("Ayuda");
        botonAyuda.addActionListener( e -> DocAssistant.mostrarAyuda() );
        
        JPanel panelBotonesCabecera = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelBotonesCabecera.add(botonTwitter);
        panelBotonesCabecera.add(Box.createHorizontalStrut(20));
        panelBotonesCabecera.add(botonFacebook);
        panelBotonesCabecera.add(Box.createHorizontalStrut(35));
        panelBotonesCabecera.add(botonSoporte);
        panelBotonesCabecera.add(Box.createHorizontalStrut(20));
        panelBotonesCabecera.add(botonAcercaDe);
        panelBotonesCabecera.add(Box.createHorizontalStrut(35));
        panelBotonesCabecera.add(botonAyuda);
        panelBotonesCabecera.add(Box.createHorizontalStrut(12));
        panelBotonesCabecera.setOpaque(false);
        
        
        //....
        
        Box boxVerticalDerecho = Box.createVerticalBox();
        boxVerticalDerecho.add(Box.createVerticalStrut(5));
        boxVerticalDerecho.add(panelBotonesFrame);
        boxVerticalDerecho.add(Box.createVerticalStrut(10));
        boxVerticalDerecho.add(panelBotonesCabecera);
        
        
        //...............................
        
        
        Box cajaGeneral = Box.createHorizontalBox();
        cajaGeneral.add(boxVerticalIzquierdo);
        cajaGeneral.add(boxVerticalDerecho);
        
        
        add(cajaGeneral);
        
    }
    
    
    private void minimizar() {
        
        if(Toolkit.getDefaultToolkit().isFrameStateSupported(JFrame.ICONIFIED))
            docAssistant.setExtendedState(JFrame.ICONIFIED);
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.drawImage(imagen, 0, 0, imagen.getWidth(null), imagen.getHeight(null), null);
        
        g2D.dispose();
    }
    
}
