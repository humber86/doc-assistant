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
import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.JPanel;


public class PanelEditorVacuna extends HJDialog {
    
    private final PanelDosis panelDosis;
    
    private final HJComboBox<String> cbDia;
    private final HJComboBox<String> cbMes;
    private final HJComboBox<String> cbAno;
    
    
    public PanelEditorVacuna(PanelDosis panel) {
        
        super(null, panel.obtenerNombreVacuna(), null, true);
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //............................
        
        panelDosis = panel;
        
        
        //............................
        
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelSuperior.add(new HJLabel(panelDosis.obtenerTituloParaDialogo()));
        panelSuperior.setOpaque(false);
        
        
        //............................
        
        String[] dias = new String[32];
        dias[0] = "Día";
        for(int i=1 ; i<=dias.length-1 ; i++)
        {
            dias[i] = ""+i;
        }
        
        cbDia = new HJComboBox<>(dias);
        
        String[] meses = {"Mes", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        
        cbMes = new HJComboBox<>(meses);
        
        cbAno = new HJComboBox<>();
        cbAno.addItem("Año");
        Calendar calendarioActual = Calendar.getInstance();
        int anoActual = calendarioActual.get(Calendar.YEAR);
        for(int i=0, j=anoActual ; i<=120 ; i++, j--)
        {
            cbAno.addItem(""+j);
        }
        
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCentral.add(new HJLabel("Fecha: "));
        panelCentral.add(cbDia);
        panelCentral.add(Box.createHorizontalStrut(4));
        panelCentral.add(cbMes);
        panelCentral.add(Box.createHorizontalStrut(4));
        panelCentral.add(cbAno);
        panelCentral.setOpaque(false);
        
        
        if(panelDosis.estaPanelListo())
        {
            String[] fechaCadena = panelDosis.obtenerFechaVacuna().split("-");
            
            cbDia.setSelectedItem(fechaCadena[0]);
            cbMes.setSelectedItem(fechaCadena[1]);
            cbAno.setSelectedItem(fechaCadena[2]);
        }
        
        
        //.........................
        
        HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
        bAceptar.addActionListener(e -> establecerFecha() );
        
        HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
        bCancelar.addActionListener(e -> {
            
            setVisible(false);
            dispose();
        });
        
        JPanel panelAceptarCancelar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelAceptarCancelar.add(Box.createHorizontalStrut(30));
        panelAceptarCancelar.add(bAceptar);
        panelAceptarCancelar.add(Box.createHorizontalStrut(30));
        panelAceptarCancelar.add(bCancelar);
        panelAceptarCancelar.add(Box.createHorizontalStrut(30));
        panelAceptarCancelar.setOpaque(false);
        
        
        //............................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelSuperior);
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelCentral);
        cajaGeneral.add(panelAceptarCancelar);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private void establecerFecha() {
        
        if(cbDia.getSelectedIndex()!=0 && cbMes.getSelectedIndex()!=0 && cbAno.getSelectedIndex()!=0)
        {
            int dia = cbDia.getSelectedIndex();
            int mes = cbMes.getSelectedIndex();
            int ano = new Integer(""+cbAno.getSelectedItem());
            
            if(Utilidades.esFechaValida(dia, mes, ano))
            {
                if(Utilidades.esFechaAnterior(dia, mes, ano))
                {
                    LocalDate fechaNacimiento = panelDosis.obtenerFechaNacimiento();
                    
                    LocalDate fechaVacuna = LocalDate.of(ano, mes, dia);
                    
                    if(fechaVacuna.isEqual(fechaNacimiento) || fechaVacuna.isAfter(fechaNacimiento))
                    {
                        panelDosis.establecerFecha(dia+"-"+mes+"-"+ano);
                        
                        panelDosis.establecerEdad(Utilidades.obtenerTiempoTranscurrido(fechaNacimiento, fechaVacuna));
                        
                        setVisible(false);
                        dispose();
                        
                    }else{ HJDialog.mostrarMensaje("Fecha Inválida", new String[] {"La fecha debe ser igual o posterior a la de nacimiento.", "Por favor, corríjala."}, IconoDeImagen.ADVERTENCIA, null); }
                    
                }else{ HJDialog.mostrarMensaje("Fecha Inválida", new String[] {"La fecha debe ser anterior o igual a la actual.", "Por favor, corríjala."}, IconoDeImagen.ADVERTENCIA, null); }
                
            }else{ HJDialog.mostrarMensaje("Fecha Inválida", new String[] {"La fecha  no es válida.", "Por favor, corríjala."}, IconoDeImagen.ADVERTENCIA, null); }
            
        }else{ HJDialog.mostrarMensaje("Fecha No Establecida", new String[] {"No ha terminado de establecer la fecha."}, IconoDeImagen.ADVERTENCIA, null); }
    }
    
    
}

