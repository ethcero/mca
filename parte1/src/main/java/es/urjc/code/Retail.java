package es.urjc.code;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author fran
 */
@Entity
@DiscriminatorValue(value="retail")
public class Retail extends Producto {

    String marca;
    String pais;

    public Retail() {
    }

    public Retail(String marca, String pais) {
        this.marca = marca;
        this.pais = pais;
    }

    public Retail(String nombre, double precio, List<Chat> chats, String marca, String pais) {
        super(nombre, precio, chats);
        this.marca = marca;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Retail{" + "marca='" + marca + '\'' + ", pais='" + pais + '\'' + "} " + super.toString();
    }
}
