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
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PanelImpresionVacunas extends HJDialog {
    
    
    public PanelImpresionVacunas(int numeroHistoria) {
        
        super(new IconoDeImagen("VistaPrevia.png", -1, 30), "Vista Previa", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //.............................
        
        PaginaVacunas pagina_1 = new PaginaVacunas(numeroHistoria, PaginaVacunas.BLOQUE_1);
        
        JPanel panelPagina_1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelPagina_1.add(pagina_1);
        panelPagina_1.setOpaque(false);
        
        PaginaVacunas pagina_2 = new PaginaVacunas(numeroHistoria, PaginaVacunas.BLOQUE_2);
        
        JPanel panelPagina_2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelPagina_2.add(pagina_2);
        panelPagina_2.setOpaque(false);
        
        
        ArrayList<JComponent> arrayPaginas = new ArrayList<>(0);
        arrayPaginas.add(pagina_1);
        arrayPaginas.add(pagina_2);
        
        
        HJPaintedBox boxPaginas = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(245,245,245), new Color(235,235,235), null, null, 100, true);
        boxPaginas.add(Box.createVerticalStrut(30));
        boxPaginas.add(panelPagina_1);
        boxPaginas.add(Box.createVerticalStrut(30));
        boxPaginas.add(panelPagina_2);
        boxPaginas.add(Box.createVerticalStrut(30));
        
        JScrollPane scrollPaginas = new JScrollPane(boxPaginas);
        scrollPaginas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaginas.setPreferredSize(new Dimension(800, 600));
        scrollPaginas.getVerticalScrollBar().setUnitIncrement(10);
        scrollPaginas.setOpaque(false);
        scrollPaginas.getViewport().setOpaque(false);
        
        
        //............................
        
        HJButton bImprimir = new HJButton(new IconoDeImagen("Imprimir.png", -1, 20), "Imprimir", Colores.COLORES_BOTONES);
        bImprimir.addActionListener( e -> {
            
            DialogoImpresion dialogoImpresion = new DialogoImpresion(arrayPaginas);
            dialogoImpresion.setVisible(true);
        });
        
        HJButton bSalir = new HJButton(null, "Salir", Colores.COLORES_BOTONES);
        bSalir.addActionListener( e -> {
            
            setVisible(false);
            dispose();
        });
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.add(bImprimir);
        panelBotones.add(bSalir);
        panelBotones.setOpaque(false);
        
        
        //............................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(scrollPaginas);
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
}
