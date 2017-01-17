package mail.mensaje.vista;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import mail.mensaje.modelo.dao.ContactoDAO;
import mail.mensaje.modelo.dao.EmpresaDAO;
import mail.mensaje.modelo.vo.Contacto;
import mail.mensaje.modelo.vo.Empresa;
import mail.mensaje.controlador.OperacionContactoControlador;

/**
 * Clase que permitira realizar operaciones con los contactos.
 * 
 * @author Félix Pedrozo
 */
public class OperacionesDeContactoVista extends JDialog {
    private JLabel jlNombre, jlApellido, jlEmail, jlEmpresa, jlFiltrar, jlTotalContactos;
    private JTextField jtfNombre, jtfApellido, jtfEmail, jtfBuscar;
    private JComboBox <Empresa> jcbEmpresa;
    private JButton jbIngresarEmpresa, jbGuardar, jbCancelar, jbEliminar;
    private JRadioButton jrbNombre, jrbApellido, jrbEmail, jrbEmpresa;
    private JTabbedPane jTabbedPane;
    private JTable jtContacto;
    private JScrollPane jspTablaContacto;
    public List<Contacto> listContacto;
    private OperacionContactoControlador controlador;
    public static boolean actualizar_eliminar = false;

    public OperacionesDeContactoVista (JFrame frame, int indice) {
        super (frame, "Contactos");
        controlador = new OperacionContactoControlador(this);
        crearIU (indice);
        
        //Cargo los componentes de la vista.
        cargarComboBox();
        cargarTablaTodosLosContactos();
        
        setVisible(true);
    }
    
    public OperacionesDeContactoVista (JFrame frame) {
        this (frame, 0);
    }
    
    public OperacionesDeContactoVista (JDialog dialog, int indice) {
        super (dialog, "Contactos");
        controlador = new OperacionContactoControlador(this);
        crearIU (indice);
        
        //Cargo los componentes de la vista.
        cargarComboBox();
        cargarTablaTodosLosContactos();
        
        setVisible(true);
    }
    
    public OperacionesDeContactoVista (JDialog dialog) {
        this (dialog, 0);
    }
    
