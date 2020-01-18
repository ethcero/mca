package es.urjc.code.repositories;

import es.urjc.code.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

   // @Query("select c from chat c join client cl ON c.client=cl.id where cl.email = ?1")
    List<Chat> findByClientEmail(String email);
}
