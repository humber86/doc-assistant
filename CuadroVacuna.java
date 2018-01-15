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
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.border.TitledBorder;


public class CuadroVacuna extends HJPaintedPanel {
    
    private final ArrayList<PanelDosis> listaPaneles;
    
    
    public CuadroVacuna(String nombreVacuna, int cantidadDosis, int cantidadRefuerzos, boolean ultDosis, boolean ultRefuerzo) {
        
        super(new GridLayout(1, 1, 0, 0) , new Color(220,250,220), new Color(205,245,205), null, null, 100, true);
        
        setLayout(new GridLayout(1, 1, 0, 0));
        
        
        if(nombreVacuna==null)
            throw new IllegalArgumentException("nombreVacuna (String) no puede ser nulo.");
        
        
        //....................
        
        listaPaneles = new ArrayList<>(0);
        
        
        //....................
        
        Box boxPaneles = Box.createVerticalBox();
        boxPaneles.add(Box.createVerticalStrut(5));
        
        if(cantidadDosis==1)
        {
            PanelDosis panel = new PanelDosis(nombreVacuna, PanelDosis.DOSIS, PanelDosis.UNICA_DOSIS_O_REFUERZO);
            
            boxPaneles.add(panel);
            
            listaPaneles.add(panel);
            
        }else{
            
            for(int i=1 ; i<=cantidadDosis ; i++)
            {
                PanelDosis panel = new PanelDosis(nombreVacuna, PanelDosis.DOSIS, i);
                
                boxPaneles.add(panel);
                
                listaPaneles.add(panel);
            }
        }
        
        if(ultDosis)
        {
            PanelDosis panel = new PanelDosis(nombreVacuna, PanelDosis.ULTIMA_DOSIS, PanelDosis.ULTIMA_DOSIS_O_REFUERZO);

            boxPaneles.add(panel);

            listaPaneles.add(panel);
        }
        
        
        if(cantidadRefuerzos==1)
        {
            PanelDosis panel = new PanelDosis(nombreVacuna, PanelDosis.REFUERZO, PanelDosis.UNICA_DOSIS_O_REFUERZO);
            
            boxPaneles.add(panel);
            
            listaPaneles.add(panel);
            
        }else{
            
            for(int i=1 ; i<=cantidadRefuerzos ; i++)
            {
                PanelDosis panel = new PanelDosis(nombreVacuna, PanelDosis.REFUERZO, i);
                
                boxPaneles.add(panel);
                
                listaPaneles.add(panel);
            }
        }
        
        if(ultRefuerzo)
        {
            PanelDosis panel = new PanelDosis(nombreVacuna, PanelDosis.ULTIMO_REFUERZO, PanelDosis.ULTIMA_DOSIS_O_REFUERZO);

            boxPaneles.add(panel);

            listaPaneles.add(panel);
        }
            
        
        boxPaneles.add(Box.createVerticalStrut(5));
        
        
        add(boxPaneles);
        
        
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Colores.LINEAS_OSCURAS, 2), nombreVacuna, TitledBorder.LEFT, TitledBorder.CENTER, new Font("Arial", Font.BOLD, 16), Colores.TEXTO)));
        
    }
    
    
    public ArrayList<PanelDosis> obtenerListaPaneles() { return listaPaneles; }
    
    
    public void establecerFechaNacimiento(LocalDate fecha) {
        
        for(int i=0 ; i<=listaPaneles.size()-1 ; i++)
        {
            listaPaneles.get(i).establecerFechaNacimiento(fecha);
        }
    }
    
    
    public void limpiarTodo() {
        
        for(int i=0 ; i<=listaPaneles.size()-1 ; i++)
        {
            listaPaneles.get(i).limpiarTodo();
        }
    }
    
    
}
