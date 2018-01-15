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
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Procedimientos {
    
    
    public static ArrayList<Integer> obtenerArrayOrdenadoHistorias() {
        
        String[] listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        ArrayList<Integer> arrayNumeros = new ArrayList<>(0);
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            arrayNumeros.add(Integer.parseInt((listaHistorias[i].split(".dda"))[0]));
        }
        
        arrayNumeros.sort( (n1,n2) -> n1.compareTo(n2) );
        
        return arrayNumeros;
    }
    
    
    public static int obtenerNroHistoria() {
        
        ArrayList<Integer> arrayHistorias = obtenerArrayOrdenadoHistorias();
        
        if(arrayHistorias.isEmpty())  return 1;
        else  return arrayHistorias.get(arrayHistorias.size()-1) + 1;
    }
    
    
    public static RespaldoHistoria crearRespaldoHistoria(int numeroHistoria) {
        
        RespaldoHistoria respaldo = new RespaldoHistoria();
        
        
        respaldo.put(Respaldo.DATOS_PERSONALES, (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+numeroHistoria+".dda"));
        
        
        File archivoFoto = new File(Directorios.FOTOS+numeroHistoria+".png");
        Image imagenFoto = null;
        if(archivoFoto.canRead())
        {
            try{
                imagenFoto = ImageIO.read(archivoFoto);
            }catch(IOException ioe) {
                Toolkit.getDefaultToolkit().beep();
                HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error al guardar la foto del paciente."}, IconoDeImagen.ERROR, null);
            }
        }
        if(imagenFoto!=null)  respaldo.put(Respaldo.FOTO, new ImageIcon(imagenFoto));
        else  respaldo.put(Respaldo.FOTO, null);
        
        
        respaldo.put(Respaldo.REPRESENTANTES, (MapaDatos)Utilidades.leerArchivo(Directorios.REPRESENTANTES+numeroHistoria+".dda"));
        
        
        respaldo.put(Respaldo.ANTECEDENTES_NEONATALES, (MapaDatos)Utilidades.leerArchivo(Directorios.ANTECEDENTES_NEONATALES+numeroHistoria+".dda"));
        
        respaldo.put(Respaldo.ANTECEDENTES_PERSONALES, (HashMap<String,String>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_PERSONALES+numeroHistoria+".dda"));
        
        respaldo.put(Respaldo.ANTECEDENTES_FAMILIARES, (HashMap<String,String>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_FAMILIARES+numeroHistoria+".dda"));
        
        respaldo.put(Respaldo.ANTECEDENTES_ALERGIAS, (HashSet<String>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_ALERGIAS+numeroHistoria+".dda"));
        
        respaldo.put(Respaldo.ANTECEDENTES_INTERVENCIONES, (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_INTERVENCIONES+numeroHistoria+".dda"));
        
        respaldo.put(Respaldo.ANTECEDENTES_HOSPITALIZACIONES, (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_HOSPITALIZACIONES+numeroHistoria+".dda"));
        
        
        respaldo.put(Respaldo.VACUNAS, (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.VACUNAS+numeroHistoria+".dda"));
        
        
        String[] listaNombresConsultas = (new File(Directorios.CONSULTAS+numeroHistoria)).list();
        
        if(listaNombresConsultas==null)  return respaldo;
        
        HashMap<String,MapaDatos> mapaMacroConsultas = new HashMap<>(0);
        
        HashMap<String,HashMap<String,HashMap<String,String>>> mapaMacroMedicamentos = new HashMap<>(0);
        
        HashMap<String,HashMap<String,ArrayList<String>>> mapaMacroExamenesLaboratorio = new HashMap<>(0);
        HashMap<String,MapaDatos> mapaMacroObservacionesExamenesLaboratorio = new HashMap<>(0);
        
        HashMap<String,HashMap<String,String>> mapaMacroEstudiosRadiologicos = new HashMap<>(0);
        HashMap<String,MapaDatos> mapaMacroObservacionesEstudiosRadiologicos = new HashMap<>(0);
        
        for(int i=listaNombresConsultas.length-1 ; i>=0 ; i--)
        {
            mapaMacroConsultas.put(listaNombresConsultas[i] ,(MapaDatos)Utilidades.leerArchivo(Directorios.CONSULTAS+numeroHistoria+"\\"+listaNombresConsultas[i]));
            
            mapaMacroMedicamentos.put(listaNombresConsultas[i], (HashMap<String,HashMap<String,String>>)Utilidades.leerArchivo(Directorios.MEDICAMENTOS+numeroHistoria+"\\"+listaNombresConsultas[i]));
            
            mapaMacroExamenesLaboratorio.put(listaNombresConsultas[i], (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.EXAMENES_DE_LABORATORIO+numeroHistoria+"\\"+listaNombresConsultas[i]));
            
            mapaMacroObservacionesExamenesLaboratorio.put(listaNombresConsultas[i], (MapaDatos)Utilidades.leerArchivo(Directorios.OBSERVACIONES_EXAMENES_DE_LABORATORIO+numeroHistoria+"\\"+listaNombresConsultas[i]));
            
            mapaMacroEstudiosRadiologicos.put(listaNombresConsultas[i], (HashMap<String,String>)Utilidades.leerArchivo(Directorios.ESTUDIOS_RADIOLOGICOS+numeroHistoria+"\\"+listaNombresConsultas[i]));
            
            mapaMacroObservacionesEstudiosRadiologicos.put(listaNombresConsultas[i], (MapaDatos)Utilidades.leerArchivo(Directorios.OBSERVACIONES_ESTUDIOS_RADIOLOGICOS+numeroHistoria+"\\"+listaNombresConsultas[i]));
        }
        
        if(mapaMacroConsultas.size()>0)
        {
            respaldo.put(Respaldo.CONSULTAS, mapaMacroConsultas);
            
            respaldo.put(Respaldo.MEDICAMENTOS, mapaMacroMedicamentos);
            
            respaldo.put(Respaldo.EXAMENES_DE_LABORATORIO, mapaMacroExamenesLaboratorio);
            respaldo.put(Respaldo.OBSERVACIONES_EXAMENES_DE_LABORATORIO, mapaMacroObservacionesExamenesLaboratorio);
            
            respaldo.put(Respaldo.ESTUDIOS_RADIOLOGICOS, mapaMacroEstudiosRadiologicos);
            respaldo.put(Respaldo.OBSERVACIONES_ESTUDIOS_RADIOLOGICOS, mapaMacroObservacionesEstudiosRadiologicos);
        }
        
        
        return respaldo;
    }
    
    
    public static void importarHistoria(int numeroHistoria, RespaldoHistoria respaldo) {
        
        Utilidades.guardarEnArchivo(Directorios.DATOS_PERSONALES+numeroHistoria+".dda", respaldo.get(Respaldo.DATOS_PERSONALES));
        
        
        if(respaldo.get(Respaldo.FOTO)!=null)
        {
            try{
                ImageIO.write(Utilidades.crearImagenBuffered(((ImageIcon)respaldo.get(Respaldo.FOTO)).getImage()), "png", new File(Directorios.FOTOS+numeroHistoria+".png"));
            }catch(IOException ioe) {
                Toolkit.getDefaultToolkit().beep();
                HJDialog.mostrarMensaje("Error al Guardar Foto", new String[] {"Ocurrió un error al guardar la foto del paciente."}, IconoDeImagen.ERROR, null);
            }
        }
        
        
        if(respaldo.get(Respaldo.REPRESENTANTES)!=null)
            Utilidades.guardarEnArchivo(Directorios.REPRESENTANTES+numeroHistoria+".dda", respaldo.get(Respaldo.REPRESENTANTES));
        
        
        if(respaldo.get(Respaldo.ANTECEDENTES_NEONATALES)!=null)
            Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_NEONATALES+numeroHistoria+".dda", respaldo.get(Respaldo.ANTECEDENTES_NEONATALES));
        
        if(respaldo.get(Respaldo.ANTECEDENTES_PERSONALES)!=null)
            Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_PERSONALES+numeroHistoria+".dda", respaldo.get(Respaldo.ANTECEDENTES_PERSONALES));
        
        if(respaldo.get(Respaldo.ANTECEDENTES_FAMILIARES)!=null)
            Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_FAMILIARES+numeroHistoria+".dda", respaldo.get(Respaldo.ANTECEDENTES_FAMILIARES));
        
        if(respaldo.get(Respaldo.ANTECEDENTES_ALERGIAS)!=null)
            Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_ALERGIAS+numeroHistoria+".dda", respaldo.get(Respaldo.ANTECEDENTES_ALERGIAS));
        
        if(respaldo.get(Respaldo.ANTECEDENTES_INTERVENCIONES)!=null)
            Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_INTERVENCIONES+numeroHistoria+".dda", respaldo.get(Respaldo.ANTECEDENTES_INTERVENCIONES));
        
        if(respaldo.get(Respaldo.ANTECEDENTES_HOSPITALIZACIONES)!=null)
            Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_HOSPITALIZACIONES+numeroHistoria+".dda", respaldo.get(Respaldo.ANTECEDENTES_HOSPITALIZACIONES));
        
        
        if(respaldo.get(Respaldo.VACUNAS)!=null)
            Utilidades.guardarEnArchivo(Directorios.VACUNAS+numeroHistoria+".dda", respaldo.get(Respaldo.VACUNAS));
        
        
        if(respaldo.get(Respaldo.CONSULTAS)!=null)
        {
            HashMap<String,MapaDatos> mapaConsultas = (HashMap<String,MapaDatos>)respaldo.get(Respaldo.CONSULTAS);
            
            Iterator<String> iteradorNombresConsultas = mapaConsultas.keySet().iterator();
            
            String nombreConsulta;
            
            HashMap<String,HashMap<String,HashMap<String,String>>> mapaMacroMedicamentos;
            
            HashMap<String,HashMap<String,ArrayList<String>>> mapaMacroExamenesLaboratorio;
            HashMap<String,MapaDatos> mapaMacroObservacionesExamenesLaboratorio;
            
            HashMap<String,HashMap<String,String>> mapaMacroEstudiosRadiologicos;
            HashMap<String,MapaDatos> mapaMacroObservacionesEstudiosRadiologicos;
            
            while(iteradorNombresConsultas.hasNext())
            {
                nombreConsulta = iteradorNombresConsultas.next();
                
                Utilidades.crear_Y_GuardarEnArchivo(Directorios.CONSULTAS+numeroHistoria, nombreConsulta, mapaConsultas.get(nombreConsulta));
                
                mapaMacroMedicamentos = (HashMap<String,HashMap<String,HashMap<String,String>>>)respaldo.get(Respaldo.MEDICAMENTOS);
                if(mapaMacroMedicamentos.get(nombreConsulta)!=null)
                    Utilidades.crear_Y_GuardarEnArchivo(Directorios.MEDICAMENTOS+numeroHistoria, nombreConsulta, mapaMacroMedicamentos.get(nombreConsulta));
                
                mapaMacroExamenesLaboratorio = (HashMap<String,HashMap<String,ArrayList<String>>>)respaldo.get(Respaldo.EXAMENES_DE_LABORATORIO);
                if(mapaMacroExamenesLaboratorio.get(nombreConsulta)!=null)
                    Utilidades.crear_Y_GuardarEnArchivo(Directorios.EXAMENES_DE_LABORATORIO+numeroHistoria, nombreConsulta, mapaMacroExamenesLaboratorio.get(nombreConsulta));
                
                mapaMacroObservacionesExamenesLaboratorio = (HashMap<String,MapaDatos>)respaldo.get(Respaldo.OBSERVACIONES_EXAMENES_DE_LABORATORIO);
                if(mapaMacroObservacionesExamenesLaboratorio.get(nombreConsulta)!=null)
                    Utilidades.crear_Y_GuardarEnArchivo(Directorios.OBSERVACIONES_EXAMENES_DE_LABORATORIO+numeroHistoria, nombreConsulta, mapaMacroObservacionesExamenesLaboratorio.get(nombreConsulta));
                
                mapaMacroEstudiosRadiologicos = (HashMap<String,HashMap<String,String>>)respaldo.get(Respaldo.ESTUDIOS_RADIOLOGICOS);
                if(mapaMacroEstudiosRadiologicos.get(nombreConsulta)!=null)
                    Utilidades.crear_Y_GuardarEnArchivo(Directorios.ESTUDIOS_RADIOLOGICOS+numeroHistoria, nombreConsulta, mapaMacroEstudiosRadiologicos.get(nombreConsulta));
                
                mapaMacroObservacionesEstudiosRadiologicos = (HashMap<String,MapaDatos>)respaldo.get(Respaldo.OBSERVACIONES_ESTUDIOS_RADIOLOGICOS);
                if(mapaMacroObservacionesEstudiosRadiologicos.get(nombreConsulta)!=null)
                    Utilidades.crear_Y_GuardarEnArchivo(Directorios.OBSERVACIONES_ESTUDIOS_RADIOLOGICOS+numeroHistoria, nombreConsulta, mapaMacroObservacionesEstudiosRadiologicos.get(nombreConsulta));
            }
        }
    }
    
    
    public static void eliminarHistoria(int numeroHistoria) {
        
        Utilidades.eliminarArchivo(Directorios.DATOS_PERSONALES+numeroHistoria+".dda");
        
        
        Utilidades.eliminarArchivo(Directorios.FOTOS+numeroHistoria+".png");
        
        
        Utilidades.eliminarArchivo(Directorios.REPRESENTANTES+numeroHistoria+".dda");
        
        
        Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_NEONATALES+numeroHistoria+".dda");
        
        Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_PERSONALES+numeroHistoria+".dda");
        
        Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_FAMILIARES+numeroHistoria+".dda");
        
        Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_ALERGIAS+numeroHistoria+".dda");
        
        Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_INTERVENCIONES+numeroHistoria+".dda");
        
        Utilidades.eliminarArchivo(Directorios.ANTECEDENTES_HOSPITALIZACIONES+numeroHistoria+".dda");
        
        
        Utilidades.eliminarArchivo(Directorios.VACUNAS+numeroHistoria+".dda");
        
        
        Utilidades.eliminarDirectorioConArchivos(Directorios.CONSULTAS+numeroHistoria);
        
        Utilidades.eliminarDirectorioConArchivos(Directorios.MEDICAMENTOS+numeroHistoria);
        
        Utilidades.eliminarDirectorioConArchivos(Directorios.EXAMENES_DE_LABORATORIO+numeroHistoria);
        
        Utilidades.eliminarDirectorioConArchivos(Directorios.OBSERVACIONES_EXAMENES_DE_LABORATORIO+numeroHistoria);
        
        Utilidades.eliminarDirectorioConArchivos(Directorios.ESTUDIOS_RADIOLOGICOS+numeroHistoria);
        
        Utilidades.eliminarDirectorioConArchivos(Directorios.OBSERVACIONES_ESTUDIOS_RADIOLOGICOS+numeroHistoria);
        
        
        String[] listaNombresDias = (new File(Directorios.CITAS)).list();
        
        HashMap<String,String> mapaDia;
        
        String key;
        
        for(int i=0 ; i<=listaNombresDias.length-1 ; i++)
        {
            mapaDia = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.CITAS+listaNombresDias[i]);
            
            boolean seModifico = false;
            
            macro:
            while(mapaDia.containsValue(""+numeroHistoria))
            {
                Iterator<String> iterador = mapaDia.keySet().iterator();
                
                while(iterador.hasNext())
                {
                    key = iterador.next();
                    
                    if(mapaDia.get(key)!=null && mapaDia.get(key).equals(""+numeroHistoria))
                    {
                        mapaDia.remove(key);
                        
                        seModifico = true;
                        
                        continue macro;
                    }
                }
            }
            
            if(seModifico)
            {
                if(mapaDia.size()>0)
                    Utilidades.guardarEnArchivo(Directorios.CITAS+listaNombresDias[i], mapaDia);
                else
                    Utilidades.eliminarArchivo(Directorios.CITAS+listaNombresDias[i]);
            }
        }
    }
    
    
    public static RespaldoTotal crearRespaldoTotal() {
        
        RespaldoTotal respaldoTotal = new RespaldoTotal();
        
        
        ArrayList<Integer> arrayHistorias = obtenerArrayOrdenadoHistorias();
        
        
        for(int i=0 ; i<=arrayHistorias.size()-1 ; i++)
        {
            respaldoTotal.put(arrayHistorias.get(i), crearRespaldoHistoria(arrayHistorias.get(i)));
        }
        
        
        return respaldoTotal;
    }
    
    
    public static void importarTodo(RespaldoTotal respaldoTotal) {
        
        Iterator<Integer> iteradorNumeros = respaldoTotal.keySet().iterator();
        
        
        ArrayList<Integer> arrayHistoriasActuales = obtenerArrayOrdenadoHistorias();
        
        for(int i=0 ; i<=arrayHistoriasActuales.size()-1 ; i++)
        {
            eliminarHistoria(arrayHistoriasActuales.get(i));
        }
        
        
        int numeroHistoriaRespaldo;
        
        while(iteradorNumeros.hasNext())
        {
            numeroHistoriaRespaldo = iteradorNumeros.next();
            
            importarHistoria(numeroHistoriaRespaldo, respaldoTotal.get(numeroHistoriaRespaldo));
        }
    }
    
    
}
