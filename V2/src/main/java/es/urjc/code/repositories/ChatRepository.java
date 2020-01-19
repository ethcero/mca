package es.urjc.code.repositories;

import es.urjc.code.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

   @Query(value = "SELECT chat.id, chat.data FROM client c JOIN chat JOIN JSON_TABLE(chat.data, \"$\" COLUMNS(client_id bigint PATH \"$.client\")) AS chat_client ON c.id=chat_client.client_id WHERE c.email = ?1", nativeQuery = true)
   List<Chat> findByClientEmail(String email);

   @Query(value = "select * from chat c, JSON_TABLE(c.data, \"$.chat\" COLUMNS(date timestamp PATH \"$[*].date\")) AS chat_date where chat_date.date = TIMESTAMP(?1)", nativeQuery = true)
   List<Chat> findByDate(Date date);

    @Query(value = "SELECT chat.id, chat.data FROM product p JOIN chat JOIN JSON_TABLE(chat.data, \"$\" COLUMNS(product_id bigint PATH \"$.product\")) AS chat_product ON p.id=chat_product.product_id WHERE p.name = ?1", nativeQuery = true)
    List<Chat> findByProductName(String name);

    @Transactional
    @Modifying
    @Query( value = "UPDATE chat c SET c.data = JSON_ARRAY_APPEND(c.data, '$.chat', JSON_OBJECT('date',?4, 'author', ?5, 'message', ?6)) WHERE c.data->\"$.client\" = ?1 AND c.data->\"$.technician\" = ?2 AND c.data->\"$.product\" = ?3", nativeQuery = true)
    void addChatLine(long client, long technician, long product, String date, String author, String message);
}
