package mail.mensaje.vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import mail.mensaje.controlador.MailControlador;

/**
 * Ventana principal del programa.
 * 
 * @author Félix Pedrozo
 */
public class MailPrincipalVista extends JFrame {
    private JTree jTree;
    private JTable jtMensRecibido, jtMensProg, jtMensEnviados, jtMensGuardado, jtMensEliminado;
    private JScrollPane jspMenPane;
    private JLabel jlConexion, jlCantMensajes;
    private JMenuBar menuBar;
    private JMenu jmArchivo, jmMensajes, jmIr, jmHerramientas, jmtema;
    private Separator sp1, sp2;
    private JButton jbInicio, jbRedactar, jbIngresarContacto,
        jbIngresarEmpresa, jbIrContactos, jbEliminarMensaje, jbResponder, 
        jbResponderTodos, jbReenviarMensaje, jbEditarMensaje, jbRetornarMensaje,
        jbIrEmpresas;
    private JPanel jpCenter, jpSouth, jpWest, jpTipoMensj, jpMensajes;
    private JToolBar toolBar;
    private final MailControlador controlador;
    
    /**
     * @param controlador Referencia de la clase que manejara los eventos de
     * la clase <code>MailPrincipal</code>
     */
    public MailPrincipalVista (MailControlador controlador) {
        this.controlador = controlador;
        crearIU ();
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
        }catch (Exception ex) {}
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
        jmtema = new JMenu ("Tema");
        
