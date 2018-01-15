/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;


public class DocAssistantApp {
    
    
    public static void main(String[] args) {
        
        try{
            Thread.setDefaultUncaughtExceptionHandler( (hilo, excepcion) -> enviarMensajeError(hilo, excepcion) );
        }catch(SecurityException se) {
            enviarMensajeError(null, se);
        }
        
        
        try{
            crearArchivoRegistro();
            
            DocAssistant ventanaPrincipal = new DocAssistant();
            
            controlarSplashScreen();
            
            
            Registro.comprobarIntegridad();
            
            if(Registro.estaProductoActivado()==false)  mostrarMensajeDiasRestantes();
            
            ComprobadorResolucion.comprobarResolucion();
            
            PanelDatosDoctor.comprobarInformacionDoctor();
            
            
            ventanaPrincipal.ejecutarMetodos_E_Hilos();
            ventanaPrincipal.setVisible(true);
            ventanaPrincipal.comprobarRecipesPersonalizados();
            
            comprobarActualizaciones();
            
        }catch(Exception excepcion) {
            enviarMensajeError(null, excepcion);
        }
    }
    
    
    public static void enviarMensajeError(Thread hilo, Throwable throwable) {
        
        Toolkit.getDefaultToolkit().beep();
        
        if(EnvioEmail.mostrarDialogo()==EnvioEmail.ACEPTAR)
        {
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloEnviarReporte = new Thread( () ->{
               
                boolean seEnvio = EnvioEmail.enviarReporte(hilo, throwable);
                
                panelEspera.setVisible(false);
                panelEspera.dispose();
                
                if(seEnvio)  EnvioEmail.mostrarMensajeNotificacionEnviada();
            });
            hiloEnviarReporte.start();
            
            panelEspera.setVisible(true);
        }
    }
    
    
    private static void crearArchivoRegistro() {
        
        File archivoRegistro = new File(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        if(archivoRegistro.exists()==false)
        {
            MapaDatos mapaDatos = new MapaDatos();
            
            Utilidades.guardarEnArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda", mapaDatos);
        }
    }
    
    
    private static void controlarSplashScreen() {
        
        SplashScreen splashScreen = SplashScreen.getSplashScreen();
        
        if(splashScreen==null)
        {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error", new String[] {"Los archivos del programa fueron alterados.", " ","El programa no puede iniciar."}, IconoDeImagen.ERROR, null);
            System.exit(0);
        }
        
        try{
            Thread.sleep(3000);
        }catch(InterruptedException ie) {
            enviarMensajeError(null, ie);
        }
        
        splashScreen.close();
    }
    
    
    private static void mostrarMensajeDiasRestantes() {
        
        long diasRestantes = Registro.obtenerDiasDePruebaRestantes();
        
        if(diasRestantes<=0)
        {
            mostrarMensajeCaducado();
            
            return;
        }
        
        int seleccion = HJDialog.mostrarDialogoPregunta("Período de Prueba", new String[] {"Aún dispones de "+diasRestantes+" días de prueba."}, new HJButton[] {new HJButton(null, "Adquirir Licencia de Uso", Colores.COLORES_BOTONES), new HJButton(null, "Introducir Código de Activación", Colores.COLORES_BOTONES), new HJButton(null, "Seguir probando", Colores.COLORES_BOTONES)}, 2, IconoDeImagen.INFORMACION, null);
        
        if(seleccion==0)
        {
            PanelAdquirirLicencia panelAdquirirLicencia = new PanelAdquirirLicencia();
            panelAdquirirLicencia.setVisible(true);
        }
        
        if(seleccion==1)
        {
            PanelActivacion panelActivacion = new PanelActivacion();
            panelActivacion.setVisible(true);
        }
    }
    
    
    private static void mostrarMensajeCaducado() {
        
        Toolkit.getDefaultToolkit().beep();
        
        int seleccion = HJDialog.mostrarDialogoPregunta("El Período de Prueba ha Caducado", new String[] {"El período que disponías para probar el programa ha caducado.", " ", "Si deseas seguir haciendo uso del programa debes adquirir la Licencia de Uso,", "o, si ya dispones de ella, debes ingresar el código de activación."}, new HJButton[] {new HJButton(null, "Adquirir Licencia de Uso", Colores.COLORES_BOTONES), new HJButton(null, "Introducir Código de Activación", Colores.COLORES_BOTONES), new HJButton(null, "Salir", Colores.COLORES_BOTONES)}, 2, IconoDeImagen.ADVERTENCIA, null);
        
        if(seleccion==0)
        {
            PanelAdquirirLicencia panelAdquirirLicencia = new PanelAdquirirLicencia();
            panelAdquirirLicencia.setVisible(true);
            
            if(Registro.estaProductoActivado()==false)  System.exit(0);
        }
        
        if(seleccion==1)
        {
            PanelActivacion panelActivacion = new PanelActivacion();
            panelActivacion.setVisible(true);
            
            if(Registro.estaProductoActivado()==false)  System.exit(0);
        }
        
        if(seleccion==2)
            System.exit(0);
    }
    
    
    private static void comprobarActualizaciones() {
        
        try{
            Runtime.getRuntime().exec("Updater.exe /silent");
        }catch(IOException ioe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error en Actualizador", new String[] {"Ocurrió un error con el subprograma de actualización."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
}
