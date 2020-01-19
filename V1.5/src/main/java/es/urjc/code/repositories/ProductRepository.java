package es.urjc.code.repositories;

import es.urjc.code.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para productos.
 *
 *  @author J. Manuel Colmenar
 */
public interface ProductRepository extends JpaRepository<Product, Long> {


}
