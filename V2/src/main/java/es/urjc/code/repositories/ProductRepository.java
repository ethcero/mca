package es.urjc.code.repositories;

import es.urjc.code.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repositorio para productos.
 *
 *  @author J. Manuel Colmenar
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    @Query(value = "SELECT * FROM product p, JSON_TABLE(p.historic_price, \"$[*]\" COLUMNS(price DOUBLE(10,4) PATH \"$.price\")) historic_price WHERE historic_price.price < ?1", nativeQuery = true)
    List<Product> findByHistoricPriceLessThan(double price);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET historic_price = JSON_ARRAY_APPEND(historic_price, '$', JSON_OBJECT('date', ?2, 'price', ?3)) WHERE name = ?1", nativeQuery = true)
    void pushPriceToHistoric(String name, String date, double price);

}
