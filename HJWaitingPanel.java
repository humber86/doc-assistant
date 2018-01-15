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
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HJWaitingPanel extends HJHeadlessDialog {
    
    
    public HJWaitingPanel() {
        
        super(null, true);
        
        
        //........................
        
        JPanel panelEtiqueta = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelEtiqueta.add(new HJLabel("Por favor, espere."));
        panelEtiqueta.setOpaque(false);
        
        JPanel panelIcono = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelIcono.add(new JLabel(new ImageIcon(getClass().getResource("images/Procesando.gif"))));
        panelIcono.setOpaque(false);
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelEtiqueta);
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelIcono);
        cajaGeneral.add(Box.createVerticalStrut(15));
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
}
