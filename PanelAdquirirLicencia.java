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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;


public class PanelAdquirirLicencia extends HJDialog {
    
    
    public PanelAdquirirLicencia() {
        
        super(null, "Adquisición de Licencia de Uso", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //...............................
        
        HJLabel lAbanico = new HJLabel("Abanico");
        
        try{
            lAbanico.setFont(Font.createFont(Font.TRUETYPE_FONT, new File(Directorios.RECURSOS+"Montalban.ttf")).deriveFont(Font.PLAIN, (float)18.0));
        }catch(FontFormatException | IOException exc) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error en Archivos", new String[] {"Algún(os) archivo(s) del programa fue(ron) alterado(s)."}, IconoDeImagen.ERROR, null);
            return;
        }
        
        HJLabel lDocAssistant = new HJLabel(" Doc Assistant");
        lDocAssistant.setFont(new Font("Arial Black", Font.PLAIN, 20));
        
        JPanel panelAbanicoDocAssistant = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelAbanicoDocAssistant.add(lAbanico);
        panelAbanicoDocAssistant.add(lDocAssistant);
        panelAbanicoDocAssistant.setOpaque(false);
        
        
        HJLabel lAdquirirLicencia = new HJLabel("Adquisición de Licencia de Uso");
        lAdquirirLicencia.setFont(new Font("Arial Black", Font.PLAIN, 18));
        lAdquirirLicencia.setForeground(Colores.AZUL_LLAMATIVO);
        
        JPanel panelAdquirirLicencia = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelAdquirirLicencia.add(lAdquirirLicencia);
        panelAdquirirLicencia.setOpaque(false);
        
        
        Box boxMedio = Box.createVerticalBox();
        boxMedio.add(panelAbanicoDocAssistant);
        boxMedio.add(panelAdquirirLicencia);
        
        
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelSuperior.add(Box.createHorizontalStrut(10));
        panelSuperior.add(new HJLabel(new IconoDeImagen("LogoAbanico.png", 110, -1)));
        panelSuperior.add(Box.createHorizontalStrut(50));
        panelSuperior.add(boxMedio);
        panelSuperior.add(Box.createHorizontalStrut(50));
        panelSuperior.add(new HJLabel(new IconoDeImagen("LogoADA.png", 80, -1)));
        panelSuperior.add(Box.createHorizontalStrut(10));
        panelSuperior.setOpaque(false);
        
        panelSuperior.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.LINEAS));
        
        
        //.............................
        
        Font formatoMensaje = new Font("Arial", Font.PLAIN, 14);
        
        HJLabel lParte1 = new HJLabel("Diríjase a nuestra ");
        lParte1.setFont(formatoMensaje);
        
        HJLabel lParte2 = new HJLabel("página web ");
        
        HJLabel lParte3 = new HJLabel("con el ");
        lParte3.setFont(formatoMensaje);
        
        HJLabel lParte4 = new HJLabel("ID de su sistema");
        
        JPanel panelLinea1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea1.add(lParte1);
        panelLinea1.add(lParte2);
        panelLinea1.add(lParte3);
        panelLinea1.add(lParte4);
        panelLinea1.setOpaque(false);
        
        
        HJLabel lParte5 = new HJLabel("para obtener su ");
        lParte5.setFont(formatoMensaje);
        
        HJLabel lParte6 = new HJLabel("Licencia de Uso");
        
        HJLabel lParte7 = new HJLabel(".");
        lParte7.setFont(formatoMensaje);
        
        JPanel panelLinea2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea2.add(lParte5);
        panelLinea2.add(lParte6);
        panelLinea2.add(lParte7);
        panelLinea2.setOpaque(false);
        
        
        //....
        
        HJButton bIrAPaginaWeb = new HJButton(null, "Ir a Página Web", Colores.COLORES_BOTONES);
        bIrAPaginaWeb.addActionListener( e -> Utilidades.abrirVinculo("http://www.abanicosystems.com/adquirir-licencia") );
        
        HJButton bIDDelSistema = new HJButton(null, "ID del Sistema", Colores.COLORES_BOTONES);
        bIDDelSistema.addActionListener( e -> mostrarPanelIDDelSistema() );
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        panelBotones.add(bIrAPaginaWeb);
        panelBotones.add(bIDDelSistema);
        panelBotones.setOpaque(false);
        
        
        //....
        
        HJLabel lSiTieneAlgunInconveniente = new HJLabel("Si tiene alguna duda o inconveniente no dude en ");
        lSiTieneAlgunInconveniente.setFont(formatoMensaje);
        
        HJCustomizedButton botonContactarnos = new HJCustomizedButton("contactarnos", new Color[] {Colores.AZUL_LLAMATIVO, Colores.AZUL_OSCURO}, true, true, 1);
        botonContactarnos.addActionListener( e -> Utilidades.abrirVinculo("http://www.abanicosystems.com/soporte") );
        
        HJLabel lPuntoFinal = new HJLabel(".");
        lPuntoFinal.setFont(formatoMensaje);
        
        JPanel panelLineaUltima = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLineaUltima.add(lSiTieneAlgunInconveniente);
        panelLineaUltima.add(botonContactarnos);
        panelLineaUltima.add(lPuntoFinal);
        panelLineaUltima.setOpaque(false);
        
        
        //....
        
        Box boxCentral = Box.createVerticalBox();
        boxCentral.add(Box.createVerticalStrut(15));
        boxCentral.add(panelLinea1);
        boxCentral.add(panelLinea2);
        boxCentral.add(Box.createVerticalStrut(15));
        boxCentral.add(panelBotones);
        boxCentral.add(Box.createVerticalStrut(15));
        boxCentral.add(panelLineaUltima);
        boxCentral.add(Box.createVerticalStrut(15));
        
        boxCentral.setOpaque(true);
        boxCentral.setBackground(Colores.NORMAL_OSCURO);
        
        
        //.............................
        
        HJButton bIntroducirCodigo = new HJButton(new IconoDeImagen("Llave.png", -1, 20), "Introducir Código de Activación", Colores.COLORES_BOTONES);
        bIntroducirCodigo.addActionListener( e -> {
            
            PanelActivacion panelActivacion = new PanelActivacion();
            panelActivacion.setVisible(true);
        });
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelInferior.add(bIntroducirCodigo);
        panelInferior.setOpaque(false);
        
        panelInferior.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //.............................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(panelSuperior);
        cajaGeneral.add(boxCentral);
        cajaGeneral.add(panelInferior);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private void mostrarPanelIDDelSistema() {
        
        MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        if(mapaDatos.containsKey(Registro.ID_SISTEMA)==false)
        {
            Registro.mostrarMensajeError();
            
            return;
        }
        
        HJTextField tIDSistema = new HJTextField(15);
        tIDSistema.setText(mapaDatos.get(Registro.ID_SISTEMA));
        
        HJButton bCopiar = new HJButton(null, "Copiar", Colores.COLORES_BOTONES);
        bCopiar.addActionListener( e -> {
            
            tIDSistema.selectAll();
            tIDSistema.copy();
            tIDSistema.select(0, 0);
            
            HJDialog.mostrarMensaje("Copiado", new String[] {"¡ID del Sistema copiado en el portapapeles!"}, IconoDeImagen.INFORMACION, null);
        });
        
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelCentral.add(tIDSistema);
        panelCentral.add(bCopiar);
        panelCentral.setOpaque(false);
        
        
        HJDialog dialogo = new HJDialog(null, "ID del Sistema", null, true);
        dialogo.anadirActionListenerABotonCerrar( e -> {
            
            dialogo.setVisible(false);
            dialogo.dispose();
        });
        
        dialogo.add(panelCentral);
        
        dialogo.pack();
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }
    
    
}
