package mail.mensaje.vista;

import javax.swing.SwingUtilities;
import mail.mensaje.controlador.MailControlador;
import static javax.swing.UIManager.*;

/**
 * Clase que contiene el metodo main, el punto de inicio del programa.
 * 
 * @author Félix Pedrozo
 */
public class Main {
    public static void main (String [] args) {
        try {
           setLookAndFeel(getSystemLookAndFeelClassName());
           
        } catch (Exception ex) {}
        
        //Crea una instancia de la clase MailControlador.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MailControlador ();
            }
        });
    }
}