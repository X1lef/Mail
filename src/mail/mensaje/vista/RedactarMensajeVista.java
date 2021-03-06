package mail.mensaje.vista;

import java.awt.*;
import javax.swing.*;
import java.io.File;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileFilter;
import mail.mensaje.controlador.RedactarMensajeControlador;

/**
 * @author Félix Pedrozo
 */
public class RedactarMensajeVista extends JDialog {
    private JTextArea jtaMensaje;
    private JScrollPane jScrollPane;
    private JToolBar jtbRedactarMensaje, jtbPropiedadesMensaje;
    private JLabel jlDestinatario, jlRemitente, jlAsunto;
    private JTextField jtfRemitente, jtfDestinatario, jtfAsunto;
    private JMenuBar jMenuBar;
    private JMenu jmEditar, jmInsertar, jmArchivo;
    private JPanel jpBotones, jpPropiedadesMensaje, jpRedactarMensaje;
    private JButton jbEnviar, jbHistorialEnvio, jbCancelar, jbGuardar,
        jbProgramarEnvio, jbContactosIr, jbNegrita, jbCursiva, jbAgregarImagen,
        jbAgregarLink, jbSubrayado;
    private final RedactarMensajeControlador controlador;
    
    public RedactarMensajeVista (JFrame frame) {
        super(frame, "Redactar mensaje");
        controlador = new RedactarMensajeControlador(this);
        crearIU();
        
        setVisible(true);
    }
    
    private void crearIU () {
        //Configración de las propiedades del Dialog.
        setModal(true);
        setSize(900, 650);
        //Configuro para que al abrir la ventana, se muestre al medio de la pantalla.
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //Configurando los componentes del jpRedactarMensaje.
        jpRedactarMensaje = new JPanel (new BorderLayout ());
        
        //Inicializo la toolBar que perttenece a redactar mensaje.
        jtbRedactarMensaje = new JToolBar ();
        
        jbAgregarImagen = new JButton ();
        jbAgregarImagen.setToolTipText("Agregar imagen");
        jbAgregarImagen.setName ("jbAgregarImagen");
        jbAgregarImagen.addActionListener(controlador);
        jbAgregarImagen.setIcon(
            new ImageIcon(getClass().getResource("iconos/imagen.png"))
        );
        jbAgregarLink = new JButton ();
        jbAgregarLink.setToolTipText("Agregar hipervínculo");
        jbAgregarLink.setIcon(
            new ImageIcon(getClass().getResource("iconos/link.png"))
        );
        jbNegrita = new JButton ();
        jbNegrita.setToolTipText("Negrita");
        jbNegrita.setIcon(
            new ImageIcon(getClass().getResource("iconos/negrita.png"))
        );
        jbCursiva = new JButton ();
        jbCursiva.setToolTipText("Cursiva");
        jbCursiva.setIcon(
            new ImageIcon(getClass().getResource("iconos/cursiva.png"))
        );
        jbSubrayado = new JButton ();
        jbSubrayado.setToolTipText("Subrayado");
        jbSubrayado.setIcon(
            new ImageIcon(getClass().getResource("iconos/subrayado.png"))
        );
        
        jtbRedactarMensaje.add (jbNegrita);
        jtbRedactarMensaje.add (jbCursiva);
        jtbRedactarMensaje.add (jbSubrayado);
        jtbRedactarMensaje.add (jbAgregarImagen);
        jtbRedactarMensaje.add (jbAgregarLink);
        
        jpRedactarMensaje.add (jtbRedactarMensaje, BorderLayout.NORTH);
        
        jtaMensaje = new JTextArea(20, 100);
        jtaMensaje.setLineWrap(true);
        //Le agrego al TextArea un scrollPane.
        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jtaMensaje);
        
        jpRedactarMensaje.add (jScrollPane, BorderLayout.CENTER);
        
