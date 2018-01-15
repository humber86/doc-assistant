/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Recipe extends JPanel {
    
    private int nroHistoria = -1;
    
    private final int tipoRecipe;
    public static final int RECIPE_MUESTRA = 0;
    public static final int RECIPE_RP = 1;
    public static final int RECIPE_INDICACIONES = 2;
    public static final int RECIPE_EXAMENES_LABORATORIO = 3;
    public static final int RECIPE_ESTUDIOS_RADIOLOGICOS = 4;
    public static final int RECIPE_INFORME = 5;
    public static final int RECIPE_REFERENCIA = 6;
    
    private String nombreLogo;
    
    private String familyNombreCompleto;
    private int styleNombreCompleto;
    private int sizeNombreCompleto;
    private String familyEspecialidad;
    private int styleEspecialidad;
    private int sizeEspecialidad;
    private String linea_info_1;
    private String linea_info_2;
    private String linea_info_3;
    private String linea_info_4;
    private int redEncabezado;
    private int greenEncabezado;
    private int blueEncabezado;
    private boolean conLineaDivisoria;
    
    private String familyContenido;
    private int styleContenido;
    private int sizeContenido;
    private int redContenido;
    private int greenContenido;
    private int blueContenido;
    private boolean conMarco;
    private boolean esPediatrico;
    
    private boolean desdeConsulta = false;
    
    private JTextField tTalla;
    private HJLabel lTalla;
    private final String rellenoTalla = "   ";
    private JTextField tPeso;
    private HJLabel lPeso;
    private final String rellenoPeso = "   ";
    private JTextField tCC;
    private HJLabel lCC;
    private final String rellenoCC = "   ";
    
    private JTextArea taContenido;
    
    private JTextField tPaciente;
    private HJLabel lPaciente;
    private JPanel panelPaciente;
    
    private JTextField tCedula;
    private HJLabel lCedula;
    private JPanel panelCedula;
    
    private JTextField tFecha;
    private HJLabel lFecha;
    private JPanel panelFecha;
    
    private Color colorEncabezado;
    
    private JPanel panelRecipe;
    
    private int alturaAreaContenido = 0;
    private boolean esPrimerCambio = true;
    private boolean seNecesitaOtroRecipe = false;
    
    
    public Recipe(int numeroHistoria, int tipo, boolean desdeConsulta) {
        
        establecerParametrosPrincipales();
        
        
        nroHistoria = numeroHistoria;
        
        tipoRecipe = tipo;
        
        this.desdeConsulta = desdeConsulta;
        
        MapaDatos mapaConfiguracion = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"ConfigRecipes.dda");
        
        llenarRecipe(mapaConfiguracion);
        
    }
    
    
    public Recipe(MapaDatos mapaConfiguracion) {
        
        establecerParametrosPrincipales();
        
        
        tipoRecipe = RECIPE_MUESTRA;
        
        llenarRecipe(mapaConfiguracion);
        
    }
    
    
    private void establecerParametrosPrincipales() {
        
        setLayout(new GridLayout(1, 1, 0, 0));
        
        setBackground(Colores.BLANCO);
        
        setPreferredSize(new Dimension(396, 612));
        
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1), BorderFactory.createEmptyBorder(25, 10, 25, 10)));
        
    }
    
    
    private void llenarRecipe(MapaDatos mapaDatos) {
        
        nombreLogo = mapaDatos.get(PanelPersonalizacionRecipes.NOMBRE_LOGO);
        
        familyNombreCompleto = mapaDatos.get(PanelPersonalizacionRecipes.FAMILY_NOMBRE_COMPLETO);
        styleNombreCompleto = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.STYLE_NOMBRE_COMPLETO));
        sizeNombreCompleto = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.SIZE_NOMBRE_COMPLETO));
        familyEspecialidad = mapaDatos.get(PanelPersonalizacionRecipes.FAMILY_ESPECIALIDAD);
        styleEspecialidad = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.STYLE_ESPECIALIDAD));
        sizeEspecialidad = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.SIZE_ESPECIALIDAD));
        linea_info_1 = mapaDatos.get(PanelPersonalizacionRecipes.LINEA_INFO_1);
        linea_info_2 = mapaDatos.get(PanelPersonalizacionRecipes.LINEA_INFO_2);
        linea_info_3 = mapaDatos.get(PanelPersonalizacionRecipes.LINEA_INFO_3);
        linea_info_4 = mapaDatos.get(PanelPersonalizacionRecipes.LINEA_INFO_4);
        redEncabezado = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.RED_ENCABEZADO));
        greenEncabezado = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.GREEN_ENCABEZADO));
        blueEncabezado = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.BLUE_ENCABEZADO));
        conLineaDivisoria = Boolean.valueOf(mapaDatos.get(PanelPersonalizacionRecipes.CON_LINEA_DIVISORIA));
        
        familyContenido = mapaDatos.get(PanelPersonalizacionRecipes.FAMILY_CONTENIDO);
        styleContenido = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.STYLE_CONTENIDO));
        sizeContenido = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.SIZE_CONTENIDO));
        redContenido = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.RED_CONTENIDO));
        greenContenido = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.GREEN_CONTENIDO));
        blueContenido = Integer.parseInt(mapaDatos.get(PanelPersonalizacionRecipes.BLUE_CONTENIDO));
        conMarco = Boolean.valueOf(mapaDatos.get(PanelPersonalizacionRecipes.CON_MARCO));
        esPediatrico = Boolean.valueOf(mapaDatos.get(PanelPersonalizacionRecipes.ES_PEDIATRICO));
        
        
        //....
        
        HJLabel lLogo = new HJLabel(new ImageIcon((new ImageIcon(Directorios.RECURSOS+nombreLogo)).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
        
        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLogo.add(lLogo);
        panelLogo.setOpaque(false);
        
        Box boxLogo = Box.createVerticalBox();
        boxLogo.add(panelLogo);
        boxLogo.add(Box.createVerticalStrut(5));
        
        
        //....
        
        colorEncabezado = new Color(redEncabezado, greenEncabezado, blueEncabezado);
        
        
        MapaDatos mapaDatosDoctor = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        HJLabel lNombreCompleto = new HJLabel(mapaDatosDoctor.get(PanelDatosDoctor.TITULO)+" "+mapaDatosDoctor.get(PanelDatosDoctor.NOMBRE_COMPLETO));
        if(Utilidades.estaDisponibleFamilia(familyNombreCompleto))
            lNombreCompleto.setFont(new Font(familyNombreCompleto, styleNombreCompleto, sizeNombreCompleto));
        else
            lNombreCompleto.setFont(new Font(DocAssistant.TIMES_NEW_ROMAN, styleNombreCompleto, sizeNombreCompleto));
        lNombreCompleto.setForeground(colorEncabezado);
        lNombreCompleto.setPreferredSize(new Dimension(getFontMetrics(lNombreCompleto.getFont()).stringWidth(lNombreCompleto.getText())+8, lNombreCompleto.getFont().getSize()));
        lNombreCompleto.setHorizontalAlignment(HJLabel.CENTER);
        
        JPanel panelNombreCompleto = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 2));
        panelNombreCompleto.add(lNombreCompleto);
        panelNombreCompleto.setMaximumSize(new Dimension(396, lNombreCompleto.getFont().getSize()+4));
        panelNombreCompleto.setOpaque(false);
        
        if(conLineaDivisoria)  panelNombreCompleto.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorEncabezado));
        
        HJLabel lEspecialidad = new HJLabel(mapaDatosDoctor.get(PanelDatosDoctor.ESPECIALIDAD));
        if(Utilidades.estaDisponibleFamilia(familyEspecialidad))
            lEspecialidad.setFont(new Font(familyEspecialidad, styleEspecialidad, sizeEspecialidad));
        else
            lEspecialidad.setFont(new Font(DocAssistant.ARIAL, styleEspecialidad, sizeEspecialidad));
        lEspecialidad.setForeground(colorEncabezado);
        lEspecialidad.setPreferredSize(new Dimension(getFontMetrics(lEspecialidad.getFont()).stringWidth(lEspecialidad.getText())+8, lEspecialidad.getFont().getSize()));
        lEspecialidad.setHorizontalAlignment(HJLabel.CENTER);
        
        JPanel panelEspecialidad = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelEspecialidad.add(lEspecialidad);
        panelEspecialidad.setOpaque(false);
        
        
        Font formatoLineas = new Font("Times New Roman", Font.PLAIN, 10);
        
        HJLabel lLinea_1 = new HJLabel();
        lLinea_1.setFont(formatoLineas);
        lLinea_1.setForeground(colorEncabezado);
        if(linea_info_1.isEmpty()==false)  lLinea_1.setText(linea_info_1);
        
        JPanel panelLinea1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea1.add(lLinea_1);
        panelLinea1.setOpaque(false);
        
        HJLabel lLinea_2 = new HJLabel();
        lLinea_2.setFont(formatoLineas);
        lLinea_2.setForeground(colorEncabezado);
        if(linea_info_2.isEmpty()==false)  lLinea_2.setText(linea_info_2);
        
        JPanel panelLinea2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea2.add(lLinea_2);
        panelLinea2.setOpaque(false);
        
        HJLabel lLinea_3 = new HJLabel();
        lLinea_3.setFont(formatoLineas);
        lLinea_3.setForeground(colorEncabezado);
        if(linea_info_3.isEmpty()==false)  lLinea_3.setText(linea_info_3);
        
        JPanel panelLinea3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea3.add(lLinea_3);
        panelLinea3.setOpaque(false);
        
        HJLabel lLinea_4 = new HJLabel();
        lLinea_4.setFont(formatoLineas);
        lLinea_4.setForeground(colorEncabezado);
        if(linea_info_4.isEmpty()==false)  lLinea_4.setText(linea_info_4);
        
        JPanel panelLinea4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLinea4.add(lLinea_4);
        panelLinea4.setOpaque(false);
        
        Box boxLineas = Box.createVerticalBox();
        boxLineas.add(panelNombreCompleto);
        boxLineas.add(Box.createVerticalStrut(3));
        boxLineas.add(panelEspecialidad);
        boxLineas.add(Box.createVerticalStrut(3));
        boxLineas.add(panelLinea1);
        boxLineas.add(panelLinea2);
        boxLineas.add(panelLinea3);
        boxLineas.add(panelLinea4);
        boxLineas.add(Box.createVerticalStrut(5));
        
        
        //....
        
        Box boxSuperior = Box.createHorizontalBox();
        boxSuperior.add(boxLogo);
        boxSuperior.add(Box.createHorizontalStrut(10));
        boxSuperior.add(boxLineas);
        
        
        //.........................
        
        MapaDatos mapaPaciente = null;
        if(nroHistoria>0)  mapaPaciente = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+nroHistoria+".dda");
        
        
        HJLabel etiquetaTalla = new HJLabel("Talla: ");
        etiquetaTalla.setFont(new Font("Arial", Font.PLAIN, 12));
        etiquetaTalla.setForeground(colorEncabezado);
        
        tTalla = new JTextField(3);
        tTalla.setForeground(colorEncabezado);
        
        lTalla = new HJLabel();
        lTalla.setFont(new Font("Arial", Font.PLAIN, 12));
        lTalla.setForeground(colorEncabezado);
        
        HJLabel etiquetaPeso = new HJLabel("Peso: ");
        etiquetaPeso.setFont(new Font("Arial", Font.PLAIN, 12));
        etiquetaPeso.setForeground(colorEncabezado);
        
        tPeso = new JTextField(3);
        tPeso.setForeground(colorEncabezado);
        
        lPeso = new HJLabel(rellenoPeso);
        lPeso.setFont(new Font("Arial", Font.PLAIN, 12));
        lPeso.setForeground(colorEncabezado);
        
        HJLabel etiquetaCC = new HJLabel("C.C.: ");
        etiquetaCC.setFont(new Font("Arial", Font.PLAIN, 12));
        etiquetaCC.setForeground(colorEncabezado);
        
        tCC = new JTextField(3);
        tCC.setForeground(colorEncabezado);
        
        lCC = new HJLabel(rellenoCC);
        lCC.setFont(new Font("Arial", Font.PLAIN, 12));
        lCC.setForeground(colorEncabezado);
        
        if(desdeConsulta)
        {
            MapaDatos mapaValoresUltConsulta = (MapaDatos)Utilidades.leerArchivo(Utilidades.obtenerPathUltimoArchivo(Directorios.CONSULTAS+nroHistoria));
            
            tTalla.setText(mapaValoresUltConsulta.get(Consulta.KEY_TALLA));
            lTalla.setText(mapaValoresUltConsulta.get(Consulta.KEY_TALLA));
            
            tPeso.setText(mapaValoresUltConsulta.get(Consulta.KEY_PESO));
            lPeso.setText(mapaValoresUltConsulta.get(Consulta.KEY_PESO));
            
            tCC.setText(mapaValoresUltConsulta.get(Consulta.KEY_CC));
            lCC.setText(mapaValoresUltConsulta.get(Consulta.KEY_CC));
        }
        
        JPanel panelTallaPesoCC = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 15));
        panelTallaPesoCC.add(etiquetaTalla);
        panelTallaPesoCC.add(tTalla);
        panelTallaPesoCC.add(lTalla);
        panelTallaPesoCC.add(Box.createHorizontalStrut(10));
        panelTallaPesoCC.add(etiquetaPeso);
        panelTallaPesoCC.add(tPeso);
        panelTallaPesoCC.add(lPeso);
        panelTallaPesoCC.add(Box.createHorizontalStrut(10));
        panelTallaPesoCC.add(etiquetaCC);
        panelTallaPesoCC.add(tCC);
        panelTallaPesoCC.add(lCC);
        panelTallaPesoCC.add(Box.createHorizontalStrut(20));
        panelTallaPesoCC.setOpaque(false);
        
        
        Box boxCabecera = Box.createHorizontalBox();
        
        if(tipoRecipe==RECIPE_MUESTRA || tipoRecipe==RECIPE_RP || tipoRecipe==RECIPE_INDICACIONES)
        {
            HJLabel lRPIndicaciones = new HJLabel();
            lRPIndicaciones.setForeground(colorEncabezado);
            if(tipoRecipe==RECIPE_INDICACIONES)  lRPIndicaciones.setText("Indicaciones:");
            else  lRPIndicaciones.setText("Rp.");
            
            JPanel panelRPIndicaciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 15));
            panelRPIndicaciones.add(Box.createHorizontalStrut(20));
            panelRPIndicaciones.add(lRPIndicaciones);
            panelRPIndicaciones.setOpaque(false);
            
            boxCabecera.add(panelRPIndicaciones);
            
            
            if(esPediatrico && tipoRecipe==RECIPE_INDICACIONES)  boxCabecera.add(panelTallaPesoCC);
            
        }else{
            
            HJLabel lTitulo = new HJLabel();
            lTitulo.setForeground(colorEncabezado);
            
            if(tipoRecipe==RECIPE_EXAMENES_LABORATORIO)  lTitulo.setText("Exámenes de Laboratorio");
            
            if(tipoRecipe==RECIPE_ESTUDIOS_RADIOLOGICOS)  lTitulo.setText("Estudios Radiológicos");
            
            if(tipoRecipe==RECIPE_INFORME)  lTitulo.setText("Informe");
            
            if(tipoRecipe==RECIPE_REFERENCIA)  lTitulo.setText("Referencia");
            
            JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
            panelTitulo.add(lTitulo);
            panelTitulo.setOpaque(false);
            
            boxCabecera.add(panelTitulo);
        }
        
        
        taContenido = new JTextArea(0, 0);
        if(Utilidades.estaDisponibleFamilia(familyContenido))
            taContenido.setFont(new Font(familyContenido, styleContenido, sizeContenido));
        else
            taContenido.setFont(new Font(DocAssistant.ARIAL, styleContenido, sizeContenido));
        taContenido.setForeground(new Color(redContenido, greenContenido, blueContenido));
        taContenido.setLineWrap(true);
        taContenido.setWrapStyleWord(true);
        taContenido.setOpaque(false);
        
        taContenido.addComponentListener( new ComponentAdapter() {
            
            @Override
            public void componentResized(ComponentEvent ce) {
                
                if(esPrimerCambio)
                {
                    alturaAreaContenido = taContenido.getSize().height;
                    esPrimerCambio = false;
                    
                }else{
                    
                    seNecesitaOtroRecipe = taContenido.getSize().height>alturaAreaContenido;
                }
            }
        });
        
        Box boxContenidoVertical = Box.createVerticalBox();
        boxContenidoVertical.add(taContenido);
        boxContenidoVertical.add(Box.createVerticalStrut(5));
        
        Box boxContenido = Box.createHorizontalBox();
        boxContenido.add(Box.createHorizontalStrut(20));
        boxContenido.add(boxContenidoVertical);
        boxContenido.add(Box.createHorizontalStrut(20));
        
        
        Font formatoPanelInferior = new Font("Arial", Font.PLAIN, 12);
        
        HJLabel lFirmaDelMedico = new HJLabel("Firma del Médico");
        lFirmaDelMedico.setFont(formatoPanelInferior);
        lFirmaDelMedico.setForeground(colorEncabezado);
        
        JPanel panelFirmaDelMedico = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 3));
        panelFirmaDelMedico.add(lFirmaDelMedico);
        panelFirmaDelMedico.setOpaque(false);
        panelFirmaDelMedico.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, colorEncabezado));
        
        JPanel panelFirmaDelMedicoMacro = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        panelFirmaDelMedicoMacro.add(panelFirmaDelMedico);
        panelFirmaDelMedicoMacro.setOpaque(false);
        
        Box boxFirmaDelMedico = Box.createVerticalBox();
        boxFirmaDelMedico.add(Box.createVerticalStrut(20));
        boxFirmaDelMedico.add(panelFirmaDelMedicoMacro);
        
        
        //....
        
        PanelCentral panelCentral = new PanelCentral(new BorderLayout(0, 0), conMarco);
        panelCentral.add(boxCabecera, BorderLayout.NORTH);
        panelCentral.add(boxContenido, BorderLayout.CENTER);
        panelCentral.add(boxFirmaDelMedico, BorderLayout.SOUTH);
        panelCentral.setOpaque(false);
        
        
        //.........................
        
        HJLabel etiquetaPaciente = new HJLabel("Paciente: ");
        etiquetaPaciente.setFont(formatoPanelInferior);
        etiquetaPaciente.setForeground(colorEncabezado);
        
        tPaciente = new JTextField(25);
        tPaciente.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        tPaciente.setForeground(colorEncabezado);
        if(mapaPaciente!=null)  tPaciente.setText(mapaPaciente.get(Personales.KEY_NOMBRES)+" "+mapaPaciente.get(Personales.KEY_APELLIDOS));
        
        lPaciente = new HJLabel("Nombres y Apellidos");
        lPaciente.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        lPaciente.setForeground(colorEncabezado);
        
        panelPaciente = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelPaciente.add(lPaciente);
        panelPaciente.setMaximumSize(new Dimension(getFontMetrics(lPaciente.getFont()).stringWidth(lPaciente.getText())+20, lPaciente.getFont().getSize()+6));
        panelPaciente.setOpaque(false);
        panelPaciente.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorEncabezado));
        
        Box boxPacienteMacro = Box.createHorizontalBox();
        boxPacienteMacro.add(etiquetaPaciente);
        boxPacienteMacro.add(tPaciente);
        boxPacienteMacro.add(panelPaciente);
        boxPacienteMacro.add(Box.createHorizontalGlue());
        
        
        HJLabel etiquetaCedula = new HJLabel("Cédula: ");
        etiquetaCedula.setFont(formatoPanelInferior);
        etiquetaCedula.setForeground(colorEncabezado);
        
        tCedula = new JTextField(12);
        tCedula.setFont(new Font("Times New Roman", Font.ITALIC, 12));
        tCedula.setForeground(colorEncabezado);
        if(mapaPaciente!=null)  tCedula.setText(mapaPaciente.get(Personales.KEY_CEDULA));
        
        lCedula = new HJLabel("7.654.321");
        lCedula.setFont(new Font("Times New Roman", Font.ITALIC, 12));
        lCedula.setForeground(colorEncabezado);
        
        panelCedula = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelCedula.add(lCedula);
        panelCedula.setOpaque(false);
        panelCedula.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorEncabezado));
        
        JPanel panelCedulaMacro = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelCedulaMacro.add(etiquetaCedula);
        panelCedulaMacro.add(tCedula);
        panelCedulaMacro.add(panelCedula);
        panelCedulaMacro.setOpaque(false);
        
        
        HJLabel etiquetaFecha = new HJLabel();
        if(tipoRecipe==RECIPE_INDICACIONES)  etiquetaFecha.setText("Próxima cita: ");
        else  etiquetaFecha.setText("Fecha: ");
        etiquetaFecha.setFont(formatoPanelInferior);
        etiquetaFecha.setForeground(colorEncabezado);
        
        tFecha = new JTextField(12);
        tFecha.setFont(formatoPanelInferior);
        tFecha.setForeground(colorEncabezado);
        if(tipoRecipe==RECIPE_INDICACIONES)  tFecha.setText(obtenerProximaCita());
        else  tFecha.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
        
        lFecha = new HJLabel("31-12-2010");
        lFecha.setFont(formatoPanelInferior);
        lFecha.setForeground(colorEncabezado);
        
        panelFecha = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelFecha.add(lFecha);
        panelFecha.setOpaque(false);
        panelFecha.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorEncabezado));
        
        JPanel panelFechaMacro = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelFechaMacro.add(etiquetaFecha);
        panelFechaMacro.add(tFecha);
        panelFechaMacro.add(panelFecha);
        panelFechaMacro.setOpaque(false);
        
        
        Box boxCedulaFecha = Box.createHorizontalBox();
        boxCedulaFecha.add(panelCedulaMacro);
        boxCedulaFecha.add(panelFechaMacro);
        
        
        //....
        
        Box boxInferior = Box.createVerticalBox();
        boxInferior.add(Box.createVerticalStrut(10));
        boxInferior.add(boxPacienteMacro);
        boxInferior.add(Box.createVerticalStrut(10));
        boxInferior.add(boxCedulaFecha);
        
        
        //..........................
        
        if(tipoRecipe==RECIPE_MUESTRA)
        {
            tTalla.setVisible(false);
            tPeso.setVisible(false);
            tCC.setVisible(false);
            
            tPaciente.setVisible(false);
            tCedula.setVisible(false);
            tFecha.setVisible(false);
            
        }else{
            
            establecerEditable(false);
        }
        
        
        //..........................
        
        
        JPanel panelGeneral = new JPanel(new BorderLayout(0, 0));
        panelGeneral.add(boxSuperior, BorderLayout.NORTH);
        panelGeneral.add(panelCentral, BorderLayout.CENTER);
        panelGeneral.add(boxInferior, BorderLayout.SOUTH);
        panelGeneral.setOpaque(false);
        
        
        panelRecipe = new JPanel(new GridLayout(1, 1, 0, 0));
        panelRecipe.add(panelGeneral);
        panelRecipe.setOpaque(false);
        
        
        add(panelRecipe);
    }
    
    
    private String obtenerProximaCita() {
        
        String[] cadenaArchivos = (new File(Directorios.CITAS)).list();
        
        for(int i=0 ; i<=cadenaArchivos.length-1 ; i++)
        {
            if(LocalDate.parse((cadenaArchivos[i].split(".dda"))[0], DateTimeFormatter.ofPattern("uuuu-MM-dd")).isEqual(LocalDate.now()))
                continue;
            
            HashMap<String,String> mapaDia = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.CITAS+cadenaArchivos[i]);
            
            if(mapaDia.containsValue(""+nroHistoria))
            {
                String[] cadenaFecha = (cadenaArchivos[i].split(".dda"))[0].split("-");
                
                return cadenaFecha[2]+"-"+cadenaFecha[1]+"-"+cadenaFecha[0];
            }
        }
        
        return null;
    }
    
    
    //....................................
    
    
    public ArrayList<String> llenar(ArrayList<String> arrayElementos, int espacios) {
        
        Utilidades.esperar(20);
        
        int ultimaPosicion;
        
        boolean esPrimeraVuelta = true;
        
        while(arrayElementos.size()>0)
        {
            ultimaPosicion = taContenido.getText().length();
            
            taContenido.append(arrayElementos.get(0));
            
            Utilidades.esperar(20);
            
            if(seNecesitaOtroRecipe)
            {
                taContenido.replaceRange("", ultimaPosicion, taContenido.getText().length());
                
                if(esPrimeraVuelta)
                {
                    Toolkit.getDefaultToolkit().beep();
                    HJDialog.mostrarMensaje("Texto Muy Largo.", new String[] {"El texto a colocar en uno de los récipes es demasiado largo."}, IconoDeImagen.ERROR, null);
                    
                    arrayElementos.remove(0);
                    
                    return arrayElementos;
                }
                
                break;
            }
            
            arrayElementos.remove(0);
            
            ultimaPosicion = taContenido.getText().length();
            
            taContenido.append("\n");
            
            Utilidades.esperar(20);
            
            if(seNecesitaOtroRecipe)
            {
                taContenido.replaceRange("", ultimaPosicion, taContenido.getText().length());
                
                break;
            }
            
            ultimaPosicion = taContenido.getText().length();
            
            String espaciosString = "";
            
            for(int i=0 ; i<=espacios-1 ; i++)
            {
                espaciosString += "\n";
            }
            
            taContenido.append(espaciosString);
            
            Utilidades.esperar(20);
            
            if(seNecesitaOtroRecipe)
            {
                taContenido.replaceRange("", ultimaPosicion, taContenido.getText().length());
                
                break;
            }
            
            esPrimeraVuelta = false;
        }
        
        return arrayElementos;
    }
    
    
    public void establecerEditable(boolean editable) {
        
        if(editable)
        {
            tTalla.setVisible(true);
            lTalla.setVisible(false);
            tPeso.setVisible(true);
            lPeso.setVisible(false);
            tCC.setVisible(true);
            lCC.setVisible(false);
            
            taContenido.setBorder(BorderFactory.createDashedBorder(Colores.LINEAS, (float)1.0, (float)15.0, (float)15.0, false));
            taContenido.setEditable(true);
            taContenido.requestFocus();
            taContenido.setCaretPosition(taContenido.getText().length());
            
            panelPaciente.setVisible(false);
            tPaciente.setVisible(true);
            
            panelCedula.setVisible(false);
            tCedula.setVisible(true);
            
            panelFecha.setVisible(false);
            tFecha.setVisible(true);
            
        }else{
            
            if(Utilidades.esTextoValido(tTalla.getText()))  lTalla.setText(tTalla.getText()+"cm.");
            else  lTalla.setText(rellenoTalla);
            tTalla.setVisible(false);
            lTalla.setVisible(true);
            
            if(Utilidades.esTextoValido(tPeso.getText()))  lPeso.setText(tPeso.getText()+"Kg.");
            else  lPeso.setText(rellenoPeso);
            tPeso.setVisible(false);
            lPeso.setVisible(true);
            
            if(Utilidades.esTextoValido(tCC.getText()))  lCC.setText(tCC.getText()+"cm.");
            else  lCC.setText(rellenoCC);
            tCC.setVisible(false);
            lCC.setVisible(true);
            
            taContenido.setBorder(null);
            taContenido.setEditable(false);
            
            tPaciente.setVisible(false);
            lPaciente.setText(tPaciente.getText());
            panelPaciente.setVisible(true);
            
            tCedula.setVisible(false);
            lCedula.setText(tCedula.getText());
            panelCedula.setVisible(true);
            
            tFecha.setVisible(false);
            lFecha.setText(tFecha.getText());
            panelFecha.setVisible(true);
        }
    }
    
    
    public void colocarCursor() {
        
        taContenido.requestFocus();
        
        taContenido.setCaretPosition(taContenido.getText().length());
    }
    
    
    public JPanel obtenerPanelParaImprimir() { return panelRecipe; }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class PanelCentral extends JPanel {
        
        private final boolean conBordesRedondeados;
        
        
        public PanelCentral(LayoutManager manejadorLayout, boolean conBordesRedondeados) {
            
            setLayout(manejadorLayout);
            
            
            this.conBordesRedondeados = conBordesRedondeados;
            
        }
        
        
        @Override
        public void paintBorder(Graphics g) {
            
            super.paintBorder(g);
            
            Graphics2D g2D = (Graphics2D)g.create();
            
            g2D.setColor(colorEncabezado);
            g2D.setStroke(new BasicStroke((float)1.0, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
            
            if(conBordesRedondeados)
            {
                g2D.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                
            }else{
                
                g2D.drawLine(0, 0, getWidth(), 0);
                
                g2D.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
            }
            
            g2D.dispose();
        }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
