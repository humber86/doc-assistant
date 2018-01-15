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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PanelImpresionRecipes extends HJDialog {
    
    private final String RECIPE = "Récipe";
    private final String INDICACIONES = "Indicaciones";
    private final String EXAMENES_DE_LABORATORIO = "Exámenes de Laboratorio";
    private final String ESTUDIOS_RADIOLOGICOS = "Estudios Radiológicos";
    private final String INFORME = "Informe";
    private final String REFERENCIA = "Referencia";
    
    private final HJPaintedPanel panelDocumentos;
    private final JScrollPane scrollDocumentos;
    
    private final ArrayList<JComponent> arrayDocumentos;
    
    private final int nroHistoria;
    
    private final boolean desdeConsulta;
    
    
    public PanelImpresionRecipes(int numeroHistoria, boolean desdeConsulta) {
        
        super(new IconoDeImagen("VistaPrevia.png", -1, 30), "Vista Previa", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea salir?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
            {
                setVisible(false);
                dispose();
            }
        });
        
        
        //..........................
        
        nroHistoria = numeroHistoria;
        
        this.desdeConsulta = desdeConsulta;
        
        
        arrayDocumentos = new ArrayList<>(0);
        
        
        panelDocumentos = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 20, 8), new Color(245,245,245), new Color(235,235,235), null, null, 100, true);
        
        scrollDocumentos = new JScrollPane(panelDocumentos);
        scrollDocumentos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollDocumentos.setPreferredSize(new Dimension(900, 685));
        scrollDocumentos.setOpaque(false);
        scrollDocumentos.getViewport().setOpaque(false);
        
        
        //..........................
        
        String[] tiposDocumentoStrings = {RECIPE, INDICACIONES, EXAMENES_DE_LABORATORIO, ESTUDIOS_RADIOLOGICOS, INFORME, REFERENCIA};
        int[] tiposDocumentos = {Recipe.RECIPE_RP, Recipe.RECIPE_INDICACIONES, Recipe.RECIPE_EXAMENES_LABORATORIO, Recipe.RECIPE_ESTUDIOS_RADIOLOGICOS, Recipe.RECIPE_INFORME, Recipe.RECIPE_REFERENCIA};
        
        HJComboBox<String> cbTipoDocumento = new HJComboBox<>(tiposDocumentoStrings);
        
        JPanel panelTipoDocumento = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelTipoDocumento.add(cbTipoDocumento);
        panelTipoDocumento.setOpaque(false);
        
        HJButton bAnadir = new HJButton(new IconoDeImagen("Anadir.png", -1, 20), "Añadir", Colores.COLORES_BOTONES);
        bAnadir.addActionListener( e -> anadirDocumento(tiposDocumentos[cbTipoDocumento.getSelectedIndex()]) );
        
        JPanel panelBotonAnadir = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonAnadir.add(bAnadir);
        panelBotonAnadir.setOpaque(false);
        
        
        Box boxAnadirDocumento = Box.createVerticalBox();
        boxAnadirDocumento.add(panelTipoDocumento);
        boxAnadirDocumento.add(Box.createVerticalStrut(5));
        boxAnadirDocumento.add(panelBotonAnadir);
        boxAnadirDocumento.setMaximumSize(new Dimension(250, 100));
        
        
        //....
        
        HJButton bImprimir = new HJButton(new IconoDeImagen("Imprimir.png", -1, 40), "Imprimir", new Font("Arial", Font.BOLD, 18), Colores.COLORES_BOTONES, true, -1, -1, null);
        bImprimir.addActionListener( e -> {
            
            alistarTodosLosModulos();
            
            DialogoImpresion dialogoImpresion = new DialogoImpresion(arrayDocumentos);
            dialogoImpresion.setVisible(true);
        });
        
        JPanel panelBotonImprimir = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotonImprimir.add(bImprimir);
        panelBotonImprimir.setOpaque(false);
        
        
        //....
        
        HJButton bSalir = new HJButton(null, "Salir", Colores.COLORES_BOTONES);
        bSalir.addActionListener( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea salir?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
            {
                setVisible(false);
                dispose();
            }
        });
        
        JPanel panelBotonSalir = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotonSalir.add(bSalir);
        panelBotonSalir.setOpaque(false);
        
        
        //....
        
        Box boxIzquierdo = Box.createVerticalBox();
        boxIzquierdo.add(boxAnadirDocumento);
        boxIzquierdo.add(Box.createVerticalStrut(50));
        boxIzquierdo.add(panelBotonImprimir);
        boxIzquierdo.add(Box.createVerticalStrut(50));
        boxIzquierdo.add(panelBotonSalir);
        
        
        //............................
        
        
        JPanel panelGeneral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelGeneral.add(boxIzquierdo);
        panelGeneral.add(scrollDocumentos);
        panelGeneral.setOpaque(false);
        
        
        add(panelGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    //..................................
    
    
    public void colocarRecipes() {
        
        if(nroHistoria<1)  return;
        
        
        String nombreArchivo = Utilidades.obtenerNombreUltimoArchivo(Directorios.CONSULTAS+nroHistoria);
        
        
        HashMap<String,HashMap<String,String>> mapaMedicamentos = (HashMap<String,HashMap<String,String>>)Utilidades.leerArchivo(Directorios.MEDICAMENTOS+nroHistoria+"\\"+nombreArchivo);
        
        if(mapaMedicamentos!=null)
        {
            ArrayList<String> arrayMedicamentosSinPrescrip = new ArrayList<>(0);
            ArrayList<String> arrayMedicamentosConPrescrip = new ArrayList<>(0);
            
            ArrayList<String> arrayIndicaciones = new ArrayList<>(0);
            
            Iterator<String> iterador = Utilidades.obtenerIteradorStringOrdenado(mapaMedicamentos);
            
            while(iterador.hasNext())
            {
                String medicamento = iterador.next();
                
                HashMap<String,String> mapaDelMedicamento = mapaMedicamentos.get(medicamento);
                
                if(mapaDelMedicamento.get(PanelMedicamento.PRESCRIPCION_INDIVIDUAL).equals("No"))
                {
                    arrayMedicamentosSinPrescrip.add("- "+medicamento);
                
                }else{
                    
                    if(mapaDelMedicamento.get(PanelMedicamento.CAJAS).equals("1"))
                        arrayMedicamentosConPrescrip.add("- "+medicamento+" (1 caja)");
                    else
                        arrayMedicamentosConPrescrip.add("- "+medicamento+" ("+mapaDelMedicamento.get(PanelMedicamento.CAJAS)+" cajas)");
                }
                
                if(mapaDelMedicamento.get(PanelMedicamento.INDICACIONES)!=null)
                    arrayIndicaciones.add("- "+medicamento+": "+mapaDelMedicamento.get(PanelMedicamento.INDICACIONES));
            }
            
            
            while(arrayMedicamentosSinPrescrip.size()>0)
            {
                Recipe recipe = new Recipe(nroHistoria, Recipe.RECIPE_RP, desdeConsulta);
                
                anadirRecipe(recipe, false);
                
                arrayMedicamentosSinPrescrip = recipe.llenar(arrayMedicamentosSinPrescrip, 1);
            }
            
            while(arrayMedicamentosConPrescrip.size()>0)
            {
                Recipe recipe = new Recipe(nroHistoria, Recipe.RECIPE_RP, desdeConsulta);
                
                anadirRecipe(recipe, false);
                
                ArrayList<String> arrayAuxiliar = new ArrayList<>(0);
                
                arrayAuxiliar.add(arrayMedicamentosConPrescrip.get(0));
                
                recipe.llenar(arrayAuxiliar, 0);
                
                arrayMedicamentosConPrescrip.remove(0);
            }
            
            while(arrayIndicaciones.size()>0)
            {
                Recipe recipe = new Recipe(nroHistoria, Recipe.RECIPE_INDICACIONES, desdeConsulta);
                
                anadirRecipe(recipe, false);
                
                arrayIndicaciones = recipe.llenar(arrayIndicaciones, 1);
            }
        }
        
        
        HashMap<String,ArrayList<String>> mapaExamenesLaboratorio = (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.EXAMENES_DE_LABORATORIO+nroHistoria+"\\"+nombreArchivo);
        
        if(mapaExamenesLaboratorio!=null)
        {
            Iterator<String> iteradorCategorias = Utilidades.obtenerIteradorStringOrdenado(mapaExamenesLaboratorio);
            
            ArrayList<String> arrayExamenesCompleto = new ArrayList<>(0);
            
            while(iteradorCategorias.hasNext())
            {
                String categoria = iteradorCategorias.next();
                
                if(categoria.equals(PanelExamenesLaboratorio.DROGAS) || categoria.equals(PanelExamenesLaboratorio.OTROS))
                    continue;
                
                ArrayList<String> arrayExamenes = Utilidades.obtenerArrayStringOrdenado(mapaExamenesLaboratorio.get(categoria));
                
                String primeraLineaEnCategoria = "    "+categoria.toUpperCase()+"\n\n"+arrayExamenes.get(0);
                
                arrayExamenes.remove(0);
                
                arrayExamenes.add(0, primeraLineaEnCategoria);
                
                for(int i=0 ; i<=arrayExamenes.size()-1 ; i++)
                {
                    arrayExamenesCompleto.add(arrayExamenes.get(i));
                }
                
                arrayExamenesCompleto.add("\n");
            }
            
            if(mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.DROGAS)!=null)
            {
                arrayExamenesCompleto.add("    "+PanelExamenesLaboratorio.DROGAS.toUpperCase()+"\n\n"+mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.DROGAS).get(0));
                arrayExamenesCompleto.add("\n");
            }
            
            if(mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.OTROS)!=null)
            {
                arrayExamenesCompleto.add("    "+PanelExamenesLaboratorio.OTROS.toUpperCase()+"\n\n"+mapaExamenesLaboratorio.get(PanelExamenesLaboratorio.OTROS).get(0));
                arrayExamenesCompleto.add("\n");
            }
            
            
            while(arrayExamenesCompleto.size()>0)
            {
                while(arrayExamenesCompleto.get(0).equals("\n"))
                {
                    arrayExamenesCompleto.remove(0);
                    
                    if(arrayExamenesCompleto.isEmpty())  break;
                }
                
                if(arrayExamenesCompleto.isEmpty())  break;
                
                Recipe recipe = new Recipe(nroHistoria, Recipe.RECIPE_EXAMENES_LABORATORIO, desdeConsulta);
                
                anadirRecipe(recipe, false);
                
                arrayExamenesCompleto = recipe.llenar(arrayExamenesCompleto, 0);
            }
        }
        
        
        HashMap<String,String> mapaEstudiosRadiologicos = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.ESTUDIOS_RADIOLOGICOS+nroHistoria+"\\"+nombreArchivo);
        
        if(mapaEstudiosRadiologicos!=null)
        {
            Iterator<String> iteradorCategorias = Utilidades.obtenerIteradorStringOrdenado(mapaEstudiosRadiologicos);
            
            ArrayList<String> arrayEstudios = new ArrayList<>(0);
            
            while(iteradorCategorias.hasNext())
            {
                String categoria = iteradorCategorias.next();
                
                if(categoria.equals(PanelEstudiosRadiologicos.OTROS))
                    continue;
                
                arrayEstudios.add("   "+categoria.toUpperCase()+"\n\n"+mapaEstudiosRadiologicos.get(categoria));
                
                arrayEstudios.add("\n");
            }
            
            if(mapaEstudiosRadiologicos.get(PanelEstudiosRadiologicos.OTROS)!=null)
                arrayEstudios.add("   "+PanelEstudiosRadiologicos.OTROS.toUpperCase()+"\n\n"+mapaEstudiosRadiologicos.get(PanelEstudiosRadiologicos.OTROS));
            
            
            while(arrayEstudios.size()>0)
            {
                while(arrayEstudios.get(0).equals("\n"))
                {
                    arrayEstudios.remove(0);
                    
                    if(arrayEstudios.isEmpty())  break;
                }
                
                if(arrayEstudios.isEmpty())  break;
                
                Recipe recipe = new Recipe(nroHistoria, Recipe.RECIPE_ESTUDIOS_RADIOLOGICOS, desdeConsulta);
                
                anadirRecipe(recipe, false);
                
                arrayEstudios = recipe.llenar(arrayEstudios, 0);
            }
        }
    }
    
    
    public void colocarDocumento(int tipo, boolean editable) {
        
        Recipe recipe = new Recipe(nroHistoria, tipo, desdeConsulta);
        
        anadirRecipe(recipe, editable);
    }
    
    
    private void anadirDocumento(int tipo)  { colocarDocumento(tipo, true); }
    
    
    private void anadirRecipe(Recipe recipe, boolean editable) {
        
        arrayDocumentos.add(recipe);
        
        ModuloRecipe moduloRecipe = new ModuloRecipe(recipe);
        moduloRecipe.establecerEditable(editable);
        
        panelDocumentos.add(moduloRecipe);
        
        panelDocumentos.validate();
        scrollDocumentos.validate();
        scrollDocumentos.getHorizontalScrollBar().setValue(scrollDocumentos.getHorizontalScrollBar().getMaximum());
        scrollDocumentos.repaint();
        
        if(editable)  moduloRecipe.obtenerRecipe().colocarCursor();
    }
    
    
    private void eliminarDocumento(ModuloRecipe moduloRecipe) {
        
        arrayDocumentos.remove(moduloRecipe.obtenerRecipe());
        
        panelDocumentos.remove(moduloRecipe);
        
        panelDocumentos.validate();
        scrollDocumentos.validate();
        scrollDocumentos.repaint();
    }
    
    
    //....................................
    
    private void alistarTodosLosModulos() {
        
        Component[] modulosRecipes = panelDocumentos.getComponents();
        
        for(int i=0 ; i<=modulosRecipes.length-1 ; i++)
        {
            ((ModuloRecipe)modulosRecipes[i]).finalizarEdicion();
        }
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class ModuloRecipe extends JPanel {
        
        private final Recipe recipeEnModulo;
        
        private HJButton bEditar;
        private HJButton bFinalizarEdicion;
        
        
        public ModuloRecipe(Recipe recipe) {
            
            setLayout(new GridLayout(1, 1, 0, 0));
            
            
            //..........................
            
            recipeEnModulo = recipe;
            
            
            //..........................
            
            JPanel panelRecipe = new JPanel(new GridLayout(1, 1, 0, 0));
            panelRecipe.add(recipeEnModulo);
            panelRecipe.setOpaque(false);
            
            
            bEditar = new HJButton(new IconoDeImagen("Editar.png", -1, 20), null, Colores.COLORES_BOTONES);
            
            bFinalizarEdicion = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), null, Colores.COLORES_BOTONES);
            bFinalizarEdicion.setVisible(false);
            
            bEditar.addActionListener( e -> {
                
                recipeEnModulo.establecerEditable(true);
                
                bEditar.setVisible(false);
                bFinalizarEdicion.setVisible(true);
            });
            
            bFinalizarEdicion.addActionListener( e -> {
                
                recipeEnModulo.establecerEditable(false);
                
                bFinalizarEdicion.setVisible(false);
                bEditar.setVisible(true);
            });
            
            HJButton bEliminar = new HJButton(new IconoDeImagen("Eliminar.png", -1, 20), null, Colores.COLORES_BOTONES);
            bEliminar.addActionListener( e -> {
                
                if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea remover esta hoja del documento?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
                    eliminarDocumento(obtenerEsteModuloRecipe());
            });
            
            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelBotones.add(bEditar);
            panelBotones.add(bFinalizarEdicion);
            panelBotones.add(Box.createHorizontalStrut(20));
            panelBotones.add(bEliminar);
            panelBotones.setOpaque(false);
            
            
            Box boxGeneral = Box.createVerticalBox();
            boxGeneral.add(panelRecipe);
            boxGeneral.add(Box.createVerticalStrut(8));
            boxGeneral.add(panelBotones);
            
            
            add(boxGeneral);
            
        }
        
        
        private ModuloRecipe obtenerEsteModuloRecipe() { return this; }
        
        
        public void establecerEditable(boolean editable) {
            
            recipeEnModulo.establecerEditable(editable);
            
            if(editable)
            {
                bEditar.setVisible(false);
                bFinalizarEdicion.setVisible(true);
            }else{
                bFinalizarEdicion.setVisible(false);
                bEditar.setVisible(true);
            }
        }
        
        
        public void finalizarEdicion() {
            
            recipeEnModulo.establecerEditable(false);
            
            bFinalizarEdicion.setVisible(false);
            bEditar.setVisible(true);
        }
        
        
        public Recipe obtenerRecipe() { return recipeEnModulo; }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
