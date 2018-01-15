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
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;


public class PanelBuscarPaciente extends HJDialog {
    
    private final HJRadioButton rbPorCedula;
    private final HJRadioButton rbPorApellido;
    
    private final HJRadioButton rbDelPaciente;
    private final HJRadioButton rbDelRepresentante;
    
    private final HJComboBox<String> cbTipoCedula;
    private final HJTextField tValor;
    
    private final TablaDatosBasicos tablaDatosBasicos;
    private final HJTable tabla;
    
    private boolean pacienteEstaSeleccionado = false;
    
    
    public PanelBuscarPaciente(boolean paraEstablecerPaciente) {
        
        super(new IconoDeImagen("BuscarPaciente.png", -1, 30), "Buscar Paciente", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //.........................
        
        HJLabel lBuscarPor = new HJLabel("Buscar por:");
        lBuscarPor.setFont(new Font("Arial", Font.BOLD, 16));
        
        rbPorCedula = new HJRadioButton("Cédula");
        
        rbPorApellido = new HJRadioButton("Apellido");
        
        ButtonGroup grupoBotonesParametrosGenerales = new ButtonGroup();
        grupoBotonesParametrosGenerales.add(rbPorCedula);
        grupoBotonesParametrosGenerales.add(rbPorApellido);
        
        rbPorCedula.setSelected(true);
        
        Box boxBuscarPor = Box.createVerticalBox();
        boxBuscarPor.add(rbPorCedula);
        boxBuscarPor.add(rbPorApellido);
        
        
        HJLabel lBuscarDel = new HJLabel("del:");
        lBuscarDel.setFont(new Font("Arial", Font.BOLD, 16));
        
        rbDelPaciente = new HJRadioButton("Paciente");
        
        rbDelRepresentante = new HJRadioButton("Representante");
        
        ButtonGroup grupoBotonesParametrosEspecificos = new ButtonGroup();
        grupoBotonesParametrosEspecificos.add(rbDelPaciente);
        grupoBotonesParametrosEspecificos.add(rbDelRepresentante);
        
        rbDelPaciente.setSelected(true);
        
        Box boxBuscarDel = Box.createVerticalBox();
        boxBuscarDel.add(rbDelPaciente);
        boxBuscarDel.add(rbDelRepresentante);
        
        
        JPanel panelParametros = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelParametros.add(lBuscarPor);
        panelParametros.add(boxBuscarPor);
        panelParametros.add(lBuscarDel);
        panelParametros.add(boxBuscarDel);
        panelParametros.setOpaque(false);
        
        
        //...........................
        
        cbTipoCedula = new HJComboBox<>(new String[] {"V-", "E-"});
        
        tValor = new HJTextField(8);
        
        HJButton bBuscar = new HJButton(new IconoDeImagen("Buscar.png", -1, 20), "Buscar", Colores.COLORES_BOTONES);
        bBuscar.addActionListener( e -> buscarPaciente() );
        
        HJButton bVerTodos = new HJButton(new IconoDeImagen("Lista.png", -1, 20), "Ver Todos", Colores.COLORES_BOTONES);
        bVerTodos.addActionListener( e -> mostrarTodos() );
        
        JPanel panelValor = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelValor.add(cbTipoCedula);
        panelValor.add(Box.createHorizontalStrut(10));
        panelValor.add(tValor);
        panelValor.add(Box.createHorizontalStrut(30));
        panelValor.add(bBuscar);
        panelValor.add(Box.createHorizontalStrut(30));
        panelValor.add(bVerTodos);
        panelValor.setOpaque(false);
        
        
        //............................
        
        rbPorCedula.addActionListener( e -> {
            
            rbDelRepresentante.setEnabled(true);
            
            cbTipoCedula.setVisible(true);
            tValor.limpiar();
            tValor.setColumns(8);
        });
        
        rbPorApellido.addActionListener( e -> {
            
            rbDelPaciente.setSelected(true);
            rbDelRepresentante.setEnabled(false);
            
            cbTipoCedula.setVisible(false);
            tValor.limpiar();
            tValor.setColumns(20);
        });
        
        
        //............................
        
        tablaDatosBasicos = new TablaDatosBasicos();
        
        tabla = new HJTable(tablaDatosBasicos);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowSorter(new TableRowSorter<>(tablaDatosBasicos));
        tabla.setBackground(Colores.NORMAL);
        
        
        arreglarCabecera();
        
        
        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTabla.setPreferredSize(new Dimension(800, 400));
        scrollTabla.getVerticalScrollBar().setUnitIncrement(10);
        scrollTabla.setOpaque(false);
        scrollTabla.getViewport().setOpaque(false);
        
        JPanel panelScrollTabla = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        panelScrollTabla.add(scrollTabla);
        panelScrollTabla.setOpaque(false);
        
        
        //............................
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        
        if(paraEstablecerPaciente==false)
        {
            HJButton bHacerConsulta = new HJButton(new IconoDeImagen("Consulta.png", -1, 40), "Hacer Consulta", new Font("Arial", Font.BOLD, 16), Colores.COLORES_BOTONES, true, 150, 80, null);
            bHacerConsulta.addActionListener( e -> mostrarPanelConsulta() );
            bHacerConsulta.setEnabled(false);
            
            HJButton bVerHistoria = new HJButton(new IconoDeImagen("Historia.png", -1, 40), "Ver Historia", new Font("Arial", Font.BOLD, 16), Colores.COLORES_BOTONES, true, 150, 80, null);
            bVerHistoria.addActionListener( e -> mostrarPanelHistoria() );
            bVerHistoria.setEnabled(false);
            
            HJButton bExportarHistoria = new HJButton(new IconoDeImagen("ExportarHistoria.png", -1, 40), "Exportar Historia", new Font("Arial", Font.BOLD, 16), Colores.COLORES_BOTONES, true, 150, 80, null);
            bExportarHistoria.addActionListener( e -> exportarHistoria() );
            bExportarHistoria.setEnabled(false);
            
            HJButton bEliminarHistoria = new HJButton(new IconoDeImagen("EliminarHistoria.png", -1, 40), "Eliminar Historia", new Font("Arial", Font.BOLD, 16), Colores.COLORES_BOTONES, true, 150, 80, null);
            bEliminarHistoria.addActionListener( e -> mostrarDialogoEliminarHistoria() );
            bEliminarHistoria.setEnabled(false);
            
            tabla.getSelectionModel().addListSelectionListener( e -> {
                
                bHacerConsulta.setEnabled(!tabla.getSelectionModel().isSelectionEmpty());
                bVerHistoria.setEnabled(!tabla.getSelectionModel().isSelectionEmpty());
                bExportarHistoria.setEnabled(!tabla.getSelectionModel().isSelectionEmpty());
                bEliminarHistoria.setEnabled(!tabla.getSelectionModel().isSelectionEmpty());
            });
            
            panelBotones.add(bHacerConsulta);
            panelBotones.add(bVerHistoria);
            panelBotones.add(bExportarHistoria);
            panelBotones.add(bEliminarHistoria);
            panelBotones.setOpaque(false);
            
        }else{
            
            HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
            bAceptar.addActionListener( e -> {
                
                pacienteEstaSeleccionado = true;
                
                setVisible(false);
                dispose();
            });
            bAceptar.setEnabled(false);
            
            HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
            bCancelar.addActionListener( e -> {
                
                setVisible(false);
                dispose();
            });
            
            tabla.getSelectionModel().addListSelectionListener( e -> bAceptar.setEnabled(!tabla.getSelectionModel().isSelectionEmpty()) );
            
            panelBotones.add(bAceptar);
            panelBotones.add(bCancelar);
            panelBotones.setOpaque(false);
        }
        
        //............................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelParametros);
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelValor);
        cajaGeneral.add(Box.createVerticalStrut(15));
        cajaGeneral.add(panelScrollTabla);
        cajaGeneral.add(panelBotones);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    private void arreglarCabecera() {
        
        tabla.getColumnModel().getColumn(0).setHeaderValue("Nro. HISTORIA");
        tabla.getColumnModel().getColumn(0).setPreferredWidth(130);
        
        tabla.getColumnModel().getColumn(1).setHeaderValue("APELLIDOS Y NOMBRES");
        tabla.getColumnModel().getColumn(1).setPreferredWidth(370);
        
        tabla.getColumnModel().getColumn(2).setHeaderValue("GÉNERO");
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        
        tabla.getColumnModel().getColumn(3).setHeaderValue("CÉDULA");
        tabla.getColumnModel().getColumn(3).setPreferredWidth(120);
        
        tabla.getColumnModel().getColumn(4).setHeaderValue("EDAD");
        tabla.getColumnModel().getColumn(4).setPreferredWidth(80);
        
        tabla.getRowSorter().toggleSortOrder(1);
    }
    
    
    //..............................
    
    
    private void buscarPaciente() {
        
        if(rbPorCedula.isSelected())
        {
            if(tValor.esTextoValido())
            {
                if(tValor.esNumeroEntero())
                {
                    if(rbDelPaciente.isSelected())
                        tablaDatosBasicos.consultarConCedulaPaciente(cbTipoCedula.getSelectedItem()+tValor.getText());
                    else
                        tablaDatosBasicos.consultarConCedulaRepresentante(cbTipoCedula.getSelectedItem()+tValor.getText());
                    
                    arreglarCabecera();
                
                }else{ HJDialog.mostrarMensaje("Error al Ingresar Datos", new String[] {"El valor de la cédula debe ser escrito como un número entero.", "(Ejemplo: 7654321)"}, IconoDeImagen.ADVERTENCIA, null); }
                
            }else{ HJDialog.mostrarMensaje("Campo Vacío", new String[] {"Debe introducir el número de cédula."}, IconoDeImagen.ADVERTENCIA, null); }
            
        }else{
            
            if(tValor.esTextoValido())
            {
                tablaDatosBasicos.consultarConApellido(tValor.getText());
                
                arreglarCabecera();
                
            }else{ HJDialog.mostrarMensaje("Campo Vacío", new String[] {"Debe introducir el apellido."}, IconoDeImagen.ADVERTENCIA, null); }
        }
    }
    
    
    private void mostrarTodos() {
        
        tablaDatosBasicos.mostrarTodaLaLista();
        
        arreglarCabecera();
    }
    
    
    //..............................
    
    private void mostrarPanelConsulta() {
        
        PanelConsulta panelConsulta = new PanelConsulta(Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(), tabla.getColumnModel().getColumnIndex("Nro. HISTORIA"))));
        panelConsulta.setVisible(true);
        
