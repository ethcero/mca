package es.urjc.code;

import javax.persistence.*;
import java.util.List;

/**
 * Clase con datos sobre productos.
 *
 * @author J. Manuel Colmenar
 */
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private String marca;
    private double precio;

    private String historiaPrecios;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="producto")
    private List<Chat> chats;

    public Producto() {
    }

    public Producto(String nombre, String marca, double precio, List<Chat> chats) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public String getHistoriaPrecios() {
        return historiaPrecios;
    }

    public void setHistoriaPrecios(String historiaPrecios) {
        this.historiaPrecios = historiaPrecios;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                ", historiaPrecios='" + historiaPrecios + '\'' +
                '}';
    }
}
