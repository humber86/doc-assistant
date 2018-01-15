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


public class PanelMedicamento extends JPanel {
    
    public static final String ANTIBIOTICO = "Antibiótico";
    public static final String PRESCRIPCION_INDIVIDUAL = "Prescripción Individual";
    public static final String CAJAS = "Cajas";
    public static final String INDICACIONES = "Indicaciones";
    
    private String antibiotico;
    private final HJCheckBox chbAntibiotico;
    private DialogoSelectorAntibiotico dialogoSelectorAntibiotico;
    
    private final HJCheckBox chbPreescripcionIndividual;
    
    private final JPanel panelAntibioticosPrescripcion;
    
    private final HJTextArea taMedicamento;
    
    private final HJComboBox<String> cbCajas;
    private final JPanel panelCajas;
    
    private final HJTextArea taIndicaciones;
    private final String TEXTO_INHABILITADO = "Debe ingresar el nombre del medicamento antes de escribir las indicaciones.";
    
    private final HJButton bEliminar;
    private final JPanel panelBotonEliminar;
    
    
    public PanelMedicamento() {
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        
        //............................
        
        antibiotico = null;
        
        chbAntibiotico = new HJCheckBox();
        chbAntibiotico.setPreferredSize(new Dimension(70, 25));
        chbAntibiotico.addActionListener( e -> {
            
            if(chbAntibiotico.isSelected())
            {
                dialogoSelectorAntibiotico = new DialogoSelectorAntibiotico();
                dialogoSelectorAntibiotico.setVisible(true);
                
            }else{
                
                antibiotico = null;
                
                chbAntibiotico.setText("");
            }
        });
        chbAntibiotico.setToolTipText("Antibiótico");
        
        
        chbPreescripcionIndividual = new HJCheckBox();
        chbPreescripcionIndividual.setToolTipText("Prescripción individual");
        
        
        taMedicamento = new HJTextArea(1, 25);
        taMedicamento.setLineWrap(false);
        taMedicamento.setWrapStyleWord(false);
        taMedicamento.addCaretListener( e -> controlarCampoIndicaciones() );
        HJScrollPane scrollMedicamento = new HJScrollPane(taMedicamento);
        scrollMedicamento.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollMedicamento.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollMedicamento.removerEscuchadorRuedaRaton();
        
        
	cbCajas = new HJComboBox<>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
	cbCajas.setEnabled(false);
        
        chbPreescripcionIndividual.addActionListener( e -> {
            
            if(chbPreescripcionIndividual.isSelected())
            {
                cbCajas.setEnabled(true);
                
            }else{
                
                cbCajas.setSelectedIndex(0);
                cbCajas.setEnabled(false);
            }
        });
        
        
        JLabel lDosPuntos = new JLabel("  :  ");
        lDosPuntos.setFont(new Font("Arial", Font.BOLD, 18));
        
        
        taIndicaciones = new HJTextArea(3, 25);
        taIndicaciones.setText(TEXTO_INHABILITADO);
        taIndicaciones.setEnabled(false);
        HJScrollPane scrollIndicaciones = new HJScrollPane(taIndicaciones);
        scrollIndicaciones.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollIndicaciones.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollIndicaciones.removerEscuchadorRuedaRaton();
        
        
        bEliminar = new HJButton(new IconoDeImagen("Eliminar.png", -1, 20), null, Colores.COLORES_BOTONES);
        
        
        //....
        
        panelAntibioticosPrescripcion = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelAntibioticosPrescripcion.add(chbAntibiotico);
        panelAntibioticosPrescripcion.add(Box.createHorizontalStrut(15));
        panelAntibioticosPrescripcion.add(chbPreescripcionIndividual);
        panelAntibioticosPrescripcion.add(Box.createHorizontalStrut(20));
        panelAntibioticosPrescripcion.setOpaque(false);
        
        panelCajas = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCajas.add(Box.createHorizontalStrut(5));
        panelCajas.add(cbCajas);
        panelCajas.setOpaque(false);
        
        panelBotonEliminar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonEliminar.add(Box.createHorizontalStrut(30));
        panelBotonEliminar.add(bEliminar);
        panelBotonEliminar.setOpaque(false);
        
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.add(Box.createHorizontalStrut(30));
        panel.add(panelAntibioticosPrescripcion);
        panel.add(scrollMedicamento);
        panel.add(panelCajas);
        panel.add(lDosPuntos);
        panel.add(scrollIndicaciones);
        panel.add(panelBotonEliminar);
        panel.add(Box.createHorizontalStrut(30));
        panel.setOpaque(false);
        
        
        //.....................
        
        
        Box boxVertical = Box.createVerticalBox();
        boxVertical.add(panel);
        boxVertical.add(Box.createVerticalStrut(15));
        
        
        add(boxVertical);
        
        
        setMaximumSize(new Dimension(1000, 78));
        
        setOpaque(false);
        
    }
    
    
    //..............................
    
    
    public void anadirEscuchadorBotonEliminar(ActionListener escuchador) { bEliminar.addActionListener(escuchador); }
    
    
    private void controlarCampoIndicaciones() {
        
        if(taMedicamento.esTextoValido())
        {
            if(taIndicaciones.isEnabled()==false)
            {
                taIndicaciones.setText(null);
                taIndicaciones.setEnabled(true);
            }
            
        }else{
            
            taIndicaciones.setText(TEXTO_INHABILITADO);
            taIndicaciones.setEnabled(false);
        }
    }
    
    
    public void establecerEditable(boolean editable) {
        
        panelAntibioticosPrescripcion.setVisible(false);
        
        taMedicamento.setEditable(editable);
        taMedicamento.setOpaque(editable);
        
        panelCajas.setVisible(editable);
        
        taIndicaciones.setEditable(editable);
        taIndicaciones.setOpaque(editable);
        if(editable==false)  taIndicaciones.setEnabled(true);
        
        panelBotonEliminar.setVisible(editable);
        
        if(editable==false)  setMaximumSize(new Dimension(900, 78));
        else  setMaximumSize(new Dimension(1000, 78));
    }
    
    
    //...............................
    
