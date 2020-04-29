
package es.ethcero.ann.practica2.notificationservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.ethcero.ann.practica2.notificationservice.service.NotificationService;

/**
 * @author fran
 */
@RestController
public class NotificationController {

   @Autowired
    NotificationService notificationService;

    @PostMapping("/notify")
    public ResponseEntity notify(@RequestBody NotifyRequest notifyRequest) {
        notificationService.notify(notifyRequest.getName(), notifyRequest.getAmount(), notifyRequest.getTotal());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
