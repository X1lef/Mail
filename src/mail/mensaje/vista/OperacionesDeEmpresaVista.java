package mail.mensaje.vista;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mail.mensaje.controlador.OperacionesDeEmpresaControlador;

/**
 * @author Félix Pedrozo
 */
public class OperacionesDeEmpresaVista extends JDialog {
    private JLabel jlEmail, jlEmpresa, jlDireccion, jlFiltrar;
    private JTextField jtfEmpresa, jtfDireccion, jtfEmail, jtfBuscar;
    private JButton jbGuardar, jbCancelar, jbBuscar, jbEliminar;
    private JPanel jpABM, jpBuscar, jpBotones, jpConsulta;
    private JRadioButton jrbEmpresa, jrbEmail;
    private JTabbedPane jTabbedPane;
    private JTable jtEmpresa;
    private JScrollPane jspTablaEmpresa;

    public OperacionesDeEmpresaVista (JFrame frame, int indice,
            OperacionesDeEmpresaControlador controlador) {
        super (frame, "Empresa");
        crearIU (indice, controlador);
    }
    
    public OperacionesDeEmpresaVista (JFrame frame,
            OperacionesDeEmpresaControlador controlador) {
        this (frame, 0, controlador);
    }
    
    public OperacionesDeEmpresaVista (JDialog dialog, int indice,
            OperacionesDeEmpresaControlador controlador) {
        super (dialog, "Empresa");
        crearIU (indice, controlador);
    }
    
    public OperacionesDeEmpresaVista (JDialog dialog,
            OperacionesDeEmpresaControlador controlador) {
        this (dialog, 0, controlador);
    }

    private void crearIU (int indice, OperacionesDeEmpresaControlador controlador) {
        setSize(570, 450);
        setLayout(new GridLayout(1, 0));
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //Configuro los componentes para el panel jpBotones.
        jbGuardar = new JButton ("Guardar");
        
        jbEliminar = new JButton ("Eliminar");
        jbEliminar.setEnabled(false);
        
        jbCancelar = new JButton ("Cancelar");
        jbCancelar.setActionCommand("jbCancelar");
        jbCancelar.addActionListener(controlador);

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

        jlEmpresa = new JLabel("Empresa :");
        jpABM.add (jlEmpresa, constraints);

        //Componente de la fila 1 columna 0.
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 20, 10, 20);

        jlDireccion = new JLabel("Dirección :");
        jpABM.add(jlDireccion, constraints);

        //Componente de la fila 2 columna 0.
        constraints.gridy = 2;

        jlEmail = new JLabel("Email :");
        jpABM.add (jlEmail, constraints);

        //Componente de la fila 0 columna 1.
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(20, 0 ,10, 20);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        jtfEmpresa = new JTextField(20);
        jpABM.add (jtfEmpresa, constraints);

        //Componente de la fila 1 columna 1.
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 10, 20);

        jtfDireccion = new JTextField(20);
        jpABM.add (jtfDireccion, constraints);

        //Componente de la fila 2 columna 1.
        constraints.gridy = 2;

        jtfEmail = new JTextField(20);
        jpABM.add (jtfEmail, constraints);

        //Componente de la fila 3 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
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
        constraints.gridwidth = 3;
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

        jrbEmpresa = new JRadioButton("Empresa", true);
        buttonGroup.add (jrbEmpresa);
        jpBuscar.add (jrbEmpresa, constraints);

        //Componente de la fila 1 columna 2.
        constraints.gridx = 2;

        jrbEmail = new JRadioButton("Email");
        buttonGroup.add (jrbEmail);
        jpBuscar.add (jrbEmail, constraints);

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

        jtEmpresa = new JTable(new DefaultTableModel(
                new String [] {"Empresa", "Dirección", "Email"},
                5
        ));
        jspTablaEmpresa = new JScrollPane(jtEmpresa);
        jpConsulta.add (jspTablaEmpresa, constraints);

        //Inserto los paneles al TabbedPane.
        jTabbedPane = new JTabbedPane();
        jTabbedPane.add ("ABM", jpABM);
        jTabbedPane.add ("Consulta", jpConsulta);
        //Le indico el indice de la pestaña que se mostrara.
        jTabbedPane.setSelectedIndex(indice);

        //Inserto al Dialog el TabbedPane.
        add (jTabbedPane);
    }
}