    public boolean estaListo() { return taMedicamento.esTextoValido(); }
    
    
    //...............................
    
    public void establecerAntibiotico(String antibiotico) { this.antibiotico = antibiotico; }
    
    public String obtenerAntibiotico() { return antibiotico; }
    
    
    public String conPrescripcionIndividual() { return chbPreescripcionIndividual.isSelected() ? "Sí" : "No"; }
    
    
    public void establecerMedicamento(String texto) { taMedicamento.setText(texto); }
    
    public String obtenerMedicamento() {
        
        if(taMedicamento.esTextoValido())  return taMedicamento.getText();
        else  return null;
    }
    
    
    public void establecerCantidadCajas(int cantidad) { cbCajas.setSelectedItem(""+cantidad); }
    
    public String obtenerCantidadCajas() { return ""+cbCajas.getSelectedItem(); }
    
    
    public void establecerIndicaciones(String texto) { taIndicaciones.setText(texto); }
    
    public String obtenerIndicaciones() {
        
        if(taIndicaciones.esTextoValido())  return taIndicaciones.getText();
        else  return null;
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class DialogoSelectorAntibiotico extends HJDialog {
        
        private final HJComboBox<String> cbAntibioticos;
        
        
        public DialogoSelectorAntibiotico() {
            
            super(null, "Seleccione", null, true);
            
            
            anadirActionListenerABotonCerrar( e -> cancelar() );
            
            
            //...........................
            
            JPanel panelEtiqueta = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelEtiqueta.add(new HJLabel("Seleccione el antibiótico:"));
            panelEtiqueta.setOpaque(false);
            
            
            String[] antibioticos = ConstantesAntibioticos.obtenerNombresCodigosAntibioticos();
            
            String[] seleccionesCombo = new String[antibioticos.length+1];
            seleccionesCombo[0] = "Seleccione...";
            for(int i=1 ; i<=seleccionesCombo.length-1 ; i++)
            {
                seleccionesCombo[i] = antibioticos[i-1];
            }
            
            cbAntibioticos = new HJComboBox<>(seleccionesCombo);
            
            JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelCombo.add(cbAntibioticos);
            panelCombo.setOpaque(false);
            
            
            Box boxSeleccion = Box.createVerticalBox();
            boxSeleccion.add(panelEtiqueta);
            boxSeleccion.add(Box.createVerticalStrut(10));
            boxSeleccion.add(panelCombo);
            
            
            JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
            panelCentral.add(new HJLabel(new IconoDeImagen("Pildora.png", -1, 50)));
            panelCentral.add(boxSeleccion);
            panelCentral.setOpaque(false);
            
            
            //....
            
            HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
            bAceptar.addActionListener( e -> {
                
                if(cbAntibioticos.getSelectedIndex()>0)
                {
                    String[] seleccion = ((String)cbAntibioticos.getSelectedItem()).split(" ");
                    
                    establecerAntibiotico(seleccion[0]);
                    
                    chbAntibiotico.setText(seleccion[2]);
                    
                    
                    cbAntibioticos.setSelectedIndex(0);
                    
                    setVisible(false);
                    
                    dispose();
                    
                }else{ HJDialog.mostrarMensaje("Sin Selección", new String[] {"No ha seleccionado ningún antibiótico."}, IconoDeImagen.ADVERTENCIA, null); }
            });
            
            HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
            bCancelar.addActionListener( e -> cancelar() );
            
            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
            panelBotones.add(bAceptar);
            panelBotones.add(bCancelar);
            panelBotones.setOpaque(false);
            
            
            //.........................
            
            
            Box boxVertical = Box.createVerticalBox();
            boxVertical.add(Box.createVerticalStrut(15));
            boxVertical.add(panelCentral);
            boxVertical.add(panelBotones);
            
            
            add(boxVertical);
            
            
            pack();
            
            setLocationRelativeTo(null);
            
        }
        
        
        private void cancelar() {
            
            cbAntibioticos.setSelectedIndex(0);
            
            if(chbAntibiotico.getText().isEmpty())
                chbAntibiotico.setSelected(false);
            
            setVisible(false);
            
            dispose();
        }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
