package es.urjc.code;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para chats.
 *
 *  @author J. Manuel Colmenar
 */
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByCliente(Cliente c1);

}
