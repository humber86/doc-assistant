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
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class HJDialog extends JDialog {
    
    private final HJDialogHeader cabecera;
    
    private final Box cajaVertical;
    
    
    public HJDialog(IconoDeImagen icono, String titulo, Frame ventanaDuena, boolean modalidadTope) {
        
        super(ventanaDuena, modalidadTope);
        
        setLayout(new GridLayout(1, 1, 0, 0));
        
        
        setUndecorated(true);
        
        if(estaSoportadaTransparencia())
            setShape(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        
        
        if(icono!=null)
            setIconImage(icono.getImage());
        
        
        //............................
        
        cabecera = new HJDialogHeader(icono, titulo, this);
        
        
        cajaVertical = Box.createVerticalBox();
        cajaVertical.add(cabecera);
        
        
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
    
    
    //.................................
    
    
    public Icon obtenerIcono() {
        
        return cabecera.obtenerIcono();
    }
    
    public void establecerIcono(Icon icono) {
        
        cabecera.establecerIcono(icono);
    }
    
    
    public String obtenerTitulo() {
        
        return cabecera.obtenerTitulo();
    }
    
    public void establecerTitulo(String titulo) {
        
        cabecera.establecerTitulo(titulo);
    }
    
    
    public HJCustomizedButton obtenerBotonCerrar() {
        
        return cabecera.obtenerBotonCerrar();
    }
    
    public void anadirActionListenerABotonCerrar(ActionListener escuchador) {
        
        cabecera.obtenerBotonCerrar().addActionListener(escuchador);
    }
    
    
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
    
    
    //.................................
    
    
    public static void mostrarMensaje(String titulo, String[] lineasDeTexto, String tipoIconoMensaje, Frame ventanaDuenaFrame) {
        
        HJDialog dialogo = new HJDialog(null, titulo, ventanaDuenaFrame, true);
        
        dialogo.anadirActionListenerABotonCerrar( e -> {
            
            dialogo.setVisible(false);
            dialogo.dispose();
        });
        
        
        JLabel lIcono = new JLabel(new IconoDeImagen(tipoIconoMensaje, -1, 50));
        
        Box boxTexto = Box.createVerticalBox();
        for(int i=0 ; i<=lineasDeTexto.length-1 ; i++)
        {
            JPanel panelTexto = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panelTexto.add(new HJLabel(lineasDeTexto[i]));
            panelTexto.setOpaque(false);
            
            boxTexto.add(panelTexto);
        }
        
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCentral.add(Box.createHorizontalStrut(20));
        panelCentral.add(lIcono);
        panelCentral.add(Box.createHorizontalStrut(20));
        panelCentral.add(boxTexto);
        panelCentral.add(Box.createHorizontalStrut(30));
        panelCentral.setOpaque(false);
        
        
        HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
        bAceptar.addActionListener(e -> {
            
            dialogo.setVisible(false);
            dialogo.dispose();
        });
        
        JPanel panelBotonAceptar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonAceptar.add(Box.createHorizontalStrut(30));
        panelBotonAceptar.add(bAceptar);
        panelBotonAceptar.add(Box.createHorizontalStrut(30));
        panelBotonAceptar.setOpaque(false);
        
        
        dialogo.add(Box.createVerticalStrut(15));
        dialogo.add(panelCentral);
        dialogo.add(Box.createVerticalStrut(15));
        dialogo.add(panelBotonAceptar);
        dialogo.add(Box.createVerticalStrut(15));
        
        dialogo.pack();
        
        dialogo.setLocationRelativeTo(null);
        
        dialogo.setVisible(true);
    }
    
    
    public static int mostrarDialogoPregunta(String titulo, String[] lineasDeTexto, HJButton[] opciones, int opcionSiSeCierra, String tipoIcono, Frame ventanaDuenaFrame) {
        
        HJDialog dialogo = new HJDialog(null, titulo, ventanaDuenaFrame, true);
        
        
        Box boxTexto = Box.createVerticalBox();
        for(int i=0 ; i<=lineasDeTexto.length-1 ; i++)
        {
            JPanel panelTexto = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panelTexto.add(new HJLabel(lineasDeTexto[i]));
            panelTexto.setOpaque(false);
            
            boxTexto.add(panelTexto);
        }
        
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCentral.add(Box.createHorizontalStrut(20));
        if(tipoIcono!=null)
        {
            panelCentral.add(new JLabel(new IconoDeImagen(tipoIcono, -1, 50)));
            panelCentral.add(Box.createHorizontalStrut(20));
        }
        panelCentral.add(boxTexto);
        panelCentral.add(Box.createHorizontalStrut(30));
        panelCentral.setOpaque(false);
        
        
        Respuesta respuesta = new Respuesta();
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotones.add(Box.createHorizontalStrut(30));
        for(int i=0 ; i<=opciones.length-1 ; i++)
        {
            final Integer valor = i;
            
            opciones[i].addActionListener( e -> {
                
                respuesta.establecerValorPrimitivo(valor);
                
                dialogo.setVisible(false);
                dialogo.dispose();
            });
            
            panelBotones.add(opciones[i]);
            panelBotones.add(Box.createHorizontalStrut(30));
        }
        panelBotones.setOpaque(false);
        
        
        dialogo.add(Box.createVerticalStrut(15));
        dialogo.add(panelCentral);
        dialogo.add(Box.createVerticalStrut(15));
        dialogo.add(panelBotones);
        dialogo.add(Box.createVerticalStrut(15));
        
        dialogo.anadirActionListenerABotonCerrar( e -> {
            
            respuesta.establecerValorPrimitivo(opcionSiSeCierra);
            
            dialogo.setVisible(false);
            dialogo.dispose();
        });
        
        dialogo.pack();
        
        dialogo.setLocationRelativeTo(null);
        
        dialogo.setVisible(true);
        
        
        return respuesta.obtenerValorPrimitivo();
    }
    
    
    public static Object mostrarDialogoSelector(String titulo, String[] lineasDeTexto, JComboBox<Object> opciones, boolean primeraOpcionValida, HJButton boton, String tipoIcono, Frame ventanaDuenaFrame) {
        
        HJDialog dialogo = new HJDialog(null, titulo, ventanaDuenaFrame, true);
        
        
        JLabel lIcono = new JLabel(new IconoDeImagen(tipoIcono, -1, 50));
        
        Box boxTexto = Box.createVerticalBox();
        for(int i=0 ; i<=lineasDeTexto.length-1 ; i++)
        {
            JPanel panelTexto = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panelTexto.add(new HJLabel(lineasDeTexto[i]));
            panelTexto.setOpaque(false);
            
            boxTexto.add(panelTexto);
        }
        
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCentral.add(Box.createHorizontalStrut(20));
        panelCentral.add(lIcono);
        panelCentral.add(Box.createHorizontalStrut(20));
        panelCentral.add(boxTexto);
        panelCentral.add(Box.createHorizontalStrut(30));
        panelCentral.setOpaque(false);
        
        
        if(primeraOpcionValida==false)
        {
            boton.setEnabled(false);
            
            opciones.addItemListener( e -> {
                
                boton.setEnabled(opciones.getSelectedIndex()>0);
            });
        }
        
        Respuesta respuesta = new Respuesta();
        
        boton.addActionListener( e -> {
            
            respuesta.establecerValorObjeto(opciones.getSelectedItem());
            
            dialogo.setVisible(false);
            dialogo.dispose();
        });
        
        
        JPanel panelSelector = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelSelector.add(opciones);
        panelSelector.add(boton);
        panelSelector.setOpaque(false);
        
        
        dialogo.add(Box.createVerticalStrut(15));
        dialogo.add(panelCentral);
        dialogo.add(Box.createVerticalStrut(15));
        dialogo.add(panelSelector);
        dialogo.add(Box.createVerticalStrut(15));
        
        dialogo.anadirActionListenerABotonCerrar( e -> {
            
            respuesta.establecerValorObjeto(null);
            
            dialogo.setVisible(false);
            dialogo.dispose();
        });
        
        dialogo.pack();
        
        dialogo.setLocationRelativeTo(null);
        
        dialogo.setVisible(true);
        
        
        return respuesta.obtenerValorObjeto();
    } 
    
    
}
