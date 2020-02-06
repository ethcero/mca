
package es.ethcero.mca.practica2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import es.ethcero.mca.practica2.model.Issue;

/**
 * @author fran
 */

public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findByClientId(long clientId);
}
