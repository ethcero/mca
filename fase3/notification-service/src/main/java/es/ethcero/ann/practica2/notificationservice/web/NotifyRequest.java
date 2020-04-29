
package es.ethcero.ann.practica2.notificationservice.web;

import es.ethcero.ann.practica2.notificationservice.domain.common.Money;
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
