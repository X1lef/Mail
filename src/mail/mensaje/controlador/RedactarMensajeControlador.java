package mail.mensaje.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import mail.mensaje.vista.HistorialDeEnvioVista;
import mail.mensaje.vista.OperacionesDeContactoVista;
import mail.mensaje.vista.ProgramarEnvioVista;
import mail.mensaje.vista.RedactarMensajeVista;

/**
 * @author Félix Pedrozo
 */
public class RedactarMensajeControlador implements ActionListener {
    private final RedactarMensajeVista redactarMensajeVista;
    
    public RedactarMensajeControlador (RedactarMensajeVista vista) {
        //Guardo referencia de la vista.
        redactarMensajeVista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((JButton)e.getSource()).getName ()) {
            case "jbContactosIr" :
                new OperacionesDeContactoVista(redactarMensajeVista, 1);
                break;
            case "jbCancelar" :
                redactarMensajeVista.dispose();
                break;
            case "jbHistorialEnvio" :
                new HistorialDeEnvioVista(redactarMensajeVista);
                break;
            case "jbProgramarEnvio" :
                new ProgramarEnvioVista(redactarMensajeVista);
                break;
            case "jbAgregarImagen" :
                redactarMensajeVista.mostrarSelectorDeArchivos();
                break;
        }
    }
}
