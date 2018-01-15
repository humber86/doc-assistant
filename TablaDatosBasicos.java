/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.io.File;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class TablaDatosBasicos extends AbstractTableModel {
    
    private String[] listaHistorias;
    
    
    public TablaDatosBasicos() {
        
        listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
    }
    
    
    //.............................
    
    
    @Override
    public Class<?> getColumnClass(int indiceColumna) { return String.class; }
    
    
    @Override
    public int getRowCount() { return listaHistorias.length; }
    
    
    @Override
    public int getColumnCount() { return 5; }
    
    
    @Override
    public String getValueAt(int indiceFila, int indiceColumna) {
         
        MapaDatos datosBasicos = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+listaHistorias[indiceFila]);
        
        switch(indiceColumna)
        {
            case 0 : return (listaHistorias[indiceFila].split(".dda"))[0];
            
            case 1 : return datosBasicos.get(Personales.KEY_APELLIDOS)+", "+datosBasicos.get(Personales.KEY_NOMBRES);
            
            case 2 : return datosBasicos.get(Personales.KEY_GENERO);
            
            case 3 : return datosBasicos.get(Personales.KEY_CEDULA);
            
            case 4 : return Utilidades.obtenerEdad(datosBasicos.get(Personales.KEY_FECHA_NACIMIENTO));
            
            default: return "---";
        }
    }
    
    
    //.............................
    
    public void actualizar() {
        
        listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        fireTableStructureChanged();
    }
    
    
    //.............................
    
    public void consultarConCedulaPaciente(String cedula) {
        
        ArrayList<String> arrayCoincidencias = new ArrayList<>(0);
        
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+listaHistorias[i]);
            
            if(cedula.equals(mapaDatos.get(Personales.KEY_CEDULA)))
                arrayCoincidencias.add(listaHistorias[i]);
        }
        
        listaHistorias = obtenerCadenaString(arrayCoincidencias);
        
        fireTableStructureChanged();
    }
    
    
    public void consultarConCedulaRepresentante(String cedula) {
        
        ArrayList<String> arrayCoincidencias = new ArrayList<>(0);
        
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            MapaDatos mapaRepresentante = (MapaDatos)Utilidades.leerArchivo(Directorios.REPRESENTANTES+listaHistorias[i]);
            
            if(mapaRepresentante!=null && mapaRepresentante.get(Representantes.KEY_CEDULA).equals(cedula))
                arrayCoincidencias.add(listaHistorias[i]);
        }
        
        listaHistorias = obtenerCadenaString(arrayCoincidencias);
        
        fireTableStructureChanged();
    }
    
    
    public void consultarConApellido(String apellido) {
        
        ArrayList<String> arrayCoincidencias = new ArrayList<>(0);
        
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+listaHistorias[i]);
            
            if(mapaDatos.get(Personales.KEY_APELLIDOS).toUpperCase().contains(apellido.toUpperCase()))
                arrayCoincidencias.add(listaHistorias[i]);
        }
        
        listaHistorias = obtenerCadenaString(arrayCoincidencias);
        
        fireTableStructureChanged();
    }
    
    
    public void mostrarTodaLaLista() {
        
        listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        fireTableStructureChanged();
    }
    
    
    //...............................
    
    private String[] obtenerCadenaString(ArrayList<String> array) {
        
        String[] cadena = new String[array.size()];
        
        for(int i=0 ; i<=cadena.length-1 ; i++)
        {
            cadena[i] = array.get(i);
        }
        
        return cadena;
    }
    
    
}
