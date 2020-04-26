
package es.ethcero.ann.practica2.web.customer;

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
public class CreateCustomerRequest {

    private String name;
    private Money credit;
}
