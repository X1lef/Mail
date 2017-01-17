package mail.mensaje.modelo.vo;

import java.util.Objects;

/**
 * @author Félix Pedrozo
 */
public class Empresa {
    private long id;
    private String nombre, email, direccion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String [] toArray () {
        return new String [] {nombre, direccion, email};
    }
    
    @Override
    public String toString () {
        return nombre;
    }
    
    @Override
    public boolean equals (Object obj) {
        if (obj != null && obj instanceof Empresa) {
            Empresa empresa = (Empresa)obj;
            if (empresa.getId() == id || Objects.equals(empresa.nombre, nombre))
                return true;
        }
        
        return false;
    }
}
