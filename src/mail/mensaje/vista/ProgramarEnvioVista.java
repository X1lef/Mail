package mail.mensaje.vista;

import java.awt.*;
import javax.swing.*;
import java.util.Date;
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;
import mail.mensaje.controlador.ProgramarEnvioControlador;

/**
 * Clase que permite configurar un mensaje para su envío.
 * 
 * @author Félix Pedrozo
 */
public class ProgramarEnvioVista extends JDialog {
    private JSpinner jsHoraMax, jsHoraMin, jsMinutoMax, jsMinutoMin, jsIntervalo;
    private JButton jbAceptar, jbCancelar;
    private JLabel jlFechaMinMax, jlHoraMinMax, jlFrecuencia, jlIntervalo, jlDescripcion;
    private JCheckBox jcbD, jcbL, jcbM, jcbX, jcbJ, jcbV, jcbS;
    private JDateChooser jdcFechaMin, jdcFechaMax;
    private JComboBox <String> jcbFrecuencia;
    private JTabbedPane jTabbedPane;
    private final ProgramarEnvioControlador controlador;
    
    public ProgramarEnvioVista (JDialog padre) {
        super (padre, "Programar envío");
        controlador = new ProgramarEnvioControlador(this);
        crearIU ();
        
        setVisible(true);
    }
    
    private void crearIU () {
        setSize (450, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        //Inserción de los paneles al TabbedPane.
        jTabbedPane = new JTabbedPane();
        jTabbedPane.add("Enviar", agregarPanelEnvioProg());
        jTabbedPane.add("Opciones de envío", agregarPanelOpcionesDeEnvio());
        
        //Inserción de los componentes al Frame.
        add (jTabbedPane);
        add (agregarPanelBotones());
    }
    
    //Configuro los componentes para el panel jpDias.
    private JPanel agregarPanelOpcionesDeEnvio () {
        jcbD = new JCheckBox("D", true);
        jcbL = new JCheckBox("L", true);
        jcbM = new JCheckBox("M", true);
        jcbX = new JCheckBox("X", true);
        jcbJ = new JCheckBox("J", true);
        jcbV = new JCheckBox("V", true);
        jcbS = new JCheckBox("S", true);
        
        //Por defecto las cajas de chequeo van ha estar desabilitadas.
        habilitarDias(false);
        
        JPanel jpDias = new JPanel (new FlowLayout(FlowLayout.CENTER, 15, 5));
        jpDias.setBorder(BorderFactory.createTitledBorder("Día de la semana"));
        jpDias.add (jcbD);
        jpDias.add (jcbL);
        jpDias.add (jcbM);
        jpDias.add (jcbX);
        jpDias.add (jcbJ);
        jpDias.add (jcbV);
        jpDias.add (jcbS);
        
        //Configuro los componentes para el panel jpOpcionesEnvio.
        JPanel jpOpcionesDeEnvio = new JPanel(new GridBagLayout());
        GridBagConstraints restricciones = new GridBagConstraints();
        
        //Componente de la fila 0 columna 0.
        restricciones.gridx = 0;
        restricciones.gridy = 0;
        restricciones.anchor = GridBagConstraints.WEST;
        restricciones.insets = new Insets(10, 10, 20, 10);
        
        jlFrecuencia = new JLabel ("Frecuencia");
        jpOpcionesDeEnvio.add (jlFrecuencia, restricciones);
        
        //Componente de la fila 0 columna 1.
        restricciones.gridx = 1;
        restricciones.weightx = 1.0;
        restricciones.insets = new Insets (10, 0, 20, 10);
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        
        jcbFrecuencia = new JComboBox<>(new String [] {"Diaria", "Semanal", "Mensual", "Anual"});
        jcbFrecuencia.addItemListener(controlador);
        jpOpcionesDeEnvio.add (jcbFrecuencia, restricciones);
        
        //Componente de la fila 0 columna 2.
        restricciones.gridx = 2;
        restricciones.weightx = 0.0;
        restricciones.fill = GridBagConstraints.NONE;
        
        jlIntervalo = new JLabel ("Cada");
        jpOpcionesDeEnvio.add (jlIntervalo, restricciones);
        
        //Componentes de la fila 0 columna 3.
        restricciones.gridx = 3;
        
        jsIntervalo = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        jsIntervalo.addChangeListener(controlador);
        jsIntervalo.setName("jsIntervalo");
        jpOpcionesDeEnvio.add (jsIntervalo, restricciones);
        
        //Componente de la fila 0 columna 4.
        restricciones.gridx = 4;
        
        jlDescripcion = new JLabel ("día");
        jlDescripcion.setPreferredSize(new Dimension(50, 14));
        jpOpcionesDeEnvio.add (jlDescripcion, restricciones);
        
        //Componente de la fila 1 columna 0.
        restricciones.gridx = 0;
        restricciones.gridy = 1;
        restricciones.weighty = 1.0;
        restricciones.gridwidth = 5;
        restricciones.anchor = GridBagConstraints.NORTHWEST;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        restricciones.insets = new Insets(0, 10, 10, 10);
        
        jpOpcionesDeEnvio.add (jpDias, restricciones);
        
        return jpOpcionesDeEnvio;
    }
    
    //Configuro los componentes para el panel jpEnvioProg.
    private JPanel agregarPanelEnvioProg () {
        JPanel jpEnvioProg = new JPanel ();
        jpEnvioProg.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints ();
        
        //Componente de la fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(10, 20, 10, 0);
        
        //Obtengo la hora actual del sistema.
        Calendar ahora = Calendar.getInstance();
        final int hora = ahora.get(Calendar.HOUR_OF_DAY);
        //TODO : La variable minuto, no lo estoy utilizando.
        final int minuto = ahora.get(Calendar.MINUTE);
        
        jlFechaMinMax = new JLabel ("Fecha minima y máxima");
        jpEnvioProg.add (jlFechaMinMax, constraints);
        
        //Componente de la fila 1 columna 0.
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 20, 10, 30);
        
        jdcFechaMax = new JDateChooser(new Date ());
        jdcFechaMax.setIcon(
                new ImageIcon(getClass().getResource("iconos\\calendario.png"))
        );
        jdcFechaMax.setDateFormatString("dd/MM/yyyy");
        jpEnvioProg.add (jdcFechaMax, constraints);
        
        //Componente de la fila 2 columna 0.
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 20, 10, 0);
        
