package es.ethcero.mca.blogcero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import es.ethcero.mca.blogcero.models.Comment;

/**
 * @author fran
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByAuthorId(Long authorId);
    List<Comment> findByPostId(Long postId);

}
