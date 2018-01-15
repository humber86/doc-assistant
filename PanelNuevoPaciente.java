/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import com.github.sarxos.webcam.Webcam;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.OverlayLayout;


public class PanelNuevoPaciente extends HJDialog {
    
    private final Font formatoTitulos = new Font("Eras Demi ITC", Font.BOLD, 26);
    
    private final Font formatoBotonesPrincipales = new Font("Arial", Font.BOLD, 18);
    private final Color[] coloresBotonesPrincipales = {Colores.AZUL_CLARO_2, Colores.AZUL_OSCURO, Colores.PRESSED};
    
    private final Color colorAzulLlamativo = Colores.AZUL_LLAMATIVO;
    
    private final HJLabel lNumero;
    
    private final JLabel lFoto;
    private final HJCustomizedButton bEliminarFoto;
    
    private final JPanel panelDerecho;
    private final CardLayout layoutCarta = new CardLayout(0, 0);
    
    private String nombreBoxSeleccionado;
    private final HJButton bDatosPersonales;
    private final BoxDatosPersonales boxDatosPersonales;
    private final String DATOS_PERSONALES = "Datos Personales";
    private final HJButton bRepresentantes;
    private final BoxRepresentantes boxRepresentantes;
    private final String REPRESENTANTES = "Representante";
    private final HJButton bAntecedentes;
    private final BoxAntecedentes boxAntecedentes;
    private final String ANTECEDENTES = "Antecedentes";
    
    
    public PanelNuevoPaciente() {
        
        super(new IconoDeImagen("NuevoPaciente.png", -1, 30), "Nuevo Paciente", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //.................................
        
        boxDatosPersonales = new BoxDatosPersonales();
        boxDatosPersonales.setName(DATOS_PERSONALES);
        
        boxRepresentantes = new BoxRepresentantes();
        boxRepresentantes.setName(REPRESENTANTES);
        
        boxAntecedentes = new BoxAntecedentes();
        boxAntecedentes.setName(ANTECEDENTES);
        
        
        //.................................
        
        HJLabel lHistoria = new HJLabel("Historia Nro.: ");
        lHistoria.setFont(new Font("Arial", Font.BOLD, 20));
        
        lNumero = new HJLabel(""+Procedimientos.obtenerNroHistoria());
        lNumero.setFont(new Font("Arial", Font.BOLD, 24));
        lNumero.setForeground(colorAzulLlamativo);
        
        JPanel panelHistoriaNumero = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelHistoriaNumero.add(lHistoria);
        panelHistoriaNumero.add(lNumero);
        panelHistoriaNumero.setOpaque(false);
        
        
        HJCustomizedButton bCapturar = new HJCustomizedButton(new IconoDeImagen("Capturar.png", -1, 40), true);
        bCapturar.addActionListener( e -> mostrarCamara() );
        bCapturar.setToolTipText("Capturar desde webcam");
        
        JPanel panelBotonCapturar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonCapturar.add(bCapturar);
        panelBotonCapturar.setOpaque(false);
        
        HJCustomizedButton bCargar = new HJCustomizedButton(new IconoDeImagen("Cargar.png", -1, 40), true);
        bCargar.addActionListener( e -> mostrarDialogoSelector() );
        bCargar.setToolTipText("Cargar desde PC");
        
        JPanel panelBotonCargar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonCargar.add(bCargar);
        panelBotonCargar.setOpaque(false);
        
        bEliminarFoto = new HJCustomizedButton(new IconoDeImagen("Eliminar.png", -1, 30), true);
        bEliminarFoto.addActionListener( e -> eliminarFoto() );
        bEliminarFoto.setEnabled(false);
        bEliminarFoto.setToolTipText("Eliminar foto");
        
        JPanel panelBotonEliminarFoto = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBotonEliminarFoto.add(bEliminarFoto);
        panelBotonEliminarFoto.setOpaque(false);
        
        
        Box boxBotonesFoto = Box.createVerticalBox();
        boxBotonesFoto.add(panelBotonCapturar);
        boxBotonesFoto.add(Box.createVerticalStrut(30));
        boxBotonesFoto.add(panelBotonCargar);
        boxBotonesFoto.add(Box.createVerticalStrut(30));
        boxBotonesFoto.add(panelBotonEliminarFoto);
        
        
        lFoto = new JLabel(new IconoDeImagen("FotoDesconocido.png", 130, 195));
        lFoto.setBorder(BorderFactory.createLineBorder(Colores.LINEAS_OSCURAS, 1));
        
        JPanel panelFoto = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelFoto.add(lFoto);
        panelFoto.setOpaque(false);
        
        
        JPanel panelFotoMacro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelFotoMacro.add(Box.createHorizontalStrut(10));
        panelFotoMacro.add(boxBotonesFoto);
        panelFotoMacro.add(Box.createHorizontalStrut(10));
        panelFotoMacro.add(panelFoto);
        panelFotoMacro.add(Box.createHorizontalStrut(10));
        panelFotoMacro.setOpaque(false);
        
        
        //....
        
        Box boxFoto = Box.createVerticalBox();
        boxFoto.add(Box.createVerticalStrut(10));
        boxFoto.add(panelHistoriaNumero);
        boxFoto.add(Box.createVerticalStrut(10));
        boxFoto.add(panelFotoMacro);
        boxFoto.add(Box.createVerticalStrut(14));
        
        boxFoto.setMaximumSize(new Dimension(220, 230));
        
        
        //.................
        
        bDatosPersonales = new HJButton(new IconoDeImagen("Paciente.png", -1, 40), DATOS_PERSONALES, formatoBotonesPrincipales, coloresBotonesPrincipales, false, 220, 67, null);
        bDatosPersonales.convertirAToggleButton();
        bDatosPersonales.addActionListener( e -> mostrarBox(DATOS_PERSONALES) );
        
        bRepresentantes = new HJButton(new IconoDeImagen("Representantes.png", -1, 40), REPRESENTANTES, formatoBotonesPrincipales, coloresBotonesPrincipales, false, 220, 67, null);
        bRepresentantes.convertirAToggleButton();
        bRepresentantes.addActionListener( e -> mostrarBox(REPRESENTANTES) );
        
        bAntecedentes = new HJButton(new IconoDeImagen("Antecedentes.png", -1, 40), ANTECEDENTES, formatoBotonesPrincipales, coloresBotonesPrincipales, false, 220, 67, null);
        bAntecedentes.convertirAToggleButton();
        bAntecedentes.addActionListener( e -> mostrarBox(ANTECEDENTES) );
        
        JPanel panelBotonesPrincipales = new JPanel(new GridLayout(3, 1, 0, 0));
        panelBotonesPrincipales.add(bDatosPersonales);
        panelBotonesPrincipales.add(bRepresentantes);
        panelBotonesPrincipales.add(bAntecedentes);
        panelBotonesPrincipales.setOpaque(false);
        
        panelBotonesPrincipales.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        HJButtonGroup grupoBotonesPrincipales = new HJButtonGroup();
        grupoBotonesPrincipales.anadir(bDatosPersonales);
        grupoBotonesPrincipales.anadir(bRepresentantes);
        grupoBotonesPrincipales.anadir(bAntecedentes);
        
        bDatosPersonales.establecerSeleccionado(true);
        
        
        //.................
        
        Box boxIzquierdo = Box.createVerticalBox();
        boxIzquierdo.add(boxFoto);
        boxIzquierdo.add(panelBotonesPrincipales);
        
        boxIzquierdo.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Colores.LINEAS));
        
        
        //.............................
        
        HJButton bGuardar = new HJButton(new IconoDeImagen("Guardar.png", -1, 20), "Guardar", Colores.COLORES_BOTONES);
        bGuardar.addActionListener( e -> guardarPaciente() );
        
        HJButton bLimpiar = new HJButton(new IconoDeImagen("Limpiar.png", -1, 20), "Limpiar", Colores.COLORES_BOTONES);
        bLimpiar.addActionListener( e -> limpiar() );
        
        HJButton bLimpiarTodo = new HJButton(new IconoDeImagen("LimpiarTodo.png", -1, 20), "Limpiar Todo", Colores.COLORES_BOTONES);
        bLimpiarTodo.addActionListener( e -> {
            
            if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea borrar toda la información suministrada?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
                limpiarTodo();
        });
        
        HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
        bCancelar.addActionListener( e -> {
            
            setVisible(false);
            dispose();
        });
        
        JPanel panelBotonesInferiores = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelBotonesInferiores.add(bGuardar);
        panelBotonesInferiores.add(Box.createHorizontalStrut(30));
        panelBotonesInferiores.add(bLimpiar);
        panelBotonesInferiores.add(Box.createHorizontalStrut(30));
        panelBotonesInferiores.add(bLimpiarTodo);
        panelBotonesInferiores.add(Box.createHorizontalStrut(30));
        panelBotonesInferiores.add(bCancelar);
        panelBotonesInferiores.setOpaque(false);
        
        panelBotonesInferiores.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //..............................
        
        panelDerecho = new JPanel(layoutCarta);
        panelDerecho.add(boxDatosPersonales);
        layoutCarta.addLayoutComponent(boxDatosPersonales, boxDatosPersonales.getName());
        panelDerecho.add(boxRepresentantes);
        layoutCarta.addLayoutComponent(boxRepresentantes, boxRepresentantes.getName());
        panelDerecho.add(boxAntecedentes);
        layoutCarta.addLayoutComponent(boxAntecedentes, boxAntecedentes.getName());
        
