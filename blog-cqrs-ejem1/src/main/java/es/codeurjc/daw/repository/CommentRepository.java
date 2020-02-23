package es.codeurjc.daw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.codeurjc.daw.model.Comment;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long>{

}
