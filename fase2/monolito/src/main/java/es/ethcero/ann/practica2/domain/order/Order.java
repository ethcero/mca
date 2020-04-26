
package es.ethcero.ann.practica2.domain.order;

import javax.persistence.Embedded;
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
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OrderDetails orderDetails;

    public Order(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public static Order createOrder(OrderDetails orderDetails) {
        return new Order(orderDetails);
    }
}
