/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelFechaYDosCampos extends JPanel {
    
    private final HJComboBox<String> cbMes;
    private final HJComboBox<String> cbAno;
    private final HJLabel lMesAno;
    private final JPanel panelMesAno;
    private final HJTextArea taCampo_1;
    private final HJTextArea taCampo_2;
    private final String TEXTO_INHABILITADO_CAMPO_2;
    private final HJButton bElimiar;
    
    
    public PanelFechaYDosCampos(String textoInhabilitadoCampo2) {
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
        
        //.........................
        
        String[] meses = {"Mes", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        cbMes = new HJComboBox<>(meses);
        
        cbAno = new HJComboBox<>();
        cbAno.addItem("Año");
        Calendar calendarioActual = Calendar.getInstance();
        int anoActual = calendarioActual.get(Calendar.YEAR);
        for(int i=0, j=anoActual ; i<=120 ; i++, j--)
        {
            cbAno.addItem(""+j);
        }
        
        lMesAno = new HJLabel();
        lMesAno.setFont(new Font("Arial", Font.BOLD, 18));
        
        panelMesAno = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelMesAno.add(Box.createHorizontalStrut(20));
        panelMesAno.add(lMesAno);
        panelMesAno.add(Box.createHorizontalStrut(20));
        panelMesAno.setOpaque(false);
        panelMesAno.setVisible(false);
        
        JLabel lDosPuntos1 = new JLabel(" : ");
        lDosPuntos1.setFont(new Font("Arial", Font.BOLD, 18));
        
        taCampo_1 = new HJTextArea(1, 25);
        taCampo_1.setLineWrap(false);
        taCampo_1.setWrapStyleWord(false);
        taCampo_1.addCaretListener( e -> controlarCampo2() );
        HJScrollPane scrollCampo_1 = new HJScrollPane(taCampo_1);
        scrollCampo_1.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollCampo_1.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollCampo_1.removerEscuchadorRuedaRaton();
        
        JLabel lDosPuntos2 = new JLabel(" : ");
        lDosPuntos2.setFont(new Font("Arial", Font.BOLD, 18));
        
        TEXTO_INHABILITADO_CAMPO_2 = textoInhabilitadoCampo2;
        
        taCampo_2 = new HJTextArea(3, 25);
        taCampo_2.setText(TEXTO_INHABILITADO_CAMPO_2);
        taCampo_2.setEnabled(false);
        HJScrollPane scrollCampo_2 = new HJScrollPane(taCampo_2);
        scrollCampo_2.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCampo_2.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCampo_2.removerEscuchadorRuedaRaton();
        
        bElimiar = new HJButton(new IconoDeImagen("Eliminar.png", -1, 20), null, null, Colores.COLORES_BOTONES, false, -1, -1, null);
        
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.add(panelMesAno);
        panel.add(cbMes);
        panel.add(Box.createHorizontalStrut(4));
        panel.add(cbAno);
        panel.add(lDosPuntos1);
        panel.add(scrollCampo_1);
        panel.add(lDosPuntos2);
        panel.add(scrollCampo_2);
        panel.add(Box.createHorizontalStrut(30));
        panel.add(bElimiar);
        panel.setOpaque(false);
        
        
        //.....................
        
        
        Box boxVertical = Box.createVerticalBox();
        boxVertical.add(panel);
        boxVertical.add(Box.createVerticalStrut(15));
        
        
        add(boxVertical);
        
        
        setMaximumSize(new Dimension(1000, 78));
        
        setOpaque(false);
        
    }
    
    
    //..............................
    
    
    public void anadirEscuchadorBotonEliminar(ActionListener escuchador) { bElimiar.addActionListener(escuchador); }
    
    
    private void controlarCampo2() {
        
        if(taCampo_1.esTextoValido())
        {
            if(taCampo_2.isEnabled()==false)
            {
                taCampo_2.setText(null);
                taCampo_2.setEnabled(true);
            }
            
        }else{
            
            taCampo_2.setText(TEXTO_INHABILITADO_CAMPO_2);
            taCampo_2.setEnabled(false);
        }
    }
    
    
    public void establecerEditable(boolean editable) {
        
        cbMes.setVisible(editable);
        cbAno.setVisible(editable);
        
        panelMesAno.setVisible(!editable);
        
        taCampo_1.setEditable(editable);
        taCampo_1.setOpaque(editable);
        
        taCampo_2.setEditable(editable);
        taCampo_2.setOpaque(editable);
        if(editable==false)  taCampo_2.setEnabled(true);
        
        bElimiar.setEnabled(editable);
    }
    
    
    //.............................
    
    public boolean estaFechaLista() { return cbMes.getSelectedIndex()!=0 && cbAno.getSelectedIndex()!=0; }
    
    
    public boolean estaCampo1Listo() { return taCampo_1.esTextoValido(); }
    
    
    public boolean estaListo() { return estaFechaLista() && estaCampo1Listo(); }
    
    
    //.............................
    
    public String obtenerMesAno() { return cbMes.getSelectedItem()+"-"+cbAno.getSelectedItem(); }
    
    public void establecerMesAno(String[] mesAno) {
        
        cbMes.setSelectedItem(mesAno[0]);
        cbAno.setSelectedItem(mesAno[1]);
        
        lMesAno.setText(mesAno[0]+"-"+mesAno[1]);
    }
    
    
    public String obtenerCampo1() {
        
        if(taCampo_1.esTextoValido())  return taCampo_1.getText();
        else  return null;
    }
    
    public void establecerCampo1(String texto) { taCampo_1.setText(texto); }
    
    
    public String obtenerCampo2() {
        
        if(taCampo_2.esTextoValido())  return taCampo_2.getText();
        else  return null;
    }
    
    public void establecerCampo2(String texto) { taCampo_2.setText(texto); }
    
    
}
