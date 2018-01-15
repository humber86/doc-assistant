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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JPanel;


public class HJBarGraph extends JPanel {
    
    private final double alturaDecena;
    private final double anchoBarra;
    private double[] alturasBarras;
    
    private final double distanciaEntreBarra;
    private final double cosenoDistancia;
    private final double senoDistancia;
    
    private final DecimalFormat formateador;
    
    private final Font formatoCantidades = new Font("Arial", Font.BOLD, 14);
    private final Font formatoPorcentajes = new Font("Arial", Font.BOLD, 16);
    
    private final int distanciaGraficaNumeroHorizontal = 5;
    private final int margenInferiorFrente;
    private final double margenInferiorFondo;
    private final int distanciaGraficaNumeroVertical = 4;
    private final int largoPalito = 6;
    private final int margenIzquierdoFrente;
    private final double margenIzquierdoFondo;
    
    private double anchoMarco;
    private double alturaMarco;
    
    private double[] cantidades;
    private double total;
    private String[] porcentajes;
    private Color[] coloresIluminados;
    private Color[] coloresConSombra;
    private final Color colorLineaGris = new Color(150,150,150);
    private final Color colorTexto = new Color(30,30,30);
    
    private boolean sonEnteros = false;
    
    
    public HJBarGraph(double pixelsPorPunto, double anchoDeBarras) {
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        setOpaque(false);
        
        
        alturaDecena = pixelsPorPunto*10.0;
        anchoBarra = anchoDeBarras;
        
        distanciaEntreBarra = anchoBarra;
        
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        
        formateador = new DecimalFormat("##0.0", simbolos);
        formateador.setRoundingMode(RoundingMode.HALF_UP);
        
        
        margenInferiorFrente = formatoCantidades.getSize()+distanciaGraficaNumeroHorizontal;
        margenIzquierdoFrente = getFontMetrics(formatoCantidades).stringWidth("100%")+distanciaGraficaNumeroVertical+largoPalito;
        
        cosenoDistancia = distanciaEntreBarra*Math.cos(Math.PI/4.0);
        senoDistancia = distanciaEntreBarra*Math.sin(Math.PI/4.0);
        
        margenInferiorFondo = margenInferiorFrente+senoDistancia;
        margenIzquierdoFondo = margenIzquierdoFrente+cosenoDistancia;
        
    }
    
    
    //................................
    
    
    public static Color[] obtenerColoresIluminadosPorDefecto() {
        
        Color[] coloresIluminadosPorDefecto = new Color[9];
        
        coloresIluminadosPorDefecto[0] = new Color(250,50,50);
        coloresIluminadosPorDefecto[1] = new Color(85,85,230);
        coloresIluminadosPorDefecto[2] = new Color(215,190,15);
        coloresIluminadosPorDefecto[3] = new Color(50,195,205);
        coloresIluminadosPorDefecto[4] = new Color(190,55,190);
        coloresIluminadosPorDefecto[5] = new Color(40,155,40);
        coloresIluminadosPorDefecto[6] = new Color(240,105,5);
        coloresIluminadosPorDefecto[7] = new Color(135,75,215);
        
        return coloresIluminadosPorDefecto;
    }
    
    
    public static Color[] obtenerColoresConSombraPorDefecto() {
        
        Color[] coloresConSombraPorDefecto = new Color[9];
        
        coloresConSombraPorDefecto[0] = new Color(210,5,5);
        coloresConSombraPorDefecto[1] = new Color(55,55,225);
        coloresConSombraPorDefecto[2] = new Color(185,160,15);
        coloresConSombraPorDefecto[3] = new Color(40,160,170);
        coloresConSombraPorDefecto[4] = new Color(155,45,155);
        coloresConSombraPorDefecto[5] = new Color(30,120,30);
        coloresConSombraPorDefecto[6] = new Color(190,85,5);
        coloresConSombraPorDefecto[7] = new Color(105,45,195);
        
        return coloresConSombraPorDefecto;
    }
    
    
    //...............................
    
