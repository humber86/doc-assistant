/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JPanel;


public class PanelAlergia extends JPanel {
    
    private final HJTextArea taAlergia;
    private final HJButton bElimiar;
    
    
    public PanelAlergia() {
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        
        //.......................
        
        taAlergia = new HJTextArea(1, 27);
        taAlergia.setLineWrap(false);
        taAlergia.setWrapStyleWord(false);
        HJScrollPane scrollAlergia = new HJScrollPane(taAlergia);
        scrollAlergia.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollAlergia.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollAlergia.removerEscuchadorRuedaRaton();
        
        bElimiar = new HJButton(new IconoDeImagen("Eliminar.png", -1, 20), null, null, Colores.COLORES_BOTONES, false, -1, -1, null);
        
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.add(scrollAlergia);
        panel.add(Box.createHorizontalStrut(30));
        panel.add(bElimiar);
        panel.setOpaque(false);
        
        
        //.....................
        
        
        Box boxVertical = Box.createVerticalBox();
        boxVertical.add(panel);
        boxVertical.add(Box.createVerticalStrut(25));
        
        
        add(boxVertical);
        
        
        setMaximumSize(new Dimension(850, 65));
        
        setOpaque(false);
        
    }
    
    
    //.............................
    
    
    public void anadirEscuchadorBotonEliminar(ActionListener escuchador) { bElimiar.addActionListener(escuchador); }
    
    
    public void establecerEditable(boolean editable) {
        
        taAlergia.setEditable(editable);
        taAlergia.setOpaque(editable);
        
        bElimiar.setEnabled(editable);
    }
    
    
    //.............................
    
    public boolean estaListo() { return taAlergia.esTextoValido(); }
    
    
    //.............................
    
    public String obtenerAlergia() {
        
        if(taAlergia.esTextoValido())  return taAlergia.getText();
        else  return null;
    }
    
    public void establecerAlergia(String texto) { taAlergia.setText(texto); }
    
    
}
