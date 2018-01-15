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
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PanelExamenesLaboratorio extends HJDialog {
    
    private final Font formatoSubtitulos = new Font("Arial", Font.BOLD, 18);
    
    public static final String HEMATOLOGIA = "Hematología";
    public static final String UROANALISIS = "Uroanálisis";
    public static final String COPROANALISIS = "Coproanálisis";
    public static final String QUIMICA_SANGUINEA = "Química Sanguínea";
    public static final String PERFILES = "Perfiles";
    public static final String BACTERIOLOGIA = "Bacteriología";
    public static final String HORMONAS = "Hormonas";
    public static final String ELECTROFERESIS = "Electroféresis";
    public static final String MISCELANEOS = "Misceláneos";
    public static final String INMUNOLOGIA = "Inmunología";
    public static final String DROGAS = "Drogas";
    public static final String OTROS = "Otros";
    
    private final ArrayList<Examen> arrayExamenesHematologia = new ArrayList<>(0);
    private final ArrayList<ExamenUroanalisis> arrayExamenesUroanalisis = new ArrayList<>(0);
    private final ArrayList<Examen> arrayExamenesCoproanalisis = new ArrayList<>(0);
    private final ArrayList<Examen> arrayExamenesQuimicaSanguinea = new ArrayList<>(0);
    private final ArrayList<Examen> arrayExamenesPerfiles = new ArrayList<>(0);
    private final ArrayList<Examen> arrayExamenesBacteriologia = new ArrayList<>(0);
    private final ArrayList<Examen> arrayExamenesHormonas = new ArrayList<>(0);
    private final ArrayList<Examen> arrayExamenesElectroferesis = new ArrayList<>(0);
    private final ArrayList<Examen> arrayExamenesMiscelaneos = new ArrayList<>(0);
    private final ArrayList<ExamenInmunologia> arrayExamenesInmunologia = new ArrayList<>(0);
    private final ArrayList<ArrayList<Examen>> arrayDeArraysExamen = new ArrayList<>(0);
    private final HJTextArea taDrogas;
    private final HJTextArea taOtros;
    
    private final HashMap<String,ArrayList<String>> mapaExamenes = new HashMap<>(0);
    
    
    public PanelExamenesLaboratorio() {
        
        super(new IconoDeImagen("Examenes.png", -1, 30), "Exámenes de Laboratorio", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Advertencia", new String[] {"Esta acción es igual a presionar el botón 'Aceptar'.", "¿Está seguro(a) de que desea continuar?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
                aceptar();
        });
        
        
        //...... HEMATOLOGÍA ......
        
        HJLabel lHematologia = new HJLabel(HEMATOLOGIA);
        lHematologia.setFont(formatoSubtitulos);
        lHematologia.setForeground(new Color(80,10,10));
        
        JPanel panelEtiquetaHematologia = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaHematologia.add(lHematologia);
        panelEtiquetaHematologia.setOpaque(false);
        
        
        Box boxHematologia = Box.createVerticalBox();
        boxHematologia.add(crear_Y_anadirExamen("Células LE", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Drepanocitos", arrayExamenesHematologia));
        
        HJCheckBox chbEosinofilos = new HJCheckBox("Eosinófilos");
        HJCheckBox chbSangre = new HJCheckBox("Sangre");
        HJCheckBox chbMocoNasal = new HJCheckBox("Moco nasal");
        HJCheckBox[] chbSecundariosEosinofilos = {chbSangre, chbMocoNasal};
        
        arrayExamenesHematologia.add(new Examen(chbEosinofilos, chbSecundariosEosinofilos));
        
        JPanel panelEosinofilos = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelEosinofilos.add(chbEosinofilos);
        panelEosinofilos.setOpaque(false);
        
        JPanel panelEosinofilosSecundarios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelEosinofilosSecundarios.add(Box.createHorizontalStrut(18));
        panelEosinofilosSecundarios.add(chbSangre);
        panelEosinofilosSecundarios.add(Box.createHorizontalStrut(5));
        panelEosinofilosSecundarios.add(chbMocoNasal);
        panelEosinofilosSecundarios.setOpaque(false);
        
        Box boxEosinofilos = Box.createVerticalBox();
        boxEosinofilos.add(panelEosinofilos);
        boxEosinofilos.add(panelEosinofilosSecundarios);
        boxEosinofilos.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        
        boxHematologia.add(boxEosinofilos);
        
        boxHematologia.add(crear_Y_anadirExamen("Eritrosedimentación (VSG)", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Fibrinógeno", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Gota gruesa", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Grupo sanguíneo y factor RH", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Hematología completa", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Hematología especial", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Hemoglobina y hematocrito", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("INR", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Investigación de hemoparásitos", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Leucocitos", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Plaquetas", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("PT (Tiempo de Protrombina)", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("PTT (Tiempo Parcial de Tromboplastina)", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Reticulocitos", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Serie blanca (fórmula y contaje)", arrayExamenesHematologia));
        boxHematologia.add(crear_Y_anadirExamen("Tiempo de sangría y coagulación", arrayExamenesHematologia));
        
        
        Box boxHematologiaMacro = Box.createVerticalBox();
        boxHematologiaMacro.add(panelEtiquetaHematologia);
        boxHematologiaMacro.add(boxHematologia);
        boxHematologiaMacro.add(Box.createVerticalStrut(5));
        
        
        HJPaintedPanel panelHematologia = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 10, 0), new Color(250,215,215), new Color(250,200,200), null, null, 100, true);
        panelHematologia.add(boxHematologiaMacro);
        
        panelHematologia.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...... UROANÁLISIS ......
        
        HJLabel lUroanalisis = new HJLabel(UROANALISIS);
        lUroanalisis.setFont(formatoSubtitulos);
        lUroanalisis.setForeground(new Color(35,45,5));
        
        JPanel panelEtiquetaUroanalisis = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaUroanalisis.add(lUroanalisis);
        panelEtiquetaUroanalisis.setOpaque(false);
        
        
        HJCustomizedButton bAyudaUroanalisis = new HJCustomizedButton("AyudaNormal.png", "AyudaNormal.png", -1, 14, true);
        bAyudaUroanalisis.addActionListener( e -> HJDialog.mostrarMensaje("Ayuda", new String[] {"O.P.: Orina Parcial", "24Hrs.: Orina 24 Hrs."}, IconoDeImagen.INFORMACION, null) );
        
        JPanel panelEtiquetasOrina = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelEtiquetasOrina.add(bAyudaUroanalisis);
        panelEtiquetasOrina.add(Box.createHorizontalStrut(10));
        panelEtiquetasOrina.add(new HJLabel("O.P."));
        panelEtiquetasOrina.add(Box.createHorizontalStrut(10));
        panelEtiquetasOrina.add(new HJLabel("24Hrs."));
        panelEtiquetasOrina.setOpaque(false);
        
        
        Box boxUroanalisis = Box.createVerticalBox();
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Ácido úrico"));
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Amilasuria"));
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Creatinina"));
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Depuración creatinina"));
        
        HJCheckBox chbElectrolitosURO = new HJCheckBox("Electrolitos");
        HJCheckBox chbSodioURO = new HJCheckBox("Sodio");
        HJCheckBox chbPotasioURO = new HJCheckBox("Potasio");
        HJCheckBox chbCalcioURO = new HJCheckBox("Calcio");
        HJCheckBox chbFosforoURO = new HJCheckBox("Fósforo");
        HJCheckBox[] chbSecundariosElectrolitosURO = {chbSodioURO, chbPotasioURO, chbCalcioURO, chbFosforoURO};
        HJCheckBox chbOrinaParcial = new HJCheckBox();
        chbOrinaParcial.setName("Orina parcial");
        HJCheckBox chbOrina24Hrs = new HJCheckBox();
        chbOrina24Hrs.setName("Orina 24 Hrs.");
        
        arrayExamenesUroanalisis.add(new ExamenUroanalisis(chbElectrolitosURO, chbSecundariosElectrolitosURO, chbOrinaParcial, chbOrina24Hrs));
        
        JPanel panelElectrolitosURO = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelElectrolitosURO.add(chbElectrolitosURO);
        panelElectrolitosURO.setOpaque(false);
        
        JPanel panelElectrolitosOrinas = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelElectrolitosOrinas.add(Box.createHorizontalStrut(10));
        panelElectrolitosOrinas.add(chbOrinaParcial);
        panelElectrolitosOrinas.add(Box.createHorizontalStrut(25));
        panelElectrolitosOrinas.add(chbOrina24Hrs);
        panelElectrolitosOrinas.add(Box.createHorizontalStrut(9));
        panelElectrolitosOrinas.setOpaque(false);
        
        Box boxElectrolitosSuperior = Box.createHorizontalBox();
        boxElectrolitosSuperior.add(panelElectrolitosURO);
        boxElectrolitosSuperior.add(panelElectrolitosOrinas);
        
        JPanel panelSodioURO = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSodioURO.add(chbSodioURO);
        panelSodioURO.setOpaque(false);
        
        JPanel panelCalcioURO = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelCalcioURO.add(chbCalcioURO);
        panelCalcioURO.setOpaque(false);
        
        Box boxVerticalSodioCalcioURO = Box.createVerticalBox();
        boxVerticalSodioCalcioURO.add(panelSodioURO);
        boxVerticalSodioCalcioURO.add(panelCalcioURO);
        
        JPanel panelPotasioURO = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelPotasioURO.add(chbPotasioURO);
        panelPotasioURO.setOpaque(false);
        
        JPanel panelFosforoURO = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelFosforoURO.add(chbFosforoURO);
        panelFosforoURO.setOpaque(false);
        
        Box boxVerticalPotasioFosforoURO = Box.createVerticalBox();
        boxVerticalPotasioFosforoURO.add(panelPotasioURO);
        boxVerticalPotasioFosforoURO.add(panelFosforoURO);
        
        JPanel panelElectrolitosSecundariosURO = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelElectrolitosSecundariosURO.add(Box.createHorizontalStrut(18));
        panelElectrolitosSecundariosURO.add(boxVerticalSodioCalcioURO);
        panelElectrolitosSecundariosURO.add(Box.createHorizontalStrut(5));
        panelElectrolitosSecundariosURO.add(boxVerticalPotasioFosforoURO);
        panelElectrolitosSecundariosURO.setOpaque(false);
        
        Box boxElectrolitosURO = Box.createVerticalBox();
        boxElectrolitosURO.add(boxElectrolitosSuperior);
        boxElectrolitosURO.add(panelElectrolitosSecundariosURO);
        boxElectrolitosURO.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        
        boxUroanalisis.add(boxElectrolitosURO);
        
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Examen completo de orina"));
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Microalbuminuria"));
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Proteinuria"));
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Relación ácido úrico/creatinina"));
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Relación albúmina/creatinina"));
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Relación calcio/creatinina"));
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Relación fósforo/creatinina"));
        boxUroanalisis.add(crear_Y_anadirExamenUroanalisis("Urea"));
        
        
        Box boxUroanalisisMacro = Box.createVerticalBox();
        boxUroanalisisMacro.add(panelEtiquetaUroanalisis);
        boxUroanalisisMacro.add(panelEtiquetasOrina);
        boxUroanalisisMacro.add(boxUroanalisis);
        boxUroanalisisMacro.add(Box.createVerticalStrut(5));
        
        
        HJPaintedPanel panelUroanalisis = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 10, 0), new Color(240,250,205), new Color(230,245,170), null, null, 100, true);
        panelUroanalisis.add(boxUroanalisisMacro);
        
        panelUroanalisis.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...... COPROANÁLISIS ......
        
        HJLabel lCoproanalisis = new HJLabel(COPROANALISIS);
        lCoproanalisis.setFont(formatoSubtitulos);
        lCoproanalisis.setForeground(new Color(5,35,40));
        
        JPanel panelEtiquetaCoproanalisis = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaCoproanalisis.add(lCoproanalisis);
        panelEtiquetaCoproanalisis.setOpaque(false);
        
        
        Box boxCoproanalisis = Box.createVerticalBox();
        boxCoproanalisis.add(crear_Y_anadirExamen("Azúcares reductores en heces", arrayExamenesCoproanalisis));
        boxCoproanalisis.add(crear_Y_anadirExamen("Examen completo de heces", arrayExamenesCoproanalisis));
        boxCoproanalisis.add(crear_Y_anadirExamen("Grasas en heces", arrayExamenesCoproanalisis));
        boxCoproanalisis.add(crear_Y_anadirExamen("Investigación de oxiuros", arrayExamenesCoproanalisis));
        boxCoproanalisis.add(crear_Y_anadirExamen("Método de concentración", arrayExamenesCoproanalisis));
        boxCoproanalisis.add(crear_Y_anadirExamen("Recuento de polimorfonucleares", arrayExamenesCoproanalisis));
        boxCoproanalisis.add(crear_Y_anadirExamen("Sangre oculta en heces", arrayExamenesCoproanalisis));
        boxCoproanalisis.add(crear_Y_anadirExamen("Seriado de heces", arrayExamenesCoproanalisis));
        
        
        Box boxCoproanalisisMacro = Box.createVerticalBox();
        boxCoproanalisisMacro.add(panelEtiquetaCoproanalisis);
        boxCoproanalisisMacro.add(boxCoproanalisis);
        boxCoproanalisisMacro.add(Box.createVerticalStrut(5));
        
        
        HJPaintedPanel panelCoproanalisis = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 10, 0), new Color(230,250,255), new Color(200,245,250), null, null, 100, true);
        panelCoproanalisis.add(boxCoproanalisisMacro);
        
        panelCoproanalisis.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...... QUÍMICA SANGUÍNEA ......
        
        HJLabel lQuimicaSanguinea = new HJLabel(QUIMICA_SANGUINEA);
        lQuimicaSanguinea.setFont(formatoSubtitulos);
        lQuimicaSanguinea.setForeground(new Color(50,10,60));
        
        JPanel panelEtiquetaQuimicaSanguinea = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaQuimicaSanguinea.add(lQuimicaSanguinea);
        panelEtiquetaQuimicaSanguinea.setOpaque(false);
        
        
        Box boxQuimicaSanguinea = Box.createVerticalBox();
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Ácido úrico", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Albúmina", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Aldolasa", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Amilasa", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Bilirrubina total y fraccionada", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("BUN", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("CK", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("CK-MB", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Colesterol HDL", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Colesterol LDL", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Colesterol total", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Colesterol VLDL", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Creatinina", arrayExamenesQuimicaSanguinea));
        
        HJCheckBox chbElectrolitosQS = new HJCheckBox("Electrolitos");
        HJCheckBox chbSodioQS = new HJCheckBox("Sodio");
        HJCheckBox chbPotasioQS = new HJCheckBox("Potasio");
        HJCheckBox chbCloroQS = new HJCheckBox("Cloro");
        HJCheckBox chbFosforoQS = new HJCheckBox("Fósforo");
        HJCheckBox chbMagnesioQS = new HJCheckBox("Magnesio");
        HJCheckBox[] chbSecundariosElectrolitosQS = {chbSodioQS, chbPotasioQS, chbCloroQS, chbFosforoQS, chbMagnesioQS};
        
        arrayExamenesQuimicaSanguinea.add(new Examen(chbElectrolitosQS, chbSecundariosElectrolitosQS));
        
        JPanel panelElectrolitosQS = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelElectrolitosQS.add(chbElectrolitosQS);
        panelElectrolitosQS.setOpaque(false);
        
        JPanel panelSodioQS = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSodioQS.add(chbSodioQS);
        panelSodioQS.setOpaque(false);
        
        JPanel panelFosforoQS = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelFosforoQS.add(chbFosforoQS);
        panelFosforoQS.setOpaque(false);
        
        Box boxVerticalSodioFosforoQS = Box.createVerticalBox();
        boxVerticalSodioFosforoQS.add(panelSodioQS);
        boxVerticalSodioFosforoQS.add(panelFosforoQS);
        
        JPanel panelPotasioQS = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelPotasioQS.add(chbPotasioQS);
        panelPotasioQS.setOpaque(false);
        
        JPanel panelMagnesioQS = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelMagnesioQS.add(chbMagnesioQS);
        panelMagnesioQS.setOpaque(false);
        
        Box boxVerticalPotasioMagnesioQS = Box.createVerticalBox();
        boxVerticalPotasioMagnesioQS.add(panelPotasioQS);
        boxVerticalPotasioMagnesioQS.add(panelMagnesioQS);
        
        JPanel panelCloroQS = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelCloroQS.add(chbCloroQS);
        panelCloroQS.setOpaque(false);
        
        Box boxVerticalCloroQS = Box.createVerticalBox();
        boxVerticalCloroQS.add(chbCloroQS);
        boxVerticalCloroQS.add(Box.createVerticalStrut(25));
        
        JPanel panelElectrolitosSecundariosQS = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelElectrolitosSecundariosQS.add(Box.createHorizontalStrut(18));
        panelElectrolitosSecundariosQS.add(boxVerticalSodioFosforoQS);
        panelElectrolitosSecundariosQS.add(Box.createHorizontalStrut(5));
        panelElectrolitosSecundariosQS.add(boxVerticalPotasioMagnesioQS);
        panelElectrolitosSecundariosQS.add(Box.createHorizontalStrut(5));
        panelElectrolitosSecundariosQS.add(boxVerticalCloroQS);
        panelElectrolitosSecundariosQS.setOpaque(false);
        
        Box boxElectrolitosQS = Box.createVerticalBox();
        boxElectrolitosQS.add(panelElectrolitosQS);
        boxElectrolitosQS.add(panelElectrolitosSecundariosQS);
        boxElectrolitosQS.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        
        boxQuimicaSanguinea.add(boxElectrolitosQS);
        
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Ferritina", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Fosfatasa ácida", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Fosfatasa alcalina", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("GGT (Gamma Glutamil Transferasa)", arrayExamenesQuimicaSanguinea));
        
        HJCheckBox chbGlicemia = new HJCheckBox("Glicemia");
        HJCheckBox chbAyuno = new HJCheckBox("Ayuno");
        HJCheckBox chbPostPandrialQS = new HJCheckBox("Post prandial");
        HJCheckBox chbCurvaT = new HJCheckBox("Curva T");
        HJCheckBox[] chbSecundariosGlicemia = {chbAyuno, chbPostPandrialQS, chbCurvaT};
        
        arrayExamenesQuimicaSanguinea.add(new Examen(chbGlicemia, chbSecundariosGlicemia));
        
        JPanel panelGlicemia = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelGlicemia.add(chbGlicemia);
        panelGlicemia.setOpaque(false);
        
        JPanel panelGlicemiaSecundarios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelGlicemiaSecundarios.add(Box.createHorizontalStrut(18));
        panelGlicemiaSecundarios.add(chbAyuno);
        panelGlicemiaSecundarios.add(Box.createHorizontalStrut(5));
        panelGlicemiaSecundarios.add(chbPostPandrialQS);
        panelGlicemiaSecundarios.add(Box.createHorizontalStrut(5));
        panelGlicemiaSecundarios.add(chbCurvaT);
        panelGlicemiaSecundarios.setOpaque(false);
        
        Box boxGlicemia = Box.createVerticalBox();
        boxGlicemia.add(panelGlicemia);
        boxGlicemia.add(panelGlicemiaSecundarios);
        boxGlicemia.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        
        boxQuimicaSanguinea.add(boxGlicemia);
        
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Hemoglobina glicosilada", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Hierro sérico y captación", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("LDH", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Lipasa", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Lípidos totales", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Proteínas totales y fraccionadas", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Prueba de tolerancia glucosada", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("TGO/AST", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("TGP/ALT", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Transferrina", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Triglicéridos", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Troponina cuantificada", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Troponina I", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Urea", arrayExamenesQuimicaSanguinea));
        boxQuimicaSanguinea.add(crear_Y_anadirExamen("Vitamina B12", arrayExamenesQuimicaSanguinea));
        
        
        Box boxQuimicaSanguineaMacro = Box.createVerticalBox();
        boxQuimicaSanguineaMacro.add(panelEtiquetaQuimicaSanguinea);
        boxQuimicaSanguineaMacro.add(boxQuimicaSanguinea);
        boxQuimicaSanguineaMacro.add(Box.createVerticalStrut(5));
        
        
        HJPaintedPanel panelQuimicaSanguinea = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 10, 0), new Color(245,225,250), new Color(240,200,250), null, null, 100, true);
        panelQuimicaSanguinea.add(boxQuimicaSanguineaMacro);
        
        panelQuimicaSanguinea.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...... PERFILES ......
        
        HJLabel lPerfiles = new HJLabel(PERFILES);
        lPerfiles.setFont(formatoSubtitulos);
        lPerfiles.setForeground(new Color(50,50,5));
        
        JPanel panelEtiquetaPerfiles = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaPerfiles.add(lPerfiles);
        panelEtiquetaPerfiles.setOpaque(false);
        
        
        Box boxPerfiles = Box.createVerticalBox();
        boxPerfiles.add(crear_Y_anadirExamen("Anemia", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Arterosclerosis", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Básico", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Básico con vitaminas", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Bioquímico", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Cardiovascular", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Cuagulación", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Diabetes", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Diarrea", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Enfermedad celíaca", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Estrés", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Fiebre", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("General", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Ginecológico", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Hepático ampliado", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Hepático básico", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Hipertensión", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Hormonal femenino", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Hormonal masculino", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Lipídico", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Metabolismo óseo", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Obesidad y diabetes", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Pre-natal", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Pre-natal II", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Pre-operatorio", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Protéico", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Renal", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Reumatológico", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Riesgo fetal", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Rutina", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Tiroideo I", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Tiroideo II", arrayExamenesPerfiles));
        boxPerfiles.add(crear_Y_anadirExamen("Trombosis", arrayExamenesPerfiles));
        
        
        Box boxPerfilesMacro = Box.createVerticalBox();
        boxPerfilesMacro.add(panelEtiquetaPerfiles);
        boxPerfilesMacro.add(boxPerfiles);
        boxPerfilesMacro.add(Box.createVerticalStrut(5));
        
        
        HJPaintedPanel panelPerfiles = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 10, 0), new Color(250,250,200), new Color(245,245,150), null, null, 100, true);
        panelPerfiles.add(boxPerfilesMacro);
        
        panelPerfiles.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...... BACTERIOLOGÍA ......
        
        HJLabel lBacteriologia = new HJLabel(BACTERIOLOGIA);
        lBacteriologia.setFont(formatoSubtitulos);
        lBacteriologia.setForeground(new Color(5,35,5));
        
        JPanel panelEtiquetaBacteriologia = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaBacteriologia.add(lBacteriologia);
        panelEtiquetaBacteriologia.setOpaque(false);
        
        
        Box boxBacteriologia = Box.createVerticalBox();
        
        HJCheckBox chbBK = new HJCheckBox("BK");
        HJCheckBox chbDirecto = new HJCheckBox("Directo");
        HJCheckBox chbCultivoB = new HJCheckBox("Cultivo");
        HJCheckBox chbEsputo = new HJCheckBox("Esputo");
        HJCheckBox chbOrina = new HJCheckBox("Orina");
        HJCheckBox[] chbSecundariosBK = {chbDirecto, chbCultivoB, chbEsputo, chbOrina};
        
        arrayExamenesBacteriologia.add(new Examen(chbBK, chbSecundariosBK));
        
        JPanel panelBK = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelBK.add(chbBK);
        panelBK.setOpaque(false);
        
        JPanel panelDirecto = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelDirecto.add(chbDirecto);
        panelDirecto.setOpaque(false);
        
        JPanel panelCultivoB = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelCultivoB.add(chbCultivoB);
        panelCultivoB.setOpaque(false);
        
        Box boxVerticalDirectoCultivo = Box.createVerticalBox();
        boxVerticalDirectoCultivo.add(panelDirecto);
        boxVerticalDirectoCultivo.add(panelCultivoB);
        
        JPanel panelEsputo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelEsputo.add(chbEsputo);
        panelEsputo.setOpaque(false);
        
        JPanel panelOrina = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelOrina.add(chbOrina);
        panelOrina.setOpaque(false);
        
        Box boxVerticalEsputoOrina = Box.createVerticalBox();
        boxVerticalEsputoOrina.add(panelEsputo);
        boxVerticalEsputoOrina.add(panelOrina);
        
        JPanel panelBKSecundarios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelBKSecundarios.add(Box.createHorizontalStrut(18));
        panelBKSecundarios.add(boxVerticalDirectoCultivo);
        panelBKSecundarios.add(Box.createHorizontalStrut(5));
        panelBKSecundarios.add(boxVerticalEsputoOrina);
        panelBKSecundarios.setOpaque(false);
        
        Box boxBK = Box.createVerticalBox();
        boxBK.add(panelBK);
        boxBK.add(panelBKSecundarios);
        boxBK.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        
        boxBacteriologia.add(boxBK);
        
        boxBacteriologia.add(crear_Y_anadirExamen("Coloración de Gram", arrayExamenesBacteriologia));
        boxBacteriologia.add(crear_Y_anadirExamen("Coprocultivo", arrayExamenesBacteriologia));
        boxBacteriologia.add(crear_Y_anadirExamen("Espermocultivo", arrayExamenesBacteriologia));
        boxBacteriologia.add(crear_Y_anadirExamen("Exudado faríngeo", arrayExamenesBacteriologia));
        boxBacteriologia.add(crear_Y_anadirExamen("Hemocultivo", arrayExamenesBacteriologia));
        boxBacteriologia.add(crear_Y_anadirExamen("Secreción conjuntival", arrayExamenesBacteriologia));
        boxBacteriologia.add(crear_Y_anadirExamen("Secreción ótica", arrayExamenesBacteriologia));
        
        HJCheckBox chbSecrecionUretral = new HJCheckBox("Secreción uretral");
        HJCheckBox chbFrescoSU = new HJCheckBox("Fresco");
        HJCheckBox chbCultivoSU = new HJCheckBox("Cultivo");
        HJCheckBox[] chbSecundariosSecrecionUretral = {chbFrescoSU, chbCultivoSU};
        
        arrayExamenesBacteriologia.add(new Examen(chbSecrecionUretral, chbSecundariosSecrecionUretral));
        
        JPanel panelSecrecionUretral = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSecrecionUretral.add(chbSecrecionUretral);
        panelSecrecionUretral.setOpaque(false);
        
        JPanel panelSecrecionUretralSecundarios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSecrecionUretralSecundarios.add(Box.createHorizontalStrut(18));
        panelSecrecionUretralSecundarios.add(chbFrescoSU);
        panelSecrecionUretralSecundarios.add(Box.createHorizontalStrut(5));
        panelSecrecionUretralSecundarios.add(chbCultivoSU);
        panelSecrecionUretralSecundarios.setOpaque(false);
        
        Box boxSecrecionUretral = Box.createVerticalBox();
        boxSecrecionUretral.add(panelSecrecionUretral);
        boxSecrecionUretral.add(panelSecrecionUretralSecundarios);
        boxSecrecionUretral.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        
        boxBacteriologia.add(boxSecrecionUretral);
        
        HJCheckBox chbSecrecionVaginal = new HJCheckBox("Secreción vaginal");
        HJCheckBox chbFrescoSV = new HJCheckBox("Fresco");
        HJCheckBox chbCultivoSV = new HJCheckBox("Cultivo");
        HJCheckBox[] chbSecundariosSecrecionVaginal = {chbFrescoSV, chbCultivoSV};
        
        arrayExamenesBacteriologia.add(new Examen(chbSecrecionVaginal, chbSecundariosSecrecionVaginal));
        
        JPanel panelSecrecionVaginal = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSecrecionVaginal.add(chbSecrecionVaginal);
        panelSecrecionVaginal.setOpaque(false);
        
        JPanel panelSecrecionVaginalSecundarios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSecrecionVaginalSecundarios.add(Box.createHorizontalStrut(18));
        panelSecrecionVaginalSecundarios.add(chbFrescoSV);
        panelSecrecionVaginalSecundarios.add(Box.createHorizontalStrut(5));
        panelSecrecionVaginalSecundarios.add(chbCultivoSV);
        panelSecrecionVaginalSecundarios.setOpaque(false);
        
        Box boxSecrecionVaginal = Box.createVerticalBox();
        boxSecrecionVaginal.add(panelSecrecionVaginal);
        boxSecrecionVaginal.add(panelSecrecionVaginalSecundarios);
        boxSecrecionVaginal.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        
        boxBacteriologia.add(boxSecrecionVaginal);
        
        boxBacteriologia.add(crear_Y_anadirExamen("Urocultivo", arrayExamenesBacteriologia));
        
        
        Box boxBacteriologiaMacro = Box.createVerticalBox();
        boxBacteriologiaMacro.add(panelEtiquetaBacteriologia);
        boxBacteriologiaMacro.add(boxBacteriologia);
        boxBacteriologiaMacro.add(Box.createVerticalStrut(5));
        
        
        HJPaintedPanel panelBacteriologia = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 10, 0), new Color(205,250,200), new Color(180,250,175), null, null, 100, true);
        panelBacteriologia.add(boxBacteriologiaMacro);
        
        panelBacteriologia.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...... HORMONAS ......
        
        HJLabel lHormonas = new HJLabel(HORMONAS);
        lHormonas.setFont(formatoSubtitulos);
        lHormonas.setForeground(new Color(55,35,5));
        
        JPanel panelEtiquetaHormonas = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaHormonas.add(lHormonas);
        panelEtiquetaHormonas.setOpaque(false);
        
        
        Box boxHormonas = Box.createVerticalBox();
        boxHormonas.add(crear_Y_anadirExamen("17 Ceto", arrayExamenesHormonas));
        boxHormonas.add(crear_Y_anadirExamen("17 OH", arrayExamenesHormonas));
        boxHormonas.add(crear_Y_anadirExamen("Cortisol", arrayExamenesHormonas));
        boxHormonas.add(crear_Y_anadirExamen("Estradiol", arrayExamenesHormonas));
        boxHormonas.add(crear_Y_anadirExamen("FSH", arrayExamenesHormonas));
        boxHormonas.add(crear_Y_anadirExamen("HCG Beta", arrayExamenesHormonas));
        boxHormonas.add(crear_Y_anadirExamen("Hormona de crecimiento", arrayExamenesHormonas));
        
        HJCheckBox chbInsulina = new HJCheckBox("Insulina");
        HJCheckBox chbBasal = new HJCheckBox("Basal");
        HJCheckBox chbPostPrandialH = new HJCheckBox("Post prandial");
        HJCheckBox[] chbSecundariosInsulina = {chbBasal, chbPostPrandialH};
        
        arrayExamenesHormonas.add(new Examen(chbInsulina, chbSecundariosInsulina));
        
        JPanel panelInsulina = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelInsulina.add(chbInsulina);
        panelInsulina.setOpaque(false);
        
        JPanel panelInsulinaSecundarios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelInsulinaSecundarios.add(Box.createHorizontalStrut(18));
        panelInsulinaSecundarios.add(chbBasal);
        panelInsulinaSecundarios.add(Box.createHorizontalStrut(5));
        panelInsulinaSecundarios.add(chbPostPrandialH);
        panelInsulinaSecundarios.setOpaque(false);
        
        Box boxInsulina = Box.createVerticalBox();
        boxInsulina.add(panelInsulina);
        boxInsulina.add(panelInsulinaSecundarios);
        boxInsulina.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        
        boxHormonas.add(boxInsulina);
        
        boxHormonas.add(crear_Y_anadirExamen("LH", arrayExamenesHormonas));
        boxHormonas.add(crear_Y_anadirExamen("Progesterona", arrayExamenesHormonas));
        boxHormonas.add(crear_Y_anadirExamen("Prolactina", arrayExamenesHormonas));
        boxHormonas.add(crear_Y_anadirExamen("Tetosterona", arrayExamenesHormonas));
        
        
        Box boxHormonasMacro = Box.createVerticalBox();
        boxHormonasMacro.add(panelEtiquetaHormonas);
        boxHormonasMacro.add(boxHormonas);
        boxHormonasMacro.add(Box.createVerticalStrut(5));
        
        
        HJPaintedPanel panelHormonas = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 10, 0), new Color(250,230,200), new Color(245,205,150), null, null, 100, true);
        panelHormonas.add(boxHormonasMacro);
        
        panelHormonas.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...... ELECTROFÉRESIS ......
        
        HJLabel lElectroferesis = new HJLabel(ELECTROFERESIS);
        lElectroferesis.setFont(formatoSubtitulos);
        lElectroferesis.setForeground(new Color(20,10,70));
        
        JPanel panelEtiquetaElectroferesis = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaElectroferesis.add(lElectroferesis);
        panelEtiquetaElectroferesis.setOpaque(false);
        
        
        Box boxElectroferesis = Box.createVerticalBox();
        boxElectroferesis.add(crear_Y_anadirExamen("Hemoglobina", arrayExamenesElectroferesis));
        boxElectroferesis.add(crear_Y_anadirExamen("Lipoproteínas", arrayExamenesElectroferesis));
        boxElectroferesis.add(crear_Y_anadirExamen("Proteínas", arrayExamenesElectroferesis));
        
        
        Box boxElectroferesisMacro = Box.createVerticalBox();
        boxElectroferesisMacro.add(panelEtiquetaElectroferesis);
        boxElectroferesisMacro.add(boxElectroferesis);
        boxElectroferesisMacro.add(Box.createVerticalStrut(5));
        
        
        HJPaintedPanel panelElectroferesis = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 10, 0), new Color(220,215,250), new Color(205,200,250), null, null, 100, true);
        panelElectroferesis.add(boxElectroferesisMacro);
        
        panelElectroferesis.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...... MISCELÁNEOS ......
        
        HJLabel lMiscelaneos = new HJLabel(MISCELANEOS);
        lMiscelaneos.setFont(formatoSubtitulos);
        lMiscelaneos.setForeground(new Color(40,40,40));
        
        JPanel panelEtiquetaMiscelaneos = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaMiscelaneos.add(lMiscelaneos);
        panelEtiquetaMiscelaneos.setOpaque(false);
        
        
        Box boxMiscelaneos = Box.createVerticalBox();
        boxMiscelaneos.add(crear_Y_anadirExamen("CA-15-3", arrayExamenesMiscelaneos));
        boxMiscelaneos.add(crear_Y_anadirExamen("CA-19-9", arrayExamenesMiscelaneos));
        boxMiscelaneos.add(crear_Y_anadirExamen("CA-125", arrayExamenesMiscelaneos));
        boxMiscelaneos.add(crear_Y_anadirExamen("Citoquímico de líquidos corporales", arrayExamenesMiscelaneos));
        boxMiscelaneos.add(crear_Y_anadirExamen("Espermatograma", arrayExamenesMiscelaneos));
        boxMiscelaneos.add(crear_Y_anadirExamen("Gasometría arterial", arrayExamenesMiscelaneos));
        boxMiscelaneos.add(crear_Y_anadirExamen("Gasometría venosa", arrayExamenesMiscelaneos));
        boxMiscelaneos.add(crear_Y_anadirExamen("HCG (embarazo)", arrayExamenesMiscelaneos));
        boxMiscelaneos.add(crear_Y_anadirExamen("HCG latex", arrayExamenesMiscelaneos));
        
        
        Box boxMiscelaneosMacro = Box.createVerticalBox();
        boxMiscelaneosMacro.add(panelEtiquetaMiscelaneos);
        boxMiscelaneosMacro.add(boxMiscelaneos);
        boxMiscelaneosMacro.add(Box.createVerticalStrut(5));
        
        
        HJPaintedPanel panelMiscelaneos = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 10, 0), new Color(230,230,230), new Color(200,200,200), null, null, 100, true);
        panelMiscelaneos.add(boxMiscelaneosMacro);
        
        panelMiscelaneos.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...... INMUNOLOGÍA ......
        
        HJLabel lInmunologia = new HJLabel(INMUNOLOGIA);
        lInmunologia.setFont(formatoSubtitulos);
        lInmunologia.setForeground(new Color(10,30,55));
        
        JPanel panelEtiquetaInmunologia = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaInmunologia.add(lInmunologia);
        panelEtiquetaInmunologia.setOpaque(false);
        
        
        HJCustomizedButton bAyudaInmunologia = new HJCustomizedButton("AyudaNormal.png", "AyudaNormal.png", -1, 14, true);
        bAyudaInmunologia.addActionListener( e -> HJDialog.mostrarMensaje("Ayuda", new String[] {"G: IgG", "M: IgM", "A: IgA", "E: IgE", "S.C.: Serología Completa", "Ctda.: Cuantificada", "Lát.: Látex"}, IconoDeImagen.INFORMACION, null) );        
        
        JPanel panelEtiquetasIg = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelEtiquetasIg.add(bAyudaInmunologia);
        panelEtiquetasIg.add(Box.createHorizontalStrut(14));
        panelEtiquetasIg.add(new HJLabel("Ig:"));
        panelEtiquetasIg.add(Box.createHorizontalStrut(10));
        panelEtiquetasIg.add(new HJLabel("G"));
        panelEtiquetasIg.add(Box.createHorizontalStrut(9));
        panelEtiquetasIg.add(new HJLabel("M"));
        panelEtiquetasIg.add(Box.createHorizontalStrut(10));
        panelEtiquetasIg.add(new HJLabel("A"));
        panelEtiquetasIg.add(Box.createHorizontalStrut(11));
        panelEtiquetasIg.add(new HJLabel("E"));
        panelEtiquetasIg.add(Box.createHorizontalStrut(14));
        panelEtiquetasIg.add(new HJLabel("S.C."));
        panelEtiquetasIg.add(Box.createHorizontalStrut(14));
        panelEtiquetasIg.add(new HJLabel("Ctda."));
        panelEtiquetasIg.add(Box.createHorizontalStrut(7));
        panelEtiquetasIg.add(new HJLabel("Lát."));
        panelEtiquetasIg.setOpaque(false);
        
        
        Box boxInmunologia = Box.createVerticalBox();
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Adenovirus", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Anticore", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Anticuerpos antinucleares", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Anticuerpos antitiroideos", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("ANTIDNA", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Antimitocondrial (AMA)", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Asto", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Cardiolipinas", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("CEA", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Células LE", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Chagas", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Chlamydia Pneumoniae", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Chlamydia Trachomatis", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Citomegalovirus", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Crioglobulinas", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Crioglutininas", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Dengue", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Epstein Barr", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Factor reumatoideo", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("FTA (ABS)", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Helicobacter Pylori", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Hepatitis A", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Hepatitis B", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Hepatitis C", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Herpes Virus 1", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Herpes Virus 2", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("HIV", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Inmunoglobulina", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("JO-1", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Mononucleosis", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Mycoplasma Pneumoniae", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Parvovirus", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Peroxidasa", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Proteína C reactiva", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("PSA", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("PSA libre", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("RA Test", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Rotavirus", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("RPR", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Rubeola", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("SM", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("SSB (LA)", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("SSB (RO)", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Tiroglobulinas", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Toxoplasmosis", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("VDRL", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("VPH", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("Widal", true));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("C3", false));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("C4", false));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("CH50", false));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("IgG", false));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("IgM", false));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("IgA", false));
        boxInmunologia.add(crear_Y_anadirExamenInmunologia("IgE", false));
        
        
        Box boxInmunologiaMacro = Box.createVerticalBox();
        boxInmunologiaMacro.add(panelEtiquetaInmunologia);
        boxInmunologiaMacro.add(panelEtiquetasIg);
        boxInmunologiaMacro.add(boxInmunologia);
        boxInmunologiaMacro.add(Box.createVerticalStrut(5));
        
        
        HJPaintedPanel panelInmunologia = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 10, 0), new Color(225,235,250), new Color(200,215,250), null, null, 100, true);
        panelInmunologia.add(boxInmunologiaMacro);
        
        panelInmunologia.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...... DROGAS ......
        
        HJLabel lDrogas = new HJLabel(DROGAS);
        lDrogas.setFont(formatoSubtitulos);
        lDrogas.setForeground(Colores.TEXTO);
        
        JPanel panelEtiquetaDrogas = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaDrogas.add(lDrogas);
        panelEtiquetaDrogas.setOpaque(false);
        
        
        taDrogas = new HJTextArea(1, 1);
        
        HJScrollPane scrollDrogas = new HJScrollPane(taDrogas);
        scrollDrogas.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollDrogas.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDrogas.setPreferredSize(new Dimension(400, 300));
        scrollDrogas.removerEscuchadorRuedaRaton();
        
        JPanel panelDrogas = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelDrogas.add(scrollDrogas);
        panelDrogas.setOpaque(false);
        
        
        Box boxDrogas = Box.createVerticalBox();
        boxDrogas.add(panelEtiquetaDrogas);
        boxDrogas.add(panelDrogas);
        boxDrogas.add(Box.createVerticalStrut(5));
        
        boxDrogas.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...... OTROS ......
        
        HJLabel lOtros = new HJLabel(OTROS);
        lOtros.setFont(formatoSubtitulos);
        lOtros.setForeground(Colores.TEXTO);
        
        JPanel panelEtiquetaOtros = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelEtiquetaOtros.add(lOtros);
        panelEtiquetaOtros.setOpaque(false);
        
        
        taOtros = new HJTextArea(1, 1);
        
        HJScrollPane scrollOtros = new HJScrollPane(taOtros);
        scrollOtros.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollOtros.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollOtros.setPreferredSize(new Dimension(400, 300));
        scrollOtros.removerEscuchadorRuedaRaton();
        
        JPanel panelOtros = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelOtros.add(scrollOtros);
        panelOtros.setOpaque(false);
        
        
        Box boxOtros = Box.createVerticalBox();
        boxOtros.add(panelEtiquetaOtros);
        boxOtros.add(panelOtros);
        boxOtros.add(Box.createVerticalStrut(5));
        
        boxOtros.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 2));
        
        
        //...........................
        
        Box boxVertical_1 = Box.createVerticalBox();
        boxVertical_1.add(panelHematologia);
        boxVertical_1.add(Box.createVerticalStrut(20));
        boxVertical_1.add(panelUroanalisis);
        boxVertical_1.add(Box.createVerticalStrut(20));
        boxVertical_1.add(panelCoproanalisis);
        boxVertical_1.add(Box.createVerticalStrut(20));
        boxVertical_1.add(panelQuimicaSanguinea);
        
        Box boxVertical_2 = Box.createVerticalBox();
        boxVertical_2.add(panelPerfiles);
        boxVertical_2.add(Box.createVerticalStrut(52));
        boxVertical_2.add(panelBacteriologia);
        boxVertical_2.add(Box.createVerticalStrut(53));
        boxVertical_2.add(panelHormonas);
        boxVertical_2.add(Box.createVerticalStrut(53));
        boxVertical_2.add(panelElectroferesis);
        boxVertical_2.add(Box.createVerticalStrut(53));
        boxVertical_2.add(panelMiscelaneos);
        
        Box boxVertical_3 = Box.createVerticalBox();
        boxVertical_3.add(panelInmunologia);
        boxVertical_3.add(Box.createVerticalStrut(67));
        boxVertical_3.add(boxDrogas);
        boxVertical_3.add(Box.createVerticalStrut(67));
        boxVertical_3.add(boxOtros);
        
        
        JPanel panelExamenesLaboratorio = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelExamenesLaboratorio.add(boxVertical_1);
        panelExamenesLaboratorio.add(boxVertical_2);
        panelExamenesLaboratorio.add(boxVertical_3);
        panelExamenesLaboratorio.setOpaque(false);
           
        JScrollPane scrollExamenesLaboratorio = new JScrollPane(panelExamenesLaboratorio);
        scrollExamenesLaboratorio.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollExamenesLaboratorio.setPreferredSize(new Dimension(1190, 615));
        scrollExamenesLaboratorio.getVerticalScrollBar().setUnitIncrement(10);
        scrollExamenesLaboratorio.setOpaque(false);
        scrollExamenesLaboratorio.getViewport().setOpaque(false);
        
        
        //....
        
        arrayDeArraysExamen.add(arrayExamenesHematologia);
        arrayDeArraysExamen.add(arrayExamenesCoproanalisis);
        arrayDeArraysExamen.add(arrayExamenesQuimicaSanguinea);
        arrayDeArraysExamen.add(arrayExamenesPerfiles);
        arrayDeArraysExamen.add(arrayExamenesBacteriologia);
        arrayDeArraysExamen.add(arrayExamenesHormonas);
        arrayDeArraysExamen.add(arrayExamenesElectroferesis);
        arrayDeArraysExamen.add(arrayExamenesMiscelaneos);
        
        
        //..........................
        
        HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
        bAceptar.addActionListener( e -> aceptar() );
        
        HJButton bLimpiar = new HJButton(new IconoDeImagen("Limpiar.png", -1, 20), "Limpiar", Colores.COLORES_BOTONES);
        bLimpiar.addActionListener( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea deseleccionar todos los exámenes?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
                limpiar();
        });
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.add(bAceptar);
        panelBotones.add(bLimpiar);
        panelBotones.setOpaque(false);
        
        
        //..........................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(scrollExamenesLaboratorio);
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private JPanel crear_Y_anadirExamen(String examen, ArrayList<Examen> arrayExamenes) {
        
        HJCheckBox chbExamen = new HJCheckBox(examen);
        
        arrayExamenes.add(new Examen(chbExamen, null));
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.add(chbExamen);
        panel.setOpaque(false);
        
        return panel;
    }
    
    
    private Box crear_Y_anadirExamenUroanalisis(String examen) {
        
        HJCheckBox chbExamen = new HJCheckBox(examen);
        
        HJCheckBox chbOrinaParcial = new HJCheckBox();
        chbOrinaParcial.setName("Orina parcial");
        
        HJCheckBox chbOrina24Hrs = new HJCheckBox();
        chbOrina24Hrs.setName("Orina 24 Hrs.");
        
        arrayExamenesUroanalisis.add(new ExamenUroanalisis(chbExamen, null, chbOrinaParcial, chbOrina24Hrs));
        
        JPanel panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelIzquierdo.add(chbExamen);
        panelIzquierdo.setOpaque(false);
        
        JPanel panelDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelDerecho.add(Box.createHorizontalStrut(10));
        panelDerecho.add(chbOrinaParcial);
        panelDerecho.add(Box.createHorizontalStrut(25));
        panelDerecho.add(chbOrina24Hrs);
        panelDerecho.add(Box.createHorizontalStrut(10));
        panelDerecho.setOpaque(false);
        
        Box box = Box.createHorizontalBox();
        box.add(panelIzquierdo);
        box.add(panelDerecho);
        
        return box;
    }
    
    
    private Box crear_Y_anadirExamenInmunologia(String examen, boolean conExamenesSecundarios) {
        
        HJCheckBox chbExamen = new HJCheckBox(examen);
        
        
        if(conExamenesSecundarios)
        {
            HJCheckBox chbIgG = new HJCheckBox();
            chbIgG.setName("IgG");
            
            HJCheckBox chbIgM = new HJCheckBox();
            chbIgM.setName("IgM");
            
            HJCheckBox chbIgA = new HJCheckBox();
            chbIgA.setName("IgA");
            
            HJCheckBox chbIgE = new HJCheckBox();
            chbIgE.setName("IgE");
            
            HJCheckBox chbSC = new HJCheckBox();
            chbSC.setName("Serología completa");
            
            HJCheckBox[] chbSecundarios = {chbIgG, chbIgM, chbIgA, chbIgE, chbSC};
            
            
            HJCheckBox chbCtda = new HJCheckBox();
            chbCtda.setName("Cuantificada");
            
            HJCheckBox chbLat = new HJCheckBox();
            chbLat.setName("Látex");
            
            
            arrayExamenesInmunologia.add(new ExamenInmunologia(chbExamen, chbSecundarios, chbCtda, chbLat));
            
            
            JPanel panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panelIzquierdo.add(chbExamen);
            panelIzquierdo.setOpaque(false);
            
            JPanel panelDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            panelDerecho.add(Box.createHorizontalStrut(10));
            panelDerecho.add(chbIgG);
            panelDerecho.add(chbIgM);
            panelDerecho.add(chbIgA);
            panelDerecho.add(chbIgE);
            panelDerecho.add(Box.createHorizontalStrut(11));
            panelDerecho.add(chbSC);
            panelDerecho.add(Box.createHorizontalStrut(25));
            panelDerecho.add(chbCtda);
            panelDerecho.add(Box.createHorizontalStrut(15));
            panelDerecho.add(chbLat);
            panelDerecho.add(Box.createHorizontalStrut(1));
            panelDerecho.setOpaque(false);
            
            Box box = Box.createHorizontalBox();
            box.add(panelIzquierdo);
            box.add(panelDerecho);
            
            return box;
            
        }else{
            
            arrayExamenesInmunologia.add(new ExamenInmunologia(chbExamen, null, null, null));
            
            JPanel panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panelIzquierdo.add(chbExamen);
            panelIzquierdo.setOpaque(false);
            
            Box box = Box.createHorizontalBox();
            box.add(panelIzquierdo);
            
            return box;
        }
    }
    
    
    //...........................
    
    
    private void aceptar() {
        
        mapaExamenes.clear();
        
        
        ArrayList<String> arrayHematologia = new ArrayList<>(0);
        
        for(int i=0 ; i<=arrayExamenesHematologia.size()-1 ; i++)
        {
            if(arrayExamenesHematologia.get(i).estaMarcado())
                arrayHematologia.add(arrayExamenesHematologia.get(i).obtenerExamen());
        }
        
        if(arrayHematologia.size()>0)  mapaExamenes.put(HEMATOLOGIA, arrayHematologia);
        
        
        ArrayList<String> arrayUroanalisis = new ArrayList<>(0);
        
        for(int i=0 ; i<=arrayExamenesUroanalisis.size()-1 ; i++)
        {
            if(arrayExamenesUroanalisis.get(i).estaMarcado())
                arrayUroanalisis.add(arrayExamenesUroanalisis.get(i).obtenerExamen());
        }
        
        if(arrayUroanalisis.size()>0)  mapaExamenes.put(UROANALISIS, arrayUroanalisis);
        
        
        ArrayList<String> arrayCoproanalisis = new ArrayList<>(0);
        
        for(int i=0 ; i<=arrayExamenesCoproanalisis.size()-1 ; i++)
        {
            if(arrayExamenesCoproanalisis.get(i).estaMarcado())
                arrayCoproanalisis.add(arrayExamenesCoproanalisis.get(i).obtenerExamen());
        }
        
        if(arrayCoproanalisis.size()>0)  mapaExamenes.put(COPROANALISIS, arrayCoproanalisis);
        
        
        ArrayList<String> arrayQuimicaSanguinea = new ArrayList<>(0);
        
        for(int i=0 ; i<=arrayExamenesQuimicaSanguinea.size()-1 ; i++)
        {
            if(arrayExamenesQuimicaSanguinea.get(i).estaMarcado())
                arrayQuimicaSanguinea.add(arrayExamenesQuimicaSanguinea.get(i).obtenerExamen());
        }
        
        if(arrayQuimicaSanguinea.size()>0)  mapaExamenes.put(QUIMICA_SANGUINEA, arrayQuimicaSanguinea);
        
        
        ArrayList<String> arrayPerfiles = new ArrayList<>(0);
        
        for(int i=0 ; i<=arrayExamenesPerfiles.size()-1 ; i++)
        {
            if(arrayExamenesPerfiles.get(i).estaMarcado())
                arrayPerfiles.add(arrayExamenesPerfiles.get(i).obtenerExamen());
        }
        
        if(arrayPerfiles.size()>0)  mapaExamenes.put(PERFILES, arrayPerfiles);
        
        
        ArrayList<String> arrayBacteriologia = new ArrayList<>(0);
        
        for(int i=0 ; i<=arrayExamenesBacteriologia.size()-1 ; i++)
        {
            if(arrayExamenesBacteriologia.get(i).estaMarcado())
                arrayBacteriologia.add(arrayExamenesBacteriologia.get(i).obtenerExamen());
        }
        
        if(arrayBacteriologia.size()>0)  mapaExamenes.put(BACTERIOLOGIA, arrayBacteriologia);
        
        
        ArrayList<String> arrayHormonas = new ArrayList<>(0);
        
        for(int i=0 ; i<=arrayExamenesHormonas.size()-1 ; i++)
        {
            if(arrayExamenesHormonas.get(i).estaMarcado())
                arrayHormonas.add(arrayExamenesHormonas.get(i).obtenerExamen());
        }
        
        if(arrayHormonas.size()>0)  mapaExamenes.put(HORMONAS, arrayHormonas);
        
        
        ArrayList<String> arrayElectroferesis = new ArrayList<>(0);
        
        for(int i=0 ; i<=arrayExamenesElectroferesis.size()-1 ; i++)
        {
            if(arrayExamenesElectroferesis.get(i).estaMarcado())
                arrayElectroferesis.add(arrayExamenesElectroferesis.get(i).obtenerExamen());
        }
        
        if(arrayElectroferesis.size()>0)  mapaExamenes.put(ELECTROFERESIS, arrayElectroferesis);
        
        
        ArrayList<String> arrayInmunologia = new ArrayList<>(0);
        
        for(int i=0 ; i<=arrayExamenesInmunologia.size()-1 ; i++)
        {
            if(arrayExamenesInmunologia.get(i).estaMarcado())
                arrayInmunologia.add(arrayExamenesInmunologia.get(i).obtenerExamen());
        }
        
        if(arrayInmunologia.size()>0)  mapaExamenes.put(INMUNOLOGIA, arrayInmunologia);
        
        
        ArrayList<String> arrayMiscelaneos = new ArrayList<>(0);
        
        for(int i=0 ; i<=arrayExamenesMiscelaneos.size()-1 ; i++)
        {
            if(arrayExamenesMiscelaneos.get(i).estaMarcado())
                arrayMiscelaneos.add(arrayExamenesMiscelaneos.get(i).obtenerExamen());
        }
        
        if(arrayMiscelaneos.size()>0)  mapaExamenes.put(MISCELANEOS, arrayMiscelaneos);
        
        
        if(taDrogas.esTextoValido())
        {
            ArrayList<String> arrayDrogas = new ArrayList<>(0);
            arrayDrogas.add(taDrogas.getText());
            
            mapaExamenes.put(DROGAS, arrayDrogas);
        }
        
        
        if(taOtros.esTextoValido())
        {
            ArrayList<String> arrayOtros = new ArrayList<>(0);
            arrayOtros.add(taOtros.getText());
            
            mapaExamenes.put(OTROS, arrayOtros);
        }
        
        
        setVisible(false);
    }
    
    
    public HashMap<String,ArrayList<String>> obtenerMapaExamenes() { return mapaExamenes; }
    
    
    private void limpiar() {
        
        for(int i=0 ; i<=arrayDeArraysExamen.size()-1 ; i++)
        {
            ArrayList<Examen> arrayExamen= arrayDeArraysExamen.get(i);
            
            for(int j=0 ; j<=arrayExamen.size()-1 ; j++)
            {
                arrayExamen.get(j).limpiar();
            }
        }
        
        for(int i=0 ; i<=arrayExamenesUroanalisis.size()-1 ; i++)
        {
            arrayExamenesUroanalisis.get(i).limpiar();
        }
        
        for(int i=0 ; i<=arrayExamenesInmunologia.size()-1 ; i++)
        {
            arrayExamenesInmunologia.get(i).limpiar();
        }
        
        taDrogas.limpiar();
        
        taOtros.limpiar();
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class Examen {
        
        private final HJCheckBox chbExamenPrincipal;
        
        private final HJCheckBox[] chbExamenesSecundarios;
        
        
        public Examen(HJCheckBox principal, HJCheckBox[] secundarios) {
            
            chbExamenPrincipal = principal;
            
            chbExamenesSecundarios = secundarios;
            
            
            if(chbExamenesSecundarios!=null)
            {
                chbExamenPrincipal.addActionListener( e -> {
                    
                    if(chbExamenPrincipal.isSelected()==false)
                    {
                        for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                        {
                            chbExamenesSecundarios[i].setSelected(false);
                        }
                    }
                });
                
                for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                {
                    chbExamenesSecundarios[i].addActionListener( e -> {
                        
                        for(int j=0 ; j<=chbExamenesSecundarios.length-1 ; j++)
                        {
                            if(chbExamenesSecundarios[j].isSelected())  chbExamenPrincipal.setSelected(true);
                        }
                    });
                }
            }
            
        }
        
        
        public boolean estaMarcado() {
            
            return chbExamenPrincipal.isSelected();
        }
        
        
        public String obtenerExamen() {
            
            String entreParentesis = "";
            String finalParentesis = "";
            
            if(chbExamenesSecundarios!=null)
            {
                boolean haySecundariosMarcados = false;
                
                for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                {
                    if(chbExamenesSecundarios[i].isSelected())
                    {
                        haySecundariosMarcados = true;
                        
                        break;
                    }
                }
                
                if(haySecundariosMarcados)
                {
                    entreParentesis = " (";
                    finalParentesis = ")";
                    
                    for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                    {
                        if(chbExamenesSecundarios[i].isSelected()) entreParentesis += chbExamenesSecundarios[i].getText()+", ";
                    }
                    
                    entreParentesis = entreParentesis.substring(0, entreParentesis.length()-2);
                }
            }
            
            return "- "+chbExamenPrincipal.getText()+entreParentesis+finalParentesis;
        }
        
        
        public void limpiar() {
            
            chbExamenPrincipal.setSelected(false);
            
            if(chbExamenesSecundarios!=null)
            {
                for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                {
                    chbExamenesSecundarios[i].setSelected(false);
                }
            }
        }
        
        
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class ExamenUroanalisis {
        
        private final HJCheckBox chbExamenPrincipal;
        
        private final HJCheckBox[] chbExamenesSecundarios;
        
        private final HJCheckBox chbOrinaParcial;
        private final HJCheckBox chbOrina24Hrs;
        
        
        public ExamenUroanalisis(HJCheckBox principal, HJCheckBox[] secundarios, HJCheckBox orinaParcial, HJCheckBox orina24Hrs) {
            
            chbExamenPrincipal = principal;
            
            chbExamenesSecundarios = secundarios;
            
            chbOrinaParcial = orinaParcial;
            chbOrina24Hrs = orina24Hrs;
            
            
            chbExamenPrincipal.addActionListener( e -> {
                
                if(chbExamenPrincipal.isSelected()==false)
                {
                    chbOrinaParcial.setSelected(false);
                    chbOrina24Hrs.setSelected(false);
                    
                    if(chbExamenesSecundarios!=null)
                    {
                        for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                        {
                            chbExamenesSecundarios[i].setSelected(false);
                        }
                    }
                }
            });
            
            
            if(chbExamenesSecundarios!=null)
            {
                for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                {
                    chbExamenesSecundarios[i].addActionListener( e -> {

                        for(int j=0 ; j<=chbExamenesSecundarios.length-1 ; j++)
                        {
                            if(chbExamenesSecundarios[j].isSelected())  chbExamenPrincipal.setSelected(true);
                        }
                    });
                }
            }
            
            
            chbOrinaParcial.addActionListener( e -> {
                
                if(chbOrinaParcial.isSelected())  chbExamenPrincipal.setSelected(true);
            });
            
            chbOrina24Hrs.addActionListener( e -> {
                
                if(chbOrina24Hrs.isSelected())  chbExamenPrincipal.setSelected(true);
            });
            
        }
        
        
        public boolean estaMarcado() {
            
            return chbExamenPrincipal.isSelected();
        }
        
        
        public String obtenerExamen() {
            
            String entreParentesis = "";
            String finalParentesis = "";
            
            if(chbExamenesSecundarios!=null)
            {
                boolean haySecundariosMarcados = false;
                
                for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                {
                    if(chbExamenesSecundarios[i].isSelected())
                    {
                        haySecundariosMarcados = true;
                        
                        break;
                    }
                }
                
                if(haySecundariosMarcados)
                {
                    entreParentesis = " (";
                    finalParentesis = ")";
                    
                    for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                    {
                        if(chbExamenesSecundarios[i].isSelected()) entreParentesis += chbExamenesSecundarios[i].getText()+", ";
                    }
                    
                    entreParentesis = entreParentesis.substring(0, entreParentesis.length()-2);
                }
            }
            
            
            String entreParentesisOrina = "";
            String finalParentesisOrina = "";
            
            if(chbOrinaParcial.isSelected() || chbOrina24Hrs.isSelected())
            {
                entreParentesisOrina = " (";
                finalParentesisOrina = ")";
                
                if(chbOrinaParcial.isSelected())  entreParentesisOrina += chbOrinaParcial.getName()+", ";
                
                if(chbOrina24Hrs.isSelected())  entreParentesisOrina += chbOrina24Hrs.getName()+", ";
                
                entreParentesisOrina = entreParentesisOrina.substring(0, entreParentesisOrina.length()-2);
            }
            
            
            return "- "+chbExamenPrincipal.getText()+entreParentesis+finalParentesis+entreParentesisOrina+finalParentesisOrina;
        }
        
        
        public void limpiar() {
            
            chbExamenPrincipal.setSelected(false);
            
            if(chbExamenesSecundarios!=null)
            {
                for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                {
                    chbExamenesSecundarios[i].setSelected(false);
                }
            }
            
            chbOrinaParcial.setSelected(false);
            
            chbOrina24Hrs.setSelected(false);
        }
        
        
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class ExamenInmunologia {
        
        private final HJCheckBox chbExamenPrincipal;
        
        private final HJCheckBox[] chbExamenesSecundarios;
        
        private final HJCheckBox chbCuantificada;
        private final HJCheckBox chbLatex;
        
        
        public ExamenInmunologia(HJCheckBox principal, HJCheckBox[] secundarios, HJCheckBox cuantificada, HJCheckBox latex) {
            
            chbExamenPrincipal = principal;
            
            chbExamenesSecundarios = secundarios;
            
            chbCuantificada = cuantificada;
            chbLatex = latex;
            
            
            if(chbExamenesSecundarios!=null)
            {
                chbExamenPrincipal.addActionListener( e -> {
                    
                    if(chbExamenPrincipal.isSelected()==false)
                    {
                        for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                        {
                            chbExamenesSecundarios[i].setSelected(false);
                        }
                        
                        chbCuantificada.setSelected(false);
                        chbLatex.setSelected(false);
                    }
                });
                
                
                for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                {
                    chbExamenesSecundarios[i].addActionListener( e -> {
                        
                        for(int j=0 ; j<=chbExamenesSecundarios.length-1 ; j++)
                        {
                            if(chbExamenesSecundarios[j].isSelected())  chbExamenPrincipal.setSelected(true);
                        }
                    });
                }
                
                
                chbCuantificada.addActionListener( e -> {
                    
                    if(chbCuantificada.isSelected())  chbExamenPrincipal.setSelected(true);
                });
                
                chbLatex.addActionListener( e -> {
                    
                    if(chbLatex.isSelected())  chbExamenPrincipal.setSelected(true);
                });
            }
            
        }
        
        
        public boolean estaMarcado() {
            
            return chbExamenPrincipal.isSelected();
        }
        
        
        public String obtenerExamen() {
            
            String entreParentesis = "";
            String finalParentesis = "";
            
            String entreParentesisCtdaLatex = "";
            String finalParentesisCtdaLatex = "";
            
            if(chbExamenesSecundarios!=null)
            {
                boolean haySecundariosMarcados = false;
                
                for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                {
                    if(chbExamenesSecundarios[i].isSelected())
                    {
                        haySecundariosMarcados = true;
                        
                        break;
                    }
                }
                
                if(haySecundariosMarcados)
                {
                    entreParentesis = " (";
                    finalParentesis = ")";
                    
                    for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                    {
                        if(chbExamenesSecundarios[i].isSelected()) entreParentesis += chbExamenesSecundarios[i].getName()+", ";
                    }
                    
                    entreParentesis = entreParentesis.substring(0, entreParentesis.length()-2);
                }
                
                
                if(chbCuantificada.isSelected() || chbLatex.isSelected())
                {
                    entreParentesisCtdaLatex = " (";
                    finalParentesisCtdaLatex = ")";
                    
                    if(chbCuantificada.isSelected())  entreParentesisCtdaLatex += chbCuantificada.getName()+", ";
                    
                    if(chbLatex.isSelected())  entreParentesisCtdaLatex += chbLatex.getName()+", ";
                    
                    entreParentesisCtdaLatex = entreParentesisCtdaLatex.substring(0, entreParentesisCtdaLatex.length()-2);
                }
            }
            
            return "- "+chbExamenPrincipal.getText()+entreParentesis+finalParentesis+entreParentesisCtdaLatex+finalParentesisCtdaLatex;
        }
        
        
        public void limpiar() {
            
            chbExamenPrincipal.setSelected(false);
            
            if(chbExamenesSecundarios!=null)
            {
                for(int i=0 ; i<=chbExamenesSecundarios.length-1 ; i++)
                {
                    chbExamenesSecundarios[i].setSelected(false);
                }
                
                chbCuantificada.setSelected(false);
                
                chbLatex.setSelected(false);
            }
        }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
