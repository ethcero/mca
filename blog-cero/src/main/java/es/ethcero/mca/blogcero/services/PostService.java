package es.ethcero.mca.blogcero.services;

import es.ethcero.mca.blogcero.models.Comment;
import es.ethcero.mca.blogcero.models.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class PostService {

    Map<Long, Post> posts = new ConcurrentHashMap<>();
    AtomicLong lastId = new AtomicLong();

    public List<Post> getPosts() {
        return new ArrayList<>(this.posts.values());
    }

    public Post getPost(long id) {
        return this.posts.get(id);
    }

    public Post addPost(Post post) {

        post.setId(lastId.incrementAndGet());
        this.posts.put(post.getId(), post);
        return post;
    }

    public Comment deleteComment(long postId, long commentId) {

        Post post = this.posts.get(postId);
        if( post != null) {
            return post.removeComment(commentId);
        }

        return null;
    }

    public Comment addComment(long postId, Comment comment) {
        Post post = this.posts.get(postId);
        if( post != null) {
            comment.setId(lastId.incrementAndGet());
            return post.addComment(comment);
        }

        return null;
    }

    public Comment getComment(long postId, long commentId) {
        Post post = this.posts.get(postId);
        if( post != null) {
            return post.getComment(commentId);
        }

        return null;
    }

    public List<Comment> getComments(long postId) {
        Post post = this.posts.get(postId);
        if( post != null) {
            return post.getComments();
        }

        return null;
    }

}
