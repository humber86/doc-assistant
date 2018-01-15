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
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;


public class DialogoPacienteNuevo extends HJDialog {
    
    private final HJTextField tNombres;
    private final HJTextField tApellidos;
    
    private String[] cadenaNombresApellidos = null;
    
    
    public DialogoPacienteNuevo() {
        
        super(new IconoDeImagen("Paciente.png", -1, 30), "Ingrese Datos", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //.....................
        
        tNombres = new HJTextField(20);
        
        JPanel panelNombres = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelNombres.add(new HJLabel("Nombres: "));
        panelNombres.add(tNombres);
        panelNombres.setOpaque(false);
        
        tApellidos = new HJTextField(20);
        
        JPanel panelApellidos = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelApellidos.add(new HJLabel("Apellidos: "));
        panelApellidos.add(tApellidos);
        panelApellidos.setOpaque(false);
        
        
        Box boxNombresApellidos = Box.createVerticalBox();
        boxNombresApellidos.add(panelNombres);
        boxNombresApellidos.add(Box.createVerticalStrut(15));
        boxNombresApellidos.add(panelApellidos);
        
        JPanel panelNombresApellidos = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelNombresApellidos.add(boxNombresApellidos);
        panelNombresApellidos.setOpaque(false);
        
        
        //.......................
        
        HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
        bAceptar.addActionListener( e -> establecerNombresApellidos() );
        
        HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
        bCancelar.addActionListener( e -> {
            
            setVisible(false);
            dispose();
        });
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.add(bAceptar);
        panelBotones.add(bCancelar);
        panelBotones.setOpaque(false);
        
        panelBotones.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //.......................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelNombresApellidos);
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private boolean estanCasillasListas() {
        
        if(tNombres.esTextoValido()==false)
        {
            HJDialog.mostrarMensaje("Faltan Datos", new String[] {"Debe ingresar los nombres del paciente."}, IconoDeImagen.ADVERTENCIA, null);
            
            return false;
        }
        
        if(tApellidos.esTextoValido()==false)
        {
            HJDialog.mostrarMensaje("Faltan Datos", new String[] {"Debe ingresar los apellidos del paciente."}, IconoDeImagen.ADVERTENCIA, null);
            
            return false;
        }
        
        return true;
    }
    
    
    private void establecerNombresApellidos() {
        
        if(estanCasillasListas())
        {
            cadenaNombresApellidos = new String[2];
            cadenaNombresApellidos[0] = tNombres.getText();
            cadenaNombresApellidos[1] = tApellidos.getText();
        }
        
        setVisible(false);
        dispose();
    }
    
    
    public String[] obtenerNombres_Y_Apellidos() { return cadenaNombresApellidos; }
    
    
}
