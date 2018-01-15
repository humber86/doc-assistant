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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PanelVacunas extends HJDialog {
    
    private final CuadroVacuna cuadroBCG = new CuadroVacuna("BCG", 2, 0, false, false);
    private final CuadroVacuna cuadroPolio = new CuadroVacuna("Polio", 3, 2, false, false);
    private final CuadroVacuna cuadroDTP = new CuadroVacuna("DTP", 3, 2, false, false);
    private final CuadroVacuna cuadroHepatitisA = new CuadroVacuna("Hepatitis A", 2, 0, false, false);
    private final CuadroVacuna cuadroHepatitisB = new CuadroVacuna("Hepatitis B", 3, 0, false, false);
    private final CuadroVacuna cuadroHInfluenzaTipoB = new CuadroVacuna("H. Influenza (tipo B)", 2, 3, false, true);
    private final CuadroVacuna cuadroInfluenza = new CuadroVacuna("Influenza", 3, 0, true, false);
    
    private final CuadroVacuna cuadroAntiamarilica = new CuadroVacuna("Antiamarílica", 1, 3, false, true);
    private final CuadroVacuna cuadroTrivalenteViralSPR = new CuadroVacuna("Trivalente Viral (SPR)", 1, 2, false, false);
    private final CuadroVacuna cuadroVaricela = new CuadroVacuna("Varicela", 2, 0, false, false);
    private final CuadroVacuna cuadroNeumococo = new CuadroVacuna("Neumococo", 3, 1, false, false);
    private final CuadroVacuna cuadroMeningococo = new CuadroVacuna("Meningococo", 3, 1, false, false);
    private final CuadroVacuna cuadroRotavirus = new CuadroVacuna("Rotavirus", 3, 0, false, false);
    private final CuadroVacuna cuadroVPH = new CuadroVacuna("VPH", 1, 0, false, false);
    
    private final ArrayList<CuadroVacuna> listaCuadrosVacunas;
    
    private final int nroHistoria;
    
    
    public PanelVacunas(int numeroHistoria) {
        
        super(new IconoDeImagen("Vacuna.png", -1, 30), "Vacunas", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Advertencia", new String[] {"Esta acción es igual a presionar el botón 'Cancelar'.", "¿Está seguro(a) de que desea continuar?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
            {
                setVisible(false);
                dispose();
            }
        });
        
        
        //..........................
        
        nroHistoria = numeroHistoria;
        
        listaCuadrosVacunas = new ArrayList<>(0);
        
        
        //..........................
        
        int distanciaBox_1 = 30;
        
        Box boxVertical_1 = Box.createVerticalBox();
        boxVertical_1.add(cuadroBCG);
        listaCuadrosVacunas.add(cuadroBCG);
        boxVertical_1.add(Box.createVerticalStrut(distanciaBox_1));
        boxVertical_1.add(cuadroPolio);
        listaCuadrosVacunas.add(cuadroPolio);
        boxVertical_1.add(Box.createVerticalStrut(distanciaBox_1));
        boxVertical_1.add(cuadroDTP);
        listaCuadrosVacunas.add(cuadroDTP);
        boxVertical_1.add(Box.createVerticalStrut(distanciaBox_1));
        boxVertical_1.add(cuadroHepatitisA);
        listaCuadrosVacunas.add(cuadroHepatitisA);
        boxVertical_1.add(Box.createVerticalStrut(distanciaBox_1));
        boxVertical_1.add(cuadroHepatitisB);
        listaCuadrosVacunas.add(cuadroHepatitisB);
        boxVertical_1.add(Box.createVerticalStrut(distanciaBox_1));
        boxVertical_1.add(cuadroHInfluenzaTipoB);
        listaCuadrosVacunas.add(cuadroHInfluenzaTipoB);
        boxVertical_1.add(Box.createVerticalStrut(distanciaBox_1));
        boxVertical_1.add(cuadroInfluenza);
        listaCuadrosVacunas.add(cuadroInfluenza);
        
        
        int distanciaBox_2 = 56;
        
        Box boxVertical_2 = Box.createVerticalBox();
        boxVertical_2.add(cuadroAntiamarilica);
        listaCuadrosVacunas.add(cuadroAntiamarilica);
        boxVertical_2.add(Box.createVerticalStrut(distanciaBox_2));
        boxVertical_2.add(cuadroTrivalenteViralSPR);
        listaCuadrosVacunas.add(cuadroTrivalenteViralSPR);
        boxVertical_2.add(Box.createVerticalStrut(distanciaBox_2));
        boxVertical_2.add(cuadroVaricela);
        listaCuadrosVacunas.add(cuadroVaricela);
        boxVertical_2.add(Box.createVerticalStrut(distanciaBox_2));
        boxVertical_2.add(cuadroNeumococo);
        listaCuadrosVacunas.add(cuadroNeumococo);
        boxVertical_2.add(Box.createVerticalStrut(distanciaBox_2));
        boxVertical_2.add(cuadroMeningococo);
        listaCuadrosVacunas.add(cuadroMeningococo);
        boxVertical_2.add(Box.createVerticalStrut(distanciaBox_2));
        boxVertical_2.add(cuadroRotavirus);
        listaCuadrosVacunas.add(cuadroRotavirus);
        boxVertical_2.add(Box.createVerticalStrut(distanciaBox_2));
        boxVertical_2.add(cuadroVPH);
        listaCuadrosVacunas.add(cuadroVPH);
        
        
        //.......
        
        JPanel panelHorizontal = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 30, 30), new Color(240,250,240), new Color(235,250,235), null, null, 100, true);
        panelHorizontal.add(boxVertical_1);
        panelHorizontal.add(boxVertical_2);
        panelHorizontal.setOpaque(false);
        
        JScrollPane scrollPanelHorizontal = new JScrollPane(panelHorizontal);
        scrollPanelHorizontal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelHorizontal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanelHorizontal.getViewport().setPreferredSize(new Dimension(1100, 580));
        scrollPanelHorizontal.getVerticalScrollBar().setUnitIncrement(10);
        scrollPanelHorizontal.setOpaque(false);
        scrollPanelHorizontal.getViewport().setOpaque(false);
        
        
        //.......................
        
        establecerFechaNacimiento();
        
        cargarDatos();
        
        
        //.......................
        
        HJButton bGuardar = new HJButton(new IconoDeImagen("Guardar.png", -1, 20), "Guardar", Colores.COLORES_BOTONES);
        bGuardar.addActionListener( e -> {
            
            guardar();
            
            setVisible(false);
            dispose();
        });
        
        HJButton bImprimir = new HJButton(new IconoDeImagen("Imprimir.png", -1, 20), "Imprimir", Colores.COLORES_BOTONES);
        bImprimir.addActionListener( e -> {
            
            guardar();
            
            PanelImpresionVacunas panelImpresionVacunas = new PanelImpresionVacunas(nroHistoria);
            panelImpresionVacunas.setVisible(true);
        });
        
        HJButton bLimpiarTodo = new HJButton(new IconoDeImagen("Limpiar.png", -1, 20), "Limpiar", Colores.COLORES_BOTONES);
        bLimpiarTodo.addActionListener( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea remover toda la información de la ventana 'Vacunas'?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null) == 0)
                limpiarTodo();
        });
        
        HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
        bCancelar.addActionListener( e -> {
            
            setVisible(false);
            dispose();
        });
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.add(bGuardar);
        panelBotones.add(bImprimir);
        panelBotones.add(bLimpiarTodo);
        panelBotones.add(bCancelar);
        panelBotones.setOpaque(false);
        
        
        //...........................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(scrollPanelHorizontal);
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private void establecerFechaNacimiento() {
        
        MapaDatos mapaPersonales = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+nroHistoria+".dda");
        
        String fechaNacimientoCadena[] = mapaPersonales.get(Personales.KEY_FECHA_NACIMIENTO).split("-");
        
        LocalDate fechaNacimiento = LocalDate.of(Integer.parseInt(fechaNacimientoCadena[2]), Integer.parseInt(fechaNacimientoCadena[1]), Integer.parseInt(fechaNacimientoCadena[0]));
        
        for(int i=0 ; i<=listaCuadrosVacunas.size()-1 ; i++)
        {
            listaCuadrosVacunas.get(i).establecerFechaNacimiento(fechaNacimiento);
        }
    }
    
    
    private void cargarDatos() {
        
        HashMap<String,ArrayList<String>> mapaVacunas = (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.VACUNAS+nroHistoria+".dda");
        
        if(mapaVacunas!=null)
        {
            CuadroVacuna cuadroVacuna;
            
            ArrayList<PanelDosis> arrayPaneles;
            
            PanelDosis panelDosis;
            
            String nombrePanel;
            
            ArrayList<String> arrayVacuna;
            
            for(int i=0 ; i<=listaCuadrosVacunas.size()-1 ; i++)
            {
                cuadroVacuna = listaCuadrosVacunas.get(i);
                
                arrayPaneles = cuadroVacuna.obtenerListaPaneles();
                
                for(int j=0 ; j<=arrayPaneles.size()-1 ; j++)
                {
                    panelDosis = arrayPaneles.get(j);
                    
                    nombrePanel = panelDosis.obtenerNombrePanel();
                    
                    if(mapaVacunas.containsKey(nombrePanel))
                    {
                        arrayVacuna = mapaVacunas.get(nombrePanel);
                        
                        panelDosis.establecerFecha(arrayVacuna.get(0));
                        panelDosis.establecerEdad(arrayVacuna.get(1));
                        
                        mapaVacunas.remove(nombrePanel);
                    }
                }
            }
        }
    }
    
    
    private void guardar() {
        
        HashMap<String,ArrayList<String>> mapaVacunas = new HashMap<>(0);
        
        for(int i=0 ; i<=listaCuadrosVacunas.size()-1 ; i++)
        {
            ArrayList<PanelDosis> listaPaneles = listaCuadrosVacunas.get(i).obtenerListaPaneles();
            
            for(int j=0 ; j<=listaPaneles.size()-1 ; j++)
            {
                PanelDosis panelDosis = listaPaneles.get(j);
                
                if(panelDosis.estaPanelListo())
                {
                    ArrayList<String> array = new ArrayList<>(0);
                    array.add(panelDosis.obtenerFechaVacuna());
                    array.add(panelDosis.obtenerEdadVacuna());
                    
                    mapaVacunas.put(panelDosis.obtenerNombrePanel(), array);
                }
            }
        }
        
        if(mapaVacunas.size()>0)
            Utilidades.guardarEnArchivo(Directorios.VACUNAS+nroHistoria+".dda", mapaVacunas);
        else
            Utilidades.eliminarArchivo(Directorios.VACUNAS+nroHistoria+".dda");
    }
    
    
    private void limpiarTodo() {
        
        for(int i=0 ; i<=listaCuadrosVacunas.size()-1 ; i++)
        {
            listaCuadrosVacunas.get(i).limpiarTodo();
        }
    }
    
    
}
