/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JLabel;


public class HJClock extends Thread {
    
    private final JLabel lHora;
    
    
    public HJClock(JLabel lHora) {
        
        this.lHora = lHora;
        
    }
    
    
    @Override
    public void run() {
        
        LocalTime horaLocal;
        
        String AM_PM;
        
        while(true){
            
            horaLocal = LocalTime.now();
            horaLocal = horaLocal.truncatedTo(ChronoUnit.SECONDS);
            
            if(horaLocal.isBefore(LocalTime.NOON))  AM_PM = "a.m.";
            else  AM_PM = "p.m.";
            
            String horaConFormato = horaLocal.format(DateTimeFormatter.ofPattern("hh:mm:ss"))+" "+AM_PM;
            
            lHora.setText(horaConFormato);
        }
    }
    
    
}
