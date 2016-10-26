package mail.mensaje.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import mail.mensaje.vista.HistorialDeEnvioVista;
import mail.mensaje.vista.RedactarMensajeVista;

/**
 * @author Félix Pedrozo
 */
public class RedactarMensajeControlador implements ActionListener {
    private RedactarMensajeVista redactarMensajeVista;
    
    public RedactarMensajeControlador (JFrame frame) {
        redactarMensajeVista = new RedactarMensajeVista(frame, this);
        redactarMensajeVista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((JButton)e.getSource()).getName ()) {
            case "jbContactosIr" :
                new OperacionContactoControlador(redactarMensajeVista, 1);
                break;
            case "jbCancelar" :
                redactarMensajeVista.dispose();
                break;
            case "jbHistorialEnvio" :
                new HistorialDeEnvioVista(redactarMensajeVista).setVisible(true);
                break;
            case "jbProgramarEnvio" :
                new ProgramarEnvioControlador(redactarMensajeVista);
                break;
            case "jbAgregarImagen" :
                redactarMensajeVista.mostrarSelectorDeArchivos();
                
        }
    }
    
}
