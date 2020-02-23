package es.codeurjc.daw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.codeurjc.daw.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
