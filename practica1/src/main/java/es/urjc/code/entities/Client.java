package es.urjc.code.entities;

import javax.persistence.*;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String city;

    public Client(){}

    public Client(String firstName, String lastName, String email, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.city = city;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Client{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
