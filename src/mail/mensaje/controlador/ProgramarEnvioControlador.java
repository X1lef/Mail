package mail.mensaje.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import mail.mensaje.vista.ProgramarEnvioVista;

/**
 * @author Félix Pedrozo
 */
public class ProgramarEnvioControlador implements ItemListener, ChangeListener,
        ActionListener {
    private int intervalo = 1;
    private String singular = "día", plural = "días";
    private final ProgramarEnvioVista programarEnvioVista;
    
    public ProgramarEnvioControlador (ProgramarEnvioVista vista) {
        programarEnvioVista = vista;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JComboBox itemFrecuencia = (JComboBox) e.getSource();
        
        String itemSeleccionado = (String)itemFrecuencia.getSelectedItem();
        if (itemSeleccionado.equals("Diaria")) {
            programarEnvioVista.seleccionarDias();
            programarEnvioVista.habilitarDias(false);
            singular = "día";
            plural = "días";
        } else {
            programarEnvioVista.habilitarDias(true);
            switch (itemSeleccionado) {
                case "Semanal":
                    singular = "semana";
                    plural = "semanas";
                    break;
                case "Mensual":
                    singular = "mes";
                    plural = "meses";
                    break;
                default:
                    singular = "año";
                    plural = "años";
                    break;
            }
        }
        programarEnvioVista.actualizarPorCantidad(intervalo, singular, plural);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner jsIntervalo = (JSpinner)e.getSource();
        switch (jsIntervalo.getName ()) {
            case "jsIntervalo" :
                intervalo = (int) jsIntervalo.getValue();
                programarEnvioVista.actualizarPorCantidad(intervalo, singular, plural);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "jbCancelar" :
                programarEnvioVista.dispose();
        }
    }
}
