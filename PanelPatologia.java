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
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelPatologia extends JPanel {
    
    private final HJTextArea taPatologia;
    private final HJTextArea taDetalles;
    private final String TEXTO_INHABILITADO = "Debe asignar la patología antes de escribir aquí.";
    private final HJButton bEliminar;
    
    
    public PanelPatologia() {
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        
        //.........................
        
        taPatologia = new HJTextArea(1, 27);
        taPatologia.setLineWrap(false);
        taPatologia.setWrapStyleWord(false);
        taPatologia.addCaretListener( e -> controlarCampoDetalles() );
        HJScrollPane scrollPatologia = new HJScrollPane(taPatologia);
        scrollPatologia.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPatologia.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPatologia.removerEscuchadorRuedaRaton();
        
        JLabel lDosPuntos = new JLabel("  :  ");
        lDosPuntos.setFont(new Font("Arial", Font.BOLD, 18));
        
        taDetalles = new HJTextArea(3, 27);
        taDetalles.setText(TEXTO_INHABILITADO);
        taDetalles.setEnabled(false);
        HJScrollPane scrollDetalles = new HJScrollPane(taDetalles);
        scrollDetalles.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollDetalles.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollDetalles.removerEscuchadorRuedaRaton();
        
        bEliminar = new HJButton(new IconoDeImagen("Eliminar.png", -1, 20), null, null, Colores.COLORES_BOTONES, false, -1, -1, null);
        
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.add(scrollPatologia);
        panel.add(lDosPuntos);
        panel.add(scrollDetalles);
        panel.add(Box.createHorizontalStrut(30));
        panel.add(bEliminar);
        panel.setOpaque(false);
        
        
        //.....................
        
        
        Box boxVertical = Box.createVerticalBox();
        boxVertical.add(panel);
        boxVertical.add(Box.createVerticalStrut(15));
        
        
        add(boxVertical);
        
        
        setMaximumSize(new Dimension(850, 78));
        
        setOpaque(false);
        
    }
    
    
    //..............................
    
    
    public void anadirEscuchadorBotonEliminar(ActionListener escuchador) { bEliminar.addActionListener(escuchador); }
    
    
    private void controlarCampoDetalles() {
        
        if(taPatologia.esTextoValido())
        {
            if(taDetalles.isEnabled()==false)
            {
                taDetalles.setText(null);
                taDetalles.setEnabled(true);
            }
            
        }else{
            
            taDetalles.setText(TEXTO_INHABILITADO);
            taDetalles.setEnabled(false);
        }
    }
    
    
    public void establecerEditable(boolean editable) {
        
        taPatologia.setEditable(editable);
        taPatologia.setOpaque(editable);
        
        taDetalles.setEditable(editable);
        taDetalles.setOpaque(editable);
        if(editable==false)  taDetalles.setEnabled(true);
        
        bEliminar.setEnabled(editable);
    }
    
    
    //...............................
    
    public boolean estaListo() { return taPatologia.esTextoValido(); }
    
    
    //...............................
    
    
    public String obtenerPatologia() {
        
        if(taPatologia.esTextoValido())  return taPatologia.getText();
        else  return null;
    }
    
    public void establecerPatologia(String texto) { taPatologia.setText(texto); }
    
    
    public String obtenerDetalles() {
        
        if(taDetalles.esTextoValido())  return taDetalles.getText();
        else  return null;
    }
    
    public void establecerDetalles(String texto) { taDetalles.setText(texto); }
    
    
}
