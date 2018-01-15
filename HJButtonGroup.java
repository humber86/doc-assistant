/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.util.ArrayList;


public class HJButtonGroup {
    
    private final ArrayList<HJButton> lista = new ArrayList<>(0);
    
    
    public HJButtonGroup() {
        
        
    }
    
    
    public void anadir(HJButton boton) {
        
        lista.add(boton);
        
        boton.addActionListener( e -> establecerSelecionado(boton) );
    }
    
    
    private void establecerSelecionado(HJButton boton) {
        
        for(int i=0 ; i<=lista.size()-1 ; i++)
        {
            if(lista.get(i).equals(boton))
                lista.get(i).establecerSeleccionado(true);
            else
                lista.get(i).establecerSeleccionado(false);
        }
    }
    
    
}
