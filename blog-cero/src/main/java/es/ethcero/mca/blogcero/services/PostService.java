package es.ethcero.mca.blogcero.services;

import es.ethcero.mca.blogcero.restModels.NewComment;
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

    public Optional<Comment> addComment(long postId, NewComment comment) {
        Comment comment1 = new Comment();
        comment1.setBody(comment.getBody());

        Optional<Author> authorOp = this.authorRepository.findById(comment.getAuthorId());

        if(!authorOp.isPresent()) {
            return Optional.empty();
        }
        if(!this.postRepository.existsById(postId)) {
            return Optional.empty();
        }

        comment1.setAuthor(authorOp.get());
        comment1.setPostId(postId);
        this.commentRepository.save(comment1);

        return Optional.of(comment1);
    }

    public Optional<Comment> getComment(long commentId) {
        return this.commentRepository.findById(commentId);
    }

    public List<Comment> getComments(long postId) {
        return this.commentRepository.findByPostId(postId);
    }


}
