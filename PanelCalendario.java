/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class PanelCalendario extends HJDialog {
    
    private final Font formatoCalendario = new Font("Arial", Font.BOLD, 18);
    
    private final LocalDate fechaActual = LocalDate.now();
    private LocalDate fechaSeleccionada = LocalDate.now();
    
    private BotonCalendario botonSeleccionado;
    
    private HashMap<String,String> mapaDia;
    
    private int ano;
    private final HJLabel lAno;
    
    private int mes;
    private final String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private final HJLabel lMes;
    
    private final int diaHoy;
    private final JPanel panelDias;
    
    private boolean estadoInicial = true;
    
    private String paciente = null;
    private final HJLabel lPaciente;
    private final String PACIENTE_NO_ESTABLECIDO = "(No Establecido)";
    
    private final HJButton bEstablecerPaciente;
    private final HJButton bRemoverPaciente;
    
    private final HJButton bAnotarCita;
    
    private final HJLabel lFecha;
    private final Box boxCitas;
    private final JScrollPane scrollCitas;
    
    private final HJButton bMarcarDiaLleno;
    private final HJButton bMarcarDiaNoLaborable;
    
    private final String MARCAR_DIA_LLENO = "Marcar Día 'Lleno'";
    private final String DESMARCAR_DIA_LLENO = "Desmarcar Día 'Lleno'";
    
    private final String MARCAR_DIA_NO_LABORABLE = "Marcar Día 'No Laborable'";
    private final String DESMARCAR_DIA_NO_LABORABLE = "Desmarcar Día 'No Laborable'";
    
    private final String DIA_LLENO = "Dia Lleno";
    private final String DIA_NO_LABORABLE = "Dia No Laborable";
    
    
    public PanelCalendario(String[] infoPaciente) {
        
        super(new IconoDeImagen("Calendario.png", -1, 30), "Calendario", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //...........................
        
        ano = fechaActual.getYear();
        mes = fechaActual.getMonthValue();
        diaHoy = fechaActual.getDayOfMonth();
        
        
        //...........................
        
        lAno = new HJLabel();
        lAno.setFont(formatoCalendario);
        lAno.setPreferredSize(new Dimension(50, 30));
        lAno.setHorizontalAlignment(HJLabel.CENTER);
        
        HJCustomizedButton bAnoIzquierda = new HJCustomizedButton("BotonIzquierdaNormal.png", "BotonIzquierdaRollover.png", 20, 30, false);
        bAnoIzquierda.addActionListener( e -> {
            
            ano--;
            
            refrescarCalendario();
        });
        
        HJCustomizedButton bAnoDerecha = new HJCustomizedButton("BotonDerechaNormal.png", "BotonDerechaRollover.png", 20, 30, false);
        bAnoDerecha.addActionListener( e -> {
            
            ano++;
            
            refrescarCalendario();
        });
        
        JPanel panelAno = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelAno.add(bAnoIzquierda);
        panelAno.add(lAno);
        panelAno.add(bAnoDerecha);
        panelAno.setOpaque(false);
        
        
        //....
        
        lMes = new HJLabel();
        lMes.setFont(new Font("Arial", Font.BOLD, 20));
        lMes.setPreferredSize(new Dimension(150, 40));
        lMes.setHorizontalAlignment(HJLabel.CENTER);
        
        HJCustomizedButton bMesIzquierda = new HJCustomizedButton("BotonIzquierdaNormal.png", "BotonIzquierdaRollover.png", 25, 30, false);
        bMesIzquierda.addActionListener( e -> {
            
            mes--;
            if(mes<1)
            {
                mes = 12;
                ano--;
            }
            
            refrescarCalendario();
        });
        
        HJCustomizedButton bMesDerecha = new HJCustomizedButton("BotonDerechaNormal.png", "BotonDerechaRollover.png", 25, 30, false);
        bMesDerecha.addActionListener( e -> {
            
            mes++;
            if(mes>12)
            {
                mes = 1;
                ano++;
            }
            
            refrescarCalendario();
        });
        
        HJPaintedPanel panelMes = new HJPaintedPanel(new FlowLayout(FlowLayout.CENTER, 0, 5), new Color(240,125,15), new Color(250,205,165), null, null, 50, false);
        panelMes.add(bMesIzquierda);
        panelMes.add(lMes);
        panelMes.add(bMesDerecha);
        
        
        //....
        
        int anchoCasillas = 60;
        int alturaCasillas = 40;
        
        
        HJLabel lLunes = new HJLabel("Lu");
        lLunes.setFont(formatoCalendario);
        lLunes.setHorizontalAlignment(HJLabel.CENTER);
        
        HJLabel lMartes = new HJLabel("Ma");
        lMartes.setFont(formatoCalendario);
        lMartes.setHorizontalAlignment(HJLabel.CENTER);
        
        HJLabel lMiercoles = new HJLabel("Mi");
        lMiercoles.setFont(formatoCalendario);
        lMiercoles.setHorizontalAlignment(HJLabel.CENTER);
        
        HJLabel lJueves = new HJLabel("Ju");
        lJueves.setFont(formatoCalendario);
        lJueves.setHorizontalAlignment(HJLabel.CENTER);
        
        HJLabel lViernes = new HJLabel("Vi");
        lViernes.setFont(formatoCalendario);
        lViernes.setHorizontalAlignment(HJLabel.CENTER);
        
        HJLabel lSabado = new HJLabel("Sa");
        lSabado.setFont(formatoCalendario);
        lSabado.setHorizontalAlignment(HJLabel.CENTER);
        
        HJLabel lDomingo = new HJLabel("Do");
        lDomingo.setFont(formatoCalendario);
        lDomingo.setForeground(new Color(215,15,20));
        lDomingo.setHorizontalAlignment(HJLabel.CENTER);
        
        JPanel panelEtiquetaDias = new JPanel(new GridLayout(1, 7, 0, 0));
        panelEtiquetaDias.add(lLunes);
        panelEtiquetaDias.add(lMartes);
        panelEtiquetaDias.add(lMiercoles);
        panelEtiquetaDias.add(lJueves);
        panelEtiquetaDias.add(lViernes);
        panelEtiquetaDias.add(lSabado);
        panelEtiquetaDias.add(lDomingo);
        panelEtiquetaDias.setPreferredSize(new Dimension(anchoCasillas*7, alturaCasillas));
        panelEtiquetaDias.setBackground(Colores.NORMAL);
        
        
        //....
        
        panelDias = new JPanel(new GridLayout(6, 7, 0, 0));
        panelDias.setPreferredSize(new Dimension(anchoCasillas*7, alturaCasillas*6));
        panelDias.setOpaque(false);
        
        panelDias.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //....
        
        Box boxCalendario = Box.createVerticalBox();
        boxCalendario.add(panelMes);
        boxCalendario.add(panelEtiquetaDias);
        boxCalendario.add(panelDias);
        
        boxCalendario.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        
        
        Box boxCalendarioMacro = Box.createVerticalBox();
        boxCalendarioMacro.add(panelAno);
        boxCalendarioMacro.add(Box.createVerticalStrut(10));
        boxCalendarioMacro.add(boxCalendario);
        
        
        JPanel panelCalendario = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelCalendario.add(boxCalendarioMacro);
        panelCalendario.setOpaque(false);
        
        
        //......................
        
        lPaciente = new HJLabel();
        lPaciente.setFont(formatoCalendario);
        lPaciente.setForeground(Colores.TEXTO);
        
        bEstablecerPaciente = new HJButton(new IconoDeImagen("Paciente.png", -1, 20), "Establecer", Colores.COLORES_BOTONES);
        bEstablecerPaciente.addActionListener( e -> mostrarDialogoEstablecerPaciente() );
        
        bRemoverPaciente = new HJButton(new IconoDeImagen("Eliminar.png", -1, 20), null, Colores.COLORES_BOTONES);
        bRemoverPaciente.addActionListener( e -> removerPaciente() );
        
        JPanel panelPaciente = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelPaciente.add(Box.createHorizontalStrut(20));
        panelPaciente.add(new HJLabel("Paciente: "));
        panelPaciente.add(lPaciente);
        panelPaciente.add(Box.createHorizontalStrut(20));
        panelPaciente.add(bEstablecerPaciente);
        panelPaciente.add(bRemoverPaciente);
        panelPaciente.add(Box.createHorizontalStrut(20));
        panelPaciente.setOpaque(false);
        
        
        //....
        
        bAnotarCita = new HJButton(new IconoDeImagen("AnotarCita.png", -1, 20), "Anotar Cita", Colores.COLORES_BOTONES);
        bAnotarCita.addActionListener( e -> mostrarDialogoHora() );
        
        JPanel panelBotonAnotarCita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelBotonAnotarCita.add(bAnotarCita);
        panelBotonAnotarCita.add(Box.createHorizontalStrut(20));
        panelBotonAnotarCita.setOpaque(false);
        
        
        //....
        
        Box boxPacienteBotonAnotarCita = Box.createHorizontalBox();
        boxPacienteBotonAnotarCita.add(panelPaciente);
        boxPacienteBotonAnotarCita.add(panelBotonAnotarCita);
        
        
        //.......................
        
        HJPaintedBox boxSuperior = new HJPaintedBox(BoxLayout.Y_AXIS, new Color(245,245,245), new Color(235,235,235), null, null, 100, true);
        boxSuperior.add(Box.createVerticalStrut(15));
        boxSuperior.add(panelCalendario);
        boxSuperior.add(Box.createVerticalStrut(15));
        boxSuperior.add(boxPacienteBotonAnotarCita);
        boxSuperior.add(Box.createVerticalStrut(15));
        
        
        //.......................
        
        HJLabel lHora = new HJLabel("Hora");
        lHora.setPreferredSize(new Dimension(101, 16));
        lHora.setHorizontalAlignment(HJLabel.CENTER);
        lHora.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Colores.LINEAS));
        
        JPanel panelCabeceraCitas = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        panelCabeceraCitas.add(lHora);
        panelCabeceraCitas.add(Box.createHorizontalStrut(50));
        panelCabeceraCitas.add(new HJLabel("Paciente"));
        panelCabeceraCitas.setOpaque(false);
        
        panelCabeceraCitas.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.LINEAS));
        
        
        boxCitas = Box.createVerticalBox();
        boxCitas.setOpaque(true);
        boxCitas.setBackground(Colores.NORMAL_OSCURO);
        
        scrollCitas = new JScrollPane(boxCitas);
        scrollCitas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollCitas.setPreferredSize(new Dimension(680, 200));
        scrollCitas.getVerticalScrollBar().setUnitIncrement(10);
        
        JPanel panelScrollCitas = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelScrollCitas.add(scrollCitas);
        panelScrollCitas.setOpaque(false);
        
        
        Box boxCitasMacro = Box.createVerticalBox();
        boxCitasMacro.add(panelCabeceraCitas);
        boxCitasMacro.add(panelScrollCitas);
        
        
        //....
        
        lFecha = new HJLabel();
        lFecha.setForeground(Colores.AZUL_LLAMATIVO);
        
        JPanel panelFecha = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        panelFecha.add(new HJLabel("Fecha: "));
        panelFecha.add(lFecha);
        panelFecha.setMaximumSize(new Dimension(400, 24));
        panelFecha.setOpaque(false);
        
        panelFecha.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 0, Colores.LINEAS));
        
        
        bMarcarDiaLleno = new HJButton(null, MARCAR_DIA_LLENO, Colores.COLORES_BOTONES);
        bMarcarDiaLleno.addActionListener( e -> {
            
            establecerDiaLleno(bMarcarDiaLleno.getText().equals(MARCAR_DIA_LLENO));
            
            if(bMarcarDiaLleno.getText().equals(MARCAR_DIA_LLENO))  bMarcarDiaLleno.setText(DESMARCAR_DIA_LLENO);
            else bMarcarDiaLleno.setText(MARCAR_DIA_LLENO);
        });
        
        JPanel panelMarcarDiaLleno = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelMarcarDiaLleno.add(bMarcarDiaLleno);
        panelMarcarDiaLleno.setOpaque(false);
        
        
        bMarcarDiaNoLaborable = new HJButton(null, MARCAR_DIA_NO_LABORABLE, Colores.COLORES_BOTONES);
        bMarcarDiaNoLaborable.addActionListener( e -> {
            
            establecerDiaNoLaborable(bMarcarDiaNoLaborable.getText().equals(MARCAR_DIA_NO_LABORABLE));
            
            if(bMarcarDiaNoLaborable.getText().equals(MARCAR_DIA_NO_LABORABLE))  bMarcarDiaNoLaborable.setText(DESMARCAR_DIA_NO_LABORABLE);
            else bMarcarDiaNoLaborable.setText(MARCAR_DIA_NO_LABORABLE);
        });
        
        JPanel panelMarcarDiaNoLaborable = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelMarcarDiaNoLaborable.add(bMarcarDiaNoLaborable);
        panelMarcarDiaNoLaborable.setOpaque(false);
        
        
        Box boxBotones = Box.createVerticalBox();
        boxBotones.add(panelFecha);
        boxBotones.add(Box.createVerticalStrut(50));
        boxBotones.add(panelMarcarDiaLleno);
        boxBotones.add(Box.createVerticalStrut(50));
        boxBotones.add(panelMarcarDiaNoLaborable);
        boxBotones.add(Box.createVerticalStrut(51));
        boxBotones.setPreferredSize(new Dimension(287, 227));
        
        
        //....
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelInferior.add(boxCitasMacro);
        panelInferior.add(boxBotones);
        panelInferior.setOpaque(false);
        
        panelInferior.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //......................
        
        if(infoPaciente==null)  removerPaciente();
        else  establecerPaciente(infoPaciente);
        
        
        refrescarCalendario();
        
        
        //......................
        
        
        Box cajaGeneral = Box.createVerticalBox();
        cajaGeneral.add(boxSuperior);
        cajaGeneral.add(panelInferior);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    //................................
    
    
    private void mostrarDialogoEstablecerPaciente() {
        
        int seleccion = HJDialog.mostrarDialogoPregunta("Seleccione", new String[] {"Seleccione el tipo de paciente:"}, new HJButton[] {new HJButton(new IconoDeImagen("Lista.png", -1, 20), "Paciente Registrado", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Paciente.png", -1, 20), "Paciente Nuevo", Colores.COLORES_BOTONES), new HJButton(null, "Cerrar", Colores.COLORES_BOTONES)}, 2, null, null);
        
        if(seleccion==0)
        {
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloPanelBuscar = new Thread( () -> {
                
                PanelBuscarPaciente panelBuscarPaciente = new PanelBuscarPaciente(true);
                
                panelEspera.setVisible(false);
                panelEspera.dispose();
                
                panelBuscarPaciente.setVisible(true);
                
                if(panelBuscarPaciente.estaPacienteSeleccionado())  establecerPaciente(new String[] {""+panelBuscarPaciente.obtenerNroHistoriaSeleccionado()});
            });
            hiloPanelBuscar.start();
            
            panelEspera.setVisible(true);
        }
        
        if(seleccion==1)
        {
            DialogoPacienteNuevo dialogoPacienteNuevo = new DialogoPacienteNuevo();
            dialogoPacienteNuevo.setVisible(true);
            
            String[] cadenaNombresApellidos = dialogoPacienteNuevo.obtenerNombres_Y_Apellidos();
            
            if(cadenaNombresApellidos!=null)  establecerPaciente(cadenaNombresApellidos);
        }
    }
    
    
    private void establecerPaciente(String[] infoPaciente) {
        
        if(infoPaciente.length>1)
        {
            paciente = infoPaciente[1]+", "+infoPaciente[0];
            
            lPaciente.setText(paciente);
            
        }else{
            
            paciente = infoPaciente[0];
            
            MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+paciente+".dda");
            
            lPaciente.setText(mapaDatos.get(Personales.KEY_APELLIDOS)+", "+mapaDatos.get(Personales.KEY_NOMBRES));
        }
        
        lPaciente.setForeground(Colores.AZUL_LLAMATIVO);
        
        bEstablecerPaciente.setVisible(false);
        bRemoverPaciente.setVisible(true);
        bAnotarCita.setEnabled(true);
    }
    
    
    private void removerPaciente() {
        
        paciente = null;
        
        lPaciente.setText(PACIENTE_NO_ESTABLECIDO);
        
        lPaciente.setForeground(Colores.TEXTO);
        
        bRemoverPaciente.setVisible(false);
        bEstablecerPaciente.setVisible(true);
        bAnotarCita.setEnabled(false);
    }
    
    
    //..................................
    
    private void mostrarDialogoHora() {
        
        if(fechaSeleccionada.isBefore(fechaActual))
        {
            HJDialog.mostrarMensaje("Fecha No Válida", new String[] {"La fecha de la cita debe ser igual a posterior a la actual."}, IconoDeImagen.ADVERTENCIA, null);
            
            return;
        }
        
        DialogoHora dialogoHora = new DialogoHora();
        dialogoHora.setVisible(true);
        
        if(dialogoHora.estaHoraEstablecida())
        {
            mapaDia.put(dialogoHora.obtenerHoraEstablecida(), paciente);
            
            guardarMapa();
            
            removerPaciente();
            
            botonSeleccionado.establecerConPacientes(true);
            
            botonSeleccionado.repaint();
            
            refrescarBoxCitas();
        }
    }
    
    
    //..................................
    
    private void establecerDiaLleno(boolean colocarLleno) {
        
        if(colocarLleno)  mapaDia.put(DIA_LLENO, null);    
        else  mapaDia.remove(DIA_LLENO);
        
        guardarMapa();
        
        botonSeleccionado.establecerLleno(colocarLleno);
        
        botonSeleccionado.repaint();
    }
    
    
    private void establecerDiaNoLaborable(boolean colocarNoLaborable) {
        
        if(colocarNoLaborable)  mapaDia.put(DIA_NO_LABORABLE, null);    
        else  mapaDia.remove(DIA_NO_LABORABLE);
        
        guardarMapa();
        
        botonSeleccionado.establecerNoLaborable(colocarNoLaborable);
        
        botonSeleccionado.repaint();
    }
    
    
    private void refrescarCalendario() {
        
        panelDias.removeAll();
        
        
        lAno.setText(""+ano);
        
        lMes.setText(meses[mes-1]);
        
        LocalDate mesSeleccionado = LocalDate.of(ano, mes, 1);
        
        int diaInicial = mesSeleccionado.getDayOfWeek().getValue();
        int diasTotales = mesSeleccionado.lengthOfMonth();
        
        
        for(int i=0 ; i<=diaInicial-2 ; i++)
        {
            BotonCalendario boton = new BotonCalendario(null);
            
            panelDias.add(boton);
        }
        
        HJButtonGroup grupoBotonesDias = new HJButtonGroup();
        
        for(int j=1 ; j<=diasTotales ; j++)
        {
            BotonCalendario boton = new BotonCalendario(""+j);
            
            colocarMarcas(boton, LocalDate.of(ano, mes, j));
            
            grupoBotonesDias.anadir(boton);
            
            if(estadoInicial)
            {
                if(j==diaHoy)  establecerBotonSeleccionado(boton);
                
            }else{
                
                if(j==1)  establecerBotonSeleccionado(boton);
            }
            
            panelDias.add(boton);
        }
        
        for(int i=diaInicial-1+diasTotales ; i<=41 ; i++)
        {
            BotonCalendario boton = new BotonCalendario(null);
            
            panelDias.add(boton);
        }
        
        
        estadoInicial = false;
        
        refrescarBoxCitas();
    }
    
    
    private void colocarMarcas(BotonCalendario boton, LocalDate fecha) {
        
        if(fecha.isEqual(fechaActual))  boton.establecerComoDiaActual(true);
        
        
        HashMap<String,String> esteMapaDia = obtenerMapaDia(fecha);
        
        boton.establecerNoLaborable(esteMapaDia.containsKey(DIA_NO_LABORABLE));
        
        boton.establecerLleno(esteMapaDia.containsKey(DIA_LLENO));
        
        boton.establecerConPacientes(hayPacientesEnMapa(esteMapaDia));  
        
        
        boton.repaint();
    }
    
    
    private boolean hayPacientesEnMapa(HashMap<String,String> esteMapaDia) {
        
        Set<String> keys = esteMapaDia.keySet();
        
        Iterator<String> iterador = keys.iterator();
        
        while(iterador.hasNext())
        {
            String posibleHora = iterador.next();
            
            if(posibleHora.equals(DIA_LLENO)==false && posibleHora.equals(DIA_NO_LABORABLE)==false)
                return true;
        }
        
        return false;
    }
    
    
    private void refrescarBoxCitas() {
        
        lFecha.setText(fechaSeleccionada.format(DateTimeFormatter.ofPattern("dd-MM-uuuu")));
        
        
        boxCitas.removeAll();
        
        
        ArrayList<String> array = new ArrayList<>(0);
        
        Set<String> keys = mapaDia.keySet();
        
        keys.stream()
                .sorted()
                .forEach( lt -> array.add(lt) );
        
        Iterator<String> iterador = array.iterator();
            
        while(iterador.hasNext())
        {
            String horaCita = iterador.next();
            
            if(horaCita.equals(DIA_LLENO) || horaCita.equals(DIA_NO_LABORABLE))  continue;
            
            
            PanelCita panelCita = new PanelCita(horaCita, mapaDia.get(horaCita));
            
            boxCitas.add(panelCita);
        }
        
        boxCitas.validate();
        scrollCitas.validate();
        scrollCitas.repaint();
    }
    
    
    private void establecerBotonSeleccionado(BotonCalendario boton) {
        
        fechaSeleccionada = LocalDate.of(ano, mes, boton.obtenerDia());
        
        mapaDia = obtenerMapaDia(fechaSeleccionada);
        
        botonSeleccionado = boton;
        
        botonSeleccionado.establecerSeleccionado(true);
        
        if(botonSeleccionado.estaLleno())  bMarcarDiaLleno.setText(DESMARCAR_DIA_LLENO);
        else bMarcarDiaLleno.setText(MARCAR_DIA_LLENO);  
        
        if(botonSeleccionado.esNoLaborable())  bMarcarDiaNoLaborable.setText(DESMARCAR_DIA_NO_LABORABLE);
        else bMarcarDiaNoLaborable.setText(MARCAR_DIA_NO_LABORABLE);
        
        refrescarBoxCitas();
    }
    
    
    private HashMap<String,String> obtenerMapaDia(LocalDate fecha) {
        
        HashMap<String,String> mapa = (HashMap<String,String>)Utilidades.leerArchivo(Directorios.CITAS+fecha.format(DateTimeFormatter.ofPattern("uuuu-MM-dd"))+".dda");
        
        if(mapa==null)  mapa = new HashMap<>(0);
        
        return mapa;
    }
    
    
    private void guardarMapa() {
        
        if(mapaDia.size()>0)
            Utilidades.guardarEnArchivo(Directorios.CITAS+fechaSeleccionada.format(DateTimeFormatter.ofPattern("uuuu-MM-dd"))+".dda", mapaDia);
        else
            Utilidades.eliminarArchivo(Directorios.CITAS+fechaSeleccionada.format(DateTimeFormatter.ofPattern("uuuu-MM-dd"))+".dda");
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class DialogoHora extends HJDialog {
        
        private final JSpinner spinnerHora;
        private final JSpinner spinnerMinutos;
        
        private final HJRadioButton rbAM;
        private final HJRadioButton rbPM;
        
        private String horaEstablecida = null;
        
        
        public DialogoHora() {
            
            super(new IconoDeImagen("Reloj.png", -1, 30), "Seleccione Hora", null, true);
            
            
            anadirActionListenerABotonCerrar( e -> {
                
                setVisible(false);
                dispose();
            });
            
            
            //.....................
            
            SpinnerNumberModel modeloNumeroSpinnerHora = new SpinnerNumberModel(new Integer(8), new Integer(1), new Integer(12), new Integer(1));
            
            spinnerHora = new JSpinner(modeloNumeroSpinnerHora);
            spinnerHora.setFont(new Font("Dialog", Font.BOLD, 16));
            
            
            SpinnerNumberModel modeloNumeroSpinnerMinutos = new SpinnerNumberModel(new Integer(0), new Integer(0), new Integer(59), new Integer(1));
            
            spinnerMinutos = new JSpinner(modeloNumeroSpinnerMinutos);
            spinnerMinutos.setFont(new Font("Dialog", Font.BOLD, 16));
            
            
            rbAM = new HJRadioButton("a.m.");
            
            rbPM = new HJRadioButton("p.m.");
            
            ButtonGroup grupoBotones = new ButtonGroup();
            grupoBotones.add(rbAM);
            grupoBotones.add(rbPM);
            
            rbAM.setSelected(true);
            
            
            JPanel panelBoxesHora = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelBoxesHora.add(Box.createHorizontalStrut(30));
            panelBoxesHora.add(crearBox(" ", new HJLabel("Hora de cita:")));
            panelBoxesHora.add(Box.createHorizontalStrut(10));
            panelBoxesHora.add(crearBox("hh:", spinnerHora));
            panelBoxesHora.add(Box.createHorizontalStrut(6));
            panelBoxesHora.add(crearBox("mm:", spinnerMinutos));
            panelBoxesHora.add(Box.createHorizontalStrut(10));
            panelBoxesHora.add(crearBox(" ", rbAM));
            panelBoxesHora.add(Box.createHorizontalStrut(10));
            panelBoxesHora.add(crearBox(" ", rbPM));
            panelBoxesHora.add(Box.createHorizontalStrut(30));
            panelBoxesHora.setOpaque(false);
            
            
            //........................
            
            HJButton bAceptar = new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Aceptar", Colores.COLORES_BOTONES);
            bAceptar.addActionListener( e -> establecerHora() );
            
            HJButton bCancelar = new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "Cancelar", Colores.COLORES_BOTONES);
            bCancelar.addActionListener( e -> {
                
                setVisible(false);
                dispose();
            });
            
            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
            panelBotones.add(bAceptar);
            panelBotones.add(bCancelar);
            panelBotones.setOpaque(false);
            
            
            //.........................
            
            
            Box cajaGeneral = Box.createVerticalBox();
            cajaGeneral.add(Box.createVerticalStrut(10));
            cajaGeneral.add(panelBoxesHora);
            cajaGeneral.add(panelBotones);
            
            
            add(cajaGeneral);
            
            
            pack();
            
            setLocationRelativeTo(null);
            
        }
        
        
        private Box crearBox(String etiqueta, JComponent componente) {
            
            JLabel lEtiqueta = new JLabel(etiqueta);
            lEtiqueta.setFont(new Font("Dialog", Font.PLAIN, 16));
            lEtiqueta.setForeground(Colores.TEXTO);
            
            JPanel panelEtiqueta = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelEtiqueta.add(lEtiqueta);
            panelEtiqueta.setOpaque(false);
            
            
            JPanel panelCampoNumero = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panelCampoNumero.add(componente);
            panelCampoNumero.setOpaque(false);
            
            
            Box boxVertical = Box.createVerticalBox();
            boxVertical.add(panelEtiqueta);
            boxVertical.add(Box.createVerticalStrut(5));
            boxVertical.add(panelCampoNumero);
            
            return boxVertical;
        }
        
        
        private void establecerHora() {
            
            int hora = Integer.parseInt(""+spinnerHora.getValue());
            
            String horaString;
            if(rbAM.isSelected())
            {
                if(hora==12)  hora = 0;
                
                if(hora<10)  horaString = "0"+hora;
                else  horaString = ""+hora;
                
            }else{
                
                if(hora<12)  hora += 12;
                
                horaString = ""+hora;
            }
            
            
            int minutos = Integer.parseInt(""+spinnerMinutos.getValue());
            
            String minutosString = ""+minutos;
            if(minutos<10)  minutosString = "0"+minutos;
            
            
            String horaSeleccionada = horaString+":"+minutosString;
            
            
            boolean estaDisponible = true;
            
            Set<String> horasOcupadas = mapaDia.keySet();
            
            if(horasOcupadas!=null)
            {
                Iterator<String> iterador = horasOcupadas.iterator();

                while(iterador.hasNext())
                {
                    String horaEnKeys = iterador.next();
                    
                    if(horaEnKeys!=null && horaEnKeys.equals(horaSeleccionada))  estaDisponible = false;
                }
            }
            
            
            if(estaDisponible)
            {
                horaEstablecida = horaSeleccionada;
                
                setVisible(false);
                dispose();
                
            }else{ HJDialog.mostrarMensaje("Hora Ocupada", new String[] {"Esta hora ya ha sido seleccionada. Por favor, elija otra."}, IconoDeImagen.ADVERTENCIA, null); }
        }
        
        
        public boolean estaHoraEstablecida() { return horaEstablecida!=null; }
        
        
        public String obtenerHoraEstablecida() { return horaEstablecida; }
        
        
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class BotonCalendario extends HJButton {
        
        private int numeroDia;
        
        private boolean diaActual = false;
        private boolean conPacientes = false;
        private boolean estaLleno = false;
        private boolean esNoLaborable = false;
        
        
        public BotonCalendario(String dia) {
            
            super(null, dia, new Font("Arial", Font.BOLD, 18), new Color[] {Colores.NORMAL, Colores.ROLLOVER, Colores.PRESSED}, false, 40, 40, null);
            
            
            //...................
            
            if(dia!=null)
            {
                numeroDia = Integer.parseInt(dia);
                
                convertirAToggleButton();
                
                addActionListener( e -> establecerBotonSeleccionado(obtenerEsteBotonCalendario()) );
            }
            
        }
        
        
        private BotonCalendario obtenerEsteBotonCalendario() { return this; }
        
        
        public int obtenerDia() { return numeroDia; }
        
        
        public void establecerComoDiaActual(boolean diaActual) { this.diaActual = diaActual; }
        
        public boolean esDiaActual() { return diaActual; }
        
        
        public void establecerConPacientes(boolean conPacientes) { this.conPacientes = conPacientes; }
        
        public boolean conPacientes() { return conPacientes; }
        
        
        public void establecerLleno(boolean estaLleno) { this.estaLleno = estaLleno; }
        
        public boolean estaLleno() { return estaLleno; }
        
        
        public void establecerNoLaborable(boolean esNoLaborable) { this.esNoLaborable = esNoLaborable; }
        
        public boolean esNoLaborable() { return esNoLaborable; }
        
        
        @Override
        public void paintComponent(Graphics g) {
            
            super.paintComponent(g);
            
            if(esNoLaborable)
            {
                g.setColor(new Color(215,15,20));
                
                g.fillRect(2, this.getHeight()-5, this.getWidth()-4, 3);
            }
            
            if(conPacientes)
            {
                g.setColor(new Color(50,155,240));
                
                g.fillRect(2, this.getHeight()-5, Math.round((float)(this.getWidth()-4)/(float)2.0), 3);
            }
            
            if(estaLleno)
            {
                if(esNoLaborable)  g.setColor(new Color(150,75,150));
                else   g.setColor(new Color(50,155,240));
                
                g.fillRect(2, this.getHeight()-5, this.getWidth()-4, 3);
            }
            
            if(diaActual)
            {
                Graphics2D g2D = (Graphics2D)g;
                
                g2D.setColor(new Color(240,125,15));
                
                g2D.setStroke(new BasicStroke((float)2.0, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
                
                g2D.drawRect(1, 1, this.getWidth()-2, this.getHeight()-2);
                
                g2D.dispose();
            }
        }
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class PanelCita extends Box {
        
        private int nroHistoriaEstaPanel;
        private final String horaCitaEstePanel;
        
        
        public PanelCita(String horaCita, String paciente) {
            
            super(BoxLayout.X_AXIS);
            
            
            //.....................
            
            horaCitaEstePanel = horaCita;
            
            
            //.....................
            
            LocalTime horaMinuto = LocalTime.parse(horaCitaEstePanel);
            
            
            int hora = horaMinuto.getHour();
            
            String AM_PM = "a.m.";
            if(hora>12)
            {
                hora -= 12;
                AM_PM = "p.m.";
            }
            
            if(hora==0)  hora = 12;
            
            
            int minutos = horaMinuto.getMinute();
            
            String minutosString = ""+minutos;
            if(minutos<10)  minutosString = "0"+minutos;
            
            
            HJLabel lHora = new HJLabel(""+hora+":"+minutosString+" "+AM_PM);
            lHora.setPreferredSize(new Dimension(100, 30));
            lHora.setHorizontalAlignment(HJLabel.CENTER);
            lHora.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Colores.LINEAS));
            
            HJLabel lApellidos_Y_Nombres = new HJLabel();
            lApellidos_Y_Nombres.setPreferredSize(new Dimension(400, 30));
            
            
            JPanel panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
            
            JPanel panelDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
            
            if(Utilidades.esNumeroEnteroPositivo(paciente))
            {
                nroHistoriaEstaPanel = Integer.parseInt(paciente);
                
                
                MapaDatos mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+paciente+".dda");
                
                lApellidos_Y_Nombres.setText(mapaDatos.get(Personales.KEY_APELLIDOS)+", "+mapaDatos.get(Personales.KEY_NOMBRES));
                
                
                HJButton bVerHistoria = new HJButton(new IconoDeImagen("Historia.png", -1, 20), null, Colores.COLORES_BOTONES);
                bVerHistoria.addActionListener( e -> mostrarPanelHistoria() );
                
                panelDerecho.add(bVerHistoria);
                
            }else{
                
                lApellidos_Y_Nombres.setText(paciente);
            }
            
            HJButton bEliminar = new HJButton(new IconoDeImagen("Eliminar.png", -1, 20), null, Colores.COLORES_BOTONES);
            bEliminar.addActionListener( e -> eliminarEstaCita() );
            
            
            panelIzquierdo.add(lHora);
            panelIzquierdo.add(Box.createHorizontalStrut(20));
            panelIzquierdo.add(lApellidos_Y_Nombres);
            panelIzquierdo.setOpaque(false);
            
            panelDerecho.add(bEliminar);
            panelDerecho.setOpaque(false);
            
            
            add(panelIzquierdo);
            add(Box.createHorizontalStrut(10));
            add(panelDerecho);
            add(Box.createHorizontalStrut(10));
            
            
            setMaximumSize(new Dimension(700, 51));
            
            setOpaque(true);
            setBackground(Colores.NORMAL);
            
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.LINEAS));
            
        }
        
        
        private void mostrarPanelHistoria() {
            
            HJWaitingPanel panelEspera = new HJWaitingPanel();
            
            Thread hiloPanelHistoria = new Thread( () -> {
                
                PanelHistoria panelHistoria = new PanelHistoria(nroHistoriaEstaPanel);

                panelEspera.setVisible(false);
                panelEspera.dispose();

                panelHistoria.setVisible(true);
            });
            hiloPanelHistoria.start();

            panelEspera.setVisible(true);
        }
        
        
        private void eliminarEstaCita() {
            
            if(HJDialog.mostrarDialogoPregunta("Confirmación", new String[] {"¿Está seguro(a) de que desea eliminar esta cita?"}, new HJButton[] {new HJButton(new IconoDeImagen("Aceptar.png", -1, 20), "Sí", Colores.COLORES_BOTONES), new HJButton(new IconoDeImagen("Cancelar.png", -1, 20), "No", Colores.COLORES_BOTONES)}, 1, IconoDeImagen.ADVERTENCIA, null)==0)
            {
                mapaDia.remove(horaCitaEstePanel);
                
                guardarMapa();
                
                botonSeleccionado.establecerConPacientes(hayPacientesEnMapa(mapaDia));
                
                botonSeleccionado.repaint();
                
                refrescarBoxCitas();
            }
        }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
