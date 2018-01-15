/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PanelHistorialConsultas extends HJDialog {
    
    private final int nroHistoria;
    
    
    public PanelHistorialConsultas(int numeroHistoria) {
        
        super(new IconoDeImagen("HistorialConsultas.png", -1, 30), "Historial de Consultas", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //..........................
        
        nroHistoria = numeroHistoria;
        
        
        //..........................
        
        JPanel panelEtiquetas = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
        panelEtiquetas.add(Box.createHorizontalStrut(55));
        panelEtiquetas.add(new HJLabel("Fecha-Hora"));
        panelEtiquetas.add(Box.createHorizontalStrut(155));
        panelEtiquetas.add(new HJLabel("Motivo de Consulta"));
        panelEtiquetas.add(Box.createHorizontalStrut(225));
        panelEtiquetas.add(new HJLabel("Diagnóstico"));
        panelEtiquetas.setOpaque(false);
        
        
        String[] listaNombresConsultas = (new File(Directorios.CONSULTAS+nroHistoria)).list();
        
        Box boxConsultas = Box.createVerticalBox();
        
        for(int i=listaNombresConsultas.length-1 ; i>=0 ; i--)
        {
            boxConsultas.add(obtenerPanelConsulta(listaNombresConsultas[i]));
        }
        
        Box boxConsultasMacro = Box.createVerticalBox();
        boxConsultasMacro.add(Box.createVerticalStrut(10));
        boxConsultasMacro.add(boxConsultas);
        
        JScrollPane scrollConsultas = new JScrollPane(boxConsultasMacro);
        scrollConsultas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollConsultas.setPreferredSize(new Dimension(960, 500));
        scrollConsultas.setOpaque(false);
        scrollConsultas.getViewport().setOpaque(false);
        
        
        //..........................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(panelEtiquetas);
        cajaGeneral.add(scrollConsultas);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private JPanel obtenerPanelConsulta(String nombreConsulta) {
        
        HJLabel lFechaHora = new HJLabel(obtenerFechaHora(nombreConsulta));
        
        
        MapaDatos mapaConsulta = (MapaDatos)Utilidades.leerArchivo(Directorios.CONSULTAS+nroHistoria+"\\"+nombreConsulta);
        
        
        HJTextArea taMotivoConsulta = new HJTextArea(1, 25);
        taMotivoConsulta.setLineWrap(false);
        taMotivoConsulta.setWrapStyleWord(false);
        taMotivoConsulta.setText(mapaConsulta.get(Consulta.KEY_MOTIVO_CONSULTA));
        taMotivoConsulta.setEditable(false);
        taMotivoConsulta.setOpaque(false);
        HJScrollPane scrollMotivoConsulta = new HJScrollPane(taMotivoConsulta);
        scrollMotivoConsulta.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollMotivoConsulta.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollMotivoConsulta.removerEscuchadorRuedaRaton();
        
        
        HJTextArea taDiagnostico = new HJTextArea(1, 25);
        taDiagnostico.setLineWrap(false);
        taDiagnostico.setWrapStyleWord(false);
        taDiagnostico.setText(mapaConsulta.get(Consulta.KEY_DIAGNOSTICO));
        taDiagnostico.setEditable(false);
        taDiagnostico.setOpaque(false);
        HJScrollPane scrollDiagnostico = new HJScrollPane(taDiagnostico);
        scrollDiagnostico.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollDiagnostico.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollDiagnostico.removerEscuchadorRuedaRaton();
        
        
        HJButton bVer = new HJButton(null, "Ver", Colores.COLORES_BOTONES);
        bVer.addActionListener( e -> {
            
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloPanelVacunas = new Thread( () -> {
                
                PanelVerConsulta panelVerConsulta = new PanelVerConsulta(nroHistoria, nombreConsulta);
                
                panelEspera.setVisible(false);
                panelEspera.dispose();
                
                panelVerConsulta.setVisible(true);
            });
            hiloPanelVacunas.start();
            
            panelEspera.setVisible(true);
        });
        
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.add(lFechaHora);
        panel.add(new HJLabel("-"));
        panel.add(scrollMotivoConsulta);
        panel.add(new HJLabel("-"));
        panel.add(scrollDiagnostico);
        panel.add(Box.createVerticalStrut(5));
        panel.add(bVer);
        panel.add(Box.createVerticalStrut(5));
        panel.setMaximumSize(new Dimension(950, 64));
        panel.setOpaque(false);
        
        
        return panel;
    }
    
    
    private String obtenerFechaHora(String nombreArchivo) {
        
        LocalDateTime fechaHora = LocalDateTime.parse((nombreArchivo.split(".dda"))[0], DateTimeFormatter.ofPattern("uuuu-MM-dd'_'HH-mm-ss"));
        
        LocalTime hora = LocalTime.of(fechaHora.getHour(), fechaHora.getMinute(), fechaHora.getSecond());
        
        String AM_PM = "a.m.";
        if(hora.isAfter(LocalTime.NOON))  AM_PM = "p.m.";
        
        return fechaHora.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"))+", "+hora.format(DateTimeFormatter.ofPattern("hh:mm:ss"))+" "+AM_PM;
    }
    
    
}
