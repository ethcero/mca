package es.ethcero.mca.blogcero.models;

import com.fasterxml.jackson.annotation.JsonView;
import es.ethcero.mca.blogcero.Views.Views;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Basic.class)
    private long id;

    @JsonView(Views.Basic.class)
    private String title;

    @JsonView(Views.Full.class)
    private String body;

    @OneToMany(mappedBy = "postId", fetch = FetchType.EAGER)
    @JsonView(Views.Full.class)
    private Set<Comment> comments = new HashSet<>();

    public Set<Comment> getComments() {
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
