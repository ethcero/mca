
package es.urjc.code;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author fran
 */

@Embeddable
public class ClienteProductoId implements Serializable {

    private Long clienteId;
    private Long productoId;

    public ClienteProductoId() {
    }
}
