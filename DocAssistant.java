/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.OverlayLayout;


public class DocAssistant extends JFrame {
    
    private static boolean seRegistroLucida;
    public static final String LUCIDA_CALLIGRAPHY = "Lucida Calligraphy";
    public static final String TIMES_NEW_ROMAN = "Times New Roman";
    private static boolean seRegistroCopperplate;
    public static final String COPPERPLATE_GOTHIC_LIGHT = "Copperplate Gothic Light";
    public static final String ARIAL = "Arial";
    
    private final HJLabel lNombreDoctor;
    private final HJLabel lEspecialidad;
    
    private final HJClock reloj;
    
    private final Box boxPacientesParaHoy;
    private final JScrollPane scrollBoxPacientesParaHoy;
    private ButtonGroup grupo_rb_PaneslesCitaHoy;
    private PanelCitaHoy panelCitaHoySeleccionado;
    
    private final HJButton bHacerConsulta;
    private final HJButton bVerHistoria;
    private final HJButton bRemover;
    
    private final JLabel lVersion;
    
    private final JLabel lTipoLicencia;
    private final String PRUEBA = "Prueba";
    private final String ACTIVADA = "Activada";
    
    
    public DocAssistant() {
        
        setLayout(new BorderLayout(0,0));
        
        setSize(1000,700);
        
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setUndecorated(true);
        
        if(estaSoportadaTransparencia())
            setShape(obtenerFormaDeMarco());
        
        
        ArrayList<Image> arrayImagenes = new ArrayList<>(0);
        arrayImagenes.add((new IconoDeImagen("LogoDocAssistant.png", -1, 30)).getImage());
        arrayImagenes.add((new IconoDeImagen("LogoDocAssistant.png", -1, 50)).getImage());
        arrayImagenes.add((new IconoDeImagen("LogoDocAssistant.png", -1, 70)).getImage());
        
        setIconImages(arrayImagenes);
        
        
        //.................................
        
        Color[] coloresHJMenuItemsArchivo = {Colores.NORMAL, Colores.ROLLOVER};
        
        HJMenuItem itemNuevoPaciente = new HJMenuItem(new IconoDeImagen("NuevoPaciente.png", -1, 18), "Nuevo Paciente", coloresHJMenuItemsArchivo);
        itemNuevoPaciente.addActionListener( e -> mostrarPanelNuevoPaciente() );
        
        HJMenuItem itemBuscarPaciente = new HJMenuItem(new IconoDeImagen("BuscarPaciente.png", -1, 18), "Buscar Paciente", coloresHJMenuItemsArchivo);
        itemBuscarPaciente.addActionListener( e -> mostrarPanelBuscarPaciente() );
        
        HJMenuItem itemAnotarCita = new HJMenuItem(new IconoDeImagen("AnotarCita.png", -1, 18), "Anotar Cita", coloresHJMenuItemsArchivo);
        itemAnotarCita.addActionListener( e -> mostrarPanelAnotarCita() );
        
        HJMenuItem itemExportarHistoria = new HJMenuItem(new IconoDeImagen("ExportarHistoria.png", -1, 18), "Exportar Historia", coloresHJMenuItemsArchivo);
        itemExportarHistoria.addActionListener( e -> mostrarPanelParaExportarHistoria() );
        
        HJMenuItem itemImportarHistoria = new HJMenuItem(new IconoDeImagen("ImportarHistoria.png", -1, 18), "Importar Historia", coloresHJMenuItemsArchivo);
        itemImportarHistoria.addActionListener( e -> mostrarDialogoImportarHistoria() );
        
        HJMenuItem itemExportarTodo = new HJMenuItem(new IconoDeImagen("ExportarTodo.png", -1, 18), "Exportar Todo", coloresHJMenuItemsArchivo);
        itemExportarTodo.addActionListener( e -> mostrarDialogoExportarTodo() );
        
        HJMenuItem itemImportarTodo = new HJMenuItem(new IconoDeImagen("ImportarTodo.png", -1, 18), "Importar Todo", coloresHJMenuItemsArchivo);
        itemImportarTodo.addActionListener( e -> mostrarDialogoImportarTodo() );
        
        HJMenuItem itemAdquirirLicencia = new HJMenuItem(new IconoDeImagen("Abanico.png", -1, 12), "Adquirir Licencia", coloresHJMenuItemsArchivo);
        itemAdquirirLicencia.addActionListener( e -> mostrarPanelAdquirirLicencia() );
        
        HJMenuItem itemActivarProducto = new HJMenuItem(new IconoDeImagen("Llave.png", -1, 18), "Activar Producto", coloresHJMenuItemsArchivo);
        itemActivarProducto.addActionListener( e -> mostrarPanelActivarProducto() );
        
        HJMenuItem itemAyuda = new HJMenuItem(new IconoDeImagen("AyudaNormal.png", -1, 18), "Ayuda", coloresHJMenuItemsArchivo);
        itemAyuda.addActionListener( e -> mostrarAyuda() );
        
        HJMenuItem itemSalir = new HJMenuItem(new IconoDeImagen("Salir.png", -1, 18), "Salir", coloresHJMenuItemsArchivo);
        itemSalir.addActionListener( e -> System.exit(0) );
        
        HJPopupMenu menuPopupArchivo = new HJPopupMenu();
        menuPopupArchivo.add(itemNuevoPaciente);
        menuPopupArchivo.add(itemBuscarPaciente);
        menuPopupArchivo.add(itemAnotarCita);
        menuPopupArchivo.addSeparator();
        menuPopupArchivo.add(itemExportarHistoria);
        menuPopupArchivo.add(itemImportarHistoria);
        menuPopupArchivo.add(itemExportarTodo);
        menuPopupArchivo.add(itemImportarTodo);
        menuPopupArchivo.addSeparator();
        menuPopupArchivo.add(itemAdquirirLicencia);
        menuPopupArchivo.add(itemActivarProducto);
        menuPopupArchivo.addSeparator();
        menuPopupArchivo.add(itemAyuda);
        menuPopupArchivo.addSeparator();
        menuPopupArchivo.add(itemSalir);
        
        
        HJCustomizedButton botonArchivo = new HJCustomizedButton("Archivo", new Color[] {Colores.TEXTO, Colores.NORMAL}, true, false, 0);
        botonArchivo.setFont(new Font("Dialog", Font.BOLD, 18));
        botonArchivo.establecerPopupMenu(menuPopupArchivo);
        
        HJTonguePanel panelLenguaBotonArchivo = new HJTonguePanel(new FlowLayout(FlowLayout.LEFT, 0, 0), HJTonguePanel.LENGUETA_DERECHA, new Color(119,119,119), new Color(212,212,212), new Color(100,100,100), new Color(100,100,100));
        panelLenguaBotonArchivo.add(Box.createHorizontalStrut(10));
        panelLenguaBotonArchivo.add(botonArchivo);
        panelLenguaBotonArchivo.setPreferredSize(new Dimension(110, 30));
        
        JPanel panelBotonArchivo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelBotonArchivo.add(panelLenguaBotonArchivo);
        panelBotonArchivo.setOpaque(false);
        
        Box boxPanelBotonArchivo = Box.createVerticalBox();
        boxPanelBotonArchivo.add(panelBotonArchivo);
        boxPanelBotonArchivo.add(Box.createVerticalStrut(35));
        
        
        //...............................
        
        registrarFormatosLetras();
        
        
        //....
        
        lNombreDoctor = new HJLabel();
        if(seRegistroLucida)  lNombreDoctor.setFont(new Font(LUCIDA_CALLIGRAPHY, Font.PLAIN, 20));
        else  lNombreDoctor.setFont(new Font(TIMES_NEW_ROMAN, Font.ITALIC, 20));
        
        JPanel panelNombreDoctor = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelNombreDoctor.add(lNombreDoctor);
        panelNombreDoctor.setPreferredSize(new Dimension(550, 30));
        panelNombreDoctor.setOpaque(false);
        
        
        lEspecialidad = new HJLabel();
        if(seRegistroCopperplate)  lEspecialidad.setFont(new Font(COPPERPLATE_GOTHIC_LIGHT, Font.BOLD, 16));
        else  lEspecialidad.setFont(new Font(ARIAL, Font.BOLD, 16));
        
        JPanel panelEspecialidad = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelEspecialidad.add(lEspecialidad);
        panelEspecialidad.setOpaque(false);
        
        
        Box boxNombreDoctorEspecialidad = Box.createVerticalBox();
        boxNombreDoctorEspecialidad.add(Box.createVerticalStrut(7));
        boxNombreDoctorEspecialidad.add(panelNombreDoctor);
        boxNombreDoctorEspecialidad.add(panelEspecialidad);
        boxNombreDoctorEspecialidad.add(Box.createVerticalStrut(7));
        
        HJPaintedPanel panelPintadoDoctor = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 0, 0), new Color(220,220,220), new Color(250,250,250), null, null, 50, false);
        panelPintadoDoctor.add(boxNombreDoctorEspecialidad);
        panelPintadoDoctor.setBorder(BorderFactory.createRaisedBevelBorder());
        
        
        //................................
        
        JLabel lHoraReloj = new JLabel();
        lHoraReloj.setFont(new Font("Dialog", Font.BOLD, 16));
        lHoraReloj.setForeground(Colores.AZUL_LLAMATIVO);
        
        reloj = new HJClock(lHoraReloj);
        
        JPanel panelReloj = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelReloj.add(lHoraReloj);
        panelReloj.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.LINEAS));
        panelReloj.setOpaque(false);
        
        
        //.........
        
        HJLabel lCronometro = new HJLabel("00:00:00");
        lCronometro.setFont(new Font("Dialog", Font.BOLD, 16));
        
        HJCustomizedButton botonCronometro = new HJCustomizedButton("CronometroNormal.png", "CronometroRollover.png", 30, 30, false);
        
        HJChronometer cronometro = new HJChronometer(botonCronometro, lCronometro);
        
        JPanel panelCronometro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 2));
        panelCronometro.add(Box.createHorizontalStrut(5));
        panelCronometro.add(botonCronometro);
        panelCronometro.add(Box.createHorizontalStrut(5));
        panelCronometro.add(lCronometro);
        panelCronometro.add(Box.createHorizontalStrut(5));
        panelCronometro.setOpaque(false);
        
        
        //.......
        
        Box boxRelojCronometro = Box.createVerticalBox();
        boxRelojCronometro.add(Box.createVerticalStrut(4));
        boxRelojCronometro.add(panelReloj);
        boxRelojCronometro.add(panelCronometro);
        boxRelojCronometro.setMaximumSize(new Dimension(200, 70));
        
        HJPaintedPanel panelPintadoRelojCronometro = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 0, 0), new Color(209,220,243), new Color(247,249,253), null, null, 50, false);
        panelPintadoRelojCronometro.add(boxRelojCronometro);
        panelPintadoRelojCronometro.setBorder(BorderFactory.createRaisedBevelBorder());
        
        
        //..................................
        
        Color[] coloresHJMenuItemsConfiguracion = {Colores.NORMAL, Colores.ROLLOVER};
        
        HJMenuItem itemDatosDelDoctor = new HJMenuItem(new IconoDeImagen("Doctor.png", -1, 18), "Datos del Doctor", coloresHJMenuItemsConfiguracion);
        itemDatosDelDoctor.addActionListener( e -> mostrarPanelDatosDelDoctor() );
        
        HJMenuItem itemPersonalizarRecipes = new HJMenuItem(new IconoDeImagen("PersonalizarRecipes.png", -1, 18), "Personalizar Récipes", coloresHJMenuItemsConfiguracion);
        itemPersonalizarRecipes.addActionListener( e -> mostrarPanelPersonalizarRecipes() );
        
        HJMenuItem itemActualizaciones = new HJMenuItem(new IconoDeImagen("ActualizarPrograma.png", -1, 18), "Actualizaciones", coloresHJMenuItemsConfiguracion);
        itemActualizaciones.addActionListener( e -> mostrarConfiguracionActualizaciones() );
        
        HJPopupMenu menuPopupConfiguracion = new HJPopupMenu();
        menuPopupConfiguracion.add(itemDatosDelDoctor);
        menuPopupConfiguracion.add(itemPersonalizarRecipes);
        menuPopupConfiguracion.add(itemActualizaciones);
        
        
        HJCustomizedButton botonConfiguracion = new HJCustomizedButton("ConfiguracionNormal.png", "ConfiguracionRollover.png", 45, 45, false);
        botonConfiguracion.setToolTipText("Configuración");
        botonConfiguracion.establecerPopupMenuDesplazandoX(menuPopupConfiguracion, -185);
        
        Box boxBotonConfiguracion = Box.createVerticalBox();
        boxBotonConfiguracion.add(Box.createVerticalStrut(9));
        boxBotonConfiguracion.add(botonConfiguracion);
        
        HJTonguePanel panelLenguaBotonConfiguracion = new HJTonguePanel(new FlowLayout(FlowLayout.RIGHT, 0, 0), HJTonguePanel.LENGUETA_IZQUIERDA, new Color(119,119,119), new Color(212,212,212), new Color(100,100,100), new Color(100,100,100));
        panelLenguaBotonConfiguracion.add(boxBotonConfiguracion);
        panelLenguaBotonConfiguracion.add(Box.createHorizontalStrut(12));
        panelLenguaBotonConfiguracion.setPreferredSize(new Dimension(103, 65));
        
        JPanel panelBotonConfiguracion = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelBotonConfiguracion.add(panelLenguaBotonConfiguracion);
        panelBotonConfiguracion.setOpaque(false);
        
        Box boxPanelBotonConfiguracion = Box.createVerticalBox();
        boxPanelBotonConfiguracion.add(panelBotonConfiguracion);
        
        
        //..................................
        
        JPanel panelPanelesSuperiores = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelPanelesSuperiores.add(boxPanelBotonArchivo);
        panelPanelesSuperiores.add(Box.createHorizontalStrut(40));
        panelPanelesSuperiores.add(panelPintadoDoctor);
        panelPanelesSuperiores.add(Box.createHorizontalStrut(40));
        panelPanelesSuperiores.add(panelPintadoRelojCronometro);
        panelPanelesSuperiores.add(Box.createHorizontalStrut(36));
        panelPanelesSuperiores.add(boxPanelBotonConfiguracion);
        panelPanelesSuperiores.setOpaque(false);
        
        
        //..................................
        
        Font fontBotonesPrincipales = new Font("Arial", Font.BOLD, 18);
        
        Color[] coloresBotonesPrincipales = Colores.COLORES_BOTONES;
        
        HJButton botonNuevoPaciente = new HJButton(new IconoDeImagen("NuevoPaciente.png", -1, 60), "Nuevo Paciente", fontBotonesPrincipales, coloresBotonesPrincipales, true, 170, 100, null);
        botonNuevoPaciente.addActionListener( e -> mostrarPanelNuevoPaciente() );
        
        HJButton botonBuscarPaciente = new HJButton(new IconoDeImagen("BuscarPaciente.png", -1, 60), "Buscar Paciente", fontBotonesPrincipales, coloresBotonesPrincipales, true, 170, 100, null);
        botonBuscarPaciente.addActionListener( e -> mostrarPanelBuscarPaciente() );
        
        HJButton botonAnotarCita = new HJButton(new IconoDeImagen("AnotarCita.png", -1, 60), "Anotar Cita", fontBotonesPrincipales, coloresBotonesPrincipales, true, 170, 100, null);
        botonAnotarCita.addActionListener( e -> mostrarPanelAnotarCita() );
        
        JPanel panelBotonesPrimeraLinea = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonesPrimeraLinea.add(botonNuevoPaciente);
        panelBotonesPrimeraLinea.add(Box.createHorizontalStrut(50));
        panelBotonesPrimeraLinea.add(botonBuscarPaciente);
        panelBotonesPrimeraLinea.add(Box.createHorizontalStrut(50));
        panelBotonesPrimeraLinea.add(botonAnotarCita);
        panelBotonesPrimeraLinea.setOpaque(false);
        
        
        HJButton botonPersonalizarRecipes = new HJButton(new IconoDeImagen("PersonalizarRecipes.png", -1, 60), "Personalizar Rp.", fontBotonesPrincipales, coloresBotonesPrincipales, true, 170, 100, null);
        botonPersonalizarRecipes.addActionListener( e -> mostrarPanelPersonalizarRecipes() );
        
        HJButton botonRecipeRapido = new HJButton(new IconoDeImagen("RecipeRapido.png", -1, 60), "Récipe Rápido", fontBotonesPrincipales, coloresBotonesPrincipales, true, 170, 100, null);
        botonRecipeRapido.addActionListener( e -> mostrarPanelRecipeRapido() );
        
        HJButton botonEstadisticas = new HJButton(new IconoDeImagen("Estadisticas.png", -1, 60), "Estadísticas", fontBotonesPrincipales, coloresBotonesPrincipales, true, 170, 100, null);
        botonEstadisticas.addActionListener( e -> mostrarPanelEstadisticas() );
        
        
        JPanel panelBotonesSegundaLinea = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonesSegundaLinea.add(botonPersonalizarRecipes);
        panelBotonesSegundaLinea.add(Box.createHorizontalStrut(50));
        panelBotonesSegundaLinea.add(botonRecipeRapido);
        panelBotonesSegundaLinea.add(Box.createHorizontalStrut(50));
        panelBotonesSegundaLinea.add(botonEstadisticas);
        panelBotonesSegundaLinea.setOpaque(false);
        
        
        //.................................
        
        Font fontBotonesUtilidades1 = new Font("Arial", Font.BOLD, 16);
        Font fontBotonesUtilidades2 = new Font("Arial", Font.BOLD, 14);
        
        HJButton botonCalendario = new HJButton(new IconoDeImagen("Calendario.png", -1, 40), "Calendario", fontBotonesUtilidades1, coloresBotonesPrincipales, true, 130, 80, null);
        botonCalendario.addActionListener( e -> mostrarPanelCalendario() );
        
        HJButton botonProgDeMejoras = new HJButton(new IconoDeImagen("Feedback.png", -1, 40), "Prog. de Mejoras", fontBotonesUtilidades2, coloresBotonesPrincipales, true, 130, 80, null);
        botonProgDeMejoras.addActionListener( e -> Utilidades.abrirVinculo("http://www.abanicosystems.com/programa-de-mejoras") );
        
        HJButton botonSoporte = new HJButton(new IconoDeImagen("Soporte.png", -1, 40), "Soporte", fontBotonesUtilidades1, coloresBotonesPrincipales, true, 130, 80, null);
        botonSoporte.addActionListener( e -> mostrarSoporte() );
        
        HJButton botonActualizaciones = new HJButton(new IconoDeImagen("ActualizarPrograma.png", -1, 40), "Actualizaciones", fontBotonesUtilidades2, coloresBotonesPrincipales, true, 130, 80, null);
        botonActualizaciones.addActionListener( e -> mostrarConfiguracionActualizaciones() );
        
        JPanel panelBotonesUtilidades = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        panelBotonesUtilidades.add(botonCalendario);
        panelBotonesUtilidades.add(botonProgDeMejoras);
        panelBotonesUtilidades.add(botonSoporte);
        panelBotonesUtilidades.add(botonActualizaciones);
        panelBotonesUtilidades.setOpaque(false);
        
        
        //.................................
        
        
        Box boxBotonesGrandes = Box.createVerticalBox();
        boxBotonesGrandes.add(Box.createVerticalStrut(50));
        boxBotonesGrandes.add(panelBotonesPrimeraLinea);
        boxBotonesGrandes.add(Box.createVerticalStrut(50));
        boxBotonesGrandes.add(panelBotonesSegundaLinea);
        boxBotonesGrandes.add(Box.createVerticalStrut(50));
        boxBotonesGrandes.add(new HJDividingPanel("Utilidades y Otros", new Font("Arial", Font.BOLD, 20), Colores.TEXTO, HJDividingPanel.IZQUIERDA, 45, Colores.LINEAS_OSCURAS, 680, 4));
        boxBotonesGrandes.add(panelBotonesUtilidades);
        boxBotonesGrandes.setPreferredSize(new Dimension(700, 550));
        
        
        //..................................
        
        HJLabel lPacientesParaHoy = new HJLabel("Pacientes para hoy:");
        lPacientesParaHoy.setFont(new Font("Dialog", Font.BOLD, 16));
        
        JPanel panelLPacientesParaHoy = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLPacientesParaHoy.add(lPacientesParaHoy);
        panelLPacientesParaHoy.setOpaque(false);
        
        
        //.........
        
        boxPacientesParaHoy = Box.createVerticalBox();
        
        scrollBoxPacientesParaHoy = new JScrollPane(boxPacientesParaHoy);
        scrollBoxPacientesParaHoy.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBoxPacientesParaHoy.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBoxPacientesParaHoy.getVerticalScrollBar().setUnitIncrement(10);
        scrollBoxPacientesParaHoy.getViewport().setPreferredSize(new Dimension(274, 373));
        scrollBoxPacientesParaHoy.getViewport().setBackground(Colores.NORMAL_OSCURO);
        
        
        //..........
        
        bHacerConsulta = new HJButton(new IconoDeImagen("Consulta.png", -1, 20), "Hacer Consulta", coloresBotonesPrincipales);
        bHacerConsulta.addActionListener( e -> hacerConsulta() );
        bHacerConsulta.setEnabled(false);
        
        HJButton bActualizar = new HJButton(new IconoDeImagen("Actualizar.png", -1, 20), null, coloresBotonesPrincipales);
        bActualizar.addActionListener( e -> llenarBoxPacientes() );
        bActualizar.setToolTipText("Actualizar Lista");
        
        JPanel panelBotonesHacerConsultaActualizar = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotonesHacerConsultaActualizar.add(bHacerConsulta);
        panelBotonesHacerConsultaActualizar.add(bActualizar);
        panelBotonesHacerConsultaActualizar.setOpaque(false);
        
        
        bVerHistoria = new HJButton(new IconoDeImagen("Historia.png", -1, 20), "Ver Historia", coloresBotonesPrincipales);
        bVerHistoria.addActionListener( e -> mostrarPanelHistoria() );
        bVerHistoria.setEnabled(false);
        
        bRemover = new HJButton(new IconoDeImagen("Eliminar.png", -1, 20), "Remover", coloresBotonesPrincipales);
        bRemover.addActionListener( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea remover la cita de este paciente?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", coloresBotonesPrincipales), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", coloresBotonesPrincipales)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
                removerCita();
        });
        bRemover.setEnabled(false);
        
        JPanel panelBotonesVerHistoriaRemover = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotonesVerHistoriaRemover.add(bVerHistoria);
        panelBotonesVerHistoriaRemover.add(bRemover);
        panelBotonesVerHistoriaRemover.setOpaque(false);
        
        
        Box boxBotonesPacientesParaHoy = Box.createVerticalBox();
        boxBotonesPacientesParaHoy.add(Box.createVerticalStrut(15));
        boxBotonesPacientesParaHoy.add(panelBotonesHacerConsultaActualizar);
        boxBotonesPacientesParaHoy.add(Box.createVerticalStrut(15));
        boxBotonesPacientesParaHoy.add(panelBotonesVerHistoriaRemover);
        boxBotonesPacientesParaHoy.add(Box.createVerticalStrut(15));
        
        boxBotonesPacientesParaHoy.setOpaque(true);
        boxBotonesPacientesParaHoy.setBackground(Colores.NORMAL);
        
        boxBotonesPacientesParaHoy.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Colores.LINEAS));
        
        
        //..........
        
        Box boxPacientesParaHoyMacro = Box.createVerticalBox();
        boxPacientesParaHoyMacro.add(Box.createVerticalStrut(24));
        boxPacientesParaHoyMacro.add(panelLPacientesParaHoy);
        boxPacientesParaHoyMacro.add(Box.createVerticalStrut(5));
        boxPacientesParaHoyMacro.add(scrollBoxPacientesParaHoy);
        boxPacientesParaHoyMacro.add(boxBotonesPacientesParaHoy);
         
        
        //..................................
        
        llenarBoxPacientes();
        
        
        //..................................
        
        Box boxAmbosBoxes = Box.createHorizontalBox();
        boxAmbosBoxes.add(boxBotonesGrandes);
        boxAmbosBoxes.add(boxPacientesParaHoyMacro);
        
        
        //..................................
        
        URL urlFondoBox = getClass().getResource("images/FondoBox.jpg");
        
        if(urlFondoBox==null)  mostrarMensajeError_Y_Salir();
        
        Image imagenBox = (new ImageIcon(urlFondoBox)).getImage();
        
        int[] cuadroImagen = {200, 0, imagenBox.getWidth(null), imagenBox.getHeight(null)-300};
        
        
        HJBox boxContenido = new HJBox(BoxLayout.Y_AXIS, imagenBox, cuadroImagen);
        boxContenido.add(panelPanelesSuperiores);
        boxContenido.add(boxAmbosBoxes);
        
        
        //..................................
        
        lVersion = new JLabel(""+Version.PRIMER_NIVEL+"."+Version.SEGUNDO_NIVEL+"."+Version.TERCER_NIVEL);
        
        lTipoLicencia = new JLabel();
        
        JPanel panelVersionLicencia = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        panelVersionLicencia.add(Box.createHorizontalStrut(20));
        panelVersionLicencia.add(new JLabel("Versión: "));
        panelVersionLicencia.add(lVersion);
        panelVersionLicencia.add(new JLabel(" - "));
        panelVersionLicencia.add(lTipoLicencia);
        panelVersionLicencia.setOpaque(false);
        
        
        JPanel panelCopyright = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelCopyright.add(new JLabel("Copyright © 2015 Abanico Systems"));
        panelCopyright.setOpaque(false);
        
        
        HJCustomizedButton botonPaginaWeb = new HJCustomizedButton("http://www.abanicosystems.com", new Color[] {Colores.AZUL_LLAMATIVO, Colores.AZUL_OSCURO}, true, true, 1);
        botonPaginaWeb.setFont(new Font("Arial", Font.BOLD, 12));
        botonPaginaWeb.addActionListener( e -> Utilidades.abrirVinculo("http://www.abanicosystems.com") );
        
        JPanel panelPaginaWeb = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 5));
        panelPaginaWeb.add(new JLabel("Página web: "));
        panelPaginaWeb.add(botonPaginaWeb);
        panelPaginaWeb.add(Box.createHorizontalStrut(20));
        panelPaginaWeb.setOpaque(false);
        
        
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new OverlayLayout(panelInferior));
        
        panelInferior.add(panelPaginaWeb);
        panelInferior.add(panelCopyright);
        panelInferior.add(panelVersionLicencia);
        panelInferior.setBackground(Colores.NORMAL);
        
        panelInferior.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //.................................
        
        LocalDate fechaActual = LocalDate.now();
        
        String[] cadenaArchivos = (new File(Directorios.CITAS)).list();
        
        for(int i=0 ; i<=cadenaArchivos.length-1 ; i++)
        {
            if(LocalDate.parse((cadenaArchivos[i].split(".dda"))[0], DateTimeFormatter.ofPattern("uuuu-MM-dd")).isBefore(fechaActual))
                Utilidades.eliminarArchivo(Directorios.CITAS+cadenaArchivos[i]);
        }
        
        
        //.................................
        
        
        add(new PanelCabecera(this), BorderLayout.NORTH);
        add(boxContenido, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
        
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
    
    
    public static void registrarFormatosLetras() {
        
        seRegistroLucida = true;
        
        try{
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(Directorios.RECURSOS+"Lucida.ttf")));
        }catch(FontFormatException | IOException exc) {
            seRegistroLucida = false;
        }
        
        
        seRegistroCopperplate = true;
        
        try{
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(Directorios.RECURSOS+"Copperplate.ttf")));
        }catch(FontFormatException | IOException exc) {
            seRegistroCopperplate = false;
        }
    }
    
    public static boolean seRegistroLucida() { return seRegistroLucida; }
    
    public static boolean seRegistroCopperplate() { return seRegistroCopperplate; }
    
    
    public void mostrarMensajeErrorRegistroLetras() {
        
        if(seRegistroLucida==false || seRegistroCopperplate==false)
        {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error en Archivos", new String[] {"Algún(os) archivo(s) de tipo de letra fue(ron) alterado(s)."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
    public static void mostrarMensajeError_Y_Salir() {
        
        Toolkit.getDefaultToolkit().beep();
        HJDialog.mostrarMensaje("Error", new String[] {"Los archivos del programa fueron alterados.", " ", "A continuación, se cerrará el programa."}, IconoDeImagen.ERROR, null);
        System.exit(0);
    }
    
    
    public void ejecutarMetodos_E_Hilos() {
        
        mostrarMensajeErrorRegistroLetras();
        
        colocarDatosDoctor();
        
        establecerLicenciaActivada(Registro.estaProductoActivado());
        
        colocarIconoAreaNotificacion();
        
        reloj.start();
    }
    
    
    private void colocarDatosDoctor() {
        
        MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        lNombreDoctor.setText(mapaDatos.get(PanelDatosDoctor.TITULO)+" "+mapaDatos.get(PanelDatosDoctor.NOMBRE_COMPLETO));
        lEspecialidad.setText(mapaDatos.get(PanelDatosDoctor.ESPECIALIDAD));
    }
    
    
    private void establecerLicenciaActivada(boolean activada) {
        
        if(activada)  lTipoLicencia.setText(ACTIVADA);
        else  lTipoLicencia.setText(PRUEBA);
    }
    
    
    private void colocarIconoAreaNotificacion() {
        
        if(SystemTray.isSupported()==false)  return;
        
        
        MenuItem itemAcercaDe = new MenuItem("Acerda de");
        itemAcercaDe.addActionListener( e -> mostrarPanelAcercaDe() );
        
        MenuItem itemAyuda = new MenuItem("Ayuda");
        itemAyuda.addActionListener( e -> mostrarAyuda() );
        
        MenuItem itemActualizar = new MenuItem("Comprobar actualizaciones");
        itemActualizar.addActionListener( e -> comprobarActualizaciones() );
        
        MenuItem itemSalir = new MenuItem("Salir");
        itemSalir.addActionListener( e -> System.exit(0) );
        
        PopupMenu menuPopup = new PopupMenu();
        menuPopup.add(itemAcercaDe);
        menuPopup.add(itemAyuda);
        menuPopup.addSeparator();
        menuPopup.add(itemActualizar);
        menuPopup.addSeparator();
        menuPopup.add(itemSalir);
        
        TrayIcon iconoBandeja = new TrayIcon((new IconoDeImagen("LogoDocAssistant.png", -1, 60)).getImage(), "Abanico Doc Assistant", menuPopup);
        iconoBandeja.setImageAutoSize(true);
        
        try{
            SystemTray.getSystemTray().add(iconoBandeja);
        }catch(AWTException awte) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error al establecer el ícono en el área de notificación."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
    public void comprobarRecipesPersonalizados() {
        
        MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"ConfigRecipes.dda");
        
        if(mapaDatos==null)
        {
            HJDialog.mostrarMensaje("Personalice sus Récipes", new String[] {"Por favor, personalice sus récipes antes de comenzar a usar el programa."}, IconoDeImagen.INFORMACION, null);
            
            PanelPersonalizacionRecipes panel = new PanelPersonalizacionRecipes();
            panel.setVisible(true);
        }
    }
    
    
    //............................
    
    private void llenarBoxPacientes() {
        
        panelCitaHoySeleccionado = null;
        
        bHacerConsulta.setEnabled(false);
        bVerHistoria.setEnabled(false);
        bRemover.setEnabled(false);
        
        
        boxPacientesParaHoy.removeAll();
        
        HashMap<String,String> mapaCitas = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.CITAS+LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd"))+".dda");
        
        if(mapaCitas!=null)
        {
            ArrayList<String> arrayHoras = new ArrayList<>(0);
            
            Set<String> posiblesHoras = mapaCitas.keySet();
            
            posiblesHoras.stream()
                    .sorted()
                    .forEach( s -> {  if(s.contains(":"))  arrayHoras.add(s);  });
            
            
            grupo_rb_PaneslesCitaHoy = new ButtonGroup();
            
            for(int i=0 ; i<=arrayHoras.size()-1 ; i++)
            {
                PanelCitaHoy panelCitaHoy = new PanelCitaHoy(arrayHoras.get(i), mapaCitas.get(arrayHoras.get(i)));
                
                panelCitaHoy.obtenerRadioButton().addActionListener( e -> {
                    
                    panelCitaHoySeleccionado = panelCitaHoy;
                    
                    bHacerConsulta.setEnabled(true);
                    bRemover.setEnabled(true);
                    
                    if(Utilidades.esNumeroEnteroPositivo(panelCitaHoySeleccionado.obtenerPaciente()))  bVerHistoria.setEnabled(true);
                    else  bVerHistoria.setEnabled(false);
                });
                
                grupo_rb_PaneslesCitaHoy.add(panelCitaHoy.obtenerRadioButton());
                
                boxPacientesParaHoy.add(panelCitaHoy);
            }
        }
        
        boxPacientesParaHoy.validate();
        scrollBoxPacientesParaHoy.validate();
        scrollBoxPacientesParaHoy.repaint();
    }
    
    
    private void hacerConsulta() {
        
        String paciente = panelCitaHoySeleccionado.obtenerPaciente();
        
        if(Utilidades.esNumeroEnteroPositivo(paciente))
        {
            PanelConsulta panel = new PanelConsulta(Integer.parseInt(paciente));
            panel.setVisible(true);
            
        }else{
            
            int seleccion = HJDialog.mostrarDialogoPregunta("Paciente Nuevo", new String[] {"Este paciente es nuevo. Es necesario registrar sus datos primero."}, new HJButton[] {new HJButton(new IconoDeImagen("NuevoPaciente.png", -1, 20), "Registrar Paciente", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.INFORMACION, null);
            
            if(seleccion==0)
            {
                PanelNuevoPaciente panel = new PanelNuevoPaciente();
                panel.setVisible(true);   
            }
        }
    }
    
    
    private void mostrarPanelHistoria() {
        
        HJWaitingPanel panelEspera = new HJWaitingPanel();
        
        Thread hiloPanelHistoria = new Thread( () -> {
            
            PanelHistoria panelHistoria = new PanelHistoria(Integer.parseInt(panelCitaHoySeleccionado.obtenerPaciente()));
            
            panelEspera.setVisible(false);
            panelEspera.dispose();
            
            panelHistoria.setVisible(true);
        });
        hiloPanelHistoria.start();
        
        panelEspera.setVisible(true);
    }
    
    
    private void removerCita() {
        
        HashMap<String,String> mapaCitas = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.CITAS+LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd"))+".dda");
        
        if(mapaCitas!=null)
        {
            mapaCitas.remove(panelCitaHoySeleccionado.obtenerHoraCita());
            
            if(mapaCitas.size()>0)
                Utilidades.guardarEnArchivo(Directorios.CITAS+LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd"))+".dda", mapaCitas);
            else
                Utilidades.eliminarArchivo(Directorios.CITAS+LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd"))+".dda");
            
            llenarBoxPacientes();
        }
    }
    
    
    //............................
    
    public static void mostrarSoporte() {
        
        Utilidades.abrirVinculo("http://www.abanicosystems.com/soporte");
    }
    
    public static void mostrarPanelAcercaDe() {
        
        PanelAcercaDe panel = new PanelAcercaDe();
        panel.setVisible(true);
    }
    
    
    public static void mostrarAyuda() {
        
        Utilidades.abrirVinculo("http://www.abanicosystems.com/preguntas-frecuentes");
    }
    
    
    //.............................
    
    private void mostrarPanelParaExportarHistoria() {
        
        String[] listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        if(listaHistorias.length==0)
        {
            HJDialog.mostrarMensaje("No hay Historias", new String[] {"No hay historias registradas."}, IconoDeImagen.ADVERTENCIA, null);
            
            return;
        }
        
        
        HJWaitingPanel panelEspera = new HJWaitingPanel();
        
        Thread hiloPanelBuscar = new Thread( () -> {
            
            PanelBuscarPaciente panelBuscarPaciente = new PanelBuscarPaciente(true);
            
            panelEspera.setVisible(false);
            panelEspera.dispose();
            
            panelBuscarPaciente.setVisible(true);
            
            if(panelBuscarPaciente.estaPacienteSeleccionado())
                exportarHistoria(panelBuscarPaciente.obtenerNroHistoriaSeleccionado());
        });
        hiloPanelBuscar.start();
        
        panelEspera.setVisible(true);
    }
    
    
    private void exportarHistoria(int numeroHistoria) {
        
        HJWaitingPanel panelEspera = new HJWaitingPanel();
        
        Thread hiloCrearRespaldoHistoria = new Thread( () -> {
            
            RespaldoHistoria respaldoHistoria = Procedimientos.crearRespaldoHistoria(numeroHistoria);
            
            panelEspera.setVisible(false);
            panelEspera.dispose();
            
            HJFileChooser selectorArchivo = new HJFileChooser(null, HJFileChooser.HISTORIA_DOC_ASSISTANT);
            
            if(selectorArchivo.showSaveDialog(this)==HJFileChooser.APPROVE_OPTION)
            {
                Utilidades.guardarEnArchivo(selectorArchivo.getSelectedFile().getPath()+".hda", respaldoHistoria);
                
                HJDialog.mostrarMensaje("Historia Guardada", new String[] {"La historia se guardó exitosamente."}, IconoDeImagen.INFORMACION, null);
            }
        });
        hiloCrearRespaldoHistoria.start();
        
        panelEspera.setVisible(true);
    }
    
    
    private void mostrarDialogoImportarHistoria() {
        
        HJFileChooser selectorArchivo = new HJFileChooser(null, HJFileChooser.HISTORIA_DOC_ASSISTANT);
        
        if(selectorArchivo.showOpenDialog(this)==HJFileChooser.APPROVE_OPTION)
        {
            RespaldoHistoria respaldo = (RespaldoHistoria)Utilidades.leerArchivo(selectorArchivo.getSelectedFile().getPath());
            
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloCrearRespaldoHistoria = new Thread( () -> {
                
                Procedimientos.importarHistoria(Procedimientos.obtenerNroHistoria(), respaldo) ;
                
                panelEspera.setVisible(false);
                panelEspera.dispose();
                
                HJDialog.mostrarMensaje("Historia Incorporada", new String[] {"La historia se incorporó exitosamente."}, IconoDeImagen.INFORMACION, null);
            });
            hiloCrearRespaldoHistoria.start();
            
            panelEspera.setVisible(true);
        }
    }
    
    
    private void mostrarDialogoExportarTodo() {
        
        String[] listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        if(listaHistorias.length==0)
        {
            HJDialog.mostrarMensaje("No hay Historias", new String[] {"No hay historias registradas."}, IconoDeImagen.ADVERTENCIA, null);
            
            return;
        }
        
        
        if(HJDialog.mostrarDialogoPregunta("Aviso", new String[] {"Este procedimiento puede tomar varios minutos, dependiendo de la cantidad de", "historias registradas y de consultas asociadas a ellas.", " ", "¿Desea continuar?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.PREGUNTA, null)==0)
        {
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloCrearRespaldoTotal = new Thread( () -> {
                
                RespaldoTotal respaldoTotal = Procedimientos.crearRespaldoTotal();
                
                panelEspera.setVisible(false);
                panelEspera.dispose();
                
                HJFileChooser selectorArchivo = new HJFileChooser(null, HJFileChooser.RESPALDO_TOTAL_DOC_ASSISTANT);
                
                if(selectorArchivo.showSaveDialog(this)==HJFileChooser.APPROVE_OPTION)
                {
                    Utilidades.guardarEnArchivo(selectorArchivo.getSelectedFile().getPath()+".tda", respaldoTotal);
                    
                    HJDialog.mostrarMensaje("Respaldo Guardado", new String[] {"El respaldo se guardó exitosamente."}, IconoDeImagen.INFORMACION, null);
                }
            });
            hiloCrearRespaldoTotal.start();
            
            panelEspera.setVisible(true);
        }
    }
    
    
    private void mostrarDialogoImportarTodo() {
        
        if(HJDialog.mostrarDialogoPregunta("Advertencia", new String[] {"Al realizar este procedimiento se eliminará toda la información actual relacionada a las historias", "y se reemplazará con la información del nuevo archivo.", " ", "¿Desea continuar?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==1)
            return;
        
        if(HJDialog.mostrarDialogoPregunta("Aviso", new String[] {"Este procedimiento puede tomar varios minutos, dependiendo de la cantidad de historias", "y de consultas asociadas a ellas que estén en el archivo de respaldo.", " ", "¿Desea continuar?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.PREGUNTA, null)==1)
            return;
        
        
        HJFileChooser selectorArchivo = new HJFileChooser(null, HJFileChooser.RESPALDO_TOTAL_DOC_ASSISTANT);
        
        if(selectorArchivo.showOpenDialog(this)==HJFileChooser.APPROVE_OPTION)
        {
            RespaldoTotal respaldoTotal = (RespaldoTotal)Utilidades.leerArchivo(selectorArchivo.getSelectedFile().getPath());
            
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloImportarTodo = new Thread( () -> {
                
                Procedimientos.importarTodo(respaldoTotal);
                
                panelEspera.setVisible(false);
                panelEspera.dispose();
                
                HJDialog.mostrarMensaje("Operación Exitosa", new String[] {"La nueva información fue establecida exitosamente."}, IconoDeImagen.INFORMACION, null);
            });
            hiloImportarTodo.start();
            
            panelEspera.setVisible(true);
        }
    }
    
    
    //.............................
    
    private void mostrarPanelAdquirirLicencia() {
        
        PanelAdquirirLicencia panel = new PanelAdquirirLicencia();
        panel.setVisible(true);
        
        establecerLicenciaActivada(Registro.estaProductoActivado());
    }
    
    
    private void mostrarPanelActivarProducto() {
        
        PanelActivacion panel = new PanelActivacion();
        panel.setVisible(true);
        
        establecerLicenciaActivada(Registro.estaProductoActivado());
    }
    
    
    //.............................
    
    private void mostrarPanelNuevoPaciente() {
        
        PanelNuevoPaciente panel = new PanelNuevoPaciente();
        panel.setVisible(true);
    }
    
    
    private void mostrarPanelBuscarPaciente() {
        
        HJWaitingPanel panelEspera = new HJWaitingPanel();
        
        Thread hiloPanelBuscar = new Thread( () -> {
            
            PanelBuscarPaciente panelBuscarPaciente = new PanelBuscarPaciente(false);
            
            panelEspera.setVisible(false);
            panelEspera.dispose();
            
            panelBuscarPaciente.setVisible(true);
            
            llenarBoxPacientes();
        });
        hiloPanelBuscar.start();
        
        panelEspera.setVisible(true);
    }
    
    
    private void mostrarPanelAnotarCita() {
        
        int seleccion = HJDialog.mostrarDialogoPregunta("Tipo de Paciente", new String[] {"Seleccione el tipo de paciente:"}, new HJButton[] {new HJButton(new IconoDeImagen("Lista.png", -1, 20), "Paciente Registrado", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Paciente.png", -1, 20), "Paciente Nuevo", Colores.COLORES_BOTONES), new HJButton(null, "Cerrar", Colores.COLORES_BOTONES)}, 2, null, null);
        
        if(seleccion==0)
        {
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloPanelBuscar = new Thread( () -> {
                
                PanelBuscarPaciente panelBuscarPaciente = new PanelBuscarPaciente(true);
                
                panelEspera.setVisible(false);
                panelEspera.dispose();
                
                panelBuscarPaciente.setVisible(true);
                
                if(panelBuscarPaciente.estaPacienteSeleccionado())
                {
                    PanelCalendario panelCalendario = new PanelCalendario(new String[] {""+panelBuscarPaciente.obtenerNroHistoriaSeleccionado()});
                    panelCalendario.setVisible(true);
                }
            });
            hiloPanelBuscar.start();
            
            panelEspera.setVisible(true);
        }
        
        if(seleccion==1)
        {
            DialogoPacienteNuevo dialogoPacienteNuevo = new DialogoPacienteNuevo();
            dialogoPacienteNuevo.setVisible(true);
            
            String[] cadenaNombresApellidos = dialogoPacienteNuevo.obtenerNombres_Y_Apellidos();
            
            if(cadenaNombresApellidos!=null)
            {
                PanelCalendario panelCalendario = new PanelCalendario(cadenaNombresApellidos);
                panelCalendario.setVisible(true);
            }
        }
    }
    
    
    private void mostrarPanelPersonalizarRecipes() {
        
        PanelPersonalizacionRecipes panel = new PanelPersonalizacionRecipes();
        panel.setVisible(true);
    }
    
    
    private void mostrarPanelRecipeRapido() {
        
        int seleccion = HJDialog.mostrarDialogoPregunta("Tipo de Paciente", new String[] {"Seleccione el tipo de paciente:"}, new HJButton[] {new HJButton(new IconoDeImagen("Lista.png", -1, 20), "Paciente Registrado", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Paciente.png", -1, 20), "Otro Paciente", Colores.COLORES_BOTONES), new HJButton(null, "Cerrar", Colores.COLORES_BOTONES)}, 2, IconoDeImagen.INFORMACION, null);
        
        if(seleccion==0)
        {
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloPanelBuscar = new Thread( () -> {
                
                PanelBuscarPaciente panelBuscarPaciente = new PanelBuscarPaciente(true);
                
                panelEspera.setVisible(false);
                panelEspera.dispose();
                
                panelBuscarPaciente.setVisible(true);
                
                if(panelBuscarPaciente.estaPacienteSeleccionado())
                {
                    PanelImpresionRecipes panelImpresionRecipes = new PanelImpresionRecipes(panelBuscarPaciente.obtenerNroHistoriaSeleccionado(), false);
                    
                    Thread hiloLlenar = new Thread( () -> {
                        
                        while(panelImpresionRecipes.isVisible()==false)
                        {
                            Utilidades.esperar(20);
                        }
                        
                        panelImpresionRecipes.colocarDocumento(Recipe.RECIPE_RP, true);
                    });
                    hiloLlenar.start();
                    
                    panelImpresionRecipes.setVisible(true);
                }
            });
            hiloPanelBuscar.start();
            
            panelEspera.setVisible(true);
        }
        
        if(seleccion==1)
        {
            PanelImpresionRecipes panelImpresionRecipes = new PanelImpresionRecipes(-1, false);
            
            Thread hiloLlenar = new Thread( () -> {
            
                while(panelImpresionRecipes.isVisible()==false)
                {
                    Utilidades.esperar(20);
                }
                
                panelImpresionRecipes.colocarDocumento(Recipe.RECIPE_RP, true);
            });
            hiloLlenar.start();
            
            panelImpresionRecipes.setVisible(true);
        }
    }
    
    
    private void mostrarPanelEstadisticas() {
        
        if((new File(Directorios.DATOS_PERSONALES)).list().length==0)
        {
            HJDialog.mostrarMensaje("No Hay Pacientes", new String[] {"Debe haber al menos un paciente registrado para poder acceder a la ventana de estadísticas."}, IconoDeImagen.INFORMACION, null);
            
            return;
        }
        
        PanelEstadisticas panel = new PanelEstadisticas();
        panel.setVisible(true);
    }
    
    
    //................................
    
    private void mostrarPanelCalendario() {
        
        PanelCalendario panel = new PanelCalendario(null);
        panel.setVisible(true);
    }
    
    
    private void mostrarConfiguracionActualizaciones() {
        
        try{
            Runtime.getRuntime().exec("Updater.exe /configure");
        }catch(IOException ioe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error en Actualizador", new String[] {"Ocurrió un error con el subprograma de actualización."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
    public static void comprobarActualizaciones() {
        
        try{
            Runtime.getRuntime().exec("Updater.exe /checknow");
        }catch(IOException ioe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error en Actualizaciones", new String[] {"Ocurrió un error al comprobar actualizaciones."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
    //.............................
    
    private void mostrarPanelDatosDelDoctor() {
        
        PanelDatosDoctor panel = new PanelDatosDoctor(false);
        
        MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        panel.establecerTituloDr(mapaDatos.get(PanelDatosDoctor.TITULO));
        panel.establecerNombreCompleto(mapaDatos.get(PanelDatosDoctor.NOMBRE_COMPLETO));
        panel.establecerEspecialidad(mapaDatos.get(PanelDatosDoctor.ESPECIALIDAD));
        
        panel.setVisible(true);
        
        if(panel.fueronDatosEstablecidos())
        {
            mapaDatos.put(PanelDatosDoctor.TITULO, panel.obtenerTituloDr());
            mapaDatos.put(PanelDatosDoctor.NOMBRE_COMPLETO, panel.obtenerNombreCompleto());
            mapaDatos.put(PanelDatosDoctor.ESPECIALIDAD, panel.obtenerEspecialidad());
            
            Utilidades.guardarEnArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda", mapaDatos);
            
            PanelDatosDoctor.mostrarMensajeDatosGuardados();
        }
        
        colocarDatosDoctor();
    }
    
    
    //.............................
    
    private Shape obtenerFormaDeMarco() {
        
        Area area = new Area(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        
        area.add(new Area(new Rectangle2D.Double(0, getHeight()-50, getWidth(), getHeight())));
        
        return area;
    }
    
    
    @Override
    public void paint(Graphics g) {
        
        super.paint(g);
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        BasicStroke pinceladaBasica = new BasicStroke((float)1.0);
        
        g2D.setStroke(pinceladaBasica);
        
        g2D.fill(pinceladaBasica.createStrokedShape(obtenerFormaDeMarco()));
        
        g2D.setStroke(new BasicStroke((float)0.5, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
        
        g2D.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
        
        g2D.dispose();
    }
    
    
}
