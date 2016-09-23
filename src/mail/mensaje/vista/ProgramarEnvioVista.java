package mail.mensaje.vista;

import com.toedter.calendar.JDateChooser;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ProgramarEnvioVista extends JDialog {
      private JPanel jpConfiguracion;
    private JButton jbAceptar, jbCancelar;
    private JComboBox jcbFrecuencia, jcbDia;
    private JTextField jtfHora;
    private JSpinner jsIntervalo;
    private JDateChooser jdcFechaMinima, jdcFechaMaxima;
    private JLabel jlFechaMinima, jlFechaMaxima, jlIntervalo, jlHora,
            jlFrecuencia, jlDia;
    
    public ProgramarEnvioVista (JFrame frame) {
        super (frame);
        inicializarComponentes ();
    }
    
    private void inicializarComponentes () {
        setTitle ("Programar envío");
        setModal(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter () {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        /*
            Configuración de los componentes del jpConfiguracion.
        */
       jpConfiguracion = new JPanel ();
       GridBagLayout gblConfiguracion = new GridBagLayout();
       jpConfiguracion.setLayout(gblConfiguracion);
       
       GridBagConstraints restricciones = new GridBagConstraints ();
       //Componente de la fila 0 columna 0. 
       restricciones.gridx = 0;
       restricciones.gridy = 0;
       restricciones.gridheight = 1;
       restricciones.gridwidth = 1;
       restricciones.anchor = GridBagConstraints.EAST;
       restricciones.insets = new Insets(10, 10, 10, 10);
       
       jlFechaMinima = new JLabel ("Fecha minima");
       jpConfiguracion.add (jlFechaMinima, restricciones);
       
       //Componenete de la fila 1 columna 0
       restricciones.gridy = 1;
       restricciones.insets = new Insets(0, 10, 10, 10);
       
       jlHora = new JLabel ("Hora");
       jpConfiguracion.add (jlHora, restricciones);
       
       //Componente de la fila 2 columna 0
       restricciones.gridy = 2;
       
       jlFrecuencia = new JLabel("Frecuencia");
       jpConfiguracion.add (jlFrecuencia, restricciones);
       
       //Componente de la fila 0 columna 1
       restricciones.gridx = 1;
       restricciones.gridy = 0;
       restricciones.ipadx = 20;
       restricciones.fill = GridBagConstraints.HORIZONTAL;
       restricciones.insets = new Insets(10, 0, 10, 10);
       
       jdcFechaMinima = new JDateChooser ();
       jpConfiguracion.add (jdcFechaMinima, restricciones);
       
       //Componentes de la fila 1 columna 1
       restricciones.gridy = 1;
       restricciones.insets = new Insets(0, 0, 10, 10);
       
       jtfHora = new JTextField ();
       jpConfiguracion.add (jtfHora, restricciones);
       
       //Componente de la fila 2 columna 1.
       restricciones.gridy = 2;
       
       String []  itemsFrec = {"Diaria", "Semanal", "Mensual", "Anual"};
       jcbFrecuencia = new JComboBox(new DefaultComboBoxModel(itemsFrec));
       
       jpConfiguracion.add (jcbFrecuencia, restricciones);
       
       //Componentes de la fila 0 columna 2.
       restricciones.gridx = 2;
       restricciones.gridy = 0;
       restricciones.ipadx = 0;
       restricciones.fill = GridBagConstraints.NONE;
       restricciones.insets = new Insets(10, 0, 10, 10);
       
       jlFechaMaxima = new JLabel ("Fecha máxima");
       
       jpConfiguracion.add (jlFechaMaxima, restricciones);
       
       //Componentes de la fila 1 columna 2.
       restricciones.gridy = 1;
       restricciones.insets = new Insets(0, 0, 10, 10);
       
       jlIntervalo = new JLabel ("Intervalo");
       jpConfiguracion.add (jlIntervalo, restricciones);
       
       //Componentes de la fila 2 columna 2.
       restricciones.gridy = 2;
       
       jlDia = new JLabel ("Día de la semana");
       jpConfiguracion.add (jlDia, restricciones);
       
       //Componentes de la fila 0 columna 3.
       restricciones.gridx = 3;
       restricciones.gridy = 0;
       restricciones.ipadx = 20;
       restricciones.fill = GridBagConstraints.HORIZONTAL;
       restricciones.insets = new Insets(10, 0, 10, 10);
       
       jdcFechaMaxima = new JDateChooser();
       jpConfiguracion.add (jdcFechaMaxima, restricciones);
       
       //Componentes de la fila 1 columna 3.
       restricciones.gridy = 1;
       restricciones.insets = new Insets(0, 0, 10, 10);
       
       jsIntervalo = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
       jpConfiguracion.add (jsIntervalo, restricciones);
       
       //Componentes de la fila 2 columna 3.
       restricciones.gridy = 2;
       
       String [] itemsDia = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
       jcbDia = new JComboBox(new DefaultComboBoxModel(itemsDia));
       
       jpConfiguracion.add (jcbDia, restricciones);
       
        /*
            Configuración de los componentes del JDialog.
       */
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints ();
        //Agrego bordes al JPanel.
        jpConfiguracion.setBorder(BorderFactory.createEtchedBorder());
        
        //Indico la posicion en la que estara el componente fila 0 columna 0.
        constraints.gridx = 0;
        constraints.gridy = 0;
        
        //Indico la cantidad de filas que ocupara.
        constraints.gridheight = 1;
        //Indico la cantidad de columnas que ocupara.
        constraints.gridwidth = 2;
        //Estiro la fila.
        constraints.weighty = 1.0;
        //Indico hacia que dirección se estirara el componente.
        constraints.fill = GridBagConstraints.BOTH;
        //Los espacios que habra alrededor del componente.
        constraints.insets = new Insets (10, 10, 10, 10);
        
        add(jpConfiguracion, constraints);
        
        //Configuración del boton Aceptar.
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 10, 10, 0);
        
        jbAceptar = new JButton ("Aceptar");
        add(jbAceptar, constraints);
        
        //Configuración del botón Cancelar.
       constraints.gridy = 1;
       constraints.weightx = 1.0;
       constraints.anchor = GridBagConstraints.EAST;
       constraints.insets = new Insets(0, 0, 10, 10);
       
       jbCancelar = new JButton ("Cancelar");
       add (jbCancelar, constraints);
       
       pack();
    }
}
