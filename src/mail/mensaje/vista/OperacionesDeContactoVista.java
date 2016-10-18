package mail.mensaje.vista;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @author Félix Pedrozo
 */
public class OperacionesDeContactoVista extends JDialog {
    private JLabel jlNombre, jlApellido, jlEmail, jlEmpresa, jlFiltrar;
    private JTextField jtfNombre, jtfApellido, jtfEmail, jtfBuscar;
    private JComboBox <String> jcbEmpresa;
    private JButton jbIngresarEmpresa, jbGuardar, jbCancelar, jbBuscar, jbEliminar;
    private JPanel jpABM, jpBuscar, jpBotones, jpConsulta;
    private JRadioButton jrbNombre, jrbApellido, jrbEmail, jrbEmpresa;
    private JTabbedPane jTabbedPane;
    private JTable jtContacto;
    private JScrollPane jspTablaContacto;

    public OperacionesDeContactoVista (JFrame frame) {
        super (frame, "Contactos");
        crearIU ();
    }

    private void crearIU () {
        setSize(570, 450);
        setLayout(new GridLayout(1, 0));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //Configuro los componentes para el panel jpBotones.
        jbGuardar = new JButton ("Guardar");
        jbEliminar = new JButton ("Eliminar");
        jbCancelar = new JButton ("Cancelar");

        jpBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        jpBotones.add (jbGuardar);
        jpBotones.add (jbEliminar);
        jpBotones.add (jbCancelar);

        //Configuro los componentes para el panel jpABM.
        jpABM = new JPanel (new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        //Componente de la fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(20, 20, 10, 20);

        jlNombre = new JLabel("Nombre :");
        jpABM.add (jlNombre, constraints);

        //Componente de la fila 1 columna 0.
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 20, 10, 20);

        jlApellido = new JLabel("Apellido :");
        jpABM.add(jlApellido, constraints);

        //Componente de la fila 2 columna 0.
        constraints.gridy = 2;

        jlEmail = new JLabel("Email :");
        jpABM.add (jlEmail, constraints);

        //Componente de la fila 3 columna 0.
        constraints.gridy = 3;

        jlEmpresa = new JLabel("Empresa :");
        jpABM.add (jlEmpresa, constraints);

        //Componente de la fila 0 columna 1.
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(20, 0 ,10, 20);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        jtfNombre = new JTextField(20);
        jpABM.add (jtfNombre, constraints);

        //Componente de la fila 1 columna 1.
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 10, 20);

        jtfApellido = new JTextField(20);
        jpABM.add (jtfApellido, constraints);

        //Componente de la fila 2 columna 1.
        constraints.gridy = 2;

        jtfEmail = new JTextField(20);
        jpABM.add (jtfEmail, constraints);

        //Componente de la fila 3 colummna 1.
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 0, 10, 10);

        //TODO : Items de prueba.
        jcbEmpresa = new JComboBox<>(new String [] {"Axe", "Rexona", "Nivea"});
        jpABM.add (jcbEmpresa, constraints);

        //Componente de la fila 3 columna 2.
        constraints.gridx = 2;
        constraints.weightx = 0.0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0, 0, 10, 20);

        jbIngresarEmpresa = new JButton ("Añadir empresa");
        jpABM.add (jbIngresarEmpresa, constraints);

        //Componente de la fila 4 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 0, 0);

        jpABM.add (jpBotones, constraints);

        //Configuro los componentes del panel jpConsulta.
        jpBuscar = new JPanel(new GridBagLayout());
        jpBuscar.setBorder (BorderFactory.createEtchedBorder());

        //Componente de la fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 20, 10, 10);

        jtfBuscar = new JTextField(25);
        jpBuscar.add (jtfBuscar, constraints);

        //Componente de la fila 0 columna 4.
        constraints.gridx = 4;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 0, 10, 10);

        jbBuscar = new JButton("Buscar");
        constraints.fill = GridBagConstraints.NONE;
        jpBuscar.add (jbBuscar, constraints);

        //Componente de la fila 1 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(0, 20, 10, 20);

        jlFiltrar = new JLabel("Filtrar por :");
        jpBuscar.add (jlFiltrar, constraints);

        ButtonGroup buttonGroup = new ButtonGroup();

        //Componente de la fila 1 columna 1.
        constraints.gridx = 1;
        constraints.insets = new Insets(0, 0, 10, 20);

        jrbNombre = new JRadioButton("Nombre", true);
        buttonGroup.add (jrbNombre);
        jpBuscar.add (jrbNombre, constraints);

        //Componente de la fila 1 columna 2.
        constraints.gridx = 2;

        jrbApellido = new JRadioButton("Apellido");
        buttonGroup.add (jrbApellido);
        jpBuscar.add (jrbApellido, constraints);

        //Componente de la fila 1 columna 3.
        constraints.gridx = 3;

        jrbEmail = new JRadioButton("Email");
        buttonGroup.add (jrbEmail);
        jpBuscar.add (jrbEmail, constraints);

        //Componente de la fila 1 columna 4.
        constraints.gridx = 4;

        jrbEmpresa = new JRadioButton("Empresa");
        buttonGroup.add (jrbEmpresa);
        jpBuscar.add (jrbEmpresa, constraints);

        //Configuro los componentes para el panel jpConsulta.
        jpConsulta = new JPanel(new GridBagLayout());

        //Componente de la fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        jpConsulta.add (jpBuscar, constraints);

        //Componente de la fila 1 columna 0.
        constraints.gridy = 1;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets (0, 10, 10,10);

        jtContacto = new JTable(new DefaultTableModel(
                new String [] {"Nombre", "Apellido", "Email", "Empresa"},
                5
        ));
        jspTablaContacto = new JScrollPane(jtContacto);
        jpConsulta.add (jspTablaContacto, constraints);

        //Inserto los paneles al TabbedPane.
        jTabbedPane = new JTabbedPane();
        jTabbedPane.add ("ABM", jpABM);
        jTabbedPane.add ("Consulta", jpConsulta);

        //Inserto al Dialog el TabbedPane.
        add (jTabbedPane);
    }
}
