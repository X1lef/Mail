package mail.mensaje.vista;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import mail.mensaje.controlador.OperacionesDeEmpresaControlador;
import mail.mensaje.modelo.dao.EmpresaDAO;
import mail.mensaje.modelo.vo.Empresa;

/**
 * Clase que permitira realizar operaciones con las empresas.
 * 
 * @author Félix Pedrozo
 */
public class OperacionesDeEmpresaVista extends JDialog {
    private JLabel jlEmail, jlEmpresa, jlDireccion, jlFiltrar, jlTotalEmpresa;
    private JTextField jtfEmpresa, jtfDireccion, jtfEmail, jtfBuscar;
    private JButton jbGuardar, jbCancelar, jbBuscar, jbEliminar;
    private JRadioButton jrbEmpresa, jrbEmail;
    private JTabbedPane jTabbedPane;
    private JTable jtEmpresa;
    private JScrollPane jspTablaEmpresa;
    public List <Empresa> listEmpresa;
    public static boolean actualizar_eliminar = false;
    private OperacionesDeEmpresaControlador controlador;

    public OperacionesDeEmpresaVista (JFrame frame, int indexTab) {
        super (frame, "Empresa");
        controlador = new OperacionesDeEmpresaControlador (this);
        crearIU (indexTab);
        
        //Cargo tabla.
        cargarTablaTodasLasEmpresas();
        
        setVisible(true);
    }
    
    public OperacionesDeEmpresaVista (JFrame frame) {
        this (frame, 0);
    }
    
    public OperacionesDeEmpresaVista (JDialog dialog, int indexTab) {
        super (dialog, "Empresa");
        //Obtengo la referencia del controlador.
        controlador = new OperacionesDeEmpresaControlador (this);
        crearIU (indexTab);
        
        //Cargar tabla.
        cargarTablaTodasLasEmpresas();
        
        setVisible(true);
    }
    
    public OperacionesDeEmpresaVista (JDialog dialog) {
        this (dialog, 0);
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

        GridBagConstraints conf = new GridBagConstraints();

        //Componente de la fila 0 columna 0.
        conf.gridx = conf.gridy = 0;
        conf.weightx = 1.0;
        conf.insets = new Insets(10, 10, 10, 10);
        conf.fill = GridBagConstraints.HORIZONTAL;

        jpConsulta.add (addPanelBuscar(), conf);

        //Componente de la fila 1 columna 0.
        conf.gridy = 1;
        conf.weighty = 1.0;
        conf.fill = GridBagConstraints.BOTH;
        conf.insets = new Insets (0, 10, 5,10);

        String [] colums = {"Empresa", "Dirección", "Email"};
        jtEmpresa = new JTable(new DefaultTableModel(colums, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        });
        jtEmpresa.addMouseListener(controlador);
        jspTablaEmpresa = new JScrollPane(jtEmpresa);
        
        jpConsulta.add (jspTablaEmpresa, conf);
        
        //Componente de la fila 2 columna 0.
        conf.gridy = 2;
        conf.weightx = conf.weighty = 0.0;
        conf.fill = GridBagConstraints.NONE;
        conf.anchor = GridBagConstraints.WEST;
        conf.insets = new Insets(0, 10, 3, 0);
        
        jlTotalEmpresa = new JLabel(UtilImg.createImageIcon("iconos/ingresar_empresa.png"));
        jpConsulta.add(jlTotalEmpresa, conf);
        
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
        jbGuardar = new JButton ();
        jbGuardar.setActionCommand("jbGuardar");
        jbGuardar.addActionListener(controlador);
        
        jbEliminar = new JButton ("Eliminar");
        jbEliminar.setActionCommand("jbEliminar");
        jbEliminar.addActionListener(controlador);
        
        jbCancelar = new JButton ("Cancelar");
        jbCancelar.setActionCommand("jbCancelar");
        jbCancelar.addActionListener(controlador);
        
        //Desabilito los botones cancelar y eliminar.
        configurarBotones(false, "Guardar");

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
    
    public void cargarTablaTodasLasEmpresas () {
        //Cargo la tabla con todos los registros de las empresas.
        cargarTablaNuevaInfo (new EmpresaDAO().todasLasEmpresas());
        //Actualizo total de empresas.
        ponerTotalDeEmpresa();
    }
    
    public void cargarTablaNuevaInfo (List <Empresa> list) {
        limpiarTabla();
        
        if (list.size() > 0) {
            //Guardo la lista de contactos.
            listEmpresa = list;
            
            DefaultTableModel modelo = (DefaultTableModel)jtEmpresa.getModel();
        
            for (Empresa empresa : listEmpresa)
                modelo.addRow(empresa.toArray());

            //Actualizar tabla.
            jtEmpresa.updateUI();
        }
    }
    
    private void limpiarTabla () {
        //Inicializo la posición de filas a cero.
        ((DefaultTableModel)jtEmpresa.getModel()).setNumRows(0);
    }
    
    public Empresa guardarDatos () {
        Empresa empresa = new Empresa ();
        
        if (actualizar_eliminar)
            empresa.setId(listEmpresa.get(jtEmpresa.getSelectedRow()).getId());
        
        //Cargo el objeto empresa.
        empresa.setNombre(jtfEmpresa.getText());
        empresa.setDireccion(jtfDireccion.getText());
        empresa.setEmail(jtfEmail.getText());
        
        //Retorna el objeto empresa.
        return empresa;
    }
    
    public void cargarDatos (int indexFila) {
        Empresa empresa = listEmpresa.get(indexFila);
        
        //Cargar los componentes de la pestaña ABM.
        jtfEmpresa.setText(empresa.getNombre());
        jtfDireccion.setText(empresa.getDireccion());
        jtfEmail.setText(empresa.getEmail());
        
        //Cambia de pestaña.
        jTabbedPane.setSelectedIndex(0);
    }
    
    public void configurarBotones (boolean estado, String text) {
        //Se le asigna un texto al botón.
        jbGuardar.setText(text);
        
        jbCancelar.setEnabled(estado);
        jbEliminar.setEnabled(estado);
    }
    
    public String radioButtonSeleccionado () {
        if (jrbEmail.isSelected())
            return jrbEmail.getText();
        
        return jrbEmpresa.getText();
    }
    
    public String buscarRegistro () {
        return jtfBuscar.getText();
    }
    
    private void ponerTotalDeEmpresa () {
        jlTotalEmpresa.setText("Total de empresas : " + jtEmpresa.getRowCount());
    }
}
