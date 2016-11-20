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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mail.mensaje.controlador.OperacionesDeEmpresaControlador;

/**
 * Clase que permitira realizar operaciones con las empresas.
 * 
 * @author Félix Pedrozo
 */
public class OperacionesDeEmpresaVista extends JDialog {
    private JLabel jlEmail, jlEmpresa, jlDireccion, jlFiltrar;
    private JTextField jtfEmpresa, jtfDireccion, jtfEmail, jtfBuscar;
    private JButton jbGuardar, jbCancelar, jbBuscar, jbEliminar;
    private JRadioButton jrbEmpresa, jrbEmail;
    private JTabbedPane jTabbedPane;
    private JTable jtEmpresa;
    private JScrollPane jspTablaEmpresa;
    private OperacionesDeEmpresaControlador controlador;

    public OperacionesDeEmpresaVista (JFrame frame, int indexTab,
            OperacionesDeEmpresaControlador controlador) {
        super (frame, "Empresa");
        //Obtengo la referencia del controlador.
        this.controlador = controlador;
        crearIU (indexTab);
    }
    
    public OperacionesDeEmpresaVista (JFrame frame,
            OperacionesDeEmpresaControlador controlador) {
        this (frame, 0, controlador);
    }
    
    public OperacionesDeEmpresaVista (JDialog dialog, int indexTab,
            OperacionesDeEmpresaControlador controlador) {
        super (dialog, "Empresa");
        //Obtengo la referencia del controlador.
        this.controlador = controlador;
        crearIU (indexTab);
    }
    
    public OperacionesDeEmpresaVista (JDialog dialog,
            OperacionesDeEmpresaControlador controlador) {
        this (dialog, 0, controlador);
    }

    private void crearIU (int indexTab) {
        setSize(570, 450);
        setLayout(new GridLayout(1, 0));
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        jTabbedPane = new JTabbedPane();
        //Inserto los paneles al TabbedPane.
        jTabbedPane.add ("ABM", addPanelABM());
        jTabbedPane.add ("Consulta", addPanelConsulta());
        //Le indico el indice de la pestaña que se mostrara al iniciar.
        jTabbedPane.setSelectedIndex(indexTab);

        //Inserto el TabbedPane al Dialog.
        add (jTabbedPane);
    }
    
    private JPanel addPanelBuscar () {
        JPanel jpBuscar = new JPanel(new GridBagLayout());
        jpBuscar.setBorder (BorderFactory.createEtchedBorder());
        
        GridBagConstraints constraints = new GridBagConstraints();

        //Componente de la fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
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

        jrbEmpresa = new JRadioButton("Empresa", true);
        buttonGroup.add (jrbEmpresa);
        jpBuscar.add (jrbEmpresa, constraints);

        //Componente de la fila 1 columna 2.
        constraints.gridx = 2;

        jrbEmail = new JRadioButton("Email");
        buttonGroup.add (jrbEmail);
        jpBuscar.add (jrbEmail, constraints);
        
        return jpBuscar;
    }
    
    //Configuro los componentes del panel jpConsulta.
    private JPanel addPanelConsulta () {
        //Configuro los componentes para el panel jpConsulta.
        JPanel jpConsulta = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        //Componente de la fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        jpConsulta.add (addPanelBuscar(), constraints);

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
        
        return jpConsulta;
    }
    
    //Configuro los componentes para el panel jpABM.
    private JPanel addPanelABM () {
        JPanel jpABM = new JPanel (new GridBagLayout());
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

        jpABM.add (addPanelBotones(), constraints);
        
        return jpABM;
    }
    
    /**
     * Configuación del panel Botones
     * @return Retorna el panel configurado.
     */
    private JPanel addPanelBotones () {
        jbGuardar = new JButton ("Guardar");
        jbGuardar.setActionCommand("jbGuardar");
        jbGuardar.addActionListener(controlador);
        
        jbEliminar = new JButton ("Eliminar");
        jbEliminar.setActionCommand("jbEliminar");
        jbEliminar.addActionListener(controlador);
        jbEliminar.setEnabled(false);
        
        jbCancelar = new JButton ("Cancelar");
        jbCancelar.setActionCommand("jbCancelar");
        jbCancelar.addActionListener(controlador);

        JPanel jpBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        jpBotones.add (jbGuardar);
        jpBotones.add (jbEliminar);
        jpBotones.add (jbCancelar);
        
        return jpBotones;
    }
          
    /**
     * Metodo que permite saber si los campos estan vacíos.
     * @param indexTab Indice de la pestaña en la que se comprobara si los campos estan vacíos.
     * @return Estado de los campos, true si algun campo esta vacío y false si todos estan llenos.
     */
    public boolean estaVacio (int indexTab) {
        if (indexTab == 1)
            return (jtfEmpresa.getText().trim().isEmpty() || 
                    jtfDireccion.getText().trim().isEmpty() ||
                    jtfEmail.getText().trim().isEmpty());
        else
            return jtfBuscar.getText().trim().isEmpty();
    }
    
    /**
     * Muestra diferentes tipos de mensajes.
     * @param mensaje El mensaje ha ser mostrado.
     * @param tipoMensaje Tipo de mensaje que se mostrara.
     */
    public void mostrarMensaje (String mensaje, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", tipoMensaje);
    }
    
    public void limpiarCampos () {
        jtfEmpresa.setText(null);
        jtfDireccion.setText(null);
        jtfEmail.setText(null);
    }
}
