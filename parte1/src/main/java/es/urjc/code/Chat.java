package es.urjc.code;

import javax.persistence.*;
import java.util.Date;

/**
 * Clase para chat.
 *
 * @author J. M. Colmenar
 */
@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Tecnico tecnico;

    private char autor;
    private Date fecha;
    private String texto;

    public Chat() {}

    public Chat(Producto producto, Cliente cliente, Tecnico tecnico, char autor, Date fecha, String texto) {
        this.producto = producto;
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.autor = autor;
        this.fecha = fecha;
        this.texto = texto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public char getAutor() {
        return autor;
    }

    public void setAutor(char autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Chat (id=" + id + ")" +
                "\n\tproducto=" + producto.getNombre() + " " + producto.getMarca() +
                "\n\tcliente=" + cliente.getNombre()+ " " + cliente.getApellidos() +
                "\n\ttecnico=" + tecnico.getNombre() + " (Nivel " + tecnico.getNivel() + ")"+
                "\n\tautor=" + (autor == 'C' ? "Cliente" : "Técnico" ) +
                "\n\tfecha=" + fecha +
                "\n\ttexto='" + texto + '\'' +
                '}';
    }
}
