package mail.mensaje.vista;

import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import mail.mensaje.controlador.MailControlador;

/**
 * Clase que contiene el metodo main, el punto de inicio del programa.
 * 
 * @author Félix Pedrozo
 */
public class Main {
    public static void main (String [] args) {
        //Configura el diseño correspondiente para cada plataforma en la que se ejecuta.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MailPrincipalVista.class.getName()).log(Level.SEVERE, null, ex);
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
