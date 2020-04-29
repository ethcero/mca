
package es.ethcero.ann.practica2.notificationservice.service;

import org.springframework.stereotype.Service;

import es.ethcero.ann.practica2.notificationservice.domain.common.Money;

/**
 * @author fran
 */
@Service
public class NotificationService {


    public void notify(String name, Money added, Money total) {
        System.out.println(String.format("%s, su crédito se ha aumentado en %.0f y tienes un total de %.0f", name, added.getAmount(), total.getAmount()));
    }
}
