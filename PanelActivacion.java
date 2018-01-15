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
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.JPanel;


public class PanelActivacion extends HJDialog {
    
    
    public PanelActivacion() {
        
        super(new IconoDeImagen("Llave.png", -1, 30), "Activación de Licencia de Uso", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //.............................
        
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelSuperior.add(Box.createHorizontalStrut(30));
        panelSuperior.add(new HJLabel("Introduzca el código de activación:"));
        panelSuperior.setOpaque(false);
        
        
        HJTextField tCodigoActivacion = new HJTextField(15);
        
        HJButton bActivar = new HJButton(new IconoDeImagen("Llave.png", -1, 20), "Activar", Colores.COLORES_BOTONES);
        bActivar.addActionListener( e -> {
            
            String codigo = tCodigoActivacion.getText();
            
            if(Registro.esCodigoActivacionValido(codigo))
            {
                MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda");
                
                mapaDatos.put(Registro.CODIGO_ACTIVACION, codigo);
                
                Utilidades.guardarEnArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda", mapaDatos);
                
                HJDialog.mostrarMensaje("Licencia de Uso Activada", new String[] {"¡FELICIDADES!", " ", "Su producto fue activado exitosamente."}, IconoDeImagen.INFORMACION, null);
                
                setVisible(false);
                dispose();
                
            }else{
                
                Toolkit.getDefaultToolkit().beep();
                HJDialog.mostrarMensaje("Código Inválido", new String[] {"El código que ingreso no es válido.", "Intente de nuevo."}, IconoDeImagen.ERROR, null);
            }
        });
        
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCentral.add(Box.createHorizontalStrut(30));
        panelCentral.add(tCodigoActivacion);
        panelCentral.add(Box.createHorizontalStrut(20));
        panelCentral.add(bActivar);
        panelCentral.add(Box.createHorizontalStrut(30));
        panelCentral.setOpaque(false);
        
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelInferior.add(Box.createHorizontalStrut(30));
        panelInferior.add(new HJLabel("NOTA: No introduzca espacios."));
        panelInferior.setOpaque(false);
        
        
        //.............................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelSuperior);
        cajaGeneral.add(Box.createVerticalStrut(5));
        cajaGeneral.add(panelCentral);
        cajaGeneral.add(Box.createVerticalStrut(5));
        cajaGeneral.add(panelInferior);
        cajaGeneral.add(Box.createVerticalStrut(15));
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
}
