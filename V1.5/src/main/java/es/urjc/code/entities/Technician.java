package es.urjc.code.entities;


import javax.persistence.*;

@Entity
public class Technician {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private int level;

    @Column(columnDefinition = "json")
    private String labels;

    public Technician(){}

    public Technician(String firstName, int level) {
        this.firstName = firstName;
        this.level = level;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Technician{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", level=").append(level);
        sb.append(", labels='").append(labels).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
