/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class Utilidades {
    
    
    public static boolean esTextoValido(String texto) {
        
        for(int i=0 ; i<=texto.length()-1 ; i++)
        {
            if(texto.charAt(i)!=' ' && texto.charAt(i)!='\t')
                return true;
        }
        
        return false;
    }
    
    
    public static boolean esNumerico(String texto) {
        
        for(int i=0 ; i<=texto.length()-1 ; i++)
        {
            if(Character.isDigit(texto.charAt(i))==false && texto.charAt(i)!='.' && texto.charAt(i)!='-')
                return false;
        }
        
        return true;
    }
    
    
    public static boolean esNumeroEntero(String texto) {
        
        for(int i=0 ; i<=texto.length()-1 ; i++)
        {
            if(Character.isDigit(texto.charAt(i))==false && texto.charAt(i)!='-')
                return false;
        }
        
        return true;
    }
    
    
    public static boolean esNumeroEnteroPositivo(String texto) {
        
        for(int i=0 ; i<=texto.length()-1 ; i++)
        {
            if(Character.isDigit(texto.charAt(i))==false)
                return false;
        }
        
        return true;
    }
    
    
    public static boolean esAnoBisiesto(int ano) {
        
        LocalDate fechaAuxiliar = LocalDate.of(ano, 1, 1);
        
        return fechaAuxiliar.isLeapYear();
    }
    
    
    public static boolean esFechaValida(int dia, int mes, int ano) {
        
        if(mes==2 || mes==4 || mes==6 || mes==9 || mes==11)
        {
            if(mes==2)
            {
                if(dia>29)
                {
                    return false;
                    
                }else{
                    
                    if(dia>28)  return esAnoBisiesto(ano);
                    else  return true;
                }
                
            }else{
                
                return dia<31;
            }
            
        }else{
            
            return true;
        }
    }
    
    
    public static boolean esFechaAnterior(int dia, int mes, int ano) {
        
        LocalDate fechaPasada = LocalDate.of(ano, mes, dia);
        
        LocalDate fechaActual = LocalDate.now();
        
        return fechaPasada.isBefore(fechaActual) || fechaPasada.isEqual(fechaActual);
    }
    
    
    public static String obtenerTiempoTranscurrido(LocalDate fechaAnterior, LocalDate fechaPosterior) {
        
        Period tiempoTranscurrido = Period.between(fechaAnterior, fechaPosterior);
        
        int anos = tiempoTranscurrido.getYears();
        int meses = tiempoTranscurrido.getMonths();
        int dias = tiempoTranscurrido.getDays();
        
        if(anos==0)
        {
            if(meses==0)
            {
                String diasString = dias+" día";
                if(dias!=1)  diasString += "s";
                
                return diasString;
                
            }else{
                
                String mesesString = meses+" mes";
                if(meses>1)  mesesString += "es";
                
                String diasString = dias+" día";
                if(dias!=1)  diasString += "s";
                
                return mesesString+", "+diasString;
            }
            
        }else{
            
            String anosString = anos+" año";
            if(anos>1)  anosString += "s";
            
            String mesesString = meses+" mes";
            if(meses!=1)  mesesString += "es";
            
            String diasString = dias+" día";
            if(dias!=1)  diasString += "s";
            
            return anosString+", "+mesesString+", "+diasString;
        }
    }
    
    
    public static String obtenerAnosTranscurridos(LocalDate fechaAnterior, LocalDate fechaPosterior) {
        
        Period tiempoTranscurrido = Period.between(fechaAnterior, fechaPosterior);
        
        int anos = tiempoTranscurrido.getYears();
        
        String anosString = anos+" año";
        if(anos!=1)  anosString += "s";
        
        return anosString;
    }
    
    
    public static String obtenerEdad(String fechaDiaMesAno) {
        
        String[] cadena = fechaDiaMesAno.split("-");
        
        LocalDate fechaNacimiento = LocalDate.of(new Integer(cadena[2]), new Integer(cadena[1]), new Integer(cadena[0]));
        LocalDate fechaActual = LocalDate.now();
        
        return obtenerAnosTranscurridos(fechaNacimiento, fechaActual);
    }
    
    
    public static int obtenerEdadEntero(String fechaDiaMesAno) {
        
        String[] cadena = fechaDiaMesAno.split("-");
        
        LocalDate fechaNacimiento = LocalDate.of(new Integer(cadena[2]), new Integer(cadena[1]), new Integer(cadena[0]));
        
        Period tiempoTranscurrido = Period.between(fechaNacimiento, LocalDate.now());
        
        return tiempoTranscurrido.getYears();
    }
    
    
    public static String obtenerEdadMinimaCadena(String fechaDiaMesAno) {
        
        String[] cadena = fechaDiaMesAno.split("-");
        
        LocalDate fechaNacimiento = LocalDate.of(new Integer(cadena[2]), new Integer(cadena[1]), new Integer(cadena[0]));
        
        Period tiempoTranscurrido = Period.between(fechaNacimiento, LocalDate.now());
        
        int anos = tiempoTranscurrido.getYears();
        int meses = tiempoTranscurrido.getMonths();
        int dias = tiempoTranscurrido.getDays();
        
        if(anos==0)
        {
            if(meses==0)
            {
                String diasString = dias+" día";
                if(dias!=1)  diasString += "s";
                
                return diasString;
                
            }else{
                
                String mesesString = meses+" mes";
                if(meses>1)  mesesString += "es";
                
                return mesesString;
            }
            
        }else{
            
            String anosString = anos+" año";
            if(anos>1)  anosString += "s";
            
            return anosString;
        }
    }
    
    
    public static Object leerArchivo(String path) {
        
        File archivo = new File(path);
        
        Object objeto = null;
        
        if(archivo.exists())
        {
            try{
                ObjectInputStream flujoDeEntradaDeObjeto = new ObjectInputStream(new FileInputStream(archivo));
                
                objeto = flujoDeEntradaDeObjeto.readObject();
                
                flujoDeEntradaDeObjeto.close();
                
            }catch(FileNotFoundException fnfe) {
                Toolkit.getDefaultToolkit().beep();
                HJDialog.mostrarMensaje("Error", new String[] {"No se encontró el archivo."}, IconoDeImagen.ERROR, null);
            }catch(IOException ioe) {
                Toolkit.getDefaultToolkit().beep();
                HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error de lectura/escritura."}, IconoDeImagen.ERROR, null);
            }catch(ClassNotFoundException cnfe) {
                Toolkit.getDefaultToolkit().beep();
                HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error de archivos internos."}, IconoDeImagen.ERROR, null);
            }
        }
        
        return objeto;
    }
    
    
    public static void guardarEnArchivo(String path, Object objeto) {
        
        File archivo = new File(path);
        
        try{
            ObjectOutputStream flujoDeSalidaDeObjeto = new ObjectOutputStream(new FileOutputStream(archivo));
            
            flujoDeSalidaDeObjeto.writeObject(objeto);
            
            flujoDeSalidaDeObjeto.close();
            
        }catch(FileNotFoundException fnfe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error", new String[] {"No se encontró el archivo."}, IconoDeImagen.ERROR, null);
        }catch(IOException ioe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error de lectura/escritura."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
    public static void crear_Y_GuardarEnArchivo(String pathDirectorio, String nombreArchivo, Object objeto) {
        
        if(pathDirectorio.endsWith("\\")==false)  pathDirectorio = pathDirectorio+"\\";
        
        File directorio = new File(pathDirectorio);
        
        if(directorio.exists()==false)  directorio.mkdirs();
        
        
        File archivo = new File(pathDirectorio+nombreArchivo);
        
        try{
            ObjectOutputStream flujoDeSalidaDeObjeto = new ObjectOutputStream(new FileOutputStream(archivo));
            
            flujoDeSalidaDeObjeto.writeObject(objeto);
            
            flujoDeSalidaDeObjeto.close();
            
        }catch(FileNotFoundException fnfe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error", new String[] {"No se encontró el archivo."}, IconoDeImagen.ERROR, null);
        }catch(IOException ioe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error de lectura/escritura."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
    public static void eliminarArchivo(String path) {
        
        File archivo = new File(path);
        
        if(archivo.exists())  archivo.delete();
    }
    
    
    public static void eliminarDirectorioConArchivos(String pathDirectorio) {
        
        if(pathDirectorio.endsWith("\\")==false)  pathDirectorio = pathDirectorio+"\\";
        
        
        File carpeta = new File(pathDirectorio);
        
        if(carpeta.exists()==false)  return;
        
        if(carpeta.isDirectory()==false)
        {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error", new String[] {"La siguiente ubicación no es un directorio:", pathDirectorio}, IconoDeImagen.ERROR, null);
            return;
        }
        
        
        String[] listaNombresArchivos = carpeta.list();
        
        for(int i=0 ; i<=listaNombresArchivos.length-1 ; i++)
        {
            (new File(pathDirectorio+listaNombresArchivos[i])).delete();
        }
        
        carpeta.delete();
    }
    
    
    public static String obtenerNombreUltimoArchivo(String pathDirectorio) {
        
        File directorio = new File(pathDirectorio);
        
        if(directorio.isDirectory()==false)  return null;
        
        String[] cadenaArchivos = directorio.list();
        
        if(cadenaArchivos.length==0)  return null;
        
        return cadenaArchivos[cadenaArchivos.length-1];
    }
    
    
    public static String obtenerPathUltimoArchivo(String pathDirectorio) {
        
        if(pathDirectorio.endsWith("\\")==false)  pathDirectorio = pathDirectorio+"\\";
        
        File directorio = new File(pathDirectorio);
        
        if(directorio.isDirectory()==false)  return null;
        
        String[] cadenaArchivos = directorio.list();
        
        if(cadenaArchivos.length==0)  return null;
        
        return pathDirectorio+cadenaArchivos[cadenaArchivos.length-1];
    }
    
    
    public static Iterator<String> obtenerIteradorStringOrdenado(HashMap mapa) {
        
        ArrayList<String> array = new ArrayList<>(0);
        
        Set<String> keys = mapa.keySet();
        
        keys.stream()
                .sorted( (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase()) )
                .forEach( s -> array.add(s) );
        
        return array.iterator();
    }
    
    
    public static Iterator<String> obtenerIteradorStringOrdenado(Set<String> set) {
        
        ArrayList<String> array = new ArrayList<>(0);
        
        set.stream()
                .sorted( (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase()) )
                .forEach( s -> array.add(s) );
        
        return array.iterator();
    }
    
    
    public static Iterator<String> obtenerIteradorStringOrdenado(ArrayList<String> array) {
        
        ArrayList<String> arrayAuxiliar = new ArrayList<>(0);
        
        array.stream()
                .sorted( (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase()) )
                .forEach( s -> arrayAuxiliar.add(s) );
        
        return arrayAuxiliar.iterator();
    }
    
    
    public static ArrayList<String> obtenerArrayStringOrdenado(ArrayList<String> array) {
        
        ArrayList<String> arrayAuxiliar = new ArrayList<>(0);
        
        array.stream()
                .sorted( (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase()) )
                .forEach( s -> arrayAuxiliar.add(s) );
        
        return arrayAuxiliar;
    }
    
    
    public static void esperar(long milisegundos) {
        
        try{
            Thread.sleep(milisegundos);
        }catch(InterruptedException ie) {
            HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error en un hilo de espera."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
    public static void abrirVinculo(String direccion) {
        
        if(Desktop.isDesktopSupported() && GraphicsEnvironment.isHeadless()==false)
        {
            Desktop escritorio = Desktop.getDesktop();
            
            if(escritorio.isSupported(Desktop.Action.BROWSE))
            {
                boolean ocurrioError = false;
                
                try{
                    escritorio.browse(new URI(direccion));
                    
                }catch( IOException | URISyntaxException exc) {
                    
                    ocurrioError = true;
                    
                }finally{
                    
                    if(ocurrioError)
                    {
                        Toolkit.getDefaultToolkit().beep();
                        HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error al abrir el navegador predeterminado."}, IconoDeImagen.ERROR, null);
                    }
                }
            }
            
        }else{
            
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Acción No Soportada", new String[] {"El sistema operativo no tiene soporte para la acción solicitada."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
    public static boolean estaDisponibleFamilia(String familia) {
        
        String[] familiasDisponibles = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        
        for(int i=0 ; i<=familiasDisponibles.length-1 ; i++)
        {
            if(familiasDisponibles[i].equals(familia))
            {
                return true;
            }
        }
        
        return false;
    }
    
    
    public static BufferedImage crearImagenBuffered(Image imagen) {
        
        BufferedImage imagenBuferred = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2D = imagenBuferred.createGraphics();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.drawImage(imagen, 0, 0, null);
        
        return imagenBuferred;
    }
    
    
}
