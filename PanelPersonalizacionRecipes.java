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
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PanelPersonalizacionRecipes extends HJDialog {
    
    public static final String NOMBRE_LOGO = "Nombre Logo";
    private String nombreLogo;
    private final String LOGO_POR_DEFECTO_AZUL = "LogoPorDefectoAzul.png";
    private final String LOGO_POR_DEFECTO_GRIS = "LogoPorDefectoGris.png";
    private final String LOGO_POR_DEFECTO_MORADO = "LogoPorDefectoMorado.png";
    private final String LOGO_POR_DEFECTO_NARANJA = "LogoPorDefectoNaranja.png";
    private final String LOGO_POR_DEFECTO_NEGRO = "LogoPorDefectoNegro.png";
    private final String LOGO_POR_DEFECTO_ROJO = "LogoPorDefectoRojo.png";
    private final String LOGO_POR_DEFECTO_ROSADO = "LogoPorDefectoRosado.png";
    private final String LOGO_POR_DEFECTO_VERDE = "LogoPorDefectoVerde.png";
    private final String LOGO_ABANICO = "LogoAbanico.png";
    private final String LOGO_PERSONALIZADO = "LogoPersonalizado.png";
    
    public static final String FAMILY_NOMBRE_COMPLETO = "Family Nombre Completo";
    public static final String STYLE_NOMBRE_COMPLETO = "Style Nombre Completo";
    public static final String SIZE_NOMBRE_COMPLETO = "Size Nombre Completo";
    public static final String FAMILY_ESPECIALIDAD = "Family Especialidad";
    public static final String STYLE_ESPECIALIDAD = "Style Especialidad";
    public static final String SIZE_ESPECIALIDAD = "Size Especialidad";
    public static final String LINEA_INFO_1 = "Línea Info. 1";
    public static final String LINEA_INFO_2 = "Línea Info. 2";
    public static final String LINEA_INFO_3 = "Línea Info. 3";
    public static final String LINEA_INFO_4 = "Línea Info. 4";
    public static final String RED_ENCABEZADO = "Red Encabezado";
    public static final String GREEN_ENCABEZADO = "Green Encabezado";
    public static final String BLUE_ENCABEZADO = "Blue Encabezado";
    public static final String CON_LINEA_DIVISORIA = "Con Línea Divisoria";
    private HJLabel lNombreCompleto;
    private String familyNombreCompleto;
    private final String LUCIDA_CALLIGRAPHY = DocAssistant.LUCIDA_CALLIGRAPHY;
    private final String TIMES_NEW_ROMAN = DocAssistant.TIMES_NEW_ROMAN;
    private int styleNombreCompleto;
    private int sizeNombreCompleto;
    private HJLabel lEspecialidad;
    private String familyEspecialidad;
    private final String COPPERPLATE_GOTHIC_LIGHT = DocAssistant.COPPERPLATE_GOTHIC_LIGHT;
    private final String ARIAL = DocAssistant.ARIAL;
    private int styleEspecialidad;
    private int sizeEspecialidad;
    private String linea_info_1;
    private String linea_info_2;
    private String linea_info_3;
    private String linea_info_4;
    private HJTextField tLinea_1;
    private HJTextField tLinea_2;
    private HJTextField tLinea_3;
    private HJTextField tLinea_4;
    private int redEncabezado;
    private int greenEncabezado;
    private int blueEncabezado;
    private PanelSelectorColor panelSelectorColorEncabezado = null;
    private boolean conLineaDivisoria;
    private final HJCheckBox chbLineaDivisoria;
    
    public static final String FAMILY_CONTENIDO = "Family Contenido";
    public static final String STYLE_CONTENIDO = "Style Contenido";
    public static final String SIZE_CONTENIDO = "Size Contenido";
    public static final String RED_CONTENIDO = "Red Contenido";
    public static final String GREEN_CONTENIDO = "Green Contenido";
    public static final String BLUE_CONTENIDO = "Blue Contenido";
    public static final String CON_MARCO = "Con Marco";
    public static final String ES_PEDIATRICO = "Es Pediátrico";
    private HJLabel lContenido;
    private String familyContenido;
    private int styleContenido;
    private int sizeContenido;
    private int redContenido;
    private int greenContenido;
    private int blueContenido;
    private PanelSelectorColor panelSelectorColorContenido = null;
    private boolean conMarco;
    private final HJCheckBox chbMarco;
    private boolean esPediatrico;
    private final HJCheckBox chbPediatrico;
    
    
    public PanelPersonalizacionRecipes() {
        
        super(new IconoDeImagen("PersonalizarRecipes.png", -1, 30), "Personalización de Récipes", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> cancelar() );
        
        
        //.........................
        
        int redTextoDefecto = 0;
        int greenTextoDefecto = 0;
        int blueTextoDefecto = 0;
        
        
        nombreLogo = LOGO_POR_DEFECTO_NEGRO;
        
        familyNombreCompleto = LUCIDA_CALLIGRAPHY;
        styleNombreCompleto = Font.PLAIN;
        sizeNombreCompleto = 14;
        familyEspecialidad = COPPERPLATE_GOTHIC_LIGHT;
        styleEspecialidad = Font.BOLD;
        sizeEspecialidad = 10;
        linea_info_1 = null;
        linea_info_2 = null;
        linea_info_3 = null;
        linea_info_4 = null;
        redEncabezado = redTextoDefecto;
        greenEncabezado = greenTextoDefecto;
        blueEncabezado = blueTextoDefecto;
        conLineaDivisoria = Boolean.valueOf("true");
        
        familyContenido = ARIAL;
        styleContenido = Font.PLAIN;
        sizeContenido = 12;
        redContenido = redTextoDefecto;
        greenContenido = greenTextoDefecto;
        blueContenido = blueTextoDefecto;
        conMarco = Boolean.valueOf("true");
        esPediatrico = Boolean.valueOf("false");
        
        
        if((new File(Directorios.CONFIGURACION+"ConfigRecipes.dda")).exists())
        {
            MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"ConfigRecipes.dda");
            
            nombreLogo = mapaDatos.get(NOMBRE_LOGO);
            
            familyNombreCompleto = mapaDatos.get(FAMILY_NOMBRE_COMPLETO);
            styleNombreCompleto = Integer.parseInt(mapaDatos.get(STYLE_NOMBRE_COMPLETO));
            sizeNombreCompleto = Integer.parseInt(mapaDatos.get(SIZE_NOMBRE_COMPLETO));
            familyEspecialidad = mapaDatos.get(FAMILY_ESPECIALIDAD);
            styleEspecialidad = Integer.parseInt(mapaDatos.get(STYLE_ESPECIALIDAD));
            sizeEspecialidad = Integer.parseInt(mapaDatos.get(SIZE_ESPECIALIDAD));
            linea_info_1 = mapaDatos.get(LINEA_INFO_1);
            linea_info_2 = mapaDatos.get(LINEA_INFO_2);
            linea_info_3 = mapaDatos.get(LINEA_INFO_3);
            linea_info_4 = mapaDatos.get(LINEA_INFO_4);
            redEncabezado = Integer.parseInt(mapaDatos.get(RED_ENCABEZADO));
            greenEncabezado = Integer.parseInt(mapaDatos.get(GREEN_ENCABEZADO));
            blueEncabezado = Integer.parseInt(mapaDatos.get(BLUE_ENCABEZADO));
            conLineaDivisoria = Boolean.valueOf(mapaDatos.get(CON_LINEA_DIVISORIA));
            
            familyContenido = mapaDatos.get(FAMILY_CONTENIDO);
            styleContenido = Integer.parseInt(mapaDatos.get(STYLE_CONTENIDO));
            sizeContenido = Integer.parseInt(mapaDatos.get(SIZE_CONTENIDO));
            redContenido = Integer.parseInt(mapaDatos.get(RED_CONTENIDO));
            greenContenido = Integer.parseInt(mapaDatos.get(GREEN_CONTENIDO));
            blueContenido = Integer.parseInt(mapaDatos.get(BLUE_CONTENIDO));
            conMarco = Boolean.valueOf(mapaDatos.get(CON_MARCO));
            esPediatrico = Boolean.valueOf(mapaDatos.get(ES_PEDIATRICO));
        }
        
        
        //........................
        
        Font formatoDivisores = new Font("Arial", Font.BOLD, 18);
        Color colorTextoDivisores = Colores.TEXTO;
        Color colorLineaDivisores = Colores.LINEAS_OSCURAS;
        
        
        //............
        
        HJDividingPanel divisorEncabezado = new HJDividingPanel("Encabezado", formatoDivisores, colorTextoDivisores, HJDividingPanel.IZQUIERDA, 50, colorLineaDivisores, 1100, 4);
        
        
        //....
        
        HJLabel lLogo = new HJLabel(new ImageIcon((new ImageIcon(Directorios.RECURSOS+nombreLogo)).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
        
        
        HJButton bLogoPorDefecto = new HJButton(null, "Por defecto", Colores.COLORES_BOTONES);
        
        
        ArrayList<String> arrayLogosPorDefecto = new ArrayList<>(0);
        arrayLogosPorDefecto.add(LOGO_POR_DEFECTO_AZUL);
        arrayLogosPorDefecto.add(LOGO_POR_DEFECTO_GRIS);
        arrayLogosPorDefecto.add(LOGO_POR_DEFECTO_MORADO);
        arrayLogosPorDefecto.add(LOGO_POR_DEFECTO_NARANJA);
        arrayLogosPorDefecto.add(LOGO_POR_DEFECTO_NEGRO);
        arrayLogosPorDefecto.add(LOGO_POR_DEFECTO_ROJO);
        arrayLogosPorDefecto.add(LOGO_POR_DEFECTO_ROSADO);
        arrayLogosPorDefecto.add(LOGO_POR_DEFECTO_VERDE);
        
        String[] coloresLogo = {"Azul", "Gris", "Morado", "Naranja", "Negro", "Rojo", "Rosado", "Verde"};
        
        HJComboBox<String> cbColoresLogo = new HJComboBox<>(coloresLogo);
        if(nombreLogo.equals(LOGO_ABANICO)==false && nombreLogo.equals(LOGO_PERSONALIZADO)==false)
            cbColoresLogo.setSelectedItem(coloresLogo[arrayLogosPorDefecto.indexOf(nombreLogo)]);
        cbColoresLogo.addItemListener( e -> {
            
            String nombreAnterior = nombreLogo;
            
            nombreLogo = arrayLogosPorDefecto.get(cbColoresLogo.getSelectedIndex());
            
            if((nombreAnterior.equals(LOGO_POR_DEFECTO_NEGRO) || nombreAnterior.equals(LOGO_POR_DEFECTO_GRIS) || nombreAnterior.equals(LOGO_ABANICO) || nombreAnterior.equals(LOGO_PERSONALIZADO)) && (nombreLogo.equals(LOGO_POR_DEFECTO_NEGRO)==false && nombreLogo.equals(LOGO_POR_DEFECTO_GRIS)==false))
                HJDialog.mostrarMensaje("Recomendación", new String[] {"Para impresiones más rápidas se recomienda establecer el logo por defecto negro o gris."}, IconoDeImagen.INFORMACION, null);
            
            lLogo.setIcon(new ImageIcon((new ImageIcon(Directorios.RECURSOS+nombreLogo)).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
        });
        
        
        bLogoPorDefecto.addActionListener( e -> {
            
            cbColoresLogo.setSelectedItem("Negro");
            
            nombreLogo = LOGO_POR_DEFECTO_NEGRO;
            lLogo.setIcon(new ImageIcon((new ImageIcon(Directorios.RECURSOS+nombreLogo)).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
        });
        
        
        JPanel panelLogoPorDefecto = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelLogoPorDefecto.add(bLogoPorDefecto);
        panelLogoPorDefecto.add(Box.createHorizontalStrut(10));
        panelLogoPorDefecto.add(cbColoresLogo);
        panelLogoPorDefecto.setOpaque(false);
        
        
        HJButton bLogoAbanico = new HJButton(null, "Logo Abanico", Colores.COLORES_BOTONES);
        bLogoAbanico.addActionListener( e -> {
            
            HJDialog.mostrarMensaje("Recomendación", new String[] {"Para impresiones más rápidas se recomienda establecer el logo por defecto negro o gris."}, IconoDeImagen.INFORMACION, null);
            
            nombreLogo = LOGO_ABANICO;
            lLogo.setIcon(new ImageIcon((new ImageIcon(Directorios.RECURSOS+nombreLogo)).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
        });
        
        JPanel panelBotonLogoAbanico = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelBotonLogoAbanico.add(bLogoAbanico);
        panelBotonLogoAbanico.setOpaque(false);
        
        
        HJButton bExaminar = new HJButton(null, "Examinar", Colores.COLORES_BOTONES);
        bExaminar.addActionListener( e -> {
            
            HJDialog.mostrarMensaje("Recomendación", new String[] {"Para impresiones más rápidas se recomienda establecer el logo por defecto negro o gris."}, IconoDeImagen.INFORMACION, null);
            
            setIconImage(new IconoDeImagen("Abrir.png", -1, 16).getImage());
            
            HJFileChooser selector = new HJFileChooser(null, HJFileChooser.IMAGENES);
            
            if(selector.showOpenDialog(obtenerEstePanel())==HJFileChooser.APPROVE_OPTION)
            {
                Image imagen = null;
                
                try{
                    imagen = ImageIO.read(selector.getSelectedFile());
                }catch(IOException ioe) {
                    Toolkit.getDefaultToolkit().beep();
                    HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error al cargar la imagen."}, IconoDeImagen.ERROR, null);
                }
                
                if(imagen==null)
                {
                    HJDialog.mostrarMensaje("Error", new String[] {"Imagen nula."}, IconoDeImagen.ERROR, null);
                    
                    return;
                }
                
                guardarLogo(new ImageIcon(imagen.getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
                
                nombreLogo = LOGO_PERSONALIZADO;
                lLogo.setIcon(new ImageIcon((new ImageIcon(Directorios.RECURSOS+nombreLogo)).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
                
            }
            
            setIconImage(null);
        });
        
        JPanel panelBotonExaminar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelBotonExaminar.add(bExaminar);
        panelBotonExaminar.setOpaque(false);
        
        
        Box boxBotonesLogo = Box.createVerticalBox();
        boxBotonesLogo.add(panelLogoPorDefecto);
        boxBotonesLogo.add(Box.createVerticalStrut(10));
        boxBotonesLogo.add(panelBotonLogoAbanico);
        boxBotonesLogo.add(Box.createVerticalStrut(10));
        boxBotonesLogo.add(panelBotonExaminar);
        
        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLogo.add(new HJLabel("Logo:"));
        panelLogo.add(Box.createHorizontalStrut(25));
        panelLogo.add(lLogo);
        panelLogo.add(Box.createHorizontalStrut(25));
        panelLogo.add(boxBotonesLogo);
        panelLogo.setOpaque(false);
        
        
        //....
        
        Color colorInicialEncabezado = new Color(redEncabezado, greenEncabezado, blueEncabezado);
        
        MapaDatos mapaDatosDoctor = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        
        lNombreCompleto = new HJLabel(mapaDatosDoctor.get(PanelDatosDoctor.TITULO)+" "+mapaDatosDoctor.get(PanelDatosDoctor.NOMBRE_COMPLETO));
        lNombreCompleto.setForeground(colorInicialEncabezado);
        lNombreCompleto.setPreferredSize(new Dimension(315, 28));
        lNombreCompleto.setHorizontalAlignment(HJLabel.CENTER);
        
        
        String[] familiasDisponibles = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        
        String[] familiasDisponiblesListos = new String[familiasDisponibles.length+1];
        familiasDisponiblesListos[0] = "Seleccione...";
        for(int i=0 ; i<=familiasDisponibles.length-1 ; i++)
        {
            familiasDisponiblesListos[i+1] = familiasDisponibles[i];
        }
        
        HJComboBox<String> cbFormatoNombreCompleto = new HJComboBox<>(familiasDisponiblesListos);
        cbFormatoNombreCompleto.addItemListener( e -> {
            
            if(cbFormatoNombreCompleto.getSelectedIndex()>0)
                lNombreCompleto.setFont(new Font(""+cbFormatoNombreCompleto.getSelectedItem(), lNombreCompleto.getFont().getStyle(), lNombreCompleto.getFont().getSize()));
        });
        
        
        Integer[] tamanos = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        
        HJComboBox<Integer> cbTamanoNombreCompleto = new HJComboBox<>(tamanos);
        cbTamanoNombreCompleto.addItemListener( e -> lNombreCompleto.setFont(new Font(lNombreCompleto.getFont().getFamily(), lNombreCompleto.getFont().getStyle(), (int)cbTamanoNombreCompleto.getSelectedItem())) );
        
        
        HJCheckBox chbNegritaNombreCompleto = new HJCheckBox("Negrita");
        chbNegritaNombreCompleto.addActionListener( e -> {
            
            if(chbNegritaNombreCompleto.isSelected())
                lNombreCompleto.setFont(new Font(lNombreCompleto.getFont().getFamily(), lNombreCompleto.getFont().getStyle()+Font.BOLD, lNombreCompleto.getFont().getSize()));
            else
                lNombreCompleto.setFont(new Font(lNombreCompleto.getFont().getFamily(), lNombreCompleto.getFont().getStyle()-Font.BOLD, lNombreCompleto.getFont().getSize()));
        });
        
        
        HJCheckBox chbItalicaNombreCompleto = new HJCheckBox("Itálica");
        chbItalicaNombreCompleto.setFont(new Font("Arial", Font.ITALIC, 14));
        chbItalicaNombreCompleto.addActionListener( e -> {
            
            if(chbItalicaNombreCompleto.isSelected())
                lNombreCompleto.setFont(new Font(lNombreCompleto.getFont().getFamily(), lNombreCompleto.getFont().getStyle()+Font.ITALIC, lNombreCompleto.getFont().getSize()));
            else
                lNombreCompleto.setFont(new Font(lNombreCompleto.getFont().getFamily(), lNombreCompleto.getFont().getStyle()-Font.ITALIC, lNombreCompleto.getFont().getSize()));
        });
        
        
        if(Utilidades.estaDisponibleFamilia(familyNombreCompleto))
        {
            lNombreCompleto.setFont(new Font(familyNombreCompleto, styleNombreCompleto, sizeNombreCompleto));
            cbFormatoNombreCompleto.setSelectedItem(familyNombreCompleto);
        }else{
            lNombreCompleto.setFont(new Font(TIMES_NEW_ROMAN, styleNombreCompleto, sizeNombreCompleto));
            cbFormatoNombreCompleto.setSelectedItem(TIMES_NEW_ROMAN);
        }
        cbTamanoNombreCompleto.setSelectedItem(sizeNombreCompleto);
        chbNegritaNombreCompleto.setSelected(styleNombreCompleto==Font.BOLD || styleNombreCompleto==Font.BOLD+Font.ITALIC);
        chbItalicaNombreCompleto.setSelected(styleNombreCompleto==Font.ITALIC || styleNombreCompleto==Font.BOLD+Font.ITALIC);
        
          
        HJButton bPorDefectoNombreCompleto = new HJButton(null, "Por defecto", Colores.COLORES_BOTONES);
        bPorDefectoNombreCompleto.addActionListener( e -> {
            
            chbNegritaNombreCompleto.setSelected(false);
            chbItalicaNombreCompleto.setSelected(false);
            
            cbTamanoNombreCompleto.setSelectedItem(14);
            
            if(DocAssistant.seRegistroLucida())
            {
                cbFormatoNombreCompleto.setSelectedItem(LUCIDA_CALLIGRAPHY);
                lNombreCompleto.setFont(new Font(LUCIDA_CALLIGRAPHY, Font.PLAIN, 14));
            }else{
                cbFormatoNombreCompleto.setSelectedItem(TIMES_NEW_ROMAN);
                lNombreCompleto.setFont(new Font(TIMES_NEW_ROMAN, Font.PLAIN, 14));
            }
        });
        
        
        JPanel panelNombreCompleto = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelNombreCompleto.add(Box.createHorizontalStrut(30));
        panelNombreCompleto.add(new HJLabel("Título-Nombre:"));
        panelNombreCompleto.add(Box.createHorizontalStrut(25));
        panelNombreCompleto.add(lNombreCompleto);
        panelNombreCompleto.add(Box.createHorizontalStrut(25));
        panelNombreCompleto.add(cbFormatoNombreCompleto);
        panelNombreCompleto.add(Box.createHorizontalStrut(15));
        panelNombreCompleto.add(cbTamanoNombreCompleto);
        panelNombreCompleto.add(Box.createHorizontalStrut(15));
        panelNombreCompleto.add(chbNegritaNombreCompleto);
        panelNombreCompleto.add(Box.createHorizontalStrut(15));
        panelNombreCompleto.add(chbItalicaNombreCompleto);
        panelNombreCompleto.add(Box.createHorizontalStrut(15));
        panelNombreCompleto.add(bPorDefectoNombreCompleto);
        panelNombreCompleto.add(Box.createHorizontalStrut(30));
        panelNombreCompleto.setOpaque(false);
        
        
        //....
        
        lEspecialidad = new HJLabel(mapaDatosDoctor.get(PanelDatosDoctor.ESPECIALIDAD));
        lEspecialidad.setForeground(colorInicialEncabezado);
        lEspecialidad.setPreferredSize(new Dimension(315, 28));
        lEspecialidad.setHorizontalAlignment(HJLabel.CENTER);
        
        
        HJComboBox<String> cbFormatoEspecialidad = new HJComboBox<>(familiasDisponiblesListos);
        cbFormatoEspecialidad.addItemListener( e -> {
            
            if(cbFormatoEspecialidad.getSelectedIndex()>0)
                lEspecialidad.setFont(new Font(""+cbFormatoEspecialidad.getSelectedItem(), lEspecialidad.getFont().getStyle(), lEspecialidad.getFont().getSize()));
        });
        
        
        HJComboBox<Integer> cbTamanoEspecialidad = new HJComboBox<>(tamanos);
        cbTamanoEspecialidad.addItemListener( e -> lEspecialidad.setFont(new Font(lEspecialidad.getFont().getFamily(), lEspecialidad.getFont().getStyle(), (int)cbTamanoEspecialidad.getSelectedItem())) );
        
        
        HJCheckBox chbNegritaEspecialidad = new HJCheckBox("Negrita");
        chbNegritaEspecialidad.addActionListener( e -> {
            
            if(chbNegritaEspecialidad.isSelected())
                lEspecialidad.setFont(new Font(lEspecialidad.getFont().getFamily(), lEspecialidad.getFont().getStyle()+Font.BOLD, lEspecialidad.getFont().getSize()));
            else
                lEspecialidad.setFont(new Font(lEspecialidad.getFont().getFamily(), lEspecialidad.getFont().getStyle()-Font.BOLD, lEspecialidad.getFont().getSize()));
        });
        
        
        HJCheckBox chbItalicaEspecialidad = new HJCheckBox("Itálica");
        chbItalicaEspecialidad.setFont(new Font("Arial", Font.ITALIC, 14));
        chbItalicaEspecialidad.addActionListener( e -> {
            
            if(chbItalicaEspecialidad.isSelected())
                lEspecialidad.setFont(new Font(lEspecialidad.getFont().getFamily(), lEspecialidad.getFont().getStyle()+Font.ITALIC, lEspecialidad.getFont().getSize()));
            else
                lEspecialidad.setFont(new Font(lEspecialidad.getFont().getFamily(), lEspecialidad.getFont().getStyle()-Font.ITALIC, lEspecialidad.getFont().getSize()));
        });
        
        
        if(Utilidades.estaDisponibleFamilia(familyEspecialidad))
        {
            lEspecialidad.setFont(new Font(familyEspecialidad, styleEspecialidad, sizeEspecialidad));
            cbFormatoEspecialidad.setSelectedItem(familyEspecialidad);
        }else{
            lEspecialidad.setFont(new Font(ARIAL, styleEspecialidad, styleEspecialidad));
            cbFormatoEspecialidad.setSelectedItem(ARIAL);
        }
        cbTamanoEspecialidad.setSelectedItem(sizeEspecialidad);
        chbNegritaEspecialidad.setSelected(styleEspecialidad==Font.BOLD || styleEspecialidad==Font.BOLD+Font.ITALIC);
        chbItalicaEspecialidad.setSelected(styleEspecialidad==Font.ITALIC || styleEspecialidad==Font.BOLD+Font.ITALIC);
        
        
        HJButton bPorDefectoEspecialidad = new HJButton(null, "Por defecto", Colores.COLORES_BOTONES);
        bPorDefectoEspecialidad.addActionListener( e -> {
            
            chbNegritaEspecialidad.setSelected(true);
            chbItalicaEspecialidad.setSelected(false);
            
            if(DocAssistant.seRegistroCopperplate())
            {
                cbFormatoEspecialidad.setSelectedItem(COPPERPLATE_GOTHIC_LIGHT);
                cbTamanoEspecialidad.setSelectedItem(10);
                lEspecialidad.setFont(new Font(COPPERPLATE_GOTHIC_LIGHT, Font.BOLD, 10));
            }else{
                cbFormatoEspecialidad.setSelectedItem(ARIAL);
                cbTamanoEspecialidad.setSelectedItem(12);
                lEspecialidad.setFont(new Font(ARIAL, Font.BOLD, 12));
            }
        });
        
        
        JPanel panelEspecialidad = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelEspecialidad.add(Box.createHorizontalStrut(30));
        panelEspecialidad.add(new HJLabel("Especialidad:"));
        panelEspecialidad.add(Box.createHorizontalStrut(25));
        panelEspecialidad.add(lEspecialidad);
        panelEspecialidad.add(Box.createHorizontalStrut(25));
        panelEspecialidad.add(cbFormatoEspecialidad);
        panelEspecialidad.add(Box.createHorizontalStrut(15));
        panelEspecialidad.add(cbTamanoEspecialidad);
        panelEspecialidad.add(Box.createHorizontalStrut(15));
        panelEspecialidad.add(chbNegritaEspecialidad);
        panelEspecialidad.add(Box.createHorizontalStrut(15));
        panelEspecialidad.add(chbItalicaEspecialidad);
        panelEspecialidad.add(Box.createHorizontalStrut(15));
        panelEspecialidad.add(bPorDefectoEspecialidad);
        panelEspecialidad.add(Box.createHorizontalStrut(30));
        panelEspecialidad.setOpaque(false);
        
        
        //....
        
        tLinea_1 = new HJTextField(42);
        tLinea_1.setText(linea_info_1);
        tLinea_1.setForeground(colorInicialEncabezado);
        
        HJLabel lCaracteresRestantes_1 = new HJLabel(""+(60-tLinea_1.getText().length()));
        
        tLinea_1.addCaretListener( e -> {
            
            lCaracteresRestantes_1.setText(""+(60-tLinea_1.getText().length()));
            
            if(tLinea_1.getText().length()>60)  lCaracteresRestantes_1.setForeground(Color.red);
            else  lCaracteresRestantes_1.setForeground(Colores.TEXTO);
        });
        
        JPanel panelLinea_1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelLinea_1.add(new HJLabel("Línea de información 1:"));
        panelLinea_1.add(Box.createHorizontalStrut(5));
        panelLinea_1.add(tLinea_1);
        panelLinea_1.add(Box.createHorizontalStrut(5));
        panelLinea_1.add(lCaracteresRestantes_1);
        panelLinea_1.setOpaque(false);
        
        
        tLinea_2 = new HJTextField(42);
        tLinea_2.setText(linea_info_2);
        tLinea_2.setForeground(colorInicialEncabezado);
        
        HJLabel lCaracteresRestantes_2 = new HJLabel(""+(60-tLinea_2.getText().length()));
        
        tLinea_2.addCaretListener( e -> {
            
            lCaracteresRestantes_2.setText(""+(60-tLinea_2.getText().length()));
            
            if(tLinea_2.getText().length()>60)  lCaracteresRestantes_2.setForeground(Color.red);
            else  lCaracteresRestantes_2.setForeground(Colores.TEXTO);
        });
        
        JPanel panelLinea_2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelLinea_2.add(new HJLabel("Línea de información 2:"));
        panelLinea_2.add(Box.createHorizontalStrut(5));
        panelLinea_2.add(tLinea_2);
        panelLinea_2.add(Box.createHorizontalStrut(5));
        panelLinea_2.add(lCaracteresRestantes_2);
        panelLinea_2.setOpaque(false);
        
        
        tLinea_3 = new HJTextField(42);
        tLinea_3.setText(linea_info_3);
        tLinea_3.setForeground(colorInicialEncabezado);
        
        HJLabel lCaracteresRestantes_3 = new HJLabel(""+(60-tLinea_3.getText().length()));
        
        tLinea_3.addCaretListener( e -> {
            
            lCaracteresRestantes_3.setText(""+(60-tLinea_3.getText().length()));
            
            if(tLinea_3.getText().length()>60)  lCaracteresRestantes_3.setForeground(Color.red);
            else  lCaracteresRestantes_3.setForeground(Colores.TEXTO);
        });
        
        JPanel panelLinea_3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelLinea_3.add(new HJLabel("Línea de información 3:"));
        panelLinea_3.add(Box.createHorizontalStrut(5));
        panelLinea_3.add(tLinea_3);
        panelLinea_3.add(Box.createHorizontalStrut(5));
        panelLinea_3.add(lCaracteresRestantes_3);
        panelLinea_3.setOpaque(false);
        
        
        tLinea_4 = new HJTextField(42);
        tLinea_4.setText(linea_info_4);
        tLinea_4.setForeground(colorInicialEncabezado);
        
        HJLabel lCaracteresRestantes_4 = new HJLabel(""+(60-tLinea_4.getText().length()));
        
        tLinea_4.addCaretListener( e -> {
            
            lCaracteresRestantes_4.setText(""+(60-tLinea_4.getText().length()));
            
            if(tLinea_4.getText().length()>60)  lCaracteresRestantes_4.setForeground(Color.red);
            else  lCaracteresRestantes_4.setForeground(Colores.TEXTO);
        });
        
        JPanel panelLinea_4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelLinea_4.add(new HJLabel("Línea de información 4:"));
        panelLinea_4.add(Box.createHorizontalStrut(5));
        panelLinea_4.add(tLinea_4);
        panelLinea_4.add(Box.createHorizontalStrut(5));
        panelLinea_4.add(lCaracteresRestantes_4);
        panelLinea_4.setOpaque(false);
        
        
        Box boxLineas = Box.createVerticalBox();
        boxLineas.add(panelLinea_1);
        boxLineas.add(Box.createVerticalStrut(10));
        boxLineas.add(panelLinea_2);
        boxLineas.add(Box.createVerticalStrut(10));
        boxLineas.add(panelLinea_3);
        boxLineas.add(Box.createVerticalStrut(10));
        boxLineas.add(panelLinea_4);
        
        JPanel panelLineas = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelLineas.add(boxLineas);
        panelLineas.setOpaque(false);
        
        
        //....
        
        JLabel lColorEncabezado = new JLabel();
        lColorEncabezado.setPreferredSize(new Dimension(15, 15));
        lColorEncabezado.setOpaque(true);
        lColorEncabezado.setBackground(colorInicialEncabezado);
        
        
        HJButton bSeleccionarColorEncabezado = new HJButton(new IconoDeImagen("Colores.png", -1, 20), "Seleccionar", Colores.COLORES_BOTONES);
        bSeleccionarColorEncabezado.addActionListener( e -> {
            
            HJDialog.mostrarMensaje("Recomendación", new String[] {"Para impresiones más rápidas se recomienda establecer el color por defecto negro,", "tanto para el encabezado como para el contenido."}, IconoDeImagen.INFORMACION, null);
            
            if(panelSelectorColorEncabezado==null)
                panelSelectorColorEncabezado = new PanelSelectorColor(colorInicialEncabezado);
            
            panelSelectorColorEncabezado.establecerColor(lColorEncabezado.getBackground());
            
            panelSelectorColorEncabezado.setVisible(true);
            
            if(panelSelectorColorEncabezado.fueColorSeleccionado())
            {
                lColorEncabezado.setBackground(panelSelectorColorEncabezado.obtenerColorSeleccionado());
                lNombreCompleto.setForeground(panelSelectorColorEncabezado.obtenerColorSeleccionado());
                lEspecialidad.setForeground(panelSelectorColorEncabezado.obtenerColorSeleccionado());
                tLinea_1.setForeground(panelSelectorColorEncabezado.obtenerColorSeleccionado());
                tLinea_2.setForeground(panelSelectorColorEncabezado.obtenerColorSeleccionado());
                tLinea_3.setForeground(panelSelectorColorEncabezado.obtenerColorSeleccionado());
                tLinea_4.setForeground(panelSelectorColorEncabezado.obtenerColorSeleccionado());
            }
        });
        
        
        HJButton bColorEncabezadoPorDefecto = new HJButton(null, "Por defecto", Colores.COLORES_BOTONES);
        bColorEncabezadoPorDefecto.addActionListener( e -> {
            
            lColorEncabezado.setBackground(Colores.NEGRO);
            lNombreCompleto.setForeground(Colores.NEGRO);
            lEspecialidad.setForeground(Colores.NEGRO);
            tLinea_1.setForeground(Colores.NEGRO);
            tLinea_2.setForeground(Colores.NEGRO);
            tLinea_3.setForeground(Colores.NEGRO);
            tLinea_4.setForeground(Colores.NEGRO);
        });
        
        
        JPanel panelColorEncabezado = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelColorEncabezado.add(new HJLabel("Color: "));
        panelColorEncabezado.add(lColorEncabezado);
        panelColorEncabezado.add(Box.createHorizontalStrut(15));
        panelColorEncabezado.add(bSeleccionarColorEncabezado);
        panelColorEncabezado.add(Box.createHorizontalStrut(15));
        panelColorEncabezado.add(bColorEncabezadoPorDefecto);
        panelColorEncabezado.setOpaque(false);
        
        
        //....
        
        chbLineaDivisoria = new HJCheckBox("Colocar línea divisoria entre Título-Nombre y Especialidad");
        
        if(conLineaDivisoria)  chbLineaDivisoria.setSelected(true);
        
        JPanel panelLineaDivisoria = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelLineaDivisoria.add(Box.createHorizontalStrut(30));
        panelLineaDivisoria.add(chbLineaDivisoria);
        panelLineaDivisoria.setOpaque(false);
        
        
        //....
        
        Box boxEncabezado = Box.createVerticalBox();
        boxEncabezado.add(divisorEncabezado);
        boxEncabezado.add(Box.createVerticalStrut(20));
        boxEncabezado.add(panelLogo);
        boxEncabezado.add(Box.createVerticalStrut(15));
        boxEncabezado.add(panelNombreCompleto);
        boxEncabezado.add(Box.createVerticalStrut(15));
        boxEncabezado.add(panelEspecialidad);
        boxEncabezado.add(Box.createVerticalStrut(15));
        boxEncabezado.add(panelLineas);
        boxEncabezado.add(Box.createVerticalStrut(15));
        boxEncabezado.add(panelColorEncabezado);
        boxEncabezado.add(Box.createVerticalStrut(15));
        boxEncabezado.add(panelLineaDivisoria);
        
        
        //.......................
        
        HJDividingPanel divisorContenido = new HJDividingPanel("Contenido", formatoDivisores, colorTextoDivisores, HJDividingPanel.IZQUIERDA, 50, colorLineaDivisores, 1100, 4);
        
        
        Color colorInicialContenido = new Color(redContenido, greenContenido, blueContenido);
        
        
        lContenido = new HJLabel("Muestra de tipo de letra y tamaño");
        lContenido.setForeground(colorInicialContenido);
        lContenido.setPreferredSize(new Dimension(315, 28));
        lContenido.setHorizontalAlignment(HJLabel.CENTER);
        
        
        HJComboBox<String> cbFormatoContenido = new HJComboBox<>(familiasDisponiblesListos);
        cbFormatoContenido.addItemListener( e -> {
            
            if(cbFormatoContenido.getSelectedIndex()>0)
                lContenido.setFont(new Font(""+cbFormatoContenido.getSelectedItem(), lContenido.getFont().getStyle(), lContenido.getFont().getSize()));
        });
        
        
        HJComboBox<Integer> cbTamanoContenido = new HJComboBox<>(tamanos);
        cbTamanoContenido.addItemListener( e -> lContenido.setFont(new Font(lContenido.getFont().getFamily(), lContenido.getFont().getStyle(), (int)cbTamanoContenido.getSelectedItem())) );
        
        
        HJCheckBox chbNegritaContenido = new HJCheckBox("Negrita");
        chbNegritaContenido.addActionListener( e -> {
            
            if(chbNegritaContenido.isSelected())
                lContenido.setFont(new Font(lContenido.getFont().getFamily(), lContenido.getFont().getStyle()+Font.BOLD, lContenido.getFont().getSize()));
            else
                lContenido.setFont(new Font(lContenido.getFont().getFamily(), lContenido.getFont().getStyle()-Font.BOLD, lContenido.getFont().getSize()));
        });
        
        
        HJCheckBox chbItalicaContenido = new HJCheckBox("Itálica");
        chbItalicaContenido.setFont(new Font("Arial", Font.ITALIC, 14));
        chbItalicaContenido.addActionListener( e -> {
            
            if(chbItalicaContenido.isSelected())
                lContenido.setFont(new Font(lContenido.getFont().getFamily(), lContenido.getFont().getStyle()+Font.ITALIC, lContenido.getFont().getSize()));
            else
                lContenido.setFont(new Font(lContenido.getFont().getFamily(), lContenido.getFont().getStyle()-Font.ITALIC, lContenido.getFont().getSize()));
        });
        
        
        if(Utilidades.estaDisponibleFamilia(familyContenido))
        {
            lContenido.setFont(new Font(familyContenido, styleContenido, sizeContenido));
            cbFormatoContenido.setSelectedItem(familyContenido);
        }else{
            lContenido.setFont(new Font(ARIAL, styleContenido, sizeContenido));
            cbFormatoContenido.setSelectedItem(ARIAL);
        }
        cbTamanoContenido.setSelectedItem(sizeContenido);
        chbNegritaContenido.setSelected(styleContenido==Font.BOLD || styleContenido==Font.BOLD+Font.ITALIC);
        chbItalicaContenido.setSelected(styleContenido==Font.ITALIC || styleContenido==Font.BOLD+Font.ITALIC);
        
        
        HJButton bPorDefectoContenido = new HJButton(null, "Por defecto", Colores.COLORES_BOTONES);
        bPorDefectoContenido.addActionListener( e -> {
            
            cbFormatoContenido.setSelectedItem(ARIAL);
            cbTamanoContenido.setSelectedItem(12);
            chbNegritaContenido.setSelected(false);
            chbItalicaContenido.setSelected(false);
            
            lContenido.setFont(new Font(ARIAL, Font.PLAIN, 12));
        });
        
        
        JPanel panelContenido = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelContenido.add(Box.createHorizontalStrut(30));
        panelContenido.add(new HJLabel("Ejemplo:"));
        panelContenido.add(Box.createHorizontalStrut(25));
        panelContenido.add(lContenido);
        panelContenido.add(Box.createHorizontalStrut(25));
        panelContenido.add(cbFormatoContenido);
        panelContenido.add(Box.createHorizontalStrut(15));
        panelContenido.add(cbTamanoContenido);
        panelContenido.add(Box.createHorizontalStrut(15));
        panelContenido.add(chbNegritaContenido);
        panelContenido.add(Box.createHorizontalStrut(15));
        panelContenido.add(chbItalicaContenido);
        panelContenido.add(Box.createHorizontalStrut(15));
        panelContenido.add(bPorDefectoContenido);
        panelContenido.add(Box.createHorizontalStrut(30));
        panelContenido.setOpaque(false);
        
        
        //....
        
        JLabel lColorContenido = new JLabel();
        lColorContenido.setPreferredSize(new Dimension(15, 15));
        lColorContenido.setOpaque(true);
        lColorContenido.setBackground(colorInicialContenido);
        
        
        HJButton bSeleccionarColorContenido = new HJButton(new IconoDeImagen("Colores.png", -1, 20), "Seleccionar", Colores.COLORES_BOTONES);
        bSeleccionarColorContenido.addActionListener( e -> {
            
            HJDialog.mostrarMensaje("Recomendación", new String[] {"Para impresiones más rápidas se recomienda establecer el color por defecto negro,", "tanto para el encabezado como para el contenido."}, IconoDeImagen.INFORMACION, null);
            
            if(panelSelectorColorContenido==null)
                panelSelectorColorContenido = new PanelSelectorColor(colorInicialContenido);
            
            panelSelectorColorContenido.establecerColor(lColorContenido.getBackground());
            
            panelSelectorColorContenido.setVisible(true);
            
            if(panelSelectorColorContenido.fueColorSeleccionado())
            {
                lColorContenido.setBackground(panelSelectorColorContenido.obtenerColorSeleccionado());
                lContenido.setForeground(panelSelectorColorContenido.obtenerColorSeleccionado());
            }
        });
        
        
        HJButton bColorContenidoPorDefecto = new HJButton(null, "Por defecto", Colores.COLORES_BOTONES);
        bColorContenidoPorDefecto.addActionListener( e -> {
            
            lColorContenido.setBackground(Colores.NEGRO);
            lContenido.setForeground(Colores.NEGRO);
        });
        
        
        JPanel panelColorContenido = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelColorContenido.add(new HJLabel("Color: "));
        panelColorContenido.add(lColorContenido);
        panelColorContenido.add(Box.createHorizontalStrut(15));
        panelColorContenido.add(bSeleccionarColorContenido);
        panelColorContenido.add(Box.createHorizontalStrut(15));
        panelColorContenido.add(bColorContenidoPorDefecto);
        panelColorContenido.setOpaque(false);
        
        
        //....
        
        chbMarco = new HJCheckBox("Colocar marco al contenido");
        
        if(conMarco)  chbMarco.setSelected(true);
        
        JPanel panelMarco = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelMarco.add(Box.createHorizontalStrut(30));
        panelMarco.add(chbMarco);
        panelMarco.setOpaque(false);
        
        
        chbPediatrico = new HJCheckBox("Colocar talla, peso y c.c. del paciente en las indicaciones");
        
        if(esPediatrico)  chbPediatrico.setSelected(true);
        
        JPanel panelPediatrico = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelPediatrico.add(Box.createHorizontalStrut(30));
        panelPediatrico.add(chbPediatrico);
        panelPediatrico.setOpaque(false);
        
        
        //....
        
        Box boxContenido = Box.createVerticalBox();
        boxContenido.add(divisorContenido);
        boxContenido.add(Box.createVerticalStrut(20));
        boxContenido.add(panelContenido);
        boxContenido.add(Box.createVerticalStrut(15));
        boxContenido.add(panelColorContenido);
        boxContenido.add(Box.createVerticalStrut(15));
        boxContenido.add(panelMarco);
        boxContenido.add(Box.createVerticalStrut(15));
        boxContenido.add(panelPediatrico);
        
        
        //.......................
        
        HJPaintedBox boxSuperior = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(245,245,245), new Color(235,235,235), null, null, 100, true);
        boxSuperior.add(Box.createVerticalStrut(30));
        boxSuperior.add(boxEncabezado);
        boxSuperior.add(Box.createVerticalStrut(30));
        boxSuperior.add(boxContenido);
        boxSuperior.add(Box.createVerticalStrut(30));
        
        JScrollPane scrollBoxSuperior = new JScrollPane(boxSuperior);
        scrollBoxSuperior.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBoxSuperior.setPreferredSize(new Dimension(1172, 620));
        scrollBoxSuperior.getVerticalScrollBar().setUnitIncrement(10);
        
        
        //.......................
        
        HJButton bVistaPrevia = new HJButton(new IconoDeImagen("VistaPrevia.png", -1, 20), "Vista Previa", Colores.COLORES_BOTONES);
        bVistaPrevia.addActionListener( e -> mostrarVistaPrevia() );
        
        HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
        bAceptar.addActionListener( e -> {
            
            guardarConfiguracion();
            
            setVisible(false);
            dispose();
        });
        
        HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
        bCancelar.addActionListener( e -> cancelar() );
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.add(bVistaPrevia);
        panelBotones.add(bAceptar);
        panelBotones.add(bCancelar);
        panelBotones.setOpaque(false);
        
        
        //........................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(scrollBoxSuperior);
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private PanelPersonalizacionRecipes obtenerEstePanel() { return this; }
    
    
    private void guardarLogo(Icon icono) {
        
        BufferedImage imagenBuffered = new BufferedImage(80, 100, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2D = imagenBuffered.createGraphics();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        icono.paintIcon(null, g2D, 0, 0);
        
        try{
            ImageIO.write(imagenBuffered, "png", new File(Directorios.RECURSOS+LOGO_PERSONALIZADO));
        }catch(IOException ioe) {
            HJDialog.mostrarMensaje("Error al Guardar Foto", new String[] {"Ocurrió un error al guardar la foto del paciente."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
    //..................................
    
    
    private void guardarConfiguracion() {
        
        MapaDatos mapaDatos = new MapaDatos();
        
        mapaDatos.put(NOMBRE_LOGO, nombreLogo);
        
        mapaDatos.put(FAMILY_NOMBRE_COMPLETO, lNombreCompleto.getFont().getFamily());
        mapaDatos.put(STYLE_NOMBRE_COMPLETO, ""+lNombreCompleto.getFont().getStyle());
        mapaDatos.put(SIZE_NOMBRE_COMPLETO, ""+lNombreCompleto.getFont().getSize());
        mapaDatos.put(FAMILY_ESPECIALIDAD, lEspecialidad.getFont().getFamily());
        mapaDatos.put(STYLE_ESPECIALIDAD, ""+lEspecialidad.getFont().getStyle());
        mapaDatos.put(SIZE_ESPECIALIDAD, ""+lEspecialidad.getFont().getSize());
        mapaDatos.put(LINEA_INFO_1, tLinea_1.getText());
        mapaDatos.put(LINEA_INFO_2, tLinea_2.getText());
        mapaDatos.put(LINEA_INFO_3, tLinea_3.getText());
        mapaDatos.put(LINEA_INFO_4, tLinea_4.getText());
        mapaDatos.put(RED_ENCABEZADO, ""+lNombreCompleto.getForeground().getRed());
        mapaDatos.put(GREEN_ENCABEZADO, ""+lNombreCompleto.getForeground().getGreen());
        mapaDatos.put(BLUE_ENCABEZADO, ""+lNombreCompleto.getForeground().getBlue());
        mapaDatos.put(CON_LINEA_DIVISORIA, ""+chbLineaDivisoria.isSelected());
        
        mapaDatos.put(FAMILY_CONTENIDO, lContenido.getFont().getFamily());
        mapaDatos.put(STYLE_CONTENIDO, ""+lContenido.getFont().getStyle());
        mapaDatos.put(SIZE_CONTENIDO, ""+lContenido.getFont().getSize());
        mapaDatos.put(RED_CONTENIDO, ""+lContenido.getForeground().getRed());
        mapaDatos.put(GREEN_CONTENIDO, ""+lContenido.getForeground().getGreen());
        mapaDatos.put(BLUE_CONTENIDO, ""+lContenido.getForeground().getBlue());
        mapaDatos.put(CON_MARCO, ""+chbMarco.isSelected());
        mapaDatos.put(ES_PEDIATRICO, ""+chbPediatrico.isSelected());
        
        Utilidades.guardarEnArchivo(Directorios.CONFIGURACION+"ConfigRecipes.dda", mapaDatos);
    }
    
    
    private void mostrarVistaPrevia() {
        
        MapaDatos mapaDatos = new MapaDatos();
        
        mapaDatos.put(NOMBRE_LOGO, nombreLogo);
        
        mapaDatos.put(FAMILY_NOMBRE_COMPLETO, lNombreCompleto.getFont().getFamily());
        mapaDatos.put(STYLE_NOMBRE_COMPLETO, ""+lNombreCompleto.getFont().getStyle());
        mapaDatos.put(SIZE_NOMBRE_COMPLETO, ""+lNombreCompleto.getFont().getSize());
        mapaDatos.put(FAMILY_ESPECIALIDAD, lEspecialidad.getFont().getFamily());
        mapaDatos.put(STYLE_ESPECIALIDAD, ""+lEspecialidad.getFont().getStyle());
        mapaDatos.put(SIZE_ESPECIALIDAD, ""+lEspecialidad.getFont().getSize());
        mapaDatos.put(LINEA_INFO_1, tLinea_1.getText());
        mapaDatos.put(LINEA_INFO_2, tLinea_2.getText());
        mapaDatos.put(LINEA_INFO_3, tLinea_3.getText());
        mapaDatos.put(LINEA_INFO_4, tLinea_4.getText());
        mapaDatos.put(RED_ENCABEZADO, ""+lNombreCompleto.getForeground().getRed());
        mapaDatos.put(GREEN_ENCABEZADO, ""+lNombreCompleto.getForeground().getGreen());
        mapaDatos.put(BLUE_ENCABEZADO, ""+lNombreCompleto.getForeground().getBlue());
        mapaDatos.put(CON_LINEA_DIVISORIA, ""+chbLineaDivisoria.isSelected());
        
        mapaDatos.put(FAMILY_CONTENIDO, lContenido.getFont().getFamily());
        mapaDatos.put(STYLE_CONTENIDO, ""+lContenido.getFont().getStyle());
        mapaDatos.put(SIZE_CONTENIDO, ""+lContenido.getFont().getSize());
        mapaDatos.put(RED_CONTENIDO, ""+lContenido.getForeground().getRed());
        mapaDatos.put(GREEN_CONTENIDO, ""+lContenido.getForeground().getGreen());
        mapaDatos.put(BLUE_CONTENIDO, ""+lContenido.getForeground().getBlue());
        mapaDatos.put(CON_MARCO, ""+chbMarco.isSelected());
        mapaDatos.put(ES_PEDIATRICO, ""+chbPediatrico.isSelected());
        
        PanelVistaPrevia panelVistaPrevia = new PanelVistaPrevia(mapaDatos);
        
        Thread hiloLlenar = new Thread( () -> {
            
            while(panelVistaPrevia.isVisible()==false)
            {
                Utilidades.esperar(20);
            }
            
            panelVistaPrevia.llenarRecipe();
        });
        hiloLlenar.start();
        
        panelVistaPrevia.setVisible(true);
    }
    
    
    private void cancelar() {
        
        MapaDatos mapaConfiguracion = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"ConfigRecipes.dda");
        
        if(mapaConfiguracion==null)
        {
            if(HJDialog.mostrarDialogoPregunta("Advertencia", new String[] {"Si en este momento no guarda una configuración personalizada (haciendo click en 'Aceptar')", "se guardará la configuración por defecto para los récipes.", " ", "¿Desea salir sin guardar?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
                guardarConfiguracion();
            else
                return;
        }
        
        setVisible(false);
        dispose();
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class PanelSelectorColor extends HJDialog {
        
        private JColorChooser selectorColor;
        
        private boolean fueColorSeleccionado = false;
        
        
        public PanelSelectorColor(Color colorInicial) {
            
            super(new IconoDeImagen("Colores.png", -1, 30), "Selección de Color", null, true);
            
            
            anadirActionListenerABotonCerrar( e -> {
                
                setVisible(false);
            });
            
            
            //......................
            
            selectorColor = new JColorChooser(colorInicial);
            selectorColor.setOpaque(false);
            
            JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelSuperior.add(selectorColor);
            panelSuperior.setOpaque(false);
            
            
            //....
            
            HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
            bAceptar.addActionListener( e -> {
                
                fueColorSeleccionado = true;
                
                setVisible(false);
            });
            
            HJButton bReestablecer = new HJButton(new IconoDeImagen("Deshacer.png", -1, 20), "Reestablecer", Colores.COLORES_BOTONES);
            bReestablecer.addActionListener( e -> selectorColor.setColor(colorInicial) );
            
            HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
            bCancelar.addActionListener( e -> {
                
                fueColorSeleccionado = false;
                
                setVisible(false);
            });
            
            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
            panelBotones.add(bAceptar);
            panelBotones.add(bReestablecer);
            panelBotones.add(bCancelar);
            panelBotones.setOpaque(false);
              
            
            //........................
            
            
            Box boxGeneral = Box.createVerticalBox();
            boxGeneral.add(panelSuperior);
            boxGeneral.add(panelBotones);
            
            
            add(boxGeneral);
            
            
            pack();
            
            setLocationRelativeTo(null);
            
        }
        
        
        public boolean fueColorSeleccionado() { return fueColorSeleccionado; }
        
        
        public void establecerColor(Color color) { selectorColor.setColor(color); }
        
        public Color obtenerColorSeleccionado() { return selectorColor.getColor(); }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
