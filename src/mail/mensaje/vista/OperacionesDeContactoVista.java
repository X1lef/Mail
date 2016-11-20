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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import mail.mensaje.controlador.OperacionContactoControlador;

/**
 * Clase que permitira realizar operaciones con los contactos.
 * 
 * @author Félix Pedrozo
 */
public class OperacionesDeContactoVista extends JDialog {
    private JLabel jlNombre, jlApellido, jlEmail, jlEmpresa, jlFiltrar;
    private JTextField jtfNombre, jtfApellido, jtfEmail, jtfBuscar;
    private JComboBox <String> jcbEmpresa;
    private JButton jbIngresarEmpresa, jbGuardar, jbCancelar, jbBuscar, jbEliminar;
    private JRadioButton jrbNombre, jrbApellido, jrbEmail, jrbEmpresa;
    private JTabbedPane jTabbedPane;
    private JTable jtContacto;
    private JScrollPane jspTablaContacto;
    private OperacionContactoControlador controlador;

    public OperacionesDeContactoVista (JFrame frame, int indice,
            OperacionContactoControlador controlador) {
        super (frame, "Contactos");
        this.controlador = controlador;
        crearIU (indice);
    }
    
    public OperacionesDeContactoVista (JFrame frame, 
              OperacionContactoControlador controlador) {
        this (frame, 0, controlador);
    }
    
    public OperacionesDeContactoVista (JDialog dialog, int indice,
                OperacionContactoControlador controlador) {
        super (dialog, "Contactos");
        this.controlador = controlador;
        crearIU (indice);
    }
    
    public OperacionesDeContactoVista (JDialog dialog,
                OperacionContactoControlador controlador) {
        this (dialog, 0, controlador);
    }
    
    private void crearIU (int indexTab) {
        setModal(true);
        setSize(570, 450);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 0));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        //Inserto los paneles al TabbedPane.
        jTabbedPane = new JTabbedPane();
        jTabbedPane.add ("ABM", agregarPanelABM());
        jTabbedPane.add ("Consulta", agregarPanelConsulta());
        //Le indico el indice de la pestaña que se mostrara.
        jTabbedPane.setSelectedIndex(indexTab);

        //Inserto al Dialog el TabbedPane.
        add (jTabbedPane);
    }
    
    //Configuro los componentes para el panel jpBotones.
    private JPanel agregarPanelBotones () {
        jbGuardar = new JButton ("Guardar");
        jbGuardar.setActionCommand("jbGuardar");
        jbGuardar.addActionListener(controlador);
        
        jbEliminar = new JButton ("Eliminar");
        jbEliminar.setEnabled(false);
        jbEliminar.setActionCommand("jbEliminar");
        jbEliminar.addActionListener(controlador);
        
        jbCancelar = new JButton ("Cancelar");
        jbCancelar.setActionCommand("jbCancelar");
        jbCancelar.addActionListener(controlador);

        JPanel jpBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        jpBotones.add (jbGuardar);
        jpBotones.add (jbEliminar);
        jpBotones.add (jbCancelar);
        
        return jpBotones;
    }
    
    private JPanel agregarPanelConsulta () {
         JPanel jpConsulta = new JPanel(new GridBagLayout());
         
         GridBagConstraints constraints = new GridBagConstraints();

        //Componente de la fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        jpConsulta.add (agregarPanelBuscar(), constraints);

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
        
        return jpConsulta;
    }
    
    private JPanel agregarPanelBuscar () {
        //Configuro los componentes del panel jpConsulta.
        JPanel jpBuscar = new JPanel(new GridBagLayout());
        jpBuscar.setBorder (BorderFactory.createEtchedBorder());
        
        GridBagConstraints constraints = new GridBagConstraints();

        //Componente de la fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
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
        jbBuscar.setActionCommand("jbBuscar");
        jbBuscar.addActionListener(controlador);
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
        
        return jpBuscar;
    }
    
    private JPanel agregarPanelABM () {
         JPanel jpABM = new JPanel (new GridBagLayout());

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
        jbIngresarEmpresa.addActionListener(controlador);
        jbIngresarEmpresa.setActionCommand("jbIngresarEmpresa");
        jpABM.add (jbIngresarEmpresa, constraints);

        //Componente de la fila 4 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 0, 0);

        jpABM.add (agregarPanelBotones(), constraints);

        return jpABM;
    }
    
    public boolean estaVacio (int indexTab) {
        if (indexTab == 1)
            return (jtfNombre.getText().trim().isEmpty() || 
                    jtfApellido.getText().trim().isEmpty() ||
                    jtfEmail.getText().trim().isEmpty());
        else
            return jtfBuscar.getText().trim().isEmpty();
    }
    
    public void mostrarMensaje (String m, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, m, "Aviso", tipoMensaje);
    }
    
    public void limpiarCampos () {
        jtfNombre.setText(null);
        jtfApellido.setText(null);
        jtfEmail.setText(null);
    }
}
