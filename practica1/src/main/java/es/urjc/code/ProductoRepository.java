package es.urjc.code;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio para productos.
 *
 *  @author J. Manuel Colmenar
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {


}
