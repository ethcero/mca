
package es.ethcero.mca.practica2.repository;

import es.ethcero.mca.practica2.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fran
 */


public interface ClientRepository extends JpaRepository<Client, Long> {

}
