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
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;


public class PanelAcercaDe extends HJDialog {
    
    private final String PRUEBA = "Prueba";
    private final String ACTIVADA = "Activada";
    
    private HJLabel lTipoLicencia;
    
    private HJCustomizedButton botonActivar;
    
    
    public PanelAcercaDe() {
        
        super(null, "Acerca de...", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //.............................
        
        lTipoLicencia = new HJLabel();
        
        botonActivar = new HJCustomizedButton("Activar", new Color[] {Colores.AZUL_LLAMATIVO, Colores.AZUL_OSCURO}, true, true, 1);
        botonActivar.addActionListener( e -> {
            
            PanelAdquirirLicencia panelAdquirirLicencia = new PanelAdquirirLicencia();
            panelAdquirirLicencia.setVisible(true);
            
            establecerLicenciaActivada(Registro.estaProductoActivado());
        });
        
        
        //.............................
        
        HJLabel lAbanico = new HJLabel("Abanico");
        
        try{
            lAbanico.setFont(Font.createFont(Font.TRUETYPE_FONT, new File(Directorios.RECURSOS+"Montalban.ttf")).deriveFont(Font.PLAIN, (float)18.0));
        }catch(FontFormatException | IOException exc) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error en Archivos", new String[] {"Algún(os) archivo(s) del programa fue(ron) alterado(s)."}, IconoDeImagen.ERROR, null);
            return;
        }
        
        JPanel panelAbanico = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelAbanico.add(lAbanico);
        panelAbanico.setMaximumSize(new Dimension(200, 24));
        panelAbanico.setOpaque(false);
        
        HJLabel lDocAssistant = new HJLabel("Doc Assistant");
        lDocAssistant.setFont(new Font("Arial Black", Font.PLAIN, 20));
        
        JPanel panelDocAssistant = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelDocAssistant.add(lDocAssistant);
        panelDocAssistant.setMaximumSize(new Dimension(200, 24));
        panelDocAssistant.setOpaque(false);
        
        HJLabel lVersionGrande = new HJLabel(""+Version.PRIMER_NIVEL+"."+Version.SEGUNDO_NIVEL);
        lVersionGrande.setFont(new Font("Arial Black", Font.PLAIN, 20));
        
        JPanel panelVersionGrande = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelVersionGrande.add(lVersionGrande);
        panelVersionGrande.setMaximumSize(new Dimension(200, 24));
        panelVersionGrande.setOpaque(false);
        
        
        Box boxAbanicoDocAssistant = Box.createVerticalBox();
        boxAbanicoDocAssistant.add(panelAbanico);
        boxAbanicoDocAssistant.add(panelDocAssistant);
        boxAbanicoDocAssistant.add(panelVersionGrande);
        
        
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelSuperior.add(Box.createHorizontalStrut(10));
        panelSuperior.add(new HJLabel(new IconoDeImagen("LogoAbanico.png", 110, -1)));
        panelSuperior.add(Box.createHorizontalStrut(50));
        panelSuperior.add(boxAbanicoDocAssistant);
        panelSuperior.add(Box.createHorizontalStrut(50));
        panelSuperior.add(new HJLabel(new IconoDeImagen("LogoADA.png", 80, -1)));
        panelSuperior.add(Box.createHorizontalStrut(10));
        panelSuperior.setOpaque(false);
        
        panelSuperior.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.LINEAS));
        
        
        //............................
        
        JPanel panelCopyright = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCopyright.add(new HJLabel("Copyright © 2015 Abanico Systems"));
        panelCopyright.setOpaque(false);
        
        JPanel panelTodosDerechosReservados = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelTodosDerechosReservados.add(new HJLabel("Todos los Derechos Reservados"));
        panelTodosDerechosReservados.setOpaque(false);
        
        
        JPanel panelLinea1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea1.add(new HJLabel("ADVERTENCIA: Este programa está protegido por leyes de"));
        panelLinea1.setOpaque(false);
        
        JPanel panelLinea2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea2.add(new HJLabel("derecho de autor y otros tratados internacionales. La"));
        panelLinea2.setOpaque(false);
        
        JPanel panelLinea3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea3.add(new HJLabel("reproducción o distribución ilícita de este programa, o"));
        panelLinea3.setOpaque(false);
        
        JPanel panelLinea4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea4.add(new HJLabel("parte del mismo, está penada por la ley con severas"));
        panelLinea4.setOpaque(false);
        
        JPanel panelLinea5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea5.add(new HJLabel("sanciones civiles y penales, y será objeto de todas las"));
        panelLinea5.setOpaque(false);
        
        JPanel panelLinea6 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea6.add(new HJLabel("acciones judiciales que correspondan."));
        panelLinea6.setOpaque(false);
        
        Box boxAdvertencia = Box.createVerticalBox();
        boxAdvertencia.add(panelLinea1);
        boxAdvertencia.add(panelLinea2);
        boxAdvertencia.add(panelLinea3);
        boxAdvertencia.add(panelLinea4);
        boxAdvertencia.add(panelLinea5);
        boxAdvertencia.add(panelLinea6);
        
        boxAdvertencia.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15), BorderFactory.createCompoundBorder(BorderFactory.createLoweredBevelBorder(), BorderFactory.createRaisedBevelBorder())));
        
        
        //....
        
        Box boxCentral = Box.createVerticalBox();
        boxCentral.add(Box.createVerticalStrut(15));
        boxCentral.add(panelCopyright);
        boxCentral.add(panelTodosDerechosReservados);
        boxCentral.add(Box.createVerticalStrut(15));
        boxCentral.add(boxAdvertencia);
        boxCentral.add(Box.createVerticalStrut(15));
        
        boxCentral.setOpaque(true);
        boxCentral.setBackground(Colores.NORMAL_OSCURO);
        
        
        //............................
        
        HJLabel lVersionPequena = new HJLabel(""+Version.PRIMER_NIVEL+"."+Version.SEGUNDO_NIVEL+"."+Version.TERCER_NIVEL);
        
        HJCustomizedButton botonComprobarActualizaciones = new HJCustomizedButton("Comprobar actualizaciones", new Color[] {Colores.AZUL_LLAMATIVO, Colores.AZUL_OSCURO}, true, true, 1);
        botonComprobarActualizaciones.setFont(new Font("Arial", Font.BOLD, 14));
        botonComprobarActualizaciones.addActionListener( e -> DocAssistant.comprobarActualizaciones() );
        
        JPanel panelVersionPequena = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelVersionPequena.add(new HJLabel("Versión: "));
        panelVersionPequena.add(lVersionPequena);
        panelVersionPequena.add(Box.createHorizontalStrut(20));
        panelVersionPequena.add(botonComprobarActualizaciones);
        panelVersionPequena.setOpaque(false);
        
        
        JPanel panelLicencia = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelLicencia.add(new HJLabel("Licencia: "));
        panelLicencia.add(lTipoLicencia);
        panelLicencia.add(Box.createHorizontalStrut(20));
        panelLicencia.add(botonActivar);
        panelLicencia.setOpaque(false);
        
        
        establecerLicenciaActivada(Registro.estaProductoActivado());
        
        
        Box boxMedio = Box.createVerticalBox();
        boxMedio.add(panelVersionPequena);
        boxMedio.add(Box.createVerticalStrut(5));
        boxMedio.add(panelLicencia);
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelInferior.add(boxMedio);
        panelInferior.setOpaque(false);
        
        panelInferior.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //............................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(panelSuperior);
        cajaGeneral.add(boxCentral);
        cajaGeneral.add(panelInferior);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private void establecerLicenciaActivada(boolean activada) {
        
        if(activada)
        {
            lTipoLicencia.setText(ACTIVADA);
            
            botonActivar.setVisible(false);
            
        }else{
            
            lTipoLicencia.setText(PRUEBA);
            
            botonActivar.setVisible(true);
        }
    }
    
    
}
