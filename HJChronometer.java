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
import java.time.Duration;
import javax.swing.JLabel;


public class HJChronometer {
    
    private final int INICIAR = 0;
    private final int DETENER = 1;
    private final int PAUSAR = 2;
    private final int REINICIAR = 3;
    
    private final HJPopupMenu menuPopup;
    private final HJCustomizedButton boton;
    private HiloCronometro hiloCronometro;
    private final JLabel lCronometro;
    
    
    public HJChronometer(HJCustomizedButton botonControlador, JLabel lCronometro) {
        
        boton = botonControlador;
        boton.setToolTipText("Cronómetro");
        
        this.lCronometro = lCronometro;
        
        hiloCronometro = new HiloCronometro(this.lCronometro, Duration.ZERO);
        
        
        Color[] coloresHJMenuItems = {Colores.AZUL_CLARO, Colores.AZUL_OSCURO, Colores.PRESSED};
        
        HJMenuItem itemIniciar = new HJMenuItem(new IconoDeImagen("Iniciar.png", -1, 16), "Iniciar", coloresHJMenuItems);
        itemIniciar.addActionListener( e -> iniciarCronometro() );
        
        HJMenuItem itemDetener = new HJMenuItem(new IconoDeImagen("Detener.png", -1, 16), "Detener", coloresHJMenuItems);
        itemDetener.addActionListener( e -> detenerCronometro() );
        itemDetener.setEnabled(false);
        
        HJMenuItem itemPausar = new HJMenuItem(new IconoDeImagen("Pausar.png", -1, 16), "Pausar", coloresHJMenuItems);
        itemPausar.addActionListener( e -> pausarCronometro() );
        itemPausar.setEnabled(false);
        
        HJMenuItem itemReiniciar = new HJMenuItem(new IconoDeImagen("Reiniciar.png", -1, 16), "Reiniciar", coloresHJMenuItems);
        itemReiniciar.addActionListener( e -> reiniciarCronometro() );
        itemReiniciar.setEnabled(false);
        
        HJMenuItem itemAjustar = new HJMenuItem(null, "Ajustar", coloresHJMenuItems);
        itemAjustar.addActionListener( e -> mostrarPanelAjustarCronometro(false) );
        
        menuPopup = new HJPopupMenu();
        menuPopup.add(itemIniciar);
        menuPopup.add(itemDetener);
        menuPopup.addSeparator();
        menuPopup.add(itemPausar);
        menuPopup.add(itemReiniciar);
        menuPopup.addSeparator();
        menuPopup.add(itemAjustar);
        
        
        boton.establecerPopupMenu(menuPopup);
        
    }
    
    
    private void iniciarCronometro() {
        
        if(hiloCronometro.obtenerDuracionInicial().isZero())
        {
            mostrarPanelAjustarCronometro(true);
            
        }else{
            
            menuPopup.obtenerMenuItem(INICIAR).setEnabled(false);
            menuPopup.obtenerMenuItem(DETENER).setEnabled(true);
            menuPopup.obtenerMenuItem(PAUSAR).setEnabled(true);
            menuPopup.obtenerMenuItem(REINICIAR).setEnabled(false);
            
            hiloCronometro.pausar();
            
            hiloCronometro = new HiloCronometro(lCronometro, hiloCronometro.obtenerDuracionInicial());
            
            hiloCronometro.correr();
        }
    }
    
    
    private void detenerCronometro() {
        
        menuPopup.obtenerMenuItem(INICIAR).setEnabled(true);
        menuPopup.obtenerMenuItem(DETENER).setEnabled(false);
        menuPopup.obtenerMenuItem(PAUSAR).setEnabled(false);
        menuPopup.obtenerMenuItem(REINICIAR).setEnabled(false);
        
        hiloCronometro.pausar();
        
        hiloCronometro = new HiloCronometro(lCronometro, hiloCronometro.obtenerDuracionInicial());
        
        hiloCronometro.establecerHorasMinutosSegundos((int)hiloCronometro.obtenerDuracionInicial().getSeconds());
    }
    
    
    private void pausarCronometro() {
        
        menuPopup.obtenerMenuItem(INICIAR).setEnabled(false);
        menuPopup.obtenerMenuItem(DETENER).setEnabled(true);
        menuPopup.obtenerMenuItem(PAUSAR).setEnabled(false);
        menuPopup.obtenerMenuItem(REINICIAR).setEnabled(true);
        
        hiloCronometro.pausar();
    }
    
    
    private void reiniciarCronometro() {
        
        menuPopup.obtenerMenuItem(INICIAR).setEnabled(false);
        menuPopup.obtenerMenuItem(DETENER).setEnabled(true);
        menuPopup.obtenerMenuItem(PAUSAR).setEnabled(true);
        menuPopup.obtenerMenuItem(REINICIAR).setEnabled(false);
        
        Duration duracionParcial = hiloCronometro.obtenerDuracionParcial();
        
        hiloCronometro = new HiloCronometro(lCronometro, hiloCronometro.obtenerDuracionInicial());
        hiloCronometro.establecerDuracionParcial(duracionParcial);
        
        hiloCronometro.correr();
    }
    
    
    private void mostrarPanelAjustarCronometro(boolean seIniciaDeInmediato) {
        
        PanelAjustarTiempo panelAjustarTiempo = new PanelAjustarTiempo(hiloCronometro.obtenerDuracionInicial());
        panelAjustarTiempo.setVisible(true);
        
        if(panelAjustarTiempo.fueTiempoEstablecido())
        {
            hiloCronometro.pausar();
            
            hiloCronometro = new HiloCronometro(lCronometro,  panelAjustarTiempo.obtenerDuracion());
            
            if(seIniciaDeInmediato)
            {
                menuPopup.obtenerMenuItem(INICIAR).setEnabled(false);
                menuPopup.obtenerMenuItem(DETENER).setEnabled(true);
                menuPopup.obtenerMenuItem(PAUSAR).setEnabled(true);
                menuPopup.obtenerMenuItem(REINICIAR).setEnabled(false);
                
                hiloCronometro.correr();
                
            }else{
                
                menuPopup.obtenerMenuItem(INICIAR).setEnabled(true);
                menuPopup.obtenerMenuItem(DETENER).setEnabled(false);
                menuPopup.obtenerMenuItem(PAUSAR).setEnabled(false);
                menuPopup.obtenerMenuItem(REINICIAR).setEnabled(false);
                
                hiloCronometro.establecerHorasMinutosSegundos((int)hiloCronometro.obtenerDuracionInicial().getSeconds());
            }
        }
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class HiloCronometro extends Thread {
        
        private final JLabel lCronometro;
        private Duration duracionInicial;
        private Duration duracion;
        private boolean corre = true;
        
        
        public HiloCronometro(JLabel lCronometro, Duration duracion) {
            
            this.lCronometro = lCronometro;
            duracionInicial = duracion;
            this.duracion = duracion;
            
        }
        
        
        @Override
        public void run() {
            
            while(corre)
            {
                int segundosTotales = (int)duracion.getSeconds();
                
                establecerHorasMinutosSegundos(segundosTotales);
                
                while(segundosTotales>0)
                {
                    if(esperarUnSegundo().equals(State.TERMINATED))
                        return;
                    
                    if(isInterrupted())
                        return;
                    
                    segundosTotales--;
                    
                    establecerHorasMinutosSegundos(segundosTotales);
                    
                    duracion = Duration.ofSeconds(segundosTotales);
                }
                
                corre = false;
            }
            
            HJDialog.mostrarMensaje("Tiempo Agotado", new String[] {"El tiempo se agotó."}, IconoDeImagen.INFORMACION, null);
            
            detenerCronometro();
        }
        
        
        public void establecerHorasMinutosSegundos(int segundosTotales) {
            
            String horasString;
            String minutosString;
            String segundosString;
            
            int minutosTotales = segundosTotales/60;
            int horasTotales = minutosTotales/60;
            
            
            int segundosRestantes = segundosTotales%60;
            
            if(segundosRestantes<10) segundosString = "0"+segundosRestantes;
            else segundosString = ""+segundosRestantes;
            
            
            int minutosRestantes = minutosTotales%60;
            
            if(minutosRestantes<10) minutosString = "0"+minutosRestantes;
            else minutosString = ""+minutosRestantes;
            
            
            if(horasTotales<10) horasString = "0"+horasTotales;
            else horasString = ""+horasTotales;
            
            
            lCronometro.setText(horasString+":"+minutosString+":"+segundosString);
        }
        
        
        private State esperarUnSegundo() {
            
            try{
                sleep(1000);
            }catch(InterruptedException ie) {
                return State.TERMINATED;
            }
            
            return getState();
        }
        
        
        public void correr() {
            
            start();
        }
        
        
        public void pausar() {
            
            interrupt();
        }
        
        
        public void establecerDuracionInicial(Duration duracion) {
            
            duracionInicial = duracion;
            
            establecerHorasMinutosSegundos((int)duracionInicial.getSeconds());
        }
        
        
        public Duration obtenerDuracionInicial() {
            
            return duracionInicial;
        }
        
        
        public void establecerDuracionParcial(Duration duracion) {
            
            this.duracion = duracion;
        }
        
        
        public Duration obtenerDuracionParcial() {
            
            return duracion;
        }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
