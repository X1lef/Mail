package mail.mensaje.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
                break;
             case "jbGuardar" :
                if (vista.estaVacio(1))
                    vista.mostrarMensaje("Los campos no deben estar vacíos", 
                            JOptionPane.ERROR_MESSAGE);
                else {
                    vista.limpiarCampos();
                    vista.mostrarMensaje("Se ha guardado la empresa correctamente",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "jbBuscar" :
                if (vista.estaVacio(2))
                    vista.mostrarMensaje("El campo de busqueda no debe estar vacío", 
                            JOptionPane.ERROR_MESSAGE);
            //TODO : Aqui debe buscar referente al atributo que puso en el campo de buscar en la bd.    
        }
    }
    
}
