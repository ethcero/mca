package es.ethcero.mca.blogcero.models;

import com.fasterxml.jackson.annotation.JsonView;
import es.ethcero.mca.blogcero.Views.Views;

import javax.persistence.*;

@Entity
public class Comment {

    public interface NoAuthor{}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.Basic.class, NoAuthor.class})
    private long id;

    @ManyToOne
    private Author author;

    @JsonView({Views.Basic.class, NoAuthor.class})
    private Long postId;

    @JsonView({Views.Full.class, NoAuthor.class})
    private String body;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public long getId() {
        return id;
    }

    @JsonView(Views.Basic.class)
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
