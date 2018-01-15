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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;


public class PanelHistoria extends HJDialog {
    
    private final Font formatoTitulos = new Font("Eras Demi ITC", Font.BOLD, 22);
    
    private final Color colorAzulLlamativo = Colores.AZUL_LLAMATIVO;
    
    private final int nroHistoria;
    
    private final HJLabel lNombresApellidosCabecera;
    private final HJLabel lGeneroCabecera;
    private final HJLabel lCedulaCabecera;
    private final HJLabel lEdadCabecera;
    private final HJLabel lUltimaConsulta;
    private final HJLabel lProximaConsulta;
    
    private final JLabel lFoto;
    private final HJCustomizedButton bEliminarFoto;
    private boolean tieneFoto = false;
    
    private final String CONSULTA_NINGUNA = "(Ninguna)";
    private final String CONSULTA_NO_ESTABLECIDA = "(No establecida)";
    
    private final JPanel panelCentral;
    private final CardLayout layoutCarta = new CardLayout(0, 0);
    
    private final BoxDatosPersonales boxDatosPersonales;
    private final String DATOS_PERSONALES = "Datos Personales";
    private final BoxRepresentantes boxRepresentantes;
    private final String REPRESENTANTES = "Representante";
    private final BoxAntecedentes boxAntecedentes;
    private final String ANTECEDENTES = "Antecedentes";
    
    
    public PanelHistoria(int numeroHistoria) {
        
        super(new IconoDeImagen("Historia.png", -1, 30), "Historia Nro.: "+numeroHistoria, null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //.......................
        
        nroHistoria = numeroHistoria;
        
        
        boxDatosPersonales = new BoxDatosPersonales();
        boxDatosPersonales.setName(DATOS_PERSONALES);
        
        boxRepresentantes = new BoxRepresentantes();
        boxRepresentantes.setName(REPRESENTANTES);
        
        boxAntecedentes = new BoxAntecedentes();
        boxAntecedentes.setName(ANTECEDENTES);
        
        
        //.......................
        
        File archivoFoto = new File(Directorios.FOTOS+nroHistoria+".png");
        
        Image imagenFoto = null;
        
        if(archivoFoto.canRead())
        {
            try{
                imagenFoto = ImageIO.read(archivoFoto);
            }catch(IOException ioe) {
                Toolkit.getDefaultToolkit().beep();
                HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error al cargar la foto del paciente."}, IconoDeImagen.ERROR, null);
            }
        }
        
        Icon iconoFoto;
        if(imagenFoto!=null)
            iconoFoto = new ImageIcon(imagenFoto.getScaledInstance(130, 195, Image.SCALE_SMOOTH));
        else
            iconoFoto = new IconoDeImagen("FotoDesconocido.png", 130, 195);
        
        
        tieneFoto = imagenFoto!=null;
        
        
        HJCustomizedButton bCapturar = new HJCustomizedButton(new IconoDeImagen("Capturar.png", -1, 40), true);
        bCapturar.addActionListener( e -> mostrarCamara() );
        bCapturar.setToolTipText("Capturar desde webcam");
        
        JPanel panelBotonCapturar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonCapturar.add(bCapturar);
        panelBotonCapturar.setOpaque(false);
        
        HJCustomizedButton bCargar = new HJCustomizedButton(new IconoDeImagen("Cargar.png", -1, 40), true);
        bCargar.addActionListener( e -> mostrarDialogoSelector() );
        bCargar.setToolTipText("Cargar desde PC");
        
        JPanel panelBotonCargar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonCargar.add(bCargar);
        panelBotonCargar.setOpaque(false);
        
        bEliminarFoto = new HJCustomizedButton(new IconoDeImagen("Eliminar.png", -1, 30), true);
        bEliminarFoto.addActionListener( e -> eliminarFoto() );
        bEliminarFoto.setEnabled(tieneFoto);
        bEliminarFoto.setToolTipText("Eliminar foto");
        
        JPanel panelBotonEliminarFoto = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonEliminarFoto.add(bEliminarFoto);
        panelBotonEliminarFoto.setOpaque(false);
        
        
        Box boxBotonesFoto = Box.createVerticalBox();
        boxBotonesFoto.add(panelBotonCapturar);
        boxBotonesFoto.add(Box.createVerticalStrut(30));
        boxBotonesFoto.add(panelBotonCargar);
        boxBotonesFoto.add(Box.createVerticalStrut(30));
        boxBotonesFoto.add(panelBotonEliminarFoto);
        
        
        lFoto = new JLabel(iconoFoto);
        lFoto.setBorder(BorderFactory.createLineBorder(Colores.LINEAS_OSCURAS, 1));
        
        JPanel panelFoto = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelFoto.add(lFoto);
        panelFoto.setOpaque(false);
        
        
        JPanel panelFotoMacro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelFotoMacro.add(boxBotonesFoto);
        panelFotoMacro.add(Box.createHorizontalStrut(20));
        panelFotoMacro.add(panelFoto);
        panelFotoMacro.setOpaque(false);
        
        
        //.......................
        
        Font formatoEtiquetas = new Font("Arial", Font.BOLD, 16);
        
        
        HJLabel lEtiquetaNombresApellidos = new HJLabel("Nombre(s) y Apellido(s): ");
        lEtiquetaNombresApellidos.setFont(formatoEtiquetas);
        
        lNombresApellidosCabecera = crearEtiquetaNegraGrande();
        
        JPanel panelNombresApellidos = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelNombresApellidos.add(lEtiquetaNombresApellidos);
        panelNombresApellidos.add(lNombresApellidosCabecera);
        panelNombresApellidos.setOpaque(false);
        
        
        HJLabel lEtiquetaGenero = new HJLabel("Género: ");
        lEtiquetaGenero.setFont(formatoEtiquetas);
        
        lGeneroCabecera = crearEtiquetaNegraGrande();
        
        HJLabel lEtiquetaCedula = new HJLabel("Cédula: ");
        lEtiquetaCedula.setFont(formatoEtiquetas);
        
        lCedulaCabecera = crearEtiquetaNegraGrande();
        
        JPanel panelGeneroCedula = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelGeneroCedula.add(lEtiquetaGenero);
        panelGeneroCedula.add(lGeneroCabecera);
        panelGeneroCedula.add(Box.createHorizontalStrut(30));
        panelGeneroCedula.add(lEtiquetaCedula);
        panelGeneroCedula.add(lCedulaCabecera);
        panelGeneroCedula.setOpaque(false);
        
        
        //....
        
        Box boxDatosPrincipales1 = Box.createVerticalBox();
        boxDatosPrincipales1.add(panelNombresApellidos);
        boxDatosPrincipales1.add(Box.createVerticalStrut(10));
        boxDatosPrincipales1.add(panelGeneroCedula);
        
        
        //....
        
        HJLabel lEtiquetaEdad = new HJLabel("Edad: ");
        lEtiquetaEdad.setFont(formatoEtiquetas);
        
        lEdadCabecera = crearEtiquetaNegraGrande();
        
        JPanel panelEdad = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelEdad.add(lEtiquetaEdad);
        panelEdad.add(lEdadCabecera);
        panelEdad.setOpaque(false);
        
        
        HJLabel lEtiquetaUltimaCita = new HJLabel("Última Consulta: ");
        lEtiquetaUltimaCita.setFont(formatoEtiquetas);
        
        lUltimaConsulta = crearEtiquetaNegraGrande();
        lUltimaConsulta.setText(CONSULTA_NINGUNA);
        
        JPanel panelUltimaCita = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelUltimaCita.add(lEtiquetaUltimaCita);
        panelUltimaCita.add(lUltimaConsulta);
        panelUltimaCita.setOpaque(false);
        
        
        HJLabel lEtiquetaProximaCita = new HJLabel("Próxima Consulta: ");
        lEtiquetaProximaCita.setFont(formatoEtiquetas);
        
        lProximaConsulta = crearEtiquetaNegraGrande();
        lProximaConsulta.setText(CONSULTA_NO_ESTABLECIDA);
        
        JPanel panelProximaCita = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelProximaCita.add(lEtiquetaProximaCita);
        panelProximaCita.add(lProximaConsulta);
        panelProximaCita.setOpaque(false);
        
        
        Box boxDatosPrincipales2 = Box.createVerticalBox();
        boxDatosPrincipales2.add(panelEdad);
        boxDatosPrincipales2.add(Box.createVerticalStrut(15));
        boxDatosPrincipales2.add(panelUltimaCita);
        boxDatosPrincipales2.add(Box.createVerticalStrut(15));
        boxDatosPrincipales2.add(panelProximaCita);
        boxDatosPrincipales2.add(Box.createHorizontalStrut(440));
        
        
        //....
        
        Font formatoBotonesGrandes = new Font("Arial", Font.BOLD, 16);
        
        
        HJButton bHacerConsulta = new HJButton(new IconoDeImagen("Consulta.png", -1, 40), "Hacer Consulta", formatoBotonesGrandes, Colores.COLORES_BOTONES, true, 130, 80, null);
        bHacerConsulta.addActionListener( e -> {
            
            PanelConsulta panelConsulta = new PanelConsulta(nroHistoria);
            panelConsulta.setVisible(true);
            
            colocarUltimaConsulta();
        });
        
        HJButton bAnotarCita = new HJButton(new IconoDeImagen("AnotarCita.png", -1, 40), "Anotar Cita", formatoBotonesGrandes, Colores.COLORES_BOTONES, true, 130, 80, null);
        bAnotarCita.addActionListener( e -> {
            
            PanelCalendario panelCalendario = new PanelCalendario(new String[] {""+nroHistoria});
            panelCalendario.setVisible(true);
            
            colocarProximaConsulta();
        });
        
        HJButton bRecipeRapido = new HJButton(new IconoDeImagen("RecipeRapido.png", -1, 40), "Récipe Rápido", formatoBotonesGrandes, Colores.COLORES_BOTONES, true, 130, 80, null);
        bRecipeRapido.addActionListener( e -> mostrarPanelRecipeRapido() );
        
        JPanel panelBotonesCabecera = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 20));
        panelBotonesCabecera.add(bHacerConsulta);
        panelBotonesCabecera.add(bAnotarCita);
        panelBotonesCabecera.add(bRecipeRapido);
        panelBotonesCabecera.setBackground(Colores.NORMAL);
        
