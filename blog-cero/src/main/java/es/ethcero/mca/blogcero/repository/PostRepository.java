package es.ethcero.mca.blogcero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ethcero.mca.blogcero.models.Post;

/**
 * @author fran
 */
public interface PostRepository extends JpaRepository<Post, Long> {

}
