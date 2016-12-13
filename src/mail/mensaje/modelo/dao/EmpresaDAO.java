package mail.mensaje.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mail.mensaje.modelo.conexion.Conexion;
import mail.mensaje.modelo.vo.Empresa;

/**
 * @author Félix Pedrozo
 */
public class EmpresaDAO {
    private final Conexion conexion = new Conexion();
    private PreparedStatement statement = null;
    
    public void insertarEmpresa (Empresa empresa) {
        String sql = "INSERT INTO empresa (nombre, email, direccion) VALUES (?,?,?)";
        
        try {
            Connection connection = conexion.abrir();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, empresa.getNombre());
            statement.setString(2, empresa.getEmail());
            statement.setString(3, empresa.getDireccion());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            //No hace nada.
        } finally {
            cerrarTodo();
        }
    }
    
    public void actualizarEmpresa (Empresa empresa) {
        String sql = "UPDATE empresa SET nombre = ?, direccion = ?, email = ? WHERE id = ?";
        
        try {
            Connection connection = conexion.abrir();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, empresa.getNombre());
            statement.setString(2, empresa.getDireccion());
            statement.setString(3, empresa.getEmail());
            statement.setLong(4, empresa.getId());
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            //No hace nada.
        } finally {
            cerrarTodo();
        }
    }
    
    public void eliminarEmpresa (long id) {
        String sql = "DELETE FROM empresa WHERE id = ?";
        
        try {
            Connection connection = conexion.abrir();
            statement = connection.prepareStatement(sql);
            
            statement.setLong(1, id);
            
            statement.executeUpdate();
        } catch (SQLException ex) {
            //No hace nada.
        } finally {
            cerrarTodo();
        }
    }
    
    public List <Empresa> todasLasEmpresas () {
        final String sql = "SELECT * FROM empresa ORDER BY nombre";
        ArrayList <Empresa> list = new ArrayList <>();
        
        try {
            Connection connection = conexion.abrir();
            statement = connection.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            
            //Inserto a la lista.
            while (rs.next())
                list.add(convertir(rs));
            
        } catch (SQLException ex) {
            //No hace nada.
        } finally {
            cerrarTodo();
        }
        
        return list;
    }
    
    public List <Empresa> obtenerContacto (String where, String atributo) {
        String query = String.format("SELECT * FROM empresa %s ORDER BY nombre", where);
        List <Empresa> listEmpresa = new ArrayList<>();
        
        try {
            Connection connection = conexion.abrir();
            statement = connection.prepareStatement(query);
            
            statement.setString(1, atributo);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next())
                listEmpresa.add(convertir(rs));
                
        } catch (SQLException e) {
            //No hace nada.
        } finally {
            cerrarTodo();
        }
        
        return listEmpresa;
    }
    
    private Empresa convertir (ResultSet rs) throws SQLException {
        Empresa empresa = new Empresa ();
        
        try {
            //Cargo el objeto empresa.
            empresa.setId(rs.getLong(1));
            empresa.setNombre(rs.getString(2));
            empresa.setEmail(rs.getString(3));
            empresa.setDireccion(rs.getString(4));
            
        } catch (SQLException ex) {
            throw ex;
        } 
        
        return empresa;
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