        jlHoraMinMax = new JLabel ("Hora minima y máxima");
        jpEnvioProg.add (jlHoraMinMax, constraints);
        
        //Componente de la fila 3 columna 0.
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(0, 20, 10, 10);
        constraints.fill = GridBagConstraints.NONE;
        
        jsHoraMax = new JSpinner (new SpinnerNumberModel (hora, 0, 23, 1));
        jpEnvioProg.add (jsHoraMax, constraints);
        
        //Componente de la fila 3 columna 1.
        constraints.gridx = 1;
        constraints.insets = new Insets(0, 0, 10, 10);
        
        jpEnvioProg.add (new JLabel (":"), constraints);
        
        //TODO : Vector para representar a los minutos.
        String [] itemsMinutos = {
            "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
            "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
            "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43",
            "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54",
            "55", "56", "57", "58", "59"
        };
        
        //Comonente de la fila 3 columna 2.
        constraints.gridx = 2;
        constraints.insets = new Insets(0, 0, 10, 0);
        constraints.weightx = 1.0;
        
        jsMinutoMax = new JSpinner (new SpinnerListModel(itemsMinutos));
        jpEnvioProg.add (jsMinutoMax, constraints);
        
        //Componente de la fila 1 columna 3.
        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.weightx = 0.0;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 10, 20);
        
        jdcFechaMin = new JDateChooser(new Date ());
        jdcFechaMin.setIcon(
            new ImageIcon (getClass().getResource("iconos\\calendario.png"))
        );
        jdcFechaMin.setDateFormatString("dd/MM/yyyy");
        jpEnvioProg.add (jdcFechaMin, constraints);
        
        //Componente de la fila 3 columna 3.
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0, 0, 10, 10);
        
        jsHoraMin = new JSpinner (new SpinnerNumberModel(hora, 0, 23, 1));
        jpEnvioProg.add (jsHoraMin, constraints);
        
        //Componente de la fila 3 columna 4.
        constraints.gridx = 4;
        
        jpEnvioProg.add (new JLabel (":"), constraints);
        
        //Componente de la fila 3 columna 5.
        constraints.gridx = 5;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(0, 0 , 10, 0);
        
        jsMinutoMin = new JSpinner(new SpinnerListModel(itemsMinutos));
        jpEnvioProg.add (jsMinutoMin, constraints);
        
        return jpEnvioProg;
    }
    
    private JPanel agregarPanelBotones () {
        JPanel jpBotones = new JPanel ();
        jpBotones.setLayout(new BoxLayout(jpBotones, BoxLayout.X_AXIS));
        jpBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        jbAceptar = new JButton ("Aceptar");
        jpBotones.add (jbAceptar);
        jpBotones.add (Box.createHorizontalGlue());
        jbCancelar = new JButton ("Cancelar");
        jbCancelar.setActionCommand("jbCancelar");
        jbCancelar.addActionListener(controlador);
        jpBotones.add (jbCancelar);
        
        return jpBotones;
    }
    
    public void actualizarPorCantidad (int cantidad, String singular, String plural) {
        if (cantidad == 1) {
            if (!jlDescripcion.getText().equals(singular))
                jlDescripcion.setText(singular);
            
        } else if (!jlDescripcion.getText().equals(plural))
            jlDescripcion.setText(plural);
    }
    
    public void habilitarDias (boolean estado) {
        if (jcbD.isEnabled() != estado) {
            jcbD.setEnabled(estado);
            jcbL.setEnabled(estado);
            jcbM.setEnabled(estado);
            jcbX.setEnabled(estado);
            jcbJ.setEnabled(estado);
            jcbV.setEnabled(estado);
            jcbS.setEnabled(estado);
        }
    }
    
    public void seleccionarDias () {
        jcbD.setSelected(true);
        jcbL.setSelected(true);
        jcbM.setSelected(true);
        jcbX.setSelected(true);
        jcbJ.setSelected(true);
        jcbV.setSelected(true);
        jcbS.setSelected(true);
    }
}