        //Creo los item de los diferentes Look And Feel que pueden ser instalados para la plataforma.
        ButtonGroup group = new ButtonGroup();
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(info.getName());
            item.addItemListener(controlador);
            group.add (item);
            jmtema.add (item);
        }
        jmHerramientas.add (jmtema);
            
        //Inicializando el menuBar y añado los menus.
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
        
        DefaultMutableTreeNode treeNodeMain = new DefaultMutableTreeNode("Tipos de mensajes");
        //Cargo los nodos al nodo principal.
        createNodes (treeNodeMain);
        
        //Inicializo el componente JTree y le agrego los nodos.
        jTree = new JTree();
        jTree.setModel(new DefaultTreeModel(treeNodeMain));
        jTree.setRootVisible(false);
        jTree.setToggleClickCount(1);
        
        jTree.addTreeSelectionListener(controlador);
        //Le configuro un tamaño por defecto.
        jTree.setPreferredSize(new Dimension(150, 90));
        
        jspMenPane = new JScrollPane(jTree);
        
        //Configuro los componentes del panel jpWest.
        jpWest = new JPanel (new GridBagLayout());
        GridBagConstraints conf = new GridBagConstraints ();
        conf.gridx = 0;
        conf.gridy = 0;
        conf.weighty = 1.0;
        conf.fill = GridBagConstraints.VERTICAL;
        conf.insets = new Insets (10, 10, 3, 10);
        
        jpWest.add (jspMenPane, conf);
        
        //Configuro los componentes del panel jpCenter.
        jpCenter = new JPanel (new GridBagLayout());
        conf.weightx = 1.0;
        conf.fill = GridBagConstraints.BOTH;
        conf.insets = new Insets(10, 0, 3, 10);
        
        jpMensajes = panelDeMensajes();
        jpCenter.add (jpMensajes, conf);
        
        //Configuro los componentes del panel jpSouth.
        jpSouth = new JPanel(new GridBagLayout());
        //Componente de la fila 0 columna 0.
        conf.weighty = 0.0;
        conf.fill = GridBagConstraints.HORIZONTAL;
        conf.anchor = GridBagConstraints.WEST;
        conf.insets = new Insets (0, 10, 3, 0);
        
        jlCantMensajes = new JLabel ("Cantidad de mensajes");
        jpSouth.add (jlCantMensajes, conf);
        
        //Componente de la fila 0 columna 1.
        conf.gridx = 1;
        conf.anchor = GridBagConstraints.EAST;
        conf.insets = new Insets (0, 0, 3, 10);
        
        //TODO : Dato de prueba.
        jlConexion = new JLabel("Internet deshabilitado", JLabel.RIGHT);
        jlConexion.setIcon(UtilImg.createImageIcon("iconos\\disconnect.png"));
        jpSouth.add (jlConexion, conf);
        
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
     * Insertara los nodos secundarios al nodo principal.
     * @param topNode Contiene la referencia del nodo principal.
     */
    private void createNodes (DefaultMutableTreeNode topNode) {
        DefaultMutableTreeNode tipoDeMensaje, categoria;
        
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
    
    /**
     * Creara los botones para insertarlo a la barra de herramientas.
     * 
     * @param toolBar Contiene la referencia de la barra de herramientas a la
     * cual se insertara los botones.
     */
    private void addButtons (JToolBar toolBar) {
        //Inicializo los botones.
        inicializarBotonesDelToolbar ();
        
        //Inserto los botones al toolbar.
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
    
    private void inicializarBotonesDelToolbar () {
        jbInicio = configurarBoton("Inicio",
                                 "iconos/inicio.png",
                                 "jbInicio", "Inicio (Alt+I)");
        jbInicio.setMnemonic(KeyEvent.VK_I);
        
        jbRedactar = configurarBoton("Redactar mensaje",
                                 "iconos/email_redactar.png",
                                 "jbRedactar", "Redactar mensaje (Alt+R)");
        jbRedactar.setMnemonic(KeyEvent.VK_R);
        
        jbIngresarContacto = configurarBoton("Ingresar contacto",
                                 "iconos/ingresar_contacto.png",
                                 "jbIngresarContacto", "Ingresar contacto (Alt+C)");
        jbIngresarContacto.setMnemonic(KeyEvent.VK_C);
        
        jbIrContactos = configurarBoton("Ir a contacto",
                                 "iconos/contactos_ir.png",
                                 "jbIrContactos", "Ir a contacto (Alt+Z)");
        jbIrContactos.setMnemonic(KeyEvent.VK_Z);
        
        jbIngresarEmpresa = configurarBoton("Ingresar empresa",
                                 "iconos/ingresar_empresa.png",
                                 "jbIngresarEmpresa", "Ingresar empresa (Alt+E)");
        jbIngresarEmpresa.setMnemonic(KeyEvent.VK_E);
        
        jbIrEmpresas = configurarBoton("Ir a empresa",
                                 "iconos/ingresar_empresa.png",
                                 "jbIrEmpresas", "Ir a empresas (Alt+X)");
        jbIrEmpresas.setMnemonic(KeyEvent.VK_X);
        
        jbEliminarMensaje = configurarBoton("Eliminar mensaje",
                                 "iconos/email_eliminar.png",
                                 "jbElimnarMensaje", "Eliminar mensaje (Alt+I)");
        
        jbResponder = configurarBoton("Responder",
                                 "iconos/responder_mensaje.png",
                                 "jbResponder", "Responder (Alt+I)");
        
        jbResponderTodos = configurarBoton("Responder a todos",
                                 "iconos/responder_mensaje.png",
                                 "jbResponderTodos", "Responder a todos (Alt+I)");
        
        jbReenviarMensaje = configurarBoton("Reenviar mensaje",
                                 "iconos/responder_mensaje.png",
                                 "jbReenviarMensaje", "Reenviar mensaje (Alt+I)");
        
        jbEditarMensaje = configurarBoton("Editar mensaje",
                                 "iconos/responder_mensaje.png",
                                 "jbEditarMensaje", "Editar mensaje (Alt+I)");
        
        jbRetornarMensaje = configurarBoton("Retornar mensaje",
                                 "iconos/responder_mensaje.png",
                                 "jbRetornarMensaje", "Retornar mensaje (Alt+I)");
    }
    
    /**
     * Configura un botón con los argumentos pasados.
     * @param text Texto que tendra el botón.
     * @param imagePath La ruta en donde se encuentra el icono para el boton.
     * @param actionCommand Comando del botón.
     * @param toolTipText Texto de ayuda del botón.
     * @param mnemonic Clave de la tecla de acceso rapido para el botón.
     * @return Retorna un objeto <code>JButton</code>
     */
    private JButton configurarBoton (String text, String imagePath,
                                     String actionCommand,
                                     String toolTipText) {
        
        JButton button = new JButton (text);
        button.setFocusable(false);
        button.setToolTipText(toolTipText);
        button.addActionListener(controlador);
        button.setActionCommand(actionCommand);
        button.setIcon(UtilImg.createImageIcon (imagePath));
        
        return button;
    }
    
    private JPanel panelMensEnviado () {
        //Creacion de la barra de herramientas.
        JToolBar jtbTipoMensRecibido = new JToolBar();
        //Agrego borde al toolBar.
        jtbTipoMensRecibido.setBorder(new EtchedBorder());
        
        //Configuro los botones para agregar al toolbar.
        JButton jbMensNormal = new JButton ("Mensaje normal");
        jbMensNormal.addActionListener(controlador);
        jbMensNormal.setActionCommand("jbMensNormal");
        
        JButton jbMensProg = new JButton ("Mensaje programado");
        jbMensProg.addActionListener(controlador);
        jbMensProg.setActionCommand("jbMensProg");
        
        jtbTipoMensRecibido.add (jbMensNormal);
        jtbTipoMensRecibido.add (new JToolBar.Separator ());
        jtbTipoMensRecibido.add (jbMensProg);
        
        jpTipoMensj = new JPanel (new CardLayout());
        
        String [] colums = {"Para", "Asunto", "Contenido", "Hora envio", "Fecha envio"};
        jpTipoMensj.add ("mensajeNormal", crearPanelConTabla(colums, jtMensEnviados));
        
        colums = new String [] {"Estado", "Para", "Asunto", "Contenido", "Fecha envio", "Hora envio", "Cant enviado"};
        jpTipoMensj.add ("mensajeProg", crearPanelConTabla(colums, jtMensProg));
        
        JPanel jpMensRecibido = new JPanel (new BorderLayout());
        jpMensRecibido.add(jtbTipoMensRecibido, BorderLayout.PAGE_START);
        jpMensRecibido.add(jpTipoMensj, BorderLayout.CENTER);
        
        return jpMensRecibido;
    }
    
    private JPanel panelDeMensajes () {
        JPanel panel = new JPanel (new CardLayout());
        
        //Inserto panel 1.
        panel.add("jpMensEnviado", panelMensEnviado());

        String [] colums = {"Leeido", "De", "Asunto", "Contenido", "Fecha envío", "Hora envio"};
        //Inserto panel 2.
        panel.add("jpMensRecibido", crearPanelConTabla(colums, jtMensRecibido));

        colums = new String []{"Para", "Asunto", "Contenido", "Fecha eliminado", "Hora eliminado"};
        //Inserto panel 3.
        panel.add("jpMensEliminado", crearPanelConTabla(colums, jtMensEliminado));

        colums = new String [] {"Para", "Asunto", "Contenido", "Fecha guardada", "Hora guardada"};
        //Inserto panel 4.
        panel.add("jpMensGuardado", crearPanelConTabla(colums, jtMensGuardado));
        
        return panel;
    }
    
    private JPanel crearPanelConTabla (String [] colums, JTable table) {
        table = new JTable (new DefaultTableModel (colums, 0));

        JPanel panel = new JPanel (new GridLayout(1, 1));
        panel.add(new JScrollPane (table));

        return panel;
    }
    
    public JPanel getMens () {
        return jpMensajes;
    }
    
    public JPanel getTipoMens () {
        return jpTipoMensj;
    }
}