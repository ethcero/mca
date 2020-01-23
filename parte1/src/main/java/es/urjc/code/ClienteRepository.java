package es.urjc.code;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para clientes.
 *
 *  @author J. Manuel Colmenar
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByEmail(String email);

}
