package es.urjc.code.repositories;

import es.urjc.code.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

   List<Chat> findByClientEmail(String email);
}
