package es.urjc.code.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date datetime;
    private char author;
    @ManyToOne
    @JoinColumn(name="client")
    private Client client;
    @ManyToOne
    @JoinColumn(name="technician")
    private Technician technician;
    @ManyToOne
    @JoinColumn(name="product")
    private Product product;
    private String message;

    public Chat(Date datetime, char author, Client client, Technician technician, Product product, String message) {
        this.datetime = datetime;
        this.author = author;
        this.client = client;
        this.technician = technician;
        this.product = product;
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Chat{");
        sb.append("id=").append(id);
        sb.append(", datetime=").append(datetime);
        sb.append(", author=").append(author);
        sb.append(", client=").append(client);
        sb.append(", technician=").append(technician);
        sb.append(", product=").append(product);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
