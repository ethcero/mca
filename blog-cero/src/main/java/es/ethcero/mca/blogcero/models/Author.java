package es.ethcero.mca.blogcero.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import es.ethcero.mca.blogcero.Views.Views;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author fran
 */
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Basic.class)
    private long id;

    @JsonView(Views.Basic.class)
    private String name;

    private int age;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    public long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {

        return name;
    }

}
