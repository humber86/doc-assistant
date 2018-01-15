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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PanelVerConsulta extends HJDialog {
    
    private final Color colorVerdeOscuro = new Color(20,155,65);
    private final Color colorAzulLlamativo = Colores.AZUL_LLAMATIVO;
    
    private final int nroHistoria;
    private final String nombreArchivo;
    
    
    public PanelVerConsulta(int numeroHistoria, String nombreDelArchivo) {
        
        super(new IconoDeImagen("Consulta.png", -1, 30), "Consulta", null, true);
        
        establecerTitulo("Consulta: "+obtenerFechaHora(nombreDelArchivo));
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //.........................
        
        nroHistoria = numeroHistoria;
        
        nombreArchivo = nombreDelArchivo;
        
        
        //.........................
        
        MapaDatos mapaConsulta = (MapaDatos)Utilidades.leerArchivo(Directorios.CONSULTAS+nroHistoria+"\\"+nombreArchivo);
        
        
        //....
        
        MapaDatos mapaPersonales = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+nroHistoria+".dda");
        
        JLabel lPaciente = new JLabel(mapaPersonales.get(Personales.KEY_NOMBRES)+" "+mapaPersonales.get(Personales.KEY_APELLIDOS));
        lPaciente.setFont(new Font("Arial", Font.BOLD, 16));
        lPaciente.setForeground(Colores.NEGRO);
        
        JPanel panelPaciente = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelPaciente.add(lPaciente);
        panelPaciente.setOpaque(false);
        
        HJEnclosingBox boxPaciente = new HJEnclosingBox(BoxLayout.Y_AXIS, "Paciente", new Font("Arial", Font.BOLD, 16), Colores.TEXTO, HJEnclosingBox.CENTRO, 0, colorVerdeOscuro, 2);
        boxPaciente.add(Box.createVerticalStrut(20));
        boxPaciente.add(panelPaciente);
        boxPaciente.add(Box.createVerticalStrut(10));
        
        JPanel panelPacienteMacro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelPacienteMacro.add(boxPaciente);
        panelPacienteMacro.setOpaque(false);
        
        
        HJTextArea taMotivoConsulta = new HJTextArea(1, 27);
        taMotivoConsulta.setLineWrap(false);
        taMotivoConsulta.setWrapStyleWord(false);
        taMotivoConsulta.setText(mapaConsulta.get(Consulta.KEY_MOTIVO_CONSULTA));
        taMotivoConsulta.setEditable(false);
        taMotivoConsulta.setOpaque(false);
        HJScrollPane scrollMotivoConsulta = new HJScrollPane(taMotivoConsulta);
        scrollMotivoConsulta.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollMotivoConsulta.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollMotivoConsulta.removerEscuchadorRuedaRaton();
        
        JPanel panelMotivoConsulta = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelMotivoConsulta.add(scrollMotivoConsulta);
        panelMotivoConsulta.setOpaque(false);
        
        HJEnclosingBox boxMotivoConsulta = new HJEnclosingBox(BoxLayout.Y_AXIS, "Motivo de Consulta", new Font("Arial", Font.BOLD, 14), Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 1);
        boxMotivoConsulta.add(Box.createVerticalStrut(20));
        boxMotivoConsulta.add(panelMotivoConsulta);
        boxMotivoConsulta.add(Box.createVerticalStrut(10));
        
        JPanel panelMotivoConsultaMacro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelMotivoConsultaMacro.add(boxMotivoConsulta);
        panelMotivoConsultaMacro.setOpaque(false);
        
        
        Box boxPacienteMotivoConsulta = Box.createVerticalBox();
        boxPacienteMotivoConsulta.add(panelPacienteMacro);
        boxPacienteMotivoConsulta.add(Box.createVerticalStrut(20));
        boxPacienteMotivoConsulta.add(panelMotivoConsultaMacro);
        
        
        //....
        
        Box boxDivisor = Box.createVerticalBox();
        boxDivisor.add(Box.createVerticalStrut(145));
        
        boxDivisor.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Colores.LINEAS));
        
        
        //....
        
        HJTextArea taSintomas = new HJTextArea(5, 27);
        taSintomas.setText(mapaConsulta.get(Consulta.KEY_SINTOMAS));
        taSintomas.setEditable(false);
        taSintomas.setOpaque(false);
        HJScrollPane scrollSintomas = new HJScrollPane(taSintomas);
        scrollSintomas.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollSintomas.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollSintomas.removerEscuchadorRuedaRaton();
        
        JPanel panelSintomas = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelSintomas.add(new HJLabel("Síntomas: "));
        panelSintomas.add(scrollSintomas);
        panelSintomas.setOpaque(false);
        
        
        //....
        
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelSuperior.add(boxPacienteMotivoConsulta);
        panelSuperior.add(boxDivisor);
        panelSuperior.add(panelSintomas);
        panelSuperior.setOpaque(false);
        
        
        //..........................
        
        HJTextField tTalla = new HJTextField(5);
        tTalla.setText(mapaConsulta.get(Consulta.KEY_TALLA));
        tTalla.setEditable(false);
        
        HJTextField tPeso = new HJTextField(5);
        tPeso.setText(mapaConsulta.get(Consulta.KEY_PESO));
        tPeso.setEditable(false);
        
        HJTextField tCC = new HJTextField(5);
        tCC.setText(mapaConsulta.get(Consulta.KEY_CC));
        tCC.setEditable(false);
        
        JPanel panelExamenFisico_Linea_1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelExamenFisico_Linea_1.add(new HJLabel("Talla: "));
        panelExamenFisico_Linea_1.add(tTalla);
        panelExamenFisico_Linea_1.add(new HJLabel(" cm."));
        panelExamenFisico_Linea_1.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_1.add(new HJLabel("Peso: "));
        panelExamenFisico_Linea_1.add(tPeso);
        panelExamenFisico_Linea_1.add(new HJLabel(" Kg."));
        panelExamenFisico_Linea_1.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_1.add(new HJLabel("C.C.: "));
        panelExamenFisico_Linea_1.add(tCC);
        panelExamenFisico_Linea_1.add(new HJLabel(" cm."));
        panelExamenFisico_Linea_1.setOpaque(false);
        
        
        HJTextField tFR = new HJTextField(5);
        tFR.setText(mapaConsulta.get(Consulta.KEY_FR));
        tFR.setEditable(false);
        
        HJTextField tFC = new HJTextField(5);
        tFC.setText(mapaConsulta.get(Consulta.KEY_FC));
        tFC.setEditable(false);
        
        HJTextField tTS = new HJTextField(5);
        tTS.setText(mapaConsulta.get(Consulta.KEY_TENSION_SIST));
        tTS.setEditable(false);
        
        HJTextField tTD = new HJTextField(5);
        tTD.setText(mapaConsulta.get(Consulta.KEY_TENSION_DIAST));
        tTD.setEditable(false);
        
        JPanel panelTSTD = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 3));
        panelTSTD.add(Box.createHorizontalStrut(5));
        panelTSTD.add(new HJLabel("Sist.: "));
        panelTSTD.add(tTS);
        panelTSTD.add(Box.createHorizontalStrut(5));
        panelTSTD.add(new HJLabel("Diast.: "));
        panelTSTD.add(tTD);
        panelTSTD.add(Box.createHorizontalStrut(5));
        panelTSTD.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        panelTSTD.setOpaque(false);
        
        JPanel panelExamenFisico_Linea_2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelExamenFisico_Linea_2.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_2.add(new HJLabel("F.R.: "));
        panelExamenFisico_Linea_2.add(tFR);
        panelExamenFisico_Linea_2.add(new HJLabel(" r.p.m."));
        panelExamenFisico_Linea_2.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_2.add(new HJLabel("F.C.: "));
        panelExamenFisico_Linea_2.add(tFC);
        panelExamenFisico_Linea_2.add(new HJLabel(" l.p.m."));
        panelExamenFisico_Linea_2.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_2.add(new HJLabel("Tensión: "));
        panelExamenFisico_Linea_2.add(panelTSTD);
        panelExamenFisico_Linea_2.add(new HJLabel(" (mmHg)"));
        panelExamenFisico_Linea_2.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_2.setOpaque(false);
        
        
        HJTextField tTemperatura = new HJTextField(5);
        tTemperatura.setText(mapaConsulta.get(Consulta.KEY_TEMPERATURA));
        tTemperatura.setEditable(false);
        
        HJTextArea taSignos = new HJTextArea(5, 27);
        taSignos.setText(mapaConsulta.get(Consulta.KEY_SIGNOS));
        taSignos.setEditable(false);
        taSignos.setOpaque(false);
        HJScrollPane scrollSignos = new HJScrollPane(taSignos);
        scrollSignos.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollSignos.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollSignos.removerEscuchadorRuedaRaton();
        
        JPanel panelExamenFisico_Linea_3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelExamenFisico_Linea_3.add(new HJLabel("Temperatura: "));
        panelExamenFisico_Linea_3.add(tTemperatura);
        panelExamenFisico_Linea_3.add(new HJLabel(" °C"));
        panelExamenFisico_Linea_3.add(Box.createHorizontalStrut(50));
        panelExamenFisico_Linea_3.add(new HJLabel("Signos: "));
        panelExamenFisico_Linea_3.add(scrollSignos);
        panelExamenFisico_Linea_3.setOpaque(false);
        
        
        HJEnclosingBox boxExamenFisico = new HJEnclosingBox(BoxLayout.Y_AXIS, "Examen Físico", new Font("Arial", Font.BOLD, 18), Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 3);
        boxExamenFisico.add(Box.createVerticalStrut(35));
        boxExamenFisico.add(panelExamenFisico_Linea_1);
        boxExamenFisico.add(Box.createVerticalStrut(20));
        boxExamenFisico.add(panelExamenFisico_Linea_2);
        boxExamenFisico.add(Box.createVerticalStrut(20));
        boxExamenFisico.add(panelExamenFisico_Linea_3);
        boxExamenFisico.add(Box.createVerticalStrut(20));
        
        
        JLabel lEtiquetaNota = new JLabel("NOTA: ");
        lEtiquetaNota.setForeground(colorAzulLlamativo);
        
        JLabel lNota = new JLabel("Los valores de este examen físico no reemplazan los valores de 'Datos Físicos' del paciente.");
        lNota.setForeground(Colores.TEXTO);
        
        JPanel panelNota = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelNota.add(lEtiquetaNota);
        panelNota.add(lNota);
        panelNota.setOpaque(false);
        
        
        Box boxExamenFisicoMacro = Box.createVerticalBox();
        boxExamenFisicoMacro.add(boxExamenFisico);
        boxExamenFisicoMacro.add(panelNota);
        
        
        JPanel panelExamenFisico = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelExamenFisico.add(boxExamenFisicoMacro);
        panelExamenFisico.setOpaque(false);
        
        
        //..........................
        
        HJTextArea taDiagnostico = new HJTextArea(1, 35);
        taDiagnostico.setLineWrap(false);
        taDiagnostico.setWrapStyleWord(false);
        taDiagnostico.setText(mapaConsulta.get(Consulta.KEY_DIAGNOSTICO));
        taDiagnostico.setEditable(false);
        taDiagnostico.setOpaque(false);
        HJScrollPane scrollDiagnostico = new HJScrollPane(taDiagnostico);
        scrollDiagnostico.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollDiagnostico.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollDiagnostico.removerEscuchadorRuedaRaton();
        
        JPanel panelDiagnostico = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelDiagnostico.add(scrollDiagnostico);
        panelDiagnostico.setOpaque(false);
        
        HJEnclosingBox boxDiagnostico = new HJEnclosingBox(BoxLayout.Y_AXIS, "Diagnóstico", new Font("Arial", Font.BOLD, 14), colorAzulLlamativo, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 1);
        boxDiagnostico.add(Box.createVerticalStrut(20));
        boxDiagnostico.add(panelDiagnostico);
        boxDiagnostico.add(Box.createVerticalStrut(10));
        
        JPanel panelDiagnosticoMacro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelDiagnosticoMacro.add(boxDiagnostico);
        panelDiagnosticoMacro.setOpaque(false);
        
        
        //..........................
        
        JPanel panelEtiquetasMedicamentos = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelEtiquetasMedicamentos.add(Box.createHorizontalStrut(132));
        panelEtiquetasMedicamentos.add(new HJLabel("Medicamento"));
        panelEtiquetasMedicamentos.add(Box.createHorizontalStrut(245));
        panelEtiquetasMedicamentos.add(new HJLabel("Indicaciones"));
        panelEtiquetasMedicamentos.setOpaque(false);
        
        
        Box boxMedicamentos = Box.createVerticalBox();
        
        HashMap<String,HashMap<String,String>> mapaMedicamentos = (HashMap<String,HashMap<String,String>>)Utilidades.leerArchivo(Directorios.MEDICAMENTOS+nroHistoria+"\\"+nombreArchivo);
        
        if(mapaMedicamentos!=null)
        {
            Iterator<String> iterador = Utilidades.obtenerIteradorStringOrdenado(mapaMedicamentos);
            
            String medicamento;
            
            HashMap<String,String> mapa;
            
            while(iterador.hasNext())
            {
                medicamento = iterador.next();
                mapa = mapaMedicamentos.get(medicamento);
                
                PanelMedicamento panelMedicamento = new PanelMedicamento();
                panelMedicamento.establecerMedicamento(medicamento);
                panelMedicamento.establecerIndicaciones(mapa.get(PanelMedicamento.INDICACIONES));
                panelMedicamento.establecerEditable(false);
                
                boxMedicamentos.add(panelMedicamento);
            }
        }
        
        
        Box boxTratamiento = Box.createVerticalBox();
        boxTratamiento.add(panelEtiquetasMedicamentos);
        boxTratamiento.add(Box.createVerticalStrut(15));
        boxTratamiento.add(boxMedicamentos);
        
        JPanel panelTratamiento = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelTratamiento.add(boxTratamiento);
        panelTratamiento.setOpaque(false);
        
        HJEnclosingBox boxTratamientoMacro = new HJEnclosingBox(BoxLayout.Y_AXIS, "Tratamiento", new Font("Arial", Font.BOLD, 18), Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 3);
        boxTratamientoMacro.add(Box.createVerticalStrut(35));
        boxTratamientoMacro.add(panelTratamiento);
        boxTratamientoMacro.add(Box.createVerticalStrut(13));
        
        JPanel panelTratamientoMacro = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelTratamientoMacro.add(boxTratamientoMacro);
        panelTratamientoMacro.setOpaque(false);
        
        
        //..........................
        
        
        JPanel panelEtiquetasExamenesLaboratorio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelEtiquetasExamenesLaboratorio.add(Box.createHorizontalStrut(158));
        panelEtiquetasExamenesLaboratorio.add(new HJLabel("Examen"));
        panelEtiquetasExamenesLaboratorio.add(Box.createHorizontalStrut(263));
        panelEtiquetasExamenesLaboratorio.add(new HJLabel("Observaciones"));
        panelEtiquetasExamenesLaboratorio.setOpaque(false);
        
        
        Box boxExamenesLaboratorio = Box.createVerticalBox();
        
        HashMap<String,ArrayList<String>> mapaExamenesLaboratorio = (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.EXAMENES_DE_LABORATORIO+nroHistoria+"\\"+nombreArchivo);
        
        if(mapaExamenesLaboratorio!=null)
        {
            Iterator<String> iteradorCategorias = Utilidades.obtenerIteradorStringOrdenado(mapaExamenesLaboratorio);
            
            String categoria;
            
            ArrayList<String> arrayExamenes;
            
            MapaDatos mapaObservaciones = (MapaDatos)Utilidades.leerArchivo(Directorios.OBSERVACIONES_EXAMENES_DE_LABORATORIO+nroHistoria+"\\"+nombreArchivo);
            
            while(iteradorCategorias.hasNext())
            {
                categoria = iteradorCategorias.next();
                
                if(categoria.equals(PanelExamenesLaboratorio.DROGAS) || categoria.equals(PanelExamenesLaboratorio.OTROS))
                    continue;
                
                JPanel panelCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 15));
                panelCategoria.add(new HJLabel(categoria.toUpperCase()));
                panelCategoria.setOpaque(false);
                
                boxExamenesLaboratorio.add(panelCategoria);
                
                
                arrayExamenes = Utilidades.obtenerArrayStringOrdenado(mapaExamenesLaboratorio.get(categoria));
                
                for(int i=0 ; i<=arrayExamenes.size()-1 ; i++)
                {
                    PanelExamen panelExamen;
                    
                    if(mapaObservaciones!=null)  panelExamen = new PanelExamen(arrayExamenes.get(i), mapaObservaciones.get(arrayExamenes.get(i)));
                    else  panelExamen = new PanelExamen(arrayExamenes.get(i), null);
                    
                    boxExamenesLaboratorio.add(panelExamen);
                }
            }
            
            if(mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.DROGAS)!=null)
            {
                JPanel panelCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 15));
                panelCategoria.add(new HJLabel(PanelExamenesLaboratorio.DROGAS.toUpperCase()));
                panelCategoria.setOpaque(false);
                
                boxExamenesLaboratorio.add(panelCategoria);
                
                
                PanelExamenGrande panelExamenGrande;
                
                if(mapaObservaciones!=null)  panelExamenGrande = new PanelExamenGrande(mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.DROGAS).get(0), mapaObservaciones.get(mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.DROGAS).get(0)));
                else  panelExamenGrande = new PanelExamenGrande(mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.DROGAS).get(0), null);
                
                boxExamenesLaboratorio.add(panelExamenGrande);
            }
            
            if(mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.OTROS)!=null)
            {
                JPanel panelCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 15));
                panelCategoria.add(new HJLabel(PanelExamenesLaboratorio.OTROS.toUpperCase()));
                panelCategoria.setOpaque(false);
                
                boxExamenesLaboratorio.add(panelCategoria);
                
                
                PanelExamenGrande panelExamenGrande;
                
                if(mapaObservaciones!=null)  panelExamenGrande = new PanelExamenGrande(mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.OTROS).get(0), mapaObservaciones.get(mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.OTROS).get(0)));
                else  panelExamenGrande = new PanelExamenGrande(mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.OTROS).get(0), null);
                
                boxExamenesLaboratorio.add(panelExamenGrande);
            }
        }
        
        
        Box boxExamenesLaboratorioMacro = Box.createVerticalBox();
        boxExamenesLaboratorioMacro.add(panelEtiquetasExamenesLaboratorio);
        boxExamenesLaboratorioMacro.add(Box.createVerticalStrut(15));
        boxExamenesLaboratorioMacro.add(boxExamenesLaboratorio);
        
        JPanel panelExamenesLaboratorioMacro = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelExamenesLaboratorioMacro.add(boxExamenesLaboratorioMacro);
        panelExamenesLaboratorioMacro.setOpaque(false);
        
        HJEnclosingBox boxExamenesLaboratorioMacroMacro = new HJEnclosingBox(BoxLayout.Y_AXIS, "Exámenes de Laboratorio", new Font("Arial", Font.BOLD, 18), Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 3);
        boxExamenesLaboratorioMacroMacro.add(Box.createVerticalStrut(35));
        boxExamenesLaboratorioMacroMacro.add(panelExamenesLaboratorioMacro);
        boxExamenesLaboratorioMacroMacro.add(Box.createVerticalStrut(13));
        
        JPanel panelExamenesLaboratorioMacroMacro = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelExamenesLaboratorioMacroMacro.add(boxExamenesLaboratorioMacroMacro);
        panelExamenesLaboratorioMacroMacro.setOpaque(false);
        
        
        //..........................
        
        JPanel panelEtiquetasEstudiosRadiologicos = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelEtiquetasEstudiosRadiologicos.add(Box.createHorizontalStrut(160));
        panelEtiquetasEstudiosRadiologicos.add(new HJLabel("Estudio"));
        panelEtiquetasEstudiosRadiologicos.add(Box.createHorizontalStrut(275));
        panelEtiquetasEstudiosRadiologicos.add(new HJLabel("Observaciones"));
        panelEtiquetasEstudiosRadiologicos.setOpaque(false);
        
        
        Box boxEstudiosRadiologicos = Box.createVerticalBox();
        
        HashMap<String,String> mapaEstudiosRadiologicos = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.ESTUDIOS_RADIOLOGICOS+nroHistoria+"\\"+nombreArchivo);
        
        if(mapaEstudiosRadiologicos!=null)
        {
            Iterator<String> iteradorCategorias = Utilidades.obtenerIteradorStringOrdenado(mapaEstudiosRadiologicos);
            
            String categoria;
            
            MapaDatos mapaObservaciones = (MapaDatos)Utilidades.leerArchivo(Directorios.OBSERVACIONES_ESTUDIOS_RADIOLOGICOS+nroHistoria+"\\"+nombreArchivo);
            
            while(iteradorCategorias.hasNext())
            {
                categoria = iteradorCategorias.next();
                
                if(categoria.equals(PanelEstudiosRadiologicos.OTROS))
                    continue;
                
                JPanel panelCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 15));
                panelCategoria.add(new HJLabel(categoria.toUpperCase()));
                panelCategoria.setOpaque(false);
                
                boxEstudiosRadiologicos.add(panelCategoria);
                
                
                PanelEstudio panelEstudio;
                
                if(mapaObservaciones!=null)  panelEstudio = new PanelEstudio(mapaEstudiosRadiologicos.get(categoria), mapaObservaciones.get(mapaEstudiosRadiologicos.get(categoria)));
                else  panelEstudio = new PanelEstudio(mapaEstudiosRadiologicos.get(categoria), null);
                
                boxEstudiosRadiologicos.add(panelEstudio);
            }
            
            if(mapaEstudiosRadiologicos.get(PanelEstudiosRadiologicos.OTROS)!=null)
            {
                JPanel panelCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 15));
                panelCategoria.add(new HJLabel(PanelEstudiosRadiologicos.OTROS.toUpperCase()));
                panelCategoria.setOpaque(false);
                
                boxEstudiosRadiologicos.add(panelCategoria);
                
                
                PanelEstudio panelEstudio;
                
                if(mapaObservaciones!=null)  panelEstudio = new PanelEstudio(mapaEstudiosRadiologicos.get(PanelEstudiosRadiologicos.OTROS), mapaObservaciones.get(mapaEstudiosRadiologicos.get(PanelEstudiosRadiologicos.OTROS)));
                else  panelEstudio = new PanelEstudio(mapaEstudiosRadiologicos.get(PanelEstudiosRadiologicos.OTROS), null);
                
                boxEstudiosRadiologicos.add(panelEstudio);
            }
        }
        
        
        Box boxEstudiosRadiologicosMacro = Box.createVerticalBox();
        boxEstudiosRadiologicosMacro.add(panelEtiquetasEstudiosRadiologicos);
        boxEstudiosRadiologicosMacro.add(Box.createVerticalStrut(15));
        boxEstudiosRadiologicosMacro.add(boxEstudiosRadiologicos);
        
        JPanel panelEstudiosRadiologicosMacro = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelEstudiosRadiologicosMacro.add(boxEstudiosRadiologicosMacro);
        panelEstudiosRadiologicosMacro.setOpaque(false);
        
        HJEnclosingBox boxEstudiosRadiologicosMacroMacro = new HJEnclosingBox(BoxLayout.Y_AXIS, "Estudios Radiológicos", new Font("Arial", Font.BOLD, 18), Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 3);
        boxEstudiosRadiologicosMacroMacro.add(Box.createVerticalStrut(35));
        boxEstudiosRadiologicosMacroMacro.add(panelEstudiosRadiologicosMacro);
        boxEstudiosRadiologicosMacroMacro.add(Box.createVerticalStrut(13));
        
        JPanel panelEstudiosRadiologicosMacroMacro = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelEstudiosRadiologicosMacroMacro.add(boxEstudiosRadiologicosMacroMacro);
        panelEstudiosRadiologicosMacroMacro.setOpaque(false);
        
        
        //..........................
        
        HJPaintedBox boxSuperior = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(245,250,245), new Color(225,240,225), null, null, 100, true);
        boxSuperior.add(Box.createVerticalStrut(30));
        boxSuperior.add(panelSuperior);
        boxSuperior.add(Box.createVerticalStrut(30));
        boxSuperior.add(panelExamenFisico);
        boxSuperior.add(Box.createVerticalStrut(30));
        boxSuperior.add(panelDiagnosticoMacro);
        if(boxMedicamentos.getComponentCount()>0)
        {
            boxSuperior.add(Box.createVerticalStrut(30));
            boxSuperior.add(panelTratamientoMacro);
        }
        if(boxExamenesLaboratorio.getComponentCount()>0)
        {
            boxSuperior.add(Box.createVerticalStrut(30));
            boxSuperior.add(panelExamenesLaboratorioMacroMacro);
        }
        if(boxEstudiosRadiologicos.getComponentCount()>0)
        {
            boxSuperior.add(Box.createVerticalStrut(30));
            boxSuperior.add(panelEstudiosRadiologicosMacroMacro);
        }
        boxSuperior.add(Box.createVerticalStrut(30));
        
        JScrollPane scrollBoxSuperior = new JScrollPane(boxSuperior);
        scrollBoxSuperior.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBoxSuperior.setPreferredSize(new Dimension(1100, 620));
        scrollBoxSuperior.getVerticalScrollBar().setUnitIncrement(10);
        
        
        //...........................
        
        HJButton bCerrar = new HJButton(null, "Cerrar", Colores.COLORES_BOTONES);
        bCerrar.addActionListener( e -> {
            
            setVisible(false);
            dispose();
        });
        
        JPanel panelBotonCerrar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelBotonCerrar.add(bCerrar);
        panelBotonCerrar.setOpaque(false);
        
        
        //.......................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(scrollBoxSuperior);
        cajaGeneral.add(panelBotonCerrar);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private String obtenerFechaHora(String nombreArchivo) {
        
        LocalDateTime fechaHora = LocalDateTime.parse((nombreArchivo.split(".dda"))[0], DateTimeFormatter.ofPattern("uuuu-MM-dd'_'HH-mm-ss"));
        
        LocalTime hora = LocalTime.of(fechaHora.getHour(), fechaHora.getMinute(), fechaHora.getSecond());
        
        String AM_PM = "a.m.";
        if(hora.isAfter(LocalTime.NOON))  AM_PM = "p.m.";
        
        return fechaHora.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"))+", "+hora.format(DateTimeFormatter.ofPattern("hh:mm:ss"))+" "+AM_PM;
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class PanelExamen extends JPanel {
        
        
        public PanelExamen(String nombreExamen, String observaciones) {
            
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            
            setOpaque(false);
            
            
            HJTextArea taNombreExamen = new HJTextArea(1, 25);
            taNombreExamen.setLineWrap(false);
            taNombreExamen.setWrapStyleWord(false);
            taNombreExamen.setText(nombreExamen);
            taNombreExamen.setEditable(false);
            taNombreExamen.setOpaque(false);
            HJScrollPane scrollNombreExamen = new HJScrollPane(taNombreExamen);
            scrollNombreExamen.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scrollNombreExamen.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollNombreExamen.removerEscuchadorRuedaRaton();
            
            
            HJTextArea taObservaciones = new HJTextArea(3, 25);
            taObservaciones.setText(observaciones);
            HJScrollPane scrollObservaciones = new HJScrollPane(taObservaciones);
            scrollObservaciones.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollObservaciones.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollObservaciones.removerEscuchadorRuedaRaton();
            
            
            HJButton botonAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), null, Colores.COLORES_BOTONES);
            
            HJButton botonEditar = new HJButton(new IconoDeImagen("Editar.png", -1, 20), null, Colores.COLORES_BOTONES);
            
            botonAceptar.addActionListener( e -> {
                
                if(taObservaciones.esTextoValido()==false)
                {
                    HJDialog.mostrarMensaje("No Hay Valor", new String[] {"No ha ingresado ningún valor."}, IconoDeImagen.ADVERTENCIA, null);
                    
                    MapaDatos mapaObservaciones = (MapaDatos)Utilidades.leerArchivo(Directorios.OBSERVACIONES_EXAMENES_DE_LABORATORIO+nroHistoria+"\\"+nombreArchivo);
                    
                    if(mapaObservaciones!=null)
                    {
                        if(mapaObservaciones.get(nombreExamen)!=null)
                        {
                            taObservaciones.setText(mapaObservaciones.get(nombreExamen));
                            
                            taObservaciones.setEditable(false);
                            taObservaciones.setOpaque(false);
                            
                            botonAceptar.setVisible(false);
                            botonEditar.setVisible(true);
                        }
                    }
                    
                    return;
                }
                
                taObservaciones.setEditable(false);
                taObservaciones.setOpaque(false);
                
                MapaDatos mapaObservaciones = (MapaDatos)Utilidades.leerArchivo(Directorios.OBSERVACIONES_EXAMENES_DE_LABORATORIO+nroHistoria+"\\"+nombreArchivo);
                
                if(mapaObservaciones==null)  mapaObservaciones = new MapaDatos();
                
                mapaObservaciones.put(nombreExamen, taObservaciones.getText());
                
                Utilidades.crear_Y_GuardarEnArchivo(Directorios.OBSERVACIONES_EXAMENES_DE_LABORATORIO+nroHistoria, nombreArchivo, mapaObservaciones);
                
                botonAceptar.setVisible(false);
                botonEditar.setVisible(true);
            });
            
            botonEditar.addActionListener( e -> {
                
                taObservaciones.setOpaque(true);
                taObservaciones.setEditable(true);
                
                botonEditar.setVisible(false);
                botonAceptar.setVisible(true);
            });
            
            
            if(observaciones==null)
            {
                botonEditar.setVisible(false);
                
            }else{
                
                taObservaciones.setEditable(false);
                taObservaciones.setOpaque(false);
                
                botonAceptar.setVisible(false);
            }
            
            
            add(Box.createHorizontalStrut(20));
            add(scrollNombreExamen);
            add(new HJLabel(":"));
            add(scrollObservaciones);
            add(botonAceptar);
            add(botonEditar);
            add(Box.createHorizontalStrut(20));
            
        }
        
        
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class PanelExamenGrande extends JPanel {
        
        
        public PanelExamenGrande(String nombreExamen, String observaciones) {
            
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            
            setOpaque(false);
            
            
            HJTextArea taNombreExamen = new HJTextArea(3, 25);
            taNombreExamen.setText(nombreExamen);
            taNombreExamen.setEditable(false);
            taNombreExamen.setOpaque(false);
            HJScrollPane scrollNombreExamen = new HJScrollPane(taNombreExamen);
            scrollNombreExamen.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollNombreExamen.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollNombreExamen.removerEscuchadorRuedaRaton();
            
            
            HJTextArea taObservaciones = new HJTextArea(3, 25);
            taObservaciones.setText(observaciones);
            HJScrollPane scrollObservaciones = new HJScrollPane(taObservaciones);
            scrollObservaciones.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollObservaciones.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollObservaciones.removerEscuchadorRuedaRaton();
            
            
            HJButton botonAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), null, Colores.COLORES_BOTONES);
            
            HJButton botonEditar = new HJButton(new IconoDeImagen("Editar.png", -1, 20), null, Colores.COLORES_BOTONES);
            
            botonAceptar.addActionListener( e -> {
                
                if(taObservaciones.esTextoValido()==false)
                {
                    HJDialog.mostrarMensaje("No Hay Valor", new String[] {"No ha ingresado ningún valor."}, IconoDeImagen.ADVERTENCIA, null);
                    
                    MapaDatos mapaObservaciones = (MapaDatos)Utilidades.leerArchivo(Directorios.OBSERVACIONES_EXAMENES_DE_LABORATORIO+nroHistoria+"\\"+nombreArchivo);
                    
                    if(mapaObservaciones!=null)
                    {
                        if(mapaObservaciones.get(nombreExamen)!=null)
                        {
                            taObservaciones.setText(mapaObservaciones.get(nombreExamen));
                            
                            taObservaciones.setEditable(false);
                            taObservaciones.setOpaque(false);
                            
                            botonAceptar.setVisible(false);
                            botonEditar.setVisible(true);
                        }
                    }
                    
                    return;
                }
                
                taObservaciones.setEditable(false);
                taObservaciones.setOpaque(false);
                
                MapaDatos mapaObservaciones = (MapaDatos)Utilidades.leerArchivo(Directorios.OBSERVACIONES_EXAMENES_DE_LABORATORIO+nroHistoria+"\\"+nombreArchivo);
                
                if(mapaObservaciones==null)  mapaObservaciones = new MapaDatos();
                
                mapaObservaciones.put(nombreExamen, taObservaciones.getText());
                
                Utilidades.crear_Y_GuardarEnArchivo(Directorios.OBSERVACIONES_EXAMENES_DE_LABORATORIO+nroHistoria, nombreArchivo, mapaObservaciones);
                
                botonAceptar.setVisible(false);
                botonEditar.setVisible(true);
            });
            
            botonEditar.addActionListener( e -> {
                
                taObservaciones.setOpaque(true);
                taObservaciones.setEditable(true);
                
                botonEditar.setVisible(false);
                botonAceptar.setVisible(true);
            });
            
            
            if(observaciones==null)
            {
                botonEditar.setVisible(false);
                
            }else{
                
                taObservaciones.setEditable(false);
                taObservaciones.setOpaque(false);
                
                botonAceptar.setVisible(false);
            }
            
            
            add(Box.createHorizontalStrut(20));
            add(scrollNombreExamen);
            add(new HJLabel(":"));
            add(scrollObservaciones);
            add(botonAceptar);
            add(botonEditar);
            add(Box.createHorizontalStrut(20));
            
        }
        
        
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class PanelEstudio extends JPanel {
        
        
        public PanelEstudio(String tipoEstudio, String observaciones) {
            
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
            
            setOpaque(false);
            
            
            HJTextArea taTipoEstudio = new HJTextArea(3, 25);
            taTipoEstudio.setText(tipoEstudio);
            taTipoEstudio.setEditable(false);
            taTipoEstudio.setOpaque(false);
            HJScrollPane scrollTipoEstudio = new HJScrollPane(taTipoEstudio);
            scrollTipoEstudio.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollTipoEstudio.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollTipoEstudio.removerEscuchadorRuedaRaton();
            
            
            HJTextArea taObservaciones = new HJTextArea(3, 25);
            taObservaciones.setText(observaciones);
            HJScrollPane scrollObservaciones = new HJScrollPane(taObservaciones);
            scrollObservaciones.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollObservaciones.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollObservaciones.removerEscuchadorRuedaRaton();
            
            
            HJButton botonAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), null, Colores.COLORES_BOTONES);
            
            HJButton botonEditar = new HJButton(new IconoDeImagen("Editar.png", -1, 20), null, Colores.COLORES_BOTONES);
            
            botonAceptar.addActionListener( e -> {
                
                if(taObservaciones.esTextoValido()==false)
                {
                    HJDialog.mostrarMensaje("No Hay Valor", new String[] {"No ha ingresado ningún valor."}, IconoDeImagen.ADVERTENCIA, null);
                    
                    MapaDatos mapaObservaciones = (MapaDatos)Utilidades.leerArchivo(Directorios.OBSERVACIONES_ESTUDIOS_RADIOLOGICOS+nroHistoria+"\\"+nombreArchivo);
                    
                    if(mapaObservaciones!=null)
                    {
                        if(mapaObservaciones.get(tipoEstudio)!=null)
                        {
                            taObservaciones.setText(mapaObservaciones.get(tipoEstudio));
                            
                            taObservaciones.setEditable(false);
                            taObservaciones.setOpaque(false);
                            
                            botonAceptar.setVisible(false);
                            botonEditar.setVisible(true);
                        }
                    }
                    
                    return;
                }
                
                taObservaciones.setEditable(false);
                taObservaciones.setOpaque(false);
                
                MapaDatos mapaObservaciones = (MapaDatos)Utilidades.leerArchivo(Directorios.OBSERVACIONES_ESTUDIOS_RADIOLOGICOS+nroHistoria+"\\"+nombreArchivo);
                
                if(mapaObservaciones==null)  mapaObservaciones = new MapaDatos();
                
                mapaObservaciones.put(tipoEstudio, taObservaciones.getText());
                
                Utilidades.crear_Y_GuardarEnArchivo(Directorios.OBSERVACIONES_ESTUDIOS_RADIOLOGICOS+nroHistoria, nombreArchivo, mapaObservaciones);
                
                botonAceptar.setVisible(false);
                botonEditar.setVisible(true);
            });
            
            botonEditar.addActionListener( e -> {
                
                taObservaciones.setOpaque(true);
                taObservaciones.setEditable(true);
                
                botonEditar.setVisible(false);
                botonAceptar.setVisible(true);
            });
            
            
            if(observaciones==null)
            {
                botonEditar.setVisible(false);
                
            }else{
                
                taObservaciones.setEditable(false);
                taObservaciones.setOpaque(false);
                
                botonAceptar.setVisible(false);
            }
            
            
            add(Box.createHorizontalStrut(20));
            add(scrollTipoEstudio);
            add(new HJLabel(":"));
            add(scrollObservaciones);
            add(botonAceptar);
            add(botonEditar);
            add(Box.createHorizontalStrut(20));
            
        }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
