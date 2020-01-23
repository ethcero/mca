
package es.urjc.code;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 * @author fran
 */
@Entity
public class ClienteProducto {

    @EmbeddedId
    private ClienteProductoId id;

    @ManyToOne
    @MapsId("clienteId")
    private Cliente cliente;

    @ManyToOne
    @MapsId("productoId")
    private Producto producto;

    private Date fechaCompra;

    public ClienteProducto() {
    }

    public ClienteProducto(ClienteProductoId id, Cliente cliente, Producto producto, Date fechaCompra) {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        this.fechaCompra = fechaCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    @Override
    public String toString() {
        return "ClienteProducto{" + "id=" + id + ", producto=" + producto + ", fechaCompra=" + fechaCompra + '}';
    }
}
