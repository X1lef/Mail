package mail.mensaje.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que contiene la configuración para conectar y desconectar de la base de datos.
 *
 * @author Félix Pedrozo
 */
public class Conexion {
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/Mail";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "001122334455";
    
    private Connection conexion = null;
    
    public Connection abrir () {
        try {
            conexion = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        
        } catch (SQLException ex) {}
        
        return conexion;
    }
    
    public void cerrar () {
        try {
            if (conexion != null) {
                conexion.close();
                
                //Elimino la referencia que posee.
                conexion = null;
            }
            
        } catch (SQLException ex) {}
    }
}
