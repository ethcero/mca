package es.urjc.code.repositories;

import es.urjc.code.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositorio para productos.
 *
 *  @author J. Manuel Colmenar
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product p, JSON_TABLE(p.historic_price, \"$[*]\" COLUMNS(price DOUBLE(10,4) PATH \"$.price\")) historic_price WHERE historic_price.price < ?1", nativeQuery = true)
    List<Product> findByHistoricPriceLessThan(double price);

}
