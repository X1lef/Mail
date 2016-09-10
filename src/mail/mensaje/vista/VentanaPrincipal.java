package mail.mensaje.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Ventana de inicio del programa, en la que solo contendra la vista.
 */
public class VentanaPrincipal extends JFrame {
    private JTree jTree;
    private JTable jtMensajes;
    private JScrollPane jspMenPane, jspTablaMensaje;
    private JLabel jlConexion;
    private JMenuBar menuBar;
    private JMenu jmArchivo, jmMensajes, jmIr, jmHerramientas;
    private Separator sp1, sp2;
    private JButton jbInicio, jbRedactar, jbIngresarContacto,
            jbIngresarEmpresa, jbIrContactos;
    private JPanel jpNorth, jpSouth;
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
        
        //Inicializando el label.
        //TODO : Valor por defecto que lo agrego para ver como queda.
        jlConexion = new JLabel("Internet deshabilitado");
        jlConexion.setIcon(
                new ImageIcon(getClass().getResource("/Iconos/disconnect.png"))
        );
        
        //Inicializo el panel y le agrego el toolbar.
        jpNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpNorth.add(toolBar);
        
        jpSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jpSouth.add(jlConexion);
        
        jtMensajes = new JTable ();
        jtMensajes.setModel(new DefaultTableModel(
            new Object [][] {
                {"leticiafie1954@gmail.com", "Publicidad de Avón", "11/02/16 10:30hs"}
            }, 
            new String [] {
                "Para", "Asunto", "Fecha"
            }
        ));
        
        jspTablaMensaje = new JScrollPane ();
        jspTablaMensaje.setViewportView(jtMensajes);
        
        DefaultMutableTreeNode treeNodeMain = new DefaultMutableTreeNode("root");
        DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("Mensajes largo tempralmente");
        
        DefaultMutableTreeNode treeNode2 = new DefaultMutableTreeNode("Recididos");
        treeNode1.add(treeNode2);
        treeNode2 = new DefaultMutableTreeNode("Enviados");
        treeNode1.add(treeNode2);
        treeNode2 = new DefaultMutableTreeNode("Eliminados");
        treeNode1.add(treeNode2);
        treeNode2 = new DefaultMutableTreeNode("Guardados");
        treeNode1.add(treeNode2);
        treeNodeMain.add(treeNode1);
        
        treeNode1 = new DefaultMutableTreeNode("Contactos");
        treeNode2 = new DefaultMutableTreeNode("basketball");
        treeNode1.add(treeNode2);
        treeNode2 = new DefaultMutableTreeNode("soccer");
        treeNode1.add(treeNode2);
        treeNode2 = new DefaultMutableTreeNode("football");
        treeNode1.add(treeNode2);
        treeNode2 = new DefaultMutableTreeNode("hockey");
        treeNode1.add(treeNode2);
        treeNodeMain.add(treeNode1);
        
        //Inicializamos el componente JTree y le agregamoslos nodos.
        jTree = new JTree();
        jTree.setModel(new DefaultTreeModel(treeNodeMain));
        jTree.setRootVisible(false);
        jTree.setToggleClickCount(1);
        
        jspMenPane = new JScrollPane();
        jspMenPane.setViewportView(jTree);
        
        //Añadiendo el menuBar al JFrame.
        setJMenuBar(menuBar);
        
        //Añadimos al JFrame los componentes.
        add (BorderLayout.NORTH, jpNorth);
        add (BorderLayout.SOUTH, jpSouth);
        add (BorderLayout.CENTER, jspTablaMensaje);
        add (BorderLayout.WEST, jspMenPane);
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
