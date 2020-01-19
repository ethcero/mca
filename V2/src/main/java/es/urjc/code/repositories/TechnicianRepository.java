package es.urjc.code.repositories;

import es.urjc.code.entities.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {


    @Query(value = " SELECT * FROM technician t\n" +
            "         WHERE t.level < ?1\n" +
            "         AND (select count(*) \n" +
            "            from chat,JSON_TABLE(chat.data, \"$\" COLUMNS(tech_id bigint PATH \"$.technician\")) AS chat_tech \n" +
            "            where t.id = chat_tech.tech_id ) = 0", nativeQuery = true)
    List<Technician> findByNotAttendedAndLevelLessThan(int level);

    @Query(value = "SELECT * FROM technician t WHERE JSON_CONTAINS(t.labels,?1,'$')=1", nativeQuery = true)
    List<Technician> FindByLabel(String label);

    @Transactional
    @Modifying
    @Query(value = "    UPDATE technician \n" +
            "    SET labels = JSON_REMOVE(labels, \n" +
            "            JSON_UNQUOTE(JSON_SEARCH(labels, 'one', ?1))) \n" +
            "    WHERE JSON_SEARCH(labels, 'one', ?1) IS NOT NULL AND level > ?2", nativeQuery = true)
    void deleteLabelWhenLevelMoreThan(String label, int level);
}


