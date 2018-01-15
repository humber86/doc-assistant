/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class PanelEstudiosRadiologicos extends HJDialog {
    
    private final Font formatoTitulos = new Font("Arial", Font.BOLD, 18);
    
    public static final String TOMOGRAFIA = "Tomografía";
    public static final String ECOGRAFIA = "Ecografía";
    public static final String RADIOGRAFIA = "Radiografía";
    public static final String RESONANCIA_MAGNETICA = "Resonancia Magnética";
    public static final String ELECTROENCEFALOGRAFIA = "Electroencefalografía";
    public static final String OTROS = "Otros";
    
    private final HJTextArea taTomografia;
    private final HJTextArea taEcografia;
    private final HJTextArea taRadiografia;
    private final HJTextArea taResonanciaMagnetica;
    private final HJTextArea taElectroencefalografia;
    private final HJTextArea taOtros;
    
    private final HashMap<String,String> mapaEstudiosRadiologicos = new HashMap<>(0);
    
    
    public PanelEstudiosRadiologicos() {
        
        super(new IconoDeImagen("Radiologia.png", -1, 30), "Estudios Radiológicos", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Advertencia", new String[] {"Esta acción es igual a presionar el botón 'Aceptar'.", "¿Está seguro(a) de que desea continuar?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
                aceptar();
        });
        
        
        //..........................
        
        taTomografia = new HJTextArea(5, 30);
        HJScrollPane scrollTomografia = new HJScrollPane(taTomografia);
        scrollTomografia.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollTomografia.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTomografia.removerEscuchadorRuedaRaton();
        
        JPanel panelTomografia = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelTomografia.add(scrollTomografia);
        panelTomografia.setOpaque(false);
        
        HJEnclosingBox boxTomografia = new HJEnclosingBox(BoxLayout.Y_AXIS, TOMOGRAFIA, formatoTitulos, Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 3);
        boxTomografia.add(Box.createVerticalStrut(18));
        boxTomografia.add(panelTomografia);
        boxTomografia.add(Box.createVerticalStrut(3));
        
        
        taEcografia = new HJTextArea(5, 30);
        HJScrollPane scrollEcografia = new HJScrollPane(taEcografia);
        scrollEcografia.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollEcografia.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollEcografia.removerEscuchadorRuedaRaton();
        
        JPanel panelEcografia = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelEcografia.add(scrollEcografia);
        panelEcografia.setOpaque(false);
        
        HJEnclosingBox boxEcografia = new HJEnclosingBox(BoxLayout.Y_AXIS, ECOGRAFIA, formatoTitulos, Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 3);
        boxEcografia.add(Box.createVerticalStrut(18));
        boxEcografia.add(panelEcografia);
        boxEcografia.add(Box.createVerticalStrut(3));
        
        
        taRadiografia = new HJTextArea(5, 30);
        HJScrollPane scrollRadiografia = new HJScrollPane(taRadiografia);
        scrollRadiografia.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollRadiografia.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollRadiografia.removerEscuchadorRuedaRaton();
        
        JPanel panelRadiografia = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelRadiografia.add(scrollRadiografia);
        panelRadiografia.setOpaque(false);
        
        HJEnclosingBox boxRadiografia = new HJEnclosingBox(BoxLayout.Y_AXIS, RADIOGRAFIA, formatoTitulos, Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 3);
        boxRadiografia.add(Box.createVerticalStrut(18));
        boxRadiografia.add(panelRadiografia);
        boxRadiografia.add(Box.createVerticalStrut(3));
        
        
        taResonanciaMagnetica = new HJTextArea(5, 30);
        HJScrollPane scrollResonanciaMagnetica = new HJScrollPane(taResonanciaMagnetica);
        scrollResonanciaMagnetica.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollResonanciaMagnetica.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollResonanciaMagnetica.removerEscuchadorRuedaRaton();
        
        JPanel panelResonanciaMagnetica = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelResonanciaMagnetica.add(scrollResonanciaMagnetica);
        panelResonanciaMagnetica.setOpaque(false);
        
        HJEnclosingBox boxResonanciaMagnetica = new HJEnclosingBox(BoxLayout.Y_AXIS, RESONANCIA_MAGNETICA, formatoTitulos, Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 3);
        boxResonanciaMagnetica.add(Box.createVerticalStrut(18));
        boxResonanciaMagnetica.add(panelResonanciaMagnetica);
        boxResonanciaMagnetica.add(Box.createVerticalStrut(3));
        
        
        taElectroencefalografia = new HJTextArea(5, 30);
        HJScrollPane scrollElectroencefalografia = new HJScrollPane(taElectroencefalografia);
        scrollElectroencefalografia.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollElectroencefalografia.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollElectroencefalografia.removerEscuchadorRuedaRaton();
        
        JPanel panelElectroencefalografia = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelElectroencefalografia.add(scrollElectroencefalografia);
        panelElectroencefalografia.setOpaque(false);
        
        HJEnclosingBox boxElectroencefalografia = new HJEnclosingBox(BoxLayout.Y_AXIS, ELECTROENCEFALOGRAFIA, formatoTitulos, Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 3);
        boxElectroencefalografia.add(Box.createVerticalStrut(18));
        boxElectroencefalografia.add(panelElectroencefalografia);
        boxElectroencefalografia.add(Box.createVerticalStrut(3));
        
        
        taOtros = new HJTextArea(5, 30);
        HJScrollPane scrollOtros = new HJScrollPane(taOtros);
        scrollOtros.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollOtros.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollOtros.removerEscuchadorRuedaRaton();
        
        JPanel panelOtros = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelOtros.add(scrollOtros);
        panelOtros.setOpaque(false);
        
        HJEnclosingBox boxOtros = new HJEnclosingBox(BoxLayout.Y_AXIS, OTROS, formatoTitulos, Colores.TEXTO, HJEnclosingBox.CENTRO, 0, Colores.LINEAS, 3);
        boxOtros.add(Box.createVerticalStrut(18));
        boxOtros.add(panelOtros);
        boxOtros.add(Box.createVerticalStrut(3));
        
        
        //.........................
        
        
        Box boxVertical_1 = Box.createVerticalBox();
        boxVertical_1.add(boxTomografia);
        boxVertical_1.add(Box.createVerticalStrut(20));
        boxVertical_1.add(boxRadiografia);
        boxVertical_1.add(Box.createVerticalStrut(20));
        boxVertical_1.add(boxElectroencefalografia);
        
        Box boxVertical_2 = Box.createVerticalBox();
        boxVertical_2.add(boxEcografia);
        boxVertical_2.add(Box.createVerticalStrut(20));
        boxVertical_2.add(boxResonanciaMagnetica);
        boxVertical_2.add(Box.createVerticalStrut(20));
        boxVertical_2.add(boxOtros);
        
        JPanel panelHorizontal = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelHorizontal.add(boxVertical_1);
        panelHorizontal.add(boxVertical_2);
        panelHorizontal.setOpaque(false);
        
        
        //.......................
        
        HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
        bAceptar.addActionListener( e -> aceptar() );
        
        HJButton bLimpiar = new HJButton(new IconoDeImagen("Limpiar.png", -1, 20), "Limpiar", Colores.COLORES_BOTONES);
        bLimpiar.addActionListener( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea borrar todo lo escrito en esta ventana?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
                limpiar();
        });
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.add(bAceptar);
        panelBotones.add(bLimpiar);
        panelBotones.setOpaque(false);
        
        panelBotones.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //..........................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(panelHorizontal);
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    //...........................
    
    
    private void aceptar() {
        
        mapaEstudiosRadiologicos.clear();
        
        
        if(taTomografia.esTextoValido())  mapaEstudiosRadiologicos.put(TOMOGRAFIA, taTomografia.getText());
        
        if(taEcografia.esTextoValido())  mapaEstudiosRadiologicos.put(ECOGRAFIA, taEcografia.getText());
        
        if(taRadiografia.esTextoValido())  mapaEstudiosRadiologicos.put(RADIOGRAFIA, taRadiografia.getText());
        
        if(taResonanciaMagnetica.esTextoValido())  mapaEstudiosRadiologicos.put(RESONANCIA_MAGNETICA, taResonanciaMagnetica.getText());
        
        if(taElectroencefalografia.esTextoValido())  mapaEstudiosRadiologicos.put(ELECTROENCEFALOGRAFIA, taElectroencefalografia.getText());
        
        if(taOtros.esTextoValido())  mapaEstudiosRadiologicos.put(OTROS, taOtros.getText());
        
        
        setVisible(false);
    }
    
    
    public HashMap<String,String> obtenerMapaEstudios() { return mapaEstudiosRadiologicos; }
    
    
    private void limpiar() {
        
        taTomografia.limpiar();
        taEcografia.limpiar();
        taRadiografia.limpiar();
        taResonanciaMagnetica.limpiar();
        taElectroencefalografia.limpiar();
        taOtros.limpiar();
    }
    
    
}
