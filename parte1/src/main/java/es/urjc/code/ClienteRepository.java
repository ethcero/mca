package es.urjc.code;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio para clientes.
 *
 *  @author J. Manuel Colmenar
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByEmail(String email);

    @Query(value = " select ciudad\n" + "    from cliente c join cliente_producto cp\n" + "    on c.id=cp.cliente_id\n" + "    group by ciudad\n" + "    having count(ciudad) = (\n"
            + "        SELECT max(total)\n" + "        FROM (\n" + "            select ciudad,count(*) as total\n" + "            from cliente c join cliente_producto cp\n"
            + "            on c.id=cp.cliente_id\n" + "            group by ciudad\n" + "            ) as c\n" + "        );",
    nativeQuery = true)
    String ciudadClientesMasCompras();

    @Query("SELECT new es.urjc.code.ClienteTotalDTO(c.nombre,c.apellidos,count(*)) FROM Cliente c JOIN ClienteProducto cp ON c.id=cp.cliente.id "
            + "    JOIN Servicio s ON s.id=cp.producto.id GROUP BY ciudad")
    List<ClienteTotalDTO> totalServiciosPorCliente();
}
