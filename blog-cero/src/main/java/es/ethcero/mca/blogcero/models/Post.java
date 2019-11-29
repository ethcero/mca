package es.ethcero.mca.blogcero.models;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Post {

    public interface Basic {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Basic.class)
    private long id;
    @JsonView(Basic.class)
    private String title;
    private String body;

    @OneToMany
    private List<Comment> comments;

    public Post(){}

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public List<Comment> getComments() {
        return comments;
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

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
