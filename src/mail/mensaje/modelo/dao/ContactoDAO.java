package mail.mensaje.modelo.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mail.mensaje.modelo.conexion.Conexion;
import mail.mensaje.modelo.vo.Contacto;
import mail.mensaje.modelo.vo.Empresa;

/**
 * @author Félix Pedrozo
 */
public class ContactoDAO {
    private final Conexion conexion = new Conexion();
    private PreparedStatement statement = null;
    
    private final String query = "SELECT ct.id, ct.nombre, ct.apellido, ct.email, em.nombre "
            + "FROM contacto AS ct INNER JOIN empresa AS em ON ct.empresa = em.id";
    
    public void insertarContacto (Contacto contacto) {
        final String sql = "INSERT INTO contacto (nombre, apellido, email, empresa) VALUES (?,?,?,?)";
        
        try {
            Connection dbConexion = conexion.abrir();
            statement = dbConexion.prepareStatement(sql);
            
            //Le paso los datos a la sentencia.
            statement.setString(1, contacto.getNombre());
            statement.setString(2, contacto.getApellido());
            statement.setString(3, contacto.getEmail());
            statement.setLong(4, contacto.getEmpresa().getId());
            
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            //No hace nada.
            
        } finally {
            cerrarTodo ();
        }
    }
    
    public void eliminarContacto (long id) {
        String sql = "DELETE FROM contacto WHERE id = ?";
        
        try {
            Connection dbConnection = conexion.abrir();
            statement = dbConnection.prepareStatement(sql);
            
            statement.setLong(1, id);
            
            statement.executeUpdate();
            
        } catch (SQLException e) {
            //No hace nada.
            
        } finally {
            cerrarTodo();
        }
    }
    
    public void actualizarContacto (Contacto contacto) {
        String sql = "UPDATE contacto SET nombre = ?, apellido = ?,"
                + "email = ?, empresa = ? WHERE id = ?";
        
        try {
            Connection dbConnection = conexion.abrir();
            statement  = dbConnection.prepareStatement(sql);
            
            statement.setString(1, contacto.getNombre());
            statement.setString(2, contacto.getApellido());
            statement.setString(3, contacto.getEmail());
            statement.setLong(4, contacto.getEmpresa().getId());
            statement.setLong(5, contacto.getId());
                    
            statement.executeUpdate();
            
        } catch (SQLException ex) {
            //No hace nada.
            
        } finally {
            cerrarTodo ();
        }
    }
    
    public List <Contacto> todosLosContacto () {
        final String sql = String.format("%s ORDER BY ct.nombre, ct.apellido", query);
        
        List <Contacto> list = new ArrayList <>();
        
        try {
            Connection dbConnection = conexion.abrir();
            statement = dbConnection.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            
            //Inserto los registros a la lista de contactos.
            while (rs.next())
                list.add(convertir(rs));
            
        } catch (SQLException ex) {
            //No hace nada.
            
        } finally {
            cerrarTodo();
        }

        return list;
    }
    
    public List <Contacto> obtenerContacto (String where, String atributo) {
        final String sql = String.format("%s %s ORDER BY ct.nombre, ct.apellido", query, where);
        List <Contacto> listContacto = new ArrayList<>();
        
        try {
            Connection connection = conexion.abrir();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, atributo);
            ResultSet rs = statement.executeQuery();
            
            //Inserto los registros a la lista de contactos.
            while (rs.next())
                listContacto.add(convertir(rs));
            
        } catch (SQLException e) {
            //No hace nada.
            
        } finally {
            cerrarTodo();
        }
        
        return listContacto;
    }
    
    private Contacto convertir (ResultSet rs) throws SQLException {
        Contacto contacto = new Contacto ();
        
        try {
             //Cargo el objeto contacto.
            contacto.setId(rs.getLong(1));
            contacto.setNombre(rs.getString(2));
            contacto.setApellido(rs.getString(3));
            contacto.setEmail(rs.getString(4));

            //Creo un objeto empresa.
            Empresa empresa = new Empresa();
            empresa.setNombre(rs.getString(5));

            //Inserto el objeto empresa al objeto contacto.
            contacto.setEmpresa(empresa);
            
        } catch (SQLException ex) {
            throw ex;
        }
        
        return contacto;
    }
    
    private void cerrarTodo () {
        try {
            conexion.cerrar();
        } finally {
            try {
                if (statement != null) { 
                    statement.close();
                    
                    //Elimino la referencia que posee.
                    statement = null;
                }
                
            } catch (SQLException ex) {
                //No hace nada.
            }
        }
    }
}
