/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Humberto José Castellano Méndez
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;


public class HJFileChooser extends JFileChooser {
    
    public static final String TODOS_ARCHIVOS = "Todos los Archivos";
    public static final String IMAGENES = "Imágenes";
    public static final String HISTORIA_DOC_ASSISTANT = "Historia Doc Assistant";
    public static final String RESPALDO_TOTAL_DOC_ASSISTANT = "Respaldo Total de Doc Assistant";
    
    private IconoDeImagen iconoHDA = new IconoDeImagen("ArchivoHDA.png", 18, -1);
    private IconoDeImagen iconoTDA = new IconoDeImagen("ArchivoTDA.png", -1, 18);
    
    
    public HJFileChooser(String pathPorDefecto, String tipoSelector) {
        
        super(pathPorDefecto);
        
        
        setFileSystemView(FileSystemView.getFileSystemView());
        
        setFileView( new FileView() {
            @Override
            public Icon getIcon(File archivo) {
                
                if(archivo==null)  return FileSystemView.getFileSystemView().getSystemIcon(archivo);
                
                
                String extension = archivo.getName().substring(archivo.getName().lastIndexOf(".")+1).toUpperCase();
                
                if(extension.equals("HDA"))  return iconoHDA;
                
                if(extension.equals("TDA"))  return iconoTDA;
                
                
                return FileSystemView.getFileSystemView().getSystemIcon(archivo);
            }
        });
        
        
        Component[] componentes = getComponents();
        
        Component[] componentesPanel_1 = ((JPanel)componentes[0]).getComponents();
        
        Component[] componentesPanelBotones = ((JPanel)componentesPanel_1[0]).getComponents();
        
        IconoDeImagen[] iconos = {new IconoDeImagen("SubirNivel.png", -1, 22), new IconoDeImagen("Escritorio.png", 22, -1), new IconoDeImagen("CrearCarpeta.png", -1, 22), new IconoDeImagen("ModoLista.png", 22, -1), new IconoDeImagen("ModoDetalles.png", 22, -1)};
        
        Insets[] margenes = {new Insets(0, 2, 0, 2), new Insets(3, 0, 3, 0), new Insets(0, 0, 0, 0), new Insets(4, 0, 4, 0), new Insets(1, 0, 1, 0)};
        
        for(int i=0, j=0 ; j<=iconos.length-1 && i<=componentesPanelBotones.length-1 ; i++)
        {
            if(componentesPanelBotones[i] instanceof JButton)
            {
                ((JButton)componentesPanelBotones[i]).setIcon(iconos[j]);
                
                ((JButton)componentesPanelBotones[i]).setMargin(margenes[j]);
                
                j++;
                
            }else{
                
                if(componentesPanelBotones[i] instanceof JToggleButton)
                {
                    ((JToggleButton)componentesPanelBotones[i]).setIcon(iconos[j]);

                    ((JToggleButton)componentesPanelBotones[i]).setMargin(margenes[j]);

                    j++;
                }
            }
        }
        
        
        switch(tipoSelector)
        {
            case TODOS_ARCHIVOS : establecerParametrosTodosLosArchivos();
                                  break;
            
            case IMAGENES : establecerParametrosImagenes();
                            break;
            
            case HISTORIA_DOC_ASSISTANT : establecerParametrosHistoriaDA();
                                          break;
            
            case RESPALDO_TOTAL_DOC_ASSISTANT : establecerParametrosRespaldoTotalDA();
                                                break;
            
            default: break;
        }
        
    }
    
    
    private void establecerParametrosTodosLosArchivos() {
        
        setAcceptAllFileFilterUsed(true);
        setMultiSelectionEnabled(false);
    }
    
    
    private void establecerParametrosImagenes() {
        
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("Archivos de Imagen (*.jpg, *.jpeg, *.png)", "jpg", "jpeg", "png");
        addChoosableFileFilter(filtroImagen);
        
        setAcceptAllFileFilterUsed(false);
        setMultiSelectionEnabled(false);
        
        setAccessory(new HJAccesory(this));
    }
    
    
    private void establecerParametrosHistoriaDA() {
        
        FileNameExtensionFilter filtroHDA = new FileNameExtensionFilter("Historia de Doc Assistant (*.hda)", "hda");
        addChoosableFileFilter(filtroHDA);
        
        setAcceptAllFileFilterUsed(false);
        setMultiSelectionEnabled(false);
    }
    
    
    private void establecerParametrosRespaldoTotalDA() {
        
        FileNameExtensionFilter filtroTDA = new FileNameExtensionFilter("Respaldo Total de Doc Assistant (*.tda)", "tda");
        addChoosableFileFilter(filtroTDA);
        
        setAcceptAllFileFilterUsed(false);
        setMultiSelectionEnabled(false);
    }
    
    
    
    //..............................................................................
    //..............................................................................
    
    
    
    private class HJAccesory extends JComponent implements PropertyChangeListener {
        
        private final int anchoAcsesorio = 180;
        private final int alturaAccesorio = 180;
        
        private ImageIcon iconoArchivo;
	private boolean seMuestraVista = false;
	
	
	public HJAccesory(JFileChooser selector) {
            
            setPreferredSize(new Dimension(anchoAcsesorio, alturaAccesorio));
            
            anadirPropertyChangeListener(selector);
            
	}
        
        
        private void anadirPropertyChangeListener(JFileChooser selector) {
            
            selector.addPropertyChangeListener(this);
        }
	
	
        @Override
	public void propertyChange(PropertyChangeEvent pce) {
            
            if(pce.getPropertyName().equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY))
            {
		seMuestraVista = false;
		
            }else{
                
		if(pce.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
		{
                    if(pce.getNewValue() instanceof File)
                    {
			iconoArchivo = new ImageIcon(((File)pce.getNewValue()).getPath());
                        
			seMuestraVista = true;
                    }
		}
            }
		
            repaint();
	}
	
	
        @Override
	public void paintComponent(Graphics g) {
            
            Graphics2D g2D = (Graphics2D)g.create();
            
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            
            g2D.setColor(Colores.BLANCO);
            g2D.fillRect(0, 0, getWidth(), getHeight());
            
            g2D.setColor(Colores.LINEAS);
            g2D.drawRect(0, 0, getWidth()-1, getHeight()-1);
            
            if(seMuestraVista)
            {
                if(iconoArchivo.getIconWidth()>iconoArchivo.getIconHeight())
                    iconoArchivo = new ImageIcon(iconoArchivo.getImage().getScaledInstance(anchoAcsesorio-10, -1, Image.SCALE_SMOOTH));
                else
                    iconoArchivo = new ImageIcon(iconoArchivo.getImage().getScaledInstance(-1, alturaAccesorio-10, Image.SCALE_SMOOTH));
                 
                iconoArchivo.paintIcon(this, g2D, (int)Math.round((anchoAcsesorio-iconoArchivo.getIconWidth())/2.0), (int)Math.round((alturaAccesorio-iconoArchivo.getIconHeight())/2.0));
                
            }else{
		
		g2D.setColor(Colores.TEXTO);
		g2D.setFont(new Font("Arial", Font.PLAIN, 14));
                
                g2D.drawString("Vista Previa", Math.round((getWidth()-g2D.getFontMetrics().stringWidth("Vista Previa"))/2.0), Math.round((getHeight()+g2D.getFont().getSize())/2.0));
            }
            
            g2D.dispose();
	}
        
        
    }
    
    
    
    //..............................................................................
    
    
}
