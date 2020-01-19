package es.urjc.code.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition = "json")
    private String data;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Chat{");
        sb.append("id=").append(id);
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
