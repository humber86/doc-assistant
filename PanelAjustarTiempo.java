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
import java.time.Duration;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class PanelAjustarTiempo extends HJDialog {
    
    private final JSpinner spinnerHoras;
    private final JSpinner spinnerMinutos;
    
    private Duration duracionInicial;
    
    boolean seEstablecioTiempo = false;
    
    
    public PanelAjustarTiempo(Duration duracionInicial) {
        
        super(new IconoDeImagen("CronometroNormal.png", -1, 30), "Ajustar Tiempo", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> cancelar() );
        
        
        //..................................
        
        this.duracionInicial = duracionInicial;
        
        
        //..................................
        
        SpinnerNumberModel modeloNumeroSpinnerHoras = new SpinnerNumberModel(new Integer(0), new Integer(0), new Integer(99), new Integer(1));
        
        spinnerHoras = new JSpinner(modeloNumeroSpinnerHoras);
        spinnerHoras.setFont(new Font("Dialog", Font.BOLD, 16));
        
        
        SpinnerNumberModel modeloNumeroSpinnerMinutos = new SpinnerNumberModel(new Integer(0), new Integer(0), new Integer(59), new Integer(1));
        
        spinnerMinutos = new JSpinner(modeloNumeroSpinnerMinutos);
        spinnerMinutos.setFont(new Font("Dialog", Font.BOLD, 16));
        
        
        JPanel panelBoxesTiempo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBoxesTiempo.add(crearBoxTexto("HH:", spinnerHoras));
        panelBoxesTiempo.add(Box.createHorizontalStrut(6));
        panelBoxesTiempo.add(crearBoxTexto("mm:", spinnerMinutos));
        panelBoxesTiempo.setOpaque(false);
        
        
        //..................................
        
        HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
        bAceptar.addActionListener( e -> establecerTiempo() );
        
        HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
        bCancelar.addActionListener( e -> cancelar() );
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.add(bAceptar);
        panelBotones.add(bCancelar);
        panelBotones.setOpaque(false);
        
        
        //.................................
          
        
        Box cajaVertical = Box.createVerticalBox();
        cajaVertical.add(Box.createVerticalStrut(10));
        cajaVertical.add(panelBoxesTiempo);
        cajaVertical.add(panelBotones);
         
        
        add(cajaVertical);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private Box crearBoxTexto(String etiqueta, JComponent campoNumero) {
        
        JLabel lEtiqueta = new JLabel(etiqueta);
        lEtiqueta.setFont(new Font("Dialog", Font.PLAIN, 16));
        lEtiqueta.setForeground(Colores.TEXTO);
        
        JPanel panelEtiqueta = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelEtiqueta.add(lEtiqueta);
        panelEtiqueta.setOpaque(false);
        
        
        JPanel panelCampoNumero = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCampoNumero.add(campoNumero);
        panelCampoNumero.setOpaque(false);
        
        
        Box boxVertical = Box.createVerticalBox();
        boxVertical.add(panelEtiqueta);
        boxVertical.add(Box.createVerticalStrut(5));
        boxVertical.add(panelCampoNumero);
        
        return boxVertical;
    }
    
    
    //..............................
    
    private void establecerTiempo() {
        
        int horas = Integer.parseInt(""+spinnerHoras.getValue());
        int minutos = Integer.parseInt(""+spinnerMinutos.getValue());
        
        int segundos = minutos*60 + horas*3600;
        
        if(segundos>0)
        {
            duracionInicial = duracionInicial.withSeconds(segundos);
            
            seEstablecioTiempo = true;
        }
        
        setVisible(false);
        dispose();
    }
    
    
    private void cancelar() {
        
        if(duracionInicial.isZero())
        {
            spinnerHoras.setValue(0);
            spinnerMinutos.setValue(0);
            
        }else{
            
            int segundosTotales = (int)duracionInicial.getSeconds();
            int minutosTotales = segundosTotales/60;
            int horasTotales = minutosTotales/60;
            
            
            int minutosRestantes = minutosTotales%60;
            
            spinnerMinutos.setValue(minutosRestantes);
            
            
            spinnerHoras.setValue(horasTotales);
        }
        
        setVisible(false);
        dispose();
    }
    
    
    public boolean fueTiempoEstablecido() { return seEstablecioTiempo; }
    
    
    public Duration obtenerDuracion() { return duracionInicial; }
    
    
}
