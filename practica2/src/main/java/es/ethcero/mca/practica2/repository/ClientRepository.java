
package es.ethcero.mca.practica2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

import es.ethcero.mca.practica2.model.Client;

/**
 * @author fran
 */


public interface ClientRepository extends JpaRepository<Client, Long> {

}
