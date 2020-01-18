package es.urjc.code.repositories;

import es.urjc.code.entities.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {


    //@Query("select t from technician t where t.level < ?1 AND (SELECT count(*) FROM chat WHERE technician = t.id) = 0")
    //List<Technician> findByNotAttendedAndLevelLessThan(int level);
}
