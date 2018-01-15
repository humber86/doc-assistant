/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.Toolkit;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class EnvioEmail {
    
    public static int ACEPTAR = 0;
    
    
    public static int mostrarDialogo() {
        
        return HJDialog.mostrarDialogoPregunta("Error", new String[] {"Ocurrió un error inesperado.", " ", "¡Ayúdanos a mejorar tu programa!", " ", "Se enviará de manera anónima una notificación acerca de este error a Abanico Systems", "para darle solución en una posterior actualización del programa."}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ERROR, null);
    }
    
    
    public static boolean enviarReporte(Thread hilo, Throwable excepcion) {
        
        String host = "smtp.zoho.com";
        int puerto = 465;
        String remitente = "error@abanicosystems.com";
        String nombreUsuario = "error@abanicosystems.com";
        String contrasena = "zohoerror1000!";
        String destinatario = "error@abanicosystems.com";
        String asunto = "Excepción Doc Assistant "+Version.PRIMER_NIVEL+"."+Version.SEGUNDO_NIVEL+"."+Version.TERCER_NIVEL;
        
        
        StringWriter escritorDeCadena = new StringWriter();
        
        PrintWriter escritorDeImpresion = new PrintWriter(escritorDeCadena, true);
        
        if(hilo!=null)
            escritorDeImpresion.println("Uncaught Exception. Hilo: "+hilo.toString());
        
        excepcion.printStackTrace(escritorDeImpresion);
        
        escritorDeImpresion.flush();
        
        escritorDeImpresion.close();
        
        
        Properties propiedadesSistema = System.getProperties();
        
        propiedadesSistema.put("mail.smtp.host", host);
        propiedadesSistema.put("mail.smtp.port", puerto);
        propiedadesSistema.put("mail.smtp.ssl.enable", true);
        propiedadesSistema.put("mail.smtp.auth", true);
        
        
        Authenticator autenticador = new Authenticator() {
            
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                
                return new PasswordAuthentication(nombreUsuario, contrasena);
            }
        };
        
        
        Session sesion = Session.getInstance(propiedadesSistema, autenticador);
        
        MimeMessage mensaje = new MimeMessage(sesion);
        
        try{
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensaje.setSubject(asunto);
            
            mensaje.setText(escritorDeCadena.toString());
            
            Transport.send(mensaje);
            
        }catch(AddressException ae) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error en Envío", new String[] {"Error en envío de mensaje (tipo 1)."}, IconoDeImagen.ERROR, null);
            return false;
        }catch(MessagingException me) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error en Envío", new String[] {"Error en envío de mensaje (tipo 2)."}, IconoDeImagen.ERROR, null);
            return false;
        }
        
        
        return true;
    }
    
    
    public static void mostrarMensajeNotificacionEnviada() {
        
        HJDialog.mostrarMensaje("Notificación Enviada", new String[] {"La notificación fue enviada."}, IconoDeImagen.INFORMACION, null);
    }
    
    
}
