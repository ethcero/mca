
package es.ethcero.ann.practica2.service.notification.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import es.ethcero.ann.practica2.domain.common.Money;
import es.ethcero.ann.practica2.service.notification.NotificationService;

/**
 * @author fran
 */
public class NotificationServiceProxy implements NotificationService {

    @Autowired
    RestTemplate restTemplate;

    private String baseUrlNotificator(String path) {
        return ServiceDestination.NOTIFICATOR_URI + "/" + path;
    }


    public void notify(String name, Money amount, Money total) {
        restTemplate.postForObject(baseUrlNotificator("notify/"),
                new NotifyRequest(name, amount, total), ResponseEntity.class);
    }

}
