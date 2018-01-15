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
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PanelConsulta extends HJDialog {
    
    private final Color colorVerdeOscuro = Colores.VERDE;
    private final Color colorAzulLlamativo = Colores.AZUL_LLAMATIVO;
    
    private final int nroHistoria;
    
    private final JLabel lPaciente;
    
    private final HJTextArea taMotivoConsulta;
    
    private final HJTextArea taSintomas;
    
    private final HJTextField tTalla;
    private final HJTextField tPeso;
    private final HJTextField tTemperatura;
    private final HJTextField tCC;
    private final HJTextField tCT;
    private final HJTextField tCA;
    private final HJTextField tFR;
    private final HJTextField tFC;
    private final HJTextField tTS;
    private final HJTextField tTD;
    
    private final HJTextArea taSignos;
    
    private final HJTextArea taDiagnostico;
    
    private final Box boxMedicamentos;
    private final HJScrollPane scrollMedicamentos;
    
    private final PanelExamenesLaboratorio panelExamenesLaboratorio = new PanelExamenesLaboratorio();
    private final HJLabel lExamenes;
    private final String EXAMENES_NO_ESTABLECIDOS = "(Exámenes no establecidos)";
    private final String EXAMENES_ESTABLECIDOS = "(Exámenes establecidos)";
    
    private final PanelEstudiosRadiologicos panelEstudiosRadiologicos = new PanelEstudiosRadiologicos();
    private final HJLabel lEstudios;
    private final String ESTUDIOS_NO_ESTABLECIDOS = "(Estudios no establecidos)";
    private final String ESTUDIOS_ESTABLECIDOS = "(Estudios establecidos)";
    
    private final HJLabel lFechaCita;
    private final HJButton bEstablecerCita;
    private final HJButton bEliminarCita;
    private final String CITA_NO_ESTABLECIDA = "(No establecida)";
    
    
    public PanelConsulta(int numeroHistoria) {
        
        super(new IconoDeImagen("Consulta.png", -1, 30), "Consulta", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Advertencia", new String[] {"Esta acción es igual a presionar el botón 'Cancelar'.", "¿Está seguro(a) de que desea continuar?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
            {
                setVisible(false);
                dispose();
            }
        });
        
        
        //.........................
        
        nroHistoria = numeroHistoria;
        
        
        //.........................
        
        MapaDatos mapaPersonales = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+nroHistoria+".dda");
        
        lPaciente = new JLabel(mapaPersonales.get(Personales.KEY_NOMBRES)+" "+mapaPersonales.get(Personales.KEY_APELLIDOS));
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
        
        
        taMotivoConsulta = new HJTextArea(1, 27);
        taMotivoConsulta.setLineWrap(false);
        taMotivoConsulta.setWrapStyleWord(false);
        HJScrollPane scrollMotivoConsulta = new HJScrollPane(taMotivoConsulta);
        scrollMotivoConsulta.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollMotivoConsulta.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollMotivoConsulta.removerEscuchadorRuedaRaton();
        
        JPanel panelMotivoConsulta = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelMotivoConsulta.add(scrollMotivoConsulta);
        panelMotivoConsulta.setOpaque(false);
        
        HJEnclosingBox boxMotivoConsulta = new HJEnclosingBox(BoxLayout.Y_AXIS, "Motivo de Consulta", new Font("Arial", Font.BOLD, 14), colorAzulLlamativo, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 1);
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
        
        taSintomas = new HJTextArea(5, 27);
        HJScrollPane scrollSintomas = new HJScrollPane(taSintomas);
        scrollSintomas.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollSintomas.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollSintomas.removerEscuchadorRuedaRaton();
        
        JPanel panelSintomas = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelSintomas.add(crearEtiquetaAzul("Síntomas: "));
        panelSintomas.add(scrollSintomas);
        panelSintomas.setOpaque(false);
        
        
        //....
        
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelSuperior.add(boxPacienteMotivoConsulta);
        panelSuperior.add(boxDivisor);
        panelSuperior.add(panelSintomas);
        panelSuperior.setOpaque(false);
        
        
        //..........................
        
        tTalla = new HJTextField(5);
        
        tPeso = new HJTextField(5);
        
        tTemperatura = new HJTextField(5);
        
        JPanel panelExamenFisico_Linea_1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelExamenFisico_Linea_1.add(new HJLabel("Talla: "));
        panelExamenFisico_Linea_1.add(tTalla);
        panelExamenFisico_Linea_1.add(new HJLabel(" cm."));
        panelExamenFisico_Linea_1.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_1.add(new HJLabel("Peso: "));
        panelExamenFisico_Linea_1.add(tPeso);
        panelExamenFisico_Linea_1.add(new HJLabel(" Kg."));
        panelExamenFisico_Linea_1.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_1.add(new HJLabel("Temperatura: "));
        panelExamenFisico_Linea_1.add(tTemperatura);
        panelExamenFisico_Linea_1.add(new HJLabel(" °C"));
        panelExamenFisico_Linea_1.setOpaque(false);
        
        
        tCC = new HJTextField(5);
        
        tCT = new HJTextField(5);
        
        tCA = new HJTextField(5);
        
        JPanel panelExamenFisico_Linea_2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelExamenFisico_Linea_2.add(new HJLabel("C. Cefálica: "));
        panelExamenFisico_Linea_2.add(tCC);
        panelExamenFisico_Linea_2.add(new HJLabel(" cm."));
        panelExamenFisico_Linea_2.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_2.add(new HJLabel("C. Torácica: "));
        panelExamenFisico_Linea_2.add(tCT);
        panelExamenFisico_Linea_2.add(new HJLabel(" cm."));
        panelExamenFisico_Linea_2.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_2.add(new HJLabel("C. Abdominal: "));
        panelExamenFisico_Linea_2.add(tCA);
        panelExamenFisico_Linea_2.add(new HJLabel(" cm."));
        panelExamenFisico_Linea_2.setOpaque(false);
        
        
        tFR = new HJTextField(5);
        
        tFC = new HJTextField(5);
        
        tTS = new HJTextField(5);
        tTD = new HJTextField(5);
        
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
        
        JPanel panelExamenFisico_Linea_3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelExamenFisico_Linea_3.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_3.add(new HJLabel("F.R.: "));
        panelExamenFisico_Linea_3.add(tFR);
        panelExamenFisico_Linea_3.add(new HJLabel(" r.p.m."));
        panelExamenFisico_Linea_3.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_3.add(new HJLabel("F.C.: "));
        panelExamenFisico_Linea_3.add(tFC);
        panelExamenFisico_Linea_3.add(new HJLabel(" l.p.m."));
        panelExamenFisico_Linea_3.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_3.add(new HJLabel("Tensión arterial: "));
        panelExamenFisico_Linea_3.add(panelTSTD);
        panelExamenFisico_Linea_3.add(new HJLabel(" (mmHg)"));
        panelExamenFisico_Linea_3.add(Box.createHorizontalStrut(40));
        panelExamenFisico_Linea_3.setOpaque(false);
        
        
        taSignos = new HJTextArea(5, 27);
        HJScrollPane scrollSignos = new HJScrollPane(taSignos);
        scrollSignos.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollSignos.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollSignos.removerEscuchadorRuedaRaton();
        
        JPanel panelExamenFisicoSignos = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        panelExamenFisicoSignos.add(Box.createHorizontalStrut(50));
        panelExamenFisicoSignos.add(crearEtiquetaAzul("Signos: "));
        panelExamenFisicoSignos.add(scrollSignos);
        panelExamenFisicoSignos.setOpaque(false);
        
        
        HJEnclosingBox boxExamenFisico = new HJEnclosingBox(BoxLayout.Y_AXIS, "Examen Físico", new Font("Arial", Font.BOLD, 18), Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 3);
        boxExamenFisico.add(Box.createVerticalStrut(35));
        boxExamenFisico.add(panelExamenFisico_Linea_1);
        boxExamenFisico.add(Box.createVerticalStrut(20));
        boxExamenFisico.add(panelExamenFisico_Linea_2);
        boxExamenFisico.add(Box.createVerticalStrut(20));
        boxExamenFisico.add(panelExamenFisico_Linea_3);
        boxExamenFisico.add(Box.createVerticalStrut(20));
        boxExamenFisico.add(panelExamenFisicoSignos);
        boxExamenFisico.add(Box.createVerticalStrut(20));
        
        
        JPanel panelExamenFisico = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelExamenFisico.add(boxExamenFisico);
        panelExamenFisico.setOpaque(false);
        
        
        //..........................
        
        taDiagnostico = new HJTextArea(1, 35);
        taDiagnostico.setLineWrap(false);
        taDiagnostico.setWrapStyleWord(false);
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
        
        HJCustomizedButton bAyudaAntibiotico = new HJCustomizedButton("AyudaNormal.png", "AyudaNormal.png", -1, 14, true);
        bAyudaAntibiotico.addActionListener( e -> HJDialog.mostrarMensaje("Antibiótico", new String[] {"Marque esta casilla si el medicamento es un antibiótico.", "Esto servirá para propósitos estadísticos."}, IconoDeImagen.INFORMACION, null) );
        
        HJCustomizedButton bAyudaPI = new HJCustomizedButton("AyudaNormal.png", "AyudaNormal.png", -1, 14, true);
        bAyudaPI.addActionListener( e -> HJDialog.mostrarMensaje("Prescripción Individual", new String[] {"Marque esta casilla si desea que el récipe para este medicamento se imprima de manera individual."}, IconoDeImagen.INFORMACION, null) );
        
        JPanel panelEtiquetasMedicamentos = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelEtiquetasMedicamentos.add(Box.createHorizontalStrut(28));
        panelEtiquetasMedicamentos.add(new HJLabel("Atb."));
        panelEtiquetasMedicamentos.add(bAyudaAntibiotico);
        panelEtiquetasMedicamentos.add(Box.createHorizontalStrut(45));
        panelEtiquetasMedicamentos.add(new HJLabel("P.I."));
        panelEtiquetasMedicamentos.add(bAyudaPI);
        panelEtiquetasMedicamentos.add(Box.createHorizontalStrut(112));
        panelEtiquetasMedicamentos.add(crearEtiquetaAzul("Medicamento"));
        panelEtiquetasMedicamentos.add(Box.createHorizontalStrut(115));
        panelEtiquetasMedicamentos.add(new HJLabel("Cajas"));
        panelEtiquetasMedicamentos.add(Box.createHorizontalStrut(145));
        panelEtiquetasMedicamentos.add(new HJLabel("Indicaciones"));
        panelEtiquetasMedicamentos.setOpaque(false);
        
        
        boxMedicamentos = Box.createVerticalBox();
        
        Box boxMedicamentosMacro = Box.createVerticalBox();
        boxMedicamentosMacro.add(Box.createVerticalStrut(15));
        boxMedicamentosMacro.add(boxMedicamentos);
        
        scrollMedicamentos = new HJScrollPane(boxMedicamentosMacro);
        scrollMedicamentos.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollMedicamentos.setPreferredSize(new Dimension(990, 300));
        scrollMedicamentos.removerEscuchadorRuedaRaton();
        scrollMedicamentos.setOpaque(false);
        scrollMedicamentos.getViewport().setOpaque(false);
        
        
        HJButton bAgregarMedicamento = new HJButton(new IconoDeImagen("AgregarMedicamento.png", -1, 20), "Agregar Medicamento", Colores.COLORES_BOTONES);
        bAgregarMedicamento.addActionListener( e -> agregarMedicamento() );
        
        JPanel panelBotonAgregarMedicamento = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonAgregarMedicamento.add(bAgregarMedicamento);
        panelBotonAgregarMedicamento.setOpaque(false);
        
        
        Box boxTratamiento = Box.createVerticalBox();
        boxTratamiento.add(panelEtiquetasMedicamentos);
        boxTratamiento.add(scrollMedicamentos);
        boxTratamiento.add(Box.createVerticalStrut(10));
        boxTratamiento.add(panelBotonAgregarMedicamento);
        
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
        
        HJButton bExamenes = new HJButton(new IconoDeImagen("Examenes.png", -1, 30), "Exámenes de Laboratorio", Colores.COLORES_BOTONES);
        bExamenes.addActionListener( e -> mostrarPanelExamenesDeLaboratorio() );
        
        JPanel panelBotonExamenes = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonExamenes.add(bExamenes);
        panelBotonExamenes.setOpaque(false);
        
        lExamenes = new HJLabel(EXAMENES_NO_ESTABLECIDOS);
        
        JPanel panelEtiquetaExamenes = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelEtiquetaExamenes.add(lExamenes);
        panelEtiquetaExamenes.setOpaque(false);
        
        Box boxExamenes = Box.createVerticalBox();
        boxExamenes.add(panelBotonExamenes);
        boxExamenes.add(Box.createVerticalStrut(5));
        boxExamenes.add(panelEtiquetaExamenes);
        
        JPanel panelExamenes = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelExamenes.add(boxExamenes);
        panelExamenes.setOpaque(false);
        
        
        //..........................
        
        HJButton bEstudios = new HJButton(new IconoDeImagen("Radiologia.png", -1, 30), "Estudios Radiológicos", Colores.COLORES_BOTONES);
        bEstudios.addActionListener( e -> mostrarPanelEstudiosRadiologicos() );
        
        JPanel panelBotonEstudios = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonEstudios.add(bEstudios);
        panelBotonEstudios.setOpaque(false);
        
        lEstudios = new HJLabel(ESTUDIOS_NO_ESTABLECIDOS);
        
        JPanel panelEtiquetaEstudios = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelEtiquetaEstudios.add(lEstudios);
        panelEtiquetaEstudios.setOpaque(false);
        
        Box boxEstudios = Box.createVerticalBox();
        boxEstudios.add(panelBotonEstudios);
        boxEstudios.add(Box.createVerticalStrut(5));
        boxEstudios.add(panelEtiquetaEstudios);
        
        JPanel panelEstudios = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelEstudios.add(boxEstudios);
        panelEstudios.setOpaque(false);
        
        
        //....
        
        Box boxExamenesEstudios = Box.createHorizontalBox();
        boxExamenesEstudios.add(Box.createHorizontalStrut(30));
        boxExamenesEstudios.add(panelExamenes);
        boxExamenesEstudios.add(panelEstudios);
        boxExamenesEstudios.add(Box.createHorizontalStrut(30));
        
        
        //..........................
        
        lFechaCita = new HJLabel();
        lFechaCita.setFont(new Font("Arial", Font.BOLD, 18));
        lFechaCita.setForeground(Colores.NEGRO);
        
        JPanel panelFechaProximaCita = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelFechaProximaCita.add(new HJLabel("Próxima cita: "));
        panelFechaProximaCita.add(lFechaCita);
        panelFechaProximaCita.setOpaque(false);
        
        
        bEstablecerCita = new HJButton(new IconoDeImagen("AnotarCita.png", -1, 20), "Establecer Cita", Colores.COLORES_BOTONES);
        bEstablecerCita.addActionListener( e -> {
            
            PanelCalendario panelCalendario = new PanelCalendario(new String[] {""+nroHistoria});
            panelCalendario.setVisible(true);
            
            colocarProximaCita();
        });
        
        bEliminarCita = new HJButton(new IconoDeImagen("Eliminar.png", -1, 20), "Eliminar Cita", Colores.COLORES_BOTONES);
        bEliminarCita.addActionListener( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea eliminar la próxima cita?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
                eliminarCitaActual();
        });
        
        JPanel panelBotonesCita = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonesCita.add(bEstablecerCita);
        panelBotonesCita.add(bEliminarCita);
        panelBotonesCita.setOpaque(false);
        
        
        Box boxProximaCita = Box.createVerticalBox();
        boxProximaCita.add(panelFechaProximaCita);
        boxProximaCita.add(Box.createVerticalStrut(5));
        boxProximaCita.add(panelBotonesCita);
        
        
        colocarProximaCita();
        
        
        //..........................
        
        HJPaintedBox boxSuperior = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(245,250,245), new Color(225,240,225), null, null, 100, true);
        boxSuperior.add(Box.createVerticalStrut(30));
        boxSuperior.add(panelSuperior);
        boxSuperior.add(Box.createVerticalStrut(30));
        boxSuperior.add(panelExamenFisico);
        boxSuperior.add(Box.createVerticalStrut(30));
        boxSuperior.add(panelDiagnosticoMacro);
        boxSuperior.add(Box.createVerticalStrut(30));
        boxSuperior.add(panelTratamientoMacro);
        boxSuperior.add(Box.createVerticalStrut(30));
        boxSuperior.add(boxExamenesEstudios);
        boxSuperior.add(Box.createVerticalStrut(15));
        boxSuperior.add(boxProximaCita);
        boxSuperior.add(Box.createVerticalStrut(30));
        
        JScrollPane scrollBoxSuperior = new JScrollPane(boxSuperior);
        scrollBoxSuperior.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBoxSuperior.setPreferredSize(new Dimension(1100, 570));
        scrollBoxSuperior.getVerticalScrollBar().setUnitIncrement(10);
        
        
        //...........................
        
        HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
        bAceptar.addActionListener( e -> guardarConsulta() );
        
        HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
        bCancelar.addActionListener( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea cancelar esta consulta?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
            {
                setVisible(false);
                dispose();
            }
        });
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.add(bAceptar);
        panelBotones.add(bCancelar);
        panelBotones.setOpaque(false);
        
        
        //.......................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(scrollBoxSuperior);
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private HJLabel crearEtiquetaAzul(String texto) {
        
        HJLabel etiqueta = new HJLabel(texto);
        etiqueta.setForeground(colorAzulLlamativo);
        
        return etiqueta;
    }
    
    
    //..........................
    
    
    private void agregarMedicamento() {
        
        if(estanMedicamentosListos())
        {
            PanelMedicamento panelMedicamento = new PanelMedicamento();
            panelMedicamento.anadirEscuchadorBotonEliminar( e -> eliminarMedicamento(panelMedicamento) );
            
            boxMedicamentos.add(panelMedicamento);
            
            boxMedicamentos.validate();
            scrollMedicamentos.validate();
            scrollMedicamentos.getVerticalScrollBar().setValue(scrollMedicamentos.getVerticalScrollBar().getMaximum());
            
        }else{ HJDialog.mostrarMensaje("Casilla(s) Disponible(s)", new String[] {"Hay casilla(s) disponible(s) para llenar. Utilice la(s) misma(s)."}, IconoDeImagen.INFORMACION, null); }
    }
    
    
    private void eliminarMedicamento(PanelMedicamento panelMedicamento) {
        
        boxMedicamentos.remove(panelMedicamento);
        
        boxMedicamentos.validate();
        scrollMedicamentos.validate();
        scrollMedicamentos.repaint();
    }
    
    
    private boolean estanMedicamentosListos() {
        
        for(int i=0 ; i<=boxMedicamentos.getComponentCount()-1 ; i++)
        {
            if(((PanelMedicamento)boxMedicamentos.getComponent(i)).estaListo()==false)
                return false;
        }
        
        return true;
    }
    
    
    //...........................
    
    private void mostrarPanelExamenesDeLaboratorio() {
        
        panelExamenesLaboratorio.setVisible(true);
        
        if(panelExamenesLaboratorio.obtenerMapaExamenes().size()>0)
        {
            lExamenes.setText(EXAMENES_ESTABLECIDOS);
            
            lExamenes.setForeground(colorVerdeOscuro);
            
        }else{
            
            lExamenes.setText(EXAMENES_NO_ESTABLECIDOS);
            
            lExamenes.setForeground(Colores.TEXTO);
        }
    }
    
    
    private void mostrarPanelEstudiosRadiologicos() {
        
        panelEstudiosRadiologicos.setVisible(true);
        
        if(panelEstudiosRadiologicos.obtenerMapaEstudios().size()>0)
        {
            lEstudios.setText(ESTUDIOS_ESTABLECIDOS);
            
            lEstudios.setForeground(colorVerdeOscuro);
            
        }else{
            
            lEstudios.setText(ESTUDIOS_NO_ESTABLECIDOS);
            
            lEstudios.setForeground(Colores.TEXTO);
        }
    }
    
    
    //..........................
    
    private void colocarProximaCita() {
        
        String[] cadenaArchivos = (new File(Directorios.CITAS)).list();
        
        for(int i=0 ; i<=cadenaArchivos.length-1 ; i++)
        {
            if(LocalDate.parse((cadenaArchivos[i].split(".dda"))[0], DateTimeFormatter.ofPattern("uuuu-MM-dd")).isEqual(LocalDate.now()))
                continue;
            
            HashMap<String,String> mapaDia = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.CITAS+cadenaArchivos[i]);
            
            if(mapaDia.containsValue(""+nroHistoria))
            {
                String[] cadenaFecha = (cadenaArchivos[i].split(".dda"))[0].split("-");
                
                lFechaCita.setText(cadenaFecha[2]+"-"+cadenaFecha[1]+"-"+cadenaFecha[0]);
                
                bEstablecerCita.setVisible(false);
                bEliminarCita.setVisible(true);
                
                return;
            }
        }
        
        lFechaCita.setText(CITA_NO_ESTABLECIDA);
        
        bEliminarCita.setVisible(false);
        bEstablecerCita.setVisible(true);
    }
    
    
    private void eliminarCitaActual() {
        
        String[] cadenaFecha = lFechaCita.getText().split("-");
        
        if(cadenaFecha.length>0)
        {
            String fechaCita = cadenaFecha[2]+"-"+cadenaFecha[1]+"-"+cadenaFecha[0];
            
            HashMap<String,String> mapaDia = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.CITAS+fechaCita+".dda");
            
            Set<String> keys = mapaDia.keySet();
            
            Iterator<String> iterador = keys.iterator();
            
            while(iterador.hasNext())
            {
                String key = iterador.next();
                
                if(mapaDia.get(key)!=null && mapaDia.get(key).equals(""+nroHistoria))
                {
                    mapaDia.remove(key);
                    
                    if(mapaDia.size()>0)
                        Utilidades.guardarEnArchivo(Directorios.CITAS+fechaCita+".dda", mapaDia);
                    else
                        Utilidades.eliminarArchivo(Directorios.CITAS+fechaCita+".dda");
                    
                    break;
                }
            }
        }
        
        colocarProximaCita();
    }
    
    
    //..........................
    
    private boolean estanCasillasListas() {
        
        if(taMotivoConsulta.esTextoValido()==false || taSintomas.esTextoValido()==false || taSignos.esTextoValido()==false || taDiagnostico.esTextoValido()==false)
        {
            HJDialog.mostrarMensaje("Faltan Datos", new String[] {"Los datos con etiquetas azules son necesarios. Por favor, llénelos."}, IconoDeImagen.ADVERTENCIA, null);
            
            return false;
        }
        
        
        if(tTalla.esNumerico()==false || tPeso.esNumerico()==false || tTemperatura.esNumerico()==false || tCC.esNumerico()==false || tCT.esNumerico()==false || tCA.esNumerico()==false || tFR.esNumerico()==false || tFC.esNumerico()==false || tTS.esNumerico()==false || tTD.esNumerico()==false)
        {
            HJDialog.mostrarMensaje("Error al Ingresar Datos en 'Examen Físico'", new String[] {"Los valores introducidos en 'Examen Físico' deben ser numéricos (enteros o con decimales).", "Utilice el punto (.) para separar decimales.", "(Ejemplo: 32 o 32.4)"}, IconoDeImagen.ADVERTENCIA, null);
            
            return false;
        }
        
        
        return true;
    }
    
    
    private void guardarConsulta() {
        
        if(estanCasillasListas())
        {
            MapaDatos mapaConsulta = new MapaDatos();
            mapaConsulta.put(Consulta.KEY_MOTIVO_CONSULTA, taMotivoConsulta.getText());
            mapaConsulta.put(Consulta.KEY_SINTOMAS, taSintomas.getText());
            if(tTalla.esTextoValido())  mapaConsulta.put(Consulta.KEY_TALLA, tTalla.getText());
            if(tPeso.esTextoValido())  mapaConsulta.put(Consulta.KEY_PESO, tPeso.getText());
            if(tTemperatura.esTextoValido())  mapaConsulta.put(Consulta.KEY_TEMPERATURA, tTemperatura.getText());
            if(tCC.esTextoValido())  mapaConsulta.put(Consulta.KEY_CC, tCC.getText());
            if(tCT.esTextoValido())  mapaConsulta.put(Consulta.KEY_CT, tCT.getText());
            if(tCA.esTextoValido())  mapaConsulta.put(Consulta.KEY_CA, tCA.getText());
            if(tFR.esTextoValido())  mapaConsulta.put(Consulta.KEY_FR, tFR.getText());
            if(tFC.esTextoValido())  mapaConsulta.put(Consulta.KEY_FC, tFC.getText());
            if(tTS.esTextoValido())  mapaConsulta.put(Consulta.KEY_TENSION_SIST, tTS.getText());
            if(tTD.esTextoValido())  mapaConsulta.put(Consulta.KEY_TENSION_DIAST, tTD.getText());
            mapaConsulta.put(Consulta.KEY_SIGNOS, taSignos.getText());
            mapaConsulta.put(Consulta.KEY_DIAGNOSTICO, taDiagnostico.getText());
            
            String nombreArchivo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd'_'HH-mm-ss"))+".dda";
            
            Utilidades.crear_Y_GuardarEnArchivo(Directorios.CONSULTAS+nroHistoria, nombreArchivo, mapaConsulta);
            
            
            HashMap<String,HashMap<String,String>> mapaMedicamentos = new HashMap<>(0);
            
            for(int i=0 ; i<=boxMedicamentos.getComponentCount()-1 ; i++)
            {
                PanelMedicamento panelMedicamento = (PanelMedicamento)boxMedicamentos.getComponent(i);
                
                if(panelMedicamento.estaListo())
                {
                    HashMap<String,String> mapa = new HashMap<>(0);
                    mapa.put(PanelMedicamento.ANTIBIOTICO, panelMedicamento.obtenerAntibiotico());
                    mapa.put(PanelMedicamento.PRESCRIPCION_INDIVIDUAL, panelMedicamento.conPrescripcionIndividual());
                    mapa.put(PanelMedicamento.CAJAS, panelMedicamento.obtenerCantidadCajas());
                    mapa.put(PanelMedicamento.INDICACIONES, panelMedicamento.obtenerIndicaciones());
                    
                    mapaMedicamentos.put(panelMedicamento.obtenerMedicamento(), mapa);
                }
            }
            
            if(mapaMedicamentos.size()>0)
                Utilidades.crear_Y_GuardarEnArchivo(Directorios.MEDICAMENTOS+nroHistoria, nombreArchivo, mapaMedicamentos);
            
            
            HashMap<String,ArrayList<String>> mapaExamenes = panelExamenesLaboratorio.obtenerMapaExamenes();
            
            if(mapaExamenes.size()>0)
                Utilidades.crear_Y_GuardarEnArchivo(Directorios.EXAMENES_DE_LABORATORIO+nroHistoria, nombreArchivo, mapaExamenes);
            
            
            HashMap<String,String> mapaEstudiosRadiologicos = panelEstudiosRadiologicos.obtenerMapaEstudios();
            
            if(mapaEstudiosRadiologicos.size()>0)
                Utilidades.crear_Y_GuardarEnArchivo(Directorios.ESTUDIOS_RADIOLOGICOS+nroHistoria, nombreArchivo, mapaEstudiosRadiologicos);
            
            
            setVisible(false);
            dispose();
            
            if(HJDialog.mostrarDialogoPregunta("Impresión de Récipes y/u Órdenes", new String[] {"¿Desea proceder a editar e imprimir los récipes y/u órdenes?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.PREGUNTA, null)==0)
            {
                PanelImpresionRecipes panelImpresionRecipes = new PanelImpresionRecipes(nroHistoria, true);
                
                Thread hiloLlenar = new Thread( () -> {

                    while(panelImpresionRecipes.isVisible()==false)
                    {
                        Utilidades.esperar(20);
                    }

                    panelImpresionRecipes.colocarRecipes();
                });
                hiloLlenar.start();
                
                panelImpresionRecipes.setVisible(true);
                
            }
        }
    }
    
    
}
