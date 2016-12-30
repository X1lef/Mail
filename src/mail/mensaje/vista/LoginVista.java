package mail.mensaje.vista;

import javax.swing.*;
import java.awt.*;
import mail.mensaje.controlador.LoginControlador;

/**
 * @author Félix Pedrozo
 * @author Micky Martínez
 */
public class LoginVista extends JFrame {
    private JButton jbAcceder, jbCancelar;
    private JLabel jlUser, jlPassword, jlRol, jlMensError;
    private JTextField jtfUser;
    private JPasswordField jpfPassword;
    private JComboBox jcbRol;
    private Component rigidArea;
    private final LoginControlador controlador;

    public LoginVista () {
        controlador = new LoginControlador(this);
        crearVistaLogin ();
        
        setVisible(true);
    }

    private void crearVistaLogin () {
        setSize (355, 320);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout ());
        setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE);

        GridBagConstraints conf = new GridBagConstraints();

        //Fila 0 columna 0.
        conf.gridx = conf.gridy = 0;
        conf.gridwidth = 2;
        conf.weightx = conf.weighty = 1.0;
        conf.anchor = GridBagConstraints.NORTHEAST;
        conf.fill = GridBagConstraints.HORIZONTAL;

        add(panelDeBienvenida(), conf);

        //Fila 1 columna 0.
        conf.gridy = 1;
        conf.gridwidth = 1;
        conf.weightx = conf.weighty = 0.0;
        conf.fill = GridBagConstraints.NONE;
        conf.insets = new Insets(27, 27, 20, 10);

        jlUser = new JLabel ("Usuario");
        add(jlUser, conf);

        //Fila 2 columna 0.
        conf.gridy = 2;
        conf.insets = new Insets(0, 27, 20, 10);

        jlPassword = new JLabel("Contraseña");
        add(jlPassword, conf);

        //Fila 3 columna 0.
        conf.gridy = 3;

        jlRol = new JLabel ("Rol");
        add(jlRol, conf);

        //Fila 1 columna 1.
        conf.gridx = conf.gridy = 1;
        conf.weightx = 1.0;
        conf.fill = GridBagConstraints.HORIZONTAL;
        conf.insets = new Insets (27, 10, 20, 27);

        jtfUser = new JTextField();
        jtfUser.addFocusListener(controlador);
        add(jtfUser, conf);

        //Fila 2 columna 1.
        conf.gridy = 2;
        conf.insets = new Insets(0, 10, 20, 27);

        jpfPassword = new JPasswordField();
        jpfPassword.addFocusListener(controlador);
        add(jpfPassword, conf);

        //Fila 3 columna 1.
        conf.gridy = 3;
        conf.weighty = 1.0;

        //TODO : Datos de prueba. Se debe cargar mediante la base de datos.
        jcbRol = new JComboBox (new String [] {"Item", "Item", "Item"});
        add(jcbRol, conf);

        //Fila 4 columna 1.
        conf.gridy = 4;
        conf.insets = new Insets(0, 10, 0, 27);

        jlMensError = new JLabel(null, new ImageIcon (getClass().getResource("iconos/error.png")), JLabel.LEFT);
        jlMensError.setVisible(false);
        jlMensError.setForeground(Color.RED);
        add(jlMensError, conf);
        
        //Area rigida para no permitir que se deforme los componentes.
        rigidArea = Box.createRigidArea(new Dimension(0, 16));
        add(rigidArea, conf);

        //Fila 5 columna 0.
        conf.gridx = 0;
        conf.gridy = 5;
        conf.weighty = 0.0;
        conf.gridwidth = 2;
        conf.insets = new Insets(20, 0, 5, 20);

        add(panelDeBotones(), conf);
    }

    private JPanel panelDeBienvenida () {
        JPanel panel = new JPanel ();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(51, 51, 51));

        //Configuro los campos de textos.
        JLabel jlBienvenida = new JLabel ("Bienvenido a Mail");
        jlBienvenida.setFont(new Font("Tahoma", Font.BOLD, 15));
        jlBienvenida.setForeground(Color.white);

        JLabel jlInfo = new JLabel ("Por favor ingrese sus credenciales de acceso.");
        jlInfo.setFont(new Font("Tahoma", Font.PLAIN, 11));
        jlInfo.setForeground(Color.white);

        //Cargo los label al panel.
        panel.add(Box.createRigidArea(new Dimension(30, 15)));
        panel.add(jlBienvenida);
        panel.add(Box.createRigidArea(new Dimension (0, 5)));
        panel.add(jlInfo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        return panel;
    }

    private JPanel panelDeBotones () {
        JPanel panel = new JPanel (new FlowLayout(FlowLayout.RIGHT));

        jbAcceder = new JButton ("Acceder");
        jbAcceder.setActionCommand("jbAcceder");
        jbAcceder.addActionListener(controlador);

        jbCancelar = new JButton ("Cancelar");
        jbCancelar.setActionCommand("jbCancelar");
        jbCancelar.addActionListener(controlador);

        panel.add(jbAcceder);
        panel.add(jbCancelar);

        return panel;
    }

    public String getUser () {
        return jtfUser.getText();
    }

    public char [] getPassword () {
        return jpfPassword.getPassword();
    }

    public boolean datosValidos () {
        return !jtfUser.getText().trim().isEmpty() && jpfPassword.getPassword().length != 0;
    }

    public void mostrarMensError (String msj) {
        jlMensError.setText(msj);
        rigidArea.setVisible(false);
        jlMensError.setVisible(true);
    }
    
    public void esconderMensError () {
        jlMensError.setVisible(false);
        rigidArea.setVisible(true);
    }
    
    public boolean isVisibleMensError () {
        return jlMensError.isVisible();
    }
}