        panelDerecho.setOpaque(false);
        
        
        nombreBoxSeleccionado = DATOS_PERSONALES;
        
        
        Box boxSuperior = Box.createHorizontalBox();
        boxSuperior.add(boxIzquierdo);
        boxSuperior.add(panelDerecho);
        
        
        //.......
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(boxSuperior);
        cajaGeneral.add(panelBotonesInferiores);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    //...............................
    
    
    private void mostrarCamara() {
        
        List<Webcam> listaWebcams = Webcam.getWebcams();
        
        JComboBox<Object> cbWebcams = new JComboBox<>();
        
        if(listaWebcams.isEmpty()==false)
        {
            cbWebcams.addItem("Seleccione...");
            
            for(int i=0 ; i<=listaWebcams.size()-1 ; i++)
            {
                cbWebcams.addItem(listaWebcams.get(i));
            }
            
        }else{
            
            HJDialog.mostrarMensaje("No se Detectaron Dispositivos de Captura", new String[] {"No se detectaron cámaras disponibles para usar.", "Por favor, asegúrese de que está(n) correctamente instalada(s) y conectada(s)."}, IconoDeImagen.ADVERTENCIA, null);
            
            return;
        }
        
        Webcam webcamSeleccionada = (Webcam)HJDialog.mostrarDialogoSelector("Seleccione Cámara", new String[] {"¿Cuál cámara desea usar?"}, cbWebcams, false, new HJButton(null, "Continuar", Colores.COLORES_BOTONES), IconoDeImagen.PREGUNTA, null);
        
        if(webcamSeleccionada!=null)
        {
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloPanelCaptura = new Thread( () -> {
                
                PanelCaptura panelCaptura = new PanelCaptura(webcamSeleccionada);
                
                panelEspera.setVisible(false);
                panelEspera.dispose();
                
                panelCaptura.setVisible(true);
                
                if(panelCaptura.seCapturoImagen())
                {
                    PanelRecortarImagen panelRecortarImagen = new PanelRecortarImagen(panelCaptura.obtenerImagenCapturada());
                    panelRecortarImagen.setVisible(true);
                    
                    if(panelRecortarImagen.seRecortoImagen())
                    {
                        lFoto.setIcon(new ImageIcon(panelRecortarImagen.obtenerImagenRecortada().getScaledInstance(130, 195, Image.SCALE_SMOOTH)));
                        
                        bEliminarFoto.setEnabled(true);
                    }
                }
            });
            hiloPanelCaptura.start();
            
            panelEspera.setVisible(true);
        }
    }
    
    
    private void mostrarDialogoSelector() {
        
        setIconImage(new IconoDeImagen("Abrir.png", -1, 16).getImage());
        
        HJFileChooser selector = new HJFileChooser(null, HJFileChooser.IMAGENES);
        
        if(selector.showOpenDialog(this)==HJFileChooser.APPROVE_OPTION)
        {
            Image imagen = null;
            
            try{
                imagen = ImageIO.read(selector.getSelectedFile());
            }catch(IOException ioe) {
                Toolkit.getDefaultToolkit().beep();
                HJDialog.mostrarMensaje("Error", new String[] {"Ocurrió un error al cargar la imagen."}, IconoDeImagen.ERROR, null);
            }
            
            if(imagen==null)  return;
            
            PanelRecortarImagen panelRecortarImagen = new PanelRecortarImagen(imagen);
            panelRecortarImagen.setVisible(true);
            
            if(panelRecortarImagen.seRecortoImagen())
            {
                lFoto.setIcon(new ImageIcon(panelRecortarImagen.obtenerImagenRecortada().getScaledInstance(130, 195, Image.SCALE_SMOOTH)));
                
                bEliminarFoto.setEnabled(true);
            }
        }
        
        setIconImage(null);
    }
    
    
    private void eliminarFoto() {
        
        lFoto.setIcon(new IconoDeImagen("FotoDesconocido.png", 130, 195));
        
        bEliminarFoto.setEnabled(false);
    }
    
    
    private void guardarFoto(int numeroHistoria) {
        
        BufferedImage imagenBuffered = new BufferedImage(130, 195, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2D = imagenBuffered.createGraphics();
        
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Icon icono = lFoto.getIcon();
        
        icono.paintIcon(null, g2D, 0, 0);
        
        try{
            ImageIO.write(imagenBuffered, "png", new File(Directorios.FOTOS+numeroHistoria+".png"));
        }catch(IOException ioe) {
            Toolkit.getDefaultToolkit().beep();
            HJDialog.mostrarMensaje("Error al Guardar Foto", new String[] {"Ocurrió un error al guardar la foto del paciente."}, IconoDeImagen.ERROR, null);
        }
    }
       
    
    //...............................
    
    private void mostrarBox(String nombreBoxAMostrar) {
        
        nombreBoxSeleccionado = nombreBoxAMostrar;
        
        layoutCarta.show(panelDerecho, nombreBoxAMostrar);
    }
    
    
    //...............................
    
    private void guardarPaciente() {
        
        if(boxDatosPersonales.estanCasillasListas() && boxRepresentantes.estanCasillasListas() && boxAntecedentes.estanAntecedentesListos())
        {
            int numeroHistoria = Procedimientos.obtenerNroHistoria();
            
            
            Utilidades.guardarEnArchivo(Directorios.DATOS_PERSONALES+numeroHistoria+".dda", boxDatosPersonales.obtenerMapaDatos());
            
            
            if(bEliminarFoto.isEnabled())  guardarFoto(numeroHistoria);
            
            
            if(boxRepresentantes.estaRepresentanteEstablecido())
                Utilidades.guardarEnArchivo(Directorios.REPRESENTANTES+numeroHistoria+".dda", boxRepresentantes.obtenerMapaDatos());
            
            
            MapaDatos antecedentesNeonatales = boxAntecedentes.obtenerAntecedentesNeonatales();
            if(antecedentesNeonatales.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_NEONATALES+numeroHistoria+".dda", antecedentesNeonatales);
            
            HashMap<String,String> antecedentesPersonales = boxAntecedentes.obtenerAntecedentesPersonales();
            if(antecedentesPersonales.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_PERSONALES+numeroHistoria+".dda", antecedentesPersonales);
            
            HashMap<String,String> antecedentesFamiliares = boxAntecedentes.obtenerAntecedentesFamiliares();
            if(antecedentesFamiliares.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_FAMILIARES+numeroHistoria+".dda", antecedentesFamiliares);
            
            HashSet<String> alergias = boxAntecedentes.obtenerAlergias();
            if(alergias.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_ALERGIAS+numeroHistoria+".dda", alergias);
            
            HashMap<String,ArrayList<String>> intervenciones = boxAntecedentes.obtenerIntervenciones();
            if(intervenciones.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_INTERVENCIONES+numeroHistoria+".dda", intervenciones);
            
            HashMap<String,ArrayList<String>> hospitalizaciones = boxAntecedentes.obtenerHospitalizaciones();
            if(hospitalizaciones.size()>0)
                Utilidades.guardarEnArchivo(Directorios.ANTECEDENTES_HOSPITALIZACIONES+numeroHistoria+".dda", hospitalizaciones);
            
            
            limpiarTodo();
            
            bDatosPersonales.doClick();
            
            boxDatosPersonales.obtenerScrollPane().getVerticalScrollBar().setValue(0);
            
            
            int respuesta = HJDialog.mostrarDialogoPregunta("Operación Exitosa", new String[] {"Los datos se guardaron correctamente.", " ", "¿Qué desea hacer a continuación?"}, new HJButton[] {new HJButton(new IconoDeImagen("Consulta.png", -1, 20), "Hacer 1era. consulta", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Historia.png", -1, 20), "Ver Historia", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("NuevoPaciente.png", -1, 20), "Registrar otro paciente", Colores.COLORES_BOTONES), new HJButton(null, "Cerrar", Colores.COLORES_BOTONES)}, 2, IconoDeImagen.INFORMACION, null);
            
            if(respuesta==0)
            {
                setVisible(false);
                dispose();
                
                PanelConsulta panelConsulta = new PanelConsulta(numeroHistoria);
                panelConsulta.setVisible(true);
            }
            
            if(respuesta==1)
            {
                setVisible(false);
                dispose();
                
                HJWaitingPanel panelEspera = new HJWaitingPanel();

                Thread hiloPanelHistoria = new Thread( () -> {

                    PanelHistoria panelHistoria = new PanelHistoria(numeroHistoria);

                    panelEspera.setVisible(false);
                    panelEspera.dispose();

                    panelHistoria.setVisible(true);
                });
                hiloPanelHistoria.start();

                panelEspera.setVisible(true);
            }
            
            if(respuesta==2)
                lNumero.setText(""+Procedimientos.obtenerNroHistoria());
            
            if(respuesta==3)
            {
                setVisible(false);
                dispose();
            }
            
        }
    }
    
    
    private void limpiar() {
        
        switch(nombreBoxSeleccionado)
        {
            case DATOS_PERSONALES : boxDatosPersonales.limpiarCasillas();
                                    break;
            
            case REPRESENTANTES : boxRepresentantes.limpiarCasillas();
                                  break;
            
            case ANTECEDENTES : boxAntecedentes.eliminarTodosAntecedentes();
                                break;
            
            default: break;
        }
    }
    
    
    private void limpiarTodo() {
        
        lFoto.setIcon(new IconoDeImagen("FotoDesconocido.png", 130, 195));
        boxDatosPersonales.limpiarCasillas();
        boxRepresentantes.limpiarCasillas();
        boxAntecedentes.eliminarTodosAntecedentes();
    }
    
    
    //................................
    
    private HJLabel crearEtiquetaAzul(String texto) {
        
        HJLabel etiqueta = new HJLabel(texto);
        etiqueta.setForeground(colorAzulLlamativo);
        
        return etiqueta;
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class BoxDatosPersonales extends HJPaintedBox {
        
        private final HJTextField tNombres;
        private final HJTextField tApellidos;
        private final HJComboBox<String> cbGenero;
        private final HJComboBox<String> cbTipoCedula;
        private final HJTextField tCedula;
        private final HJComboBox<String> cbDia;
        private final HJComboBox<String> cbMes;
        private final HJComboBox<String> cbAno;
        
        private final HJTextField tTelefono1;
        private final HJTextField tTelefono2;
        private final HJTextField tEMail;
        
        private final HJTextField tEstado;
        private final HJTextField tCiudad;
        private final HJTextArea taDireccion;
        
        private final HJComboBox<String> cbGrupoSanguineo;
        private final HJTextArea taLugarNacimiento;
        private final HJTextArea taLugarProcedencia;
        
        public final JScrollPane scrollBoxVertical;
        
        
        public BoxDatosPersonales() {
            
            super(BoxLayout.Y_AXIS, new Color(245,250,255), new Color(235,245,255), null, null, 100, true);
            
            
            JLabel lIconoDatosPersonales = new JLabel(new IconoDeImagen("Paciente.png", -1, 40));
            lIconoDatosPersonales.setFont(formatoTitulos);
            
            JPanel panelIcono = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelIcono.add(lIconoDatosPersonales);
            panelIcono.add(Box.createHorizontalStrut(lIconoDatosPersonales.getFontMetrics(lIconoDatosPersonales.getFont()).stringWidth(DATOS_PERSONALES) + 70));
            panelIcono.setOpaque(false);
            
            
            JLabel lDatosPersonales = new JLabel(DATOS_PERSONALES);
            lDatosPersonales.setFont(formatoTitulos);
            
            JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
            panelTitulo.add(lDatosPersonales);
            panelTitulo.setOpaque(false);
            
            
            JPanel panelTituloGeneral = new JPanel();
            panelTituloGeneral.setLayout(new OverlayLayout(panelTituloGeneral));
            panelTituloGeneral.add(panelIcono);
            panelTituloGeneral.add(panelTitulo);
            panelTituloGeneral.setMaximumSize(new Dimension(400, 40));
            panelTituloGeneral.setOpaque(false);
            
            
            //...................
            
            Font formatoEnclosingBoxes = new Font("Arial", Font.BOLD, 20);
            Color colorTextoEnclosingBoxes = Colores.TEXTO;
            Color colorLineaEnclosingBoxes = Colores.LINEAS_OSCURAS;
            
            //....
            
            tNombres = new HJTextField(20);
            tApellidos = new HJTextField(20);
            
            JPanel panelNombresApellidos = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelNombresApellidos.add(crearEtiquetaAzul("Nombres: "));
            panelNombresApellidos.add(tNombres);
            panelNombresApellidos.add(Box.createHorizontalStrut(30));
            panelNombresApellidos.add(crearEtiquetaAzul("Apellidos: "));
            panelNombresApellidos.add(tApellidos);
            panelNombresApellidos.setOpaque(false);
            
            
            String[] generos = {"Seleccione...", "Femenino", "Masculino"};
            cbGenero = new HJComboBox<>(generos);
            
            String[] tiposCedula = {"V-", "E-"};
            cbTipoCedula = new HJComboBox<>(tiposCedula);
            tCedula = new HJTextField(8);
            
            
            String[] dias = new String[32];
            dias[0] = "Día";
            for(int i=1 ; i<=31 ; i++)
            {
                dias[i] = ""+i;
            }
            
            cbDia = new HJComboBox<>(dias);
            
            String[] meses = {"Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            
            cbMes = new HJComboBox<>(meses);
            
            cbAno = new HJComboBox<>();
            cbAno.addItem("Año");
            LocalDate fechaActual = LocalDate.now();
            int anoActual = fechaActual.getYear();
            for(int i=0, j=anoActual ; i<=120 ; i++, j--)
            {
                cbAno.addItem(""+j);
            }
            
            JPanel panelGeneroCedulaNacimiento = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelGeneroCedulaNacimiento.add(crearEtiquetaAzul("Género: "));
            panelGeneroCedulaNacimiento.add(cbGenero);
            panelGeneroCedulaNacimiento.add(Box.createHorizontalStrut(30));
            panelGeneroCedulaNacimiento.add(new HJLabel("Cédula: "));
            panelGeneroCedulaNacimiento.add(cbTipoCedula);
            panelGeneroCedulaNacimiento.add(Box.createHorizontalStrut(4));
            panelGeneroCedulaNacimiento.add(tCedula);
            panelGeneroCedulaNacimiento.add(Box.createHorizontalStrut(30));
            panelGeneroCedulaNacimiento.add(crearEtiquetaAzul("Fecha de Nacimiento: "));
            panelGeneroCedulaNacimiento.add(cbDia);
            panelGeneroCedulaNacimiento.add(Box.createHorizontalStrut(4));
            panelGeneroCedulaNacimiento.add(cbMes);
            panelGeneroCedulaNacimiento.add(Box.createHorizontalStrut(4));
            panelGeneroCedulaNacimiento.add(cbAno);
            panelGeneroCedulaNacimiento.setOpaque(false);
            
            
            HJEnclosingBox boxDatosBasicos = new HJEnclosingBox(BoxLayout.Y_AXIS, "Datos Básicos", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxDatosBasicos.add(Box.createVerticalStrut(35));
            boxDatosBasicos.add(panelNombresApellidos);
            boxDatosBasicos.add(Box.createVerticalStrut(20));
            boxDatosBasicos.add(panelGeneroCedulaNacimiento);
            boxDatosBasicos.add(Box.createVerticalStrut(15));
            
            Box boxDatosBasicosGeneral = Box.createHorizontalBox();
            boxDatosBasicosGeneral.add(Box.createHorizontalStrut(10));
            boxDatosBasicosGeneral.add(boxDatosBasicos);
            boxDatosBasicosGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            tTelefono1 = new HJTextField(9);
            tTelefono2 = new HJTextField(9);
            tEMail = new HJTextField(22);
            
            JPanel panelTelefonosEmail = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelTelefonosEmail.add(new HJLabel("Teléfono: "));
            panelTelefonosEmail.add(tTelefono1);
            panelTelefonosEmail.add(Box.createHorizontalStrut(30));
            panelTelefonosEmail.add(new HJLabel("Teléfono 2: "));
            panelTelefonosEmail.add(tTelefono2);
            panelTelefonosEmail.add(Box.createHorizontalStrut(30));
            panelTelefonosEmail.add(new HJLabel("E-Mail: "));
            panelTelefonosEmail.add(tEMail);
            panelTelefonosEmail.setOpaque(false);
            
            
            HJEnclosingBox boxInformacionDeContacto = new HJEnclosingBox(BoxLayout.Y_AXIS, "Información de Contacto", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxInformacionDeContacto.add(Box.createVerticalStrut(35));
            boxInformacionDeContacto.add(panelTelefonosEmail);
            boxInformacionDeContacto.add(Box.createVerticalStrut(15));
            
            Box boxInformacionDeContactoGeneral = Box.createHorizontalBox();
            boxInformacionDeContactoGeneral.add(Box.createHorizontalStrut(10));
            boxInformacionDeContactoGeneral.add(boxInformacionDeContacto);
            boxInformacionDeContactoGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            tEstado = new HJTextField(12);
            tCiudad = new HJTextField(12);
            
            taDireccion = new HJTextArea(3, 25);
            HJScrollPane scrollDireccion = new HJScrollPane(taDireccion);
            scrollDireccion.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollDireccion.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollDireccion.removerEscuchadorRuedaRaton();
            
            
            JPanel panelEstadoCiudadDireccion = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(20));
            panelEstadoCiudadDireccion.add(new HJLabel("Estado: "));
            panelEstadoCiudadDireccion.add(tEstado);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(30));
            panelEstadoCiudadDireccion.add(new HJLabel("Ciudad: "));
            panelEstadoCiudadDireccion.add(tCiudad);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(30));
            panelEstadoCiudadDireccion.add(new HJLabel("Dirección: "));
            panelEstadoCiudadDireccion.add(scrollDireccion);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(20));
            panelEstadoCiudadDireccion.setOpaque(false);
            
            
            HJEnclosingBox boxDireccion = new HJEnclosingBox(BoxLayout.Y_AXIS, "Dirección", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxDireccion.add(Box.createVerticalStrut(30));
            boxDireccion.add(panelEstadoCiudadDireccion);
            boxDireccion.add(Box.createVerticalStrut(15));
            
            Box boxDireccionGeneral = Box.createHorizontalBox();
            boxDireccionGeneral.add(Box.createHorizontalStrut(10));
            boxDireccionGeneral.add(boxDireccion);
            boxDireccionGeneral.add(Box.createHorizontalStrut(10));
            
            
            //....................
            
            String[] gruposSanguineos = {"---", "O+", "A+", "B+", "AB+", "O-", "A-", "B-", "AB-"};
            cbGrupoSanguineo = new HJComboBox<>(gruposSanguineos);
            
            JPanel panelGrupoSanguineo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelGrupoSanguineo.add(new HJLabel("Grupo Sanguíneo: "));
            panelGrupoSanguineo.add(cbGrupoSanguineo);
            panelGrupoSanguineo.setOpaque(false);
            
            
            taLugarNacimiento = new HJTextArea(2, 20);
            HJScrollPane scrollLugarNacimiento = new HJScrollPane(taLugarNacimiento);
            scrollLugarNacimiento.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollLugarNacimiento.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollLugarNacimiento.removerEscuchadorRuedaRaton();
            
            taLugarProcedencia = new HJTextArea(2, 20);
            HJScrollPane scrollLugarProcedencia = new HJScrollPane(taLugarProcedencia);
            scrollLugarProcedencia.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollLugarProcedencia.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollLugarProcedencia.removerEscuchadorRuedaRaton();
            
            JPanel panelNacimientoProcedencia = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelNacimientoProcedencia.add(new HJLabel("Lugar de Nacimiento: "));
            panelNacimientoProcedencia.add(scrollLugarNacimiento);
            panelNacimientoProcedencia.add(Box.createHorizontalStrut(30));
            panelNacimientoProcedencia.add(new HJLabel("Lugar de Procedencia: "));
            panelNacimientoProcedencia.add(scrollLugarProcedencia);
            panelNacimientoProcedencia.setOpaque(false);
            
            
            HJEnclosingBox boxInformacionAdicional = new HJEnclosingBox(BoxLayout.Y_AXIS, "Información Adicional", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxInformacionAdicional.add(Box.createVerticalStrut(30));
            boxInformacionAdicional.add(panelGrupoSanguineo);
            boxInformacionAdicional.add(Box.createVerticalStrut(15));
            boxInformacionAdicional.add(panelNacimientoProcedencia);
            boxInformacionAdicional.add(Box.createVerticalStrut(15));
            
            Box boxInformacionAdicionalGeneral = Box.createHorizontalBox();
            boxInformacionAdicionalGeneral.add(Box.createHorizontalStrut(10));
            boxInformacionAdicionalGeneral.add(boxInformacionAdicional);
            boxInformacionAdicionalGeneral.add(Box.createHorizontalStrut(10));
            
            
            //....................
            
            
            Box boxVertical = Box.createVerticalBox();
            boxVertical.add(Box.createVerticalStrut(15));
            boxVertical.add(panelTituloGeneral);
            boxVertical.add(Box.createVerticalStrut(15));
            boxVertical.add(boxDatosBasicosGeneral);
            boxVertical.add(Box.createVerticalStrut(25));
            boxVertical.add(boxInformacionDeContactoGeneral);
            boxVertical.add(Box.createVerticalStrut(25));
            boxVertical.add(boxDireccionGeneral);
            boxVertical.add(Box.createVerticalStrut(25));
            boxVertical.add(boxInformacionAdicionalGeneral);
            boxVertical.add(Box.createVerticalStrut(25));
            
            scrollBoxVertical = new JScrollPane(boxVertical);
            scrollBoxVertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollBoxVertical.setPreferredSize(new Dimension(958, 470));
            scrollBoxVertical.getVerticalScrollBar().setUnitIncrement(10);
            scrollBoxVertical.setOpaque(false);
            scrollBoxVertical.getViewport().setOpaque(false);
            
            
            add(scrollBoxVertical);
            
        }
        
        
        public void limpiarCasillas() {
            
            tNombres.limpiar();
            tApellidos.limpiar();
            cbGenero.setSelectedIndex(0);
            cbTipoCedula.setSelectedIndex(0);
            tCedula.limpiar();
            cbDia.setSelectedIndex(0);
            cbMes.setSelectedIndex(0);
            cbAno.setSelectedIndex(0);
            
            tTelefono1.limpiar();
            tTelefono2.limpiar();
            tEMail.limpiar();

            tEstado.limpiar();
            tCiudad.limpiar();
            taDireccion.limpiar();
            
            cbGrupoSanguineo.setSelectedIndex(0);
            taLugarNacimiento.limpiar();
            taLugarProcedencia.limpiar();
        }
        
        
        public boolean estanCasillasListas() {
            
            if(tNombres.esTextoValido()==false || tApellidos.esTextoValido()==false || cbGenero.getSelectedIndex()==0 || cbDia.getSelectedIndex()==0 || cbMes.getSelectedIndex()==0 || cbAno.getSelectedIndex()==0)
            {
                HJDialog.mostrarMensaje("Faltan Datos", new String[] {"Los datos con etiquetas azules son necesarios. Por favor, llénelos."}, IconoDeImagen.ADVERTENCIA, null);
                
                bDatosPersonales.doClick();
                
                return false;
            }
            
            
            if(tCedula.esTextoValido()==false && boxRepresentantes.estaRepresentanteEstablecido()==false)
            {
                HJDialog.mostrarMensaje("Faltan Datos", new String[] {"Debe introducir un número de cédula a menos que haya establecido los 'Datos Básicos' del representante."}, IconoDeImagen.ADVERTENCIA, null);
                
                bDatosPersonales.doClick();
                
                return false;
            }
            
            
            if(tCedula.esNumeroEntero()==false)
            {
                HJDialog.mostrarMensaje("Error al Ingresar Datos", new String[] {"El valor de la cédula debe ser escrito como un número entero.", "(Ejemplo: 7654321)"}, IconoDeImagen.ADVERTENCIA, null);
                
                bDatosPersonales.doClick();
                
                return false;
            }
            
            
            int dia = cbDia.getSelectedIndex();
            int mes = cbMes.getSelectedIndex();
            int ano = new Integer(""+cbAno.getSelectedItem());
            
            if(Utilidades.esFechaValida(dia, mes, ano)==false)
            {
                HJDialog.mostrarMensaje("Fecha Inválida", new String[] {"La fecha es inválida.", "Por favor, corríjala."}, IconoDeImagen.ADVERTENCIA, null);
                
                bDatosPersonales.doClick();
                
                return false;
            }
            
            if(Utilidades.esFechaAnterior(dia, mes, ano)==false)
            {
                HJDialog.mostrarMensaje("Fecha Inválida", new String[] {"La fecha debe ser anterior o igual a la actual.", "Por favor, corríjala."}, IconoDeImagen.ADVERTENCIA, null);
                
                bDatosPersonales.doClick();
                
                return false;
            }
            
            
            return true;
        }
        
        
        public MapaDatos obtenerMapaDatos() {
            
            MapaDatos mapaDatos = new MapaDatos();
            mapaDatos.put(Personales.KEY_NOMBRES, tNombres.getText());
            mapaDatos.put(Personales.KEY_APELLIDOS, tApellidos.getText());
            mapaDatos.put(Personales.KEY_GENERO, ""+cbGenero.getSelectedItem());
            if(tCedula.esTextoValido())  mapaDatos.put(Personales.KEY_CEDULA, ""+cbTipoCedula.getSelectedItem()+tCedula.getText());
            mapaDatos.put(Personales.KEY_FECHA_NACIMIENTO, cbDia.getSelectedIndex()+"-"+cbMes.getSelectedIndex()+"-"+cbAno.getSelectedItem());
            if(tTelefono1.esTextoValido())  mapaDatos.put(Personales.KEY_TELEFONO_1, tTelefono1.getText());
            if(tTelefono2.esTextoValido())  mapaDatos.put(Personales.KEY_TELEFONO_2, tTelefono2.getText());
            if(tEMail.esTextoValido())  mapaDatos.put(Personales.KEY_EMAIL, tEMail.getText());
            if(tEstado.esTextoValido())  mapaDatos.put(Personales.KEY_ESTADO, tEstado.getText());
            if(tCiudad.esTextoValido())  mapaDatos.put(Personales.KEY_CIUDAD, tCiudad.getText());
            if(taDireccion.esTextoValido())  mapaDatos.put(Personales.KEY_DIRECCION, taDireccion.getText());
            if(cbGrupoSanguineo.getSelectedIndex()>0)  mapaDatos.put(Personales.KEY_GRUPO_SANGUINEO, ""+cbGrupoSanguineo.getSelectedItem());
            if(taLugarNacimiento.esTextoValido())  mapaDatos.put(Personales.KEY_LUGAR_NACIMIENTO, taLugarNacimiento.getText());
            if(taLugarProcedencia.esTextoValido())  mapaDatos.put(Personales.KEY_LUGAR_PROCEDENCIA, taLugarProcedencia.getText());
            
            return mapaDatos;
        }
        
        
        public String obtenerEstado() { return tEstado.getText(); }
        
        public String obtenerCiudad() { return tCiudad.getText(); }
        
        public String obtenerDireccion() { return taDireccion.getText(); }
        
        
        public JScrollPane obtenerScrollPane() { return scrollBoxVertical; }
        
        
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class BoxRepresentantes extends HJPaintedBox {
        
        private final HJTextField tNombres;
        private final HJTextField tApellidos;
        private final HJComboBox<String> cbTipoCedula;
        private final HJTextField tCedula;
        private final HJComboBox<String> cbParentesco;
        
        private final HJTextField tTelefono1;
        private final HJTextField tTelefono2;
        private final HJTextField tEMail;
        
        private final HJTextField tEstado;
        private final HJTextField tCiudad;
        private final HJTextArea taDireccion;
        
        
        public BoxRepresentantes() {
            
            super(BoxLayout.Y_AXIS, new Color(245,245,245), new Color(235,235,235), null, null, 100, true);
            
            
            JLabel lIconoRepresentantes = new JLabel(new IconoDeImagen("Representantes.png", -1, 40));
            lIconoRepresentantes.setFont(formatoTitulos);
            
            JPanel panelIcono = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelIcono.add(lIconoRepresentantes);
            panelIcono.add(Box.createHorizontalStrut(lIconoRepresentantes.getFontMetrics(lIconoRepresentantes.getFont()).stringWidth(REPRESENTANTES) + 70));
            panelIcono.setOpaque(false);
            
            
            JLabel lRepresentantes = new JLabel(REPRESENTANTES);
            lRepresentantes.setFont(formatoTitulos);
            
            JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
            panelTitulo.add(lRepresentantes);
            panelTitulo.setOpaque(false);
            
            
            JPanel panelTituloGeneral = new JPanel();
            panelTituloGeneral.setLayout(new OverlayLayout(panelTituloGeneral));
            panelTituloGeneral.add(panelIcono);
            panelTituloGeneral.add(panelTitulo);
            panelTituloGeneral.setMaximumSize(new Dimension(400, 40));
            panelTituloGeneral.setOpaque(false);
            
            
            //...................
            
            Font formatoEnclosingBoxes = new Font("Arial", Font.BOLD, 20);
            Color colorTextoEnclosingBoxes = Colores.TEXTO;
            Color colorLineaEnclosingBoxes = Colores.LINEAS_OSCURAS;
            
            //....
            
            tNombres = new HJTextField(20);
            tApellidos = new HJTextField(20);
            
            JPanel panelNombresApellidos = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelNombresApellidos.add(new HJLabel("Nombres: "));
            panelNombresApellidos.add(tNombres);
            panelNombresApellidos.add(Box.createHorizontalStrut(30));
            panelNombresApellidos.add(new HJLabel("Apellidos: "));
            panelNombresApellidos.add(tApellidos);
            panelNombresApellidos.setOpaque(false);
            
            
            String[] tiposCedula = {"V-", "E-"};
            cbTipoCedula = new HJComboBox<>(tiposCedula);
            tCedula = new HJTextField(8);
            
            String[] parentesco = {"Seleccione...", "Madre/Padre", "Abuelo(a)", "Hijo(a)", "Nieto(a)", "Tío(a)", "Otro"};
            cbParentesco = new HJComboBox<>(parentesco);
            
            JPanel panelCedulaParentesco = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelCedulaParentesco.add(new HJLabel("Cédula: "));
            panelCedulaParentesco.add(cbTipoCedula);
            panelCedulaParentesco.add(Box.createHorizontalStrut(4));
            panelCedulaParentesco.add(tCedula);
            panelCedulaParentesco.add(Box.createHorizontalStrut(30));
            panelCedulaParentesco.add(new HJLabel("Parentesco: "));
            panelCedulaParentesco.add(cbParentesco);
            panelCedulaParentesco.setOpaque(false);
            
            
            HJEnclosingBox boxDatosBasicos = new HJEnclosingBox(BoxLayout.Y_AXIS, "Datos Básicos", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxDatosBasicos.add(Box.createVerticalStrut(30));
            boxDatosBasicos.add(panelNombresApellidos);
            boxDatosBasicos.add(Box.createVerticalStrut(15));
            boxDatosBasicos.add(panelCedulaParentesco);
            boxDatosBasicos.add(Box.createVerticalStrut(10));
            
            Box boxDatosBasicosGeneral = Box.createHorizontalBox();
            boxDatosBasicosGeneral.add(Box.createHorizontalStrut(10));
            boxDatosBasicosGeneral.add(boxDatosBasicos);
            boxDatosBasicosGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            tTelefono1 = new HJTextField(9);
            tTelefono2 = new HJTextField(9);
            tEMail = new HJTextField(22);
            
            JPanel panelTelefonosEmail = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelTelefonosEmail.add(new HJLabel("Teléfono: "));
            panelTelefonosEmail.add(tTelefono1);
            panelTelefonosEmail.add(Box.createHorizontalStrut(30));
            panelTelefonosEmail.add(new HJLabel("Teléfono 2: "));
            panelTelefonosEmail.add(tTelefono2);
            panelTelefonosEmail.add(Box.createHorizontalStrut(30));
            panelTelefonosEmail.add(new HJLabel("E-Mail: "));
            panelTelefonosEmail.add(tEMail);
            panelTelefonosEmail.setOpaque(false);
            
            
            HJEnclosingBox boxInformacionDeContacto = new HJEnclosingBox(BoxLayout.Y_AXIS, "Información de Contacto", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxInformacionDeContacto.add(Box.createVerticalStrut(30));
            boxInformacionDeContacto.add(panelTelefonosEmail);
            boxInformacionDeContacto.add(Box.createVerticalStrut(10));
            
            Box boxInformacionDeContactoGeneral = Box.createHorizontalBox();
            boxInformacionDeContactoGeneral.add(Box.createHorizontalStrut(10));
            boxInformacionDeContactoGeneral.add(boxInformacionDeContacto);
            boxInformacionDeContactoGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            HJCustomizedButton bDireccionPaciente = new HJCustomizedButton("Colocar dirección del paciente", new Color[] {Colores.AZUL_LLAMATIVO, Colores.AZUL_OSCURO}, true, true, 2);
            bDireccionPaciente.addActionListener( e -> colocarDireccionPaciente() );
            
            JPanel panelBotonDireccionPaciente = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panelBotonDireccionPaciente.add(Box.createHorizontalStrut(50));
            panelBotonDireccionPaciente.add(bDireccionPaciente);
            panelBotonDireccionPaciente.setOpaque(false);
            
            
            tEstado = new HJTextField(12);
            tCiudad = new HJTextField(12);
            
            taDireccion = new HJTextArea(3, 25);
            HJScrollPane scrollDireccion = new HJScrollPane(taDireccion);
            scrollDireccion.setVerticalScrollBarPolicy(HJScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollDireccion.setHorizontalScrollBarPolicy(HJScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollDireccion.removerEscuchadorRuedaRaton();
            
            
            JPanel panelEstadoCiudadDireccion = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(20));
            panelEstadoCiudadDireccion.add(new HJLabel("Estado: "));
            panelEstadoCiudadDireccion.add(tEstado);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(30));
            panelEstadoCiudadDireccion.add(new HJLabel("Ciudad: "));
            panelEstadoCiudadDireccion.add(tCiudad);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(30));
            panelEstadoCiudadDireccion.add(new HJLabel("Dirección: "));
            panelEstadoCiudadDireccion.add(scrollDireccion);
            panelEstadoCiudadDireccion.add(Box.createHorizontalStrut(20));
            panelEstadoCiudadDireccion.setOpaque(false);
            
            
            HJEnclosingBox boxDireccion = new HJEnclosingBox(BoxLayout.Y_AXIS, "Dirección", formatoEnclosingBoxes, colorTextoEnclosingBoxes, HJEnclosingBox.IZQUIERDA, 50, colorLineaEnclosingBoxes, 3);
            boxDireccion.add(Box.createVerticalStrut(25));
            boxDireccion.add(panelBotonDireccionPaciente);
            boxDireccion.add(panelEstadoCiudadDireccion);
            boxDireccion.add(Box.createVerticalStrut(10));
            
            Box boxDireccionGeneral = Box.createHorizontalBox();
            boxDireccionGeneral.add(Box.createHorizontalStrut(10));
            boxDireccionGeneral.add(boxDireccion);
            boxDireccionGeneral.add(Box.createHorizontalStrut(10));
            
            
            //...................
            
            
            Box boxVertical = Box.createVerticalBox();
            boxVertical.add(Box.createVerticalStrut(15));
            boxVertical.add(panelTituloGeneral);
            boxVertical.add(Box.createVerticalStrut(15));
            boxVertical.add(boxDatosBasicosGeneral);
            boxVertical.add(Box.createVerticalStrut(25));
            boxVertical.add(boxInformacionDeContactoGeneral);
            boxVertical.add(Box.createVerticalStrut(25));
            boxVertical.add(boxDireccionGeneral);
            boxVertical.add(Box.createVerticalStrut(25));
            
            
            add(boxVertical);
            
        }
        
        
        public void limpiarCasillas() {
            
            tNombres.limpiar();
            tApellidos.limpiar();
            cbTipoCedula.setSelectedIndex(0);
            tCedula.limpiar();
            cbParentesco.setSelectedIndex(0);
            
            tTelefono1.limpiar();
            tTelefono2.limpiar();
            tEMail.limpiar();

            tEstado.limpiar();
            tCiudad.limpiar();
            taDireccion.limpiar();
        }
        
        
        private void colocarDireccionPaciente() {
            
            tEstado.setText(boxDatosPersonales.obtenerEstado());
            tCiudad.setText(boxDatosPersonales.obtenerCiudad());
            taDireccion.setText(boxDatosPersonales.obtenerDireccion());
        }
        
        
        private boolean seComenzoAEstablecerRepresentante() {
            
            return tNombres.esTextoValido() || tApellidos.esTextoValido() || tCedula.esTextoValido() || cbParentesco.getSelectedIndex()>0 || tTelefono1.esTextoValido() || tTelefono2.esTextoValido() || tEMail.esTextoValido() || tEstado.esTextoValido() || tCiudad.esTextoValido() || taDireccion.esTextoValido();
        }
        
        
        public boolean estaRepresentanteEstablecido() {
            
            return tNombres.esTextoValido() && tApellidos.esTextoValido() && (tCedula.esTextoValido() && tCedula.esNumeroEntero()) && cbParentesco.getSelectedIndex()>0;
        }
        
        
        public boolean estanCasillasListas() {
            
            if(seComenzoAEstablecerRepresentante())
            {
                if(tNombres.esTextoValido()==false || tApellidos.esTextoValido()==false || tCedula.esTextoValido()==false || cbParentesco.getSelectedIndex()==0)
                {
                    HJDialog.mostrarMensaje("Faltan Datos del Representante", new String[] {"Si va a registrar el representante, debe llenar, al menos, el apartado 'Datos Básicos' del mismo."}, IconoDeImagen.ADVERTENCIA, null);
                    
                    bRepresentantes.doClick();
                    
                    return false;
                }
                
                
                if(tCedula.esNumeroEntero()==false)
                {
                    HJDialog.mostrarMensaje("Error al Ingresar Datos del Representante", new String[] {"El valor de la cédula debe ser escrito como un número entero.", "(Ejemplo: 7654321)"}, IconoDeImagen.ADVERTENCIA, null);
                    
                    bRepresentantes.doClick();
                    
                    return false;
                }
                
                
                return true;
                
            }else{
                
                return true;
            }
        }
        
        
        public MapaDatos obtenerMapaDatos() {
            
            MapaDatos mapaDatos = new MapaDatos();
            mapaDatos.put(Representantes.KEY_NOMBRES, tNombres.getText());
            mapaDatos.put(Representantes.KEY_APELLIDOS, tApellidos.getText());
            mapaDatos.put(Representantes.KEY_CEDULA, ""+cbTipoCedula.getSelectedItem()+tCedula.getText());
            mapaDatos.put(Representantes.KEY_PARENTESCO, ""+cbParentesco.getSelectedItem());
            if(tTelefono1.esTextoValido())  mapaDatos.put(Representantes.KEY_TELEFONO_1, tTelefono1.getText());
            if(tTelefono2.esTextoValido())  mapaDatos.put(Representantes.KEY_TELEFONO_2, tTelefono2.getText());
            if(tEMail.esTextoValido())  mapaDatos.put(Representantes.KEY_EMAIL, tEMail.getText());
            if(tEstado.esTextoValido())  mapaDatos.put(Representantes.KEY_ESTADO, tEstado.getText());
            if(tCiudad.esTextoValido())  mapaDatos.put(Representantes.KEY_CIUDAD, tCiudad.getText());
            if(taDireccion.esTextoValido())  mapaDatos.put(Representantes.KEY_DIRECCION, taDireccion.getText());
            
            return mapaDatos;
        }
        
        
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class BoxAntecedentes extends Box {
        
        private final HJTextField tTalla;
        private final HJTextField tPeso;
        private final HJTextField tC_Cefalica;
        private final HJTextField tC_Toracica;
        private final HJTextField tC_Abdominal;
        
        private final Box boxPersonales;
        private final JScrollPane scrollPersonales;
        
        private final Box boxFamiliares;
        private final JScrollPane scrollFamiliares;
        
        private final Box boxAlergias;
        private final JScrollPane scrollAlergias;
        
        private final Box boxIntervenciones;
        private final JScrollPane scrollIntervenciones;
        private final String TEXTO_INHABILITADO_INTERVENCIONES = "Debe asignar el tipo de intervención antes de escribir aquí.";
        
        private final Box boxHospitalizaciones;
        private final JScrollPane scrollHospitalizaciones;
        private final String TEXTO_INHABILITADO_HOSPITALIZACIONES = "Debe asignar el motivo de la hospitalización antes de escribir aquí.";
        
        private final JTabbedPane tabbedPanel;
        
        
        public BoxAntecedentes() {
            
            super(BoxLayout.Y_AXIS);
            
            
            JLabel lIconoAntecedentes = new JLabel(new IconoDeImagen("Antecedentes.png", -1, 40));
            lIconoAntecedentes.setFont(formatoTitulos);
            
            JPanel panelIcono = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelIcono.add(lIconoAntecedentes);
            panelIcono.add(Box.createHorizontalStrut(lIconoAntecedentes.getFontMetrics(lIconoAntecedentes.getFont()).stringWidth(ANTECEDENTES) + 70));
            panelIcono.setOpaque(false);
            
            
            JLabel lAntecedentes = new JLabel(ANTECEDENTES);
            lAntecedentes.setFont(formatoTitulos);
            
            JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
            panelTitulo.add(lAntecedentes);
            panelTitulo.setOpaque(false);
            
            JPanel panelTituloGeneral = new JPanel();
            panelTituloGeneral.setLayout(new OverlayLayout(panelTituloGeneral));
            panelTituloGeneral.add(panelIcono);
            panelTituloGeneral.add(panelTitulo);
            panelTituloGeneral.setMaximumSize(new Dimension(400, 40));
            panelTituloGeneral.setOpaque(false);
            
            
            //.... ANTECEDENTES NEONATALES ....
            
            tTalla = new HJTextField(5);
            tPeso = new HJTextField(5);
            
            JPanel panelTallaPeso = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelTallaPeso.add(new HJLabel("Talla: "));
            panelTallaPeso.add(tTalla);
            panelTallaPeso.add(new HJLabel(" cm."));
            panelTallaPeso.add(Box.createHorizontalStrut(70));
            panelTallaPeso.add(new HJLabel("Peso: "));
            panelTallaPeso.add(tPeso);
            panelTallaPeso.add(new HJLabel(" Kg."));
            panelTallaPeso.setPreferredSize(new Dimension(900, 30));
            panelTallaPeso.setOpaque(false);
            
            
            tC_Cefalica = new HJTextField(5);
            tC_Toracica = new HJTextField(5);
            tC_Abdominal = new HJTextField(5);
            
            JPanel panelCircunferencias = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelCircunferencias.add(new HJLabel("C. Cefálica: "));
            panelCircunferencias.add(tC_Cefalica);
            panelCircunferencias.add(new HJLabel(" cm."));
            panelCircunferencias.add(Box.createHorizontalStrut(50));
            panelCircunferencias.add(new HJLabel("C. Torácica: "));
            panelCircunferencias.add(tC_Toracica);
            panelCircunferencias.add(new HJLabel(" c.m."));
            panelCircunferencias.add(Box.createHorizontalStrut(50));
            panelCircunferencias.add(new HJLabel("C. Abdominal: "));
            panelCircunferencias.add(tC_Abdominal);
            panelCircunferencias.add(new HJLabel(" c.m."));
            panelCircunferencias.setPreferredSize(new Dimension(900, 30));
            panelCircunferencias.setOpaque(false);
            
            
            Box boxAntecedentesNeonatales = Box.createVerticalBox();
            boxAntecedentesNeonatales.add(Box.createVerticalStrut(100));
            boxAntecedentesNeonatales.add(panelTallaPeso);
            boxAntecedentesNeonatales.add(Box.createVerticalStrut(40));
            boxAntecedentesNeonatales.add(panelCircunferencias);
            
            HJPaintedPanel panelHorizontalNeonatales = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 0, 0), new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            panelHorizontalNeonatales.add(boxAntecedentesNeonatales);
            panelHorizontalNeonatales.setOpaque(false);
            
            
            //................................
            
            tabbedPanel = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
            
            tabbedPanel.add("Antecedentes Neonatales", panelHorizontalNeonatales);
            
            
            //.... ANTECEDENTES PERSONALES ....
            
            HJLabel lPatologiaPersonales = new HJLabel("Patología");
            lPatologiaPersonales.setForeground(colorAzulLlamativo);
            
            JPanel panelEtiquetasPersonales = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
            panelEtiquetasPersonales.add(Box.createHorizontalStrut(200));
            panelEtiquetasPersonales.add(lPatologiaPersonales);
            panelEtiquetasPersonales.add(Box.createHorizontalStrut(300));
            panelEtiquetasPersonales.add(new HJLabel("Detalles"));
            panelEtiquetasPersonales.setMaximumSize(new Dimension(1000, 35));
            panelEtiquetasPersonales.setOpaque(false);
            
            
            //....
            
            boxPersonales = Box.createVerticalBox();
            
            Box boxPersonalesGeneral = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            boxPersonalesGeneral.add(Box.createVerticalStrut(15));
            boxPersonalesGeneral.add(boxPersonales);
            
            scrollPersonales = new JScrollPane(boxPersonalesGeneral);
            scrollPersonales.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPersonales.getVerticalScrollBar().setUnitIncrement(10);
            
            
            //....
            
            HJButton bAnadirPersonales = new HJButton(new IconoDeImagen("Anadir.png", -1, 20), "Anadir", Colores.COLORES_BOTONES);
            bAnadirPersonales.addActionListener( e -> anadirPanelPersonales() );
            
            JPanel panelBotonAnadirPersonales = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
            panelBotonAnadirPersonales.add(bAnadirPersonales);
            panelBotonAnadirPersonales.setMaximumSize(new Dimension(100, 70));
            panelBotonAnadirPersonales.setOpaque(false);
            
            
            //....
            
            Box boxVerticalPersonales = Box.createVerticalBox();
            boxVerticalPersonales.add(panelEtiquetasPersonales);
            boxVerticalPersonales.add(scrollPersonales);
            boxVerticalPersonales.add(panelBotonAnadirPersonales);
            
            
            //................................
            
            tabbedPanel.add("Antecedentes Personales", boxVerticalPersonales);
            
            
            //.... ANTECEDENTES FAMILIARES ....
            
            HJLabel lPatologiaFamiliares = new HJLabel("Patología");
            lPatologiaFamiliares.setForeground(colorAzulLlamativo);
            
            JPanel panelEtiquetasFamiliares = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
            panelEtiquetasFamiliares.add(Box.createHorizontalStrut(200));
            panelEtiquetasFamiliares.add(lPatologiaFamiliares);
            panelEtiquetasFamiliares.add(Box.createHorizontalStrut(300));
            panelEtiquetasFamiliares.add(new HJLabel("Detalles"));
            panelEtiquetasFamiliares.setMaximumSize(new Dimension(1000, 35));
            panelEtiquetasFamiliares.setOpaque(false);
            
            
            //....
            
            boxFamiliares = Box.createVerticalBox();
            
            Box boxFamiliaresGeneral = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            boxFamiliaresGeneral.add(Box.createVerticalStrut(15));
            boxFamiliaresGeneral.add(boxFamiliares);
            
            scrollFamiliares = new JScrollPane(boxFamiliaresGeneral);
            scrollFamiliares.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollFamiliares.getVerticalScrollBar().setUnitIncrement(10);
            
            
            //....
            
            HJButton bAnadirFamiliares = new HJButton(new IconoDeImagen("Anadir.png", -1, 20), "Anadir", Colores.COLORES_BOTONES);
            bAnadirFamiliares.addActionListener( e -> anadirPanelFamiliares() );
            
            JPanel panelBotonAnadirFamiliares = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
            panelBotonAnadirFamiliares.add(bAnadirFamiliares);
            panelBotonAnadirFamiliares.setMaximumSize(new Dimension(100, 70));
            panelBotonAnadirFamiliares.setOpaque(false);
            
            
            //....
            
            Box boxVerticalFamiliares = Box.createVerticalBox();
            boxVerticalFamiliares.add(panelEtiquetasFamiliares);
            boxVerticalFamiliares.add(scrollFamiliares);
            boxVerticalFamiliares.add(panelBotonAnadirFamiliares);
            
            
            //.................
            
            tabbedPanel.add("Antecedentes Familiares", boxVerticalFamiliares);
            
            
            //.... ALERGIAS ...
            
            HJLabel lAlergias = new HJLabel("Alergias");
            lAlergias.setForeground(colorAzulLlamativo);
            
            JPanel panelEtiquetaAlergias = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
            panelEtiquetaAlergias.add(Box.createHorizontalStrut(390));
            panelEtiquetaAlergias.add(lAlergias);
            panelEtiquetaAlergias.setMaximumSize(new Dimension(1000, 35));
            panelEtiquetaAlergias.setOpaque(false);
            
            
            //....
            
            boxAlergias = Box.createVerticalBox();
            
            Box boxAlergiasGeneral = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            boxAlergiasGeneral.add(Box.createVerticalStrut(25));
            boxAlergiasGeneral.add(boxAlergias);
            
            scrollAlergias = new JScrollPane(boxAlergiasGeneral);
            scrollAlergias.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollAlergias.getVerticalScrollBar().setUnitIncrement(10);
            
            
            //....
            
            HJButton bAnadirAlergias = new HJButton(new IconoDeImagen("Anadir.png", -1, 20), "Anadir", Colores.COLORES_BOTONES);
            bAnadirAlergias.addActionListener( e -> anadirPanelAlergias() );
            
            JPanel panelBotonAnadirAlergias = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
            panelBotonAnadirAlergias.add(bAnadirAlergias);
            panelBotonAnadirAlergias.setMaximumSize(new Dimension(100, 70));
            panelBotonAnadirAlergias.setOpaque(false);
            
            
            //....
            
            Box boxVerticalAlergias = Box.createVerticalBox();
            boxVerticalAlergias.add(panelEtiquetaAlergias);
            boxVerticalAlergias.add(scrollAlergias);
            boxVerticalAlergias.add(panelBotonAnadirAlergias);
            
            
            //...................................
            
            tabbedPanel.add("Alergias", boxVerticalAlergias);
            
            
            //.... INTERVENCIONES QUIRÚRGICAS ....
            
            HJLabel lFechaIntervenciones = new HJLabel("Fecha");
            lFechaIntervenciones.setForeground(colorAzulLlamativo);
            
            HJLabel lTipo = new HJLabel("Tipo");
            lTipo.setForeground(colorAzulLlamativo);
            
            JPanel panelEtiquetasIntervenciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
            panelEtiquetasIntervenciones.add(Box.createHorizontalStrut(72));
            panelEtiquetasIntervenciones.add(lFechaIntervenciones);
            panelEtiquetasIntervenciones.add(Box.createHorizontalStrut(180));
            panelEtiquetasIntervenciones.add(lTipo);
            panelEtiquetasIntervenciones.add(Box.createHorizontalStrut(292));
            panelEtiquetasIntervenciones.add(new HJLabel("Detalles"));
            panelEtiquetasIntervenciones.setMaximumSize(new Dimension(1000, 35));
            panelEtiquetasIntervenciones.setOpaque(false);
            
            
            //....
            
            boxIntervenciones = Box.createVerticalBox();
            
            Box boxIntervencionesGeneral = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            boxIntervencionesGeneral.add(Box.createVerticalStrut(15));
            boxIntervencionesGeneral.add(boxIntervenciones);
            
            scrollIntervenciones = new JScrollPane(boxIntervencionesGeneral);
            scrollIntervenciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollIntervenciones.getVerticalScrollBar().setUnitIncrement(10);
            
            
            //....
            
            HJButton bAnadirIntervenciones = new HJButton(new IconoDeImagen("Anadir.png", -1, 20), "Anadir", Colores.COLORES_BOTONES);
            bAnadirIntervenciones.addActionListener( e -> anadirPanelIntervenciones() );
            
            JPanel panelBotonAnadirIntervenciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
            panelBotonAnadirIntervenciones.add(bAnadirIntervenciones);
            panelBotonAnadirIntervenciones.setMaximumSize(new Dimension(100, 70));
            panelBotonAnadirIntervenciones.setOpaque(false);
            
            
            //....
            
            Box boxVerticalIntervenciones = Box.createVerticalBox();
            boxVerticalIntervenciones.add(panelEtiquetasIntervenciones);
            boxVerticalIntervenciones.add(scrollIntervenciones);
            boxVerticalIntervenciones.add(panelBotonAnadirIntervenciones);
            
            
            //...........................
            
            tabbedPanel.add("Intervenciones Quirúrgicas", boxVerticalIntervenciones);
            
            
            //.... HOSPITALIZACIONES ....
            
            HJLabel lFechaHospitalizaciones = new HJLabel("Fecha");
            lFechaHospitalizaciones.setForeground(colorAzulLlamativo);
            
            HJLabel lMotivo = new HJLabel("Motivo");
            lMotivo.setForeground(colorAzulLlamativo);
            
            JPanel panelEtiquetasHospitalizaciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
            panelEtiquetasHospitalizaciones.add(Box.createHorizontalStrut(72));
            panelEtiquetasHospitalizaciones.add(lFechaHospitalizaciones);
            panelEtiquetasHospitalizaciones.add(Box.createHorizontalStrut(176));
            panelEtiquetasHospitalizaciones.add(lMotivo);
            panelEtiquetasHospitalizaciones.add(Box.createHorizontalStrut(277));
            panelEtiquetasHospitalizaciones.add(new HJLabel("Detalles"));
            panelEtiquetasHospitalizaciones.setMaximumSize(new Dimension(1000, 35));
            panelEtiquetasHospitalizaciones.setOpaque(false);
            
            
            //....
            
            boxHospitalizaciones = Box.createVerticalBox();
            
            Box boxHospitalizacionesGeneral = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(255,250,240), new Color(255,240,215), null, null, 100, true);
            boxHospitalizacionesGeneral.add(Box.createVerticalStrut(15));
            boxHospitalizacionesGeneral.add(boxHospitalizaciones);
            
            scrollHospitalizaciones = new JScrollPane(boxHospitalizacionesGeneral);
            scrollHospitalizaciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollHospitalizaciones.getVerticalScrollBar().setUnitIncrement(10);
            
            
            //....
            
            HJButton bAnadirHospitalizaciones = new HJButton(new IconoDeImagen("Anadir.png", -1, 20), "Anadir", Colores.COLORES_BOTONES);
            bAnadirHospitalizaciones.addActionListener( e -> anadirPanelHospitalizaciones() );
            
            JPanel panelBotonAnadirHospitalizaciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
            panelBotonAnadirHospitalizaciones.add(bAnadirHospitalizaciones);
            panelBotonAnadirHospitalizaciones.setMaximumSize(new Dimension(100, 70));
            panelBotonAnadirHospitalizaciones.setOpaque(false);
            
            
            //....
            
            Box boxVerticalHospitalizaciones = Box.createVerticalBox();
            boxVerticalHospitalizaciones.add(panelEtiquetasHospitalizaciones);
            boxVerticalHospitalizaciones.add(scrollHospitalizaciones);
            boxVerticalHospitalizaciones.add(panelBotonAnadirHospitalizaciones);
            
            
            //.........................
            
            tabbedPanel.add("Hospitalizaciones", boxVerticalHospitalizaciones);
            
            
            //.........................
            
            
            Box boxVertical = Box.createVerticalBox();
            boxVertical.add(Box.createVerticalStrut(10));
            boxVertical.add(panelTituloGeneral);
            boxVertical.add(Box.createVerticalStrut(10));
            boxVertical.add(tabbedPanel);
            
            
            add(boxVertical);
            
        }
        
        
        private void anadirPanelPersonales() {
            
            if(estanPanelesDeBoxListos(boxPersonales))
            {
                PanelPatologia panelPatologia = new PanelPatologia();
                panelPatologia.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelPatologia) );
                boxPersonales.add(panelPatologia);
                boxPersonales.validate();
                
                scrollPersonales.validate();
                scrollPersonales.getVerticalScrollBar().setValue(scrollPersonales.getVerticalScrollBar().getMaximum());
                
            }else{ HJDialog.mostrarMensaje("Casilla(s) Disponible(s)", new String[] {"Hay casilla(s) disponible(s) para llenar. Utilice la(s) misma(s)."}, IconoDeImagen.INFORMACION, null); }
        }
        
        
        private void anadirPanelFamiliares() {
            
            if(estanPanelesDeBoxListos(boxFamiliares))
            {
                PanelPatologia panelPatologia = new PanelPatologia();
                panelPatologia.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelPatologia) );
                boxFamiliares.add(panelPatologia);
                boxFamiliares.validate();
                
                scrollFamiliares.validate();
                scrollFamiliares.getVerticalScrollBar().setValue(scrollFamiliares.getVerticalScrollBar().getMaximum());
                
            }else{ HJDialog.mostrarMensaje("Casilla(s) Disponible(s)", new String[] {"Hay casilla(s) disponible(s) para llenar. Utilice la(s) misma(s)."}, IconoDeImagen.INFORMACION, null); }
        }
        
        
        private void anadirPanelAlergias() {
            
            if(estanPanelesDeBoxListos(boxAlergias))
            {
                PanelAlergia panelAlergia = new PanelAlergia();
                panelAlergia.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelAlergia) );
                boxAlergias.add(panelAlergia);
                boxAlergias.validate();
                
                scrollAlergias.validate();
                scrollAlergias.getVerticalScrollBar().setValue(scrollAlergias.getVerticalScrollBar().getMaximum());
                
            }else{ HJDialog.mostrarMensaje("Casilla(s) Disponible(s)", new String[] {"Hay casilla(s) disponible(s) para llenar. Utilice la(s) misma(s)."}, IconoDeImagen.INFORMACION, null); }
        }
        
        
        private void anadirPanelIntervenciones() {
            
            if(estanPanelesDeBoxListos(boxIntervenciones))
            {
                PanelFechaYDosCampos panelIntervencion = new PanelFechaYDosCampos(TEXTO_INHABILITADO_INTERVENCIONES);
                panelIntervencion.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelIntervencion) );
                boxIntervenciones.add(panelIntervencion);
                boxIntervenciones.validate();
                
                scrollIntervenciones.validate();
                scrollIntervenciones.getVerticalScrollBar().setValue(scrollIntervenciones.getVerticalScrollBar().getMaximum());
                
            }else{ HJDialog.mostrarMensaje("Fecha(s) y/o Tipo(s) No Establecidos", new String[] {"Todas las fechas y los tipos de las intervenciónes deben estar establecidos antes de añadir otra."}, IconoDeImagen.INFORMACION, null); }
        }
        
        
        private void anadirPanelHospitalizaciones() {
            
            if(estanPanelesDeBoxListos(boxHospitalizaciones))
            {
                PanelFechaYDosCampos panelHospitalizacion = new PanelFechaYDosCampos(TEXTO_INHABILITADO_HOSPITALIZACIONES);
                panelHospitalizacion.anadirEscuchadorBotonEliminar( e -> eliminarAntecedente(panelHospitalizacion) );
                boxHospitalizaciones.add(panelHospitalizacion);
                boxHospitalizaciones.validate();
                
                scrollHospitalizaciones.validate();
                scrollHospitalizaciones.getVerticalScrollBar().setValue(scrollHospitalizaciones.getVerticalScrollBar().getMaximum());
                
            }else{ HJDialog.mostrarMensaje("Fecha(s) y/o Tipo(s) No Establecidos", new String[] {"Todas las fechas y los motivos de las hospitalizaciones deben estar establecidos antes de añadir otra."}, IconoDeImagen.INFORMACION, null); }
        }
        
        
        private boolean estanAntecedentesListos() {
            
            if(tTalla.esNumerico()==false || tPeso.esNumerico()==false || tC_Cefalica.esNumerico()==false || tC_Toracica.esNumerico()==false || tC_Abdominal.esNumerico()==false)
            {
                HJDialog.mostrarMensaje("Error al Ingresar Datos en Antecedentes (Neonatales)", new String[] {"Los valores introducidos en 'Antecedentes Neonatales' deben ser numéricos (enteros o con decimales).", "Utilice el punto (.) para separar decimales.", "(Ejemplo: 32 o 32.4)"}, IconoDeImagen.ADVERTENCIA, null);
                
                bAntecedentes.doClick();
                
                tabbedPanel.setSelectedIndex(0);
                
                return false;
            }
            
            if(estanPanelesDeBoxListos(boxPersonales)==false)
            {
                HJDialog.mostrarMensaje("Datos Incompletos en Antecedentes (Personales)", new String[] {"Hay alguna(s) casilla(s) sin completar en 'Antecedentes Personales'.", "Por favor, complete al menos el campo 'Patología' o elimine el antecedente."}, IconoDeImagen.ADVERTENCIA, null);
                
                bAntecedentes.doClick();
                
                tabbedPanel.setSelectedIndex(1);
                
                return false;
            }
            
            if(estanPanelesDeBoxListos(boxFamiliares)==false)
            {
                HJDialog.mostrarMensaje("Datos Incompletos en Antecedentes (Familiares)", new String[] {"Hay alguna(s) casilla(s) sin completar en 'Antecedentes Familiares'.", "Por favor, complete al menos el campo 'Patología' o elimine el antecedente."}, IconoDeImagen.ADVERTENCIA, null);
                
                bAntecedentes.doClick();
                
                tabbedPanel.setSelectedIndex(2);
                
                return false;
            }
            
            if(estanPanelesDeBoxListos(boxAlergias)==false)
            {
                HJDialog.mostrarMensaje("Datos Incompletos en Antecedentes (Alergias)", new String[] {"Hay alguna(s) casilla(s) sin completar en 'Alergias'.", "Por favor, complétela(s) o elimínela(s)."}, IconoDeImagen.ADVERTENCIA, null);
                
                bAntecedentes.doClick();
                
                tabbedPanel.setSelectedIndex(3);
                
                return false;
            }
            
            if(estanPanelesDeBoxListos(boxIntervenciones)==false)
            {
                HJDialog.mostrarMensaje("Datos Incompletos en Antecedentes (Intervenciones Quirúrgicas)", new String[] {"Hay alguna(s) casilla(s) sin completar en 'Intervenciones Quirúrgicas'.", "Por favor, complete al menos el campo 'Fecha' y el campo 'Tipo' o elimine el antecedente."}, IconoDeImagen.ADVERTENCIA, null);
                
                bAntecedentes.doClick();
                
                tabbedPanel.setSelectedIndex(4);
                
                return false;
            }
            
            if(estanPanelesDeBoxListos(boxHospitalizaciones)==false)
            {
                HJDialog.mostrarMensaje("Datos Incompletos en Antecedentes (Hospitalizaciones)", new String[] {"Hay alguna(s) casilla(s) sin completar en 'Hospitalizaciones'.", "Por favor, complete al menos el campo 'Fecha' y el campo 'Motivo' o elimine el antecedente."}, IconoDeImagen.ADVERTENCIA, null);
                
                bAntecedentes.doClick();
                
                tabbedPanel.setSelectedIndex(5);
                
                return false;
            }
            
            
            return true;
        }
        
        
        private boolean estanPanelesDeBoxListos(Box boxDeAntecedentes) {
            
            Component[] componentes = boxDeAntecedentes.getComponents();
            
            if(componentes.length==0)
                return true;
            
            
            boolean estanListos = true;
            
            if(boxDeAntecedentes.equals(boxPersonales) || boxDeAntecedentes.equals(boxFamiliares))
            {
                for(int i=0 ; i<=componentes.length-1 ; i++)
                {
                    if(((PanelPatologia)componentes[i]).estaListo()==false)
                        estanListos = false;
                }
            }
            
            if(boxDeAntecedentes.equals(boxAlergias))
            {
                for(int i=0 ; i<=componentes.length-1 ; i++)
                {
                    if(((PanelAlergia)componentes[i]).estaListo()==false)
                        estanListos = false;
                }
            }
            
            if(boxDeAntecedentes.equals(boxIntervenciones) || boxDeAntecedentes.equals(boxHospitalizaciones))
            {
                for(int i=0 ; i<=componentes.length-1 ; i++)
                {
                    if(((PanelFechaYDosCampos)componentes[i]).estaListo()==false)
                        estanListos = false;
                }
            }
            
            
            return estanListos;
        }
        
        
        private void eliminarAntecedente(JPanel panelAntecedente) {
            
            Box boxContenedor = (Box)panelAntecedente.getParent();
            
            boxContenedor.remove(panelAntecedente);
            boxContenedor.validate();
            
            if(boxContenedor.equals(boxPersonales))
            {
                scrollPersonales.validate();
                scrollPersonales.repaint();
                
            }else{
                
                if(boxContenedor.equals(boxFamiliares))
                {
                    scrollFamiliares.validate();
                    scrollFamiliares.repaint();
                    
                }else{
                    
                    if(boxContenedor.equals(boxAlergias))
                    {
                        scrollAlergias.validate();
                        scrollAlergias.repaint();
                        
                    }else{
                        
                        if(boxContenedor.equals(boxIntervenciones))
                        {
                            scrollIntervenciones.validate();
                            scrollIntervenciones.repaint();
                            
                        }else{
                            
                            scrollHospitalizaciones.validate();
                            scrollHospitalizaciones.repaint();
                        }
                    }
                }
            }
        }
        
        
        public void eliminarTodosAntecedentes() {
            
            tTalla.limpiar();
            tPeso.limpiar();
            tC_Cefalica.limpiar();
            tC_Toracica.limpiar();
            tC_Abdominal.limpiar();
            
            boxPersonales.removeAll();
            boxPersonales.validate();
            scrollPersonales.validate();
            scrollPersonales.repaint();
            
            boxFamiliares.removeAll();
            boxFamiliares.validate();
            scrollFamiliares.validate();
            scrollFamiliares.repaint();
            
            boxAlergias.removeAll();
            boxAlergias.validate();
            scrollAlergias.validate();
            scrollAlergias.repaint();
            
            boxIntervenciones.removeAll();
            boxIntervenciones.validate();
            scrollIntervenciones.validate();
            scrollIntervenciones.repaint();
            
            boxHospitalizaciones.removeAll();
            boxHospitalizaciones.validate();
            scrollHospitalizaciones.validate();
            scrollHospitalizaciones.repaint();
        }
        
        
        public MapaDatos obtenerAntecedentesNeonatales() {
            
            MapaDatos mapaDatos = new MapaDatos();
            if(tTalla.esTextoValido())  mapaDatos.put(Neonatales.KEY_TALLA, tTalla.getText());
            if(tPeso.esTextoValido())  mapaDatos.put(Neonatales.KEY_PESO, tPeso.getText());
            if(tC_Cefalica.esTextoValido())  mapaDatos.put(Neonatales.KEY_C_CEFALICA, tC_Cefalica.getText());
            if(tC_Toracica.esTextoValido())  mapaDatos.put(Neonatales.KEY_C_TORACICA, tC_Toracica.getText());
            if(tC_Abdominal.esTextoValido())  mapaDatos.put(Neonatales.KEY_C_ABDOMINAL, tC_Abdominal.getText());
            
            return mapaDatos;
        }
        
        
        public HashMap<String,String> obtenerAntecedentesPersonales() {
            
            HashMap<String,String> datos = new HashMap<>(0);
            
            for(int i=0 ; i<=boxPersonales.getComponentCount()-1 ; i++)
            {
                PanelPatologia panelPatologia = (PanelPatologia)boxPersonales.getComponent(i);
                
                datos.put(panelPatologia.obtenerPatologia(), panelPatologia.obtenerDetalles());
            }
            
            return datos;
        }
        
        
        public HashMap<String,String> obtenerAntecedentesFamiliares() {
            
            HashMap<String,String> datos = new HashMap<>(0);
            
            for(int i=0 ; i<=boxFamiliares.getComponentCount()-1 ; i++)
            {
                PanelPatologia panelPatologia = (PanelPatologia)boxFamiliares.getComponent(i);
                
                datos.put(panelPatologia.obtenerPatologia(), panelPatologia.obtenerDetalles());
            }
            
            return datos;
        }
        
        
        public HashSet<String> obtenerAlergias() {
            
            HashSet<String> datos = new HashSet<>(0);
            
            for(int i=0 ; i<=boxAlergias.getComponentCount()-1 ; i++)
            {
                datos.add(((PanelAlergia)boxAlergias.getComponent(i)).obtenerAlergia());
            }
            
            return datos;
        }
        
        
        public HashMap<String,ArrayList<String>> obtenerIntervenciones() {
            
            HashMap<String,ArrayList<String>> datos = new HashMap<>(0);
            
            for(int i=0 ; i<=boxIntervenciones.getComponentCount()-1 ; i++)
            {
                PanelFechaYDosCampos panelIntervencion = (PanelFechaYDosCampos)boxIntervenciones.getComponent(i);
                
                ArrayList<String> array = new ArrayList<>(0);
                array.add(panelIntervencion.obtenerMesAno());
                array.add(panelIntervencion.obtenerCampo2());
                
                datos.put(panelIntervencion.obtenerCampo1(), array);
            }
            
            return datos;
        }
        
        
        public HashMap<String,ArrayList<String>> obtenerHospitalizaciones() {
            
            HashMap<String,ArrayList<String>> datos = new HashMap<>(0);
            
            for(int i=0 ; i<=boxHospitalizaciones.getComponentCount()-1 ; i++)
            {
                PanelFechaYDosCampos panelHospitalizacion = (PanelFechaYDosCampos)boxHospitalizaciones.getComponent(i);
                
                ArrayList<String> array = new ArrayList<>(0);
                array.add(panelHospitalizacion.obtenerMesAno());
                array.add(panelHospitalizacion.obtenerCampo2());
                
                datos.put(panelHospitalizacion.obtenerCampo1(), array);
            }
            
            return datos;
        }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
