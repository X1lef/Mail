package mail.mensaje.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import mail.mensaje.modelo.dao.ContactoDAO;
import mail.mensaje.vista.OperacionesDeContactoVista;
import static mail.mensaje.vista.OperacionesDeContactoVista.actualizar_eliminar;
import static javax.swing.JOptionPane.*;
import mail.mensaje.modelo.vo.Contacto;

/**
 * @author Félix Pedrozo
 */
public class OperacionContactoControlador extends MouseAdapter implements ActionListener {
    private OperacionesDeContactoVista vista;
    private ContactoDAO modelo;
    private int indexFila;
    
    public OperacionContactoControlador (JFrame frame) {
        this(frame, 0);
    }
    
    public OperacionContactoControlador (JFrame frame, int intervalo) {
        vista = new OperacionesDeContactoVista(frame, intervalo, this);
        modelo = new ContactoDAO ();
        vista.setVisible(true);
    }
    
    public OperacionContactoControlador (JDialog dialog) {
        this(dialog, 0);
    }
    
    public OperacionContactoControlador (JDialog dialog, int intervalo) {
        vista = new OperacionesDeContactoVista(dialog, intervalo, this);
        modelo = new ContactoDAO ();
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "jbIngresarEmpresa" :
                new OperacionesDeEmpresaControlador(vista);
                break;
                
            case "jbCancelar" :
                vista.configurarBotones(false, "Guardar");
                actualizar_eliminar = false;
                vista.limpiarCampos();
                break;
                
            case "jbMostrarTodo" :
                //TODO : Me falta el botón mostrar todo.
                break;
                
            case "jbBuscar" :
                if (vista.estaVacio(2))
                    vista.mostrarMensaje("El campo de busqueda no debe estar vacío", ERROR_MESSAGE);
                
                else {
                    vista.cargarTablaNuevaInfo(buscarRegistro());
                }
                break;
                
            default :
                //Comprobar si esta vacío los componentes.
                if (vista.estaVacio(1)) {
                    vista.mostrarMensaje("Los campos no deben estar vacíos", ERROR_MESSAGE);
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
}