        //Configurando los componentes del jpPropiedadesMensaje.
        jpPropiedadesMensaje = new JPanel (new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints ();
        
        //Componente de la fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(20, 10, 10, 10);
        
        jlRemitente = new JLabel ("De");
        jpPropiedadesMensaje.add (jlRemitente, constraints);
        
         //Componente de la fila 1 columna 0.
         constraints.gridy = 1;
         constraints.insets = new Insets (0, 10, 10, 10);
         
         jlDestinatario = new JLabel ("Para");
         jpPropiedadesMensaje.add (jlDestinatario, constraints);
         
         //Componente de la fila 2 columna 0.
         constraints.gridy = 2;

         jlAsunto = new JLabel ("Asunto");
         jpPropiedadesMensaje.add (jlAsunto, constraints);
         
         //Componente de la fila 1 columna 2.
         constraints.gridx = 2;
         constraints.gridy = 1;
         constraints.insets = new Insets(0, 0, 10, 10);
         
         jbContactosIr = new JButton("...");
         jbContactosIr.setToolTipText("Ir a contactos (Ctrl+Alt+C)");
         jbContactosIr.setName("jbContactosIr");
         jbContactosIr.addActionListener(controlador);
         jpPropiedadesMensaje.add (jbContactosIr, constraints);
         
         //Componente de la fila 0 columna 1.
         constraints.gridx = 1;
         constraints.gridy = 0;
         constraints.gridwidth = 2;
         constraints.weightx = 1.0;
         constraints.anchor = GridBagConstraints.CENTER;
         constraints.fill = GridBagConstraints.HORIZONTAL;
         constraints.insets = new Insets(20, 0, 10, 10);
         
         //TODO : Dato de prueba.
         jtfRemitente = new JTextField ("horacionfs@gmail.com");
         jtfRemitente.setEnabled(false);
         
         jpPropiedadesMensaje.add (jtfRemitente, constraints);
         
          //Componente de la fila 1 columna 1.
         constraints.gridy = 1;
         constraints.gridwidth = 1;
         constraints.insets = new Insets (0, 0, 10, 10);
         
         jtfDestinatario = new JTextField ();
         jpPropiedadesMensaje.add (jtfDestinatario, constraints);
         
         //Componente de la fila 2 columna 1.
         constraints.gridy = 2;
         constraints.gridwidth = 2;
         
         jtfAsunto = new JTextField ();
         jpPropiedadesMensaje.add (jtfAsunto, constraints);
         
         //Componente de la fila 3 columna 0.
         constraints.gridx = 0;
         constraints.gridy = 3;
         constraints.gridwidth = 3;
         constraints.weighty = 1.0;
         constraints.fill = GridBagConstraints.BOTH;
         constraints.insets = new Insets(5, 10, 0, 10);
         
         jpRedactarMensaje.setBorder(BorderFactory.createEtchedBorder());
         
         jpPropiedadesMensaje.add (jpRedactarMensaje, constraints);
         
        //Inserto los componentes al JDialog que posee un gestor de diseño BorderLayout.
        jMenuBar = new JMenuBar();
        
        jmArchivo = new JMenu("Archivo");
        jmEditar = new JMenu("Editar");
        jmInsertar = new JMenu("Insertar");
        
        jMenuBar.add(jmArchivo);
        jMenuBar.add(jmEditar);
        jMenuBar.add(jmInsertar);
        
        setJMenuBar(jMenuBar);
        
        //Configuración de los componentes del Toolbar.
        jtbPropiedadesMensaje = new JToolBar ();
        //Agrego borde al Toolbar.
        jtbPropiedadesMensaje.setBorder(new SoftBevelBorder (BevelBorder.RAISED));
        //Inserción de los botones al Toolbar.
        jbGuardar = new JButton ("Guardar mensaje", 
                new ImageIcon (getClass().getResource("/mail/mensaje/vista/iconos/guardar_mensaje.png"))
        );
        jbGuardar.setToolTipText("Guardar mensaje (Ctrl+G)");
        jbGuardar.setFocusable(false);
        jtbPropiedadesMensaje.add(jbGuardar);
        
        jbProgramarEnvio = new JButton ("Programar envío", 
                new ImageIcon (getClass().getResource("/mail/mensaje/vista/iconos/time.png"))
        );
        jbProgramarEnvio.setToolTipText("Programar envío (Ctrl+E)");
        jbProgramarEnvio.setName("jbProgramarEnvio");
        jbProgramarEnvio.setFocusable (false);
        jbProgramarEnvio.addActionListener(controlador);
        jtbPropiedadesMensaje.add (jbProgramarEnvio);
        
        jpBotones = new JPanel (new FlowLayout (FlowLayout.RIGHT, 10, 10));
        jbCancelar = new JButton ("Cancelar");
        jbCancelar.setName("jbCancelar");
        jbCancelar.addActionListener(controlador);
        
        jbHistorialEnvio = new JButton ("Historial de envío");
        jbHistorialEnvio.setName("jbHistorialEnvio");
        jbHistorialEnvio.addActionListener(controlador);
        
        jbEnviar = new JButton ("Enviar");
        
        jpBotones.add (jbEnviar);
        jpBotones.add (jbHistorialEnvio);
        jpBotones.add (jbCancelar);
        
        //Añado los componentes al Dialog.
        add (jtbPropiedadesMensaje, BorderLayout.NORTH);
        add (jpPropiedadesMensaje, BorderLayout.CENTER);
        add (jpBotones, BorderLayout.SOUTH);
    }
    
    public void mostrarSelectorDeArchivos () {
        JFileChooser fileChooser = new JFileChooser();
        //Creo una clase anonima que contiene la configuracion de filtrado de las imagenes.
        fileChooser.setFileFilter(new FileFilter () {
            @Override
            public boolean accept(File f) {
                String path = f.getPath();
                return (f.isDirectory() || path.endsWith(UtilImg.GIF) ||
                        path.endsWith(UtilImg.JPEG) || 
                        path.endsWith(UtilImg.JPG) ||
                        path.endsWith(UtilImg.PNG));
            }
            @Override
            public String getDescription() {
                return "Imagenes";
            }
        });
        //Muestro el selector de archivos.
        fileChooser.showOpenDialog(this);
    }
}