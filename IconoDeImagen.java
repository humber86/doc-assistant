/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;


public class IconoDeImagen extends ImageIcon{
    
    public static final String INFORMACION = "Informacion.png";
    public static final String ADVERTENCIA = "Advertencia.png";
    public static final String PREGUNTA = "Pregunta.png";
    public static final String ERROR = "Error.png";
    
    
    public IconoDeImagen(String nombreIcono, int ancho, int altura) {
        
        URL urlIcono = getClass().getResource("images/"+nombreIcono);
        
        if(urlIcono==null)  DocAssistant.mostrarMensajeError_Y_Salir();
        
        ImageIcon iconoImagen = new ImageIcon(urlIcono);
        
        float anchoIcono = iconoImagen.getIconWidth();
        float alturaIcono = iconoImagen.getIconHeight();
        
        
        float anchoFinal = ancho;
        float alturaFinal = altura;
        
        
        if(ancho<0 && altura<0)
        {
            anchoFinal = anchoIcono;
            alturaFinal = alturaIcono;
            
        }
        
        if(ancho<0 && altura>=0)
            anchoFinal = altura*(anchoIcono/alturaIcono);
        
        if(ancho>=0 && altura<0)
            alturaFinal = ancho*(alturaIcono/anchoIcono);
        
        
        
        setImage(iconoImagen.getImage().getScaledInstance(Math.round(anchoFinal), Math.round(alturaFinal), Image.SCALE_SMOOTH));
        
    }
    
    
}
