package mail.mensaje.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
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
    private JLabel jlConexion, jlCantMensajes;
    private JMenuBar menuBar;
    private JMenu jmArchivo, jmMensajes, jmIr, jmHerramientas, jmLookAndFeel;
    private Separator sp1, sp2;
    private JButton jbInicio, jbRedactar, jbIngresarContacto,
        jbIngresarEmpresa, jbIrContactos, jbEliminarMensaje, jbResponder, 
        jbResponderTodos, jbReenviarMensaje, jbEditarMensaje, jbRetornarMensaje,
        jbIrEmpresas;
    private JPanel jpCenter, jpSouth, jpWest;
    private JToolBar toolBar;
    private MailControlador controlador;
    
    public MailPrincipalVista (MailControlador controlador) {
        this.controlador = controlador;
        crearIU ();
    }
    
    /**
     * Se encarga de inicializar los componentes que forman la IU, también
     * agrega los componentes al contenedor.
     */
    private void crearIU () {
        //Inicializando los menus.
        jmArchivo = new JMenu ("Archivo");
        jmMensajes = new JMenu ("Mensajes");
        jmIr = new JMenu ("Ir");
        jmHerramientas = new JMenu ("Herramientas");
        jmLookAndFeel = new JMenu ("Look And Feel");
        
        //Creo los item de los diferentes Look And Feel que pueden ser instalados para la plataforma.
        ButtonGroup group = new ButtonGroup();
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(info.getName());
            item.addItemListener(controlador);
            group.add (item);
            jmLookAndFeel.add (item);
        }
        jmHerramientas.add (jmLookAndFeel);
            
        //Inicializando el menuBar y añadiendo los menus.
        menuBar = new JMenuBar();
        menuBar.add(jmArchivo);
        menuBar.add(jmMensajes);
        menuBar.add(jmIr);
        menuBar.add(jmHerramientas);
        
        toolBar = new JToolBar();
        //Agrego borde al toolBar.
        toolBar.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        //Inserto los botones al toolbar.
        addButtons(toolBar);
        //Configuro los botones que seran visibles.
        activarVisibilidad(true, false, false, false, false, false);
        
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
        
        DefaultMutableTreeNode treeNodeMain = new DefaultMutableTreeNode("Tipos de mensajes");
        //Cargo los nodos al nodo principal.
        createNodes (treeNodeMain);
        
        //Inicializo el componente JTree y le agrego los nodos.
        jTree = new JTree();
        jTree.setModel(new DefaultTreeModel(treeNodeMain));
        jTree.setRootVisible(false);
        jTree.setToggleClickCount(1);
        
        jTree.addTreeSelectionListener(controlador);
        jTree.setPreferredSize(new Dimension(150, 90));
        
        jspMenPane = new JScrollPane(jTree);
        
        //Configuro los componentes del panel jpWest.
        jpWest = new JPanel (new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints ();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.insets = new Insets (10, 10, 3, 10);
        
        jpWest.add (jspMenPane, constraints);
        
        //Configuro los componentes del panel jpCenter.
        jpCenter = new JPanel (new GridBagLayout());
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(10, 0, 3, 10);
        
        jpCenter.add (jspTablaMensaje, constraints);
        
        //Configuro los componentes del panel jpSouth.
        jpSouth = new JPanel(new GridBagLayout());
        //Componente de la fila 0 columna 0.
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets (0, 10, 3, 0);
        
        jlCantMensajes = new JLabel ("Cantidad de mensajes");
        jpSouth.add (jlCantMensajes, constraints);
        
        //Componente de la fila 0 columna 1.
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets (0, 0, 3, 10);
        
        //TODO : Valor por defecto que lo agrego para ver como queda.
        jlConexion = new JLabel("Internet deshabilitado", JLabel.RIGHT);
        jlConexion.setIcon(UtilImg.createImageIcon("iconos\\disconnect.png"));
        jpSouth.add (jlConexion, constraints);
        
        //Configuro mi Frame.
        setTitle("Mail");
        setLayout(new BorderLayout());
        
        //Añadiendo el menuBar al JFrame.
        setJMenuBar(menuBar);
        
        //Añado al JFrame los componentes.
        add (BorderLayout.NORTH, toolBar);
        add (BorderLayout.SOUTH, jpSouth);
        add (BorderLayout.WEST, jpWest);
        add (BorderLayout.CENTER, jpCenter);
        
        //El tamaño minimo en la que puede ser minimizado.
        setMinimumSize(new Dimension(800, 600));
        //Maximimo la ventana por defecto.
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        jbIrEmpresas.setVisible(act1);
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
    
    public void setLookAndFeel (String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        }catch (Exception ex) {
            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
    }
    
    private void createNodes (DefaultMutableTreeNode topNode) {
        DefaultMutableTreeNode categoria = null,
                               tipoDeMensaje = null;
        
        categoria = new DefaultMutableTreeNode ("Mensajes");
        topNode.add (categoria);
        
        tipoDeMensaje = new DefaultMutableTreeNode("Enviados");
        categoria.add (tipoDeMensaje);
        
        tipoDeMensaje = new DefaultMutableTreeNode("Recibidos");
        categoria.add (tipoDeMensaje);
        
        tipoDeMensaje = new DefaultMutableTreeNode("Guardados");
        categoria.add (tipoDeMensaje);
        
        tipoDeMensaje = new DefaultMutableTreeNode("Eliminados");
        categoria.add (tipoDeMensaje);
    }
    
    private void addButtons (JToolBar toolBar) {
        jbInicio = configurarBoton("Inicio",
                                 "iconos/inicio.png",
                                 "jbInicio", "Inicio (Alt+I)",
                                 KeyEvent.VK_I);
        
        jbRedactar = configurarBoton("Redactar mensaje",
                                 "iconos/email_redactar.png",
                                 "jbRedactar", "Redactar mensaje (Alt+R)",
                                 KeyEvent.VK_R);
        
        jbIngresarContacto = configurarBoton("Ingresar contacto",
                                 "iconos/ingresar_contacto.png",
                                 "jbIngresarContacto", "Ingresar contacto (Alt+C)",
                                 KeyEvent.VK_C);
        
        jbIrContactos = configurarBoton("Ir a contacto",
                                 "iconos/contactos_ir.png",
                                 "jbIrContactos", "Ir a contacto (Alt+Z)",
                                 KeyEvent.VK_Z);
        
        jbIngresarEmpresa = configurarBoton("Ingresar empresa",
                                 "iconos/ingresar_empresa.png",
                                 "jbIngresarEmpresa", "Ingresar empresa (Alt+E)",
                                 KeyEvent.VK_E);
        
        jbIrEmpresas = configurarBoton("Ir a empresa",
                                 "iconos/ingresar_empresa.png",
                                 "jbIrEmpresas", "Ir a empresas (Alt+X)",
                                 KeyEvent.VK_X);
        
        jbEliminarMensaje = configurarBoton("Eliminar mensaje",
                                 "iconos/email_eliminar.png",
                                 "jbElimnarMensaje", "Eliminar mensaje (Alt+I)",
                                 0);
        
        jbResponder = configurarBoton("Responder",
                                 "iconos/responder_mensaje.png",
                                 "jbResponder", "Responder (Alt+I)",
                                 0);
        
        jbResponderTodos = configurarBoton("Responder a todos",
                                 "iconos/responder_mensaje.png",
                                 "jbResponderTodos", "Responder a todos (Alt+I)",
                                 0);
        
        jbReenviarMensaje = configurarBoton("Reenviar mensaje",
                                 "iconos/responder_mensaje.png",
                                 "jbReenviarMensaje", "Reenviar mensaje (Alt+I)",
                                 0);
        
        jbEditarMensaje = configurarBoton("Editar mensaje",
                                 "iconos/responder_mensaje.png",
                                 "jbEditarMensaje", "Editar mensaje (Alt+I)",
                                 0);
        
        jbRetornarMensaje = configurarBoton("Retornar mensaje",
                                 "iconos/responder_mensaje.png",
                                 "jbRetornarMensaje", "Retornar mensaje (Alt+I)",
                                 0);
        
        toolBar.add(jbInicio);
        sp1 = new JToolBar.Separator();
        toolBar.add(sp1);
        toolBar.add(jbRedactar);
        
        toolBar.add(jbResponder);
        toolBar.add(jbResponderTodos);
        toolBar.add(jbEditarMensaje);
        toolBar.add(jbEliminarMensaje);
        toolBar.add(jbReenviarMensaje);
        toolBar.add(jbRetornarMensaje);
        
        sp2 = new JToolBar.Separator();
        toolBar.add(sp2);
        toolBar.add(jbIngresarContacto);
        toolBar.add(jbIrContactos);
        toolBar.add(jbIngresarEmpresa);
        toolBar.add(jbIrEmpresas);
    }
    
    private JButton configurarBoton (String text, String imagePath,
                                     String actionCommand,
                                     String toolTipText,
                                     int mnemonic) {
        
        JButton button = new JButton (text);
        button.setFocusable(false);
        button.setMnemonic(mnemonic);
        button.setToolTipText(toolTipText);
        button.addActionListener(controlador);
        button.setActionCommand(actionCommand);
        button.setIcon(UtilImg.createImageIcon (imagePath));
        
        return button;
    }
}