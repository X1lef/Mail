package mail.mensaje.controlador;

import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import static javax.swing.JOptionPane.*;
import mail.mensaje.modelo.vo.Contacto;
import mail.mensaje.modelo.dao.ContactoDAO;
import mail.mensaje.vista.OperacionesDeContactoVista;
import static mail.mensaje.vista.OperacionesDeContactoVista.actualizar_eliminar;
import mail.mensaje.vista.OperacionesDeEmpresaVista;

/**
 * @author Félix Pedrozo
 */
public class OperacionContactoControlador extends MouseAdapter implements ActionListener, KeyListener {
    private final OperacionesDeContactoVista vista;
    private final ContactoDAO modelo;
    private boolean tecleado = false;
    private int indexFila;
    
    public OperacionContactoControlador (OperacionesDeContactoVista vista) {
        //Guardo la referencia de la vista.
        this.vista = vista;
        modelo = new ContactoDAO ();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "jbIngresarEmpresa" :
                new OperacionesDeEmpresaVista(vista);
                break;
                
            case "jbCancelar" :
                vista.configurarBotones(false, "Guardar");
                actualizar_eliminar = false;
                vista.limpiarCampos();
                break;
                
            default :
                //Comprobar si esta vacío los componentes.
                if (vista.estaVacio(1)) {
                    vista.mostrarMensaje("Los campos no deben estar vacíos", ERROR_MESSAGE);
                    return;
                
                //Comprobar si es una dirección de correo valida.
                } else if (!vista.direccDeCorreoValida()) {
                    vista.mostrarMensaje("Dirección de correo no valida.", ERROR_MESSAGE);
                    return;
                    
                } else if (command.equals("jbEliminar")){
                    modelo.eliminarContacto(vista.listContacto.get(indexFila).getId());
                    vista.mostrarMensaje("Se ha eliminado el contacto correctamente", INFORMATION_MESSAGE);
                    vista.configurarBotones(false, "Guardar");
                    actualizar_eliminar = false;
                    
                //Bloque de código para el botón guardar.  
                } else {
                    //Actualizar registro.
                    if (actualizar_eliminar) {
                        modelo.actualizarContacto(vista.guardarDatos());
                        vista.mostrarMensaje("Se ha actualizado el contacto correctamente", INFORMATION_MESSAGE);
                        vista.configurarBotones(false, "Guardar");
                        actualizar_eliminar = false;
                    
                    //Insertar registro.
                    } else {
                        modelo.insertarContacto(vista.guardarDatos());
                        vista.mostrarMensaje("Se ha guardado el contacto correctamente", INFORMATION_MESSAGE);
                    }
                }
                //Actualizar vista.
                vista.limpiarCampos();
                vista.cargarTablaTodosLosContactos();
                
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            JTable tabla = (JTable)e.getSource();
            indexFila = tabla.getSelectedRow();
            actualizar_eliminar = true;
            
            vista.configurarBotones(true, "Actualizar");
            vista.cargarDatos (indexFila);
        }
    }
    
    private List <Contacto> buscarRegistro () {
        final String where;
        
        switch (vista.radioButtonSeleccionado()) {
            //Selecciono nombre.
            case "Nombre" :
                where = "WHERE UPPER(ct.nombre) LIKE UPPER(CONCAT(?, '%'))";
                break;
            
            //Selecciono apellido.
            case "Apellido" :
                where = "WHERE UPPER(ct.apellido) LIKE UPPER(CONCAT(?, '%'))";
                break;
                
            //Selecciono email.
            case "Email" :
                where = "WHERE UPPER(ct.email) LIKE UPPER(CONCAT(?, '%'))";
                break;
                
            //Selecciono empresa.
            default :
                where = "WHERE UPPER(em.nombre) LIKE UPPER(CONCAT(?, '%'))";
                break;
        }
        
        return modelo.obtenerContacto(where, vista.buscarRegistro());
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (Character.isAlphabetic(e.getKeyCode())) {
             vista.cargarTablaNuevaInfo(buscarRegistro());
             
             if (!tecleado) tecleado = true;
             
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (vista.estaVacio(2)) {
                System.out.println ("keyReleased.");
                
                if (tecleado) {
                    vista.cargarTablaTodosLosContactos();
                    tecleado = false;
                }
            } else
                vista.cargarTablaNuevaInfo(buscarRegistro());
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        //No se utiliza
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //No se utiliza.
    }
}
