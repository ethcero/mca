package es.urjc.code.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Entidad Product.
 *
 * @author J. Manuel Colmenar
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String brand;
    private Double price;

    @Column(columnDefinition = "json")
    private String historicPrice;

    public Product() {
    }

    public Product(String name, String brand, Double price) {
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", price=").append(price);
        sb.append(", historicPrice='").append(historicPrice).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void setHistoricPrice(String historicPrice) {
        this.historicPrice = historicPrice;
    }

}
