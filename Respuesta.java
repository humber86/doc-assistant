/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */


public class Respuesta {
    
    private int valorPrimitivo;
    
    private Object valorObjeto = null;
    
    
    public Respuesta() {
        
    }
    
    
    public void establecerValorPrimitivo(int valor) {
        
        valorPrimitivo = valor;
    }
    
    public int obtenerValorPrimitivo() {
        
        return valorPrimitivo;
    }
    
    
    public void establecerValorObjeto(Object valor) {
        
        valorObjeto = valor;
    }
    
    public Object obtenerValorObjeto() {
        
        return valorObjeto;
    }
    
    
}
