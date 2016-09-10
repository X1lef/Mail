package mail.mensaje.vista;

import javax.swing.SwingUtilities;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;

/**
 * @author Félix Pedrozo
 * 
 * Clase que contiene el metodo main,el punto de inicio del programa.
 */
public class Main {
    public static void main (String [] args) {
        //Configura el diseño correspondiente para cada plataforma en la que se ejecuta.
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Crea una instancia de la clase VentanaPricipal.
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
