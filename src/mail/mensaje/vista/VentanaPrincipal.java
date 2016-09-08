package mail.mensaje.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

public class VentanaPrincipal extends JFrame {
    private JButton btnInicio, btnRedactar, btnIngresarContacto;
    private JSeparator sp1, sp2;
    private JToolBar toolBar;
    
    
    public VentanaPrincipal () {
        crearUI ();
    }
    
    public void crearUI () {
        //Inicializamos los botones.
        btnInicio = new JButton("Inicio");
        btnRedactar = new JButton("Redactar mensaje");
        btnIngresarContacto = new JButton("Ingresar contacto");
        
        sp1 = new JSeparator();
        sp2 = new JSeparator();
        
        //Inserto los botones al toolbar.
        toolBar = new JToolBar();
        toolBar.add(btnInicio);
        toolBar.add(sp1);
        toolBar.add(btnRedactar);
        toolBar.add(sp2);
        toolBar.add(btnIngresarContacto);
        
        //Añadimos al JFrame el toolbar.
        add (BorderLayout.NORTH, toolBar);
        
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