        panelBotonesCabecera.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Colores.LINEAS));
        
        
        //....
        
        JPanel panelDatosPrincipales2_Y_Botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelDatosPrincipales2_Y_Botones.add(boxDatosPrincipales2);
        panelDatosPrincipales2_Y_Botones.add(panelBotonesCabecera);
        panelDatosPrincipales2_Y_Botones.setOpaque(false);
        
        
        //....
        
        Box boxDatosPrincipalesMacro = Box.createVerticalBox();
        boxDatosPrincipalesMacro.add(Box.createVerticalStrut(10));
        boxDatosPrincipalesMacro.add(boxDatosPrincipales1);
        boxDatosPrincipalesMacro.add(Box.createVerticalStrut(10));
        boxDatosPrincipalesMacro.add(panelDatosPrincipales2_Y_Botones);
        boxDatosPrincipalesMacro.add(Box.createVerticalStrut(10));
        
        
        //....
        
        llenarCabecera();
        
        
        //....
        
        Color colorRelleno1;
        Color colorRelleno2;
        if(lGeneroCabecera.getText()!=null && lGeneroCabecera.getText().equals("Femenino"))
        {
            colorRelleno1 = new Color(250,230,245);
            colorRelleno2 = new Color(250,220,240);
        }else{
            colorRelleno1 = new Color(225,230,250);
            colorRelleno2 = new Color(215,220,250);
        }
        
        
        HJPaintedBox boxSuperior = new HJPaintedBox(BoxLayout.X_AXIS, colorRelleno1, colorRelleno2, null, null, 100, true);
        boxSuperior.add(Box.createHorizontalStrut(20));
        boxSuperior.add(panelFotoMacro);
        boxSuperior.add(Box.createHorizontalStrut(30));
        boxSuperior.add(boxDatosPrincipalesMacro);
        
        boxSuperior.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.LINEAS));
        
        
        //.......................
        
        Font formatoBotonesPrincipales = new Font("Arial", Font.BOLD, 18);
        Color[] coloresBotonesPrincipales = {Colores.AZUL_CLARO_2, Colores.AZUL_OSCURO, Colores.PRESSED};
        
        HJButton bDatosPersonales = new HJButton(new IconoDeImagen("Paciente.png", -1, 40), DATOS_PERSONALES, formatoBotonesPrincipales, coloresBotonesPrincipales, false, 220, 67, null);
        bDatosPersonales.convertirAToggleButton();
        bDatosPersonales.addActionListener( e -> mostrarBox(DATOS_PERSONALES) );
        
        HJButton bRepresentantes = new HJButton(new IconoDeImagen("Representantes.png", -1, 40), REPRESENTANTES, formatoBotonesPrincipales, coloresBotonesPrincipales, false, 220, 67, null);
        bRepresentantes.convertirAToggleButton();
        bRepresentantes.addActionListener( e -> mostrarBox(REPRESENTANTES) );
        
        HJButton bAntecedentes = new HJButton(new IconoDeImagen("Antecedentes.png", -1, 40), ANTECEDENTES, formatoBotonesPrincipales, coloresBotonesPrincipales, false, 220, 67, null);
        bAntecedentes.convertirAToggleButton();
        bAntecedentes.addActionListener( e -> mostrarBox(ANTECEDENTES) );
        
        JPanel panelBotonesPrincipales = new JPanel(new GridLayout(3, 1, 0, 0));
        panelBotonesPrincipales.add(bDatosPersonales);
        panelBotonesPrincipales.add(bRepresentantes);
        panelBotonesPrincipales.add(bAntecedentes);
        panelBotonesPrincipales.setOpaque(false);
        
        
        HJButtonGroup grupoBotonesPrincipales = new HJButtonGroup();
        grupoBotonesPrincipales.anadir(bDatosPersonales);
        grupoBotonesPrincipales.anadir(bRepresentantes);
        grupoBotonesPrincipales.anadir(bAntecedentes);
        
        bDatosPersonales.establecerSeleccionado(true);
        
        
        //....
        
        HJButton bVacunas = new HJButton(new IconoDeImagen("Vacuna.png", -1, 35), "Vacunas", formatoBotonesPrincipales, Colores.COLORES_BOTONES, false, -1, -1, null);
        bVacunas.addActionListener( e -> mostrarPanelVacunas() );
        
        JPanel panelBotonVacunas = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelBotonVacunas.add(bVacunas);
        panelBotonVacunas.setOpaque(false);
        
        panelBotonVacunas.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.LINEAS));
        
        
        HJButton bUltimaConsulta = new HJButton(new IconoDeImagen("Consulta.png", -1, 40), "Últ. Consulta", formatoBotonesGrandes, Colores.COLORES_BOTONES, true, -1, -1, null);
        bUltimaConsulta.addActionListener( e -> mostrarUltimaConsulta() );
        
        JPanel panelBotonUltimaConsulta = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonUltimaConsulta.add(bUltimaConsulta);
        panelBotonUltimaConsulta.setOpaque(false);
        
        
        HJButton bHistorialConsultas = new HJButton(new IconoDeImagen("HistorialConsultas.png", -1, 40), "Hist. de Consultas", formatoBotonesGrandes, Colores.COLORES_BOTONES, true, -1, -1, null);
        bHistorialConsultas.addActionListener( e -> mostrarHistorialConsultas() );
        
        JPanel panelBotonHistoriaConsultas = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonHistoriaConsultas.add(bHistorialConsultas);
        panelBotonHistoriaConsultas.setOpaque(false);
        
        
        Box boxBotonesSecundarios = Box.createVerticalBox();
        boxBotonesSecundarios.add(panelBotonVacunas);
        boxBotonesSecundarios.add(Box.createVerticalStrut(16));
        boxBotonesSecundarios.add(panelBotonUltimaConsulta);
        boxBotonesSecundarios.add(Box.createVerticalStrut(16));
        boxBotonesSecundarios.add(panelBotonHistoriaConsultas);
        boxBotonesSecundarios.add(Box.createVerticalStrut(16));
        
        boxBotonesSecundarios.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //....
        
        Box boxIzquierdo = Box.createVerticalBox();
        boxIzquierdo.add(panelBotonesPrincipales);
        boxIzquierdo.add(boxBotonesSecundarios);
        
        boxIzquierdo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Colores.LINEAS));
        
        
        //.........................
        
        panelCentral = new JPanel(layoutCarta);
        panelCentral.add(boxDatosPersonales);
        layoutCarta.addLayoutComponent(boxDatosPersonales, boxDatosPersonales.getName());
        panelCentral.add(boxRepresentantes);
        layoutCarta.addLayoutComponent(boxRepresentantes, boxRepresentantes.getName());
        panelCentral.add(boxAntecedentes);
        layoutCarta.addLayoutComponent(boxAntecedentes, boxAntecedentes.getName());
        
        panelCentral.setOpaque(false);
        
        panelCentral.setPreferredSize(new Dimension(958, 449));
        
        
        //.....................
        
        JPanel panelPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelPrincipal.add(boxIzquierdo);
        panelPrincipal.add(panelCentral);
        panelPrincipal.setOpaque(false);
        
        
        //.....................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(boxSuperior);
        cajaGeneral.add(panelPrincipal);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private HJLabel crearEtiquetaNegraGrande() {
        
        HJLabel etiqueta = new HJLabel();
        etiqueta.setFont(new Font("Arial Black", Font.PLAIN, 20));
        etiqueta.setForeground(Colores.NEGRO);
        
        return etiqueta;
    }
    
    
    private HJLabel crearEtiquetaAzul(String texto) {
        
        HJLabel etiqueta = new HJLabel(texto);
        etiqueta.setForeground(colorAzulLlamativo);
        
        return etiqueta;
    }
    
    
    private HJLabel crearEtiquetaNegra() {
        
        HJLabel etiqueta = new HJLabel();
        etiqueta.setFont(new Font("Arial", Font.BOLD, 18));
        etiqueta.setForeground(Colores.NEGRO);
        
        return etiqueta;
    }
    
    
    private void llenarCabecera() {
        
        colocarDatosBasicos();
        
        colocarUltimaConsulta();
        
        colocarProximaConsulta();
    }
    
    
    private void colocarDatosBasicos() {
        
        MapaDatos datosPersonales = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+nroHistoria+".dda");
        
        lNombresApellidosCabecera.setText(datosPersonales.get(Personales.KEY_NOMBRES)+" "+datosPersonales.get(Personales.KEY_APELLIDOS));
        lGeneroCabecera.setText(datosPersonales.get(Personales.KEY_GENERO));
        lCedulaCabecera.setText(datosPersonales.get(Personales.KEY_CEDULA));
        
        String[] diaMesAno = datosPersonales.get(Personales.KEY_FECHA_NACIMIENTO).split("-");
        
        lEdadCabecera.setText(Utilidades.obtenerTiempoTranscurrido(LocalDate.of(Integer.parseInt(diaMesAno[2]), Integer.parseInt(diaMesAno[1]), Integer.parseInt(diaMesAno[0])), LocalDate.now()));
    }
    
    
    private void colocarUltimaConsulta() {
        
        String nombreArchivo = Utilidades.obtenerNombreUltimoArchivo(Directorios.CONSULTAS+nroHistoria);
        
        if(nombreArchivo!=null)
        {
            String[] cadenaAnoMesDia = ((nombreArchivo.split("_"))[0]).split("-");
            
            lUltimaConsulta.setText(cadenaAnoMesDia[2]+"-"+cadenaAnoMesDia[1]+"-"+cadenaAnoMesDia[0]);
            
            return;
        }
        
        lUltimaConsulta.setText(CONSULTA_NINGUNA);
    }
    
    
    private void colocarProximaConsulta() {
        
        String[] cadenaArchivos = (new File(Directorios.CITAS)).list();
        
        for(int i=0 ; i<=cadenaArchivos.length-1 ; i++)
        {
            if(LocalDate.parse((cadenaArchivos[i].split(".dda"))[0], DateTimeFormatter.ofPattern("uuuu-MM-dd")).isEqual(LocalDate.now()))
                continue;
            
            HashMap<String,String> mapaDia = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.CITAS+cadenaArchivos[i]);
            
            if(mapaDia.containsValue(""+nroHistoria))
            {
                String[] cadenaFecha = (cadenaArchivos[i].split(".dda"))[0].split("-");
                
                lProximaConsulta.setText(cadenaFecha[2]+"-"+cadenaFecha[1]+"-"+cadenaFecha[0]);
                
                return;
            }
        }
        
        lProximaConsulta.setText(CONSULTA_NO_ESTABLECIDA);
    }
    
    
    //...............................
    
    
    private void mostrarCamara() {
        
        List<Webcam> listaWebcams = Webcam.getWebcams();
        
        JComboBox<Object> cbWebcams = new JComboBox<>();
        
        if(listaWebcams.isEmpty()==false)
        {
            cbWebcams.addItem("Seleccione...");
            
            for(int i=0 ; i<=listaWebcams.size()-1 ; i++)
            {
                cbWebcams.addItem(listaWebcams.get(i));
            }
            
        }else{
            
            HJDialog.mostrarMensaje("No se Detectaron Dispositivos de Captura", new String[] {"No se detectaron cámaras disponibles para usar.", "Por favor, asegúrese de que está(n) correctamente instalada(s) y conectada(s)."}, IconoDeImagen.ADVERTENCIA, null);
            
            return;
        }
        
        Webcam webcamSeleccionada = (Webcam)HJDialog.mostrarDialogoSelector("Seleccione Cámara", new String[] {"¿Cuál cámara desea usar?"}, cbWebcams, false, new HJButton(null, "Continuar", Colores.COLORES_BOTONES), IconoDeImagen.PREGUNTA, null);
        
        if(webcamSeleccionada!=null)
        {
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloPanelCaptura = new Thread( () -> {
                
                PanelCaptura panelCaptura = new PanelCaptura(webcamSeleccionada);
                
                panelEspera.setVisible(false);
                panelEspera.dispose();
                
                panelCaptura.setVisible(true);
                
                if(panelCaptura.seCapturoImagen())
                {
                    PanelRecortarImagen panelRecortarImagen = new PanelRecortarImagen(panelCaptura.obtenerImagenCapturada());
                    panelRecortarImagen.setVisible(true);
                    
                    if(panelRecortarImagen.seRecortoImagen())
                    {
                        lFoto.setIcon(new ImageIcon(panelRecortarImagen.obtenerImagenRecortada().getScaledInstance(130, 195, Image.SCALE_SMOOTH)));
                        
                        bEliminarFoto.setEnabled(true);
                        
                        guardarFoto();
                    }
                }
            });
            hiloPanelCaptura.start();
            
            panelEspera.setVisible(true);
        }
    }
    
    
    private void mostrarDialogoSelector() {
        
        setIconImage(new IconoDeImagen("Abrir.png", -1, 16).getImage());
        
        HJFileChooser selector = new HJFileChooser(null, HJFileChooser.IMAGENES);
        
        if(selector.showOpenDialog(this)==HJFileChooser.APPROVE_OPTION)
        {
            Image imagen = null;
            
            try{
                imagen = ImageIO.read(selector.getSelectedFile());
            }catch(IOException ioe) {
                Toolkit.getDefaultToolkit().beep();
                HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error al cargar la imagen."}, IconoDeImagen.ERROR, null);
            }
            
            if(imagen==null)  return;
            
            PanelRecortarImagen panelRecortarImagen = new PanelRecortarImagen(imagen);
            panelRecortarImagen.setVisible(true);
            
            if(panelRecortarImagen.seRecortoImagen())
            {
                lFoto.setIcon(new ImageIcon(panelRecortarImagen.obtenerImagenRecortada().getScaledInstance(130, 195, Image.SCALE_SMOOTH)));
                
                bEliminarFoto.setEnabled(true);
                
                guardarFoto();
            }
        }
        
        setIconImage(null);
    }
    
    
    private void guardarFoto() {
        
        BufferedImage imagenBuffered = new BufferedImage(130, 195, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2D = imagenBuffered.createGraphics();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Icon icono = lFoto.getIcon();
        
        icono.paintIcon(null, g2D, 0, 0);
        
        try{
            ImageIO.write(imagenBuffered, "png", new File(Directorios.FOTOS+nroHistoria+".png"));
        }catch(IOException ioe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error al Guardar Foto", new String[] {"Ocurrió un error al guardar la foto del paciente."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
    private void eliminarFoto() {
        
        if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea eliminar la foto del paciente?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
        {
            Utilidades.eliminarArchivo(Directorios.FOTOS+nroHistoria+".png");
            
            lFoto.setIcon(new IconoDeImagen("FotoDesconocido.png", 130, 195));
            
            bEliminarFoto.setEnabled(false);
        }
    }
       
    
    //...............................
    
    private void mostrarBox(String nombreBoxAMostrar) {
        
        layoutCarta.show(panelCentral, nombreBoxAMostrar);
    }
    
    
    //...............................
    
    private void mostrarPanelRecipeRapido() {
        
        PanelImpresionRecipes panelImpresionRecipes = new PanelImpresionRecipes(nroHistoria, false);
        
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
    
    
    private void mostrarPanelVacunas() {
        
        HJWaitingPanel panelEspera = new HJWaitingPanel();
        
        Thread hiloPanelVacunas = new Thread( () -> {
            
            PanelVacunas panelVacunas = new PanelVacunas(nroHistoria);
            
            panelEspera.setVisible(false);
            panelEspera.dispose();
            
            panelVacunas.setVisible(true);
        });
        hiloPanelVacunas.start();
        
        panelEspera.setVisible(true);
    }
    
    
    private void mostrarUltimaConsulta() {
        
        String[] listaNombresConsultas = (new File(Directorios.CONSULTAS+nroHistoria)).list();
        
        if(listaNombresConsultas==null)
        {
            HJDialog.mostrarMensaje("No Hay Consultas", new String[] {"No hay consultas registradas para este paciente."}, IconoDeImagen.ADVERTENCIA, null);
            
            return;
        }
        
        HJWaitingPanel panelEspera = new HJWaitingPanel();
        
        Thread hiloPanelVacunas = new Thread( () -> {
            
            PanelVerConsulta panelVerConsulta = new PanelVerConsulta(nroHistoria, listaNombresConsultas[listaNombresConsultas.length-1]);
            
            panelEspera.setVisible(false);
            panelEspera.dispose();
            
            panelVerConsulta.setVisible(true);
        });
        hiloPanelVacunas.start();
        
        panelEspera.setVisible(true);
    }
    
    
    private void mostrarHistorialConsultas() {
        
        String[] listaNombresConsultas = (new File(Directorios.CONSULTAS+nroHistoria)).list();
        
        if(listaNombresConsultas==null)
        {
            HJDialog.mostrarMensaje("No Hay Consultas", new String[] {"No hay consultas registradas para este paciente."}, IconoDeImagen.ADVERTENCIA, null);
            
            return;
        }
        
        PanelHistorialConsultas panelHistorialConsultas = new PanelHistorialConsultas(nroHistoria);
        panelHistorialConsultas.setVisible(true);
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class BoxDatosPersonales extends HJPaintedBox {
        
        private final HJLabel lNombres;
        private final HJTextField tNombres;
        private final HJLabel lApellidos;
        private final HJTextField tApellidos;
        private final HJLabel lGenero;
        private final HJComboBox<String> cbGenero;
        private final HJLabel lCedula;
        private final HJComboBox<String> cbTipoCedula;
        private final HJTextField tCedula;
        private final HJLabel lFechaDeNacimiento;
        private final HJComboBox<String> cbDia;
        private final HJComboBox<String> cbMes;
        private final HJComboBox<String> cbAno;
        
        private final HJLabel lTelefono1;
        private final HJTextField tTelefono1;
        private final HJLabel lTelefono2;
        private final HJTextField tTelefono2;
        private final HJLabel lEMail;
        private final HJTextField tEMail;
        
        private final HJLabel lEstado;
        private final HJTextField tEstado;
        private final HJLabel lCiudad;
        private final HJTextField tCiudad;
        private final HJTextArea taDireccion;
        
        private final HJLabel lGrupoSanguineo;
        private final HJComboBox<String> cbGrupoSanguineo;
        private final HJTextArea taLugarNacimiento;
        private final HJTextArea taLugarProcedencia;
        
        private final HJButton bEditar;
        private final HJButton bGuardar;
        private final HJButton bCancelar;
        
        
        public BoxDatosPersonales() {
            
            super(BoxLayout.Y_AXIS, new Color(245,250,255), new Color(235,245,255), null, null, 100, true);
            
            
            JLabel lIconoDatosPersonales = new JLabel(new IconoDeImagen("Paciente.png", -1, 30));
            lIconoDatosPersonales.setFont(formatoTitulos);
            
            JLabel lDatosPersonales = new JLabel(DATOS_PERSONALES);
            lDatosPersonales.setFont(formatoTitulos);
            
            bEditar = new HJButton(new IconoDeImagen("Editar.png", -1, 20), "Editar", Colores.COLORES_BOTONES);
            bEditar.addActionListener( e -> establecerEditable(true) );
            
            bGuardar = new HJButton(new IconoDeImagen("Guardar.png", -1, 20), "Guardar", Colores.COLORES_BOTONES);
            bGuardar.addActionListener( e -> guardar() );
            
            bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
            bCancelar.addActionListener( e -> cancelar() );
            
            JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panelSuperior.add(Box.createHorizontalStrut(30));
            panelSuperior.add(lIconoDatosPersonales);
            panelSuperior.add(Box.createHorizontalStrut(15));
            panelSuperior.add(lDatosPersonales);
            panelSuperior.add(Box.createHorizontalStrut(30));
            panelSuperior.add(bEditar);
            panelSuperior.add(bGuardar);
            panelSuperior.add(Box.createHorizontalStrut(30));
            panelSuperior.add(bCancelar);
            panelSuperior.setOpaque(false);
            
            panelSuperior.setMaximumSize(new Dimension(900, 30));
            
            Box boxSuperior = Box.createVerticalBox();
            boxSuperior.add(panelSuperior);
            
            
            //...................
            
            Font formatoEnclosingBoxes = new Font("Arial", Font.BOLD, 20);
            Color colorTextoEnclosingBoxes = Colores.TEXTO;
            Color colorLineaEnclosingBoxes = Colores.LINEAS_OSCURAS;
            
            //....
            
            lNombres = crearEtiquetaNegra();
            tNombres = new HJTextField(20);
            
            lApellidos = crearEtiquetaNegra();
            tApellidos = new HJTextField(20);
            
            JPanel panelNombresApellidos = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelNombresApellidos.add(crearEtiquetaAzul("Nombres: "));
            panelNombresApellidos.add(lNombres);
            panelNombresApellidos.add(tNombres);
            panelNombresApellidos.add(Box.createHorizontalStrut(30));
            panelNombresApellidos.add(crearEtiquetaAzul("Apellidos: "));
            panelNombresApellidos.add(lApellidos);
            panelNombresApellidos.add(tApellidos);
            panelNombresApellidos.setOpaque(false);
            
            
            lGenero = crearEtiquetaNegra();
            
            String[] generos = {"Seleccione...", "Femenino", "Masculino"};
            cbGenero = new HJComboBox<>(generos);
            
            
            lCedula = crearEtiquetaNegra();
            
            String[] tiposCedula = {"V-", "E-"};
            cbTipoCedula = new HJComboBox<>(tiposCedula);
            tCedula = new HJTextField(8);
            
            
            lFechaDeNacimiento = crearEtiquetaNegra();
            
            String[] dias = new String[32];
            dias[0] = "Día";
            for(int i=1 ; i<=31 ; i++)
            {
                dias[i] = ""+i;
            }
            
            cbDia = new HJComboBox<>(dias);
            
            String[] meses = {"Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            
            cbMes = new HJComboBox<>(meses);
            
            cbAno = new HJComboBox<>();
            cbAno.addItem("Año");
            LocalDate fechaActual = LocalDate.now();
            int anoActual = fechaActual.getYear();
            for(int i=0, j=anoActual ; i<=120 ; i++, j--)
            {
                cbAno.addItem(""+j);
            }
            
            JPanel panelGeneroCedulaNacimiento = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelGeneroCedulaNacimiento.add(crearEtiquetaAzul("Género: "));
            panelGeneroCedulaNacimiento.add(lGenero);
            panelGeneroCedulaNacimiento.add(cbGenero);
            panelGeneroCedulaNacimiento.add(Box.createHorizontalStrut(30));
            panelGeneroCedulaNacimiento.add(new HJLabel("Cédula: "));
            panelGeneroCedulaNacimiento.add(lCedula);
            panelGeneroCedulaNacimiento.add(cbTipoCedula);
            panelGeneroCedulaNacimiento.add(Box.createHorizontalStrut(4));
            panelGeneroCedulaNacimiento.add(tCedula);
            panelGeneroCedulaNacimiento.add(Box.createHorizontalStrut(30));
            panelGeneroCedulaNacimiento.add(crearEtiquetaAzul("Fecha de Nacimiento: "));
            panelGeneroCedulaNacimiento.add(lFechaDeNacimiento);
            panelGeneroCedulaNacimiento.add(cbDia);
            panelGeneroCedulaNacimiento.add(Box.createHorizontalStrut(4));
            panelGeneroCedulaNacimiento.add(cbMes);
            panelGeneroCedulaNacimiento.add(Box.createHorizontalStrut(4));
            panelGeneroCedulaNacimiento.add(cbAno);
            panelGeneroCedulaNacimiento.setOpaque(false);
            
            
            HJEnclosingBox boxDatosBasicos = new HJEnclosingBox(BoxLayout.Y_AXIS, "Datos Básicos", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxDatosBasicos.add(Box.createVerticalStrut(30));
            boxDatosBasicos.add(panelNombresApellidos);
            boxDatosBasicos.add(Box.createVerticalStrut(15));
            boxDatosBasicos.add(panelGeneroCedulaNacimiento);
            boxDatosBasicos.add(Box.createVerticalStrut(10));
            
            Box boxDatosBasicosGeneral = Box.createHorizontalBox();
            boxDatosBasicosGeneral.add(Box.createHorizontalStrut(10));
            boxDatosBasicosGeneral.add(boxDatosBasicos);
            boxDatosBasicosGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            lTelefono1 = crearEtiquetaNegra();
            tTelefono1 = new HJTextField(9);
            
            lTelefono2 = crearEtiquetaNegra();
            tTelefono2 = new HJTextField(9);
            
            lEMail = crearEtiquetaNegra();
            tEMail = new HJTextField(22);
            
            JPanel panelTelefonosEmail = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelTelefonosEmail.add(new HJLabel("Teléfono: "));
            panelTelefonosEmail.add(lTelefono1);
            panelTelefonosEmail.add(tTelefono1);
            panelTelefonosEmail.add(Box.createHorizontalStrut(30));
            panelTelefonosEmail.add(new HJLabel("Teléfono 2: "));
            panelTelefonosEmail.add(lTelefono2);
            panelTelefonosEmail.add(tTelefono2);
            panelTelefonosEmail.add(Box.createHorizontalStrut(30));
            panelTelefonosEmail.add(new HJLabel("E-Mail: "));
            panelTelefonosEmail.add(lEMail);
            panelTelefonosEmail.add(tEMail);
            panelTelefonosEmail.setOpaque(false);
            
            
            HJEnclosingBox boxInformacionDeContacto = new HJEnclosingBox(BoxLayout.Y_AXIS, "Información de Contacto", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxInformacionDeContacto.add(Box.createVerticalStrut(30));
            boxInformacionDeContacto.add(panelTelefonosEmail);
            boxInformacionDeContacto.add(Box.createVerticalStrut(10));
            
            Box boxInformacionDeContactoGeneral = Box.createHorizontalBox();
            boxInformacionDeContactoGeneral.add(Box.createHorizontalStrut(10));
            boxInformacionDeContactoGeneral.add(boxInformacionDeContacto);
            boxInformacionDeContactoGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            lEstado = crearEtiquetaNegra();
            tEstado = new HJTextField(12);
            
            lCiudad = crearEtiquetaNegra();
            tCiudad = new HJTextField(12);
            
            taDireccion = new HJTextArea(3, 25);
            HJScrollPane scrollDireccion = new HJScrollPane(taDireccion);
            scrollDireccion.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollDireccion.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollDireccion.removerEscuchadorRuedaRaton();
            
            
            JPanel panelEstadoCiudadDireccion = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(20));
            panelEstadoCiudadDireccion.add(new HJLabel("Estado: "));
            panelEstadoCiudadDireccion.add(lEstado);
            panelEstadoCiudadDireccion.add(tEstado);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(30));
            panelEstadoCiudadDireccion.add(new HJLabel("Ciudad: "));
            panelEstadoCiudadDireccion.add(lCiudad);
            panelEstadoCiudadDireccion.add(tCiudad);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(30));
            panelEstadoCiudadDireccion.add(new HJLabel("Dirección: "));
            panelEstadoCiudadDireccion.add(scrollDireccion);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(20));
            panelEstadoCiudadDireccion.setOpaque(false);
            
            
            HJEnclosingBox boxDireccion = new HJEnclosingBox(BoxLayout.Y_AXIS, "Dirección", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxDireccion.add(Box.createVerticalStrut(30));
            boxDireccion.add(panelEstadoCiudadDireccion);
            boxDireccion.add(Box.createVerticalStrut(10));
            
            Box boxDireccionGeneral = Box.createHorizontalBox();
            boxDireccionGeneral.add(Box.createHorizontalStrut(10));
            boxDireccionGeneral.add(boxDireccion);
            boxDireccionGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            lGrupoSanguineo = crearEtiquetaNegra();
            
            String[] gruposSanguineos = {"---", "O+", "A+", "B+", "AB+", "O-", "A-", "B-", "AB-"};
            cbGrupoSanguineo = new HJComboBox<>(gruposSanguineos);
            
            JPanel panelGrupoSanguineo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelGrupoSanguineo.add(new HJLabel("Grupo Sanguíneo: "));
            panelGrupoSanguineo.add(lGrupoSanguineo);
            panelGrupoSanguineo.add(cbGrupoSanguineo);
            panelGrupoSanguineo.setOpaque(false);
            
            
            taLugarNacimiento = new HJTextArea(2, 20);
            HJScrollPane scrollLugarNacimiento = new HJScrollPane(taLugarNacimiento);
            scrollLugarNacimiento.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollLugarNacimiento.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollLugarNacimiento.removerEscuchadorRuedaRaton();
            
            taLugarProcedencia = new HJTextArea(2, 20);
            HJScrollPane scrollLugarProcedencia = new HJScrollPane(taLugarProcedencia);
            scrollLugarProcedencia.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollLugarProcedencia.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollLugarProcedencia.removerEscuchadorRuedaRaton();
            
            JPanel panelNacimientoProcedencia = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelNacimientoProcedencia.add(new HJLabel("Lugar de Nacimiento: "));
            panelNacimientoProcedencia.add(scrollLugarNacimiento);
            panelNacimientoProcedencia.add(Box.createHorizontalStrut(30));
            panelNacimientoProcedencia.add(new HJLabel("Lugar de Procedencia: "));
            panelNacimientoProcedencia.add(scrollLugarProcedencia);
            panelNacimientoProcedencia.setOpaque(false);
            
            
            HJEnclosingBox boxInformacionAdicional = new HJEnclosingBox(BoxLayout.Y_AXIS, "Información Adicional", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxInformacionAdicional.add(Box.createVerticalStrut(30));
            boxInformacionAdicional.add(panelGrupoSanguineo);
            boxInformacionAdicional.add(Box.createVerticalStrut(15));
            boxInformacionAdicional.add(panelNacimientoProcedencia);
            boxInformacionAdicional.add(Box.createVerticalStrut(15));
            
            Box boxInformacionAdicionalGeneral = Box.createHorizontalBox();
            boxInformacionAdicionalGeneral.add(Box.createHorizontalStrut(10));
            boxInformacionAdicionalGeneral.add(boxInformacionAdicional);
            boxInformacionAdicionalGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            
            Box boxVertical = Box.createVerticalBox();
            boxVertical.add(Box.createVerticalStrut(20));
            boxVertical.add(boxSuperior);
            boxVertical.add(Box.createVerticalStrut(20));
            boxVertical.add(boxDatosBasicosGeneral);
            boxVertical.add(Box.createVerticalStrut(25));
            boxVertical.add(boxInformacionDeContactoGeneral);
            boxVertical.add(Box.createVerticalStrut(25));
            boxVertical.add(boxDireccionGeneral);
            boxVertical.add(Box.createVerticalStrut(25));
            boxVertical.add(boxInformacionAdicionalGeneral);
            boxVertical.add(Box.createVerticalStrut(25));
            
            JScrollPane scrollBoxVertical = new JScrollPane(boxVertical);
            scrollBoxVertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollBoxVertical.getVerticalScrollBar().setUnitIncrement(10);
            scrollBoxVertical.setOpaque(false);
            scrollBoxVertical.getViewport().setOpaque(false);
            
            
            add(scrollBoxVertical);
            
            
            cargarDatos();
            
            establecerEditable(false);
            
        }
        
        
        private void establecerEditable(boolean editable) {
            
            lNombres.setVisible(!editable);
            tNombres.setVisible(editable);
            lApellidos.setVisible(!editable);
            tApellidos.setVisible(editable);
            lGenero.setVisible(!editable);
            cbGenero.setVisible(editable);
            lCedula.setVisible(!editable);
            cbTipoCedula.setVisible(editable);
            tCedula.setVisible(editable);
            lFechaDeNacimiento.setVisible(!editable);
            cbDia.setVisible(editable);
            cbMes.setVisible(editable);
            cbAno.setVisible(editable);
            
            lTelefono1.setVisible(!editable);
            tTelefono1.setVisible(editable);
            lTelefono2.setVisible(!editable);
            tTelefono2.setVisible(editable);
            lEMail.setVisible(!editable);
            tEMail.setVisible(editable);
            
            lEstado.setVisible(!editable);
            tEstado.setVisible(editable);
            lCiudad.setVisible(!editable);
            tCiudad.setVisible(editable);
            taDireccion.setEditable(editable);
            taDireccion.setOpaque(editable);
            
            lGrupoSanguineo.setVisible(!editable);
            cbGrupoSanguineo.setVisible(editable);
            taLugarNacimiento.setEditable(editable);
            taLugarNacimiento.setOpaque(editable);
            taLugarProcedencia.setEditable(editable);
            taLugarProcedencia.setOpaque(editable);

            bEditar.setVisible(!editable);
            bGuardar.setVisible(editable);
            bCancelar.setVisible(editable);
        }
        
        
        private void cargarDatos() {
            
            MapaDatos datosPersonales = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+nroHistoria+".dda");
            
            lNombres.setText(datosPersonales.get(Personales.KEY_NOMBRES));
            tNombres.setText(datosPersonales.get(Personales.KEY_NOMBRES));
            
            lApellidos.setText(datosPersonales.get(Personales.KEY_APELLIDOS));
            tApellidos.setText(datosPersonales.get(Personales.KEY_APELLIDOS));
            
            lGenero.setText(datosPersonales.get(Personales.KEY_GENERO));
            cbGenero.setSelectedItem(datosPersonales.get(Personales.KEY_GENERO));
            
            if(datosPersonales.get(Personales.KEY_CEDULA)!=null)
            {
                lCedula.setText(datosPersonales.get(Personales.KEY_CEDULA));
                cbTipoCedula.setSelectedItem(datosPersonales.get(Personales.KEY_CEDULA).substring(0, 2));
                tCedula.setText(datosPersonales.get(Personales.KEY_CEDULA).substring(2));    
            }
            
            
            String[] fechaNacimiento = datosPersonales.get(Personales.KEY_FECHA_NACIMIENTO).split("-");
            
            String dia = fechaNacimiento[0];
            if(dia.length()==1)
                dia = "0"+dia;
            
            lFechaDeNacimiento.setText(dia+"-"+cbMes.getItemAt(Integer.parseInt(fechaNacimiento[1]))+"-"+fechaNacimiento[2]);
            cbDia.setSelectedIndex(Integer.parseInt(fechaNacimiento[0]));
            cbMes.setSelectedIndex(Integer.parseInt(fechaNacimiento[1]));
            cbAno.setSelectedItem(fechaNacimiento[2]);
            
            lTelefono1.setText(datosPersonales.get(Personales.KEY_TELEFONO_1));
            tTelefono1.setText(datosPersonales.get(Personales.KEY_TELEFONO_1));
            
            lTelefono2.setText(datosPersonales.get(Personales.KEY_TELEFONO_2));
            tTelefono2.setText(datosPersonales.get(Personales.KEY_TELEFONO_2));
            
            lEMail.setText(datosPersonales.get(Personales.KEY_EMAIL));
            tEMail.setText(datosPersonales.get(Personales.KEY_EMAIL));
            
            lEstado.setText(datosPersonales.get(Personales.KEY_ESTADO));
            tEstado.setText(datosPersonales.get(Personales.KEY_ESTADO));
            
            lCiudad.setText(datosPersonales.get(Personales.KEY_CIUDAD));
            tCiudad.setText(datosPersonales.get(Personales.KEY_CIUDAD));
            
            taDireccion.setText(datosPersonales.get(Personales.KEY_DIRECCION));
            
            lGrupoSanguineo.setText(datosPersonales.get(Personales.KEY_GRUPO_SANGUINEO));
            if(datosPersonales.get(Personales.KEY_GRUPO_SANGUINEO)!=null)
                cbGrupoSanguineo.setSelectedItem(datosPersonales.get(Personales.KEY_GRUPO_SANGUINEO));
            
            taLugarNacimiento.setText(datosPersonales.get(Personales.KEY_LUGAR_NACIMIENTO));
            
            taLugarProcedencia.setText(datosPersonales.get(Personales.KEY_LUGAR_PROCEDENCIA));
        }
        
        
        private void guardar() {
            
            if(estanCasillasListas())
            {
                MapaDatos mapaDatos = new MapaDatos();
                mapaDatos.put(Personales.KEY_NOMBRES, tNombres.getText());
                mapaDatos.put(Personales.KEY_APELLIDOS, tApellidos.getText());
                mapaDatos.put(Personales.KEY_GENERO, ""+cbGenero.getSelectedItem());
                if(tCedula.esTextoValido())  mapaDatos.put(Personales.KEY_CEDULA, ""+cbTipoCedula.getSelectedItem()+tCedula.getText());
                mapaDatos.put(Personales.KEY_FECHA_NACIMIENTO, ""+cbDia.getSelectedIndex()+"-"+cbMes.getSelectedIndex()+"-"+cbAno.getSelectedItem());
                if(tTelefono1.esTextoValido())  mapaDatos.put(Personales.KEY_TELEFONO_1, tTelefono1.getText());
                if(tTelefono2.esTextoValido())  mapaDatos.put(Personales.KEY_TELEFONO_2, tTelefono2.getText());
                if(tEMail.esTextoValido())  mapaDatos.put(Personales.KEY_EMAIL, tEMail.getText());
                if(tEstado.esTextoValido())  mapaDatos.put(Personales.KEY_ESTADO, tEstado.getText());
                if(tCiudad.esTextoValido())  mapaDatos.put(Personales.KEY_CIUDAD, tCiudad.getText());
                if(taDireccion.esTextoValido())  mapaDatos.put(Personales.KEY_DIRECCION, taDireccion.getText());
                if(cbGrupoSanguineo.getSelectedIndex()>0)  mapaDatos.put(Personales.KEY_GRUPO_SANGUINEO, ""+cbGrupoSanguineo.getSelectedItem());
                if(taLugarNacimiento.esTextoValido())  mapaDatos.put(Personales.KEY_LUGAR_NACIMIENTO, taLugarNacimiento.getText());
                if(taLugarProcedencia.esTextoValido())  mapaDatos.put(Personales.KEY_LUGAR_PROCEDENCIA, taLugarProcedencia.getText());
                
                Utilidades.guardarEnArchivo(Directorios.DATOS_PERSONALES+nroHistoria+".dda", mapaDatos);
                
                cargarDatos();
                
                establecerEditable(false);
                
                colocarDatosBasicos();
            }
        }
        
        
        private void cancelar() {
            
            cargarDatos();
            
            establecerEditable(false);
        }
        
        
        private boolean estanCasillasListas() {
            
            if(tNombres.esTextoValido()==false || tApellidos.esTextoValido()==false || cbGenero.getSelectedIndex()==0 || cbDia.getSelectedIndex()==0 || cbMes.getSelectedIndex()==0 || cbAno.getSelectedIndex()==0)
            {
                HJDialog.mostrarMensaje("Faltan Datos", new String[] {"Los datos con etiquetas azules son necesarios. Por favor, llénelos."}, IconoDeImagen.ADVERTENCIA, null);
                
                return false;
            }
            
            
            if(tCedula.esTextoValido()==false && boxRepresentantes.estaRepresentanteEstablecido()==false)
            {
                HJDialog.mostrarMensaje("Faltan Datos", new String[] {"Debe introducir un número de cédula a menos que haya establecido un representante."}, IconoDeImagen.ADVERTENCIA, null);
                
                return false;
            }
            
            
            if(tCedula.esNumeroEntero()==false)
            {
                HJDialog.mostrarMensaje("Error al Ingresar Datos", new String[] {"El valor de la cédula debe ser escrito como un número entero.", "(Ejemplo: 7654321)"}, IconoDeImagen.ADVERTENCIA, null);
                
                return false;
            }
            
            
            int dia = cbDia.getSelectedIndex();
            int mes = cbMes.getSelectedIndex();
            int ano = new Integer(""+cbAno.getSelectedItem());
            
            if(Utilidades.esFechaValida(dia, mes, ano)==false)
            {
                HJDialog.mostrarMensaje("Fecha Inválida", new String[] {"La fecha es inválida.", "Por favor, corríjala."}, IconoDeImagen.ADVERTENCIA, null);
                
                return false;
            }
            
            if(Utilidades.esFechaAnterior(dia, mes, ano)==false)
            {
                HJDialog.mostrarMensaje("Fecha Inválida", new String[] {"La fecha debe ser anterior o igual a la actual.", "Por favor, corríjala."}, IconoDeImagen.ADVERTENCIA, null);
                
                return false;
            }
            
            
            return true;
        }
        
        
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class BoxRepresentantes extends HJPaintedBox {
        
        private final HJLabel lNombres;
        private final HJTextField tNombres;
        private final HJLabel lApellidos;
        private final HJTextField tApellidos;
        private final HJLabel lCedula;
        private final HJComboBox<String> cbTipoCedula;
        private final HJTextField tCedula;
        private final HJLabel lParentesco;
        private final HJComboBox<String> cbParentesco;
        
        private final HJLabel lTelefono1;
        private final HJTextField tTelefono1;
        private final HJLabel lTelefono2;
        private final HJTextField tTelefono2;
        private final HJLabel lEMail;
        private final HJTextField tEMail;
        
        private final JPanel panelBotonDireccionPaciente;
        private final HJLabel lEstado;
        private final HJTextField tEstado;
        private final HJLabel lCiudad;
        private final HJTextField tCiudad;
        private final HJTextArea taDireccion;
        
        private final HJButton bEstablecerRepresentante;
        private final HJButton bEditar;
        private final HJButton bGuardar;
        private final JPanel panelBotonEliminarPaciente;
        private final HJButton bCancelar;
        
        private final Box boxVerticalConRepresentante;
        private final Box boxVerticalSinRepresentante;
        
        
        public BoxRepresentantes() {
            
            super(BoxLayout.Y_AXIS, new Color(245,245,245), new Color(235,235,235), null, null, 100, true);
            
            
            JLabel lIconoRepresentantes = new JLabel(new IconoDeImagen("Representantes.png", -1, 30));
            lIconoRepresentantes.setFont(formatoTitulos);
            
            JLabel lRepresentantes = new JLabel(REPRESENTANTES);
            lRepresentantes.setFont(formatoTitulos);
            
            bEstablecerRepresentante = new HJButton(new IconoDeImagen("Representantes.png", -1, 20), "Establecer Representante", Colores.COLORES_BOTONES);
            bEstablecerRepresentante.addActionListener( e -> establecerRepresentante() );
            
            bEditar = new HJButton(new IconoDeImagen("Editar.png", -1, 20), "Editar", Colores.COLORES_BOTONES);
            bEditar.addActionListener( e -> establecerEditable(true) );
            
            bGuardar = new HJButton(new IconoDeImagen("Guardar.png", -1, 20), "Guardar", Colores.COLORES_BOTONES);
            bGuardar.addActionListener( e -> guardar() );
            
            HJButton bEliminarRepresentante = new HJButton(new IconoDeImagen("Eliminar.png", -1, 20), "Eliminar Representante", Colores.COLORES_BOTONES);
            bEliminarRepresentante.addActionListener( e -> eliminarRepresentante() );
            
            panelBotonEliminarPaciente = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelBotonEliminarPaciente.add(Box.createHorizontalStrut(30));
            panelBotonEliminarPaciente.add(bEliminarRepresentante);
            panelBotonEliminarPaciente.setOpaque(false);
            
            bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
            bCancelar.addActionListener( e -> cancelar() );
            
            JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panelSuperior.add(Box.createHorizontalStrut(30));
            panelSuperior.add(lIconoRepresentantes);
            panelSuperior.add(Box.createHorizontalStrut(15));
            panelSuperior.add(lRepresentantes);
            panelSuperior.add(Box.createHorizontalStrut(30));
            panelSuperior.add(bEstablecerRepresentante);
            panelSuperior.add(bEditar);
            panelSuperior.add(bGuardar);
            panelSuperior.add(panelBotonEliminarPaciente);
            panelSuperior.add(Box.createHorizontalStrut(30));
            panelSuperior.add(bCancelar);
            panelSuperior.setOpaque(false);
            
            panelSuperior.setMaximumSize(new Dimension(900, 30));
            
            Box boxSuperior = Box.createVerticalBox();
            boxSuperior.add(panelSuperior);
            
            
            //...................
            
            Font formatoEnclosingBoxes = new Font("Arial", Font.BOLD, 20);
            Color colorTextoEnclosingBoxes = Colores.TEXTO;
            Color colorLineaEnclosingBoxes = Colores.LINEAS_OSCURAS;
            
            //....
            
            lNombres = crearEtiquetaNegra();
            tNombres = new HJTextField(20);
            
            lApellidos = crearEtiquetaNegra();
            tApellidos = new HJTextField(20);
            
            JPanel panelNombresApellidos = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelNombresApellidos.add(crearEtiquetaAzul("Nombres: "));
            panelNombresApellidos.add(lNombres);
            panelNombresApellidos.add(tNombres);
            panelNombresApellidos.add(Box.createHorizontalStrut(30));
            panelNombresApellidos.add(crearEtiquetaAzul("Apellidos: "));
            panelNombresApellidos.add(lApellidos);
            panelNombresApellidos.add(tApellidos);
            panelNombresApellidos.setOpaque(false);
            
            
            lCedula = crearEtiquetaNegra();
            
            String[] tiposCedula = {"V-", "E-"};
            cbTipoCedula = new HJComboBox<>(tiposCedula);
            tCedula = new HJTextField(8);
            
            
            lParentesco = crearEtiquetaNegra();
            
            String[] parentescos = {"Seleccione...", "Madre/Padre", "Abuelo(a)", "Hijo(a)", "Nieto(a)", "Tío(a)", "Otro"};
            cbParentesco = new HJComboBox<>(parentescos);
            
            JPanel panelCedulaParentesco = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelCedulaParentesco.add(crearEtiquetaAzul("Cédula: "));
            panelCedulaParentesco.add(lCedula);
            panelCedulaParentesco.add(cbTipoCedula);
            panelCedulaParentesco.add(Box.createHorizontalStrut(4));
            panelCedulaParentesco.add(tCedula);
            panelCedulaParentesco.add(Box.createHorizontalStrut(30));
            panelCedulaParentesco.add(crearEtiquetaAzul("Parentesco: "));
            panelCedulaParentesco.add(lParentesco);
            panelCedulaParentesco.add(cbParentesco);
            panelCedulaParentesco.setOpaque(false);
            
            
            HJEnclosingBox boxDatosBasicos = new HJEnclosingBox(BoxLayout.Y_AXIS, "Datos Básicos", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxDatosBasicos.add(Box.createVerticalStrut(30));
            boxDatosBasicos.add(panelNombresApellidos);
            boxDatosBasicos.add(Box.createVerticalStrut(15));
            boxDatosBasicos.add(panelCedulaParentesco);
            boxDatosBasicos.add(Box.createVerticalStrut(15));
            
            Box boxDatosBasicosGeneral = Box.createHorizontalBox();
            boxDatosBasicosGeneral.add(Box.createHorizontalStrut(10));
            boxDatosBasicosGeneral.add(boxDatosBasicos);
            boxDatosBasicosGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            lTelefono1 = crearEtiquetaNegra();
            tTelefono1 = new HJTextField(9);
            
            lTelefono2 = crearEtiquetaNegra();
            tTelefono2 = new HJTextField(9);
            
            lEMail = crearEtiquetaNegra();
            tEMail = new HJTextField(22);
            
            JPanel panelTelefonosEmail = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelTelefonosEmail.add(new HJLabel("Teléfono: "));
            panelTelefonosEmail.add(lTelefono1);
            panelTelefonosEmail.add(tTelefono1);
            panelTelefonosEmail.add(Box.createHorizontalStrut(30));
            panelTelefonosEmail.add(new HJLabel("Teléfono 2: "));
            panelTelefonosEmail.add(lTelefono2);
            panelTelefonosEmail.add(tTelefono2);
            panelTelefonosEmail.add(Box.createHorizontalStrut(30));
            panelTelefonosEmail.add(new HJLabel("E-Mail: "));
            panelTelefonosEmail.add(lEMail);
            panelTelefonosEmail.add(tEMail);
            panelTelefonosEmail.setOpaque(false);
            
            
            HJEnclosingBox boxInformacionDeContacto = new HJEnclosingBox(BoxLayout.Y_AXIS, "Información de Contacto", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxInformacionDeContacto.add(Box.createVerticalStrut(30));
            boxInformacionDeContacto.add(panelTelefonosEmail);
            boxInformacionDeContacto.add(Box.createVerticalStrut(15));
            
            Box boxInformacionDeContactoGeneral = Box.createHorizontalBox();
            boxInformacionDeContactoGeneral.add(Box.createHorizontalStrut(10));
            boxInformacionDeContactoGeneral.add(boxInformacionDeContacto);
            boxInformacionDeContactoGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            HJCustomizedButton bDireccionPaciente = new HJCustomizedButton("Colocar dirección del paciente", new Color[] {Colores.AZUL_LLAMATIVO, Colores.AZUL_OSCURO}, true, true, 1);
            bDireccionPaciente.addActionListener( e -> colocarDireccionPaciente() );
            
            panelBotonDireccionPaciente = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panelBotonDireccionPaciente.add(Box.createHorizontalStrut(50));
            panelBotonDireccionPaciente.add(bDireccionPaciente);
            panelBotonDireccionPaciente.setOpaque(false);
            
            
            lEstado = crearEtiquetaNegra();
            tEstado = new HJTextField(12);
            
            lCiudad = crearEtiquetaNegra();
            tCiudad = new HJTextField(12);
            
            taDireccion = new HJTextArea(3, 25);
            HJScrollPane scrollDireccion = new HJScrollPane(taDireccion);
            scrollDireccion.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollDireccion.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollDireccion.removerEscuchadorRuedaRaton();
            
            
            JPanel panelEstadoCiudadDireccion = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(20));
            panelEstadoCiudadDireccion.add(new HJLabel("Estado: "));
            panelEstadoCiudadDireccion.add(lEstado);
            panelEstadoCiudadDireccion.add(tEstado);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(30));
            panelEstadoCiudadDireccion.add(new HJLabel("Ciudad: "));
            panelEstadoCiudadDireccion.add(lCiudad);
            panelEstadoCiudadDireccion.add(tCiudad);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(30));
            panelEstadoCiudadDireccion.add(new HJLabel("Dirección: "));
            panelEstadoCiudadDireccion.add(scrollDireccion);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(20));
            panelEstadoCiudadDireccion.setOpaque(false);
            
            
            HJEnclosingBox boxDireccion = new HJEnclosingBox(BoxLayout.Y_AXIS, "Dirección", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxDireccion.add(Box.createVerticalStrut(25));
            boxDireccion.add(panelBotonDireccionPaciente);
            boxDireccion.add(panelEstadoCiudadDireccion);
            boxDireccion.add(Box.createVerticalStrut(15));
            
            Box boxDireccionGeneral = Box.createHorizontalBox();
            boxDireccionGeneral.add(Box.createHorizontalStrut(10));
            boxDireccionGeneral.add(boxDireccion);
            boxDireccionGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            
            HJLabel lRepresentanteNoEstablecido = new HJLabel("Representante No Establecido");
            lRepresentanteNoEstablecido.setFont(new Font("Arial", Font.BOLD, 22));
            
            JPanel panelEtiquetaNoHayRepresentante = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
            panelEtiquetaNoHayRepresentante.add(lRepresentanteNoEstablecido);
            panelEtiquetaNoHayRepresentante.setOpaque(false);
            
            boxVerticalSinRepresentante = Box.createVerticalBox();
            boxVerticalSinRepresentante.add(panelEtiquetaNoHayRepresentante);
            
            
            boxVerticalConRepresentante = Box.createVerticalBox();
            boxVerticalConRepresentante.add(boxDatosBasicosGeneral);
            boxVerticalConRepresentante.add(Box.createVerticalStrut(25));
            boxVerticalConRepresentante.add(boxInformacionDeContactoGeneral);
            boxVerticalConRepresentante.add(Box.createVerticalStrut(25));
            boxVerticalConRepresentante.add(boxDireccionGeneral);
            boxVerticalConRepresentante.add(Box.createVerticalStrut(25));
            
            
            Box boxVertical = Box.createVerticalBox();
            boxVertical.add(Box.createVerticalStrut(20));
            boxVertical.add(boxSuperior);
            boxVertical.add(Box.createVerticalStrut(20));
            boxVertical.add(boxVerticalSinRepresentante);
            boxVertical.add(boxVerticalConRepresentante);
            
            
            add(boxVertical);
            
            
            cargarDatos();
            
            establecerEditable(false);
            
        }
        
        
        private void establecerEditable(boolean editable) {
            
            lNombres.setVisible(!editable);
            tNombres.setVisible(editable);
            lApellidos.setVisible(!editable);
            tApellidos.setVisible(editable);
            lCedula.setVisible(!editable);
            cbTipoCedula.setVisible(editable);
            tCedula.setVisible(editable);
            lParentesco.setVisible(!editable);
            cbParentesco.setVisible(editable);
            
            lTelefono1.setVisible(!editable);
            tTelefono1.setVisible(editable);
            lTelefono2.setVisible(!editable);
            tTelefono2.setVisible(editable);
            lEMail.setVisible(!editable);
            tEMail.setVisible(editable);
            
            panelBotonDireccionPaciente.setVisible(editable);
            lEstado.setVisible(!editable);
            tEstado.setVisible(editable);
            lCiudad.setVisible(!editable);
            tCiudad.setVisible(editable);
            taDireccion.setEditable(editable);
            taDireccion.setOpaque(editable);
            
            if(estaRepresentanteEstablecido())
            {
                bEstablecerRepresentante.setVisible(false);
                bEditar.setVisible(!editable);
                bGuardar.setVisible(editable);
                panelBotonEliminarPaciente.setVisible(editable);
                bCancelar.setVisible(editable);
                
            }else{
                
                bEstablecerRepresentante.setVisible(true);
                bEditar.setVisible(false);
                bGuardar.setVisible(false);
                panelBotonEliminarPaciente.setVisible(false);
                bCancelar.setVisible(false);
            }
            
        }
        
        
        private void cargarDatos() {
            
            MapaDatos datosRepresentante = (MapaDatos)Utilidades.leerArchivo(Directorios.REPRESENTANTES+nroHistoria+".dda");
            
            if(datosRepresentante!=null)
            {
                lNombres.setText(datosRepresentante.get(Representantes.KEY_NOMBRES));
                tNombres.setText(datosRepresentante.get(Representantes.KEY_NOMBRES));
                
                lApellidos.setText(datosRepresentante.get(Representantes.KEY_APELLIDOS));
                tApellidos.setText(datosRepresentante.get(Representantes.KEY_APELLIDOS));
                
                lCedula.setText(datosRepresentante.get(Representantes.KEY_CEDULA));
                cbTipoCedula.setSelectedItem(datosRepresentante.get(Representantes.KEY_CEDULA).substring(0, 2));
                tCedula.setText(datosRepresentante.get(Representantes.KEY_CEDULA).substring(2));
                
                lParentesco.setText(datosRepresentante.get(Representantes.KEY_PARENTESCO));
                cbParentesco.setSelectedItem(datosRepresentante.get(Representantes.KEY_PARENTESCO));
                
                lTelefono1.setText(datosRepresentante.get(Representantes.KEY_TELEFONO_1));
                tTelefono1.setText(datosRepresentante.get(Representantes.KEY_TELEFONO_1));
                
                lTelefono2.setText(datosRepresentante.get(Representantes.KEY_TELEFONO_2));
                tTelefono2.setText(datosRepresentante.get(Representantes.KEY_TELEFONO_2));
                
                lEMail.setText(datosRepresentante.get(Representantes.KEY_EMAIL));
                tEMail.setText(datosRepresentante.get(Representantes.KEY_EMAIL));
                
                lEstado.setText(datosRepresentante.get(Representantes.KEY_ESTADO));
                tEstado.setText(datosRepresentante.get(Representantes.KEY_ESTADO));
                
                lCiudad.setText(datosRepresentante.get(Representantes.KEY_CIUDAD));
                tCiudad.setText(datosRepresentante.get(Representantes.KEY_CIUDAD));
                
                taDireccion.setText(datosRepresentante.get(Representantes.KEY_DIRECCION));
                
                
                boxVerticalSinRepresentante.setVisible(false);
                boxVerticalConRepresentante.setVisible(true);
                
            }else{
                
                boxVerticalConRepresentante.setVisible(false);
                boxVerticalSinRepresentante.setVisible(true);
            }
        }
        
        
        private void guardar() {
            
            if(estanCasillasListas())
            {
                MapaDatos mapaDatos = new MapaDatos();
                mapaDatos.put(Representantes.KEY_NOMBRES, tNombres.getText());
                mapaDatos.put(Representantes.KEY_APELLIDOS, tApellidos.getText());
                mapaDatos.put(Representantes.KEY_CEDULA, ""+cbTipoCedula.getSelectedItem()+tCedula.getText());
                mapaDatos.put(Representantes.KEY_PARENTESCO, ""+cbParentesco.getSelectedItem());
                if(tTelefono1.esTextoValido())  mapaDatos.put(Representantes.KEY_TELEFONO_1, tTelefono1.getText());
                if(tTelefono2.esTextoValido())  mapaDatos.put(Representantes.KEY_TELEFONO_2, tTelefono2.getText());
                if(tEMail.esTextoValido())  mapaDatos.put(Representantes.KEY_EMAIL, tEMail.getText());
                if(tEstado.esTextoValido())  mapaDatos.put(Representantes.KEY_ESTADO, tEstado.getText());
                if(tCiudad.esTextoValido())  mapaDatos.put(Representantes.KEY_CIUDAD, tCiudad.getText());
                if(taDireccion.esTextoValido())  mapaDatos.put(Representantes.KEY_DIRECCION, taDireccion.getText());
                
                Utilidades.guardarEnArchivo(Directorios.REPRESENTANTES+nroHistoria+".dda", mapaDatos);
                
                cargarDatos();
                
                establecerEditable(false);
            }
        }
        
        
        private void cancelar() {
            
            cargarDatos();
            
            establecerEditable(false);
        }
        
        
        private boolean estanCasillasListas() {
            
            if(tNombres.esTextoValido()==false || tApellidos.esTextoValido()==false || tCedula.esTextoValido()==false || cbParentesco.getSelectedIndex()==0)
            {
                HJDialog.mostrarMensaje("Faltan Datos", new String[] {"Los datos con etiquetas azules son necesarios. Por favor, llénelos."}, IconoDeImagen.ADVERTENCIA, null);
                
                return false;
            }
            
            
            if(tCedula.esNumeroEntero()==false)
            {
                HJDialog.mostrarMensaje("Error al Ingresar Datos", new String[] {"El valor de la cédula debe ser escrito como un número entero.", "(Ejemplo: 7654321)"}, IconoDeImagen.ADVERTENCIA, null);
                
                return false;
            }
            
            
            return true;
        }
        
        
        private void establecerRepresentante() {
            
            establecerEditable(true);
            
            boxVerticalSinRepresentante.setVisible(false);
            boxVerticalConRepresentante.setVisible(true);
            
            bEstablecerRepresentante.setVisible(false);
            bEditar.setVisible(false);
            bGuardar.setVisible(true);
            panelBotonEliminarPaciente.setVisible(false);
            bCancelar.setVisible(true);
        }
        
        
        public boolean estaRepresentanteEstablecido() {
            
            MapaDatos datosRepresentante = (MapaDatos)Utilidades.leerArchivo(Directorios.REPRESENTANTES+nroHistoria+".dda");
            
            return datosRepresentante!=null;
        }
        
        
        private void eliminarRepresentante() {
            
            if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea eliminar toda la información del representante?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA,null)==0)
            {
                Utilidades.eliminarArchivo(Directorios.REPRESENTANTES+nroHistoria+".dda");
                
                boxVerticalConRepresentante.setVisible(false);
                boxVerticalSinRepresentante.setVisible(true);
                
                bEstablecerRepresentante.setVisible(true);
                bEditar.setVisible(false);
                bGuardar.setVisible(false);
                panelBotonEliminarPaciente.setVisible(false);
                bCancelar.setVisible(false);
            }
        }
        
        
        private void colocarDireccionPaciente() {
            
            MapaDatos datosPersonales = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+nroHistoria+".dda");
            
            tEstado.setText(datosPersonales.get(Personales.KEY_ESTADO));
            tCiudad.setText(datosPersonales.get(Personales.KEY_CIUDAD));
            taDireccion.setText(datosPersonales.get(Personales.KEY_DIRECCION));
        }
        
        
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class BoxAntecedentes extends Box {
        
        private JTabbedPane tabbedPanel;
        
        private final HJLabel lTalla;
        private final HJTextField tTalla;
        private final HJLabel lPeso;
        private final HJTextField tPeso;
        private final HJLabel lC_Cefalica;
        private final HJTextField tC_Cefalica;
        private final HJLabel lC_Toracica;
        private final HJTextField tC_Toracica;
        private final HJLabel lC_Abdominal;
        private final HJTextField tC_Abdominal;
        
        private final Box boxPersonales;
        private final JScrollPane scrollPersonales;
        
        private final Box boxFamiliares;
        private final JScrollPane scrollFamiliares;
        
        private final Box boxAlergias;
        private final JScrollPane scrollAlergias;
        
        private final Box boxIntervenciones;
        private final JScrollPane scrollIntervenciones;
        private final String TEXTO_INHABILITADO_INTERVENCIONES = "Debe asignar el tipo de intervención antes de escribir aquí.";
        
        private final Box boxHospitalizaciones;
        private final JScrollPane scrollHospitalizaciones;
        private final String TEXTO_INHABILITADO_HOSPITALIZACIONES = "Debe asignar el motivo de la hospitalización antes de escribir aquí.";
        
        private final HJButton bEditar;
        private final HJButton bAnadir;
        private final HJButton bGuardar;
        private final HJButton bCancelar;
        
        
        public BoxAntecedentes() {
            
            super(BoxLayout.Y_AXIS);
            
            
            tabbedPanel = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
            
            
            JLabel lIconoAntecedentes = new JLabel(new IconoDeImagen("Antecedentes.png", -1, 30));
            lIconoAntecedentes.setFont(formatoTitulos);
            
            JLabel lAntecedentes = new JLabel(ANTECEDENTES);
            lAntecedentes.setFont(formatoTitulos);
            
            bEditar = new HJButton(new IconoDeImagen("Editar.png", -1, 20), "Editar", Colores.COLORES_BOTONES);
            bEditar.addActionListener( e -> establecerEditable(true) );
            
            bAnadir = new HJButton(new IconoDeImagen("Anadir.png", -1, 20), "Anadir", Colores.COLORES_BOTONES);
            bAnadir.addActionListener( e -> {
                
                switch(tabbedPanel.getSelectedIndex())
                {
                    case 1 : anadirPanelPersonales();
                             break;
                    
                    case 2 : anadirPanelFamiliares();
                             break;
                    
                    case 3 : anadirPanelAlergias();
                             break;
                    
                    case 4 : anadirPanelIntervenciones();
                             break;
                    
                    case 5 : anadirPanelHospitalizaciones();
                             break;
                    
                    default: break;
                }
            });
            
            tabbedPanel.addChangeListener( e -> {
                
                if(bEditar.isVisible()==false)  bAnadir.setVisible(tabbedPanel.getSelectedIndex()!=0);
            });
            
            bGuardar = new HJButton(new IconoDeImagen("Guardar.png", -1, 20), "Guardar", Colores.COLORES_BOTONES);
            bGuardar.addActionListener( e -> guardar() );
            
            bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
            bCancelar.addActionListener( e -> {
                
                cargarDatos();
                
                establecerEditable(false);
            });
            
            JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panelSuperior.add(Box.createHorizontalStrut(30));
            panelSuperior.add(lIconoAntecedentes);
            panelSuperior.add(Box.createHorizontalStrut(15));
            panelSuperior.add(lAntecedentes);
            panelSuperior.add(Box.createHorizontalStrut(30));
            panelSuperior.add(bEditar);
            panelSuperior.add(bAnadir);
            panelSuperior.add(Box.createHorizontalStrut(30));
            panelSuperior.add(bGuardar);
            panelSuperior.add(Box.createHorizontalStrut(30));
            panelSuperior.add(bCancelar);
            panelSuperior.setOpaque(false);
            
            panelSuperior.setMaximumSize(new Dimension(900, 30));
            
            Box boxSuperior = Box.createVerticalBox();
            boxSuperior.add(panelSuperior);
            
            
            //.... ANTECEDENTES NEONATALES ....
            
            lTalla = crearEtiquetaNegra();
            tTalla = new HJTextField(5);
            
            lPeso = crearEtiquetaNegra();
            tPeso = new HJTextField(5);
            
            JPanel panelTallaPeso = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelTallaPeso.add(new HJLabel("Talla: "));
            panelTallaPeso.add(lTalla);
            panelTallaPeso.add(tTalla);
            panelTallaPeso.add(new HJLabel(" cm."));
            panelTallaPeso.add(Box.createHorizontalStrut(70));
            panelTallaPeso.add(new HJLabel("Peso: "));
            panelTallaPeso.add(lPeso);
            panelTallaPeso.add(tPeso);
            panelTallaPeso.add(new HJLabel(" Kg."));
            panelTallaPeso.setPreferredSize(new Dimension(900, 30));
            panelTallaPeso.setOpaque(false);
            
            
            lC_Cefalica = crearEtiquetaNegra();
            tC_Cefalica = new HJTextField(5);
            
            lC_Toracica = crearEtiquetaNegra();
            tC_Toracica = new HJTextField(5);
            
            lC_Abdominal = crearEtiquetaNegra();
            tC_Abdominal = new HJTextField(5);
            
            JPanel panelCircunferencias = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelCircunferencias.add(new HJLabel("C. Cefálica: "));
            panelCircunferencias.add(lC_Cefalica);
            panelCircunferencias.add(tC_Cefalica);
            panelCircunferencias.add(new HJLabel(" cm."));
            panelCircunferencias.add(Box.createHorizontalStrut(50));
            panelCircunferencias.add(new HJLabel("C. Torácica: "));
            panelCircunferencias.add(lC_Toracica);
            panelCircunferencias.add(tC_Toracica);
            panelCircunferencias.add(new HJLabel(" c.m."));
            panelCircunferencias.add(Box.createHorizontalStrut(50));
            panelCircunferencias.add(new HJLabel("C. Abdominal: "));
            panelCircunferencias.add(lC_Abdominal);
            panelCircunferencias.add(tC_Abdominal);
            panelCircunferencias.add(new HJLabel(" c.m."));
            panelCircunferencias.setPreferredSize(new Dimension(900, 30));
            panelCircunferencias.setOpaque(false);
            
            
            Box boxAntecedentesNeonatales = Box.createVerticalBox();
            boxAntecedentesNeonatales.add(Box.createVerticalStrut(100));
            boxAntecedentesNeonatales.add(panelTallaPeso);
            boxAntecedentesNeonatales.add(Box.createVerticalStrut(40));
            boxAntecedentesNeonatales.add(panelCircunferencias);
            
            HJPaintedPanel panelHorizontalNeonatales = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 0, 0), new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            panelHorizontalNeonatales.add(boxAntecedentesNeonatales);
            panelHorizontalNeonatales.setOpaque(false);
            
            
            //.................................
            
            tabbedPanel.add("Antecedentes Neonatales", panelHorizontalNeonatales);
            
            
            //.... ANTECEDENTES PERSONALES ....
            
            HJLabel lPatologiaPersonales = new HJLabel("Patología");
            lPatologiaPersonales.setForeground(colorAzulLlamativo);
            
            JPanel panelEtiquetasPersonales = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
            panelEtiquetasPersonales.add(Box.createHorizontalStrut(200));
            panelEtiquetasPersonales.add(lPatologiaPersonales);
            panelEtiquetasPersonales.add(Box.createHorizontalStrut(300));
            panelEtiquetasPersonales.add(new HJLabel("Detalles"));
            panelEtiquetasPersonales.setMaximumSize(new Dimension(1000, 35));
            panelEtiquetasPersonales.setOpaque(false);
            
            //....
            
            boxPersonales = Box.createVerticalBox();
            
            Box boxPersonalesGeneral = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            boxPersonalesGeneral.add(Box.createVerticalStrut(15));
            boxPersonalesGeneral.add(boxPersonales);
            
            scrollPersonales = new JScrollPane(boxPersonalesGeneral);
            scrollPersonales.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPersonales.getVerticalScrollBar().setUnitIncrement(10);
            
            //....
            
            Box boxVerticalPersonales = Box.createVerticalBox();
            boxVerticalPersonales.add(panelEtiquetasPersonales);
            boxVerticalPersonales.add(scrollPersonales);
            
            //....
            
            tabbedPanel.add("Antecedentes Personales", boxVerticalPersonales);
            
            
            //.... ANTECEDENTES FAMILIARES ....
            
            HJLabel lPatologiaFamiliares = new HJLabel("Patología");
            lPatologiaFamiliares.setForeground(colorAzulLlamativo);
            
            JPanel panelEtiquetasFamiliares = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
            panelEtiquetasFamiliares.add(Box.createHorizontalStrut(200));
            panelEtiquetasFamiliares.add(lPatologiaFamiliares);
            panelEtiquetasFamiliares.add(Box.createHorizontalStrut(300));
            panelEtiquetasFamiliares.add(new HJLabel("Detalles"));
            panelEtiquetasFamiliares.setMaximumSize(new Dimension(1000, 35));
            panelEtiquetasFamiliares.setOpaque(false);
            
            //....
            
            boxFamiliares = Box.createVerticalBox();
            
            Box boxFamiliaresGeneral = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            boxFamiliaresGeneral.add(Box.createVerticalStrut(15));
            boxFamiliaresGeneral.add(boxFamiliares);
            
            scrollFamiliares = new JScrollPane(boxFamiliaresGeneral);
            scrollFamiliares.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollFamiliares.getVerticalScrollBar().setUnitIncrement(10);
            
            //....
            
            Box boxVerticalFamiliares = Box.createVerticalBox();
            boxVerticalFamiliares.add(panelEtiquetasFamiliares);
            boxVerticalFamiliares.add(scrollFamiliares);
            
            //....
            
            tabbedPanel.add("Antecedentes Familiares", boxVerticalFamiliares);
            
            
            //.... ALERGIAS ...
            
            HJLabel lAlergias = new HJLabel("Alergias");
            lAlergias.setForeground(colorAzulLlamativo);
            
            JPanel panelEtiquetaAlergias = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
            panelEtiquetaAlergias.add(Box.createHorizontalStrut(390));
            panelEtiquetaAlergias.add(lAlergias);
            panelEtiquetaAlergias.setMaximumSize(new Dimension(1000, 35));
            panelEtiquetaAlergias.setOpaque(false);
            
            //....
            
            boxAlergias = Box.createVerticalBox();
            
            Box boxAlergiasGeneral = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            boxAlergiasGeneral.add(Box.createVerticalStrut(25));
            boxAlergiasGeneral.add(boxAlergias);
            
            scrollAlergias = new JScrollPane(boxAlergiasGeneral);
            scrollAlergias.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollAlergias.getVerticalScrollBar().setUnitIncrement(10);
            
            //....
            
            Box boxVerticalAlergias = Box.createVerticalBox();
            boxVerticalAlergias.add(panelEtiquetaAlergias);
            boxVerticalAlergias.add(scrollAlergias);
            
            //....
            
            tabbedPanel.add("Alergias", boxVerticalAlergias);
            
            
            //.... INTERVENCIONES QUIRÚRGICAS ....
            
            HJLabel lFechaIntervenciones = new HJLabel("Fecha");
            lFechaIntervenciones.setForeground(colorAzulLlamativo);
            
            HJLabel lTipo = new HJLabel("Tipo");
            lTipo.setForeground(colorAzulLlamativo);
            
            JPanel panelEtiquetasIntervenciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
            panelEtiquetasIntervenciones.add(Box.createHorizontalStrut(72));
            panelEtiquetasIntervenciones.add(lFechaIntervenciones);
            panelEtiquetasIntervenciones.add(Box.createHorizontalStrut(180));
            panelEtiquetasIntervenciones.add(lTipo);
            panelEtiquetasIntervenciones.add(Box.createHorizontalStrut(292));
            panelEtiquetasIntervenciones.add(new HJLabel("Detalles"));
            panelEtiquetasIntervenciones.setMaximumSize(new Dimension(1000, 35));
            panelEtiquetasIntervenciones.setOpaque(false);
            
            //....
            
            boxIntervenciones = Box.createVerticalBox();
            
            Box boxIntervencionesGeneral = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            boxIntervencionesGeneral.add(Box.createVerticalStrut(15));
            boxIntervencionesGeneral.add(boxIntervenciones);
            
            scrollIntervenciones = new JScrollPane(boxIntervencionesGeneral);
            scrollIntervenciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollIntervenciones.getVerticalScrollBar().setUnitIncrement(10);
            
            //....
            
            Box boxVerticalIntervenciones = Box.createVerticalBox();
            boxVerticalIntervenciones.add(panelEtiquetasIntervenciones);
            boxVerticalIntervenciones.add(scrollIntervenciones);
            
            //....
            
            tabbedPanel.add("Intervenciones Quirúrgicas", boxVerticalIntervenciones);
            
            
            //.... HOSPITALIZACIONES ....
            
            HJLabel lFechaHospitalizaciones = new HJLabel("Fecha");
            lFechaHospitalizaciones.setForeground(colorAzulLlamativo);
            
            HJLabel lMotivo = new HJLabel("Motivo");
            lMotivo.setForeground(colorAzulLlamativo);
            
            JPanel panelEtiquetasHospitalizaciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
            panelEtiquetasHospitalizaciones.add(Box.createHorizontalStrut(72));
            panelEtiquetasHospitalizaciones.add(lFechaHospitalizaciones);
            panelEtiquetasHospitalizaciones.add(Box.createHorizontalStrut(176));
            panelEtiquetasHospitalizaciones.add(lMotivo);
            panelEtiquetasHospitalizaciones.add(Box.createHorizontalStrut(277));
            panelEtiquetasHospitalizaciones.add(new HJLabel("Detalles"));
            panelEtiquetasHospitalizaciones.setMaximumSize(new Dimension(1000, 35));
            panelEtiquetasHospitalizaciones.setOpaque(false);
            
            //....
            
            boxHospitalizaciones = Box.createVerticalBox();
            
            Box boxHospitalizacionesGeneral = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            boxHospitalizacionesGeneral.add(Box.createVerticalStrut(15));
            boxHospitalizacionesGeneral.add(boxHospitalizaciones);
            
            scrollHospitalizaciones = new JScrollPane(boxHospitalizacionesGeneral);
            scrollHospitalizaciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollHospitalizaciones.getVerticalScrollBar().setUnitIncrement(10);
            
            //....
            
            Box boxVerticalHospitalizaciones = Box.createVerticalBox();
            boxVerticalHospitalizaciones.add(panelEtiquetasHospitalizaciones);
            boxVerticalHospitalizaciones.add(scrollHospitalizaciones);
            
            //....
            
            tabbedPanel.add("Hospitalizaciones", boxVerticalHospitalizaciones);
            
            
            //.........................
            
            
            Box boxVertical = Box.createVerticalBox();
            boxVertical.add(Box.createVerticalStrut(20));
            boxVertical.add(boxSuperior);
            boxVertical.add(Box.createVerticalStrut(15));
            boxVertical.add(tabbedPanel);
            
            
            add(boxVertical);
            
            
            cargarDatos();
            
            establecerEditable(false);
            
        }
        
        
        private void anadirPanelPersonales() {
            
            if(estanPanelesDeBoxListos(boxPersonales))
            {
                PanelPatologia panelPatologia = new PanelPatologia();
                panelPatologia.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelPatologia) );
                boxPersonales.add(panelPatologia);
                boxPersonales.validate();
                
                scrollPersonales.validate();
                scrollPersonales.getVerticalScrollBar().setValue(scrollPersonales.getVerticalScrollBar().getMaximum());
                
            }else{
                
                HJDialog.mostrarMensaje("Casilla(s) Disponible(s)", new String[] {"Hay casilla(s) disponible(s) para llenar. Utilice la(s) misma(s)."}, IconoDeImagen.INFORMACION, null);
            }
        }
        
        
        private void anadirPanelFamiliares() {
            
            if(estanPanelesDeBoxListos(boxFamiliares))
            {
                PanelPatologia panelPatologia = new PanelPatologia();
                panelPatologia.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelPatologia) );
                boxFamiliares.add(panelPatologia);
                boxFamiliares.validate();
                
                scrollFamiliares.validate();
                scrollFamiliares.getVerticalScrollBar().setValue(scrollFamiliares.getVerticalScrollBar().getMaximum());
                
            }else{
                
                HJDialog.mostrarMensaje("Casilla(s) Disponible(s)", new String[] {"Hay casilla(s) disponible(s) para llenar. Utilice la(s) misma(s)."}, IconoDeImagen.INFORMACION, null);
            }
        }
        
        
        private void anadirPanelAlergias() {
            
            if(estanPanelesDeBoxListos(boxAlergias))
            {
                PanelAlergia panelAlergia = new PanelAlergia();
                panelAlergia.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelAlergia) );
                boxAlergias.add(panelAlergia);
                boxAlergias.validate();
                
                scrollAlergias.validate();
                scrollAlergias.getVerticalScrollBar().setValue(scrollAlergias.getVerticalScrollBar().getMaximum());
                
            }else{
                
                HJDialog.mostrarMensaje("Casilla(s) Disponible(s)", new String[] {"Hay casilla(s) disponible(s) para llenar. Utilice la(s) misma(s)."}, IconoDeImagen.INFORMACION, null);
            }
        }
        
        
        private void anadirPanelIntervenciones() {
            
            if(estanPanelesDeBoxListos(boxIntervenciones))
            {
                PanelFechaYDosCampos panelIntervencion = new PanelFechaYDosCampos(TEXTO_INHABILITADO_INTERVENCIONES);
                panelIntervencion.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelIntervencion) );
                boxIntervenciones.add(panelIntervencion);
                boxIntervenciones.validate();
                
                scrollIntervenciones.validate();
                scrollIntervenciones.getVerticalScrollBar().setValue(scrollIntervenciones.getVerticalScrollBar().getMaximum());
                
            }else{
                
                HJDialog.mostrarMensaje("Fecha(s) y/o Tipo(s) No Establecidos", new String[] {"Todas las fechas y los tipos de las intervenciónes deben estar establecidos antes de añadir otra."}, IconoDeImagen.INFORMACION, null);
            }
        }
        
        
        private void anadirPanelHospitalizaciones() {
            
            if(estanPanelesDeBoxListos(boxHospitalizaciones))
            {
                PanelFechaYDosCampos panelHospitalizacion = new PanelFechaYDosCampos(TEXTO_INHABILITADO_HOSPITALIZACIONES);
                panelHospitalizacion.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelHospitalizacion) );
                boxHospitalizaciones.add(panelHospitalizacion);
                boxHospitalizaciones.validate();
                
                scrollHospitalizaciones.validate();
                scrollHospitalizaciones.getVerticalScrollBar().setValue(scrollHospitalizaciones.getVerticalScrollBar().getMaximum());
                
            }else{
                
                HJDialog.mostrarMensaje("Fecha(s) y/o Tipo(s) No Establecidos", new String[] {"Todas las fechas y los motivos de las hospitalizaciones deben estar establecidos antes de añadir otra."}, IconoDeImagen.INFORMACION, null);
            }
        }
        
        
        private boolean estanPanelesDeBoxListos(Box boxAntecedentes) {
            
            Component[] componentes = boxAntecedentes.getComponents();
            
            if(componentes.length==0)
                return true;
            
            boolean estanListos = true;
            
            if(componentes[0] instanceof PanelPatologia)
            {
                for(int i=componentes.length-1 ; i>=0 ; i--)
                {
                    if(((PanelPatologia)componentes[i]).estaListo()==false)
                        estanListos = false;
                }
                
            }else{
                
                if(componentes[0] instanceof PanelAlergia)
                {
                    for(int i=componentes.length-1 ; i>=0 ; i--)
                    {
                        if(((PanelAlergia)componentes[i]).estaListo()==false)
                            estanListos = false;
                    }
                    
                }else{
                    
                    if(componentes[0] instanceof PanelFechaYDosCampos)
                    {
                        for(int i=componentes.length-1 ; i>=0 ; i--)
                        {
                            if(((PanelFechaYDosCampos)componentes[i]).estaListo()==false)
                                estanListos = false;
                        }
                    }
                }
            }
            
            return estanListos;
        }
        
        
        private void eliminarAntecedente(JPanel panelAntecedente) {
            
            Box boxContenedor = (Box)panelAntecedente.getParent();
            
            boxContenedor.remove(panelAntecedente);
            boxContenedor.validate();
            
            if(boxContenedor.equals(boxPersonales))
            {
                scrollPersonales.validate();
                scrollPersonales.repaint();
                
            }else{
                
                if(boxContenedor.equals(boxFamiliares))
                {
                    scrollFamiliares.validate();
                    scrollFamiliares.repaint();
                    
                }else{
                    
                    if(boxContenedor.equals(boxAlergias))
                    {
                        scrollAlergias.validate();
                        scrollAlergias.repaint();
                        
                    }else{
                        
                        if(boxContenedor.equals(boxIntervenciones))
                        {
                            scrollIntervenciones.validate();
                            scrollIntervenciones.repaint();
                            
                        }else{
                            
                            scrollHospitalizaciones.validate();
                            scrollHospitalizaciones.repaint();
                        }
                    }
                }
            }
        }
        
        
        private void establecerEditable(boolean editable) {
            
            lTalla.setVisible(!editable);
            tTalla.setVisible(editable);
            lPeso.setVisible(!editable);
            tPeso.setVisible(editable);
            lC_Cefalica.setVisible(!editable);
            tC_Cefalica.setVisible(editable);
            lC_Toracica.setVisible(!editable);
            tC_Toracica.setVisible(editable);
            lC_Abdominal.setVisible(!editable);
            tC_Abdominal.setVisible(editable);
            
            for(int i=0 ; i<=boxPersonales.getComponentCount()-1 ; i++)
            {
                PanelPatologia panelPatologia = (PanelPatologia)boxPersonales.getComponent(i);
                panelPatologia.establecerEditable(editable);
            }
            
            for(int i=0 ; i<=boxFamiliares.getComponentCount()-1 ; i++)
            {
                PanelPatologia panelPatologia = (PanelPatologia)boxFamiliares.getComponent(i);
                panelPatologia.establecerEditable(editable);
            }
            
            for(int i=0 ; i<=boxAlergias.getComponentCount()-1 ; i++)
            {
                PanelAlergia panelAlergia = (PanelAlergia)boxAlergias.getComponent(i);
                panelAlergia.establecerEditable(editable);
            }
            
            for(int i=0 ; i<=boxIntervenciones.getComponentCount()-1 ; i++)
            {
                PanelFechaYDosCampos panel = (PanelFechaYDosCampos)boxIntervenciones.getComponent(i);
                panel.establecerEditable(editable);
            }
            
            for(int i=0 ; i<=boxHospitalizaciones.getComponentCount()-1 ; i++)
            {
                PanelFechaYDosCampos panel = (PanelFechaYDosCampos)boxHospitalizaciones.getComponent(i);
                panel.establecerEditable(editable);
            }
            
            bEditar.setVisible(!editable);
            bAnadir.setVisible(editable && tabbedPanel.getSelectedIndex()!=0);
            bGuardar.setVisible(editable);
            bCancelar.setVisible(editable);
        }
        
        
        private void cargarDatos() {
            
            MapaDatos mapaAntecedentesNeonatales = (MapaDatos)Utilidades.leerArchivo(Directorios.ANTECEDENTES_NEONATALES+nroHistoria+".dda");
            
            if(mapaAntecedentesNeonatales!=null)
            {
                lTalla.setText(mapaAntecedentesNeonatales.get(Neonatales.KEY_TALLA));
                tTalla.setText(mapaAntecedentesNeonatales.get(Neonatales.KEY_TALLA));
                
                lPeso.setText(mapaAntecedentesNeonatales.get(Neonatales.KEY_PESO));
                tPeso.setText(mapaAntecedentesNeonatales.get(Neonatales.KEY_PESO));
                
                lC_Cefalica.setText(mapaAntecedentesNeonatales.get(Neonatales.KEY_C_CEFALICA));
                tC_Cefalica.setText(mapaAntecedentesNeonatales.get(Neonatales.KEY_C_CEFALICA));
                
                lC_Toracica.setText(mapaAntecedentesNeonatales.get(Neonatales.KEY_C_TORACICA));
                tC_Toracica.setText(mapaAntecedentesNeonatales.get(Neonatales.KEY_C_TORACICA));
                
                lC_Abdominal.setText(mapaAntecedentesNeonatales.get(Neonatales.KEY_C_ABDOMINAL));
                tC_Abdominal.setText(mapaAntecedentesNeonatales.get(Neonatales.KEY_C_ABDOMINAL));
            }
            
            //....
            
            boxPersonales.removeAll();
            
            HashMap<String,String> mapaAntecedentesPersonales = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_PERSONALES+nroHistoria+".dda");
            
            if(mapaAntecedentesPersonales!=null)
            {
                Iterator<String> iteradorKeys = Utilidades.obtenerIteradorStringOrdenado(mapaAntecedentesPersonales);
                
                while(iteradorKeys.hasNext())
                {
                    String patologia = iteradorKeys.next();
                    String detalles = mapaAntecedentesPersonales.get(patologia);
                    
                    PanelPatologia panelPatologia = new PanelPatologia();
                    panelPatologia.establecerPatologia(patologia);
                    panelPatologia.establecerDetalles(detalles);
                    panelPatologia.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelPatologia) );
                    boxPersonales.add(panelPatologia);
                }
            }
            
            boxPersonales.validate();
            
            scrollPersonales.validate();
            scrollPersonales.getVerticalScrollBar().setValue(scrollPersonales.getVerticalScrollBar().getMaximum());
            
            //....
            
            boxFamiliares.removeAll();
            
            HashMap<String,String> mapaAntecedentesFamiliares = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_FAMILIARES+nroHistoria+".dda");
            
            if(mapaAntecedentesFamiliares!=null)
            {
                Iterator<String> iteradorKeys = Utilidades.obtenerIteradorStringOrdenado(mapaAntecedentesFamiliares);
                
                while(iteradorKeys.hasNext())
                {
                    String patologia = iteradorKeys.next();
                    String detalles = mapaAntecedentesFamiliares.get(patologia);
                    
                    PanelPatologia panelPatologia = new PanelPatologia();
                    panelPatologia.establecerPatologia(patologia);
                    panelPatologia.establecerDetalles(detalles);
                    panelPatologia.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelPatologia) );
                    boxFamiliares.add(panelPatologia);
                }
            }
            
            boxFamiliares.validate();
            
            scrollFamiliares.validate();
            scrollFamiliares.getVerticalScrollBar().setValue(scrollFamiliares.getVerticalScrollBar().getMaximum());
            
            //....
            
            boxAlergias.removeAll();
            
            HashSet<String> conjuntoAlergias = (HashSet<String>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_ALERGIAS+nroHistoria+".dda");
            
            if(conjuntoAlergias!=null)
            {
                Iterator<String> iterador = Utilidades.obtenerIteradorStringOrdenado(conjuntoAlergias);
                
                while(iterador.hasNext())
                {
                    PanelAlergia panelAlergia = new PanelAlergia();
                    panelAlergia.establecerAlergia(iterador.next());
                    panelAlergia.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelAlergia) );
                    boxAlergias.add(panelAlergia);
                }
            }
            
            boxAlergias.validate();
            
            scrollAlergias.validate();
            scrollAlergias.getVerticalScrollBar().setValue(scrollAlergias.getVerticalScrollBar().getMaximum());
            
            //....
            
            boxIntervenciones.removeAll();
            
            HashMap<String,ArrayList<String>> mapaIntervenciones = (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_INTERVENCIONES+nroHistoria+".dda");
            
            if(mapaIntervenciones!=null)
            {
                Iterator<String> iteradorAux = mapaIntervenciones.keySet().iterator();
                
                ArrayList<String> arrayAux = new ArrayList<>(0);
                
                String tipoIntervencion;
                
                String[] fechaMesAno;
                
                while(iteradorAux.hasNext())
                {
                    tipoIntervencion = iteradorAux.next();
                    
                    fechaMesAno = mapaIntervenciones.get(tipoIntervencion).get(0).split("-");
                    
                    arrayAux.add(fechaMesAno[1]+"-"+fechaMesAno[0]+"_"+fechaMesAno[0]+"-"+fechaMesAno[1]+"_"+tipoIntervencion);
                }
                
                Iterator<String> iteradorCadena = Utilidades.obtenerIteradorStringOrdenado(arrayAux);
                
                String[] fechas_Y_tipo;
                
                String[] mesAno;
                
                while(iteradorCadena.hasNext())
                {
                    fechas_Y_tipo = iteradorCadena.next().split("_", 3);
                    
                    mesAno = fechas_Y_tipo[1].split("-");
                    
                    PanelFechaYDosCampos panelIntervencion = new PanelFechaYDosCampos(TEXTO_INHABILITADO_INTERVENCIONES);
                    panelIntervencion.establecerMesAno(mesAno);
                    panelIntervencion.establecerCampo1(fechas_Y_tipo[2]);
                    panelIntervencion.establecerCampo2(mapaIntervenciones.get(fechas_Y_tipo[2]).get(1));
                    panelIntervencion.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelIntervencion) );
                    boxIntervenciones.add(panelIntervencion);
                }
            }
            
            boxIntervenciones.validate();
            
            scrollIntervenciones.validate();
            scrollIntervenciones.getVerticalScrollBar().setValue(scrollIntervenciones.getVerticalScrollBar().getMaximum());
            
            //....
            
            boxHospitalizaciones.removeAll();
            
            HashMap<String,ArrayList<String>> mapaHospitalizaciones = (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_HOSPITALIZACIONES+nroHistoria+".dda");
            
            if(mapaHospitalizaciones!=null)
            {
                Iterator<String> iteradorAux = mapaHospitalizaciones.keySet().iterator();
                
                ArrayList<String> arrayAux = new ArrayList<>(0);
                
                String motivoHospitalizacion;
                
                String[] fechaMesAno;
                
                while(iteradorAux.hasNext())
                {
                    motivoHospitalizacion = iteradorAux.next();
                    
                    fechaMesAno = mapaHospitalizaciones.get(motivoHospitalizacion).get(0).split("-");
                    
                    arrayAux.add(fechaMesAno[1]+"-"+fechaMesAno[0]+"_"+fechaMesAno[0]+"-"+fechaMesAno[1]+"_"+motivoHospitalizacion);
                }
                
                Iterator<String> iteradorCadena = Utilidades.obtenerIteradorStringOrdenado(arrayAux);
                
                String[] fechas_Y_tipo;
                
                String[] mesAno;
                
                while(iteradorCadena.hasNext())
                {
                    fechas_Y_tipo = iteradorCadena.next().split("_", 3);
                    
                    mesAno = fechas_Y_tipo[1].split("-");
                    
                    PanelFechaYDosCampos panelHospitalizacion = new PanelFechaYDosCampos(TEXTO_INHABILITADO_HOSPITALIZACIONES);
                    panelHospitalizacion.establecerMesAno(mesAno);
                    panelHospitalizacion.establecerCampo1(fechas_Y_tipo[2]);
                    panelHospitalizacion.establecerCampo2(mapaHospitalizaciones.get(fechas_Y_tipo[2]).get(1));
                    panelHospitalizacion.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelHospitalizacion) );
                    boxHospitalizaciones.add(panelHospitalizacion);
                }
            }
            
            boxHospitalizaciones.validate();
            
            scrollHospitalizaciones.validate();
            scrollHospitalizaciones.getVerticalScrollBar().setValue(scrollHospitalizaciones.getVerticalScrollBar().getMaximum());
        }
        
        
        private void guardar() {
            
            if(estanAntecedentesListos()==false)  return;
            
            
            MapaDatos mapaAntecedentesNeonatales = new MapaDatos();
            if(tTalla.esTextoValido())  mapaAntecedentesNeonatales.put(Neonatales.KEY_TALLA, tTalla.getText());
            if(tPeso.esTextoValido())  mapaAntecedentesNeonatales.put(Neonatales.KEY_PESO, tPeso.getText());
            if(tC_Cefalica.esTextoValido())  mapaAntecedentesNeonatales.put(Neonatales.KEY_C_CEFALICA, tC_Cefalica.getText());
            if(tC_Toracica.esTextoValido())  mapaAntecedentesNeonatales.put(Neonatales.KEY_C_TORACICA, tC_Toracica.getText());
            if(tC_Abdominal.esTextoValido())  mapaAntecedentesNeonatales.put(Neonatales.KEY_C_ABDOMINAL, tC_Abdominal.getText());
            
            if(mapaAntecedentesNeonatales.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_NEONATALES+nroHistoria+".dda", mapaAntecedentesNeonatales);
            else
                Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_NEONATALES+nroHistoria+".dda");
            
            
            HashMap<String,String> mapaAntecedentesPersonales = new HashMap<>(0);
            
            for(int i=0 ; i<=boxPersonales.getComponentCount()-1 ; i++)
            {
                PanelPatologia panelPatologia = (PanelPatologia)boxPersonales.getComponent(i);
                
                mapaAntecedentesPersonales.put(panelPatologia.obtenerPatologia(), panelPatologia.obtenerDetalles());
            }
            
            if(mapaAntecedentesPersonales.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_PERSONALES+nroHistoria+".dda", mapaAntecedentesPersonales);
            else
                Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_PERSONALES+nroHistoria+".dda");
            
            
            HashMap<String,String> mapaAntecedentesFamiliares = new HashMap<>(0);
            
            for(int i=0 ; i<=boxFamiliares.getComponentCount()-1 ; i++)
            {
                PanelPatologia panelPatologia = (PanelPatologia)boxFamiliares.getComponent(i);
                
                mapaAntecedentesFamiliares.put(panelPatologia.obtenerPatologia(), panelPatologia.obtenerDetalles());
            }
            
            if(mapaAntecedentesFamiliares.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_FAMILIARES+nroHistoria+".dda", mapaAntecedentesFamiliares);
            else
                Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_FAMILIARES+nroHistoria+".dda");
            
            
            HashSet<String> mapaAlergias = new HashSet<>(0);
            
            for(int i=0 ; i<=boxAlergias.getComponentCount()-1 ; i++)
            {
                PanelAlergia panelAlergia = (PanelAlergia)boxAlergias.getComponent(i);
                
                mapaAlergias.add(panelAlergia.obtenerAlergia());
            }
            
            if(mapaAlergias.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_ALERGIAS+nroHistoria+".dda", mapaAlergias);
            else
                Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_ALERGIAS+nroHistoria+".dda");
            
            
            HashMap<String,ArrayList<String>> mapaIntervenciones = new HashMap<>(0);
            
            for(int i=0 ; i<=boxIntervenciones.getComponentCount()-1 ; i++)
            {
                PanelFechaYDosCampos panelIntervencion = (PanelFechaYDosCampos)boxIntervenciones.getComponent(i);
                
                ArrayList<String> array = new ArrayList<>(0);
                array.add(panelIntervencion.obtenerMesAno());
                array.add(panelIntervencion.obtenerCampo2());
                
                mapaIntervenciones.put(panelIntervencion.obtenerCampo1(), array);
            }
            
            if(mapaIntervenciones.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_INTERVENCIONES+nroHistoria+".dda", mapaIntervenciones);
            else
                Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_INTERVENCIONES+nroHistoria+".dda");
            
            
            HashMap<String,ArrayList<String>> mapaHospitalizaciones = new HashMap<>(0);
            
            for(int i=0 ; i<=boxHospitalizaciones.getComponentCount()-1 ; i++)
            {
                PanelFechaYDosCampos panelHospitalizacion = (PanelFechaYDosCampos)boxHospitalizaciones.getComponent(i);
                
                ArrayList<String> array = new ArrayList<>(0);
                array.add(panelHospitalizacion.obtenerMesAno());
                array.add(panelHospitalizacion.obtenerCampo2());
                
                mapaHospitalizaciones.put(panelHospitalizacion.obtenerCampo1(), array);
            }
            
            if(mapaHospitalizaciones.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_HOSPITALIZACIONES+nroHistoria+".dda", mapaHospitalizaciones);
            else
                Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_HOSPITALIZACIONES+nroHistoria+".dda");
            
            
            cargarDatos();
            
            establecerEditable(false);
        }
        
        
        private boolean estanAntecedentesListos() {
            
            if(tTalla.esNumerico()==false || tPeso.esNumerico()==false || tC_Cefalica.esNumerico()==false || tC_Toracica.esNumerico()==false || tC_Abdominal.esNumerico()==false)
            {
                HJDialog.mostrarMensaje("Error al Ingresar Datos en 'Antecedentes Neonatales'", new String[] {"Los valores introducidos en 'Antecedentes Neonatales' deben ser numéricos (enteros o con decimales).", "Utilice el punto (.) para separar decimales.", "(Ejemplo: 32 o 32.4)"}, IconoDeImagen.ADVERTENCIA, null);
                
                tabbedPanel.setSelectedIndex(0);
                
                return false;
            }
            
            if(estanPanelesDeBoxListos(boxPersonales)==false)
            {
                HJDialog.mostrarMensaje("Datos Incompletos en 'Antecedentes Personales'", new String[] {"Hay alguna(s) casilla(s) sin completar en 'Antecedentes Personales'.", "Por favor, complete al menos el campo 'Patología' o elimine el antecedente."}, IconoDeImagen.ADVERTENCIA, null);
                
                tabbedPanel.setSelectedIndex(1);
                
                return false;
            }
            
            if(estanPanelesDeBoxListos(boxFamiliares)==false)
            {
                HJDialog.mostrarMensaje("Datos Incompletos en 'Antecedentes Familiares'", new String[] {"Hay alguna(s) casilla(s) sin completar en 'Antecedentes Familiares'.", "Por favor, complete al menos el campo 'Patología' o elimine el antecedente."}, IconoDeImagen.ADVERTENCIA, null);
                
                tabbedPanel.setSelectedIndex(2);
                
                return false;
            }
            
            if(estanPanelesDeBoxListos(boxAlergias)==false)
            {
                HJDialog.mostrarMensaje("Datos Incompletos en 'Alergias'", new String[] {"Hay alguna(s) casilla(s) sin completar en 'Alergias'.", "Por favor, complétela(s) o elimínela(s)."}, IconoDeImagen.ADVERTENCIA, null);
                
                tabbedPanel.setSelectedIndex(3);
                
                return false;
            }
            
            if(estanPanelesDeBoxListos(boxIntervenciones)==false)
            {
                HJDialog.mostrarMensaje("Datos Incompletos en 'Intervenciones Quirúrgicas'", new String[] {"Hay alguna(s) casilla(s) sin completar en 'Intervenciones Quirúrgicas'.", "Por favor, complete al menos el campo 'Fecha' y el campo 'Tipo' o elimine el antecedente."}, IconoDeImagen.ADVERTENCIA, null);
                
                tabbedPanel.setSelectedIndex(4);
                
                return false;
            }
            
            if(estanPanelesDeBoxListos(boxHospitalizaciones)==false)
            {
                HJDialog.mostrarMensaje("Datos Incompletos en 'Hospitalizaciones'", new String[] {"Hay alguna(s) casilla(s) sin completar en 'Hospitalizaciones'.", "Por favor, complete al menos el campo 'Fecha' y el campo 'Motivo' o elimine el antecedente."}, IconoDeImagen.ADVERTENCIA, null);
                
                tabbedPanel.setSelectedIndex(5);
                
                return false;
            }
            
            
            return true;
        }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
