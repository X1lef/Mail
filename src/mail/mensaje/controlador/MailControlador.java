package mail.mensaje.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import mail.mensaje.vista.MailPrincipalVista;
import mail.mensaje.vista.OperacionesDeContactoVista;
import mail.mensaje.vista.RedactarMensajeVista;

public class MailControlador implements TreeSelectionListener, ActionListener {
    private MailPrincipalVista vista;
    
    public MailControlador () {
        vista = new MailPrincipalVista(this);
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode nodoSeleccionado;
        nodoSeleccionado = (DefaultMutableTreeNode) vista.getTree().getLastSelectedPathComponent();
            
        switch(nodoSeleccionado.getUserObject().toString()) {
            case "Enviados" :
                vista.activarVisibilidad(false, true, true, true, false, false);
                break;
            case "Eliminados" :
                vista.activarVisibilidad(false, false, true, false, true, false);
                break;
            case "Guardados" :
                vista.activarVisibilidad(false, false, true, true, false, false);
                break;
            case "Recibidos" :
                vista.activarVisibilidad(false, false, true, false, false, true);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((JButton)e.getSource()).getName()) {
            case "jbInicio" :
                vista.activarVisibilidad(true, false, false, false, false, false);
                break;
            case "jbRedactar" :
                new RedactarMensajeVista(vista).setVisible(true);
                break;
            case "jbIngresarContacto" :
                new OperacionesDeContactoVista(vista).setVisible(true);
                break;
        }
    }
}
