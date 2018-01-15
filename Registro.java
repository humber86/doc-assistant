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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class Registro {
    
    public static final String ID_SISTEMA = "ID del Sistema";
    public static final String CODIGO_ACTIVACION = "Codigo Activacion";
    
    private static final String cadenaValida = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String cadenaParaIndices = "NM5RQP6UTS7XWV8ZY90CBA1FED2IHG3LKJ4O";
    private static final String cadenaParaCodificar = "9CB8DEFA7IH6KLG5JNM4PQR3UT2VXWS1ZY99";
    private static final String cadenaParaCodigoValidacion = "5DEXS7YC9HU2PQLG9N8BZ6VWFA491JKRM3TI";
    
    
    private static String obtenerSerialDiscoDuro() {
        
        String salida = "";
        
        try{
            File archivoTemporal = File.createTempFile("dsnda", ".vbs");
            
            FileWriter escritorDeArchivo = new FileWriter(archivoTemporal);
            
            String script = "Dim puntoString\n"
                    + "Dim solicitud\n"
                    + "Dim objetoWMI\n"
                    + "Dim discosDuros\n"
                    + "Dim discoDuro\n"
                    + "Dim particiones\n"
                    + "Dim particion\n"
                    + "Dim discosLogicos\n"
                    + "Dim discoLogico\n"
                    + "\n"
                    + "puntoString = \".\"\n"
                    + "Set objetoWMI = GetObject(\"winmgmts:\\\\\" & puntoString & \"\\root\\cimv2\")\n"
                    + "Set discosDuros = objetoWMI.ExecQuery(\"SELECT * FROM Win32_DiskDrive\",,48)\n"
                    + "For Each discoDuro In discosDuros\n"
                    + "    solicitud = \"ASSOCIATORS OF {Win32_DiskDrive.DeviceID='\" + discoDuro.DeviceID + \"'} WHERE AssocClass = Win32_DiskDriveToDiskPartition\"\n"
                    + "    Set particiones = objetoWMI.ExecQuery(solicitud)\n"
                    + "    For Each particion In particiones\n"
                    + "        solicitud = \"ASSOCIATORS OF {Win32_DiskPartition.DeviceID='\" + particion.DeviceID + \"'} WHERE AssocClass = Win32_LogicalDiskToPartition\"\n"
                    + "        Set discosLogicos = objetoWMI.ExecQuery(solicitud)\n"
                    + "        For Each discoLogico In discosLogicos\n"
                    + "            If discoLogico.DeviceID = \"C:\" Then\n"
                    + "                Wscript.Echo discoDuro.SerialNumber\n"
                    + "            End If\n"
                    + "        Next\n"
                    + "    Next\n"
                    + "Next";
            
            escritorDeArchivo.write(script);
            escritorDeArchivo.close();
            
            Process proceso = Runtime.getRuntime().exec("cscript //NoLogo "+archivoTemporal.getPath());
            
            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            
            String linea;
            
            while((linea = lector.readLine()) !=null)
            {
                salida+=linea;
            }
            
            lector.close();
            
            archivoTemporal.delete();
            
        }catch(IOException ioe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error con los registros."}, IconoDeImagen.ERROR, null);
        }
        
        
        if(salida.isEmpty())  return null;
        
        
        salida = salida.toUpperCase();
        
        String salidaCorregida = "";
        for(int i=0 ; i<=salida.length()-1 ; i++)
        {
            if(cadenaValida.contains(""+salida.charAt(i)))
                salidaCorregida += salida.charAt(i);
        }
        
        
        if(salidaCorregida.length()<8)
        {
            while(salidaCorregida.length()<8)
            {
                salidaCorregida = "0"+salidaCorregida;
            }
            
        }else{
            
            salidaCorregida = salidaCorregida.substring(salidaCorregida.length()-8, salidaCorregida.length());
        }
        
        
        return salidaCorregida;
    }
    
    
    private static LocalDateTime obtenerFechaHoraArchivo() {
        
        File archivo = new File(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        Path path = archivo.toPath();
        
        BasicFileAttributes atributos = null;
        try{
            atributos = Files.readAttributes(path, BasicFileAttributes.class);
        }catch(IOException ioe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error del Sistema", new String[] {"No se puede acceder a ciertos atributos del sistema."}, IconoDeImagen.ERROR, null);
            DocAssistantApp.enviarMensajeError(null, ioe);
        }
        
        if(atributos!=null)
            return LocalDateTime.ofInstant(atributos.creationTime().toInstant(), ZoneId.systemDefault());
        else
            return null;
    }
    
    
    private static LocalDateTime obtenerFechaHoraCarpeta() {
        
        File carpeta = new File(Directorios.DIRECTORIO_PRINCIPAL);
        
        Path path = carpeta.toPath();
        
        BasicFileAttributes atributos = null;
        try{
            atributos = Files.readAttributes(path, BasicFileAttributes.class);
        }catch(IOException ioe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error del Sistema", new String[] {"No se puede acceder a ciertos atributos del sistema."}, IconoDeImagen.ERROR, null);
            DocAssistantApp.enviarMensajeError(null, ioe);
        }
        
        if(atributos!=null)  return LocalDateTime.ofInstant(atributos.creationTime().toInstant(), ZoneId.systemDefault());
        else  return null;
    }
    
    
    private static String obtenerIDSistema() {
        
        String serialDiscoDuro = obtenerSerialDiscoDuro();
        String fechaRecortadaArchivo = obtenerFechaHoraArchivo().format(DateTimeFormatter.ofPattern("MMHHmmss"));
        
        String cadenaCombinada = "";
        for(int i=0 ; i<=fechaRecortadaArchivo.length()-1 ; i++)
        {
            cadenaCombinada += serialDiscoDuro.charAt(i);
            cadenaCombinada += fechaRecortadaArchivo.charAt(i);
        }
        
        
        cadenaCombinada = cadenaCombinada.toUpperCase();
        
        int[] indices = new int[cadenaCombinada.length()];
        for(int i=0 ; i<=cadenaCombinada.length()-1 ; i++)
        {
            indices[i] = cadenaParaIndices.indexOf(cadenaCombinada.charAt(i));
        }
        
        
        String cadenaCodificada = "";
        for(int i=0 ; i<=indices.length-1 ; i++)
        {
            cadenaCodificada += cadenaParaCodificar.charAt(indices[i]);
        }
        
        
        return cadenaCodificada;
    }
    
    
    private static String obtenerCodigoActivacion(String idSistema) {
        
        idSistema = idSistema.toUpperCase();
        
        int[] indices = new int[idSistema.length()];
        int posicion1;
        int posicion2;
        for(int i=0 ; i<=indices.length-1 ; i++)
        {
            posicion1 = cadenaParaCodificar.indexOf(idSistema.charAt(i));
            
            if(i==0)  posicion2 = cadenaParaCodificar.indexOf(idSistema.charAt(idSistema.length()-1));
            else  posicion2 = cadenaParaCodificar.indexOf(idSistema.charAt(i-1));
            
            indices[i] = (posicion1+posicion2)%36;
        }
        
        String codigoActivacion = "";
        for(int i=0 ; i<=indices.length-1 ; i++)
        {
            codigoActivacion += cadenaParaCodigoValidacion.charAt(indices[i]);
        }
        
        return codigoActivacion;
    }
    
    
    public static boolean esCodigoActivacionValido(String codigoActivacion) {
        
        MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        return codigoActivacion.toUpperCase().equals(obtenerCodigoActivacion(mapaDatos.get(ID_SISTEMA)).toUpperCase());
    }
    
    
    public static void comprobarIntegridad() {
        
        LocalDateTime fechaHoraCarpeta = obtenerFechaHoraCarpeta();
        
        LocalDateTime fechaHoraInferiorCarpeta = fechaHoraCarpeta.minusMinutes(30);
        LocalDateTime fechaHoraSuperiorCarpeta = fechaHoraCarpeta.plusDays(1);
        
        LocalDateTime fechaHoraArchivo = obtenerFechaHoraArchivo();
        
        if(fechaHoraArchivo==null)  mostrarMensajeError_Y_salir();
        
        if((fechaHoraArchivo.isAfter(fechaHoraInferiorCarpeta) && fechaHoraArchivo.isBefore(fechaHoraSuperiorCarpeta))==false)
            mostrarMensajeError_Y_salir();
        
        
        MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        if(mapaDatos==null)
        {
            mostrarMensajeError_Y_salir();
            
        }else{
            
            if(mapaDatos.size()==0)
            {
                mapaDatos.put(ID_SISTEMA, obtenerIDSistema());
                
                Utilidades.guardarEnArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda", mapaDatos);
                
            }else{
                
                if(mapaDatos.containsKey(ID_SISTEMA)==false)
                {
                    mostrarMensajeError_Y_salir();
                    
                }else{
                    
                    if(obtenerIDSistema().equals(mapaDatos.get(ID_SISTEMA))==false)
                        mostrarMensajeError_Y_salir();
                }
            }
        }
    }
    
    
    public static boolean estaProductoActivado() {
        
        MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        if(mapaDatos.containsKey(CODIGO_ACTIVACION))
        {
            if(obtenerCodigoActivacion(mapaDatos.get(ID_SISTEMA)).equals(mapaDatos.get(CODIGO_ACTIVACION)))
            {
                return true;
                
            }else{
                
                Toolkit.getDefaultToolkit().beep();
                HJDialog.mostrarMensaje("Código de Activación Inválido", new String[] {"Su código de activación no es válido.", " ", "Por favor, comuníquese con Abanico Systems para obtener un código de activación válido."}, IconoDeImagen.ERROR, null);
                
                return false;
            }
                
        }else{
            
            return false;
        }
    }
    
    
    public static long obtenerDiasDePruebaRestantes() {
        
        LocalDateTime fechaHoraLimite = obtenerFechaHoraCarpeta().plusDays(31);
        
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        
        Duration intervalo = Duration.between(fechaHoraActual, fechaHoraLimite);
        
        return intervalo.toDays();
    }
    
    
    private static void mostrarMensajeError_Y_salir() {
        
        mostrarMensajeError();
        
        System.exit(0);
    }
    
    
    public static void mostrarMensajeError() {
        
        Toolkit.getDefaultToolkit().beep();
        
        HJDialog.mostrarMensaje("Archivo(s) Inválido(s)", new String[] {"Los archivos del programa fueron alterados.", " ","El programa no puede iniciar."}, IconoDeImagen.ERROR, null);
    }
    
    
}
