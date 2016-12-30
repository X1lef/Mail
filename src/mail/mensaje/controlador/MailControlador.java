package mail.mensaje.controlador;

import java.awt.event.*;
import java.awt.CardLayout;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import mail.mensaje.vista.MailPrincipalVista;
import mail.mensaje.vista.OperacionesDeContactoVista;
import mail.mensaje.vista.OperacionesDeEmpresaVista;
import mail.mensaje.vista.RedactarMensajeVista;

public class MailControlador implements TreeSelectionListener, ActionListener, ItemListener {
    private final MailPrincipalVista vista;
    
    public MailControlador (MailPrincipalVista vista) {
        //Guardo la referencia de la vista.
        this.vista = vista;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode nodoSeleccionado;
        nodoSeleccionado = (DefaultMutableTreeNode) vista.getTree().getLastSelectedPathComponent();
            
        switch(nodoSeleccionado.getUserObject().toString()) {
            case "Enviados" :
                cambiarPanelDeMens ("jpMensEnviado");
                vista.activarVisibilidad(false, true, true, true, false, false);
                break;
            case "Eliminados" :
                cambiarPanelDeMens ("jpMensEliminado");
                vista.activarVisibilidad(false, false, true, false, true, false);
                break;
            case "Guardados" :
                cambiarPanelDeMens ("jpMensGuardado");
                vista.activarVisibilidad(false, false, true, true, false, false);
                break;
            case "Recibidos" :
                cambiarPanelDeMens ("jpMensRecibido");
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
                new RedactarMensajeVista(vista);
                break;
            case "jbIngresarContacto" :
                new OperacionesDeContactoVista (vista);
                break;
            case "jbIrContactos" :
                new OperacionesDeContactoVista(vista, 1);
                break;
            case "jbIngresarEmpresa" :
                new OperacionesDeEmpresaVista (vista);
                break;
            case "jbIrEmpresas" :
                new OperacionesDeEmpresaVista (vista, 1);
                break;
            case "jbMensNormal" :
                cambiarPanelDeTipoMensEnviado("mensajeNormal");
                break;
            case "jbMensProg" :
                cambiarPanelDeTipoMensEnviado ("mensajeProg");
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
    
    private void cambiarPanelDeMens (String nombrePanel) {
        CardLayout layout = (CardLayout) vista.getMens().getLayout();
        layout.show(vista.getMens(), nombrePanel);
    }

    private void cambiarPanelDeTipoMensEnviado (String nombrePanel) {
        CardLayout cardLayout = (CardLayout) vista.getTipoMens().getLayout();
        cardLayout.show(vista.getTipoMens(), nombrePanel);
    }
}
