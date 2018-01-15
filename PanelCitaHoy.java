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
import java.time.LocalTime;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;


public class PanelCitaHoy extends HJPaintedBox {
    
    private final HJRadioButton rbApellidos_Y_Nombres;
    
    private final String horaCita;
    private final String paciente;
    
    
    public PanelCitaHoy(String horaCita, String paciente) {
        
        super(BoxLayout.Y_AXIS, new Color(235,240,250), Colores.AZUL_CLARO, null, null, 100, true);
        
        
        //...................
        
        this.horaCita = horaCita;
        this.paciente = paciente;
        
        
        //...................
        
        LocalTime horaMinuto = LocalTime.parse(this.horaCita);
        
        
        int hora = horaMinuto.getHour();
        
        String AM_PM = "a.m.";
        if(hora>12)
        {
            hora -= 12;
            AM_PM = "p.m.";
        }
        
        if(hora==0)  hora = 12;
        
        
        int minutos = horaMinuto.getMinute();
        
        String minutosString = ""+minutos;
        if(minutos<10)  minutosString = "0"+minutos;
        
        
        HJLabel lHora = new HJLabel(""+hora+":"+minutosString+" "+AM_PM);
        lHora.setForeground(Colores.AZUL_LLAMATIVO);
        
        Box boxSuperior = Box.createHorizontalBox();
        boxSuperior.add(Box.createHorizontalStrut(10));
        boxSuperior.add(new HJLabel("Hora: "));
        boxSuperior.add(lHora);
        boxSuperior.setAlignmentX(Box.LEFT_ALIGNMENT);
        
        
        rbApellidos_Y_Nombres = new HJRadioButton();
        
        if(Utilidades.esNumeroEnteroPositivo(this.paciente))
        {
            MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+this.paciente+".dda");
            
            rbApellidos_Y_Nombres.setText(mapaDatos.get(Personales.KEY_APELLIDOS)+", "+mapaDatos.get(Personales.KEY_NOMBRES));
            
        }else{
            
            boxSuperior.add(Box.createHorizontalStrut(10));
            boxSuperior.add(new HJLabel("(Paciente nuevo)"));
            
            rbApellidos_Y_Nombres.setText(this.paciente);
        }
        
        
        //...................
        
        
        add(Box.createVerticalStrut(5));
        add(boxSuperior);
        add(rbApellidos_Y_Nombres);
        add(Box.createVerticalStrut(2));
        
        
        setMaximumSize(new Dimension(278, 55));
        
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.LINEAS));
        
    }
    
    
    public HJRadioButton obtenerRadioButton() { return rbApellidos_Y_Nombres; }
    
    
    public String obtenerHoraCita() { return horaCita;}
    
    public String obtenerPaciente() { return paciente; }
    
    
}
