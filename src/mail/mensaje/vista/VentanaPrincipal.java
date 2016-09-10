package mail.mensaje.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.JTree;

/**
 * Ventana de inicio del programa, en la que solo contendra la vista.
 */
public class VentanaPrincipal extends JFrame {
    private JMenuBar menuBar;
    private JMenu mArchivo, mMensajes, mIr, mHerramientas;
    private JButton btnInicio, btnRedactar, btnIngresarContacto;
    private JPanel pnNorth;
    private Separator sp1, sp2;
    private JToolBar toolBar;
    
    
    public VentanaPrincipal () {
        crearUI ();
    }
    
    /**
     * Se encarga de inicializar los componentes que forman la UI, tambien
     * agrega los componentes al contenedor.
     */
    public void crearUI () {
        //Inicializando los menus.
        mArchivo = new JMenu ("Archivo");
        mMensajes = new JMenu ("Mensajes");
        mIr = new JMenu ("Ir");
        mHerramientas = new JMenu ("Herramientas");
        
        //Inicializando el menuBar y añadiendo los menus.
        menuBar = new JMenuBar();
        menuBar.add(mArchivo);
        menuBar.add(mMensajes);
        menuBar.add(mIr);
        menuBar.add(mHerramientas);
        
        //Inicializamos los botones.
        btnInicio = new JButton("Inicio");
        btnRedactar = new JButton("Redactar mensaje");
        btnIngresarContacto = new JButton("Ingresar contacto");
        
        sp1 = new JToolBar.Separator();
        sp2 = new JToolBar.Separator();
        
        //Inserto los botones al toolbar.
        toolBar = new JToolBar();
        toolBar.add(btnInicio);
        toolBar.add(sp1);
        toolBar.add(btnRedactar);
        toolBar.add(sp2);
        toolBar.add(btnIngresarContacto);
        
        //Inicializo el panel y le agrego el toolbar.
        pnNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnNorth.add(toolBar);
        
        //Añadiendo el menuBar al JFrame.
        setJMenuBar(menuBar);
        
        //Añadimos al JFrame los componentes.
        add (BorderLayout.NORTH, pnNorth);
        
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
