package mail.mensaje.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import mail.mensaje.vista.OperacionesDeContactoVista;

/**
 * @author Félix Pedrozo
 */
public class OperacionContactoControlador implements ActionListener {
    private OperacionesDeContactoVista vista;
    
    public OperacionContactoControlador (JFrame frame) {
        vista = new OperacionesDeContactoVista(frame, this);
        vista.setVisible(true);
    }
    
    public OperacionContactoControlador (JFrame frame, int intervalo) {
        vista = new OperacionesDeContactoVista(frame, intervalo, this);
        vista.setVisible(true);
    }
    
    public OperacionContactoControlador (JDialog dialog) {
        vista = new OperacionesDeContactoVista(dialog, this);
        vista.setVisible(true);
    }
    
    public OperacionContactoControlador (JDialog dialog, int intervalo) {
        vista = new OperacionesDeContactoVista(dialog, intervalo, this);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "jbIngresarEmpresa" :
                new OperacionesDeEmpresaControlador(vista);
                break;
            case "jbCancelar" :
                vista.dispose();
        }
    }
    
}