    public void graficar(double[] cantidades, double total, Color[] coloresIluminados, Color[] coloresConSombra) {
        
        sonEnteros = false;
        
        if(coloresIluminados.length!=coloresConSombra.length)
            throw new IllegalArgumentException("'coloresIluminados' (Color[]) y 'coloresConSombra' (Color[]) deben ser de igual longitud.");
        
        if(cantidades.length>coloresIluminados.length)
            throw new IllegalArgumentException("'cantidades' (double[]) debe ser de menor o igual longitud que 'coloresIlumninados' (Color[]) y 'coloresConSombra' (Color[]).");
        
        
        this.cantidades = cantidades;
        this.total = total;
        this.coloresIluminados = coloresIluminados;
        this.coloresConSombra = coloresConSombra;
        
        
        alturasBarras = new double[this.cantidades.length];
        for(int i=0 ; i<=this.cantidades.length-1 ; i++)
        {
            alturasBarras[i] = (this.cantidades[i]*10.0*alturaDecena)/this.total;
        }
        
        
        porcentajes = new String[this.cantidades.length];
        for(int i=0 ; i<=this.cantidades.length-1 ; i++)
        {
            porcentajes[i] = formateador.format((this.cantidades[i]*100.0)/this.total);
        }
        
        
        anchoMarco = distanciaEntreBarra*(this.cantidades.length+1)+anchoBarra*(this.cantidades.length);
        alturaMarco = alturaDecena*11;
        
        
        int ancho = (int)Math.round(margenIzquierdoFondo)+(int)Math.round(anchoMarco)+(int)Math.round(getFontMetrics(formatoCantidades).stringWidth("(PTES.)")/2.0)+2;
        int altura = (int)Math.round(margenInferiorFondo)+(int)Math.round(alturaMarco)+1;
        
        Dimension tamano = new Dimension(ancho, altura);
        
        setSize(tamano);
        setPreferredSize(tamano);
        setMaximumSize(tamano);
        setMinimumSize(tamano);
        
        
        repaint();
    }
    
    
    public void graficar(int[] cantidadesEnteras, int totalEntero, Color[] coloresIluminadosParaEnteros, Color[] coloresConSombraParaEnteros) {
        
        double[] cantidadesDouble = new double[cantidadesEnteras.length];
        for(int i=0 ; i<=cantidadesDouble.length-1 ; i++)
        {
            cantidadesDouble[i] = cantidadesEnteras[i];
        }
        
        graficar(cantidadesDouble, totalEntero, coloresIluminadosParaEnteros, coloresConSombraParaEnteros);
        
        sonEnteros = true;
        
        repaint();
    }
    
    
    //...............................
    
    private Shape obtenerFormaSombraFondo() {
        
        Path2D.Double camino = new Path2D.Double();
        
        camino.moveTo(margenIzquierdoFrente, senoDistancia);
        
        camino.lineTo(margenIzquierdoFrente, getHeight()-margenInferiorFrente);
        
        camino.lineTo(margenIzquierdoFrente+anchoMarco, getHeight()-margenInferiorFrente);
        
        camino.lineTo(margenIzquierdoFondo+anchoMarco, alturaMarco);
        
        camino.lineTo(margenIzquierdoFondo, alturaMarco);
        
        camino.lineTo(margenIzquierdoFondo, 0);
        
        camino.closePath();
        
        return camino;
    }
    
    
    private Path2D obtenerCaminoLineasFondo(int indiceDecena) {
        
        Path2D.Double camino = new Path2D.Double();
        
        camino.moveTo(margenIzquierdoFrente-largoPalito, getHeight()-(margenInferiorFrente+indiceDecena*alturaDecena));
        
        camino.lineTo(margenIzquierdoFrente, getHeight()-(margenInferiorFrente+indiceDecena*alturaDecena));
        
        camino.lineTo(margenIzquierdoFondo, getHeight()-(margenInferiorFondo+indiceDecena*alturaDecena));
        
        camino.lineTo(margenIzquierdoFondo+anchoMarco, getHeight()-(margenInferiorFondo+indiceDecena*alturaDecena));
        
        return camino;
    }
    
    
    //.............
    
    double xPrimerPunto;
    double yPrimerPunto;
    
