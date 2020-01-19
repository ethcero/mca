package es.urjc.code.repositories;

import es.urjc.code.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

   @Query(value = "SELECT chat.id, chat.data FROM client c JOIN chat JOIN JSON_TABLE(chat.data, \"$\" COLUMNS(client_id bigint PATH \"$.client\")) AS chat_client ON c.id=chat_client.client_id WHERE c.email = ?1", nativeQuery = true)
   List<Chat> findByClientEmail(String email);
}
