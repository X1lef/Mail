package mail.mensaje.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Ventana de inicio del programa, en la que solo contendra la vista.
 */
public class VentanaPrincipal extends JFrame {
    private JTree jtArbol;
    private JScrollPane jspMenPane;
    private JMenuBar menuBar;
    private JMenu jmArchivo, jmMensajes, jmIr, jmHerramientas;
    private JButton jbInicio, jbRedactar, jbIngresarContacto,
            jbIngresarEmpresa, jbIrContactos;
    private JPanel jpNorth;
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
        jmArchivo = new JMenu ("Archivo");
        jmMensajes = new JMenu ("Mensajes");
        jmIr = new JMenu ("Ir");
        jmHerramientas = new JMenu ("Herramientas");
        
        //Inicializando el menuBar y añadiendo los menus.
        menuBar = new JMenuBar();
        menuBar.add(jmArchivo);
        menuBar.add(jmMensajes);
        menuBar.add(jmIr);
        menuBar.add(jmHerramientas);
        
        //Inicializamos los botones para añadir al toolBar.
        jbInicio = new JButton();
        jbInicio.setIcon(
                new ImageIcon(getClass().getResource("/Iconos/inicio.png"))
        );
        jbInicio.setFocusable(false);
        jbInicio.setToolTipText("Inicio (Ctrl+I)");
        
        jbRedactar = new JButton();
        jbRedactar.setIcon(
                new ImageIcon(getClass().getResource("/Iconos/email_redactar.png"))
        );
        jbRedactar.setFocusable(false);
        jbRedactar.setToolTipText("Redactar mensaje (Ctrl+M)");
        
        jbIngresarContacto = new JButton();
        jbIngresarContacto.setIcon(
                new ImageIcon(getClass().getResource("/Iconos/ingresar_contacto.png"))
        );
        jbIngresarContacto.setFocusable(false);
        jbIngresarContacto.setToolTipText("Ingresar contacto (Ctrl+Alt+C)");
        
        jbIngresarEmpresa = new JButton();
        jbIngresarEmpresa.setIcon(
                new ImageIcon(getClass().getResource("/Iconos/ingresar_empresa.png"))
        );
        jbIngresarEmpresa.setFocusable(false);
        jbIngresarEmpresa.setToolTipText("Ingresar empresa (Ctrl+E)");
        
        
        jbIrContactos = new JButton();
        jbIrContactos.setIcon(
                new ImageIcon(getClass().getResource("/Iconos/contactos_ir.png"))
        );
        jbIrContactos.setFocusable(false);
        jbIrContactos.setToolTipText("Ir a contactos (Ctrl+A)");
        
        sp1 = new JToolBar.Separator();
        sp2 = new JToolBar.Separator();
        
        //Inserto los botones al toolbar.
        toolBar = new JToolBar();
        toolBar.add(jbInicio);
        toolBar.add(sp1);
        toolBar.add(jbRedactar);
        toolBar.add(sp2);
        toolBar.add(jbIngresarContacto);
        toolBar.add(jbIrContactos);
        toolBar.add(jbIngresarEmpresa);
        
        
        //Inicializo el panel y le agrego el toolbar.
        jpNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpNorth.add(toolBar);
        
//        DefaultMutableTreeNode treeNodeMain = new DefaultMutableTreeNode("root");
//        DefaultMutableTreeNode treeNode2 = new DefaultMutableTreeNode("Mensajes");
//        
//        DefaultMutableTreeNode treeNode3 = new DefaultMutableTreeNode("Recididos");
//        treeNode2.add(treeNode3);
//        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Enviados");
//        treeNode2.add(treeNode3);
//        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Eliminados");
//        treeNode2.add(treeNode3);
//        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Guardados");
//        treeNode2.add(treeNode3);
//        treeNodeMain.add(treeNode2);
//        
//        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Contactos");
//        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("basketball");
//        treeNode2.add(treeNode3);
//        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("soccer");
//        treeNode2.add(treeNode3);
//        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("football");
//        treeNode2.add(treeNode3);
//        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hockey");
//        treeNode2.add(treeNode3);
//        treeNodeMain.add(treeNode2);
//        
//        jtArbol.setModel(new DefaultTreeModel(treeNodeMain));
//        jtArbol.setRootVisible(false);
//        jtArbol.setToggleClickCount(1);
//        jspMenPane.setViewportView(jtArbol);
        
        //Añadiendo el menuBar al JFrame.
        setJMenuBar(menuBar);
        
        //Añadimos al JFrame los componentes.
//        add (BorderLayout.SOUTH, jspMenPane);
        add (BorderLayout.NORTH, jpNorth);
        
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
