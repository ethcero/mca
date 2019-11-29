package es.ethcero.mca.blogcero.services;

import es.ethcero.mca.blogcero.models.Author;
import es.ethcero.mca.blogcero.models.Comment;
import es.ethcero.mca.blogcero.repository.AuthorRepository;
import es.ethcero.mca.blogcero.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CommentRepository commentRepository;

    public Author addAuthor(Author author) {
        this.authorRepository.save(author);
        return author;
    }

    public Optional<List<Comment>> getAuthorComments(long authorId) {

        if(this.authorRepository.existsById(authorId)){
            return Optional.of(this.commentRepository.findByAuthorId(authorId));
        }
        return Optional.empty();

    }

    public List<Author> getAuthors() {
        return this.authorRepository.findAll();
    }

}