        tablaDatosBasicos.fireTableStructureChanged();
        arreglarCabecera();
    }
    
    
    private void mostrarPanelHistoria() {
        
        HJWaitingPanel panelEspera = new HJWaitingPanel();
        
        Thread hiloPanelHistoria = new Thread( () -> {
            
            PanelHistoria panelHistoria = new PanelHistoria(Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(), tabla.getColumnModel().getColumnIndex("Nro. HISTORIA"))));
            
            panelEspera.setVisible(false);
            panelEspera.dispose();
            
            panelHistoria.setVisible(true);
            
            tablaDatosBasicos.fireTableStructureChanged();
            arreglarCabecera();
        });
        hiloPanelHistoria.start();
        
        panelEspera.setVisible(true);
    }
    
    
    private void exportarHistoria() {
        
        HJWaitingPanel panelEspera = new HJWaitingPanel();
        
        Thread hiloObtenerHistoria = new Thread( () -> {
            
            RespaldoHistoria respaldoHistoria = Procedimientos.crearRespaldoHistoria(Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(), tabla.getColumnModel().getColumnIndex("Nro. HISTORIA"))));
            
            panelEspera.setVisible(false);
            panelEspera.dispose();
            
            setIconImage(new IconoDeImagen("LogoDocAssistant.png", -1, 16).getImage());
            
            HJFileChooser selectorArchivo = new HJFileChooser(null, HJFileChooser.HISTORIA_DOC_ASSISTANT);
            
            if(selectorArchivo.showSaveDialog(this)==HJFileChooser.APPROVE_OPTION)
            {
                Utilidades.guardarEnArchivo(selectorArchivo.getSelectedFile().getPath()+".hda", respaldoHistoria);
                
                HJDialog.mostrarMensaje("Historia Guardada", new String[] {"La historia se guardó exitosamente."}, IconoDeImagen.INFORMACION, null);
            }
            
            setIconImage(null);
        });
        hiloObtenerHistoria.start();
        
        panelEspera.setVisible(true);
    }
    
    
    private void mostrarDialogoEliminarHistoria() {
        
        if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea eliminar esta historia?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null) == 0)
        {
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloEliminarHistoria = new Thread( () -> {
                
                Procedimientos.eliminarHistoria(Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(), tabla.getColumnModel().getColumnIndex("Nro. HISTORIA"))));
                
                tablaDatosBasicos.actualizar();
                
                arreglarCabecera();
                
                panelEspera.setVisible(false);
                panelEspera.dispose();
                
                HJDialog.mostrarMensaje("Historia eliminada.", new String[] {"La historia fue eliminada."}, IconoDeImagen.INFORMACION, null);
            });
            hiloEliminarHistoria.start();
            
            panelEspera.setVisible(true);
        }
    }
    
    
    //.................................
    
    public boolean estaPacienteSeleccionado() { return pacienteEstaSeleccionado; }
    
    
    public int obtenerNroHistoriaSeleccionado() { return Integer.parseInt(""+tabla.getValueAt(tabla.getSelectedRow(), 0)); }
    
    
}
