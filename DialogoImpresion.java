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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Locale;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.PrintQuality;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class DialogoImpresion extends HJDialog implements Printable {
    
    private final String TAMANO_MEDIA_CARTA = "1/2 Carta";
    private final String TAMANO_CARTA = "Carta";
    
    private final String CALIDAD_NORMAL = "Normal";
    private final String CALIDAD_BORRADOR = "Borrador";
    
    private int margenSuperior;
    private int margenIzquierdo;
    
    private HJComboBox<String> cbTamanoPapel;
    private HJComboBox<String> cbCalidadImpresion;
    private JSpinner spinnerCopias;
    
    private boolean sonRecipes;
    
    private PrinterJob trabajoImpresion;
    
    private ArrayList<JComponent> arrayElementos;
    
    
    public DialogoImpresion(ArrayList<JComponent> arrayHojas) {
        
        super(new IconoDeImagen("Imprimir.png", -1, 30), "Ajustes de Impresión", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        if(arrayHojas==null || arrayHojas.isEmpty())
        {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("No hay Documentos", new String[] {"No se encontraron documentos para imprimir."}, IconoDeImagen.ERROR, null);
            
            return;
        }
        
        
        //...........................
        
        sonRecipes = arrayHojas.get(0) instanceof Recipe;
        
        trabajoImpresion = PrinterJob.getPrinterJob();
        
        arrayElementos = new ArrayList<>(0);
        arrayElementos.addAll(arrayHojas);
        
        
        //...........................
        
        PrintService[] impresoras = PrinterJob.lookupPrintServices();
        
        String[] nombresImpresoras = new String[impresoras.length];
        for(int i=0 ; i<=impresoras.length-1 ; i++)
        {
            nombresImpresoras[i] = impresoras[i].getName();
        }
        
        HJComboBox<String> cbNombresImpresoras;
        if(impresoras.length>0)
        {
            cbNombresImpresoras = new HJComboBox<>(nombresImpresoras);
            cbNombresImpresoras.setSelectedItem(trabajoImpresion.getPrintService().getName());
            
        }else{
            
            cbNombresImpresoras = new HJComboBox<>();
            cbNombresImpresoras.addItem("(No hay impresoras)");
        }
        cbNombresImpresoras.addItemListener( e -> {
            
            try{
                trabajoImpresion.setPrintService(impresoras[cbNombresImpresoras.getSelectedIndex()]);
            }catch(PrinterException pe) {
                Toolkit.getDefaultToolkit().beep();
                HJDialog.mostrarMensaje("Error", new String[] {"No se pudo establecer la impresora seleccionada."}, IconoDeImagen.ERROR, null);
            }
            
            establecerTiposCalidadImpresion(impresoras[cbNombresImpresoras.getSelectedIndex()]);
        });
        
        JPanel panelSeleccionImpresora = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelSeleccionImpresora.add(Box.createHorizontalStrut(30));
        panelSeleccionImpresora.add(new HJLabel("Impresora: "));
        panelSeleccionImpresora.add(cbNombresImpresoras);
        panelSeleccionImpresora.add(Box.createHorizontalStrut(30));
        panelSeleccionImpresora.setOpaque(false);
        
        
        //..........................
        
        cbTamanoPapel = new HJComboBox<>();
        
        if(sonRecipes)  cbTamanoPapel.addItem(TAMANO_MEDIA_CARTA);
        else  cbTamanoPapel.addItem(TAMANO_CARTA);
        
        cbTamanoPapel.addItemListener( e -> establecerMargenes((String)cbTamanoPapel.getSelectedItem()) );
        
        
        cbCalidadImpresion = new HJComboBox<>();
        
        establecerTiposCalidadImpresion(trabajoImpresion.getPrintService());
        
        
        spinnerCopias = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinnerCopias.setFont(new Font("Dialog", Font.BOLD, 16));
        
        JPanel panelCopias = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelCopias.add(new HJLabel("Copias: "));
        panelCopias.add(spinnerCopias);
        panelCopias.setOpaque(false);
        
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelInferior.add(Box.createHorizontalStrut(15));
        panelInferior.add(new HJLabel("Tamaño de Papel: "));
        panelInferior.add(cbTamanoPapel);
        panelInferior.add(Box.createHorizontalStrut(15));
        panelInferior.add(new HJLabel("Calidad de Impresión: "));
        panelInferior.add(cbCalidadImpresion);
        panelInferior.add(Box.createHorizontalStrut(15));
        panelInferior.add(new HJLabel("Copias: "));
        panelInferior.add(spinnerCopias);
        panelInferior.add(Box.createHorizontalStrut(15));
        panelInferior.setOpaque(false);
        
        panelInferior.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Colores.LINEAS), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        
        
        //....
        
        if(sonRecipes)  establecerMargenes(TAMANO_MEDIA_CARTA);
        else  establecerMargenes(TAMANO_CARTA);
        
        
        //..........................
        
        HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
        bAceptar.addActionListener( e -> {
            
            setVisible(false);
            dispose();
            
            imprimir();
        });
        
        HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
        bCancelar.addActionListener( e -> {
            
            setVisible(false);
            dispose();
        });
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        panelBotones.add(bAceptar);
        panelBotones.add(bCancelar);
        panelBotones.setOpaque(false);
        
        
        //..........................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelSeleccionImpresora);
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelInferior);
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private void establecerTiposCalidadImpresion(PrintService impresora) {
        
        cbCalidadImpresion.removeAllItems();
        
        cbCalidadImpresion.addItem(CALIDAD_NORMAL);
        
        Object valores = impresora.getSupportedAttributeValues(PrintQuality.class, null, null);
        
        if(valores!=null)  cbCalidadImpresion.addItem(CALIDAD_BORRADOR);
    }
    
    
    private void establecerMargenes(String tamanoPapel) {
         
        if(tamanoPapel.equals(TAMANO_MEDIA_CARTA))
        {
            margenSuperior = 25;
            margenIzquierdo = 11;
            
        }else{
            
            margenSuperior = 25;
            margenIzquierdo = 25;
        }
    }
    
    
    //...............................
    
    
    private void imprimir() {
        
        int anchoHoja;
        int alturaHoja;
        
        if(sonRecipes)
        {
            anchoHoja = 396;
            alturaHoja = 612;
        }else{
            anchoHoja = 612;
            alturaHoja = 792;
        }
        
        Paper papel = new Paper();
        papel.setSize(anchoHoja, alturaHoja);
        papel.setImageableArea(0, 0, anchoHoja, alturaHoja);
        
        PageFormat formatoDePagina = new PageFormat();
        formatoDePagina.setOrientation(PageFormat.PORTRAIT);
        formatoDePagina.setPaper(papel);
        
        
        Book libro = new Book();
        libro.append(this, formatoDePagina, arrayElementos.size());
        
        trabajoImpresion.setPageable(libro);
        
        
        PrintRequestAttributeSet conjuntoAtributos = new HashPrintRequestAttributeSet();
         
        if(((String)cbCalidadImpresion.getSelectedItem()).equals(CALIDAD_NORMAL))
            conjuntoAtributos.add(PrintQuality.NORMAL);
        else
            conjuntoAtributos.add(PrintQuality.DRAFT);
        
        conjuntoAtributos.add(new Copies(new Integer(""+spinnerCopias.getValue())));
        
        conjuntoAtributos.add(new JobName("Doc Assitant Printing", Locale.getDefault()));
        
        try{
            trabajoImpresion.print(conjuntoAtributos);
        }catch(PrinterException pe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error al imprimir."}, IconoDeImagen.ERROR, null);
        }
    }
    
    
    //..............................
    
    @Override
    public int print(Graphics g, PageFormat formatoPagina, int indicePagina) {
        
        if(indicePagina>arrayElementos.size()-1)  return NO_SUCH_PAGE;
        
        Graphics2D g2D = (Graphics2D)g;
        
        g2D.translate(formatoPagina.getImageableX()+margenIzquierdo, formatoPagina.getImageableY()+margenSuperior);
        
        if(sonRecipes)  ((Recipe)arrayElementos.get(indicePagina)).obtenerPanelParaImprimir().printAll(g);
        else  ((PaginaVacunas)arrayElementos.get(indicePagina)).obtenerPanelParaImprimir().printAll(g);
        
        return Printable.PAGE_EXISTS;
    }
    
    
}
