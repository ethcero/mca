package es.ethcero.mca.blogcero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ethcero.mca.blogcero.models.Author;

/**
 * @author fran
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
