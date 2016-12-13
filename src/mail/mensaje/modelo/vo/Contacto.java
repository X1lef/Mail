package mail.mensaje.modelo.vo;

import java.util.Objects;

/**
 * @author Félix Pedrozo
 */
public class Contacto {
    private long id;
    private String nombre, apellido, email;
    private Empresa empresa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public String [] toArray () {
        return new String [] {nombre, apellido, email, empresa.getNombre()};
    }
    
    @Override
    public String toString () {
        return String.format("%s %s", nombre, apellido);
    }
    
    @Override
    public boolean equals (Object obj) {
        if (obj != null && obj instanceof Contacto) {
            Contacto contacto = (Contacto)obj;
            if (contacto.getId() == id || Objects.equals(contacto.nombre, nombre))
                return true;
        }
        return false;
    }
}
