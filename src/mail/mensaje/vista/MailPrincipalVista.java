package mail.mensaje.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import mail.mensaje.controlador.MailControlador;

/**
 * Ventana de inicio del programa, en la que solo contendrá la vista.
 * 
 * @author Félix Pedrozo
 */
public class MailPrincipalVista extends JFrame {
    private JTree jTree;
    private JTable jtMensajes;
    private JScrollPane jspMenPane, jspTablaMensaje;
    private JLabel jlConexion;
    private JMenuBar menuBar;
    private JMenu jmArchivo, jmMensajes, jmIr, jmHerramientas;
    private Separator sp1, sp2;
    private JButton jbInicio, jbRedactar, jbIngresarContacto,
        jbIngresarEmpresa, jbIrContactos, jbEliminarMensaje, jbResponder, 
        jbResponderTodos, jbReenviarMensaje, jbEditarMensaje, jbRetornarMensaje;
    private JPanel jpCenter, jpSouth;
    private JToolBar toolBar;
    
    public MailPrincipalVista (MailControlador evento) {
        crearIU (evento);
    }
    
    /**
     * Se encarga de inicializar los componentes que forman la IU, también
     * agrega los componentes al contenedor.
     */
    private void crearIU (MailControlador mailControlador) {
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
        
        //Inicializo los botones para añadir al toolBar.
        jbInicio = new JButton("Inicio");
        jbInicio.setName("jbInicio");
        jbInicio.setIcon(
            new ImageIcon(getClass().getResource("iconos\\inicio.png"))
        );
        jbInicio.setFocusable(false);
        jbInicio.setToolTipText("Inicio (Ctrl+I)");
        jbInicio.addActionListener(mailControlador);
        
        jbRedactar = new JButton("Redactar mensaje");
        jbRedactar.setName("jbRedactar");
        jbRedactar.setIcon(
            new ImageIcon(getClass().getResource("iconos\\email_redactar.png"))
        );
        jbRedactar.setFocusable(false);
        jbRedactar.addActionListener(mailControlador);
        jbRedactar.setToolTipText("Redactar mensaje (Ctrl+M)");
        
        jbIngresarContacto = new JButton("Ingresar contacto");
        jbIngresarContacto.setName("jbIngresarContacto");
        jbIngresarContacto.setIcon(
            new ImageIcon(getClass().getResource("iconos\\ingresar_contacto.png"))
        );
        jbIngresarContacto.setFocusable(false);
        jbIngresarContacto.addActionListener(mailControlador);
        jbIngresarContacto.setToolTipText("Ingresar contacto (Ctrl+Alt+C)");
        
        jbIngresarEmpresa = new JButton("Ingresar empresa");
        jbIngresarEmpresa.setName("jbIngresarEmpresa");
        jbIngresarEmpresa.setIcon(
            new ImageIcon(getClass().getResource("iconos\\ingresar_empresa.png"))
        );
        jbIngresarEmpresa.setFocusable(false);
        jbIngresarEmpresa.addActionListener(mailControlador);
        jbIngresarEmpresa.setToolTipText("Ingresar empresa (Ctrl+E)");
        
        
        jbIrContactos = new JButton("Ir a contactos");
        jbIrContactos.setName("jbIrContactos");
        jbIrContactos.setIcon(
            new ImageIcon(getClass().getResource("iconos\\contactos_ir.png"))
        );
        jbIrContactos.setFocusable(false);
        jbIrContactos.addActionListener(mailControlador);
        jbIrContactos.setToolTipText("Ir a contactos (Ctrl+A)");
        
        jbEliminarMensaje = new JButton("Eliminar mensaje");
        jbEliminarMensaje.setName("jbEliminarMensaje");
        jbEliminarMensaje.setIcon(
            new ImageIcon(getClass().getResource("iconos\\email_eliminar.png"))
        );
        jbEliminarMensaje.setFocusable(false);
        jbEliminarMensaje.setVisible(false);
        jbEliminarMensaje.setToolTipText("Eliminar mensaje");
        
        jbResponder = new JButton("Responder mensaje");
        jbResponder.setIcon(
            new ImageIcon(getClass().getResource("iconos\\responder_mensaje.png"))
        );
        jbResponder.setFocusable(false);
        jbResponder.setVisible(false);
        jbResponder.setToolTipText("Responder mensaje");
        
        jbResponderTodos = new JButton("Responder a todos");
        jbResponderTodos.setIcon(
            new ImageIcon(getClass().getResource("iconos\\responder_mensaje.png"))
        );
        jbResponderTodos.setFocusable(false);
        jbResponderTodos.setVisible(false);
        jbResponderTodos.setToolTipText("Responder a todos");
        
        jbReenviarMensaje = new JButton("Reenviar mensaje");
        jbReenviarMensaje.setIcon(
            new ImageIcon(getClass().getResource("iconos\\responder_mensaje.png"))
        );
        jbReenviarMensaje.setFocusable(false);
        jbReenviarMensaje.setVisible(false);
        jbReenviarMensaje.setToolTipText("Reenviar mensaje");
        
        jbEditarMensaje = new JButton("Editar mensaje");
        jbEditarMensaje.setIcon(
            new ImageIcon(getClass().getResource("iconos\\responder_mensaje.png"))
        );
        jbEditarMensaje.setFocusable(false);
        jbEditarMensaje.setVisible(false);
        jbEditarMensaje.setToolTipText("Editar mensaje");
        
        jbRetornarMensaje = new JButton("Retornar mensaje");
        jbRetornarMensaje.setIcon(
            new ImageIcon(getClass().getResource("iconos\\responder_mensaje.png"))
        );
        jbRetornarMensaje.setFocusable(false);
        jbRetornarMensaje.setVisible(false);
        jbRetornarMensaje.setToolTipText("Retornar mensaje");
        
        sp1 = new JToolBar.Separator();
        sp2 = new JToolBar.Separator();
        
        //Inserto los botones al toolbar.
        toolBar = new JToolBar();
        //Agrego borde al toolBar.
        toolBar.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        
        toolBar.add(jbInicio);
        toolBar.add(sp1);
        toolBar.add(jbRedactar);
        
        toolBar.add(jbResponder);
        toolBar.add(jbResponderTodos);
        toolBar.add(jbEditarMensaje);
        toolBar.add(jbEliminarMensaje);
        toolBar.add(jbReenviarMensaje);
        toolBar.add(jbRetornarMensaje);
        
        toolBar.add(sp2);
        toolBar.add(jbIngresarContacto);
        toolBar.add(jbIrContactos);
        toolBar.add(jbIngresarEmpresa);
        
        //Inicializando el label.
        //TODO : Valor por defecto que lo agrego para ver como queda.
        jlConexion = new JLabel("Internet deshabilitado");
        jlConexion.setIcon(
            new ImageIcon(getClass().getResource("iconos\\disconnect.png"))
        );
        
        jpSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 2));
        jpSouth.add(jlConexion);
        
        jtMensajes = new JTable (new DefaultTableModel(
            new Object [][] {
                {"leticiafie1954@gmail.com", "Publicidad de Avón", "11/02/16", "10:30hs"},
                {"leticiafie1954@gmail.com", "Publicidad de Avón", "11/02/16", "10:30hs"},
                {"leticiafie1954@gmail.com", "Publicidad de Avón", "11/02/16", "10:30hs"},
                {"leticiafie1954@gmail.com", "Publicidad de Avón", "11/02/16", "10:30hs"},
            }, 
            new String [] {
                "Para", "Asunto", "Fecha", "Hora"
            }
        ));
        
        jspTablaMensaje = new JScrollPane ();
        jspTablaMensaje.setViewportView(jtMensajes);
        
        DefaultMutableTreeNode treeNodeMain = new DefaultMutableTreeNode("root");
        DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("Mensajes");
        
        DefaultMutableTreeNode treeNode2 = new DefaultMutableTreeNode("Enviados");
        treeNode1.add(treeNode2);
        treeNode2 = new DefaultMutableTreeNode("Eliminados");
        treeNode1.add(treeNode2);
        treeNode2 = new DefaultMutableTreeNode("Guardados");
        treeNode1.add(treeNode2);
        treeNodeMain.add(treeNode1);
        
        //Inicializo el componente JTree y le agrego los nodos.
        jTree = new JTree();
        jTree.setModel(new DefaultTreeModel(treeNodeMain));
        jTree.setRootVisible(false);
        jTree.setToggleClickCount(1);
        
        jTree.addTreeSelectionListener(mailControlador);
        
        jspMenPane = new JScrollPane();
        jspMenPane.setViewportView(jTree);
        
        jpCenter = new JPanel(new GridBagLayout ());
        GridBagConstraints constraints = new GridBagConstraints ();
        //Componente de la fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1.0;
        //TODO : Posee un error al poner esto en el JTree al presionar un nodo.
        constraints.ipadx = 80;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.insets = new Insets(10, 10, 5, 10);
        
        jpCenter.add (jspMenPane, constraints);
        
        //Componente de la fila 0 columna 1.
        constraints.gridx = 1;
        constraints.weightx = 1.0;
        constraints.ipadx = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 0, 5, 10);
        
        jpCenter.add (jspTablaMensaje, constraints);
        
        //Configuro mi Frame.
        setTitle("Mail");
        
        //Añadiendo el menuBar al JFrame.
        setJMenuBar(menuBar);
        
        //Añado al JFrame los componentes.
        add (BorderLayout.NORTH, toolBar);
        add (BorderLayout.SOUTH, jpSouth);
        add (BorderLayout.CENTER, jpCenter);
        
        //El tamaño minimo en la que puede ser minimizado.
        setMinimumSize(new Dimension(800, 600));
        //Maximimo la ventana por defecto.
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * Determina cuál de los botones del ToolBar será visible dependiendo de los valores
     * de los argumentos pasados.
     * @param act1 Activa u desactiva los botones Ingresar contacto, Ingresar empresa y Consultar contactos.
     * @param act2 Activa u desactiva el botón Reenviar mensaje.
     * @param act3 Activa u desactiva el botón eliminar mensaje.
     * @param act4 Activa u desactiva el botón editar mensaje.
     * @param act5 Activa u desactiva el botón retornar mensaje.
     * @param act6 Activa u desactiva el botón responder a todos.
     */
    public void activarVisibilidad (boolean act1, boolean act2, boolean act3, boolean act4, boolean act5, boolean act6) {
        jbIngresarContacto.setVisible(act1);
        jbIngresarEmpresa.setVisible(act1);
        jbIrContactos.setVisible(act1);
        sp2.setVisible(act1);
        
        jbReenviarMensaje.setVisible(act2);
        jbEliminarMensaje.setVisible(act3);
        jbEditarMensaje.setVisible(act4);
        jbRetornarMensaje.setVisible(act5);
        
        jbResponder.setVisible(act6);
        jbResponderTodos.setVisible(act6);
    }
    
    /**
     * Permite acceder a la referencia del objeto de instancia <code>JTree</code>
     * @return Devuelve la referencia de un objeto <code>JTree</code>.
     */
    public JTree getTree () {
        return jTree;
    }
}