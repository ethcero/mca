
package es.ethcero.ann.practica2.service.notification.proxy;

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
public class NotifyRequest {

    private String name;
    private Money amount;
    private Money total;
}
