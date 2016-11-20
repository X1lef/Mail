package mail.mensaje.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
                break;
            case "jbGuardar" :
                if (vista.estaVacio(1))
                    vista.mostrarMensaje("Los campos no deben estar vacíos", 
                            JOptionPane.ERROR_MESSAGE);
                else {
                    vista.limpiarCampos();
                    vista.mostrarMensaje("Se ha guardado el contacto correctamente",
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
