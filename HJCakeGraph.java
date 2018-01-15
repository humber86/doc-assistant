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
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JPanel;


public class HJCakeGraph extends JPanel {
    
    private final int radioHorizontal;
    private final int radioVertical;
    private final int altura;
    
    private final DecimalFormat formateador;
    
    private double[] cantidades;
    private double total;
    private double totalCantidades;
    private String[] porcentajes;
    private Color[] coloresIluminados;
    private Color[] coloresConSombra;
    private final Color colorIluminadoParaRestante = new Color(250,240,200);
    private final Color colorConSombraParaRestante = new Color(245,225,155);
    
    
    public HJCakeGraph(int radioHorizontal, int radioVertical, int altura) {
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        setOpaque(false);
        
        
        this.radioHorizontal = radioHorizontal;
        this.radioVertical = radioVertical;
        this.altura = altura;
        
        
        Dimension tamano = new Dimension(this.radioHorizontal*2, this.radioVertical*2+this.altura);
        
        setSize(tamano);
        setPreferredSize(tamano);
        setMaximumSize(tamano);
        setMinimumSize(tamano);
        
        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        
        formateador = new DecimalFormat("##0.0", simbolos);
        formateador.setRoundingMode(RoundingMode.HALF_UP);
        
    }
    
    
    //...............................
    
    
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
        
        totalCantidades = 0.0;
        
        for(int i=0 ; i<=cantidades.length-1 ; i++)
        {
            totalCantidades += cantidades[i];
        }
        
        if(totalCantidades>total)
            throw new IllegalArgumentException("suma de 'cantidades' (double[]) debe ser menor o igual a 'total' (double).");
        
        if(coloresIluminados.length!=coloresConSombra.length)
            throw new IllegalArgumentException("'coloresIluminados' (Color[]) y 'coloresConSombra' (Color[]) deben ser de igual longitud.");
        
