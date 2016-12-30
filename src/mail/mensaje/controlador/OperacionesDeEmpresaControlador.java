package mail.mensaje.controlador;

import java.awt.event.*;
import java.util.List;
import mail.mensaje.modelo.dao.EmpresaDAO;
import mail.mensaje.vista.OperacionesDeEmpresaVista;
import javax.swing.JTable;
import static javax.swing.JOptionPane.*;
import mail.mensaje.modelo.vo.Empresa;
import static mail.mensaje.vista.OperacionesDeEmpresaVista.actualizar_eliminar;

/**
 *
 * @author Félix Pedrozo
 */
public class OperacionesDeEmpresaControlador extends MouseAdapter implements ActionListener {
    private final OperacionesDeEmpresaVista vista;
    private final EmpresaDAO modelo;
    private int indexFila;
    
    public OperacionesDeEmpresaControlador (OperacionesDeEmpresaVista vista) {
        //Guardo la referencia de la vista.
        this.vista = vista;
        modelo = new EmpresaDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch (command) {
            case "jbCancelar" :
                actualizar_eliminar = false;
                vista.limpiarCampos();
                vista.configurarBotones(false, "Guardar");
                break;
                
            case "jbBuscar" :
                if (vista.estaVacio(2))
                    vista.mostrarMensaje("El campo de busqueda no debe estar vacío", ERROR_MESSAGE);
                
                else {
                    vista.cargarTablaNuevaInfo(buscarRegistro());
                }
                break;
                
            case "jbMostrarTodo" :
                //TODO : Me falta el botón mostrar todo.
                break;
                
            default :
                //Valido los datos.
                if (vista.estaVacio(1)) {
                     vista.mostrarMensaje("Los campos no deben estar vacíos", ERROR_MESSAGE);
                     return;
                
                //Bloque de codigo para eliminar registro.
                } else if (command.equals ("jbEliminar")) {
                    modelo.eliminarEmpresa(vista.listEmpresa.get(indexFila).getId());
                    vista.mostrarMensaje ("Se ha eliminado la empresa correctamente.", INFORMATION_MESSAGE);
                    actualizar_eliminar = false;
                    vista.configurarBotones(false, "Guardar");
                    
                //Bloque de codigo para actualizar y insertar.
                } else {
                    //Actualizar registro.
                    if (actualizar_eliminar) {
                        modelo.actualizarEmpresa(vista.guardarDatos());
                        vista.mostrarMensaje("Se ha actualizado la empresa correctamente", INFORMATION_MESSAGE);
                        vista.configurarBotones(false, "Guardar");
                        actualizar_eliminar = false;
                    
                    //Insertar registro.
                    } else {
                        modelo.insertarEmpresa(vista.guardarDatos());
                        vista.mostrarMensaje("Se ha guardado la empresa correctamente", INFORMATION_MESSAGE);
                    }
                }
                //Actualizo la vista.
                vista.limpiarCampos();
                vista.cargarTablaTodasLasEmpresas();
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            //Guardo la fila seleccionada.
            indexFila = ((JTable)e.getSource()).getSelectedRow();
            //Activado para poder eliminar o actualizar.
            actualizar_eliminar = true;
            //Activo los botones eliminar y actualizar.
            vista.configurarBotones(true, "Actualizar");
            vista.cargarDatos(indexFila);
        }
    }
    
    private List <Empresa> buscarRegistro () {
        final String where;
        
        switch(vista.radioButtonSeleccionado()) {
            //Selecciono email.
            case "Email" :
                where = "WHERE UPPER(email) LIKE UPPER(CONCAT(?, '%'))";
                break;
               
            //Selecciono empresa.    
            default:
                //TODO : Probar porque no me sale la consulta.
                 where = "WHERE UPPER(nombre) LIKE UPPER(CONCAT(?, '%'))"; 
                 break;
        }
        
        return modelo.obtenerEmpresa(where, vista.buscarRegistro());
    }
}
