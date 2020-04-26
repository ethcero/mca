
package es.ethcero.ann.practica2.web.order;

import es.ethcero.ann.practica2.domain.common.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author fran
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private long productId;
    private long customerId;
    private int quantity;
    private Money orderTotal;
}
