package mail.mensaje.vista;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import mail.mensaje.controlador.MailControlador;

/**
 * Clase que contiene el metodo main, el punto de inicio del programa.
 * 
 * @author Félix Pedrozo
 */
public class Main {
    public static void main (String [] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            
        }
        //Crea una instancia de la clase MailControlador.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MailControlador ();
            }
        });
    }
}