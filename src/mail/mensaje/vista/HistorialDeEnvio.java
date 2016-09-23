package mail.mensaje.vista;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Clase que muestra el historial futuro de los mensajes pendientes que van ha
 * ser enviados.
 */
public class HistorialDeEnvio extends JDialog {
    private JTable jtHistorial;
    private JScrollPane jspHistorial;
    private JPanel jpInfo;
    private JLabel jlCantMensEnviado, jlCantMensPendiente;
    
    public HistorialDeEnvio (JFrame frame) {
        super (frame);
        inicializarComponentes ();
    }
    
    private void inicializarComponentes () {
        //Configuro al JDialog.
        setTitle("Historial de envío");
        setSize(250, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout (getContentPane(), BoxLayout.Y_AXIS));
        setModal(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HistorialDeEnvio.this.setVisible(false);
                HistorialDeEnvio.this.dispose();
                System.exit(0);
            }
        });
        
        //TODO : Dato por auxiliares para ver el diseño de estos.
        //Creo los datos por defecto para la tabla.
        Object [][] data = {
            {"Enviado", "12/03/16", "13:30"},
            {"Enviado", "12/04/16", "13:30"},
            {"Pendiente", "12/05/16", "13:30"}
        };
        Object [] columns = {"Estado", "Fecha", "Hora"};
        
        jtHistorial = new JTable(data, columns);
        jspHistorial = new JScrollPane(jtHistorial);
        
        //TODO : Dato por defecto que le pongo para ver como queda.
        //Configuro los label que mostraran información referente al historial de envío.
        jlCantMensEnviado = new JLabel("Pendiente : 1");
        jlCantMensEnviado.setIcon(
            new ImageIcon(getClass().getResource("/mail/mensaje/vista/iconos/time.png"))
        );
        //TODO : Dato por defecto que le pongo para ver como queda.
        jlCantMensPendiente = new JLabel ("Enviado : 2");
        jlCantMensPendiente.setIcon(
            new ImageIcon(getClass().getResource("/mail/mensaje/vista/iconos/email.png"))
        );
        jpInfo = new JPanel();
        jpInfo.setLayout(new BoxLayout(jpInfo, BoxLayout.X_AXIS));
        jpInfo.add (jlCantMensEnviado);
        jpInfo.add (Box.createRigidArea(new Dimension(20, 20)));
        jpInfo.add (jlCantMensPendiente);
        jpInfo.add (Box.createHorizontalGlue());
        
        //Añado los componentes al JDialog.
        add (jspHistorial);
        add (jpInfo);
    }
}
