package es.ethcero.mca.blogcero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import es.ethcero.mca.blogcero.models.Author;
import es.ethcero.mca.blogcero.models.Comment;
import es.ethcero.mca.blogcero.models.Post;
import es.ethcero.mca.blogcero.repository.AuthorRepository;
import es.ethcero.mca.blogcero.repository.CommentRepository;
import es.ethcero.mca.blogcero.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AuthorRepository authorRepository;

    public List<Post> getPosts() {

        return this.postRepository.findAll();
    }

    public Optional<Post> getPost(long id) {

        return this.postRepository.findById(id);
    }

    public Post addPost(Post post) {

        this.postRepository.save(post);
        return post;
    }

    public void deleteComment(long commentId) {
        this.commentRepository.deleteById(commentId);
    }

    public Optional<Comment> addComment(long postId, Comment comment) {

        Optional<Post> post = this.postRepository.findById(postId);
        if( post.isPresent() && this.authorRepository.existsById(comment.getAuthor().getId())) {
            post.get().addComment(comment);
            this.postRepository.save(post.get());

            return Optional.of(comment);
        }

        return Optional.empty();
    }

/*    public Optional<Comment> getComment(long postId, long commentId) {
        Optional<Post> post = this.postRepository.findById(postId);
        if( post.isPresent()) {
            return Optional.ofNullable(post.get().getComment(commentId));
        }

        return Optional.empty();
    }
*/
    public Optional<List<Comment>> getComments(long postId) {
        Optional<Post> post = this.postRepository.findById(postId);

        return post.isPresent() ? Optional.of(post.get().getComments()): Optional.empty();

    }

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