        if(cantidades.length>coloresIluminados.length)
            throw new IllegalArgumentException("'cantidades' (double[]) debe ser de menor o igual longitud que 'coloresIlumninados' (Color[]) y 'coloresConSombra' (Color[]).");
        
        
        this.cantidades = cantidades;
        this.total = total;
        this.coloresIluminados = coloresIluminados;
        this.coloresConSombra = coloresConSombra;
        
        
        porcentajes = new String[this.cantidades.length+1];
        for(int i=0 ; i<=this.cantidades.length-1 ; i++)
        {
            porcentajes[i] = formateador.format((this.cantidades[i]*100.0)/this.total);
        }
        if(totalCantidades<this.total)
            porcentajes[porcentajes.length-1] = formateador.format(((this.total-totalCantidades)*100.0)/this.total);
        
        
        repaint();
    }
    
    
    //...............................
    
    double anguloInicialRadianes;
    double desplazamientoX_anguloInicial;
    double anguloFinal;
    double anguloFinalRadianes;
    double desplazamientoX_anguloFinal;
    
    private Shape obtenerFormaSombra(double anguloInicial, double anguloBarrido) {
        
        Area area = new Area(new Ellipse2D.Double(0, altura, getWidth(), getHeight()-altura));
        
        anguloInicialRadianes = (anguloInicial*Math.PI)/180.0;
        
        if(anguloInicial<180.0)  desplazamientoX_anguloInicial = 0.0;
        else  desplazamientoX_anguloInicial = radioHorizontal+radioHorizontal*Math.cos(anguloInicialRadianes);
        
        anguloFinal = anguloInicial+anguloBarrido;
        
        anguloFinalRadianes = (anguloFinal*Math.PI)/180.0;
        
        desplazamientoX_anguloFinal = radioHorizontal+radioHorizontal*Math.cos(anguloFinalRadianes);
        
        area.intersect(new Area(new Rectangle2D.Double(desplazamientoX_anguloInicial, radioVertical+altura, desplazamientoX_anguloFinal, getHeight())));
        
        area.add(new Area(new Rectangle2D.Double(desplazamientoX_anguloInicial, radioVertical, desplazamientoX_anguloFinal, altura)));
        
        return area;
    }
    
    
    //...............................
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        
        Graphics2D g2D = (Graphics2D)g.create();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        
        double anguloInicial = 0.0;
        double anguloBarrido;
        
        for(int i=0 ; i<=cantidades.length-1 ; i++)
        {
            anguloBarrido = (cantidades[i]*360.0)/total;
            
            anguloFinal = anguloInicial+anguloBarrido;
            
            if(anguloFinal>180.0)
            {
                for(int j=i ; j<=cantidades.length-1 ; j++)
                {
                    g2D.setColor(coloresConSombra[j]);
                    
                    anguloBarrido = (cantidades[j]*360.0)/total;
                    
                    g2D.fill(obtenerFormaSombra(anguloInicial, anguloBarrido));
                    
                    anguloInicial = anguloFinal;
                }
                
                break;
            }
            
            anguloInicial += anguloBarrido;
        }
        
        if(totalCantidades<total)
        {
            g2D.setColor(colorConSombraParaRestante);
              
            anguloBarrido = ((total-totalCantidades)*360.0)/total;
            
            g2D.fill(obtenerFormaSombra(anguloInicial, anguloBarrido));
        }
        
        
        anguloInicial = 0.0;
        Rectangle2D.Double marcoElipse = new Rectangle2D.Double(0, 0, getWidth(), getHeight()-altura);
        
        for(int i=0 ; i<=cantidades.length-1 ; i++)
        {
            g2D.setColor(coloresIluminados[i]);
            
            anguloBarrido = (cantidades[i]*360.0)/total;
            
            g2D.fill(new Arc2D.Double(marcoElipse, anguloInicial, anguloBarrido, Arc2D.PIE));
            
            anguloInicial += anguloBarrido;
        }
        
        if(totalCantidades<total)
        {
            g2D.setColor(colorIluminadoParaRestante);
            
            anguloBarrido = ((total-totalCantidades)*360.0)/total;
            
            g2D.fill(new Arc2D.Double(marcoElipse, anguloInicial, anguloBarrido, Arc2D.PIE));
        }
        
        
        anguloInicial = 0.0;
        double anguloMedio;
        double anguloMedioRadianes;
        double desplazamientoX_anguloMedio;
        double desplazamientoY_anguloMedio;
        double xString;
        double yString;
        
        g2D.setColor(new Color(30,30,30));
        g2D.setFont(new Font("Arial", Font.BOLD, 16));
        
        for(int i=0 ; i<=cantidades.length-1 ; i++)
        {
            anguloBarrido = (cantidades[i]*360.0)/total;
            
            anguloMedio = anguloInicial+anguloBarrido/2.0;
            
            anguloMedioRadianes = (anguloMedio*Math.PI)/180.0;
            
            desplazamientoX_anguloMedio = radioHorizontal+(radioHorizontal*0.8)*Math.cos(anguloMedioRadianes);
            
            desplazamientoY_anguloMedio = radioVertical-(radioVertical*0.8)*Math.sin(anguloMedioRadianes);
            
            xString = desplazamientoX_anguloMedio - g2D.getFontMetrics(g2D.getFont()).stringWidth(porcentajes[i]+"%")/2.0;
            
            yString = desplazamientoY_anguloMedio + (g2D.getFont().getSize()*0.75)/2.0;
            
            g2D.drawString(porcentajes[i]+"%", (float)xString, (float)yString);
            
            anguloInicial += anguloBarrido;
        }
        
        if(totalCantidades<total)
        {
            anguloBarrido = ((total-totalCantidades)*360.0)/total;
            
            anguloMedio = anguloInicial+anguloBarrido/2.0;
            
            anguloMedioRadianes = (anguloMedio*Math.PI)/180.0;
            
            desplazamientoX_anguloMedio = radioHorizontal+(radioHorizontal*0.8)*Math.cos(anguloMedioRadianes);
            
            desplazamientoY_anguloMedio = radioVertical-(radioVertical*0.8)*Math.sin(anguloMedioRadianes);
            
            xString = desplazamientoX_anguloMedio - g2D.getFontMetrics(g2D.getFont()).stringWidth(porcentajes[porcentajes.length-1]+"%")/2.0;
            
            yString = desplazamientoY_anguloMedio + (g2D.getFont().getSize()*0.75)/2.0;
            
            g2D.drawString(porcentajes[porcentajes.length-1]+"%", (float)xString, (float)yString);
        }
        
        
        g2D.setColor(new Color(150,150,150));
        
        g2D.draw(new Ellipse2D.Double(0, 0, getWidth()-1, getHeight()-altura-1));
        
        g2D.drawLine(0, radioVertical, 0, radioVertical+altura);
        g2D.drawLine(getWidth()-1, radioVertical, getWidth()-1, radioVertical+altura);
        
        g2D.draw(new Arc2D.Double(new Rectangle2D.Double(0, altura-1, getWidth()-0.5, radioVertical*2.0), 180, 180, Arc2D.OPEN));
        
        
        g2D.dispose();
    }
    
    
}
