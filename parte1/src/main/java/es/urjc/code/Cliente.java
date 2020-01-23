package es.urjc.code;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Clase para cliente.
 *
 * @author J. M. Colmenar
 */
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private String apellidos;
    private String email;
    private String ciudad;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="cliente")
    private List<Chat> chats;

    // Relación
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    private List<ClienteProducto> productos = new ArrayList<>();

    public Cliente() {}

    public Cliente(String nombre, String apellidos, String email, String ciudad, List<Chat> chats) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.ciudad = ciudad;
        this.chats = chats;
    }

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public List<ClienteProducto> getProductos() {
        return productos;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre='" + nombre + '\'' + ", apellidos='" + apellidos + '\'' + ", email='" + email + '\'' + ", ciudad='" + ciudad + '\''
                + ", productos=" + productos + '}';
    }
}
