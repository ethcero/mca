package es.urjc.code;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repositorio para productos.
 *
 *  @author J. Manuel Colmenar
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(value = "select distinct p.* from producto p,JSON_TABLE(historia_precios,'$[*]' COLUMNS (pr varchar(20) PATH '$.precio')) tt where pr < ?1",
    nativeQuery = true)
    List<Producto> findByPrecioLessThan(double precio);

    Producto findProductoByNombre(String nombre);

    @Modifying
    @Transactional
    @Query("UPDATE Producto p SET p.historiaPrecios = FUNCTION('JSON_ARRAY_APPEND',historia_precios, '$', ?2) WHERE p=?1")
    void addHistoria(Producto p2, String dato);

}