    private Shape obtenerFormaSombraSuperior(int indiceBarra) {
        
        xPrimerPunto = margenIzquierdoFrente+distanciaEntreBarra*(indiceBarra+1)+anchoBarra*indiceBarra;
        yPrimerPunto = getHeight()-margenInferiorFrente-alturasBarras[indiceBarra];
        
        Path2D.Double camino = new Path2D.Double();
        
        camino.moveTo(xPrimerPunto, yPrimerPunto);
        
        camino.lineTo(xPrimerPunto+cosenoDistancia, yPrimerPunto-senoDistancia);
        
        camino.lineTo(xPrimerPunto+cosenoDistancia+anchoBarra, yPrimerPunto-senoDistancia);
        
        camino.lineTo(xPrimerPunto+anchoBarra, yPrimerPunto);
        
        camino.closePath();
        
        return camino;
    }
    
    
    private Shape obtenerFormaSombraDerecha(int indiceBarra) {
        
        xPrimerPunto = margenIzquierdoFrente+distanciaEntreBarra*(indiceBarra+1)+anchoBarra*indiceBarra+anchoBarra;
        yPrimerPunto = getHeight()-margenInferiorFrente-alturasBarras[indiceBarra];
        
        Path2D.Double camino = new Path2D.Double();
        
        camino.moveTo(xPrimerPunto, yPrimerPunto);
        
        camino.lineTo(xPrimerPunto+cosenoDistancia, yPrimerPunto-senoDistancia);
        
        camino.lineTo(xPrimerPunto+cosenoDistancia, yPrimerPunto-senoDistancia+alturasBarras[indiceBarra]);
        
        camino.lineTo(xPrimerPunto+cosenoDistancia-cosenoDistancia, yPrimerPunto-senoDistancia+alturasBarras[indiceBarra]+senoDistancia);
        
        camino.closePath();
        
        return camino;
    }
    
    
    private Shape obtenerRecuadroBarra(int indiceBarra) {
        
        xPrimerPunto = margenIzquierdoFrente+distanciaEntreBarra*(indiceBarra+1)+anchoBarra*indiceBarra;
        yPrimerPunto = getHeight()-margenInferiorFrente-alturasBarras[indiceBarra];
        
        return new Rectangle2D.Double(xPrimerPunto, yPrimerPunto, anchoBarra, alturasBarras[indiceBarra]);
    }
    
    
    double xPuntoMedio;
    double mitadAnchoTexto;
    
    private float obtenerX_porcentaje(int indiceBarra) {
        
        xPuntoMedio = margenIzquierdoFrente+distanciaEntreBarra*(indiceBarra+1)+anchoBarra*indiceBarra+anchoBarra/2.0+cosenoDistancia/2.0;
        mitadAnchoTexto = getFontMetrics(formatoPorcentajes).stringWidth(porcentajes[indiceBarra]+"%")/2.0;
        
        return (float)(xPuntoMedio-mitadAnchoTexto);
    }
    
    private float obtenerY_porcentaje(int indiceBarra) {
        
        return (float)(getHeight()-margenInferiorFrente-alturasBarras[indiceBarra]-senoDistancia-3.0);
    }
    
    private float obtenerX_cantidad(int indiceBarra) {
        
        xPuntoMedio = margenIzquierdoFrente+distanciaEntreBarra*(indiceBarra+1)+anchoBarra*indiceBarra+anchoBarra/2.0;
        mitadAnchoTexto = getFontMetrics(formatoCantidades).stringWidth(""+cantidades[indiceBarra])/2.0;
        
        return (float)(xPuntoMedio-mitadAnchoTexto);
    }
    
    private float obtenerY_cantidad() {
        
        return (float)getHeight();
    }
    
    private float obtenerX_cantidadEntera(int indiceBarra) {
        
        xPuntoMedio = margenIzquierdoFrente+distanciaEntreBarra*(indiceBarra+1)+anchoBarra*indiceBarra+anchoBarra/2.0;
        mitadAnchoTexto = getFontMetrics(formatoCantidades).stringWidth(""+(int)cantidades[indiceBarra])/2.0;
        
        return (float)(xPuntoMedio-mitadAnchoTexto);
    }
    
