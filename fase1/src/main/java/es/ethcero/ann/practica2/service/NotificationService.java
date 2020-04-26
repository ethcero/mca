
package es.ethcero.ann.practica2.service;

import org.springframework.stereotype.Service;

import es.ethcero.ann.practica2.domain.common.Money;

/**
 * @author fran
 */
@Service
public class NotificationService {


    public void notify(String name, Money added, Money total) {
        System.out.println(String.format("%s, su crédito se ha aumentado en %.0f y tienes un total de %.0f", name, added.getAmount(), total.getAmount()));
    }
}
