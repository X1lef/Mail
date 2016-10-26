package mail.mensaje.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import mail.mensaje.vista.MailPrincipalVista;

public class MailControlador implements TreeSelectionListener, ActionListener, ItemListener {
    private MailPrincipalVista vista;
    
    public MailControlador () {
        vista = new MailPrincipalVista(this);
        //Configura el diseño correspondiente para cada plataforma en la que se ejecuta.
//        vista.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        vista.setVisible(true);
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
        switch (e.getActionCommand()) {
            case "jbInicio" :
                vista.activarVisibilidad(true, false, false, false, false, false);
                break;
            case "jbRedactar" :
                new RedactarMensajeControlador(vista);
                break;
            case "jbIngresarContacto" :
                new OperacionContactoControlador (vista);
                break;
            case "jbIrContactos" :
                new OperacionContactoControlador(vista, 1);
                break;
            case "jbIngresarEmpresa" :
                new OperacionesDeEmpresaControlador (vista);
                break;
            case "jbIrEmpresas" :
                new OperacionesDeEmpresaControlador (vista, 1);
                break;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JRadioButtonMenuItem item = (JRadioButtonMenuItem) e.getItem();
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (item.getText().equals(info.getName())) {
                    vista.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
    }
}
