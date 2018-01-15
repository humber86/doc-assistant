/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;


public class ComprobadorResolucion {
    
    
    public static void comprobarResolucion() {
        
        GraphicsDevice pantalla = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        
        DisplayMode modoResolucion = pantalla.getDisplayMode();
        
        int ancho = modoResolucion.getWidth();
        int altura = modoResolucion.getHeight();
        
        if(ancho<1280 || altura<768)
        {
            int seleccion = HJDialog.mostrarDialogoPregunta("Resolución de Pantalla", new String[] {"Para ejecutar este programa la resolución de su pantalla debe ser", "igual o mayor a 1280x768.", "La resolución actual de su pantalla es de "+ancho+"x"+altura+".", " ", "Por favor, ajuste la resolución para que sea mayor o igual a la requerida.", "Si no sabe cómo hacerlo, haga click en 'Ayuda' para llevarlo a", "'Preguntas Frecuentes', donde encontrará la información necesaria", "para realizar este ajuste."}, new HJButton[] {new HJButton(null, "Ayuda", Colores.COLORES_BOTONES), new HJButton(null, "Salir", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null);
            
            if(seleccion==0)  Utilidades.abrirVinculo("http://www.abanicosystems.com/preguntas-frecuentes");
            
            System.exit(0);
        }
    }
    
    
}
