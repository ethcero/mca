package es.urjc.code;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author fran
 */
@Entity
@DiscriminatorValue(value="servicio")
public class Servicio extends Producto {

    String proveedor;
    Date fecha_inicio;
    Date fecha_fin;
    Double promocion;

    public Servicio() {
    }

    public Servicio(String proveedor, Date fecha_inicio, Date fecha_fin, Double promocion) {
        this.proveedor = proveedor;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.promocion = promocion;
    }

    public Servicio(String nombre, double precio, List<Chat> chats, String proveedor, Date fecha_inicio, Date fecha_fin, Double promocion) {
        super(nombre, precio, chats);
        this.proveedor = proveedor;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.promocion = promocion;
    }

    @Override
    public String toString() {
        return "Servicio{" + "proveedor='" + proveedor + '\'' + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", promocion=" + promocion + "} " + super
                .toString();
    }
}
