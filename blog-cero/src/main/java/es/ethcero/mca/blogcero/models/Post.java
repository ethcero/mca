package es.ethcero.mca.blogcero.models;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Post {

    public interface Basic {}

    @JsonView(Basic.class)
    private long id;
    @JsonView(Basic.class)
    private String title;
    private String body;
    private Map<Long, Comment> comments = new ConcurrentHashMap<>();

    public Post(){}

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments.values());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Comment getComment(long id) {
        return this.comments.get(id);
    }

    public Comment addComment(Comment comment) {
       this.comments.put(comment.getId(), comment);
       return this.comments.get(comment.getId());
    }

    public Comment removeComment(long id) {
        return this.comments.remove(id);
    }
}
