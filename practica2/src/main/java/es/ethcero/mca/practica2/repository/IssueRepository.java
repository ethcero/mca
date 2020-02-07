
package es.ethcero.mca.practica2.repository;

import es.ethcero.mca.practica2.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author fran
 */

public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query(value = "SELECT * FROM issues i WHERE i.client_id=?1", nativeQuery = true)
    List<Issue> findByClientId(long clientId);
}
