/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class PaginaVacunas extends JPanel {
    
    private final Color colorNegro = new Color(0,0,0);
    private final Font formatoDatos = new Font("Times New Roman", Font.ITALIC, 14);
    
    private final String DOSIS = "Dosis";
    private final String REFUERZO = "Refuerzo";
    private final String ULTIMA_DOSIS = "Ult. Dosis";
    private final String ULTIMO_REFUERZO = "Ult. Refuerzo";
    
    private final int UNICA_DOSIS_O_REFUERZO = 0;
    private final int ULTIMA_DOSIS_O_REFUERZO = -1;
    
    private final HashMap<String,ArrayList<String>> mapaVacunas;
    
    public static final int BLOQUE_1 = 1;
    public static final int BLOQUE_2 = 2;
    
    private final JPanel panelVacunas;
    
    
    public PaginaVacunas(int numeroHistoria, int bloque) {
        
        setLayout(new GridLayout(1, 1, 0, 0));
        
        setBackground(Colores.BLANCO);
        
        setPreferredSize(new Dimension(612, 792));
        
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1), BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        
        
        //..........................
        
        HJLabel lInformeVacunas = obtenerEtiquetaNegra("Informe de Vacunas");
        lInformeVacunas.setFont(new Font("Arial", Font.BOLD, 16));
        
        JPanel panelInformeVacunas = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelInformeVacunas.add(lInformeVacunas);
        panelInformeVacunas.setOpaque(false);
        
        
        MapaDatos mapaDatosPaciente = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+numeroHistoria+".dda");
        
        HJLabel lNombreCompleto = obtenerEtiquetaNegra(mapaDatosPaciente.get(Personales.KEY_NOMBRES)+" "+mapaDatosPaciente.get(Personales.KEY_APELLIDOS));
        lNombreCompleto.setFont(formatoDatos);
        
        JPanel panelPaciente = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelPaciente.add(obtenerEtiquetaNegra("Paciente: "));
        panelPaciente.add(lNombreCompleto);
        panelPaciente.setOpaque(false);
        
        HJLabel lCedula = obtenerEtiquetaNegra();
        lCedula.setFont(formatoDatos);
        if(mapaDatosPaciente.get(Personales.KEY_CEDULA)!=null)  lCedula.setText(mapaDatosPaciente.get(Personales.KEY_CEDULA));
        
        HJLabel lGenero = obtenerEtiquetaNegra(mapaDatosPaciente.get(Personales.KEY_GENERO));
        lGenero.setFont(formatoDatos);
        
        HJLabel lEdad = obtenerEtiquetaNegra(Utilidades.obtenerEdadMinimaCadena(mapaDatosPaciente.get(Personales.KEY_FECHA_NACIMIENTO)));
        lEdad.setFont(formatoDatos);
        
        JPanel panelCedulaGeneroEdad = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCedulaGeneroEdad.add(obtenerEtiquetaNegra("Cédula: "));
        panelCedulaGeneroEdad.add(lCedula);
        panelCedulaGeneroEdad.add(Box.createHorizontalStrut(30));
        panelCedulaGeneroEdad.add(obtenerEtiquetaNegra("Género: "));
        panelCedulaGeneroEdad.add(lGenero);
        panelCedulaGeneroEdad.add(Box.createHorizontalStrut(30));
        panelCedulaGeneroEdad.add(obtenerEtiquetaNegra("Edad: "));
        panelCedulaGeneroEdad.add(lEdad);
        panelCedulaGeneroEdad.setOpaque(false);
        
        
        HJLabel lFecha = obtenerEtiquetaNegra(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
        lFecha.setFont(formatoDatos);
        
        JPanel panelFecha = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelFecha.add(obtenerEtiquetaNegra("Fecha de emisión: "));
        panelFecha.add(lFecha);
        panelFecha.setOpaque(false);
        
        
        Box boxCentralCabecera = Box.createVerticalBox();
        boxCentralCabecera.add(panelInformeVacunas);
        boxCentralCabecera.add(Box.createVerticalStrut(10));
        boxCentralCabecera.add(panelPaciente);
        boxCentralCabecera.add(Box.createVerticalStrut(5));
        boxCentralCabecera.add(panelCedulaGeneroEdad);
        boxCentralCabecera.add(Box.createVerticalStrut(5));
        boxCentralCabecera.add(panelFecha);
        boxCentralCabecera.add(Box.createVerticalStrut(10));
        
        
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelSuperior.add(new HJLabel(new IconoDeImagen("VacunaImpresion.png", -1, 80)));
        panelSuperior.add(Box.createHorizontalStrut(30));
        panelSuperior.add(boxCentralCabecera);
        panelSuperior.setOpaque(false);
        
        Box boxSuperior = Box.createHorizontalBox();
        boxSuperior.add(panelSuperior);
        
        
        //............................
        
        mapaVacunas = (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.VACUNAS+numeroHistoria+".dda");
        
        Box boxVertical1 = Box.createVerticalBox();
        
        Box boxVertical2 = Box.createVerticalBox();
        
        if(bloque==BLOQUE_1)
        {
            boxVertical1.add(obtenerBoxVacuna("BCG", 2, 0, false, false));
            boxVertical1.add(Box.createVerticalStrut(10));
            boxVertical1.add(obtenerBoxVacuna("Polio", 3, 2, false, false));
            boxVertical1.add(Box.createVerticalStrut(10));
            boxVertical1.add(obtenerBoxVacuna("DTP", 3, 2, false, false));
            boxVertical1.add(Box.createVerticalStrut(10));
            boxVertical1.add(obtenerBoxVacuna("Hepatitis A", 2, 0, false, false));
            boxVertical1.add(Box.createVerticalStrut(10));
            boxVertical1.add(obtenerBoxVacuna("Hepatitis B", 3, 0, false, false));
            
            boxVertical2.add(obtenerBoxVacuna("H. Influenza (tipo B)", 2, 3, false, true));
            boxVertical2.add(Box.createVerticalStrut(10));
            boxVertical2.add(obtenerBoxVacuna("Influenza", 3, 0, true, false));
            boxVertical2.add(Box.createVerticalStrut(10));
            boxVertical2.add(obtenerBoxVacuna("Antiamarílica", 1, 3, false, true));
            boxVertical2.add(Box.createVerticalStrut(10));
            boxVertical2.add(obtenerBoxVacuna("Trivalente Viral (SPR)", 1, 2, false, false));
            boxVertical2.add(Box.createVerticalStrut(10));
            boxVertical2.add(obtenerBoxVacuna("Varicela", 2, 0, false, false));
            
        }else{
            
            boxVertical1.add(obtenerBoxVacuna("Neumococo", 3, 1, false, false));
            boxVertical1.add(Box.createVerticalStrut(10));
            boxVertical1.add(obtenerBoxVacuna("Meningococo", 3, 1, false, false));
            boxVertical1.add(Box.createVerticalStrut(10));
            boxVertical1.add(obtenerBoxVacuna("Rotavirus", 3, 0, false, false));
            boxVertical1.add(Box.createVerticalStrut(10));
            boxVertical1.add(obtenerBoxVacuna("VPH", 1, 0, false, false));
        }
        
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        panelCentral.add(boxVertical1);
        if(boxVertical2.getComponentCount()>0)
        {
            panelCentral.add(Box.createHorizontalStrut(10));
            panelCentral.add(boxVertical2);
        }
        panelCentral.setOpaque(false);
        
        panelCentral.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, colorNegro));
        
        
        //.............................
        
        MapaDatos mapaDatosDoctor = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        HJLabel lNombreDoctor = obtenerEtiquetaNegra(mapaDatosDoctor.get(PanelDatosDoctor.TITULO)+" "+mapaDatosDoctor.get(PanelDatosDoctor.NOMBRE_COMPLETO));
        lNombreDoctor.setFont(formatoDatos);
        
        HJLabel lFirma = new HJLabel();
        lFirma.setPreferredSize(new Dimension(120, 14));
        lFirma.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorNegro));
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelInferior.add(obtenerEtiquetaNegra("Emitido por: "));
        panelInferior.add(lNombreDoctor);
        panelInferior.add(Box.createHorizontalStrut(50));
        panelInferior.add(obtenerEtiquetaNegra("Firma: "));
        panelInferior.add(lFirma);
        panelInferior.setOpaque(false);
        
        Box boxInferior = Box.createVerticalBox();
        boxInferior.add(Box.createVerticalStrut(20));
        boxInferior.add(panelInferior);
        
        
        //..........................
        
        
        JPanel panelGeneral = new JPanel(new BorderLayout(0, 0));
        panelGeneral.add(boxSuperior, BorderLayout.NORTH);
        panelGeneral.add(panelCentral, BorderLayout.CENTER);
        panelGeneral.add(boxInferior, BorderLayout.SOUTH);
        panelGeneral.setOpaque(false);
        
        
        panelVacunas = new JPanel(new GridLayout(1, 1, 0, 0));
        panelVacunas.add(panelGeneral);
        panelVacunas.setOpaque(false);
        
        
        add(panelVacunas);
        
    }
    
    
    private HJLabel obtenerEtiquetaNegra(String texto) {
        
        HJLabel etiqueta = new HJLabel(texto);
        etiqueta.setForeground(colorNegro);
        
        return etiqueta;
    }
    
    private HJLabel obtenerEtiquetaNegra() { return obtenerEtiquetaNegra(null); }
    
    
    private HJEnclosingBox obtenerBoxVacuna(String nombreVacuna, int cantidadDosis, int cantidadRefuerzos, boolean ultDosis, boolean ultRefuerzo) {
        
        HJLabel lFecha = obtenerEtiquetaNegra("Fecha");
        lFecha.setFont(new Font("Arial", Font.PLAIN, 9));
        
        HJLabel lEdad = obtenerEtiquetaNegra("Edad");
        lEdad.setFont(new Font("Arial", Font.PLAIN, 9));
        
        JPanel panelFechaEdad = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelFechaEdad.add(lFecha);
        panelFechaEdad.add(Box.createHorizontalStrut(76));
        panelFechaEdad.add(lEdad);
        panelFechaEdad.add(Box.createHorizontalStrut(64));
        panelFechaEdad.setOpaque(false);
        
        
        HJEnclosingBox boxPaneles = new HJEnclosingBox(BoxLayout.Y_AXIS, nombreVacuna, new Font("Arial", Font.BOLD, 14), colorNegro, HJEnclosingBox.IZQUIERDA, 15, colorNegro, 1);
        boxPaneles.add(Box.createVerticalStrut(14));
        boxPaneles.add(panelFechaEdad);
        
        if(cantidadDosis==1)
        {
            boxPaneles.add(obtenerPanelDosis(nombreVacuna, DOSIS, UNICA_DOSIS_O_REFUERZO));
            
        }else{
            
            for(int i=1 ; i<=cantidadDosis ; i++)
            {
                boxPaneles.add(obtenerPanelDosis(nombreVacuna, DOSIS, i));
            }
        }
        
        if(ultDosis)  boxPaneles.add(obtenerPanelDosis(nombreVacuna, ULTIMA_DOSIS, ULTIMA_DOSIS_O_REFUERZO));
        
        
        if(cantidadRefuerzos==1)
        {
            boxPaneles.add(obtenerPanelDosis(nombreVacuna, REFUERZO, UNICA_DOSIS_O_REFUERZO));
            
        }else{
            
            for(int i=1 ; i<=cantidadRefuerzos ; i++)
            {
                boxPaneles.add(obtenerPanelDosis(nombreVacuna, REFUERZO, 1));
            }
        }
        
        if(ultRefuerzo)  boxPaneles.add(obtenerPanelDosis(nombreVacuna, ULTIMO_REFUERZO, ULTIMA_DOSIS_O_REFUERZO));
        
        
        boxPaneles.add(Box.createVerticalStrut(1));
        
        
        return boxPaneles;
    }
    
    
    private JPanel obtenerPanelDosis(String nombreVacuna, String tipo, int numero) {
        
        HJLabel lTexto = obtenerEtiquetaNegra();
        lTexto.setFont(new Font("Arial", Font.PLAIN, 9));
        
        if((tipo.equals(DOSIS) || tipo.equals(REFUERZO)) && numero>0)  lTexto.setText(tipo+" "+numero);
        else  lTexto.setText(tipo);
        
        
        HJLabel lFecha = obtenerEtiquetaNegra();
        lFecha.setFont(new Font("Arial", Font.PLAIN, 9));
        lFecha.setPreferredSize(new Dimension(55, 12));
        lFecha.setHorizontalAlignment(HJLabel.CENTER);
        lFecha.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorNegro));
        
        HJLabel lEdad = obtenerEtiquetaNegra();
        lEdad.setFont(new Font("Arial", Font.PLAIN, 9));
        lEdad.setPreferredSize(new Dimension(130, 12));
        lEdad.setHorizontalAlignment(HJLabel.CENTER);
        lEdad.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, colorNegro));
        
        if(mapaVacunas!=null && mapaVacunas.get(nombreVacuna+"-"+tipo+"-"+numero)!=null)
        {
            lFecha.setText(mapaVacunas.get(nombreVacuna+"-"+tipo+"-"+numero).get(0));
            
            lEdad.setText(mapaVacunas.get(nombreVacuna+"-"+tipo+"-"+numero).get(1));
        }
        
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 4));
        panel.add(lTexto);
        panel.add(lFecha);
        panel.add(lEdad);
        panel.setOpaque(false);
        
        
        return panel;
    }
    
    
    //.............................
    
    
    public JPanel obtenerPanelParaImprimir() { return panelVacunas; }
    
    
}
