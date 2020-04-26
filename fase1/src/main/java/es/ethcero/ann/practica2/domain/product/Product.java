
package es.ethcero.ann.practica2.domain.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author fran
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int stock;

    public Product(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public void reserveStock(int quantity) {
        if(quantity <= stock) {
            stock = stock - quantity;
        }else{
            throw new InsufficientStockException();
        }
    }
}
