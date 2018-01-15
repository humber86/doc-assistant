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
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class PanelVistaPrevia extends HJDialog {
    
    private final Recipe recipe;
    
    
    public PanelVistaPrevia(MapaDatos mapaDatos) {
        
        super(new IconoDeImagen("VistaPrevia.png", -1, 30), "Vista Previa", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //.......................
        
        recipe = new Recipe(mapaDatos);
        
        JPanel panelRecipe = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelRecipe.add(recipe);
        panelRecipe.setOpaque(false);
        
        
        HJPaintedBox boxSuperior = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(245,245,245), new Color(235,235,235), null, null, 100, true);
        boxSuperior.add(panelRecipe);
        
        
        //........................
        
        HJButton bImprimir = new HJButton(new IconoDeImagen("Imprimir.png", -1, 20), "Imprimir", Colores.COLORES_BOTONES);
        bImprimir.addActionListener( e -> iniciarTareaImpresion() );
        
        HJButton bCerrar = new HJButton(null, "Cerrar", Colores.COLORES_BOTONES);
        bCerrar.addActionListener( e -> {
            
            setVisible(false);
            dispose();
        });
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.add(bImprimir);
        panelBotones.add(bCerrar);
        panelBotones.setOpaque(false);
        
        panelBotones.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //........................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(boxSuperior);
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    public void llenarRecipe() {
        
        ArrayList<String> arrayContenido = new ArrayList<>(0);
        for(int i=1 ; i<=8 ; i++)
        {
            arrayContenido.add("- Esta es una muestra del tipo, tamaño y color de la letra del contenido.");
        }
        
        recipe.llenar(arrayContenido, 1);
    }
    
    
    private void iniciarTareaImpresion() {
        
        ArrayList<JComponent> arrayRecipe = new ArrayList<>(0);
        arrayRecipe.add(recipe);
        
        DialogoImpresion dialogoImpresion = new DialogoImpresion(arrayRecipe);
        dialogoImpresion.setVisible(true);
    }
    
    
}