    private float obtenerY_cantidadEntera() {
        
        return (float)getHeight();
    }
    
    
    //...............................
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        
        g2D.setColor(new Color(255,255,255));
        g2D.fill(new Rectangle2D.Double(margenIzquierdoFondo, 0, anchoMarco, alturaMarco));
        
        g2D.setColor(colorLineaGris);
        g2D.draw(new Rectangle2D.Double(margenIzquierdoFondo, 0, anchoMarco, alturaMarco));
        
        
        g2D.setColor(new Color(245,245,245));
        g2D.fill(obtenerFormaSombraFondo());
        
        g2D.setColor(colorLineaGris);
        g2D.draw(obtenerFormaSombraFondo());
        
        
        g2D.setColor(new Color(100,100,100));
        for(int i=0 ; i<=10 ; i++)
        {
            g2D.draw(obtenerCaminoLineasFondo(i));
        }
        
        
        for(int i=0 ; i<=cantidades.length-1 ; i++)
        {
            if(this.cantidades[i]<0.0)  continue;
            
            g2D.setColor(coloresConSombra[i]);
            g2D.fill(obtenerFormaSombraSuperior(i));
            g2D.fill(obtenerFormaSombraDerecha(i));
            g2D.setColor(colorLineaGris);
            g2D.draw(obtenerFormaSombraSuperior(i));
            g2D.draw(obtenerFormaSombraDerecha(i));
            
            g2D.setColor(coloresIluminados[i]);
            g2D.fill(obtenerRecuadroBarra(i));
            g2D.setColor(colorLineaGris);
            g2D.draw(obtenerRecuadroBarra(i));
            
            g2D.setFont(formatoPorcentajes);
            g2D.setColor(colorTexto);
            g2D.drawString(porcentajes[i]+"%", obtenerX_porcentaje(i), obtenerY_porcentaje(i));
            
            g2D.setFont(formatoCantidades);
            if(sonEnteros)  g2D.drawString(""+(int)cantidades[i], obtenerX_cantidadEntera(i),  obtenerY_cantidadEntera());
            else  g2D.drawString(""+cantidades[i], obtenerX_cantidad(i),  obtenerY_cantidad());
        }
        
        g2D.setFont(formatoCantidades);
        g2D.setColor(colorTexto);
        g2D.drawString("(PTES.)", getWidth()-getFontMetrics(formatoCantidades).stringWidth("(PTES.)"), getHeight()-3);
        
        
        int margenIzquierdoParaPorcentajes = margenIzquierdoFrente-largoPalito-distanciaGraficaNumeroVertical;
        int margenInferiorParaPorcentajes = getHeight()-margenInferiorFrente+(int)Math.round((formatoCantidades.getSize()*0.8)/2.0);
        
        g2D.drawString("0%", (margenIzquierdoParaPorcentajes-getFontMetrics(formatoCantidades).stringWidth("0%")), margenInferiorParaPorcentajes);
        g2D.drawString("20%", (margenIzquierdoParaPorcentajes-getFontMetrics(formatoCantidades).stringWidth("20%")), margenInferiorParaPorcentajes-(int)Math.round(alturaDecena*2));
        g2D.drawString("40%", (margenIzquierdoParaPorcentajes-getFontMetrics(formatoCantidades).stringWidth("40%")), margenInferiorParaPorcentajes-(int)Math.round(alturaDecena*4));
        g2D.drawString("60%", (margenIzquierdoParaPorcentajes-getFontMetrics(formatoCantidades).stringWidth("60%")), margenInferiorParaPorcentajes-(int)Math.round(alturaDecena*6));
        g2D.drawString("80%", (margenIzquierdoParaPorcentajes-getFontMetrics(formatoCantidades).stringWidth("80%")), margenInferiorParaPorcentajes-(int)Math.round(alturaDecena*8));
        g2D.drawString("100%", (margenIzquierdoParaPorcentajes-getFontMetrics(formatoCantidades).stringWidth("100%")), margenInferiorParaPorcentajes-(int)Math.round(alturaDecena*10));
        
        
        g2D.dispose();
    }
    
    
}
