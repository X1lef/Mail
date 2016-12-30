package mail.mensaje.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import mail.mensaje.vista.LoginVista;
import mail.mensaje.vista.MailPrincipalVista;

/**
 * @author Félix Pedrozo
 * @author Micky Martínez
 */
public class LoginControlador implements ActionListener, FocusListener {
    //TODO : Datos de prueba.
    private final String user = "f";
    private final char [] password = "1".toCharArray();
    
    private final LoginVista vista;

    public LoginControlador (LoginVista vista) {
        //Guardo la referencia de la vista.
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "jbCancelar" :
                System.exit(0);
                break;

            case "jbAcceder" :
                if (vista.datosValidos()) {
                    //Validar si los datos son correctos.
                    if (!user.equals(vista.getUser()))
                        vista.mostrarMensError("Usuario incorrecto.");
                    
                    else if (!Arrays.equals(password, vista.getPassword()))
                        vista.mostrarMensError("Contraseña incorrecta.");
                    
                    else {
                        //Abro la nueva ventana MailPrincipalVista.
                        new MailPrincipalVista ();
                        //Libero los recursos utilizados por la ventana LoginVista.
                        vista.dispose();
                    }
                    
                } else
                    vista.mostrarMensError("Los campos no pueden estar vacíos.");

                break;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        //Compruebo si el componente esta visible.
        if (vista.isVisibleMensError())
            vista.esconderMensError();
    }

    @Override
    public void focusLost(FocusEvent e) {
        //No se utiliza.
    }
}