    private void crearIU (int indexTab) {
        setModal(true);
        setSize(570, 450);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 0));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
    
    private JPanel agregarPanelConsulta () {
         JPanel jpConsulta = new JPanel(new GridBagLayout());
         
         GridBagConstraints conf = new GridBagConstraints();

        //Componente de la fila 0 columna 0.
        conf.gridx = 0;
        conf.gridy = 0;
        conf.weightx = 1.0;
        conf.insets = new Insets(10, 10, 10, 10);
        conf.fill = GridBagConstraints.HORIZONTAL;

        jpConsulta.add (agregarPanelBuscar(), conf);

        //Componente de la fila 1 columna 0.
        conf.gridy = 1;
        conf.weighty = 1.0;
        conf.fill = GridBagConstraints.BOTH;
        conf.insets = new Insets (0, 10, 5,10);

        String [] colums = {"Nombre", "Apellido", "Email", "Empresa"};
        jtContacto = new JTable(new DefaultTableModel(colums, 0) {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false;
             }
            
        });
        jtContacto.addMouseListener(controlador);
        jspTablaContacto = new JScrollPane(jtContacto);
        jpConsulta.add (jspTablaContacto, conf);
        
        //Componente de la fila 2 columna 0.
        conf.gridy = 2;
        conf.weightx = conf.weighty = 0.0;
        conf.fill = GridBagConstraints.NONE;
        conf.anchor = GridBagConstraints.WEST;
        conf.insets = new Insets(0, 10, 3, 0);
        
        jlTotalContactos = new JLabel(UtilImg.createImageIcon("iconos\\contacto.png"));
        jpConsulta.add(jlTotalContactos, conf);
        
        return jpConsulta;
    }
    
    private JPanel agregarPanelBuscar () {
        //Configuro los componentes del panel jpConsulta.
        JPanel jpBuscar = new JPanel(new GridBagLayout());
        jpBuscar.setBorder (BorderFactory.createTitledBorder("Buscar"));
        
        GridBagConstraints constraints = new GridBagConstraints();

        //Componente de la fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 20, 10, 10);

        jtfBuscar = new JTextField(25);
        jtfBuscar.addKeyListener(controlador);
        jpBuscar.add (jtfBuscar, constraints);

        //Componente de la fila 1 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.NONE;
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

        jcbEmpresa = new JComboBox<>();
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
            return (isEmpty(jtfNombre.getText()) || isEmpty(jtfApellido.getText()) ||
                    isEmpty(jtfEmail.getText()));
        else
            return isEmpty(jtfBuscar.getText());
    }
    
    public boolean direccDeCorreoValida () {
         Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+"
                 + "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
         Matcher mat = pattern.matcher(jtfEmail.getText());
        
        return mat.find();
    }
    
    private boolean isEmpty (String s) {
        return s.trim().isEmpty();
    }
    
    public void mostrarMensaje (String m, int tipoMensaje) {
        JOptionPane.showMessageDialog(this, m, "Aviso", tipoMensaje);
    }
    
    public void limpiarCampos () {
        jtfNombre.setText(null);
        jtfApellido.setText(null);
        jtfEmail.setText(null);
        jcbEmpresa.setSelectedIndex(0);
    }
    
    public void cargarComboBox () {
        List <Empresa> list = new EmpresaDAO ().todasLasEmpresas();
        
        for (Empresa empresa : list)
            jcbEmpresa.addItem(empresa);
    }
    
    public void cargarTablaTodosLosContactos () {
        //Carga la tabla con todos los registros de los contactos.
        cargarTablaNuevaInfo(new ContactoDAO().todosLosContacto());
        ponerTotalDeContactos ();
    }
    
    public void cargarTablaNuevaInfo (List <Contacto> list) {
        limpiarTabla();
        
        if (list.size() > 0) {
            //Guardo la lista.
            listContacto = list;
            
            //Obtengo el modelo de la tabla.
            DefaultTableModel modelo = (DefaultTableModel)jtContacto.getModel();

            //Inserto las filas.
            for (Contacto contacto : listContacto)
                modelo.addRow (contacto.toArray());

            //Actualizar tabla.
            jtContacto.updateUI();
        }
    }
    
    public void cargarDatos (int indexFila) {
        Contacto contacto = listContacto.get(indexFila);
        
        //Inserto los datos a los componentes.
        jtfNombre.setText(contacto.getNombre());
        jtfApellido.setText(contacto.getApellido());
        jtfEmail.setText(contacto.getEmail());
        jcbEmpresa.setSelectedItem(contacto.getEmpresa());
        
        jTabbedPane.setSelectedIndex(0);
    }
    
    private void limpiarTabla () {
        //Le inicializo la cantidad de filas a cero.
        if (jtContacto.getRowCount() != 0)
            ((DefaultTableModel)jtContacto.getModel()).setNumRows(0);
    }
    
    public Contacto guardarDatos () {
        Contacto contacto = new Contacto ();
        
        if (actualizar_eliminar)
            contacto.setId(listContacto.get(jtContacto.getSelectedRow()).getId());
        
        //Cargo el objeto contacto.
        contacto.setNombre(jtfNombre.getText());
        contacto.setApellido(jtfApellido.getText());
        contacto.setEmail(jtfEmail.getText());
        contacto.setEmpresa((Empresa)jcbEmpresa.getSelectedItem());
        
        //Retorna los datos de la cargados.
        return contacto;
    }
    
    public void configurarBotones (boolean estado, String text) {
        //Cambiar texto de botón.
        jbGuardar.setText(text);
        
        jbEliminar.setEnabled(estado);
        jbCancelar.setEnabled(estado);
    }
    
    public String radioButtonSeleccionado () {
        if (jrbNombre.isSelected())
            return jrbNombre.getText();
        else if (jrbApellido.isSelected())
            return jrbApellido.getText();
        else if (jrbEmail.isSelected())
            return jrbEmail.getText();
        
        return jrbEmpresa.getText();
    }
    
    public String buscarRegistro () {
        return jtfBuscar.getText();
    }
    
    private void ponerTotalDeContactos () {
        jlTotalContactos.setText("Total de contactos : " + jtContacto.getRowCount());
    }
}
