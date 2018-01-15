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
import java.time.LocalDate;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelDosis extends JPanel {
    
    private final String nombre;
    
    private final String tipo;
    public static final String DOSIS = "Dosis";
    public static final String REFUERZO = "Refuerzo";
    public static final String ULTIMA_DOSIS = "Ult. Dosis";
    public static final String ULTIMO_REFUERZO = "Ult. Refuerzo";
    
    private final int numero;
    public static final int UNICA_DOSIS_O_REFUERZO = 0;
    public static final int ULTIMA_DOSIS_O_REFUERZO = -1;
    
    private final JTextField tFecha;
    private final JTextField tEdad;
    
    private final String tituloParaDialogo;
    
    private LocalDate fechaNacimiento;
    
    
    public PanelDosis(String nombreVacuna, String tipoDosis, int numeroDosis) {
        
        setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));
        
        setOpaque(false);
        
        
        //........................
        
        nombre = nombreVacuna;
        
        tipo = tipoDosis;
        
        numero = numeroDosis;
        
        
        //........................
        
        tFecha = new JTextField(7);
        tFecha.setText("Fecha");
        tFecha.setEditable(false);
        tFecha.setEnabled(false);
        
        tEdad = new JTextField(18);
        tEdad.setText("Edad");
        tEdad.setEditable(false);
        tEdad.setEnabled(false);
        
        
        HJCustomizedButton botonEditar = new HJCustomizedButton(new IconoDeImagen("Editar.png", -1, 20), true);
        botonEditar.addActionListener( e -> {
            
            PanelEditorVacuna panelEditorVacuna = new PanelEditorVacuna(obtenerEstePanelDosis());
            panelEditorVacuna.setVisible(true);
        });
        
        HJCustomizedButton botonEliminar = new HJCustomizedButton(new IconoDeImagen("Eliminar.png", -1, 20), true);
        botonEliminar.addActionListener( e -> {
            
            tFecha.setText("Fecha");
            tFecha.setEnabled(false);
            
            tEdad.setText("Edad");
            tEdad.setEnabled(false);
        });
        
        
        if((tipo.equals(DOSIS) || tipo.equals(REFUERZO)) && numero>0)  tituloParaDialogo = tipo+" "+numero;
        else  tituloParaDialogo = tipo;
        
        
        //........................
        
        
        add(Box.createHorizontalStrut(10));
        add(new HJLabel(tituloParaDialogo+":"));
        add(Box.createHorizontalStrut(10));
        add(tFecha);
        add(Box.createHorizontalStrut(10));
        add(tEdad);
        add(Box.createHorizontalStrut(10));
        add(botonEditar);
        add(Box.createHorizontalStrut(10));
        add(botonEliminar);
        add(Box.createHorizontalStrut(10));
        
    }
    
    
    private PanelDosis obtenerEstePanelDosis() { return this; }
    
    
    public String obtenerNombreVacuna() { return nombre; }
    
    
    public String obtenerTipoDosis() { return tipo; }
    
    
    public int obtenerNumeroDosis() { return numero; }
    
    
    public String obtenerTituloParaDialogo() { return tituloParaDialogo; }
    
    
    public String obtenerNombrePanel() { return nombre+"-"+tipo+"-"+numero; } 
    
    
    public void establecerFecha(String fecha) {
        
        tFecha.setText(fecha);
        tFecha.setEnabled(true);
    }
    
    public String obtenerFechaVacuna() { return tFecha.getText(); }
    
    public void limpiarFecha() {
        
        tFecha.setText("Fecha");
        tFecha.setEnabled(false);
    }
    
    
    public void establecerEdad(String edad) {
        
        tEdad.setText(edad);
        tEdad.setEnabled(true);
    }
    
    public String obtenerEdadVacuna() { return tEdad.getText(); }
    
    public void limpiarEdad() {
        
        tEdad.setText("Edad");
        tEdad.setEnabled(false);
    }
    
    
    public void establecerFechaNacimiento(LocalDate fecha) { fechaNacimiento = fecha; }
    
    
    public LocalDate obtenerFechaNacimiento() { return fechaNacimiento; }
    
    
    public boolean estaPanelListo() { return tFecha.isEnabled(); }
    
    
    public void limpiarTodo() {
        
        limpiarFecha();
        limpiarEdad();
    }
}
