/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.JPanel;


public class PanelCaptura extends HJDialog {
    
    private boolean seCapturoImagen = false;
    private Image imagenCapturada;
    
    
    public PanelCaptura(Webcam webcam) {
        
        super(new IconoDeImagen("Capturar.png", -1, 30), "Captura de Imagen", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        if(webcam==null)
            throw new IllegalArgumentException("webcamSeleccionada (Webcam) no puede ser nulo.");
        
        
        //..........................
        
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        
        
        WebcamPanel panelWebcam = new WebcamPanel(webcam);
        
        PanelWebcam panelWebcamMacro = new PanelWebcam();
        panelWebcamMacro.add(panelWebcam);
        
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelCentral.add(panelWebcamMacro);
        panelCentral.setOpaque(false);
        
        
        //..........................
        
        Webcam.addDiscoveryListener(new WebcamDiscoveryListener() {
            
            @Override
            public void webcamFound(WebcamDiscoveryEvent wde) {
                
            }
            
            @Override
            public void webcamGone(WebcamDiscoveryEvent wde) {
                
                cerrarWebcam(webcam, panelWebcam, panelWebcamMacro);
            }
        });
        
        
        //....
        
        HJButton bCapturar = new HJButton(new IconoDeImagen("Camara.png", -1, 20), "Capturar", Colores.COLORES_BOTONES);
        bCapturar.addActionListener( e ->  {
            
            seCapturoImagen = true;
            
            imagenCapturada = (Image)webcam.getImage();
            
            setVisible(false);
            dispose();
        });
        
        JPanel panelBotonCaptura = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelBotonCaptura.add(bCapturar);
        panelBotonCaptura.setOpaque(false);
        
        
        //.....................
        
        addWindowListener( new WindowAdapter() {
            
            @Override
            public void windowClosed(WindowEvent we) {
                
                cerrarWebcam(webcam, panelWebcam, panelWebcamMacro);
            }
        });
        
        
        //.....................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelCentral);
        cajaGeneral.add(panelBotonCaptura);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private void cerrarWebcam(Webcam webcam, WebcamPanel panelWebcam, PanelWebcam panelWebcamMacro) {
        
        if(webcam!=null)
        {
            panelWebcam.stop();
            
            webcam.close();
            
            panelWebcamMacro.remove(panelWebcam);
            panelWebcamMacro.validate();
            panelWebcamMacro.repaint();
            
            validate();
            repaint();
        }
    }
    
    
    //................................
    
    public boolean seCapturoImagen() { return seCapturoImagen; }
    
    
    public Image obtenerImagenCapturada() { return imagenCapturada; }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class PanelWebcam extends JPanel {
        
        
        public PanelWebcam() {
            
            setLayout(new GridLayout(1, 1, 0, 0));
            
            setPreferredSize(WebcamResolution.VGA.getSize());
            
        }
        
        
        @Override
        public void paintComponent(Graphics g) {
            
            super.paintComponent(g);
            
            Graphics2D g2D = (Graphics2D)g.create();
            
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            g2D.setColor(new Color(50,50,50));
            
            g2D.fillRect(0, 0, getWidth(), getHeight());
            
            g2D.setColor(Colores.NORMAL);
            g2D.setFont(new Font("Arial", Font.BOLD, 26));
            
            g2D.drawString("No hay imagen.", (float)((getWidth()-g2D.getFontMetrics().stringWidth("No hay imagen."))/2.0), (float)((getHeight()+g2D.getFont().getSize())/2.0));
            
            g2D.dispose();
        }
        
        
    }
    
    
    //..............................................................................
    
    
}
