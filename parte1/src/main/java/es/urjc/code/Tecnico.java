package es.urjc.code;

import javax.persistence.*;
import java.util.List;

/**
 * Clase con datos sobre técnicos.
 *
 * @author J. M. Colmenar
 */
@Entity
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;
    private int nivel;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="tecnico")
    private List<Chat> chats;

    private String habilidades;

    public Tecnico() {}

    public Tecnico(String nombre, int nivel, List<Chat> chats) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.chats = chats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", habilidades=" + habilidades +
                '}';
    }
}
