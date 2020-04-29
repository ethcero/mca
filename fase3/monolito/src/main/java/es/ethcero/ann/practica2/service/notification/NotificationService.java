
package es.ethcero.ann.practica2.service.notification;

import es.ethcero.ann.practica2.domain.common.Money;

/**
 * @author fran
 */
public interface NotificationService {


    void notify(String name, Money amount, Money total);
}
