/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;


public class PanelDatosDoctor extends HJDialog {
    
    public static final String TITULO = "Título";
    public static final String NOMBRE_COMPLETO = "Nombre Completo";
    public static final String ESPECIALIDAD = "Especialidad";
    
    private final HJComboBox<String> cbTitulo;
    private final HJTextField tNombreCompleto;
    private final HJTextField tEspecialidad;
    
    private boolean fueronDatosEstablecidos = false;
    
    
    public PanelDatosDoctor(boolean primeraVez) {
        
        super(new IconoDeImagen("Doctor.png", -1, 30), "Datos del(la) Doctor(a)", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> cancelar(primeraVez) );
        
        
        //.........................
        
        String[] titulos = {"Dr.", "Dra."};
        
        cbTitulo = new HJComboBox<>(titulos);
        
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTitulo.add(new HJLabel("Seleccione: "));
        panelTitulo.add(cbTitulo);
        panelTitulo.setOpaque(false);
        
        tNombreCompleto = new HJTextField(30);
        
        JPanel panelNombreCompleto = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelNombreCompleto.add(new HJLabel("Nombre Completo: "));
        panelNombreCompleto.add(tNombreCompleto);
        panelNombreCompleto.setOpaque(false);
        
        tEspecialidad = new HJTextField(30);
        
        JPanel panelEspecialidad = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelEspecialidad.add(new HJLabel("Especialidad: "));
        panelEspecialidad.add(tEspecialidad);
        panelEspecialidad.setOpaque(false);
        
        
        Box boxDatos = Box.createVerticalBox();
        boxDatos.add(panelTitulo);
        boxDatos.add(Box.createVerticalStrut(15));
        boxDatos.add(panelNombreCompleto);
        boxDatos.add(Box.createVerticalStrut(15));
        boxDatos.add(panelEspecialidad);
        
        JPanel panelDatos = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelDatos.add(boxDatos);
        panelDatos.setOpaque(false);
        
        
        //......................
        
        HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
        bAceptar.addActionListener( e -> aceptar() );
        
        HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
        bCancelar.addActionListener( e -> cancelar(primeraVez) );
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.add(bAceptar);
        panelBotones.add(bCancelar);
        panelBotones.setOpaque(false);
        
        panelBotones.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //.....................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        if(primeraVez)
        {
            HJLabel lEnunciado = new HJLabel("Debe completar estos datos antes de iniciar el programa.");
            lEnunciado.setForeground(Colores.AZUL_LLAMATIVO);
            
            JPanel panelEnunciado = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelEnunciado.add(lEnunciado);
            panelEnunciado.setOpaque(false);
            
            cajaGeneral.add(Box.createVerticalStrut(15));
            cajaGeneral.add(panelEnunciado);
        }
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelDatos);
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    //...............................
    
    
    private boolean estanCasillasListas() {
        
        if(tNombreCompleto.esTextoValido()==false)
        {
            HJDialog.mostrarMensaje("Faltan Datos", new String[] {"Aún no ha establecido su Nombre Completo."}, IconoDeImagen.ADVERTENCIA, null);
            
            return false;
        }
        
        if(tEspecialidad.esTextoValido()==false)
        {
            HJDialog.mostrarMensaje("Faltan Datos", new String[] {"Aún no ha establecido su Especialidad."}, IconoDeImagen.ADVERTENCIA, null);
            
            return false;
        }
        
        return true;
    }
    
    
    private void aceptar() {
        
        if(estanCasillasListas())
        {
            fueronDatosEstablecidos = true;
            
            setVisible(false);
            dispose();
        }
    }
    
    
    public static void mostrarMensajeDatosGuardados() {
        
        HJDialog.mostrarMensaje("Datos Guardados", new String[] {"Los datos fueron guardados con éxito."}, IconoDeImagen.INFORMACION, null);
    }
    
    
    private void cancelar(boolean primeraVez) {
        
        fueronDatosEstablecidos = false;
        
        if(primeraVez)
        {
            if(HJDialog.mostrarDialogoPregunta("Advertencia", new String[] {"No podrá iniciar el programa hasta que complete estos datos.", " ", "¿Está seguro(a) de que desea cancelar este procedimiento?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
                System.exit(0);
            
        }else{
            
            setVisible(false);
            dispose();
        }
    }
    
    
    //..................................
    
    public boolean fueronDatosEstablecidos() { return fueronDatosEstablecidos; }
    
    
    public void establecerTituloDr(String titulo) { cbTitulo.setSelectedItem(titulo); }
    
    public String obtenerTituloDr() { return ""+cbTitulo.getSelectedItem(); }
    
    
    public void establecerNombreCompleto(String nombreCompleto) { tNombreCompleto.setText(nombreCompleto); }
    
    public String obtenerNombreCompleto() { return tNombreCompleto.getText(); }
    
    
    public void establecerEspecialidad(String especialidad) { tEspecialidad.setText(especialidad); }
    
    public String obtenerEspecialidad() { return tEspecialidad.getText(); }
    
    
    //..................................
    
    public static void comprobarInformacionDoctor() {
        
        MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda");
        
        if(mapaDatos.size()<=1 || mapaDatos.containsKey(PanelDatosDoctor.TITULO)==false)
        {
            PanelDatosDoctor panelDatosDoctor = new PanelDatosDoctor(true);
            panelDatosDoctor.setVisible(true);
            
            if(panelDatosDoctor.fueronDatosEstablecidos())
            {
                mapaDatos.put(PanelDatosDoctor.TITULO, panelDatosDoctor.obtenerTituloDr());
                mapaDatos.put(PanelDatosDoctor.NOMBRE_COMPLETO, panelDatosDoctor.obtenerNombreCompleto());
                mapaDatos.put(PanelDatosDoctor.ESPECIALIDAD, panelDatosDoctor.obtenerEspecialidad());
                
                Utilidades.guardarEnArchivo(Directorios.CONFIGURACION+"DatosDoctor.dda", mapaDatos);
                
                PanelDatosDoctor.mostrarMensajeDatosGuardados();
            }
        }
    }
    
    
}
