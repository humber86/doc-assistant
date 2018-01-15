/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PanelEstadisticas extends HJDialog {
    
    private final String DIAGNOSTICO = "Diagnóstico";
    private final String DIAGNOSTICO_ULTIMA_CITA = "Diagnóstico en Última Cita";
    private final String GENERO = "Género";
    private final String GRUPO_ETARIO = "Grupo Etario";
    private final String MEDICAMENTO_RECETADO = "Medicamento Recetado";
    private final String MEDICAMENTO_RECETADO_ULT_CITA = "Medicamento Recetado en Últ. Cita";
    private final String MOTIVO_HOSPITALIZACION = "Motivo de Hospitalización";
    private final String TIPO_INTERVENCION_QUIRURGICA = "Tipo de Intervención Quirúrgica";
    
    private final String FEMENINO = "Femenino";
    private final String MASCULINO = "Masculino";
    
    private final String LACTANTE_MENOR = "Lactante menor (0-11 meses)";
    private final String LACTANTE_MAYOR = "Lactante mayor (12-23 meses)";
    private final String PREESCOLAR = "Preescolar (2-5 años)";
    private final String ESCOLAR = "Escolar (6-13 años)";
    private final String ADOLESCENTE = "Adolescente (14-17 años)";
    private final String ADULTO_JOVEN = "Adulto joven (18-34 años)";
    private final String ADULTO_MADURO = "Adulto maduro (35-64 años)";
    private final String ADULTO_MAYOR = "Adulto mayor (65 años o más)";
    
    private final Box boxCampos;
    private final JScrollPane scrollCampos;
    private final JPanel panelBotonGraficar;
    
    private final HJLabel lNombreGrafica;
    
    private final Box boxLeyendaIzquierdo;
    private final Box boxLeyendaDerecho;
    
    private final HJBarGraph graficaBarra;
    private final HJFrequencyChart tablaFrecuencia;
    private final HJCakeGraph graficaTorta;
    
    
    public PanelEstadisticas() {
        
        super(new IconoDeImagen("Estadisticas.png", -1, 30), "Estadísticas", null, true);
        
        
        anadirActionListenerABotonCerrar( e -> {
            
            setVisible(false);
            dispose();
        });
        
        
        //...............................
        
        graficaBarra = new HJBarGraph(2, 26);
        
        tablaFrecuencia = new HJFrequencyChart();
        
        graficaTorta = new HJCakeGraph(150, 60, 30);
        
        
        //...............................
        
        JPanel panelGraficaSegun = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelGraficaSegun.add(new HJLabel("Gráfica según:"));
        panelGraficaSegun.setOpaque(false);
        
        HJComboBox<String> cbTipoGrafica = new HJComboBox<>();
        cbTipoGrafica.addItem(DIAGNOSTICO);
        cbTipoGrafica.addItem(DIAGNOSTICO_ULTIMA_CITA);
        cbTipoGrafica.addItem(GENERO);
        cbTipoGrafica.addItem(GRUPO_ETARIO);
        cbTipoGrafica.addItem(MEDICAMENTO_RECETADO);
        cbTipoGrafica.addItem(MEDICAMENTO_RECETADO_ULT_CITA);
        cbTipoGrafica.addItem(MOTIVO_HOSPITALIZACION);
        cbTipoGrafica.addItem(TIPO_INTERVENCION_QUIRURGICA);
        
        JPanel panelTipoGrafica = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelTipoGrafica.add(cbTipoGrafica);
        panelTipoGrafica.setOpaque(false);
        
        Box boxTipoGrafica = Box.createVerticalBox();
        boxTipoGrafica.add(panelGraficaSegun);
        boxTipoGrafica.add(Box.createVerticalStrut(5));
        boxTipoGrafica.add(panelTipoGrafica);
        boxTipoGrafica.setMaximumSize(new Dimension(328, 50));
        
        
        boxCampos = Box.createVerticalBox();
        
        scrollCampos = new JScrollPane(boxCampos);
        scrollCampos.setPreferredSize(new Dimension(323, 440));
        scrollCampos.getVerticalScrollBar().setUnitIncrement(10);
        scrollCampos.setOpaque(false);
        scrollCampos.getViewport().setOpaque(false);
        
        Box boxCamposMacro = Box.createVerticalBox();
        boxCamposMacro.add(Box.createVerticalStrut(10));
        boxCamposMacro.add(scrollCampos);
        
        
        HJButton bGraficar = new HJButton(null, "Graficar", Colores.COLORES_BOTONES);
        bGraficar.addActionListener( e -> {
            
            String tipoGrafica = ""+cbTipoGrafica.getSelectedItem();
            
            switch(tipoGrafica)
            {
                case DIAGNOSTICO : graficarDiagnostico();
                                   break;
                
                case DIAGNOSTICO_ULTIMA_CITA : graficarDiagnosticoUltimaCita();
                                               break;
                
                case MEDICAMENTO_RECETADO : graficarMedicamentoRecetado();
                                            break;
                
                case MEDICAMENTO_RECETADO_ULT_CITA : graficarMedicamentoRecetadoUltCita();
                                               break;
                
                case MOTIVO_HOSPITALIZACION : graficarMotivoHospitalizacion();
                                              break;
                
                case TIPO_INTERVENCION_QUIRURGICA : graficarTipoIntervencionQuirurgica();
                                    break;
                
                default: break;
            }
        });
        
        panelBotonGraficar = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        panelBotonGraficar.add(bGraficar);
        panelBotonGraficar.setOpaque(false);
        
        
        Box boxIzquierdo = Box.createVerticalBox();
        boxIzquierdo.add(Box.createVerticalStrut(20));
        boxIzquierdo.add(boxTipoGrafica);
        boxIzquierdo.add(Box.createVerticalStrut(10));
        boxIzquierdo.add(boxCamposMacro);
        boxIzquierdo.add(panelBotonGraficar);
        boxIzquierdo.add(Box.createHorizontalStrut(328));
        
        boxIzquierdo.setOpaque(true);
        boxIzquierdo.setBackground(Colores.NORMAL_OSCURO);
        
        
        //...............................
        
        HJLabel lGrafica = new HJLabel("Gráfica: ");
        lGrafica.setFont(new Font("Dialog", Font.PLAIN, 22));
        
        lNombreGrafica = new HJLabel();
        lNombreGrafica.setFont(new Font("Arial", Font.BOLD, 22));
        
        JPanel panelNombreGrafica = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelNombreGrafica.add(lGrafica);
        panelNombreGrafica.add(lNombreGrafica);
        panelNombreGrafica.setOpaque(false);
        
        Box boxNombreGrafica = Box.createVerticalBox();
        boxNombreGrafica.add(Box.createVerticalStrut(20));
        boxNombreGrafica.add(panelNombreGrafica);
        
        
        //....
        
        cbTipoGrafica.addItemListener( e -> {
            
            String tipo = ""+cbTipoGrafica.getSelectedItem();
            
            lNombreGrafica.setText(tipo);
            
            if(tipo.equals(MEDICAMENTO_RECETADO) || tipo.equals(MEDICAMENTO_RECETADO_ULT_CITA))
            {
                mostrarCasillas(true, true);
                
            }else{
                
                if(tipo.equals(GENERO))
                {
                    mostrarCasillas(false, false);
                    graficarGenero();
                    
                }else{
                    
                    if(tipo.equals(GRUPO_ETARIO))
                    {
                        mostrarCasillas(false, false);
                        graficarGrupoEtario();
                        
                    }else{
                        
                        mostrarCasillas(true, false);
                    }
                }
            }
        });
        
        
        //....
        
        
        JPanel panelGraficas = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        panelGraficas.add(graficaBarra);
        panelGraficas.add(tablaFrecuencia);
        panelGraficas.add(graficaTorta);
        panelGraficas.setOpaque(false);
        
        
        boxLeyendaIzquierdo = Box.createVerticalBox();
        
        boxLeyendaDerecho = Box.createVerticalBox();
        
        JPanel panelLeyenda = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        panelLeyenda.add(boxLeyendaIzquierdo);
        panelLeyenda.add(boxLeyendaDerecho);
        panelLeyenda.setPreferredSize(new Dimension(787, 166));
        panelLeyenda.setBackground(Colores.NORMAL_OSCURO);
        
        panelLeyenda.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Colores.LINEAS));
        
        
        //....
        
        JPanel panelDerecho = new JPanel(new BorderLayout(0, 0));
        panelDerecho.add(boxNombreGrafica, BorderLayout.NORTH);
        panelDerecho.add(panelGraficas, BorderLayout.CENTER);
        panelDerecho.add(panelLeyenda, BorderLayout.SOUTH);
        panelDerecho.setOpaque(false);
        
        panelDerecho.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Colores.LINEAS));
        
        
        //.........................
        
        cbTipoGrafica.setSelectedItem(GRUPO_ETARIO);
        
        
        //.........................
        
        
        Box cajaGeneral = Box.createHorizontalBox();
        cajaGeneral.add(boxIzquierdo);
        cajaGeneral.add(panelDerecho);
        
        
        add(cajaGeneral);
        
        
        pack();
        
        setLocationRelativeTo(null);
        
    }
    
    
    //.....................................
    
    
    private void mostrarCasillas(boolean seMuestran, boolean sonMedicamentos) {
        
        graficaBarra.setVisible(false);
        tablaFrecuencia.setVisible(false);
        graficaTorta.setVisible(false);
        
        
        boxLeyendaIzquierdo.removeAll();
        boxLeyendaDerecho.removeAll();
        
        boxLeyendaIzquierdo.validate();
        boxLeyendaDerecho.validate();
        
        
        if(seMuestran)
        {
            boxCampos.removeAll();
            
            Color[] colores = HJFrequencyChart.obtenerColoresPorDefecto();
            
            for(int i=0 ; i<=7 ; i++)
            {
                boxCampos.add(new Campo(colores[i], sonMedicamentos));
            }
            
            boxCampos.validate();
            scrollCampos.validate();
            scrollCampos.repaint();
            scrollCampos.getVerticalScrollBar().setValue(scrollCampos.getVerticalScrollBar().getMinimum());
            
            boxCampos.setVisible(true);
            scrollCampos.setVisible(true);
            panelBotonGraficar.setVisible(true);
            
        }else{
            
            boxCampos.setVisible(false);
            scrollCampos.setVisible(false);
            panelBotonGraficar.setVisible(false);
        }
    }
    
    
    //................................
    
    private void graficarDiagnostico() {
        
        int[] diagnosticos = {-1, -1, -1, -1, -1, -1, -1, -1};
        
        for(int i=0 ; i<=boxCampos.getComponentCount()-1 ; i++)
        {
            if(((Campo)boxCampos.getComponent(i)).estaListo())  diagnosticos[i] = 0;
        }
        
        
        String[] listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        String historia;
        
        String[] consultas;
        
        MapaDatos mapaConsulta;
        
        String diagnostico;
        
        Campo campo;
        
        ArrayList<Integer> arrayHistoriasDiagnostico0 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasDiagnostico1 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasDiagnostico2 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasDiagnostico3 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasDiagnostico4 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasDiagnostico5 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasDiagnostico6 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasDiagnostico7 = new ArrayList<>(0);
        
        HashMap<Integer,ArrayList<Integer>> mapaDiagnosticosHistorias = new HashMap<>(0);
        mapaDiagnosticosHistorias.put(0, arrayHistoriasDiagnostico0);
        mapaDiagnosticosHistorias.put(1, arrayHistoriasDiagnostico1);
        mapaDiagnosticosHistorias.put(2, arrayHistoriasDiagnostico2);
        mapaDiagnosticosHistorias.put(3, arrayHistoriasDiagnostico3);
        mapaDiagnosticosHistorias.put(4, arrayHistoriasDiagnostico4);
        mapaDiagnosticosHistorias.put(5, arrayHistoriasDiagnostico5);
        mapaDiagnosticosHistorias.put(6, arrayHistoriasDiagnostico6);
        mapaDiagnosticosHistorias.put(7, arrayHistoriasDiagnostico7);
        
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            historia = (listaHistorias[i].split(".dda"))[0];
            
            consultas = (new File(Directorios.CONSULTAS+historia)).list();
            
            if(consultas==null)  continue;
            
            for(int j=0 ; j<=consultas.length-1 ; j++)
            {
                mapaConsulta = (MapaDatos)Utilidades.leerArchivo(Directorios.CONSULTAS+historia+"\\"+consultas[j]);
                
                diagnostico = mapaConsulta.get(Consulta.KEY_DIAGNOSTICO);
                
                for(int k=0 ; k<=boxCampos.getComponentCount()-1 ; k++)
                {
                    campo = (Campo)boxCampos.getComponent(k);
                    
                    if(campo.estaListo()==false)  continue;
                    
                    if(diagnostico.toUpperCase().contains(campo.obtenerValorCampo().toUpperCase()))
                        mapaDiagnosticosHistorias.get(k).add(Integer.parseInt(historia));
                }
            }
        }
        
        
        if(diagnosticos[0]>=0)  diagnosticos[0] = (int)arrayHistoriasDiagnostico0.stream().distinct().count();
        if(diagnosticos[1]>=0)  diagnosticos[1] = (int)arrayHistoriasDiagnostico1.stream().distinct().count();
        if(diagnosticos[2]>=0)  diagnosticos[2] = (int)arrayHistoriasDiagnostico2.stream().distinct().count();
        if(diagnosticos[3]>=0)  diagnosticos[3] = (int)arrayHistoriasDiagnostico3.stream().distinct().count();
        if(diagnosticos[4]>=0)  diagnosticos[4] = (int)arrayHistoriasDiagnostico4.stream().distinct().count();
        if(diagnosticos[5]>=0)  diagnosticos[5] = (int)arrayHistoriasDiagnostico5.stream().distinct().count();
        if(diagnosticos[6]>=0)  diagnosticos[6] = (int)arrayHistoriasDiagnostico6.stream().distinct().count();
        if(diagnosticos[7]>=0)  diagnosticos[7] = (int)arrayHistoriasDiagnostico7.stream().distinct().count();
        
        
        graficaBarra.setVisible(false);
        
        graficaBarra.graficar(diagnosticos, listaHistorias.length, HJBarGraph.obtenerColoresIluminadosPorDefecto(), HJBarGraph.obtenerColoresConSombraPorDefecto());
        
        graficaBarra.setVisible(true);
        
        colocarLeyenda();
    }
    
    
    private void graficarDiagnosticoUltimaCita() {
        
        int[] diagnosticos = {-1, -1, -1, -1, -1, -1, -1, -1};
        
        for(int i=0 ; i<=boxCampos.getComponentCount()-1 ; i++)
        {
            if(((Campo)boxCampos.getComponent(i)).estaListo())  diagnosticos[i] = 0;
        }
        
        
        String[] listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        String historia;
        
        String ultimaConsulta;
        
        MapaDatos mapaConsulta;
        
        String diagnostico;
        
        Campo campo;
        
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            historia = (listaHistorias[i].split(".dda"))[0];
            
            ultimaConsulta = Utilidades.obtenerNombreUltimoArchivo(Directorios.CONSULTAS+historia);
            
            if(ultimaConsulta==null)  continue;
            
            mapaConsulta = (MapaDatos)Utilidades.leerArchivo(Directorios.CONSULTAS+historia+"\\"+ultimaConsulta);
            
            diagnostico = mapaConsulta.get(Consulta.KEY_DIAGNOSTICO);
            
            for(int k=0 ; k<=boxCampos.getComponentCount()-1 ; k++)
            {
                campo = (Campo)boxCampos.getComponent(k);
                
                if(campo.estaListo()==false)  continue;
                
                if(diagnostico.toUpperCase().contains(campo.obtenerValorCampo().toUpperCase()))  diagnosticos[k] += 1;
            }
        }
        
        
        graficaBarra.setVisible(false);
        
        graficaBarra.graficar(diagnosticos, listaHistorias.length, HJBarGraph.obtenerColoresIluminadosPorDefecto(), HJBarGraph.obtenerColoresConSombraPorDefecto());
        
        graficaBarra.setVisible(true);
        
        colocarLeyenda();
    }
    
    
    private void graficarGenero() {
        
        int femenino = 0;
        int masculino = 0;
        
        String[] listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        MapaDatos mapaDatos;
        
        String genero;
        
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+listaHistorias[i]);
            
            genero = mapaDatos.get(Personales.KEY_GENERO);
            
            if(genero.toUpperCase().equals(FEMENINO.toUpperCase()))  femenino++;
            else  masculino++;
        }
        
        
        double[] cantidades = {femenino, masculino};
        
        graficaTorta.setVisible(false);
        
        graficaTorta.graficar(cantidades, listaHistorias.length, HJCakeGraph.obtenerColoresIluminadosPorDefecto(), HJCakeGraph.obtenerColoresConSombraPorDefecto());
        
        int[] cantidadesEnteros = {femenino, masculino};
        
        tablaFrecuencia.setVisible(false);
        
        tablaFrecuencia.mostrarDatos(cantidadesEnteros, listaHistorias.length, HJFrequencyChart.obtenerColoresPorDefecto());
        
        tablaFrecuencia.setVisible(true);
        graficaTorta.setVisible(true);
        
        colocarLeyenda();
    }
    
    
    private void graficarGrupoEtario() {
        
        int lactanteMenor = 0;
        int lactanteMayor = 0;
        int preescolar = 0;
        int escolar = 0;
        int adolescente = 0;
        int adultoJoven = 0;
        int adultoMaduro = 0;
        int adultoMayor = 0;
        
        String[] listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        MapaDatos mapaDatos;
        
        int edad;
        
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            mapaDatos = (MapaDatos)Utilidades.leerArchivo(Directorios.DATOS_PERSONALES+listaHistorias[i]);
            
            edad = Utilidades.obtenerEdadEntero(mapaDatos.get(Personales.KEY_FECHA_NACIMIENTO));
            
            if(edad<1)
            {
                lactanteMenor++;
            }else{
                if(edad>=1 && edad<2)
                {
                    lactanteMayor++;
                }else{
                    if(edad>=2 && edad<6)
                    {
                        preescolar++;
                    }else{
                        if(edad>=6 && edad<14)
                        {
                            escolar++;
                        }else{
                            if(edad>=14 && edad<18)
                            {
                                adolescente++;
                            }else{
                                if(edad>=18 && edad<35)
                                {
                                    adultoJoven++;
                                }else{
                                    if(edad>=35 && edad<65)  adultoMaduro++;
                                    else  adultoMayor++;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        double[] cantidades = {lactanteMenor, lactanteMayor, preescolar, escolar, adolescente, adultoJoven, adultoMaduro, adultoMayor};
        
        graficaTorta.setVisible(false);
        
        graficaTorta.graficar(cantidades, listaHistorias.length, HJCakeGraph.obtenerColoresIluminadosPorDefecto(), HJCakeGraph.obtenerColoresConSombraPorDefecto());
        
        int[] cantidadesEnteros = {lactanteMenor, lactanteMayor, preescolar, escolar, adolescente, adultoJoven, adultoMaduro, adultoMayor};
        
        tablaFrecuencia.setVisible(false);
        
        tablaFrecuencia.mostrarDatos(cantidadesEnteros, listaHistorias.length, HJFrequencyChart.obtenerColoresPorDefecto());
        
        tablaFrecuencia.setVisible(true);
        graficaTorta.setVisible(true);
        
        colocarLeyenda();
    }
    
    
    private void graficarMedicamentoRecetado() {
        
        int[] medicamentos = {-1, -1, -1, -1, -1, -1, -1, -1};
        
        for(int i=0 ; i<=boxCampos.getComponentCount()-1 ; i++)
        {
            if(((Campo)boxCampos.getComponent(i)).estaListo())  medicamentos[i] = 0;
        }
        
        
        String[] listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        String historia;
        
        String[] gruposDeMedicamentos;
        
        HashMap<String,HashMap<String,String>> mapaMedicamentos;
        
        Iterator<String> iteradorMedicamentos;
        
        String medicamento;
        
        HashMap<String,String> mapaMedicamento;
        
        Campo campo;
        
        ArrayList<Integer> arrayHistoriasMedicamento0 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento1 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento2 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento3 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento4 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento5 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento6 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento7 = new ArrayList<>(0);
        
        HashMap<Integer,ArrayList<Integer>> mapaMedicamentosHistorias = new HashMap<>(0);
        mapaMedicamentosHistorias.put(0, arrayHistoriasMedicamento0);
        mapaMedicamentosHistorias.put(1, arrayHistoriasMedicamento1);
        mapaMedicamentosHistorias.put(2, arrayHistoriasMedicamento2);
        mapaMedicamentosHistorias.put(3, arrayHistoriasMedicamento3);
        mapaMedicamentosHistorias.put(4, arrayHistoriasMedicamento4);
        mapaMedicamentosHistorias.put(5, arrayHistoriasMedicamento5);
        mapaMedicamentosHistorias.put(6, arrayHistoriasMedicamento6);
        mapaMedicamentosHistorias.put(7, arrayHistoriasMedicamento7);
        
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            historia = (listaHistorias[i].split(".dda"))[0];
            
            gruposDeMedicamentos = (new File(Directorios.MEDICAMENTOS+historia)).list();
            
            if(gruposDeMedicamentos==null)  continue;
            
            for(int j=0 ; j<=gruposDeMedicamentos.length-1 ; j++)
            {
                mapaMedicamentos = (HashMap<String,HashMap<String,String>>)Utilidades.leerArchivo(Directorios.MEDICAMENTOS+historia+"\\"+gruposDeMedicamentos[j]);
                
                iteradorMedicamentos = mapaMedicamentos.keySet().iterator();
                
                while(iteradorMedicamentos.hasNext())
                {
                    medicamento = iteradorMedicamentos.next();
                    
                    for(int k=0 ; k<=boxCampos.getComponentCount()-1 ; k++)
                    {
                        campo = (Campo)boxCampos.getComponent(k);
                        
                        if(campo.estaListo()==false)  continue;
                        
                        if(campo.estaAntibioticoEstablecido())
                        {
                            mapaMedicamento = mapaMedicamentos.get(medicamento);
                            
                            if(mapaMedicamento.get(PanelMedicamento.ANTIBIOTICO)==null)  continue;
                            
                            if(campo.obtenerAntibiotico().toUpperCase().equals(mapaMedicamento.get(PanelMedicamento.ANTIBIOTICO).toUpperCase()))
                                mapaMedicamentosHistorias.get(k).add(Integer.parseInt(historia));
                            
                        }else{
                            
                            if(medicamento.toUpperCase().contains(campo.obtenerValorCampo().toUpperCase()))
                                mapaMedicamentosHistorias.get(k).add(Integer.parseInt(historia));
                        }
                    }
                }
            }
        }
        
        
        if(medicamentos[0]>=0)  medicamentos[0] = (int)arrayHistoriasMedicamento0.stream().distinct().count();
        if(medicamentos[1]>=0)  medicamentos[1] = (int)arrayHistoriasMedicamento1.stream().distinct().count();
        if(medicamentos[2]>=0)  medicamentos[2] = (int)arrayHistoriasMedicamento2.stream().distinct().count();
        if(medicamentos[3]>=0)  medicamentos[3] = (int)arrayHistoriasMedicamento3.stream().distinct().count();
        if(medicamentos[4]>=0)  medicamentos[4] = (int)arrayHistoriasMedicamento4.stream().distinct().count();
        if(medicamentos[5]>=0)  medicamentos[5] = (int)arrayHistoriasMedicamento5.stream().distinct().count();
        if(medicamentos[6]>=0)  medicamentos[6] = (int)arrayHistoriasMedicamento6.stream().distinct().count();
        if(medicamentos[7]>=0)  medicamentos[7] = (int)arrayHistoriasMedicamento7.stream().distinct().count();
        
        
        graficaBarra.setVisible(false);
        
        graficaBarra.graficar(medicamentos, listaHistorias.length, HJBarGraph.obtenerColoresIluminadosPorDefecto(), HJBarGraph.obtenerColoresConSombraPorDefecto());
        
        graficaBarra.setVisible(true);
        
        colocarLeyenda();
    }
    
    
    private void graficarMedicamentoRecetadoUltCita() {
        
        int[] medicamentos = {-1, -1, -1, -1, -1, -1, -1, -1};
        
        for(int i=0 ; i<=boxCampos.getComponentCount()-1 ; i++)
        {
            if(((Campo)boxCampos.getComponent(i)).estaListo())  medicamentos[i] = 0;
        }
        
        
        String[] listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        String historia;
        
        String ultimaConsulta;
        
        HashMap<String,HashMap<String,String>> mapaMedicamentos;
        
        Iterator<String> iteradorMedicamentos;
        
        String medicamento;
        
        HashMap<String,String> mapaMedicamento;
        
        Campo campo;
        
        ArrayList<Integer> arrayHistoriasMedicamento0 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento1 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento2 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento3 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento4 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento5 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento6 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMedicamento7 = new ArrayList<>(0);
        
        HashMap<Integer,ArrayList<Integer>> mapaMedicamentosHistorias = new HashMap<>(0);
        mapaMedicamentosHistorias.put(0, arrayHistoriasMedicamento0);
        mapaMedicamentosHistorias.put(1, arrayHistoriasMedicamento1);
        mapaMedicamentosHistorias.put(2, arrayHistoriasMedicamento2);
        mapaMedicamentosHistorias.put(3, arrayHistoriasMedicamento3);
        mapaMedicamentosHistorias.put(4, arrayHistoriasMedicamento4);
        mapaMedicamentosHistorias.put(5, arrayHistoriasMedicamento5);
        mapaMedicamentosHistorias.put(6, arrayHistoriasMedicamento6);
        mapaMedicamentosHistorias.put(7, arrayHistoriasMedicamento7);
        
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            historia = (listaHistorias[i].split(".dda"))[0];
            
            ultimaConsulta = Utilidades.obtenerNombreUltimoArchivo(Directorios.CONSULTAS+historia);
            
            if(ultimaConsulta==null)  continue;
            
            mapaMedicamentos = (HashMap<String,HashMap<String,String>>)Utilidades.leerArchivo(Directorios.MEDICAMENTOS+historia+"\\"+ultimaConsulta);
            
            if(mapaMedicamentos==null)  continue;
            
            iteradorMedicamentos = mapaMedicamentos.keySet().iterator();
            
            while(iteradorMedicamentos.hasNext())
            {
                medicamento = iteradorMedicamentos.next();
                
                for(int k=0 ; k<=boxCampos.getComponentCount()-1 ; k++)
                {
                    campo = (Campo)boxCampos.getComponent(k);
                    
                    if(campo.estaListo()==false)  continue;
                    
                    if(campo.estaAntibioticoEstablecido())
                    {
                        mapaMedicamento = mapaMedicamentos.get(medicamento);
                        
                        if(mapaMedicamento.get(PanelMedicamento.ANTIBIOTICO)==null)  continue;
                        
                        if(campo.obtenerAntibiotico().toUpperCase().equals(mapaMedicamento.get(PanelMedicamento.ANTIBIOTICO).toUpperCase()))
                            mapaMedicamentosHistorias.get(k).add(Integer.parseInt(historia));
                        
                    }else{
                        
                        if(medicamento.toUpperCase().contains(campo.obtenerValorCampo().toUpperCase()))
                            mapaMedicamentosHistorias.get(k).add(Integer.parseInt(historia));
                    }
                }
            }
        }
        
        
        if(medicamentos[0]>=0)  medicamentos[0] = (int)arrayHistoriasMedicamento0.stream().distinct().count();
        if(medicamentos[1]>=0)  medicamentos[1] = (int)arrayHistoriasMedicamento1.stream().distinct().count();
        if(medicamentos[2]>=0)  medicamentos[2] = (int)arrayHistoriasMedicamento2.stream().distinct().count();
        if(medicamentos[3]>=0)  medicamentos[3] = (int)arrayHistoriasMedicamento3.stream().distinct().count();
        if(medicamentos[4]>=0)  medicamentos[4] = (int)arrayHistoriasMedicamento4.stream().distinct().count();
        if(medicamentos[5]>=0)  medicamentos[5] = (int)arrayHistoriasMedicamento5.stream().distinct().count();
        if(medicamentos[6]>=0)  medicamentos[6] = (int)arrayHistoriasMedicamento6.stream().distinct().count();
        if(medicamentos[7]>=0)  medicamentos[7] = (int)arrayHistoriasMedicamento7.stream().distinct().count();
        
        
        graficaBarra.setVisible(false);
        
        graficaBarra.graficar(medicamentos, listaHistorias.length, HJBarGraph.obtenerColoresIluminadosPorDefecto(), HJBarGraph.obtenerColoresConSombraPorDefecto());
        
        graficaBarra.setVisible(true);
        
        colocarLeyenda();
    }
    
    
    private void graficarMotivoHospitalizacion() {
        
        int[] motivos = {-1, -1, -1, -1, -1, -1, -1, -1};
        
        for(int i=0 ; i<=boxCampos.getComponentCount()-1 ; i++)
        {
            if(((Campo)boxCampos.getComponent(i)).estaListo())  motivos[i] = 0;
        }
        
        
        String[] listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        String historia;
        
        HashMap<String,ArrayList<String>> mapaHospitalizaciones;
        
        Iterator<String> iteradorMotivos;
        
        String motivo;
        
        Campo campo;
        
        ArrayList<Integer> arrayHistoriasMotivo0 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMotivo1 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMotivo2 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMotivo3 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMotivo4 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMotivo5 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMotivo6 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasMotivo7 = new ArrayList<>(0);
        
        HashMap<Integer,ArrayList<Integer>> mapaMotivosHistorias = new HashMap<>(0);
        mapaMotivosHistorias.put(0, arrayHistoriasMotivo0);
        mapaMotivosHistorias.put(1, arrayHistoriasMotivo1);
        mapaMotivosHistorias.put(2, arrayHistoriasMotivo2);
        mapaMotivosHistorias.put(3, arrayHistoriasMotivo3);
        mapaMotivosHistorias.put(4, arrayHistoriasMotivo4);
        mapaMotivosHistorias.put(5, arrayHistoriasMotivo5);
        mapaMotivosHistorias.put(6, arrayHistoriasMotivo6);
        mapaMotivosHistorias.put(7, arrayHistoriasMotivo7);
        
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            historia = (listaHistorias[i].split(".dda"))[0];
            
            mapaHospitalizaciones = (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_HOSPITALIZACIONES+listaHistorias[i]);
            
            if(mapaHospitalizaciones==null)  continue;
            
            iteradorMotivos = mapaHospitalizaciones.keySet().iterator();
            
            while(iteradorMotivos.hasNext())
            {
                motivo = iteradorMotivos.next();
                
                for(int k=0 ; k<=boxCampos.getComponentCount()-1 ; k++)
                {
                    campo = (Campo)boxCampos.getComponent(k);
                    
                    if(campo.estaListo()==false)  continue;
                    
                    if(motivo.toUpperCase().contains(campo.obtenerValorCampo().toUpperCase()))
                        mapaMotivosHistorias.get(k).add(Integer.parseInt(historia));
                }
            }
        }
        
        
        if(motivos[0]>=0)  motivos[0] = (int)arrayHistoriasMotivo0.stream().distinct().count();
        if(motivos[1]>=0)  motivos[1] = (int)arrayHistoriasMotivo1.stream().distinct().count();
        if(motivos[2]>=0)  motivos[2] = (int)arrayHistoriasMotivo2.stream().distinct().count();
        if(motivos[3]>=0)  motivos[3] = (int)arrayHistoriasMotivo3.stream().distinct().count();
        if(motivos[4]>=0)  motivos[4] = (int)arrayHistoriasMotivo4.stream().distinct().count();
        if(motivos[5]>=0)  motivos[5] = (int)arrayHistoriasMotivo5.stream().distinct().count();
        if(motivos[6]>=0)  motivos[6] = (int)arrayHistoriasMotivo6.stream().distinct().count();
        if(motivos[7]>=0)  motivos[7] = (int)arrayHistoriasMotivo7.stream().distinct().count();
        
        
        graficaBarra.setVisible(false);
        
        graficaBarra.graficar(motivos, listaHistorias.length, HJBarGraph.obtenerColoresIluminadosPorDefecto(), HJBarGraph.obtenerColoresConSombraPorDefecto());
        
        graficaBarra.setVisible(true);
        
        colocarLeyenda();
    }
    
    
    private void graficarTipoIntervencionQuirurgica() {
        
        int[] tipos = {-1, -1, -1, -1, -1, -1, -1, -1};
        
        for(int i=0 ; i<=boxCampos.getComponentCount()-1 ; i++)
        {
            if(((Campo)boxCampos.getComponent(i)).estaListo())  tipos[i] = 0;
        }
        
        
        String[] listaHistorias = (new File(Directorios.DATOS_PERSONALES)).list();
        
        String historia;
        
        HashMap<String,ArrayList<String>> mapaIntervenciones;
        
        Iterator<String> iteradorTipos;
        
        String tipo;
        
        Campo campo;
        
        ArrayList<Integer> arrayHistoriasTipo0 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasTipo1 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasTipo2 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasTipo3 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasTipo4 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasTipo5 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasTipo6 = new ArrayList<>(0);
        ArrayList<Integer> arrayHistoriasTipo7 = new ArrayList<>(0);
        
        HashMap<Integer,ArrayList<Integer>> mapaTiposHistorias = new HashMap<>(0);
        mapaTiposHistorias.put(0, arrayHistoriasTipo0);
        mapaTiposHistorias.put(1, arrayHistoriasTipo1);
        mapaTiposHistorias.put(2, arrayHistoriasTipo2);
        mapaTiposHistorias.put(3, arrayHistoriasTipo3);
        mapaTiposHistorias.put(4, arrayHistoriasTipo4);
        mapaTiposHistorias.put(5, arrayHistoriasTipo5);
        mapaTiposHistorias.put(6, arrayHistoriasTipo6);
        mapaTiposHistorias.put(7, arrayHistoriasTipo7);
        
        for(int i=0 ; i<=listaHistorias.length-1 ; i++)
        {
            historia = (listaHistorias[i].split(".dda"))[0];
            
            mapaIntervenciones = (HashMap<String,ArrayList<String>>)Utilidades.leerArchivo(Directorios.ANTECEDENTES_INTERVENCIONES+listaHistorias[i]);
            
            if(mapaIntervenciones==null)  continue;
            
            iteradorTipos = mapaIntervenciones.keySet().iterator();
            
            while(iteradorTipos.hasNext())
            {
                tipo = iteradorTipos.next();
                
                for(int k=0 ; k<=boxCampos.getComponentCount()-1 ; k++)
                {
                    campo = (Campo)boxCampos.getComponent(k);
                    
                    if(campo.estaListo()==false)  continue;
                    
                    if(tipo.toUpperCase().contains(campo.obtenerValorCampo().toUpperCase()))
                        mapaTiposHistorias.get(k).add(Integer.parseInt(historia));
                }
            }
        }
        
        
        if(tipos[0]>=0)  tipos[0] = (int)arrayHistoriasTipo0.stream().distinct().count();
        if(tipos[1]>=0)  tipos[1] = (int)arrayHistoriasTipo1.stream().distinct().count();
        if(tipos[2]>=0)  tipos[2] = (int)arrayHistoriasTipo2.stream().distinct().count();
        if(tipos[3]>=0)  tipos[3] = (int)arrayHistoriasTipo3.stream().distinct().count();
        if(tipos[4]>=0)  tipos[4] = (int)arrayHistoriasTipo4.stream().distinct().count();
        if(tipos[5]>=0)  tipos[5] = (int)arrayHistoriasTipo5.stream().distinct().count();
        if(tipos[6]>=0)  tipos[6] = (int)arrayHistoriasTipo6.stream().distinct().count();
        if(tipos[7]>=0)  tipos[7] = (int)arrayHistoriasTipo7.stream().distinct().count();
        
        
        graficaBarra.setVisible(false);
        
        graficaBarra.graficar(tipos, listaHistorias.length, HJBarGraph.obtenerColoresIluminadosPorDefecto(), HJBarGraph.obtenerColoresConSombraPorDefecto());
        
        graficaBarra.setVisible(true);
        
        colocarLeyenda();
    }
    
    
    //....................................
    
    private void colocarLeyenda() {
        
        boxLeyendaIzquierdo.setVisible(false);
        boxLeyendaDerecho.setVisible(false);
        
        boxLeyendaIzquierdo.removeAll();
        boxLeyendaDerecho.removeAll();
        
        
        Color[] coloresLeyenda = HJFrequencyChart.obtenerColoresPorDefecto();
        
        if(lNombreGrafica.getText().equals(GENERO))
        {
            boxLeyendaIzquierdo.add(obtenerLeyenda(coloresLeyenda[0], FEMENINO));
            boxLeyendaIzquierdo.add(Box.createVerticalStrut(15));
            boxLeyendaIzquierdo.add(obtenerLeyenda(coloresLeyenda[1], MASCULINO));
            
            boxLeyendaIzquierdo.setVisible(true);
            
        }else{
            
            if(lNombreGrafica.getText().equals(GRUPO_ETARIO))
            {
                boxLeyendaIzquierdo.add(obtenerLeyenda(coloresLeyenda[0], LACTANTE_MENOR));
                boxLeyendaIzquierdo.add(Box.createVerticalStrut(15));
                boxLeyendaIzquierdo.add(obtenerLeyenda(coloresLeyenda[1], LACTANTE_MAYOR));
                boxLeyendaIzquierdo.add(Box.createVerticalStrut(15));
                boxLeyendaIzquierdo.add(obtenerLeyenda(coloresLeyenda[2], PREESCOLAR));
                boxLeyendaIzquierdo.add(Box.createVerticalStrut(15));
                boxLeyendaIzquierdo.add(obtenerLeyenda(coloresLeyenda[3], ESCOLAR));
                
                boxLeyendaDerecho.add(obtenerLeyenda(coloresLeyenda[4], ADOLESCENTE));
                boxLeyendaDerecho.add(Box.createVerticalStrut(15));
                boxLeyendaDerecho.add(obtenerLeyenda(coloresLeyenda[5], ADULTO_JOVEN));
                boxLeyendaDerecho.add(Box.createVerticalStrut(15));
                boxLeyendaDerecho.add(obtenerLeyenda(coloresLeyenda[6], ADULTO_MADURO));
                boxLeyendaDerecho.add(Box.createVerticalStrut(15));
                boxLeyendaDerecho.add(obtenerLeyenda(coloresLeyenda[7], ADULTO_MAYOR));
                
                boxLeyendaIzquierdo.setVisible(true);
                boxLeyendaDerecho.setVisible(true);
                
            }else{
                
                for(int i=0, j=1 ; i<=boxCampos.getComponentCount()-1 ; i++)
                {
                    if(((Campo)boxCampos.getComponent(i)).estaListo())
                    {
                        if(j<=4)
                        {
                            boxLeyendaIzquierdo.add(obtenerLeyenda(coloresLeyenda[i], ((Campo)boxCampos.getComponent(i)).obtenerValorGeneral()));
                            boxLeyendaIzquierdo.add(Box.createVerticalStrut(15));
                            j++;
                        }else{
                            boxLeyendaDerecho.add(obtenerLeyenda(coloresLeyenda[i], ((Campo)boxCampos.getComponent(i)).obtenerValorGeneral()));
                            boxLeyendaDerecho.add(Box.createVerticalStrut(15));
                        }
                    }
                }
                
                if(boxLeyendaIzquierdo.getComponentCount()>0)
                {
                    boxLeyendaIzquierdo.remove(boxLeyendaIzquierdo.getComponentCount()-1);
                    boxLeyendaIzquierdo.setVisible(true);
                }
                
                if(boxLeyendaDerecho.getComponentCount()>0)
                {
                    boxLeyendaDerecho.remove(boxLeyendaDerecho.getComponentCount()-1);
                    boxLeyendaDerecho.setVisible(true);
                }
            }
        }
    }
    
    
    private JPanel obtenerLeyenda(Color color, String texto) {
        
        HJLabel lColor = new HJLabel();
        lColor.setPreferredSize(new Dimension(15, 15));
        lColor.setOpaque(true);
        lColor.setBackground(color);
        lColor.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.add(lColor);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(new HJLabel(texto));
        panel.setOpaque(false);
        
        return panel;
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class Campo extends JPanel {
        
        private HJComboBox<String> cbAntibioticos;
        private final HJLabel lColor;
        private HJTextField tCampo;
        
        
        public Campo(Color color, boolean esMedicamento) {
            
            setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
            
            setOpaque(false);
            
            
            //...........................
            
            lColor = new HJLabel();
            lColor.setPreferredSize(new Dimension(15, 15));
            lColor.setOpaque(true);
            lColor.setBackground(color);
            lColor.setBorder(BorderFactory.createLineBorder(Colores.LINEAS, 1));
            
            tCampo = new HJTextField(20);
            
            JPanel panelColorCampo = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            panelColorCampo.add(lColor);
            panelColorCampo.add(Box.createHorizontalStrut(10));
            panelColorCampo.add(tCampo);
            panelColorCampo.setOpaque(false);
            
            
            String[] nombresAntibioticos = ConstantesAntibioticos.obtenerNombresAntibioticos();
            
            String[] seleccionesAntibioticos = new String[nombresAntibioticos.length+1];
            seleccionesAntibioticos[0] = "No Antibiótico";
            for(int i=1 ; i<=seleccionesAntibioticos.length-1 ; i++)
            {
                seleccionesAntibioticos[i] = nombresAntibioticos[i-1];
            }
            
            cbAntibioticos = new HJComboBox<>(seleccionesAntibioticos);
            cbAntibioticos.addItemListener( e -> {
                
                if(cbAntibioticos.getSelectedIndex()>0)
                {
                    tCampo.setText("Antibiótico establecido");
                    tCampo.setEnabled(false);
                }else{
                    tCampo.limpiar();
                    tCampo.setEnabled(true);
                }
            });
            
            
            Box boxVertical = Box.createVerticalBox();
            
            if(esMedicamento)
            {
                JPanel panelAntibioticos = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
                panelAntibioticos.add(cbAntibioticos);
                panelAntibioticos.setOpaque(false);
                
                boxVertical.add(panelAntibioticos);
                boxVertical.add(Box.createVerticalStrut(5));
            }
            
            
            boxVertical.add(panelColorCampo);
            
            
            add(boxVertical);
            
        }
        
        
        public void inicializar() {
            
            cbAntibioticos.setSelectedIndex(0);
            tCampo.limpiar();
        }
        
        
        public boolean estaListo() { return cbAntibioticos.getSelectedIndex()>0 || tCampo.esTextoValido(); }
        
        
        public boolean estaAntibioticoEstablecido() { return cbAntibioticos.getSelectedIndex()>0; }
        
        public String obtenerAntibiotico() {
            
            if(estaAntibioticoEstablecido())  return ""+cbAntibioticos.getSelectedItem();
            else  return null;
        }
        
        public Color obtenerColor() { return lColor.getBackground(); }
        
        public String obtenerValorCampo() { return tCampo.getText(); }
        
        
        public String obtenerValorGeneral() {
            
            if(estaAntibioticoEstablecido())  return ""+cbAntibioticos.getSelectedItem();
            else  return tCampo.getText();
        }
        
        
    }
    
    
    
    //..............................................................................
    
    
}
