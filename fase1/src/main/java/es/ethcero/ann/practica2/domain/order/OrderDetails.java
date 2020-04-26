
package es.ethcero.ann.practica2.domain.order;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import es.ethcero.ann.practica2.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author fran
 */
@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    private long customerId;
    private long productId;
    private int quantity;
    @Embedded
    private Money orderTotal;
}
