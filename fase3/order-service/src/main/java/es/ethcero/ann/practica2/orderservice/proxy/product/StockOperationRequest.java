
package es.ethcero.ann.practica2.orderservice.proxy.product;

import es.ethcero.ann.practica2.orderservice.common.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author fran
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockOperationRequest {

    private Operation operation;
    private int quantity;
}
