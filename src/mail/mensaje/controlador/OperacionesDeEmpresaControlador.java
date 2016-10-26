package mail.mensaje.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import mail.mensaje.vista.OperacionesDeEmpresaVista;

/**
 *
 * @author Fèlix Pedrozo
 */
public class OperacionesDeEmpresaControlador implements ActionListener {
    private OperacionesDeEmpresaVista vista;
    
    public OperacionesDeEmpresaControlador (JDialog dialog) {
        vista = new OperacionesDeEmpresaVista (dialog, this);
        vista.setVisible(true);
    }
    public OperacionesDeEmpresaControlador (JFrame frame) {
        vista = new OperacionesDeEmpresaVista (frame, this);
        vista.setVisible(true);
    }
    public OperacionesDeEmpresaControlador (JFrame frame, int intervalo) {
        vista = new OperacionesDeEmpresaVista (frame, intervalo, this);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "jbCancelar" :
                vista.dispose();
        }
    }
    
}
