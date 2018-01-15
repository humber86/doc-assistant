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
import java.awt.GridLayout;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class HJFrequencyChart extends Box {
    
    private final DecimalFormat formateador;
    
    private int[] cantidades;
    private int total;
    private int totalCantidades;
    private String[] porcentajes;
    private Color[] colores;
    private final Color colorParaRestante = new Color(250,240,200);
    private final Color colorLineas = new Color(150,150,150);
    
    
    public HJFrequencyChart() {
        
        super(BoxLayout.Y_AXIS);
        
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        
        formateador = new DecimalFormat("##0.0", simbolos);
        formateador.setRoundingMode(RoundingMode.HALF_UP);
        
    }
    
    
    //................................
    
    
    public static Color[] obtenerColoresPorDefecto() {
        
        Color[] coloresPorDefecto = new Color[9];
        
        coloresPorDefecto[0] = new Color(250,50,50);
        coloresPorDefecto[1] = new Color(85,85,230);
        coloresPorDefecto[2] = new Color(215,190,15);
        coloresPorDefecto[3] = new Color(50,195,205);
        coloresPorDefecto[4] = new Color(190,55,190);
        coloresPorDefecto[5] = new Color(40,155,40);
        coloresPorDefecto[6] = new Color(240,105,5);
        coloresPorDefecto[7] = new Color(135,75,215);
        
        return coloresPorDefecto;
    }
    
    
    //.......................................
    
    public void mostrarDatos(int[] cantidades, int total, Color[] colores) {
        
        totalCantidades = 0;
        
        for(int i=0 ; i<=cantidades.length-1 ; i++)
        {
            totalCantidades += cantidades[i];
        }
        
        if(totalCantidades>total)
            throw new IllegalArgumentException("suma de 'cantidades' (int[]) debe ser menor o igual a 'total' (int).");
        
        if(cantidades.length>colores.length)
            throw new IllegalArgumentException("'cantidades' (int[]) debe ser de menor o igual longitud que 'colores' (Color[]).");
        
        
        this.cantidades = cantidades;
        this.total = total;
        this.colores = colores;
        
        
        porcentajes = new String[this.cantidades.length+1];
        for(int i=0 ; i<=this.cantidades.length-1 ; i++)
        {
            porcentajes[i] = formateador.format((this.cantidades[i]*100.0)/this.total)+"%";
        }
        if(totalCantidades<this.total)
            porcentajes[porcentajes.length-1] = formateador.format(((this.total-totalCantidades)*100.0)/this.total);
        
        
        mostrarCuadro();
    }
    
    
    //...................................
    
    private void mostrarCuadro() {
        
        removeAll();
        
        
        JPanel panelCabecera = obtenerPanelHorizontal("Grupo", "Frec. Absoluta", "Frec. Relativa");
        panelCabecera.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, colorLineas));
        
        add(panelCabecera);
        for(int i=0 ; i<=cantidades.length-1 ; i++)
        {
            add(obtenerPanelGrupo(colores[i], cantidades[i], porcentajes[i]));
        }
        
        if(totalCantidades<total)
            add(obtenerPanelGrupo(colorParaRestante, total-totalCantidades, porcentajes[porcentajes.length-1]));
        
        JPanel panelTotal = obtenerPanelHorizontal("TOTAL", ""+total, "100%");
        panelTotal.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, colorLineas));
        
        add(panelTotal);
        
        
        validate();
        repaint();
    }
    
    
    private JPanel obtenerPanelHorizontal(String textoColumna1, String textoColumna2, String textoColumna3) {
        
        JPanel panelGrupo = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelGrupo.add(new HJLabel(textoColumna1));
        panelGrupo.setOpaque(false);
        
        panelGrupo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, colorLineas));
        
        JPanel panelFrecuenciaAbsoluta = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelFrecuenciaAbsoluta.add(new HJLabel(textoColumna2));
        panelFrecuenciaAbsoluta.setOpaque(false);
        
        panelFrecuenciaAbsoluta.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, colorLineas));
        
        JPanel panelFrecuenciaRelativa = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panelFrecuenciaRelativa.add(new HJLabel(textoColumna3));
        panelFrecuenciaRelativa.setOpaque(false);
        
        panelFrecuenciaRelativa.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, colorLineas));
        
        
        JPanel panel = new JPanel(new GridLayout(1, 3, 0, 0));
        panel.add(panelGrupo);
        panel.add(panelFrecuenciaAbsoluta);
        panel.add(panelFrecuenciaRelativa);
        panel.setOpaque(false);
        
        
        return panel;
    }
    
    
    private JPanel obtenerPanelGrupo(Color color, int frecuenciaAbsoluta, String frecuenciaRelativa) {
        
        HJLabel lColor = new HJLabel();
        lColor.setPreferredSize(new Dimension(15, 15));
        lColor.setOpaque(true);
        lColor.setBackground(color);
        lColor.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        
        JPanel panelColor = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelColor.add(lColor);
        panelColor.setOpaque(false);
        
        panelColor.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, colorLineas));
        
        JPanel panelFrecuenciaAbsoluta = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelFrecuenciaAbsoluta.add(new HJLabel(""+frecuenciaAbsoluta));
        panelFrecuenciaAbsoluta.setOpaque(false);
        
        panelFrecuenciaAbsoluta.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, colorLineas));
        
        JPanel panelFrecuenciaRelativa = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelFrecuenciaRelativa.add(new HJLabel(frecuenciaRelativa));
        panelFrecuenciaRelativa.setOpaque(false);
        
        panelFrecuenciaRelativa.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, colorLineas));
        
        
        JPanel panel = new JPanel(new GridLayout(1, 3, 0, 0));
        panel.add(panelColor);
        panel.add(panelFrecuenciaAbsoluta);
        panel.add(panelFrecuenciaRelativa);
        panel.setOpaque(false);
        
        panel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, colorLineas));
        
        
        return panel;
    }
    
    
}
